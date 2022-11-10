package ch2;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class ServerFrame  extends Frame implements ActionListener{
    Panel p1,p2;
    Label l1,l2;
    TextField tfPort,tfSay;
    Button btnStart, btnSay;
    TextArea ta;
    Choice clientChoice;

    ServerSocket server;
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
        try{
            Object source=e.getSource();
            //启动服务端
            if(source==btnStart){
                Thread t=new ListenThread();
                ta.append("server starting..."+'\n');
                t.start();
            }
            //服务端发送消息
            else if(source== btnSay){
                String clientName=clientChoice.getSelectedItem();
                Socket s1=clientMap.get(clientName);
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
    //服务端连接
    class ListenThread extends Thread{
        @Override
        public void run() {
            try{
                int port=Integer.parseInt(tfPort.getText());
                server=new ServerSocket(port);
                while (true){
                    Socket s1=server.accept();
                    num++;
                    String clientName="client-"+num;
                    ta.append(clientName+" connecting..."+"\n");
                    clientChoice.add(clientName);
                    clientMap.put(clientName,s1);
                    Thread t=new ServerTXThread(s1);
                    t.start();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    //服务端接收
    class ServerTXThread extends  Thread{
        Socket s1;
        public ServerTXThread(Socket s){
            s1=s;
        }
        @Override
        public void run() {
            try {
                while (true){
                    InputStream is=s1.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                    String msg=reader.readLine();
                    ta.append(msg+'\n');
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

class MyHandler extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}