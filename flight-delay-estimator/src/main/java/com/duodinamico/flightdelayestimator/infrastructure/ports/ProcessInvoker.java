package com.duodinamico.flightdelayestimator.infrastructure.ports;

import java.io.IOException;

public interface ProcessInvoker {
    public void executeExternalProcess() throws IOException, InterruptedException;
}
