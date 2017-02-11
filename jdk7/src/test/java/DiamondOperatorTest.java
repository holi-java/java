import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by holi on 2/11/17.
 */
public class DiamondOperatorTest {
    @Test
    public void sample() throws Exception {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(1);

        assertThat(numbers, equalTo(singletonList(1)));
    }
}
