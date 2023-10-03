import java.util.ArrayList;
import labTwo.behaviour.*;

public class University {

    private List<Faculty> faculties;

    public University(){
        faculties = new ArrayList<>();
    }

    public void addFaculty(Faculty faculty){
        this.faculties.add(faculty);
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();

        for (Faculty faculty : this.faculties) {
            text.append(faculty).append("\n");
        }
        return text.toString();
    }
}