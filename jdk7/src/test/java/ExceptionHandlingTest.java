import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by holi on 2/12/17.
 */
public class ExceptionHandlingTest {

    @Test
    public void catchesWithMultiExceptions() throws Exception {
        assertThatExceptionBeCaught(new IllegalStateException());
        assertThatExceptionBeCaught(new NullPointerException());

        try {
            assertThatExceptionBeCaught(new IOException());
            fail("exception should be rethrown");
        } catch (IOException expected) {
        }
    }

    private void assertThatExceptionBeCaught(Exception error) throws Exception {
        try {
            throw error;
        } catch (IllegalStateException | NullPointerException expected) {
            assertTrue(true);
        }
    }
}
