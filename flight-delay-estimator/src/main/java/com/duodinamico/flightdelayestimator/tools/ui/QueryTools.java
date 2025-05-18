package com.duodinamico.flightdelayestimator.tools.ui;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class QueryTools {

    public Map<String, String> pedirFiltros() {
        Scanner sc = new Scanner(System.in);
        Map<String, String> filtros = new HashMap<>();
        System.out.print("Nombre de aeropuerto: ");
        filtros.put("airportName", sc.nextLine().trim());
        System.out.print("De Salida o Llegada: ");
        filtros.put("airportType", sc.nextLine().trim());
        System.out.print("Modelo predictivo: ");
        filtros.put("model", sc.nextLine().trim());
        return filtros;
    }

    public void filtrarYMostrar(String rutaCsv, Map<String, String> filtros, List<String> columnasMostrar) throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader(rutaCsv))) {
            String[] encabezado = null;
            try {
                encabezado = reader.readNext();
            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            }
            if (encabezado == null) return;
            Map<String, Integer> indices = mapearIndices(encabezado);
            reader.forEach(fila -> {
                if (coincideConFiltros(fila, filtros, indices)) {
                    for (String columna : columnasMostrar) {
                        String valor = fila[indices.get(columna)];
                        System.out.println(columna + ": " + valor);
                    }
                }
            });
        }
    }

    public Map<String, Integer> mapearIndices(String[] encabezado) {
        Map<String, Integer> indices = new HashMap<>();
        for (int i = 0; i < encabezado.length; i++)
            indices.put(encabezado[i], i);
        return indices;
    }

    public boolean coincideConFiltros(String[] fila, Map<String, String> filtros, Map<String, Integer> indices) {
        for (Map.Entry<String, String> entry : filtros.entrySet()) {
            String valorFiltro = entry.getValue();
            if (!valorFiltro.isEmpty()) {
                String valorFila = fila[indices.get(entry.getKey())];
                if (!valorFiltro.equalsIgnoreCase(valorFila)) return false;
            }
        }
        return true;
    }

}
