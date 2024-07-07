package stream;

import java.util.List;

public class Stream {
    public static void main(String[] args) {
        List<String> list = List.of("asf", "bcd", "asd", "bed", "bbb");
        list.stream()
                .filter(s -> s.startsWith("b"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }
}
