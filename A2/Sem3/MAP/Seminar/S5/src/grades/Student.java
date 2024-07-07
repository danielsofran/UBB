package grades;

public class Student extends Entity<Long> {
    private String name;
    private int group;

    public Student(Long id, String name, int group){
        super(id);
        this.name = name;
        this.group = group;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getGroup(){
        return group;
    }

    public void setGroup(int group){
        this.group = group;
    }

    @Override
    public String toString(){
        return "Student{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", group=" + group +
                '}';
    }
}
