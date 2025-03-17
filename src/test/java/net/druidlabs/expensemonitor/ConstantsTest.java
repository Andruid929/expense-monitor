package net.druidlabs.expensemonitor;

import net.druidlabs.expensemonitor.io.IOConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

     @Test
    void getProperties() {
         assertEquals("C:\\Users\\Andruid929\\Documents\\Expenses manager\\", IOConstants.DEFAULT_SAVE_FOLDER_LOCATION);
     }

}