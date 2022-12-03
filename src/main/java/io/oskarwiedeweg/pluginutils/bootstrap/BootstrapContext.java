package io.oskarwiedeweg.pluginutils.bootstrap;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import io.oskarwiedeweg.pluginutils.annotations.*;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class BootstrapContext {

    private final Set<Consumer<Binder>> binders;
    private final AnnotationHandler annotationHandler;

    public BootstrapContext(Plugin plugin) {
        this.binders = new HashSet<>();

        this.annotationHandler = new AnnotationHandler(Map.of(Component.class, new ComponentAnnotationProcessor(), Listener.class, new ListenerAnnotationProcessor(plugin)));
    }

    public void getBinder(Consumer<Binder> binderConsumer) {
        binders.add(binderConsumer);
    }

    public void withClasses(Set<Class<?>> classes) {
        for (Class<?> aClass : classes) {
            annotationHandler.processAnnotations(aClass, this);
        }
    }

    public Injector buildInjector(Set<Module> modules) {
        Set<Module> injectorModules = new HashSet<>(modules);
        injectorModules.add(binder -> {
            for (Consumer<Binder> binderConsumer : binders) {
                binderConsumer.accept(binder);
            }
        });
        return Guice.createInjector(injectorModules);
    }

    public void getOnFinish(Consumer<Injector> onFinish) {

    }
}
