import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by holi on 2/11/17.
 */
public class AutomaticResourceManagementTest {

    @Test
    public void basic() throws Exception {
        AtomicBoolean closed = new AtomicBoolean(false);

        try (Closeable resource = resourceNotifiedUntilClosed(closed)) {
            assertFalse(closed.get());
        }

        assertTrue(closed.get());
    }

    @Test
    public void lifecycle() throws Exception {
        AtomicBoolean closed = new AtomicBoolean(false);

        try (Closeable resource = resourceNotifiedUntilClosed(closed)) {
            assertFalse(closed.get());
        } catch (Throwable ex) {
            fail("can't be reached");
        } finally {
            assertTrue(closed.get());
        }

        assertTrue(closed.get());
    }

    private Closeable resourceNotifiedUntilClosed(final AtomicBoolean closed) {
        return new Closeable() {
            @Override
            public void close() throws IOException {
                closed.set(true);
            }
        };
    }

    @Test
    public void failsOnClosingResource() throws Exception {
        IOException error = new IOException();

        try (Closeable resource = resourceFailsOnClosing(error)) {

        } catch (IOException actual) {
            assertThat(actual, is(error));
        }
    }

    private Closeable resourceFailsOnClosing(final IOException error) {
        return new Closeable() {
            @Override
            public void close() throws IOException {
                throw error;
            }
        };
    }
}
