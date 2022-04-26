package jinbiao.lambdas.MethodReference.instanceReMethod;


import jinbiao.lambdas.MethodReference.MyPrintable;

public class MethodReference1 {
    public static void printString(MyPrintable p){
        p.print("hello");
    }

    public static void main(String[] args) {
        //完成写法:
        printString(new MyPrintable() {
            @Override
            public void print(String str) {
                MethodReadObj methodReadObj = new MethodReadObj();
                methodReadObj.printUpperCaseString(str);
            }
        });

        //lambda表达式写法:
        printString(str-> {
            MethodReadObj methodReadObj = new MethodReadObj();
            methodReadObj.printUpperCaseString(str);
        });

        /**
         * 使用方法引用：
         * 1.MethodReadObj对象已经存在
         * 2.成员方法printUpperCaseString已经存在
         * 所以可以使用对象名引用成员方法
         */
        MethodReadObj methodReadObj = new MethodReadObj();
        printString(methodReadObj::printUpperCaseString);
    }
}
