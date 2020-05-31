package Teacher.Server;

import Student.Client.Attendee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Meeting implements Serializable {

    private String publicID,hostName,classCode;
    private ArrayList<Attendee> attendees = new ArrayList<Attendee>();

    public Meeting(String hostName,String classCode) {
        this.hostName = hostName;
        this.classCode = classCode;
        this.publicID = createID();

        MeetingHolder.meeting = this;
    }

    public String getPublicID() {
        return publicID;
    }

    public String getHostName() {
        return hostName;
    }

    public String createID(){
        StringBuilder temp = new StringBuilder();
        for(int i = 0 ; i<9  ; i++){
            temp.append(new Random().nextInt(9));
            if(i % 3 == 2 && i != 8) {
                temp.append("-");
            }
        }
        return temp.toString();
    }

    public void addStudent(Attendee attendee) {
        this.attendees.add(attendee);
    }

    public String getClassCode() {
        return classCode;
    }

    public ArrayList<Attendee> getAttendees() {
        return attendees;
    }
}
