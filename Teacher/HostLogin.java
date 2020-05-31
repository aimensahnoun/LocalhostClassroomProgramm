package Teacher;

import javax.swing.*;
import java.awt.*;

public class HostLogin extends JPanel {

    private HostLoginSideBar login;
    
    private LoginListener listener;
    public HostLogin(){

        setLayout(new BorderLayout());
        login = new HostLoginSideBar();
        

        add(login,BorderLayout.WEST);
        
        

        login.setListener(listener);

    }

    public void setLoginListener(LoginListener listener) {
        this.listener = listener;
        login.setListener(this.listener);
    }



}
