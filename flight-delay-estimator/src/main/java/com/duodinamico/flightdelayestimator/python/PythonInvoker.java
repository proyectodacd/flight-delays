package com.duodinamico.flightdelayestimator.python;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PythonInvoker implements ProcessInvoker{

    private final String processedDatamartFilePath;

    public PythonInvoker(String processedDatamartFilePath) {
        this.processedDatamartFilePath = processedDatamartFilePath;
    }

    @Override
    public void executeExternalProcess() throws IOException, InterruptedException {
        System.out.println("Entrenando modelos predictivos...");

        File script = copiarRecursoAArchivo("script.py", "script.py");
        File csv = copiarRecursoAArchivo("datamart-processed-data.csv", "datos.csv");
        ProcessBuilder pb = new ProcessBuilder("python", script.getAbsolutePath(), csv.getAbsolutePath(), this.processedDatamartFilePath);
        Process process = pb.start();
        String exitCode = process.waitFor() == 0 ? "Modelos entrenados con éxito."  : "Error al entrenar los modelos.";
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
