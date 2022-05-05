package jinbiao.javaStudy.StackOrHeap;

/**
 * 1、栈内存用来存放基本类型的变量和引用变量，堆内存用来存储java中的对象，无论是成员变量，局部变量，还是类变量，他们指向的对象都存储在堆内存中。
 */
public class StringTest {
    public static void main(String[] args) {
//        1.  步骤：
//        1) 栈中开辟一块空间存放引用str1，
//        2) String池中开辟一块空间，存放String常量"abc"，
//        3) 引用str1指向池中String常量"abc"，
//        4) str1所指代的地址即常量"abc"所在地址，输出为true
        String str1 = "abc";
        System.out.println("1结果:"+String.valueOf(str1 == "abc"));

//        2. 步骤：
//        1) 栈中开辟一块空间存放引用str2，
//        2) 堆中开辟一块空间存放一个新建的String对象"abc"，
//        3) 引用str2指向堆中的新建的String对象"abc"，
//        4) str2所指代的对象地址为堆中地址，而常量"abc"地址在池中，输出为false
        String str2 = new String("abc");
        System.out.println("2结果:"+String.valueOf(str2 == "abc"));


//        3.  步骤：
//        1) 栈中开辟一块空间存放引用str3，
//        2) 堆中开辟一块新空间存放另外一个(不同于str2所指)新建的String对象，
//        3) 引用str3指向另外新建的那个String对象
//        4) str3和str2指向堆中不同的String对象，地址也不相同，输出为false
        String str3 = new String("abc");
        System.out.println("3结果:"+String.valueOf(str3 == str2));


//        4.步骤：
//        1) 栈中开辟一块空间存放引用str4，
//        2) 根据编译器合并已知量的优化功能，池中开辟一块空间，存放合并后的String常量"ab"，
//        3) 引用str4指向池中常量"ab"，
//        4) str4所指即池中常量"ab"，输出为true
        String str4 = "a" + "b";
        System.out.println("4结果:"+String.valueOf(str4 == "ab"));


        //5.步骤：同4
        final String s = "a";
        String str5 = s + "b";
        System.out.println("5结果:"+String.valueOf(str5 == "ab"));

//        6.步骤：
//        1) 栈中开辟一块中间存放引用s1，s1指向池中String常量"a"，
//        2) 栈中开辟一块中间存放引用s2，s2指向池中String常量"b"，
//        3) 栈中开辟一块中间存放引用str6，
//        4) s1 + s2通过StringBuilder的最后一步toString()方法还原一个新的String对象"ab"，因此堆中开辟一块空间存放此对象，
//        5) 引用str6指向堆中(s1 + s2)所还原的新String对象，
//        6) str6指向的对象在堆中，而常量"ab"在池中，输出为false
        String s1 = "a";
        String s2 = "b";
        String str6 = s1 + s2;
        System.out.println("6结果:"+String.valueOf(str6 == "ab"));

//        7.步骤：
//        1) 栈中开辟一块空间存放引用str7，
//        2) substring()方法还原一个新的String对象"ab"（不同于str6所指），堆中开辟一块空间存放此对象，
//        3) 引用str7指向堆中的新String对象，
         String str7 = "abc".substring(0, 2);
         System.out.println("7结果:"+String.valueOf(str7 == "ab"));


//        8.步骤：
//        1) 栈中开辟一块空间存放引用str6，
//        2) toUpperCase()方法还原一个新的String对象"ABC"，池中并未开辟新的空间存放String常量"ABC"，
//        3) 引用str8指向堆中的新String对象
        String str8 = "abc".toUpperCase();
        System.out.println("8结果:"+(str8 == "ABC"));

    }
}
