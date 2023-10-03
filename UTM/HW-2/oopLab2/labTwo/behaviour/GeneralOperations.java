import java.util.Scanner;

public class GeneralOperations {
    private University university;
    private Scanner scanner;

    public GeneralOperations(University university, Scanner scanner) {
        this.university = university;
        this.scanner = scanner;
    }

    public void addFaculty() {
        System.out.println("faculty name:");
        String facultyName = scanner.nextLine();
        System.out.println("faculty abbrev:");
        String facultyAbbrev = scanner.nextLine();
        System.out.println("faculty field:");
        String facultyField = scanner.nextLine();

        Faculty faculty = new Faculty(facultyName,facultyAbbrev, facultyField);
        this.university.addFaculty(faculty);
    }

    public void printFaculties() {
        System.out.println(university);
    }

    public void findFacultyByStudentIdentifier() {
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

        System.out.println("Student does not belong to any faculty or is not found.");
    }

    public void displayFacultiesByField() {
        System.out.println("Enter field:");
        String field = scanner.nextLine();

        System.out.println("Faculties in the " + field + " field:");
        boolean found = false;

        for (Faculty faculty : university.getFaculties()) {
            if (faculty.getField().equals(field.toString())) {
                System.out.println(faculty.getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No faculties found in the " + field + " field.");
        }
    }
}
