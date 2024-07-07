package grades;

public class Nota {
    private String prof;
    private Student student;
    private Tema tema;
    private double value;

    public Nota(String prof, Student student, Tema tema, double value){
        this.prof = prof;
        this.student = student;
        this.tema = tema;
        this.value = value;
    }

    public String getProf(){
        return prof;
    }

    public void setProf(String prof){
        this.prof = prof;
    }

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public Tema getTema(){
        return tema;
    }

    public void setTema(Tema tema){
        this.tema = tema;
    }

    public double getValue(){
        return value;
    }

    public void setValue(double value){
        this.value = value;
    }

    @Override
    public String toString(){
        return "Nota{" +
                "prof='" + prof + '\'' +
                ", student=" + student +
                ", tema=" + tema +
                ", value=" + value +
                '}';
    }
}
