package Student;


import Student.Client.Attendee;
import Student.Client.Connect;
import Teacher.Server.Meeting;
import Teacher.SidePanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.regex.Pattern;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private StudentLogin loginPage;
    private Classroom classroom;

    private String title ;
    public MainFrame(){
        super("Bau Connect - Student");
        cardLayout = new CardLayout();
        loginPage = new StudentLogin();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLayout(cardLayout);
        add(loginPage,"Login");



        loginPage.setLoginListener(new LoginListener(){

                                       public void setStudentdata(LoginEvent e ){
                                           Attendee student = e.getStudent();
                                           System.out.println(student.getAttendeeName());
                                           if(!Pattern.matches("[0-9]+" ,student.getAttendeeName()) && !student.getAttendeeName().isEmpty() && !student.getAttendeeName().isBlank() && Pattern.matches("[0-9]+" ,student.getStudentID())) {
                                               try {
                                                   Connect connection = new Connect();
                                                   connection.getMeeting();
                                                   Meeting joiningclass = connection.getTest();
                                                   SidePanel panel = connection.getTest2();
                                                   student.setLoginTime();
                                                   joiningclass.addStudent(student);
                                                   Thread t1 = new Thread(() -> {
                                                       try {
                                                           connection.returnMeeting(joiningclass);
                                                       } catch (IOException ex) {
                                                           ex.printStackTrace();
                                                       }
                                                   });

                                                   t1.start();

                                                   classroom = new Classroom(joiningclass, panel, connection);
                                                   add(classroom, "Classroom");
                                                   classroom.setClassroomDetail();
                                                   cardLayout.show(Student.MainFrame.this.getContentPane(), "Classroom");

                                                   Student.MainFrame.super.setTitle(joiningclass.getClassCode() + " Classroom ID : " + joiningclass.getPublicID());

                                               } catch (IOException | ClassNotFoundException ex) {
                                                   ex.printStackTrace();

                                               }

                                           }
                                           else{
                                               JOptionPane.showMessageDialog(new JFrame(), "Enter a valid name and Student ID", "Naming Problem",
                                                       JOptionPane.ERROR_MESSAGE);
                                           }
                                       }

                                   });



        Dimension minimal = new Dimension((int)(screenSize.width * 1),(int)(screenSize.height * .92));
        setMaximumSize(minimal);
        setMinimumSize(minimal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }


}
