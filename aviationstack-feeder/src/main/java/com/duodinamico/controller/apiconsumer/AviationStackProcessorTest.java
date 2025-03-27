package com.duodinamico.controller.apiconsumer;

public class AviationStackProcessorTest {
    public static void main(String[] args) {
        AviationStackProcessor aviationStackProcessor = new AviationStackProcessor();
        System.out.println(aviationStackProcessor.petitionValidator(aviationStackProcessor.flightsPetition(args), args));
        System.out.println(args[aviationStackProcessor.getCurrentKey()]);
    }
}
