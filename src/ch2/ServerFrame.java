package ch2;

import java.awt.*;
import java.awt.event.*;


public class ServerFrame  extends Frame implements ActionListener{
    Panel p1,p2;
    Label l;
    TextField tf;
    Button b;
    TextArea ta;
    CheckboxGroup cbg;
    Choice c;

    ServerFrame(String title) {
        super(title);

        p1=new Panel();
        this.add(p1,"North");
        l=new Label("Port:");   p1.add(l);
        tf=new TextField("4000",44);   p1.add(tf);
        b=new Button("listen");   p1.add(b);
        b.addActionListener(this);

        ta=new TextArea(4,44); this.add(ta,"Center");

        p2=new Panel();  this.add(p2,"South");
        cbg=new CheckboxGroup();
        Checkbox cb1=new Checkbox("swimming",cbg,true);   p2.add(cb1);
        Checkbox cb2=new Checkbox("football",cbg,false);  p2.add(cb2);
        Checkbox cb3=new Checkbox("basketball",cbg,false);p2.add(cb3);

        c=new Choice();  p2.add(c);
        c.add("red");c.add("blue");c.add("green");

        this.addWindowListener(new MyHandler());
        this.setSize(500,250);
        this.setVisible(true);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        new ServerFrame("hi");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ta.append("Server is listening on port 4000\n");
    }
}

class MyHandler extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}