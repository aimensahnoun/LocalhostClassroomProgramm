package Student;

import Student.Client.Connect;
import Teacher.Server.Meeting;
import Teacher.SidePanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class Classroom extends JPanel implements ItemListener {

    private Toolbar toolbar ;
    private SidePanel sidePanel;
    private String studentName, hostname;

    public Classroom(Meeting meetingInput , SidePanel panel, Connect connection) throws IOException, ClassNotFoundException {

        studentName = meetingInput.getAttendees().get(0).getAttendeeName();
        hostname = meetingInput.getHostName();
        setLayout(new BorderLayout());

        toolbar = new Toolbar(connection);
        final WhiteBoard whiteBoard = new WhiteBoard(connection, toolbar);
        sidePanel = panel;



        add(toolbar,BorderLayout.NORTH);
        add(whiteBoard,BorderLayout.CENTER);
        add(sidePanel,BorderLayout.EAST);

        
        Thread t1 = new Thread(()->{
         while(true) {
    		try {
    			
				connection.endClass();
				
    			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
         }
    	});
    	
    	t1.start();


    }


    public void setClassroomDetail(){
        sidePanel.setHostName(this.hostname);
        sidePanel.setStudent(this.studentName);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
