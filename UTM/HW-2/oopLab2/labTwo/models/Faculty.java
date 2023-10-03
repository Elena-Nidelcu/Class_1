import java.util.ArrayList;

public class Faculty {
    private String name;
    private String abbrev;
    private String field;
    private List<Student> enrolledStudents;

    public Faculty(String name, String abbrev, String field) {
        this.name = name;
        this.abbrev = abbrev;
        this.field = field;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public String getField() {
        return field;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void graduateStudent(Student student) {
        student.graduate();
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", abbrev='" + abbrev + '\'' +
                ", field='" + field + '\'' +
                ", enrolledStudents=" + enrolledStudents +
                '}';
    }
}
