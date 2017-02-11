package com.holi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by holi on 2/11/17.
 */
public class StreamTest {

    private final IntStream source = Arrays.stream(new int[]{1, 2, 3, 4});

    @Test
    public void sample() throws Exception {
        int[] evens = source.filter(i -> i % 2 == 0).toArray();

        assertThat(evens, equalTo(new int[]{2, 4}));
    }

    @Test
    public void sameStreamInstanceCannotBeUsedMoreThanOnce() throws Exception {
        source.boxed();

        try {
            source.boxed();
            fail("should failed");
        } catch (IllegalStateException expected) {
        }
    }
}
