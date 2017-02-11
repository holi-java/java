package com.holi;

import org.junit.Test;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by holi on 2/11/17.
 */
public class RepeatableAnnotationTest {
    @Test
    public void outdatedBeforeJdk8() throws Exception {
        Class<?> annotatedClass = Outdated.class;

        assertThat(annotatedClass.getAnnotation(Hit.class), is(nullValue()));
        assertThat(annotatedClass.getAnnotation(Hits.class).value(), arrayWithSize(2));
        assertThat(annotatedClass.getAnnotationsByType(Hit.class), arrayWithSize(2));
    }

    @Hits({@Hit, @Hit})
    class Outdated {

    }

    @Test
    public void annotatedWithRepeatableAnnotationImplicitlyWhenAnnotatedRepeatedAnnotations() throws Exception {
        Class<?> annotatedClass = Repeated.class;

        assertThat(annotatedClass.getAnnotation(Hit.class), is(nullValue()));
        assertThat(annotatedClass.getAnnotation(Hits.class).value(), arrayWithSize(2));
        assertThat(annotatedClass.getAnnotationsByType(Hit.class), arrayWithSize(2));
    }

    @Hit
    @Hit
    class Repeated {

    }

    @Test
    public void annotatedWithSingleAnnotation() throws Exception {
        Class<?> annotatedClass = Singleton.class;

        assertThat(annotatedClass.getAnnotation(Hit.class), is(not(nullValue())));
        assertThat(annotatedClass.getAnnotation(Hits.class), is(nullValue()));
        assertThat(annotatedClass.getAnnotationsByType(Hit.class), arrayWithSize(1));
    }

    @Hit
    class Singleton {
    }

    @Retention(RUNTIME)
    @interface Hits {
        Hit[] value();
    }

    @Retention(RUNTIME)
    @Repeatable(Hits.class)
    @interface Hit {
        String value() default "";
    }
}
