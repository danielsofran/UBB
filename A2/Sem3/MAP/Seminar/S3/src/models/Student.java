package models;

import java.util.Map;
import java.util.Objects;

public class Student {
    private String name;
    private float medie;

    public Student(String name, float medie) {
        this.name = name;
        this.medie = medie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMedie() {
        return medie;
    }

    public void setMedie(float medie) {
        this.medie = medie;
    }

    public Integer getMedieRotunjita(){
        return Math.round(medie);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // daca sunt aceleasi referinte
        if (o == null || getClass() != o.getClass()) return false; // daca obiectul e null sau daca nu sunt de acelasi tip
        Student student = (Student) o; // cast la tipul Student
        return Float.compare(student.getMedie(), getMedie()) == 0 && getName().equals(student.getName()); // daca medie si nume sunt egale
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMedie());
    }

    @Override
    public String toString() {
        return "Studentul " + name + " are media " + medie;
    }
}
