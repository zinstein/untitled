package ch2;

import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.util.HashMap;


public class ServerFrame  extends Frame implements ActionListener{
    Panel p1,p2;
    Label l1,l2;
    TextField tfPort,tfSay;
    Button btnStart, btnSay;
    TextArea ta;
    Choice clientChoice;

    int num=0;
    HashMap<String, Socket> clientMap=new  HashMap<String, Socket>();

    ServerFrame(String title) {
        super(title);

        p1=new Panel();                    this.add(p1,"North");
        l1=new Label("Port:");             p1.add(l1);
        tfPort=new TextField("4000",40);   p1.add(tfPort);
        btnStart=new Button("Start");     p1.add(btnStart);
        btnStart.addActionListener(this);

        ta=new TextArea(10,40);             this.add(ta,"Center");

        p2=new Panel();                    this.add(p2,"South");
        l2=new Label("Say:");              p2.add(l2);
        clientChoice=new Choice();         p2.add(clientChoice);
        clientChoice.add("Please...");
        tfSay=new TextField(40);           p2.add(tfSay);
        btnSay =new Button("Say");         p2.add(btnSay);
        btnSay.addActionListener(this);

        this.addWindowListener(new MyHandler());
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        ServerFrame f=new ServerFrame("服务端");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if(source==btnStart){
            ta.append("Server starting...\n");
        }
        else if(source== btnSay){
        }
    }
}

class MyHandler extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}