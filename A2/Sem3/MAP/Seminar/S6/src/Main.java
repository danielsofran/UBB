import domain.User;
import repo.UserRepoDB;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/academic";
        String username = "postgres";
        String password = "iamamaster";
        UserRepoDB userRepoDB = new UserRepoDB(url, username, password);
        User user = new User(1L, "John");
        userRepoDB.save(user);
        userRepoDB.findAll().forEach(System.out::println);
    }
}