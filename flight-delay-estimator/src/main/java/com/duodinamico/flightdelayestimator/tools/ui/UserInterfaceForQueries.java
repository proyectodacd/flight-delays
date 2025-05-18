package com.duodinamico.flightdelayestimator.tools.ui;

import java.io.IOException;
import java.util.*;

public class UserInterfaceForQueries {

    private final String processedDatamartFilePath;
    private final QueryTools queryTools;
    private final ProcessRepeater processRepeater;

    public UserInterfaceForQueries(String processedDatamartFilePath, QueryTools queryTools, ProcessRepeater processRepeater) {
        this.processedDatamartFilePath = processedDatamartFilePath;
        this.queryTools = queryTools;
        this.processRepeater = processRepeater;
    }

    public void executeUserInterface() {
        System.out.println("DESCUBRA EL RENDIMIENTO PREDICTIVO DE UN MODELO EN EL AEROPUERTO QUE DESEE:");
        this.processRepeater.repetirHastaQueUsuarioDigaNo(this::askUser,"¿Desea hacer otra consulta? (s/n):");
        System.out.println("\nGRACIAS POR UTILIZAR FLIGHTDELAYS®");
        System.exit(0);
    }

    public Runnable askUser() {
        return () -> {Map<String, String> filtros = this.queryTools.pedirFiltros();
        try {
            this.queryTools.filtrarYMostrar(this.processedDatamartFilePath, filtros, List.of("R2", "MAE", "MSE"));
            System.out.println("-----------------------------------------------------------------------");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        };

    }




}