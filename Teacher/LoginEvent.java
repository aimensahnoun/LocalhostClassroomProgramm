package Teacher;

import Teacher.Server.Meeting;

import java.util.EventObject;

public class LoginEvent extends EventObject {

    public static Meeting classroom;
    private String courseCode;

    public LoginEvent(Object source) {
        super(source);
    }

    public LoginEvent(Object source, String hostName, String courseCode) {
        super(source);
        this.classroom = new Meeting(hostName,courseCode);
        this.courseCode = courseCode;

    }

    public Meeting getClassroom() {
        return classroom;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
