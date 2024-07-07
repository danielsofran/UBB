package map;

import models.Student;

import java.util.*;

public class MyMap {
    private Map<Integer, List<Student>> map;

    public MyMap(){
        map = new TreeMap<>(new CompareKey());
    }

    /**
     * Adauga un student in map
     * @param student: studentul care va fi adaugat
     */
    public void add(Student student){
        Integer medie = student.getMedieRotunjita();
        List<Student> studentList = map.get(medie);
        if(studentList == null){
            studentList = new ArrayList<>();
            map.put(medie, studentList);
        }
        studentList.add(student);
    }

    public void print(){
        for (Map.Entry<Integer, List<Student>> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() +
                    "; Value: " + entry.getValue());
        }
    }

    public Set<Map.Entry<Integer, List<Student>>> getEntries(){
        return map.entrySet();
    }

    private static class CompareKey implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
}
