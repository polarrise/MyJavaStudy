package com.jinbiao.manyThreads.control_thread_run;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author wangjinbiao
 * @date 2023/2/6 16:36
 * @desc 三个线程按序打印ABC方式11:使用管道流PipedStream

 *  实现思路:线程之间通信，还有一种比较笨重的办法——PipedInputStream/PipedOutStream。
 * 一个线程使用PipedOutStream写数据，一个线程使用PipedInputStream读数据，而且Piped的读取只能一对一。
 * 那么，在这道题里：
 * 线程A使用PipedOutStream向线程B写入数据，线程B读取后，打印输出
 * 线程B和C也是相同的姿势
   准确来讲，一个中文字符占1-4个字节。编码不同，占据的字节数不同：
   1、占1个字节的编码是ISO-8859-1；
   2、占2个字节的编码是GB2312、GBK、GB18030、UTF-16BE、UTF-16LE；
   3、占3个字节的编码是UTF-8；
   4、占4个字节的编码是UTF-16。
 */
public class UsePipedStream {

  public static void main(String[] args) throws IOException {
    //线程A的输出流
    PipedOutputStream pipedOutputStreamA = new PipedOutputStream();
    //线程B的输出流
    PipedOutputStream pipedOutputStreamB = new PipedOutputStream();
    //线程B的输入流
    PipedInputStream pipedInputStreamB = new PipedInputStream();
    //线程C的输入流
    PipedInputStream pipedInputStreamC = new PipedInputStream();

    //一个线程使用PipedOutStream写数据，一个线程使用PipedInputStream读数据
    pipedOutputStreamA.connect(pipedInputStreamB);
    pipedOutputStreamB.connect(pipedInputStreamC);

    new Thread(()->{
      System.out.println(Thread.currentThread().getName() + "打印A");
      try {
        System.out.println("'打印B'占几个字节:"+"打印B".getBytes().length);
        pipedOutputStreamA.write("打印B".getBytes());
      } catch (IOException e) {
        e.printStackTrace();
      }
    },"Thread-A").start();

    new Thread(()->{
      //流读取:
      byte [] buffer = new byte[7];
      try {
        pipedInputStreamB.read(buffer);
        //转换成String
        String msg = new String(buffer);
        System.out.println(Thread.currentThread().getName() + msg);
        pipedOutputStreamB.write("打印C".getBytes());
      } catch (IOException e) {
        e.printStackTrace();
      }
    },"Thread-B").start();

    new Thread(() -> {
      //流读取:
      byte [] buffer = new byte[7];
      try {
        pipedInputStreamC.read(buffer);
        String msg = new String(buffer);
        System.out.println(Thread.currentThread().getName() + msg);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }, "Thread-C").start();
  }
}
