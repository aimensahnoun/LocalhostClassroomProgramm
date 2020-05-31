package Student;

import Student.Client.Connect;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Toolbar extends JPanel {


    private JButton hand;
    private JLabel counterLabel,rectLabel,ovalLabel;
    private int counter,rect,oval = 0;
    private Connect connection;
    private String test;

    public Toolbar(Connect connect) throws IOException {
        this.connection = connect;
        setBorder(BorderFactory.createRaisedBevelBorder());
        setBackground(Color.GRAY);

        counterLabel = new JLabel("Total Shapes Drawn : "+ counter);
        counterLabel.setBorder(new EmptyBorder(7,10,0,0));
        rectLabel = new JLabel("Rectangles Drawn : "+ rect);
        rectLabel.setBorder(new EmptyBorder(7,10,0,0));
        ovalLabel = new JLabel("Cirlces Drawn : "+ oval);
        ovalLabel.setBorder(new EmptyBorder(7,10,0,0));

        hand = new JButton("Raise Hand");

        setLayout(new FlowLayout(FlowLayout.LEFT));

        
        
        
        

        add(hand);
        add(counterLabel);
        add(rectLabel);
        add(ovalLabel);



        hand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
            		
					try {
						connection.raiseHand();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
              	
              
              
              }
            
            
            
        });


    }
    
    public void increaseCounter(){
        this.counter++ ;
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

    


















}



