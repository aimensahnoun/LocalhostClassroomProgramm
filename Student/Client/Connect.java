package Student.Client;

import Teacher.Server.Meeting;
import Teacher.SidePanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connect {

    private Socket socket;
    private BufferedReader input;
    private PrintStream output;
    private Meeting test;
    private SidePanel test2;
    private int count;
    private Color test3,color;

    private int x,y,x2,y2,id=0,ss=0,oldX,oldY;
    private String shapeName;

    public Connect() throws UnknownHostException, IOException
    {
        socket = new Socket("127.0.0.1", 5000);

    }

    public void getMeeting() throws IOException, ClassNotFoundException {
        ObjectInputStream is=new ObjectInputStream(socket.getInputStream());
        test = (Meeting) is.readObject();
        test2 = (SidePanel) is.readObject();
        //test3 = (Color) is.readObject();
        is.close();
       
    }

    public void getDrawings() throws IOException{
        socket = new Socket("127.0.0.1", 9001);
        final DataInputStream is = new DataInputStream(socket.getInputStream());


        x = is.readInt();
        y = is.readInt();
        x2 = is.readInt();
        y2 = is.readInt();
        shapeName = is.readUTF();   
        socket.close();
        
    }


    


    public void returnMeeting(Meeting meeting) throws IOException {
        socket = new Socket("127.0.0.1", 5015);
        System.out.println("connected");
        ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
        os.flush();
        os.writeObject(meeting);
        os.flush();
        socket.close();
        System.out.println("Done");
    }

//    public void getCounter() throws IOException {
//        socket = new Socket("127.0.0.1", 5002);
//        final DataInputStream is = new DataInputStream(socket.getInputStream());
//        this.count = is.readInt();
//        is.close();
//        socket.close();
//    }
    
    public void raiseHand() throws IOException {
    	socket = new Socket("127.0.0.1", 5001);
    	final DataOutputStream os = new DataOutputStream(socket.getOutputStream());
    	
    	os.writeInt(1);
    	socket.close();
    	
    
    	
    }
    
    
    
    public void endClass() throws IOException {
    	
    	socket = new Socket("127.0.0.1", 5004);
    	
    	
    	final DataInputStream is = new DataInputStream(socket.getInputStream());
    	
    	int num = is.readInt();
    	if(num == 1) {
    		int option = JOptionPane.showConfirmDialog(new JFrame(), "Time is over", "Class has ended",
                    JOptionPane.DEFAULT_OPTION);
            
             if(option == JOptionPane.OK_OPTION) {
            	 System.exit(0);
             }
    	}
    	is.close();
    	socket.close();
    	
    	
    }


    public int getCount() {
        return count;
    }

    public int getId(){
        return id;
    }
    public Color getTest3() {
        return test3;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
    public String getShapes(){
        return shapeName;
    }

    public Meeting getTest() {
        return test;
    }

    public SidePanel getTest2() {
        return test2;
    }





}
