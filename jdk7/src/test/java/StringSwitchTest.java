import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by holi on 2/11/17.
 */
public class StringSwitchTest {

    @Test
    public void stringSwitchEnabled() throws Exception {
        switch ("foo") {
            case "bar":
            default:
                throw new Exception();
            case "foo":
                assertTrue(true);
                break;
        }
    }
}
