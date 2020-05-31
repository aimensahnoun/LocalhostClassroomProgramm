package Student.Client;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Attendee implements Serializable {
    private String attendeeName,loginTime,exitTime,studentID;

    public Attendee(String attendeeName,String id) {
        this.attendeeName = attendeeName;
        this.studentID = id;
    }

    public String getAttendeeName() {
        return attendeeName;
    }



    public String getLoginTime() {
        return loginTime;
    }

    public String getExitTime() {
        return exitTime;
    }
    
    public String getStudentID() {
    	return studentID;
    }

    public void setLoginTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime temp = LocalDateTime.now();
        this.loginTime = dtf.format(temp);
    }


}
