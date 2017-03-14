import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver on 13/03/2017.
 */
public class hashCollisionBasicTest {
    @Test(timeout = 10000)
    public void testHashColliderGivenValue()  {
        HashCollider hashCollider = new HashCollider();
        String result =hashCollider.bruteForce("machine-learning",4);
        assertEquals("f320e001d1",result);
    }

}
