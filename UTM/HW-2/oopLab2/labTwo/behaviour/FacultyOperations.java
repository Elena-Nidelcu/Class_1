import java.util.Scanner;

public class FacultyOperations {
    private University university;
    private Scanner scanner;

    public FacultyOperations(University university, Scanner scanner) {
        this.university = university;
        this.scanner = scanner;
    }

    public void createAndAssignStudentToFaculty() {
        System.out.println("Enter student's unique identifier (email or ID):");
        String uniqueIdentifier = scanner.nextLine();
        System.out.println("Enter student's first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter student's last name:");
        String lastName = scanner.nextLine();

        int facultyIndex = 0;

        Faculty faculty = university.getFaculties().get(facultyIndex);

        Student student = new Student(uniqueIdentifier, firstName, lastName);
        faculty.enrollStudent(student);
        System.out.println("Student enrolled in " + faculty.getName() + " faculty.");
    }

    public void graduateStudentFromFaculty() {
        System.out.println("Enter student's unique identifier (email or ID):");
        String uniqueIdentifier = scanner.nextLine();

        for (Faculty faculty : university.getFaculties()) {
            for (Student student : faculty.getEnrolledStudents()) {
                if (student.getUniqueIdentifier().equals(uniqueIdentifier)) {
                    faculty.graduateStudent(student);
                    System.out.println("Student graduated from " + faculty.getName() + " faculty.");
                    return;
                }
            }
        }

        System.out.println("Student not found or already graduated.");
    }

    public void displayEnrolledStudents() {
        List<Faculty> faculties = university.getFaculties();
        for (Faculty faculty : faculties) {
            System.out.println("Enrolled students in " + faculty.getName() + " faculty:");
            for (Student student : faculty.getEnrolledStudents()) {
                System.out.println(student);
            }
        }
    }

    public void displayGraduates() {
        List<Faculty> faculties = university.getFaculties();
        for (Faculty faculty : faculties) {
            System.out.println("Graduated students from " + faculty.getName() + " faculty:");
            for (Student student : faculty.getEnrolledStudents()) {
                if (student.isGraduated()) {
                    System.out.println(student);
                }
            }
        }
    }

    public void checkIfStudentBelongs() {
        System.out.println("Enter student's email:");
        String uniqueIdentifier = scanner.nextLine();

        for (Faculty faculty : university.getFaculties()) {
            for (Student student : faculty.getEnrolledStudents()) {
                if (student.getUniqueIdentifier().equals(uniqueIdentifier)) {
                    System.out.println("Student belongs to " + faculty.getName() + " faculty.");
                    return;
                }
            }
        }

        System.out.println("Student does not belong to any faculty.");
    }
}
