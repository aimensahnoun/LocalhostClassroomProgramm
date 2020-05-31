package Teacher;

import java.awt.*;
import java.util.EventObject;

public class ShapeEvent extends EventObject {
    int shapeId;
    String title;
    Color color;

    public Color getColor() {
        return color;
    }

    public ShapeEvent(Object source){
        super(source);
    }

    public String getTitle() {
        return title;
    }



    public ShapeEvent(Object source, int shapeId,String title,Color color) {
        super(source);
        this.shapeId = shapeId;
        this.title = title;
        this.color = color;
    }

    public int getShapeId() {
        return shapeId;
    }

    public void setShapeId(int shapeId) {
        this.shapeId = shapeId;
    }
}
