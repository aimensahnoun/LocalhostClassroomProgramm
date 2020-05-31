package Teacher;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HostLoginSideBar extends JPanel {

    private JLabel hostName,classCode;
    private JTextField name,code;
    private JButton submit;
    private LoginListener listener;



    public HostLoginSideBar(){
        Dimension dim = getPreferredSize();
        dim.width= 250;
        setPreferredSize(dim);



        hostName = new JLabel("Host Name: ");
        classCode = new JLabel("Course Code: ");

        name = new JTextField(10);
        code = new JTextField(10);



        submit = new JButton("Host Class");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameOutput = name.getText();
                String codeOutput = code.getText();


                LoginEvent ev = new LoginEvent(this,nameOutput,codeOutput);



                if(listener !=null){
                    listener.setClassroomData(ev);
                }

            }
        });

        Border borderInner = BorderFactory.createTitledBorder("Create a new classroom");
        Border borderOuter = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(borderOuter,borderInner));
        loadLayout();
    }
    public void loadLayout(){
        setLayout(new GridBagLayout());

        GridBagConstraints gridCons = new GridBagConstraints();

        //Host Name Row
        gridCons.gridy = 20;

        gridCons.weightx = 1;
        gridCons.weighty = 0.1;

        gridCons.gridx = 0;
        gridCons.fill = GridBagConstraints.NONE;
        gridCons.anchor = GridBagConstraints.LINE_END;
        gridCons.insets = new Insets(0,0,0,5);
        add(hostName,gridCons);

        gridCons.anchor = GridBagConstraints.LINE_START;
        gridCons.gridx = 1;
        gridCons.insets = new Insets(0,0,0,0);
        add(name,gridCons);

        //Class Code Row
        gridCons.gridy++;

        gridCons.weightx = 1;
        gridCons.weighty = 0.1;

        gridCons.anchor = GridBagConstraints.LINE_END;
        gridCons.gridx = 0;

        gridCons.insets = new Insets(0,0,0,5);
        add(classCode,gridCons);

        gridCons.anchor = GridBagConstraints.LINE_START;
        gridCons.gridx = 1;
        gridCons.insets = new Insets(0,0,0,0);
        add(code,gridCons);


        // Button Row
        gridCons.gridy++;
        gridCons.weightx = 1;
        gridCons.weighty = 2;

        gridCons.anchor = GridBagConstraints.FIRST_LINE_START;
        gridCons.insets = new Insets(0,0,0,0);
        gridCons.gridx = 1;
        add(submit,gridCons);





    }

    public void setListener(LoginListener listener) {
        this.listener = listener;
    }
}
