package com.jinbiao.huaweiOd.study2;

public class Test1 {

    /**
     * 最小公约数
     * %取余。  15%12=3。 15除12得1余3.    12%15=12   12除15得0余12   .
     */
    int GYS(int a,int b){
        int n=a%b;        //15%12=3
        if(n==0){        //如果a%b==0,最大公约数就是b
            return b;
        }else{          //否则,拿着15%12=3,再递归一次GYS(15,3)
            return GYS(a,n);   //GYS(15,3)->return 3
        }
    }

    /**
     * 最小公倍数。  让a,b都取除以一个a,b都能除尽的数。  然后用a*b/n 即可求得最小公倍数
     */
    static void GBS(int a,int b){
        for(int i = Math.min(a,b); i > 0; i--){
            if(a%i == 0 && b%i == 0){   //找到都可以除尽得那个数。 比如 6，9 都可以把3除尽, 然后6*9/3=18就是他们的最小公倍数
                System.out.println(a*b/i);
                break;
            }
        }
    }
    public static void main(String[] args) {
//        Scanner in=new Scanner(System.in);
//        int a=in.nextInt();
//        int b=in.nextInt();
        System.out.println(new Test1().GYS(15,12));
        GBS(2 ,4);

        GBS(6 ,8);

        GBS(6 ,9);
    }
}
