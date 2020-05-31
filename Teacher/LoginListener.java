package Teacher;

import java.util.EventListener;

public interface LoginListener extends EventListener {
    public void setClassroomData(LoginEvent e );
}
