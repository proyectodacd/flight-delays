package com.duodinamico.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PythonInvoker {

    public PythonInvoker() {
    }

    public void executePythonScript() throws IOException, InterruptedException {
        File script = copiarRecursoAArchivo("script.py", "script.py");
        File csv = copiarRecursoAArchivo("datamart-processed-data.csv", "datos.csv");
        ProcessBuilder pb = new ProcessBuilder("python3", script.getAbsolutePath(), csv.getAbsolutePath());
        pb.inheritIO();
        Process process = pb.start();
        int exitCode = process.waitFor();
        System.out.println("Código de salida: " + exitCode);
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
