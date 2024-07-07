import map.MyMap;
import models.Student;

import java.util.*;

public class Main {

    private static class CompareStudent implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static void main(String[] args) {
        Set<Student> studentiHash = new HashSet<>();
        studentiHash.add(new Student("Ion", 9.5f));
        studentiHash.add(new Student("Maria", 8.5f));
        studentiHash.add(new Student("Mariana", 8.5f));
        studentiHash.add(new Student("Andrei", 9.4f));
        studentiHash.add(new Student("Vasile", 8.2f));

        System.out.println("HashSet");
        for (Student student : studentiHash) {
            System.out.println(student);
        }

        Set<Student> studentiTree = new TreeSet<>(new CompareStudent());
        studentiTree.addAll(studentiHash);
        System.out.println("\nTreeSet");
        for (Student student : studentiTree) {
            System.out.println(student);
        }

        Map<String, Student> studentiMap = new HashMap<>();
        for (Student student : studentiTree) {
            studentiMap.put(student.getName(), student);
        }
        System.out.println("\nHashMap");
        for (Map.Entry<String, Student> entry : studentiMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + "; Value: " + entry.getValue());
        }

        Map<String, Student> studentiTreeMap = new TreeMap<>(studentiMap);
        System.out.println("\nTreeMap");
        for (Map.Entry<String, Student> entry : studentiTreeMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + "; Value: " + entry.getValue());
        }

        System.out.println("\nMyMap");
        MyMap myMap = new MyMap();
        myMap.add(new Student("Ion", 9.5f));
        myMap.add(new Student("Maria", 8.5f));
        myMap.add(new Student("Mariana", 8.5f));
        myMap.add(new Student("Andrei", 9.4f));
        myMap.add(new Student("Vasile", 8.2f));

        myMap.print();

        System.out.println("\nSortedMyMap");
        for(Map.Entry<Integer, List<Student>> entry : myMap.getEntries()){
            System.out.println("Key: " + entry.getKey());
            entry.getValue().sort(new CompareStudent());
            System.out.println("Value: " + entry.getValue());
        }
    }
}