


try:
    import sklearn
except ImportError:
    import subprocess
    import sys
    subprocess.check_call([sys.executable, "-m", "pip", "install", "scikit-learn"])
    import sklearn  # vuelve a intentar importarlo ahora que está instalado

try:
    import pandas
except ImportError:
    import subprocess
    import sys
    subprocess.check_call([sys.executable, "-m", "pip", "install", "pandas"])
    import pandas  # vuelve a intentarlo


import sys
import pandas as pd

from sklearn.linear_model import LinearRegression
from sklearn.neighbors import KNeighborsRegressor
from sklearn.model_selection import train_test_split
from sklearn.metrics import r2_score, mean_absolute_error, mean_squared_error

# Leer el dataset
csv_path = sys.argv[1]
df = pd.read_csv(csv_path)
df_noduplicates = df.drop_duplicates()

df_noduplicates['airportType'] = df_noduplicates['airportType'].replace({
    'Departure': 'Salida',
    'Arrival': 'Llegada'
})

# Lista para guardar resultados
resultados = []

# Iterar por tipo de aeropuerto y nombre
for airportType in df_noduplicates['airportType'].unique():
    for airportName in df_noduplicates['airportName'].unique():

        subdf = df_noduplicates[
            (df_noduplicates["airportType"] == airportType) &
            (df_noduplicates["airportName"] == airportName)
        ]

        # Saltar si no hay datos suficientes
        if len(subdf) < 5:
            continue

        print("-------------------------------------")
        print(f"{airportName} + {airportType}")

        X = subdf.iloc[:, 4:-2].values
        y = subdf.iloc[:, -1].values

        # Modelos a probar
        modelos = [
            ("LinearRegression", LinearRegression()),
            ("KNNRegressor", KNeighborsRegressor(n_neighbors=5))
        ]

        for nombre_modelo, clf in modelos:
            Xtrain, Xtest, ytrain, ytest = train_test_split(X, y, test_size=0.2, random_state=2468)
            clf.fit(Xtrain, ytrain)
            ypred = clf.predict(Xtest)

            # Métricas
            r2 = r2_score(ytest, ypred)
            mae = mean_absolute_error(ytest, ypred)
            mse = mean_squared_error(ytest, ypred)

            print(nombre_modelo)
            print("R2 score:", r2)
            print("MAE:", mae)
            print("MSE:", mse)

            # Guardar resultado
            resultados.append({
                "airportType": airportType,
                "airportName": airportName,
                "model": nombre_modelo,
                "R2": round(r2,4),
                "MAE": round(mae,4),
                "MSE": round(mse,4)
            })

# Guardar a CSV
output_df = pd.DataFrame(resultados)
output_df.to_csv(sys.argv[2], index=False)
print(f"\n Resultados guardados en {sys.argv[2]}")

