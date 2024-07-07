package grades;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Runner {
    public static void report1(List<Nota> note){
        Predicate<Nota> byGroup = n -> n.getStudent().getGroup() == 221;
        Predicate<Nota> byProf = n -> n.getProf().equals("Istvan");
        Predicate<Nota> filter = byGroup.and(byProf);
        note.stream()
                .filter(filter)
                .map( x -> new NotaDto(x.getValue(), x.getStudent().getName(), x.getTema().getId()))
                .forEach(System.out::println);
    }

    public static void report2(List<Nota> note){
        Map<Student, List<Nota>> studentListMap = note.stream()
                .collect(Collectors.groupingBy(x -> x.getStudent()));
        studentListMap.entrySet().forEach(x -> {
            System.out.println(x.getKey().getName());
            int count = x.getValue().size();
            double sum = x.getValue().stream().map(y -> y.getValue()).reduce(0.0, (a,b) -> a+b);
            System.out.println("Average: " + sum/count);
        });
    }

    public static Double mediaNotelorLaTema(List<Nota> note, String temaId){
        return note.stream()
                .filter(n -> Objects.equals(n.getTema().getId(), temaId))
                .mapToDouble(Nota::getValue)
                .average().orElse(0.0);
    }

    public static void temaCuMediaMaxima(List<Nota> note){
        note.stream()
                .collect(Collectors.groupingBy(Nota::getTema))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> mediaNotelorLaTema(note, x.getKey().getId())))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .ifPresent(x -> System.out.println("Tema cu media maxima: "+x.getKey()));
    }

    public static void ceaMaiGreaTema(List<Nota> note){
        note.stream()
                .collect(Collectors.groupingBy(Nota::getTema))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> mediaNotelorLaTema(note, x.getKey().getId())))
                .entrySet().stream().min(Map.Entry.comparingByValue())
                .ifPresent(x -> System.out.println("Tema cea mai grea: "+x.getKey()));
    }

    public static void main(String[] args) {
        Student s1 = new Student(1L, "John", 221);
        Student s2 = new Student(2L, "Alice", 222);
        Student s3 = new Student(3L, "Bob", 221);
        Student s4 = new Student(4L, "Eve", 223);
        List<Student> students = List.of(s1, s2, s3, s4);

        Tema t1 = new Tema("1", "lab1");
        Tema t2 = new Tema("2", "lab2");
        Tema t3 = new Tema("3", "lab3");
        Tema t4 = new Tema("4", "lab4");
        List<Tema> teme = List.of(t1, t2, t3, t4);

        Nota n1 = new Nota("Istvan", s1, t1, 10);
        Nota n2 = new Nota("Istvan", s2, t1, 9);
        Nota n3 = new Nota("Istvan", s3, t1, 8);
        Nota n32 = new Nota("Istvan", s3, t2, 10);
        Nota n4 = new Nota("Istvan", s4, t1, 7);
        List<Nota> note = List.of(n1, n2, n3, n4, n32);

        report2(note);
    }
}
