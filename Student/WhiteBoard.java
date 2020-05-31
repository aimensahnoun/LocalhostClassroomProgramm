package Student;

import Student.Client.Connect;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WhiteBoard extends JComponent {

    private Graphics2D graphical2;
    private Image drawing;
    private Connect connection;
    private int x,y,x2,y2,endX,endY;
    private String shape = new String();

    public WhiteBoard(Connect connection, Toolbar tool) throws IOException, ClassNotFoundException {
        this.connection = connection;
        setDoubleBuffered(false);
        Thread t1 = new Thread(() -> {
            try {


                while(true) {
                 /*   if(connection.getTest3() != null && graphical2 != null){
                        graphical2.setColor(connection.getTest3());
                    }*/



                    connection.getDrawings();

                    this.shape = connection.getShapes();

                    this.x = connection.getX();
                    this.y = connection.getY();
                    this.x2 = connection.getX2();
                    this.y2 = connection.getY2();

                    if(this.shape.contains("Rect")) {
                        graphical2.drawRect(x, y, x2, y2);
                        repaint();
                        tool.increaseCounter();
                        tool.increaseRectCount();
                        
                    }else if(this.shape.contains("Oval")){
                        graphical2.drawOval(x,y,x2,y2);
                        repaint();
                        tool.increaseCounter();
                        tool.increaseRoundCount();
                        
                        
                    } else{
                        Thread t2 = new Thread(()->{
                            this.clearDrawings();
                        });
                        t2.start();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        t1.start();



    }


//    public void setEndPoint(int x ,int y){
//        this.endX= x;
//        this.endY = y;
//
//    }

    protected void paintComponent(Graphics g) {
        if (drawing == null) {
            drawing = createImage(getSize().width, getSize().height);
            graphical2 = (Graphics2D) drawing.getGraphics();
            graphical2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clearDrawings();
        }
        g.drawImage(drawing, 0, 0, null);
    }

    public void clearDrawings() {
        graphical2.setPaint(Color.white);
        // draw white on entire draw area to clear
        graphical2.fillRect(0, 0, getSize().width, getSize().height);
        graphical2.setPaint(Color.black);
        repaint();

    }
    public void setColor(Color color) {
        graphical2.setColor(color);
    }


}
