package ch2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args)throws Exception {
        ServerSocket server=new ServerSocket(6666);
        System.out.println("server is listening on:"+6666);
        Socket s2=server.accept();
        System.out.println("client connected...");
        InputStream is =s2.getInputStream();
        BufferedReader reader= new BufferedReader(new InputStreamReader(is,"GBK"));
        boolean flag=true;
        while(flag){
            System.out.println(reader.readLine());
        }
        is.close();
        s2.close();
        server.close();
    }
}
