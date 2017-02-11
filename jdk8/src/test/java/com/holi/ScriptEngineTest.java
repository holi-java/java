package com.holi;

import org.junit.Before;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by holi on 2/11/17.
 */
public class ScriptEngineTest {

    private ScriptEngine engine;

    @Before
    public void setUp() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByExtension("js");
    }

    @Test
    public void eval() throws Exception {
        String code = "function add(a,b){return a+b};" +
                "add(1,2);";

        Number value = (Number) engine.eval(code);

        assertThat(value.intValue(), equalTo(3));
    }
}
