package jinbiao.lambdas.MethodReference.demo;

public class Demo {

    public static String test(Test test){
        String s = test.myTest("123");
        System.out.println(s);
        return s;
    }

    public static void main(String[] args) {
        test(new Test() {
            @Override
            public String myTest(String str) {
                StringBuilder sb=new StringBuilder();
                sb.append(str);
                return sb.reverse().toString();
            }
        });

        test(str->{
            StringBuilder sb=new StringBuilder();
            sb.append(str);
            return sb.reverse().toString();
        });

    }
}
