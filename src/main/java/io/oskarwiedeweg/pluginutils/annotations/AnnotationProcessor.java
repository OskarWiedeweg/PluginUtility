package io.oskarwiedeweg.pluginutils.annotations;

import io.oskarwiedeweg.pluginutils.bootstrap.BootstrapContext;

import java.lang.annotation.Annotation;

public interface AnnotationProcessor {
    <T> void process(Annotation annotation, Class<T> cls, BootstrapContext context);

}
