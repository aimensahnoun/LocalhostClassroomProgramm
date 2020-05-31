package Student;


import java.util.EventListener;

public interface LoginListener extends EventListener {
    public void setStudentdata(LoginEvent e );
}