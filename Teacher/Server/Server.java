package Teacher.Server;

import Teacher.SidePanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket socket, drawSocket ;
    private Socket s;
    private Meeting holder;
    private SidePanel panel;
    private ObjectOutputStream oss;
    private int pp;

    public Server() throws IOException {
        socket = new ServerSocket(5000);
        drawSocket = new ServerSocket(9001);
        
        
    }


    public void hostMeeting(Color color) throws IOException {
        holder = MeetingHolder.meeting;
        panel = SideBarHolder.sidePanel;
        System.out.println("Thread Running in background");
        s = null;
        s = socket.accept();
        System.out.println("Connection accepted");
        ObjectOutputStream os=new ObjectOutputStream(s.getOutputStream());
        os.writeObject(MeetingHolder.meeting);
        os.writeObject(panel);
        
        s.close();

    }

    public void drawShape(int x,int y , int x2, int y2 , int shape) throws IOException {
    	
        String shapeName;
        switch (shape){
            case 0:
                shapeName = "Rect";
                break;
            case 1:
                shapeName = "Oval";
                break;
            case 74:
                shapeName = "Clear";
                break;
            default:
                shapeName = "NaN";
                break;
        }
        
        
        s = null;
        s = drawSocket.accept();

        final DataOutputStream os = new DataOutputStream(s.getOutputStream());



        os.writeInt(x);
        os.flush();
        os.writeInt(y);
        os.flush();
        os.writeInt(x2);
        os.flush();
        os.writeInt(y2);
        os.flush();
        os.writeUTF(shapeName);
        
       
        os.close();
        s.close();
        
        

    }

    public Meeting getUpdatedMeeting() throws IOException, ClassNotFoundException {
    	ServerSocket newS = new ServerSocket(5015);
        s = null;
        s = newS.accept();
        System.out.println("Connected");
        ObjectInputStream is=new ObjectInputStream(s.getInputStream());
        Meeting meeting = (Meeting) is.readObject();
        System.out.println("Got Meeting");
        is.close();
        s.close();
        System.out.println("Done");
        return meeting;

    }



//    public void sendCount(int counter) throws IOException {
//    	ServerSocket newS = new ServerSocket(5002);
//    	s = null;
//    	s = newS.accept();
//    	final DataOutputStream os = new DataOutputStream(s.getOutputStream());
//
//    	os.writeInt(counter);
//    	os.close();
//    	s.close();
//    	newS.close();
//
//    }
    public void getHand() throws IOException {
    	ServerSocket newS = new ServerSocket(5001);
    	s = null;
    	s = newS.accept();
    	final DataInputStream is = new DataInputStream(s.getInputStream());

    	int num = is.readInt();
    	if(num == 1) {
    		JOptionPane.showMessageDialog(new JFrame(), "A student has raised their hand", "Question",
                    JOptionPane.INFORMATION_MESSAGE);
    	}
    	is.close();
    	s.close();
    	newS.close();

    }



    public void classEnd() throws IOException {


    	ServerSocket newS = new ServerSocket(5004);
    	s = null;
    	s = newS.accept();
    	final DataOutputStream os = new DataOutputStream(s.getOutputStream());
    	os.writeInt(1);
    	s.close();
    	newS.close();

    }

    public void sendColor(Color color,int id) throws IOException {
        s = null;
        s = socket.accept();
        final DataOutputStream os = new DataOutputStream(s.getOutputStream());
        final DataInputStream is = new DataInputStream(s.getInputStream());
        os.writeInt(id);

        oss = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));
        oss.writeObject(color);
        oss.flush();

        s.close();
    }






}
