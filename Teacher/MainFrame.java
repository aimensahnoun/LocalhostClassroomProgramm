package Teacher;

import Teacher.Server.Meeting;

import javax.swing.*;



import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private HostLogin loginPage;
    private Classroom classroom;
    private boolean isHosted = false ;
    public MainFrame()  {
        super("Bau Connect - Host");
        cardLayout = new CardLayout();
        loginPage = new HostLogin();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLayout(cardLayout);
        add(loginPage,"Login");


        loginPage.setLoginListener(new LoginListener(){
            public void setClassroomData(LoginEvent e ){
                Meeting meeting = e.getClassroom();
                String classCode = e.getCourseCode();

                if((!classCode.isBlank() || !classCode.isEmpty()) && (!meeting.getHostName().isEmpty() || !meeting.getHostName().isBlank()) ) {
                    try {
                        createClassroom();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    classroom.setClassroomDetails(meeting.getHostName());
                    cardLayout.show(MainFrame.this.getContentPane(), "Classroom");
                    MainFrame.super.setTitle(meeting.getClassCode() + " Classroom ID : " + meeting.getPublicID());
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Host name and class code cannot be empty", "Hosting Problem",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        }
        );



        
        
        
        
        Dimension minimal = new Dimension((int)(screenSize.width * 1),(int)(screenSize.height * .92));
        setMaximumSize(minimal);
        setMinimumSize(minimal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public void createClassroom() throws IOException {
        this.classroom = new Classroom();
        add(this.classroom,"Classroom");
    }


}
