package com.exercise.utils;

import com.exercise.constants.Constants;
import com.exercise.core.constants.Actions;
import com.exercise.core.constants.Direction;
import com.exercise.entities.Cdr;
import com.exercise.entities.MinutesNumber;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Utils {
    private Utils() {
    }

    private static Utils utils;

    public static Utils getInstance() {
        if (null == utils) {
            return new Utils();
        } else {
            return utils;
        }
    }

    public void convertEnumsToUpperCase(Cdr cdr) {
        cdr.setDirection(Direction.valueOf(cdr.getDirection().toString().toUpperCase()));
        cdr.setAction(Actions.valueOf(cdr.getAction().toString().toUpperCase()));
    }

    public MinutesNumber getCompletedAndIncompletedMinsCalls(List<Cdr> cdrs){
        AtomicReference<Double> completedMins = new AtomicReference<>((double) 0);
        AtomicReference<Double> incompletedMins = new AtomicReference<>((double) 0);

        Runnable completedCalls = getCompletedCalls(cdrs, completedMins);
        Runnable incompletedCalls = getInCompletedCalls(cdrs, incompletedMins);

        initiatingThreadsAwaitingThreadsToFinish(completedCalls, incompletedCalls);

        return new MinutesNumber(completedMins.get(), incompletedMins.get());
    }

    private void initiatingThreadsAwaitingThreadsToFinish(Runnable completedCalls, Runnable incompletedCalls) {
        Thread completedThread = new Thread(completedCalls);
        Thread incompletedThread = new Thread(incompletedCalls);
        completedThread.start();
        incompletedThread.start();
        try {
            completedThread.join();
            incompletedThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private Runnable getCompletedCalls(List<Cdr> cdrs, AtomicReference<Double> completedMins) {
        return () -> {
            completedMins.set(cdrs.stream().filter(cdr -> null != cdr.getAnswer_start() && Actions.HANGUP.equals(cdr.getAction())).collect(Collectors.toList())
                    .stream().mapToDouble(completed -> completed.getDuration()).sum());
        };
    }

    private Runnable getInCompletedCalls(List<Cdr> cdrs, AtomicReference<Double> incompletedMins) {
        return () -> {
            incompletedMins.set(cdrs.stream().filter(cdr -> null == cdr.getAnswer_start() && Actions.HANGUP.equals(cdr.getAction())).collect(Collectors.toList())
                    .stream().mapToDouble(incompleted -> incompleted.getDuration()).sum());
        };
    }

    public String retrieveGetCdrUrlAPI(){
        StringBuilder sb = new StringBuilder();
        return  sb.append(Constants.HTTP).append(retrieveDeviceName()).append(Constants.PORT_3030).append(Constants.GET_CDR).toString();
    }

    private String retrieveDeviceName(){
        try {
            return InetAddress.getLocalHost().getHostName().toLowerCase();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
