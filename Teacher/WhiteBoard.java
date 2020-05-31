package Teacher;

import Teacher.Server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

public class WhiteBoard extends JComponent {


    private int shape;
    private Graphics2D graphical2;
    private Image drawing;
    private int cX, cY, oldX, oldY , endX,endY;
    private Server server;
    private int id = 0;
    private Boolean done = false;
    private int counter = 0;

    public WhiteBoard(Server server,Toolbar tool){
        this.server = server;
        this.shape = 0;
        this.counter = tool.getCounter();
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
            public void mouseReleased(MouseEvent e) {
                setEndPoint(e.getX(), e.getY());
                int px = Math.min(oldX,endX);
                int py = Math.min(oldY,endY);
                int pw=Math.abs(oldX-endX);
                int ph=Math.abs(oldY-endY);
                

                if(shape == 0){


                    graphical2.drawRect(px, py, pw, ph);
                    tool.increaseCounter();
                    tool.increaseRectCount();
                    Thread t1 = new Thread(() -> {
                        try {
                            server.drawShape(px,py,pw,ph,0);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });

                    t1.start();








                }else if(shape == 1){
                    graphical2.drawOval(px,py,pw,ph);
                    tool.increaseCounter();
                    tool.increaseRoundCount();
                    Thread t1 = new Thread(() -> {
                        try {
                            server.drawShape(px,py,pw,ph,1);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });

                    t1.start();

                }




                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
//                cX = e.getX();
//                cY = e.getY();
//                if (graphical2 != null && shape == 74 ) {
//                    graphical2.drawLine(oldX, oldY, cX, cY);
//                    repaint();
//
//                    Thread t1 = new Thread(() -> {
//                        try {
//
//                            server.drawShape(oldX, oldY, cX, cY,74);
//
//                        } catch (IOException ex) {
//                            ex.printStackTrace();
//                        }
//                    });
//
//                    t1.start();
//
//                    oldX = cX;
//                    oldY = cY;
//                }
//                else{
//                    setEndPoint(e.getX(),e.getY());
//                    repaint();
//                }
            }
        });
    }

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
        graphical2.fillRect(0, 0, getSize().width, getSize().height);
        graphical2.setPaint(Color.black);
        repaint();

    }

    public void setEndPoint(int x ,int y){
        this.endX= x;
        this.endY = y;

    }

    public void actionReciver(int id , String title,Color color) throws IOException {
        if(id == 99){
            this.id = 99;
            setColor(color);
            Thread t1 = new Thread(() -> {
                try {
                    server.sendColor(color,id);

                    } catch (IOException ex) {
                    ex.printStackTrace();
                    }
            });

            t1.start();

        }
        else if(title == "Clear"){
            clearDrawings();
        }else{
            setShape(id);
        }
    }

    public void setShape(int shape) {
        if(shape == 0){
            this.shape = shape;
        }else if(shape == 1){
            this.shape = shape;
        }else if (shape == 2){
            this.shape = shape;
        }

    }

    public void setColor(Color color) {
        graphical2.setPaint(color);
    }


}
