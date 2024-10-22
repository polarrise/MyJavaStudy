package com.jinbiao.javaStudy.modifier;

/**
 * protected: 可以在本包下和其子类访问（本包子类或其他包的子类）、把Test换个包目录就不行了..
 */
public class Test {

    public static void main(String[] args) {
        Parent par = new Parent();
        par.test();

        SubClass sub = new SubClass();
        sub.test();

        ParInterfaceImpl parInterface = new ParInterfaceImpl();
        parInterface.test();
        parInterface.test2();
    }
}
