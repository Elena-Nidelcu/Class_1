package labTwo.models;

import java.util.List;

public class Faculty {
    private String name;
    private String abbreviation;
//    private List<Student> students;
    private String studyField;

    enum Fields {
        MECHANICAL_ENGINEERING,
        SOFTWARE_ENGINEERING,
        FOOD_TECHNOLOGY,
        URBANISM_ARCHITECTURE,
        VETERINARY_MEDICINE
    }

    public Faculty(String name, String abbreviation, String studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;
    }

    @Override
    public String toString() {
        return name + " - " + abbreviation + " - " + studyField;
    }
}
