package com.duodinamico.flightdelayestimator.python;

import java.io.IOException;

public class UserInterfaceController {

    private final ProcessInvoker processInvoker;

    public UserInterfaceController(ProcessInvoker processInvoker) {
        this.processInvoker = processInvoker;
    }

    public void execute() {
        // hilo para que entrene cada 1 min
        try {
            this.processInvoker.executeExternalProcess();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ;
        // pide al usuario lo que quiere para la query
    }

    public Runnable processDatamart() {
        return () -> {
            try {
                this.processInvoker.executeExternalProcess();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
