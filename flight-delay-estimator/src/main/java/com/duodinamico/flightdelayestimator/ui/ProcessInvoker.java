package com.duodinamico.flightdelayestimator.ui;

import java.io.IOException;

public interface ProcessInvoker {
    public void executeExternalProcess() throws IOException, InterruptedException;
}
