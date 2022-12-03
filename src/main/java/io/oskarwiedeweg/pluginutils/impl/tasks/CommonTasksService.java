package io.oskarwiedeweg.pluginutils.impl.tasks;

import io.oskarwiedeweg.pluginutils.apis.tasks.Task;
import io.oskarwiedeweg.pluginutils.apis.tasks.TaskService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import javax.inject.Inject;

public class CommonTasksService implements TaskService {

    private final Plugin plugin;

    @Inject
    public CommonTasksService(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Task runSync(Runnable run, long delay) {
        BukkitTask bukkitTask = Bukkit.getScheduler().runTaskLater(plugin, run, delay);
        return bukkitTask::cancel;
    }

    @Override
    public Task runAsync(Runnable run, long delay) {
        BukkitTask bukkitTask = Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, run, delay);
        return bukkitTask::cancel;
    }

    @Override
    public Task runRepeated(Runnable run, long period, long delay) {
        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, run, delay, period);
        return () -> Bukkit.getScheduler().cancelTask(taskId);
    }

}
