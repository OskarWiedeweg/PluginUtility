package io.oskarwiedeweg.pluginutils.exceptions;

import java.lang.reflect.Constructor;

public class NoSuitableConstructorException extends PluginUtilBootstrapException {

    public NoSuitableConstructorException(Class<?> cls, Constructor<?> constructor, Constructor<?> constructor2) {
        super(String.format("Could not find suitable constructor in '%s', found '%s' but there where '%s' also.",
                cls.getName(),
                constructor2,
                constructor));
    }

    public NoSuitableConstructorException(Class<?> cls) {
        super(String.format("Could not find any suitable constructor in '%s'. There have to be at least one public constructor. If there are multiple constructors, one of them has to be annotated with @Inject!", cls.getName()));
    }
}
