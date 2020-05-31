package Student;

import Student.Client.Attendee;

import java.util.EventObject;

public class LoginEvent extends EventObject {

    Attendee student;

    public LoginEvent(Object source) {
        super(source);
    }

    public LoginEvent(Object source, String studentName, String id) {
        super(source);
        this.student = new Attendee(studentName,id);


    }

    public Attendee getStudent() {
        return student;
    }
}
