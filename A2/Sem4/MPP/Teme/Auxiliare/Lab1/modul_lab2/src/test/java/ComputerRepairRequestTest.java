import model.ComputerRepairRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComputerRepairRequestTest {
    
    @Test
    @DisplayName("FirstTest")
    public void test1(){
        ComputerRepairRequest crr = new ComputerRepairRequest();
        assertEquals(crr.getOwnerName(), "");
        assertEquals(crr.getOwnerAddress(), "");
    }

    @Test
    @DisplayName("SecondTest")
    public void test2(){
        ComputerRepairRequest crr = new ComputerRepairRequest(1, "owner", "address", "07 nam cartela", "model", "01.01.2023", "descr");
        assertEquals(crr.getID(), 1, "Test ID");
    }
}
