package test;

import businesslogic.BL_TC_001;
import org.testng.annotations.Test;

public class TC_001 extends BL_TC_001 {

    @Test
    public void TC01() throws Throwable {



        try {
            InitialSetup();
            ChangeTheRegion();
            FindOutButtonCheck();
            SearchWord("international payments");
            ValidateLink();
            Teardown();
        } catch (Throwable t)
            {
            t.printStackTrace();
            Teardown();
            }


    }
}