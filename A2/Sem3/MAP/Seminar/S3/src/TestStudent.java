import models.validators.StudentValidator;
import repo.StudentFileRepository;

public class TestStudent {
    public static void main(String[] args){
        StudentFileRepository repo = new StudentFileRepository(new StudentValidator(), "data/students.csv");
        repo.findAll().forEach(System.out::println);
    }
}
