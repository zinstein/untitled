package ch2;

import java.awt.*;
import java.awt.event.*;


public class ClientFrame  extends Frame implements ActionListener{
    Panel p1,p2;
    Label l1,l2,l3;
    TextField tfIP,tfPort,tfSay;
    Button btnConnect, btnSay;
    TextArea ta;

    ClientFrame(String title) {
        super(title);

        p1=new Panel();                    this.add(p1,"North");
        l1=new Label("Server IP:");             p1.add(l1);
        tfIP=new TextField("127.0.0.1",10);   p1.add(tfIP);
        l2=new Label("Server Port:");             p1.add(l2);
        tfPort=new TextField("4000",10);   p1.add(tfPort);
        btnConnect =new Button("Connect");     p1.add(btnConnect);
        btnConnect.addActionListener(this);

        ta=new TextArea(10,40);             this.add(ta,"Center");

        p2=new Panel();                    this.add(p2,"South");
        l3=new Label("Say:");              p2.add(l3);
        tfSay=new TextField(40);           p2.add(tfSay);
        btnSay =new Button("Say");         p2.add(btnSay);
        btnSay.addActionListener(this);

        this.addWindowListener(new MyHandler());
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        ClientFrame f=new ClientFrame("服务端");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if(source== btnConnect){
            ta.append("Connect to server...\n");
        }
        else if(source== btnSay){
        }
    }
}

