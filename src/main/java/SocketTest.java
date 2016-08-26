
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jakob
 */



public class SocketTest {
    
    String ip = "localhost";
    int portNum = 8080;
    
    public void setPortNum(int portNum){
        this.portNum = portNum;
    }
    public void setIp(String ip){
        this.ip = ip;
    }
    
    public static void handleClient(Socket s){
        
        try{
            
        
        Scanner scn = new Scanner(s.getInputStream());
        PrintWriter prnt = new PrintWriter(s.getOutputStream(),true);
        String msg ="";
        
        
            while(!msg.equals("STOP"))
            {
            msg = scn.nextLine();
                if (msg.indexOf("#")>=0)
                {
                    if (msg.contains("UPPER"))
                    {
                        prnt.println(msg.toUpperCase().substring(msg.indexOf("#")+1));
                        
                    }
                    else if (msg.contains("LOWER"))
                    {
                            prnt.println(msg.toLowerCase().substring(msg.indexOf("#")+1));
                    }
                    else if (msg.contains("REVERSE"))
                    {
                            prnt.println(new StringBuilder(msg.substring(msg.indexOf("#")+1)).reverse().toString());
                            //sb = new StringBuilder(msg.substring(msg.indexOf("#")+1)).reverse().toString();
                    }
                    else if(msg.contains("TRANSLATE"))
                    {
                        if (msg.substring(msg.indexOf("#")+1).equalsIgnoreCase("hund"))
                        {
                            prnt.println("dog");

                        }
                        else
                        {
                            prnt.println("#NOT_FOUND");
                        }
                    }
                    else
                    {
                        msg="STOP";
                    }
                }
            
            
            }
        scn.close();
        prnt.close();
        s.close();
        
        }
        catch(IOException ex){
            
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        
     
    SocketTest sT = new SocketTest();
    int portNum=8080;
            String ip="localhost";
        if (args.length == 2) {
            portNum = Integer.parseInt(args[1]);
            sT.setPortNum(portNum);
            ip = args[0];
            sT.setIp(ip);
            
        }
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, portNum));
        
        System.out.println("server start "+ portNum + ip);
    
        while(true){
        Socket link =ss.accept();
        System.out.println("newconn");
        handleClient(link);
    }
        
    }
    
    
}
