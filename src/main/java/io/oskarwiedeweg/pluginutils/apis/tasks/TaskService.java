package io.oskarwiedeweg.pluginutils.apis.tasks;

public interface TaskService {

    default Task runSync(Runnable run) {
        return runSync(run, 0L);
    }

    Task runSync(Runnable run, long delay);

    default Task runAsync(Runnable run) {
        return runAsync(run, 0L);
    }

    Task runAsync(Runnable run, long delay);

    default Task runRepeated(Runnable run, long period) {
        return runRepeated(run, period, 0L);
    }

    Task runRepeated(Runnable run, long period, long delay);

}
