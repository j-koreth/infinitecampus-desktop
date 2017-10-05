package sample;

import javafx.beans.property.SimpleStringProperty;

public class GradeTable {
    private final SimpleStringProperty time = new SimpleStringProperty("");
	private final SimpleStringProperty course = new SimpleStringProperty("");
    private final SimpleStringProperty assignment = new SimpleStringProperty("");
    private final SimpleStringProperty grade = new SimpleStringProperty("");
    public GradeTable(){
        this("", "", "", "");
    }
    public GradeTable(String time, String course, String assignment, String grade) {
        setTime(time);
        setCourse(course);
        setAssignment(assignment);
        setGrade(grade);
    }
    public String getTime() {
        return time.get();
    }
    public void setTime(String time) {
        this.time.set(time);
    }

    public String getCourse() {
        return course.get();
    }
    public void setCourse(String course) {
        this.course.set(course);
    }

    public String getAssignment() {
        return assignment.get();
    }
    public void setAssignment(String assignment) {
        this.assignment.set(assignment);
    }

    public String getGrade() {
        return grade.get();
    }
    public void setGrade(String grades) {
        grade.set(grades);
    }

}