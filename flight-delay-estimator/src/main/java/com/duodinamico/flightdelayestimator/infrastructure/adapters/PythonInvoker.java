package com.duodinamico.flightdelayestimator.infrastructure.adapters;

import com.duodinamico.flightdelayestimator.infrastructure.ports.ProcessInvoker;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PythonInvoker implements ProcessInvoker {

    private final String processedDatamartFilePath;
    private final String cleanDatamartFilePath;

    public PythonInvoker(String processedDatamartFilePath, String cleanDatamartFilePath) {
        this.processedDatamartFilePath = processedDatamartFilePath;
        this.cleanDatamartFilePath = cleanDatamartFilePath;
    }

    @Override
    public void executeExternalProcess() throws IOException, InterruptedException {
        System.out.println("Entrenando modelos predictivos...");
        File script = copiarRecursoAArchivo("script.py", "script.py");
        ProcessBuilder pb = new ProcessBuilder("python", script.getAbsolutePath(), this.cleanDatamartFilePath, this.processedDatamartFilePath);
        Process process = pb.start();
        String exitCode = process.waitFor() == 0 ? "Modelos entrenados con éxito. (Próximo ajuste de modelos en 2 minutos)" + "\n-----------------------------------------------------------------------" : "Error al entrenar los modelos." + "\n-----------------------------------------------------------------------";
        System.out.println(exitCode);
    }

    private File copiarRecursoAArchivo(String recurso, String nombreArchivoDestino) throws IOException {
        InputStream input = PythonInvoker.class.getClassLoader().getResourceAsStream(recurso);
        if (input == null) {
            throw new FileNotFoundException("Recurso no encontrado: " + recurso);
        }
        File temp = new File(System.getProperty("java.io.tmpdir"), nombreArchivoDestino);
        Files.copy(input, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return temp;
    }
}
