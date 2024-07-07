package repo;

import models.Student;
import models.validators.Validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StudentFileRepository extends InMemoryRepository<Long, Student>{
    private String fileName;

    public StudentFileRepository(Validator<Student> validator, String fileName)
    {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    private void loadData(){
        Path path = Paths.get(fileName);
        try{
            List<String> lines = Files.readAllLines(path);
            for(String line : lines)
            {
                String[] words = line.split(";");
                Student student = new Student(words[1], Float.parseFloat(words[2]));
                student.setId(Long.parseLong(words[0]));
                super.save(student);
            }
        }
        catch (IOException ex){
            System.err.println("Eroatre la citirea fisierului!");
            ex.printStackTrace();
        }
    }
}
