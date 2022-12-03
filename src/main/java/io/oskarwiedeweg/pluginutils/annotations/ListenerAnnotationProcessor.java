package io.oskarwiedeweg.pluginutils.annotations;

import io.oskarwiedeweg.pluginutils.bootstrap.BootstrapContext;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import java.lang.annotation.Annotation;

public class ListenerAnnotationProcessor implements AnnotationProcessor {

    private final Plugin plugin;

    @Inject
    public ListenerAnnotationProcessor(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public <T> void process(Annotation annotation, Class<T> cls, BootstrapContext context) {
        if (Listener.class.isAssignableFrom(cls)) {
            context.getOnFinish((injector) -> {
                T instance = injector.getInstance(cls);
                Bukkit.getPluginManager().registerEvents((Listener) instance, plugin);
            });
        }
    }

}
