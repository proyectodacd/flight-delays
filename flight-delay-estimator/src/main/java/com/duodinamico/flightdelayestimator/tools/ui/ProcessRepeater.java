package com.duodinamico.flightdelayestimator.tools.ui;

import java.util.Scanner;
import java.util.function.Supplier;

public class ProcessRepeater {

    public void repetirHastaQueUsuarioDigaNo(Supplier<Runnable> generarMetodo, String mensaje) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            generarMetodo.get().run();

            System.out.print(mensaje);
            System.out.flush();
            String respuesta = sc.nextLine().trim().toLowerCase();

            if (!respuesta.equals("s")) {
                break;
            }
        }
    }

}
