import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by silver on 13/03/2017.
 */
public class hashCollisionBasicTest {
    @Test()
    public void testGivenHashCollider()  {
        HashCollider hashCollider = new HashCollider();
        String result =hashCollider.bruteForce("machine-learning",4);
        assertEquals("f320e001d1",result);
    }
}
