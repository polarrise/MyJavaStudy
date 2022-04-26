package jinbiao.huaweiOd.study2;

public class Test3 {
    public static void GYS(int a,int b){ //15,12
        int n=a%b;
        if(n==0){
            System.out.println(b);
            return;
        }
        if(n!=0){
            GYS(a,n);
        }
    }

    public static void GBS(int a,int b){
         for(int i=Math.min(a,b);i>0;i--){
             if(a%i==0 && b%i==0){
                 System.out.println(a*b/i);
                 break;
             }
         }
    }

    public static void test(String str){  //I am a student   =>tneduts a ma I
        StringBuilder sc=new StringBuilder(str);
        sc.reverse();
        System.out.println(sc);
    }
    public static void main(String[] args) {
        test("I am a student");

        GBS(4,6);

        GYS(45,30   );
    }
}
