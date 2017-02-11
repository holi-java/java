package com.holi;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by holi on 2/11/17.
 */
public class OptionalTest {

    public static final String FOO = "foo";
    public static final String BAR = "bar";
    private final Optional<String> nil = Optional.ofNullable(null);
    private final Optional<String> bar = Optional.ofNullable(BAR);

    @Test
    public void defaults() throws Exception {

        assertThat(nil.orElse(FOO), equalTo(FOO));
        assertThat(bar.orElse(FOO), equalTo(BAR));
    }

    @Test
    public void test() throws Exception {
        assertFalse(nil.isPresent());
        assertTrue(bar.isPresent());
    }

    @Test
    public void rawValue() throws Exception {
        assertThat(bar.get(), equalTo(BAR));
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsNoSuchElementExceptionIfRawValueWasNull() throws Exception {
        nil.get();
    }
}
