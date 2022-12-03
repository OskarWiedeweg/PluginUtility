package io.oskarwiedeweg.pluginutils.util;


import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.HashSet;
import java.util.Set;

public abstract class AnnotationUtil {

    private AnnotationUtil() {}

    public static Set<Annotation> getAnnotations(AnnotatedElement element) {
        return getAnnotations(element, new HashSet<>());
    }

    private static Set<Annotation> getAnnotations(AnnotatedElement element, Set<Annotation> scannedAnnotations) {
        for (Annotation annotation : element.getAnnotations()) {
            if (scannedAnnotations.contains(annotation) || isInJavaLangAnnotationPackage(annotation.annotationType())) {
                continue;
            }
            scannedAnnotations.add(annotation);

            getAnnotations(annotation.annotationType(), scannedAnnotations);
        }

        if (element instanceof Class<?>) {
            Class<?> cls = (Class<?>) element;

            Class<?> superClass;
            if ((superClass = cls.getSuperclass()) != null) {
                getAnnotations(superClass, scannedAnnotations);
            }

            for (Class<?> anInterface : cls.getInterfaces()) {
                getAnnotations(anInterface, scannedAnnotations);
            }
        }

        return scannedAnnotations;
    }

    private static boolean isInJavaLangAnnotationPackage(Class<? extends Annotation> annotationClass) {
        return annotationClass.getPackage().getName().startsWith("java.lang.annotation.");
    }

}
