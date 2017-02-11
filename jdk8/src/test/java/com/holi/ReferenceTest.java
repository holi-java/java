package com.holi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by holi on 2/11/17.
 */
public class ReferenceTest {
    @Test
    public void method() throws Exception {
        List<Integer> numbers = new ArrayList<>();
        Consumer<Integer> collector = numbers::add;

        collector.accept(1);

        assertThat(numbers, is(asList(1)));
    }

    @Test
    public void constructor() throws Exception {
        Supplier<Date> now = Date::new;

        assertThat(now, is(notNullValue()));
    }

    @Test
    public void constructorWithArgs() throws Exception {
        Function<String, Integer> factory = Integer::new;

        Integer result = factory.apply("2");

        assertThat(result, equalTo(2));
    }

    @Test
    public void staticMethod() throws Exception {
        Function<String, Integer> parser = Integer::parseInt;

        Integer result = parser.apply("3");

        assertThat(result, equalTo(3));
    }
}
