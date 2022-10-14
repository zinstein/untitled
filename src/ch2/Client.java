package ch2;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket s1=new Socket("127.0.0.1",6666);
        OutputStream os=s1.getOutputStream();
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(os,"GBK"));
        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        while (flag) {
            String s = sc.next();
            writer.write(s);
            writer.newLine();
            writer.flush();
        }
        os.close();
        writer.close();
    }
}
