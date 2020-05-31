package Student;



import javax.swing.*;
import java.awt.*;

public class StudentLogin extends JPanel {

    private StudentLoginSideBar login;
    private LoginListener listener;
    public StudentLogin(){

        setLayout(new BorderLayout());
        login = new StudentLoginSideBar();

        add(login,BorderLayout.WEST);

        login.setListener(listener);

    }

    public void setLoginListener(LoginListener listener) {
        this.listener = listener;
        login.setListener(this.listener);
    }



}
