package io.oskarwiedeweg.pluginutils.inject;

import com.google.inject.AbstractModule;
import io.oskarwiedeweg.pluginutils.apis.tasks.TaskService;
import io.oskarwiedeweg.pluginutils.impl.tasks.CommonTasksService;

public class CommonModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TaskService.class).to(CommonTasksService.class);
    }
}
