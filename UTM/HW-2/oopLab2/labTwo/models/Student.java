public class Student {
    private String uniqueIdentifier;
    private String firstName;
    private String lastName;
    private boolean graduated;

    public Student(String uniqueIdentifier, String firstName, String lastName) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.firstName = firstName;
        this.lastName= lastName;
        this.graduated = false;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public String getName() {
        return firstName + lastName;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void graduate() {
        this.graduated = true;
    }

    @Override
    public String toString() {
        return "Student{" +
                "uniqueIdentifier='" + uniqueIdentifier + '\'' +
                ",name='" + firstName + ' ' + lastName + '\'' +
                ", graduated=" + graduated +
                '}';
    }
}