package com.holi;

import org.junit.Test;

import java.util.concurrent.Callable;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by holi on 2/11/17.
 */
public class LambdaTest {
    @Test
    public void castAsFunctionInterfaceAutomatically() throws Exception {
        Callable<Integer> action = () -> {
            return 1;
        };

        assertThat(action.call(), equalTo(1));
    }

    @Test
    public void castAsSingleMethodInterfaceAutomatically() throws Exception {
        Result action = () -> 1;

        assertThat(action.value(), equalTo(1));
    }

    interface Result {
        int value();
    }
}
