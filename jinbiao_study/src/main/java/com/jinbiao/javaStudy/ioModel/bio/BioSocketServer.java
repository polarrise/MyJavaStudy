package com.jinbiao.javaStudy.ioModel.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wangjinbiao
 * @date 2023/2/10 15:42
 * @desc
 */
public class BioSocketServer {

  public static void main(String[] args) throws IOException {
     ServerSocket serverSocket = new ServerSocket(9000);
     while (true){
       System.out.println("等待连接...");
       //该方法将阻塞，直到建立连接<p> 将创建一个新的Socket
       Socket clientSocket = serverSocket.accept();
       System.out.println("客户端有连接了...");
       //一个死循环去处理客户端发过来的消息:
       new Thread(()->{
         try {
           handler(clientSocket);
         } catch (IOException e) {
           e.printStackTrace();
         }
       }).start();
     }
  }

  private static void handler(Socket clientSocket) throws IOException{
    byte[] bytes = new byte[1024];
    System.out.println("准备read...");
    //接收客户端的数据，阻塞方法，没有数据可读时就阻塞
    int read = clientSocket.getInputStream().read(bytes);
    System.out.println("read完毕...");
    while (read != -1){
      System.out.println("接收到客户的数据："+new String(bytes,0,read));
    }
  }
}
