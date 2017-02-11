import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by holi on 2/12/17.
 */
public class NumberLiteralsTest {
    @Test
    public void underscore() throws Exception {
        int value = 1_000_000;

        assertThat(value, equalTo(1000000));
        assertThat(value, equalTo(1_00_00_0_0));
    }
}
