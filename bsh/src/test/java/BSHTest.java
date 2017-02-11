import bsh.EvalError;
import bsh.Interpreter;
import bsh.ParseException;
import bsh.TargetError;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.internal.matchers.ThrowableMessageMatcher.hasMessage;

/**
 * Created by holi on 2/11/17.
 */
public class BSHTest {

    private final Interpreter bsh = new Interpreter();

    @Test
    public void importsDefaultPackagesAutomatically() throws Exception {
        Integer number = (Integer) bsh.eval("Integer.parseInt(\"3\")");

        assertThat(number, equalTo(3));
    }

    @Test
    public void catchesUncheckedException() throws Exception {
        assertExceptionShouldBeCaught(IllegalStateException.class);
    }

    @Test
    public void catchesCheckedException() throws Exception {
        assertExceptionShouldBeCaught(IOException.class);
    }


    @Test
    public void didNotSupportsJava8() throws Exception {
        try {
            bsh.eval("()->{}");
            fail("should failed");
        } catch (ParseException expected) {
        }
    }

    @Test
    public void didNotSupportsGenericType() throws Exception {
        try {
            bsh.eval("List<Integer> numbers;");
            fail("should failed");
        } catch (EvalError expected) {
        }
    }

    private void assertExceptionShouldBeCaught(Class<? extends Throwable> errorType) throws EvalError {
        String statement = String.format("throw new %s(\"ok\");", errorType.getCanonicalName());

        try {
            bsh.eval(statement);
            fail("should failed");
        } catch (TargetError error) {
            Throwable expected = error.getTarget();

            assertThat(expected, instanceOf(errorType));
            assertThat(expected, hasMessage(equalTo("ok")));
        }
    }
}
