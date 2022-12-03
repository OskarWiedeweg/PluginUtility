package io.oskarwiedeweg.pluginutils.annotations;

import io.oskarwiedeweg.pluginutils.bootstrap.BootstrapContext;
import io.oskarwiedeweg.pluginutils.exceptions.NoSuitableConstructorException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public class ComponentAnnotationProcessor implements AnnotationProcessor {

    @Override
    public <T> void process(Annotation annotation, Class<T> cls, BootstrapContext context) {
        context.getBinder(binder -> binder.bind(cls).toConstructor(findSuitableConstructor(cls)));
    }

    @SuppressWarnings("unchecked")
    private <T> Constructor<T> findSuitableConstructor(Class<T> cls) {
        Constructor<T>[] constructors = (Constructor<T>[]) cls.getConstructors();
        if (constructors.length == 1) {
            return constructors[0];
        }

        Constructor<T> constructor = null;
        for (Constructor<T> tConstructor : constructors) {
            if (tConstructor.isAnnotationPresent(javax.inject.Inject.class) || tConstructor.isAnnotationPresent(com.google.inject.Inject.class)) {
                if (constructor == null) {
                    constructor = tConstructor;
                } else {
                    throw new NoSuitableConstructorException(cls, constructor, tConstructor);
                }
            }
        }

        if (constructor == null) {
            throw new NoSuitableConstructorException(cls);
        }

        return constructor;
    }

}
