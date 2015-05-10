import models.Gem;
import models.GemList;

import org.junit.Test;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void BSTinitialCheck() {
        GemList test = new GemList();
        Gem gArray[] = test.getAllGems();
        for(Gem g : gArray){
        	System.out.println(g.getName());
        }
    }

}
