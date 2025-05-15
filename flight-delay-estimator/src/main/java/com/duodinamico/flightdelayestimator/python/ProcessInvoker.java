package com.duodinamico.flightdelayestimator.python;

import java.io.IOException;

public interface ProcessInvoker {
    public void executeExternalProcess() throws IOException, InterruptedException;
}
