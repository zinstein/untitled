package ch2;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ClientFrame  extends Frame implements ActionListener{
    Panel p1,p2;
    Label l1,l2,l3;
    TextField tfIP,tfPort,tfSay;
    Button btnConnect, btnSay;
    TextArea ta;
    Socket s1;
    int flag=0;

    ClientFrame(String title) {
        super(title);

        p1=new Panel();                    this.add(p1,"North");
        l1=new Label("Server IP:");             p1.add(l1);
        tfIP=new TextField("127.0.0.1",10);   p1.add(tfIP);
        l2=new Label("Server Port:");             p1.add(l2);
        tfPort=new TextField("4000",10);   p1.add(tfPort);
        btnConnect =new Button("Connect");     p1.add(btnConnect);
        btnConnect.addActionListener(this);

        ta=new TextArea(10,50);             this.add(ta,"Center");

        p2=new Panel();                    this.add(p2,"South");
        l3=new Label("Say:");              p2.add(l3);
        tfSay=new TextField(50);           p2.add(tfSay);
        btnSay =new Button("Say");         p2.add(btnSay);
        btnSay.addActionListener(this);

        this.addWindowListener(new MyHandler());
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        ClientFrame f=new ClientFrame("客户端");
    }

@Override
public void actionPerformed(ActionEvent e) {
    try{
        Object source=e.getSource();
        //启动客户端
        if(source==btnConnect){
            if(flag==0){
                int port=Integer.parseInt(tfPort.getText());
                s1=new Socket("192.168.1.104",2000);
                ta.append("Connect to server..."+'\n');
                Thread t=new ClientTXThread(s1);
                t.start();
                flag++;
            }
        }
        //客户端发送消息
        else if(source== btnSay){
            OutputStream os=s1.getOutputStream();
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(os,"GBK"));
            writer.write(tfSay.getText());
            writer.newLine();
            writer.flush();
        }
    }catch (Exception e2){
        e2.printStackTrace();
    }
}
//通信线程，接收消息
class ClientTXThread extends  Thread{
    Socket s2;
    public ClientTXThread(Socket s){
        s2=s;
    }
    @Override
    public void run() {
        try {
            while(true) {
                InputStream is = s2.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"GBK"));
                String msg = reader.readLine();
                ta.append(msg + '\n');
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
}