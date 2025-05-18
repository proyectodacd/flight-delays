package com.duodinamico.flightdelayestimator.application.usecases.userinterface;

import com.duodinamico.flightdelayestimator.tools.TaskScheduler;
import com.duodinamico.flightdelayestimator.infrastructure.ports.ProcessInvoker;
import com.duodinamico.flightdelayestimator.tools.ui.UserInterfaceForQueries;

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
        this.taskScheduler.programarTareaCadaCiertoTiempo(scheduler, processDatamart, 120,0);
        try {
            latch.await();
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
        this.userInterfaceForQueries.executeUserInterface();
    }

    CountDownLatch latch = new CountDownLatch(1);
    public Runnable processDatamart() {
        return () -> {
            try {
                this.processInvoker.executeExternalProcess();
                latch.countDown();
            } catch (Exception e) {
                System.out.println(e.getMessage());;
            }
        };
    }

}
