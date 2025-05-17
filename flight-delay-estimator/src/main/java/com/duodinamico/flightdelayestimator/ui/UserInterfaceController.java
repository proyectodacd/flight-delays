package com.duodinamico.flightdelayestimator.ui;

import com.duodinamico.flightdelayestimator.datamart.tools.TaskScheduler;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class UserInterfaceController {

    private final ProcessInvoker processInvoker;
    private final TaskScheduler taskScheduler;
    private final UserInterfaceForQueries userInterfaceForQueries;

    public UserInterfaceController(ProcessInvoker processInvoker, TaskScheduler taskScheduler, UserInterfaceForQueries userInterfaceForQueries) {
        this.processInvoker = processInvoker;
        this.taskScheduler = taskScheduler;
        this.userInterfaceForQueries = userInterfaceForQueries;
    }

    public void execute() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        Runnable processDatamart = processDatamart();
        this.taskScheduler.programarTareaCadaCiertoTiempo(scheduler, processDatamart, 600,0);
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.userInterfaceForQueries.executeUserInterface();
    }

    CountDownLatch latch = new CountDownLatch(1);
    public Runnable processDatamart() {
        return () -> {
            try {
                this.processInvoker.executeExternalProcess();
                latch.countDown();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
