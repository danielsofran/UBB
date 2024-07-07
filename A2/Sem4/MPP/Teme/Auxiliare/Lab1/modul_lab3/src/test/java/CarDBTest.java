import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarDBTest {
    @Test
    @DisplayName("Test 0")
    public void test0(){
        //assertEquals(0, 1);
        Properties props=new Properties();
        System.err.println(System.getProperty("user.dir"));
        try {
            FileReader fr = new FileReader("bd.config");
            props.load(fr);
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        CarRepository carRepo=new CarsDBRepository(props);
        int size = ((Collection<Car>)carRepo.findAll()).size();
        int nrTesla = carRepo.findByManufacturer("Tesla").size();
        int nrTesla2 = carRepo.findByManufacturer("Tesla2").size();
        carRepo.add(new Car("Tesla","Model S", 2019));
        assertEquals(((Collection<Car>)carRepo.findAll()).size(), size + 1);
        Car tesla = carRepo.findByManufacturer("Tesla").iterator().next();
        assertEquals(tesla.getManufacturer(), "Tesla");
        assertEquals(tesla.getModel(), "Model S");
        assertEquals(tesla.getYear(), 2019);
        int id = tesla.getId();
        tesla.setManufacturer("Tesla2");
        tesla.setModel("Model S2");
        tesla.setYear(2020);
        carRepo.update(id, tesla);
        assertEquals(carRepo.findByManufacturer("Tesla").size(), nrTesla);
        assertEquals(carRepo.findByManufacturer("Tesla2").size(), nrTesla2 + 1);

        Collection<Car> cars2019_2020 = carRepo.findBetweenYears(2019, 2020);
        cars2019_2020.forEach(System.out::println);
        Collection<Car> cars = (Collection<Car>) carRepo.findAll();
        assertEquals(cars2019_2020.size(), cars.size());
    }
}
