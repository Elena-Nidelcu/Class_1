import labTwo.models.*

import java.util.Scanner;

public class ApplicationLoop {
    private University university;
    private Scanner scanner;
    private String command;
    private GeneralOperations generalOperations;
    private FacultyOperations facultyOperations;

    public ApplicationLoop() {
        this.university = new University();
        this.scanner = new Scanner(System.in);
        this.command = "";
        this.generalOperations = new GeneralOperations(university, scanner);
        this.facultyOperations = new FacultyOperations(university, scanner);
    }

    public void run() {
        while (!this.command.equals("q")) {
            this.command = takeUserInput();
            switch (this.command) {
                case "f":
                    facultyOperationsMenu();
                    break;
                case "g":
                    generalOperationsMenu();
                    break;
                case "q":
                    System.out.println("Finish");
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
        scanner.close();
    }

    private void generalOperationsMenu() {
        System.out.println("General Operations:");
        System.out.println("nf Add Faculty");
        System.out.println("pf Print Faculties");
        System.out.println("df Display Faculties by Field");
        System.out.println("ff Find Faculty by Student Identifier");
        System.out.println("Enter the operation: ");
        String operation = scanner.nextLine();

        switch (operation) {
            case "nf":
                generalOperations.addFaculty();
                break;
            case "pf":
                generalOperations.printFaculties();
                break;
            case "df":
                generalOperations.displayFacultiesByField();
                break;
            case "ff":
                generalOperations.findFacultyByStudentIdentifier();
                break;
            default:
                System.out.println("Invalid operation");
        }
    }

    private void facultyOperationsMenu() {
        System.out.println("Faculty Operations:");
        System.out.println("ns Create and Assign Student to Faculty");
        System.out.println("gs Graduate Student from Faculty");
        System.out.println("ds Display Enrolled Students");
        System.out.println("dg Display Graduates");
        System.out.println("sb Check If Student Belongs");
        System.out.println("Enter the operation: ");
        String operation = scanner.nextLine();

        switch (operation) {
            case "ns":
                facultyOperations.createAndAssignStudentToFaculty();
                break;
            case "gs":
                facultyOperations.graduateStudentFromFaculty();
                break;
            case "ds":
                facultyOperations.displayEnrolledStudents();
                break;
            case "dg":
                facultyOperations.displayGraduates();
                break;
            case "sb":
                facultyOperations.checkIfStudentBelongs();
                break;
            default:
                System.out.println("Invalid operation");
        }
    }

    private String takeUserInput() {
        System.out.println("Write command (g for general, f for faculty, q to quit): ");
        return scanner.nextLine();
    }
}