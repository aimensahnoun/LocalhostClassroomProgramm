package Student;



import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentLoginSideBar extends JPanel {

    private JLabel studentName,classroomID;
    private JTextField student, id;
    private JButton submit;
    private LoginListener listener;



    public StudentLoginSideBar(){
        Dimension dim = getPreferredSize();
        dim.width= 250;
        setPreferredSize(dim);



        studentName = new JLabel("Student Name: ");
        classroomID = new JLabel("Student ID: ");

        student = new JTextField(8);
        id = new JTextField(8);



        submit = new JButton("Join Class");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = student.getText();
                String studentid = id.getText();
                Student.LoginEvent ev = new LoginEvent(this,studentName,studentid);
                if(listener != null){
                    listener.setStudentdata(ev);
                }
            }
        });


        Border borderInner = BorderFactory.createTitledBorder("Join a classroom");
        Border borderOuter = BorderFactory.createEmptyBorder(5,5,5,15);
        setBorder(BorderFactory.createCompoundBorder(borderOuter,borderInner));
        loadLayout();
    }
    public void loadLayout(){
        setLayout(new GridBagLayout());

        GridBagConstraints gridCons = new GridBagConstraints();

        //Host Name Row
        gridCons.gridy = 20;

        gridCons.weightx = 1;
        gridCons.weighty = 0.1;

        gridCons.gridx = 0;
        gridCons.fill = GridBagConstraints.NONE;
        gridCons.anchor = GridBagConstraints.LINE_END;
        gridCons.insets = new Insets(0,0,0,5);
        add(studentName,gridCons);

        gridCons.anchor = GridBagConstraints.LINE_START;
        gridCons.gridx = 1;
        gridCons.insets = new Insets(0,0,0,0);
        add(student,gridCons);

        //Class Code Row
        gridCons.gridy++;

        gridCons.weightx = 1;
        gridCons.weighty = 0.1;

        gridCons.anchor = GridBagConstraints.LINE_END;
        gridCons.gridx = 0;

        gridCons.insets = new Insets(0,0,0,5);
        add(classroomID,gridCons);

        gridCons.anchor = GridBagConstraints.LINE_START;
        gridCons.gridx = 1;
        gridCons.insets = new Insets(0,0,0,0);
        add(id,gridCons);


        // Button Row
        gridCons.gridy++;
        gridCons.weightx = 1;
        gridCons.weighty = 2;

        gridCons.anchor = GridBagConstraints.FIRST_LINE_START;
        gridCons.insets = new Insets(0,0,0,0);
        gridCons.gridx = 1;
        add(submit,gridCons);





    }

    public void setListener(LoginListener listener) {
        this.listener = listener;
    }
}
