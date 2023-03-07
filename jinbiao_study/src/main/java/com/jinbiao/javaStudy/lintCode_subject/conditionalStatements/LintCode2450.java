package com.jinbiao.javaStudy.lintCode_subject.conditionalStatements;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.function.IntConsumer;

/**
 * @author com.jinbiao
 * @date 2022/1/11
 * @apiNote
 */
public class LintCode2450 {
    public void printHelloWorld(IntConsumer intConsumer) throws InterruptedException {
        new Thread(() -> {
            intConsumer.accept(1);
        }).start();
    }
    public static void main(String[] args) {
        try {
            String outputPath = args[1];
            PrintStream ps = new PrintStream(outputPath);
            IntConsumer print = (x -> {
                try {
                    String name = Thread.currentThread().getName();
                    if ("main".equals(name)) {
                        throw new Exception("You need to start a new thread.");
                    }
                    ps.write("hello world".getBytes(StandardCharsets.UTF_8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            LintCode2450 solution = new LintCode2450();
            solution.printHelloWorld(print);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
