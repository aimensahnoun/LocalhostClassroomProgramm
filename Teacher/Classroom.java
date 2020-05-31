package Teacher;

import Teacher.Server.Meeting;
import Teacher.Server.MeetingHolder;
import Teacher.Server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class Classroom extends JPanel implements ItemListener {

    private Toolbar toolbar ;
    public static SidePanel sidePanel;
    private Server server = new Server();
    private Meeting updatedMeeting;

    public Classroom() throws IOException {

        setLayout(new BorderLayout());

        toolbar = new Toolbar(server);
        final WhiteBoard whiteBoard = new WhiteBoard(server,toolbar);
        sidePanel = new SidePanel(MeetingHolder.meeting);


        add(toolbar,BorderLayout.NORTH);
        add(whiteBoard,BorderLayout.CENTER);
        add(sidePanel,BorderLayout.EAST);




        toolbar.setShapeListener(new ShapeListener(){
            public void shapeSet(ShapeEvent e){
                int id = e.getShapeId();
                String title = e.getTitle();
                Color color = e.getColor();
                try {
                    whiteBoard.actionReciver(id,title,color);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        Thread t1 = new Thread(() -> {
            try {
                       server.hostMeeting(Color.black);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        t1.start();


        Thread t2 = new Thread(() ->{
            try{

                    updatedMeeting = server.getUpdatedMeeting();
                    MeetingHolder.meeting = updatedMeeting;
                    System.out.println(updatedMeeting);
                    
                    addNewStudent(updatedMeeting.getAttendees().get(0).getAttendeeName());
                    
                    toolbar.getData(updatedMeeting.getAttendees().get(0).getAttendeeName(), updatedMeeting.getAttendees().get(0).getStudentID(), updatedMeeting.getAttendees().get(0).getLoginTime(),updatedMeeting.getHostName(),updatedMeeting.getClassCode());

            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        });

        t2.start();

       
        Thread t3 = new Thread(()->{
        	try {
        		while(true) {
				server.getHand();
        		}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });
        
        t3.start();
       
        
        
        
        
    }


    public void setClassroomDetails(String hostname){
        sidePanel.setHostName(hostname);

    }

    public void addNewStudent(String studentName){
        sidePanel.setStudent(studentName);
    }


    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
