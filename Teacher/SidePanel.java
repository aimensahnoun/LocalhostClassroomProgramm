package Teacher;

import Teacher.Server.Meeting;
import Teacher.Server.SideBarHolder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class SidePanel extends JPanel implements Serializable, ActionListener {


    private JLabel hostNameLabel,hostName,attendees,student;

    public SidePanel(Meeting meeting){
        SideBarHolder.sidePanel = this;
        Dimension dim = getPreferredSize();
        dim.width = 300;
        setPreferredSize(dim);
        setBorder(BorderFactory.createEtchedBorder());

        setBackground(Color.GRAY);
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));


        hostNameLabel = new JLabel("Host:");
        hostNameLabel.setBorder(new EmptyBorder(10,10,10,10));
        hostName = new JLabel();
        hostName.setBorder(new EmptyBorder(0,10,10,10));
        attendees = new JLabel("Attendees:");
        attendees.setBorder(new EmptyBorder(10,10,10,10));
        student = new JLabel();
        student.setBorder(new EmptyBorder(10,10,10,10));




        add(hostNameLabel);
        add(hostName);
        add(attendees);
        add(student);

    }

    public void setHostName(String hostName) {
        this.hostName.setText(hostName);
    }
    public void setStudent(String student){
        this.student.setText(student);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
