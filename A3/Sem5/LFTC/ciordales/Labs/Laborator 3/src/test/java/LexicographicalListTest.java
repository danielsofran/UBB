import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LexicographicalListTest {

    private LexicographicalList lexicographicalList;

    @BeforeEach
    public void setUp(){
        lexicographicalList = new LexicographicalList();
    }

    @Test
    void addItemList(){

        ItemSymbolTable item1 = new ItemSymbolTable("c10");
        lexicographicalList.addItem(item1);
        Optional<ItemSymbolTable> foundItem1 = lexicographicalList.findElement("c10");
        Assertions.assertFalse(foundItem1.isEmpty());
        Assertions.assertEquals(foundItem1.get().getIndexSymbolTable(), 1);
        Assertions.assertEquals(foundItem1.get().getSymbol(), "c10");
        Assertions.assertEquals(foundItem1.get().getNextElement(), null);

        ItemSymbolTable item2 = new ItemSymbolTable("c20");
        ItemSymbolTable item3 = new ItemSymbolTable("c30");
        ItemSymbolTable item4 = new ItemSymbolTable("c40");
        ItemSymbolTable item5 = new ItemSymbolTable("c0");
        lexicographicalList.addItem(item2);
        lexicographicalList.addItem(item3);
        lexicographicalList.addItem(item4);
        lexicographicalList.addItem(item5);

        Optional<ItemSymbolTable> foundItem5 = lexicographicalList.findElement("c0");
        Assertions.assertFalse(foundItem5.isEmpty());
        Assertions.assertEquals(foundItem5.get().getIndexSymbolTable(), 5);
        Assertions.assertEquals(foundItem5.get().getSymbol(), "c0");
        Assertions.assertEquals(foundItem5.get().getNextElement().getSymbol(), "c10");

        ItemSymbolTable item6 = new ItemSymbolTable("c35");
        lexicographicalList.addItem(item6);
        Optional<ItemSymbolTable> foundItem6 = lexicographicalList.findElement("c35");
        Assertions.assertFalse(foundItem6.isEmpty());
        Assertions.assertEquals(foundItem6.get().getIndexSymbolTable(), 6);
        Assertions.assertEquals(foundItem6.get().getSymbol(), "c35");
        Assertions.assertEquals(foundItem6.get().getNextElement().getSymbol(), "c40");
        Assertions.assertEquals(foundItem6.get().getNextElement().getNextElement(), null);

        ItemSymbolTable item7 = new ItemSymbolTable("c50");
        lexicographicalList.addItem(item7);
        Optional<ItemSymbolTable> foundItem7 = lexicographicalList.findElement("c50");
        Assertions.assertFalse(foundItem7.isEmpty());
        Assertions.assertEquals(foundItem7.get().getIndexSymbolTable(), 7);
        Assertions.assertEquals(foundItem7.get().getSymbol(), "c50");
        Assertions.assertEquals(foundItem7.get().getNextElement(), null);

        Assertions.assertTrue(lexicographicalList.findElement("Ba").isEmpty());
    }
}
