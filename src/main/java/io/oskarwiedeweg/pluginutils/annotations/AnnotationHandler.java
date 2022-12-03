package io.oskarwiedeweg.pluginutils.annotations;

import io.oskarwiedeweg.pluginutils.bootstrap.BootstrapContext;
import io.oskarwiedeweg.pluginutils.util.AnnotationUtil;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandler {

    private final Map<Class<? extends Annotation>, AnnotationProcessor> annotationProcessors;

    public AnnotationHandler() {
        this(new HashMap<>());
    }

    public AnnotationHandler(Map<Class<? extends Annotation>, AnnotationProcessor> annotationProcessors) {
        this.annotationProcessors = annotationProcessors;
    }

    public void processAnnotations(Class<?> cls, BootstrapContext context) {
        Set<Annotation> annotations = AnnotationUtil.getAnnotations(cls);
        for (Annotation annotation : annotations) {
            AnnotationProcessor annotationProcessor = annotationProcessors.get(annotation.annotationType());
            if (annotationProcessor != null) {
                annotationProcessor.process(annotation, cls, context);
            }
        }
    }

}
