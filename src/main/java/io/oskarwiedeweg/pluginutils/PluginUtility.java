package io.oskarwiedeweg.pluginutils;

import com.google.inject.Binder;
import com.google.inject.Injector;
import com.google.inject.Module;
import io.oskarwiedeweg.pluginutils.bootstrap.BootstrapContext;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.Set;

public abstract class PluginUtility extends JavaPlugin {

    private Injector injector;

    @Override
    public final void onEnable() {
        BootstrapContext bootstrapContext = createBootstrapContext();
        bootstrapContext.getBinder(binder -> bindSelf(binder, this));

        injector = bootstrapContext.buildInjector(getModules());
    }

    @SuppressWarnings("unchecked")
    private <T extends PluginUtility> void bindSelf(Binder binder, T instance) {
        binder.bind((Class<T>) getClass()).toInstance(instance);
    }

    @Override
    public final void onDisable() {
    }

    private BootstrapContext createBootstrapContext() {
        BootstrapContext context = new BootstrapContext(this);

        Set<Class<?>> classes = scanClasses();
        context.withClasses(classes);

        return context;
    }

    private Set<Class<?>> scanClasses() {
        String[] packages = scanPackages();
        return new Reflections(packages).getSubTypesOf(Object.class);
    }

    protected String[] scanPackages() {
        return new String[]{ getClass().getPackageName() };
    }

    protected Set<Module> getModules() {
        return Set.of();
    }

    public Injector getInjector() {
        return injector;
    }
}
