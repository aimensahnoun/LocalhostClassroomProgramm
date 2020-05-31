package Teacher;

import Teacher.Server.Meeting;
import Teacher.Server.Server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Toolbar extends JPanel {

    private JMenu shapesMenu;
    private JMenuBar mb;
    private JMenuItem rectangle, circle, line;
    private JColorChooser colorChooser;
    private ShapeListener shapeListener;
    private Color currentPenColor;
    private JButton brush, color, clear,attendance;
    private JLabel counterLabel,rectLabel,ovalLabel,countdown;
    private JPanel colorPanel;
    private int counter,rect,oval = 0;
    private Timer timer;
    private Server server;
    private Meeting meeting;
    private String host,student,studentID,courseID,loginTime;
    private int tim = 8;



    public Toolbar(Server server) throws IOException {
        setBorder(BorderFactory.createRaisedBevelBorder());
        setBackground(Color.GRAY);

        this.server = server;
        
        
        

        //currentPenColor = Color.BLACK;

//        colorPanel = new JPanel();
//        colorPanel.setBackground(Color.gray);

        clear = new JButton("Clear");
        //brush = new JButton("Brush");
        //color = new JButton("Choose Color");

        counterLabel = new JLabel("Total Shapes Drawn : "+ counter);
        counterLabel.setBorder(new EmptyBorder(20,10,20,0));
        rectLabel = new JLabel("Rectangles Drawn : "+ rect);
        rectLabel.setBorder(new EmptyBorder(20,10,20,0));
        ovalLabel = new JLabel("Cirlces Drawn : "+ oval);
        ovalLabel.setBorder(new EmptyBorder(20,10,20,0));
        countdown = new JLabel("Remaining time : 01:30");
        ovalLabel.setBorder(new EmptyBorder(20,10,20,0));
        
        
        

        /*colorPanel.setLayout(new BoxLayout(colorPanel,BoxLayout.PAGE_AXIS));
        colorPanel.add(color);
        colorPanel.add(colorLabel);



        colorLabel.setBackground(null);*/


        mb = new JMenuBar();
        shapesMenu = new JMenu("Select Shapes:");
        rectangle = new JMenuItem("Rectangle");
        circle = new JMenuItem("Circle");

        shapesMenu.add(rectangle);
        shapesMenu.add(circle);
        mb.add(shapesMenu);
        

        //color.addActionListener(new ColorButtonListener());



        timer = new Timer(1000, new ActionListener() {
            int time = 90;
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                countdown.setText("Remaining time : "+format(time / 60) + ":" + format(time % 60));
                if (time == 0) {
                	
                		tim = 74;
                	
                	
                	
                	
                    final Timer timer = (Timer) e.getSource();
                    timer.stop();
                    int option = JOptionPane.showConfirmDialog(new JFrame(), "Time is over", "Class has ended",
                            JOptionPane.DEFAULT_OPTION);
                    
                     if(option == JOptionPane.OK_OPTION) {
                    	 
                    	 System.exit(0);
                     }
                     
                }
            }
        });
        
        
        timer.start();
        
        
        Thread t1 = new Thread(()->{
        	while(true) {
        		System.out.println("");
        	if(tim == 74) {
        		System.out.println("");
        	try {
				server.classEnd();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        	}
        	}
        });
        
        
        
        t1.start();
        
    	
        

        rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = 0;

                ShapeEvent ev = new ShapeEvent(this, id, "NaN",currentPenColor);
                if (shapeListener != null) {
                    shapeListener.shapeSet(ev);
                }
            }
        });


        circle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = 1;
                ShapeEvent ev = new ShapeEvent(this, id, "NaN",currentPenColor);
                if (shapeListener != null) {
                    shapeListener.shapeSet(ev);
                }
            }
        });


        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = "Clear";
               Thread t1 = new Thread(()->{
                    try {
                        server.drawShape(0,0,0,0,74);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                t1.start();
                ShapeEvent ev = new ShapeEvent(this, 74, title,currentPenColor);
                if (shapeListener != null) {
                    shapeListener.shapeSet(ev);
                }
            }
        });

        /*brush.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ShapeEvent ev = new ShapeEvent(this, 74, "NaN",currentPenColor);
                if (shapeListener != null) {
                    shapeListener.shapeSet(ev);
                }
            }
        });*/

        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        attendance = new JButton("Take Attendance");
        
        
        
        attendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
              try {
				FileWriter atten = new FileWriter("attendance.txt");
				atten.write("Course : " + courseID + " hosted by : " + host+"\n"+ 
							"Student : "+ student + " with student ID : "+ studentID + " has joined the classroom at : "+ loginTime+"\n");
				atten.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
              
              
              

                
            }
        });
        

        add(mb);
        //add(brush);

        //add(colorPanel);
        add(clear);
        add(counterLabel);
        add(rectLabel);
        add(ovalLabel);
        add(attendance);
        add(countdown);
        
        
        
        
        
    }

    public void setShapeListener(ShapeListener listener) {
        this.shapeListener = listener;
    }

    public void setCurrentPenColor(Color currentPenColor) {
        this.currentPenColor = currentPenColor;
    }
    public void increaseCounter(){
        this.counter++;
        this.counterLabel.setText("Total Shapes Drawn :" + this.counter);
    }
    public void increaseRectCount() {
    	this.rect++;
    	this.rectLabel.setText("Rectangles Drawn : "+ this.rect);
    }
    public void increaseRoundCount() {
    	this.oval++;
    	this.ovalLabel.setText("Cirlces Drawn : "+ this.oval);
    }
    public int getCounter() {
        return counter;
    }
    
    public void getData(String student , String studentID,String loginTime, String host, String courseID) {
    	this.student = student;
    	this.studentID = studentID;
    	this.loginTime = loginTime;
    	this.host = host;
    	this.courseID = courseID;
    }
    
    private static String format(int i) {
        String result = String.valueOf(i);
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }
}



