package com.jinbiao.dataStruct_study.linkedlist.oneWay;

/**
 * @author com.jinbiao
 * @date 2021/12/6
 * @apiNote
 */
public class Sulotion {

    /**
     *刚开始链表:1->2->3->4->5->null
     * 刚开始head和odd指向同一块内存地址,由于odd在循环体里面的赋值,造成odd指向第三个节点、第五个节点,  odd换了指向
     * 刚开始head.next和even指向同一块内存地址,由于even在循环体里面的赋值,造成even指向第四个节点、第六个节点 null, even换了指向:

                                           循环条件:2->3->4->5->null        4->5->null
                                          执行第一次循环                     执行第二次循环
head节点:     head:1->2->3->4->5->null     head:1->3->4->5->null           head:1->3->5->nul
第一个奇数节点: odd:1->2->3->4->5->null      odd:3->4->5->null               odd:{5->null}
第一个偶数节点: even: 2->3->4->5->null       evenDummy:2->4->5->null         evenDummy:2->4->null
                                          even:4->5->null                 even重新赋值:null
     */

    /**
     * lintCode:1292 · 奇偶链表
     * 给定单链表，将所有奇数节点连接在一起，然后将偶数节点连接在一起。 请注意，这里我们讨论的是节点编号，而不是节点中的值。
     */
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) {  //如果头节点没有指向下一个节点对象的引用 --直接返回。
            return head;
        }
        ListNode odd = head,     //定义一个odd变量刚开始指向head内存地址.

        //odd为奇数节点 even为偶数节点  head和odd都是引用类型，指向同一个内存地址。改odd相当于就是改head

        even = head.next;
        ListNode evenDummy = even;   //偶数节点

        while(even != null && even.next != null){      //如果第二个节点不等于null 且有指向下下一个节点的引用
            odd.next = even.next;      //奇数节点的后面是-偶尔节点的下一个    也就是1的后面是3   3的后面是5
            System.out.println("head:"+head);
            odd = odd.next;            //odd赋值为第三个节点
            System.out.println("odd:"+odd);

            even.next = even.next.next;  //第二个节点后面为第四个节点        四的后面是null
            System.out.println("head:"+head);

            even = even.next;         //把第四个节点赋值给even
            System.out.println("even:"+even);
        }
        odd.next = evenDummy;            //第五个节点的后面是2->4->null
        return head;
    }

    /**
     * lintCode:756 · 两数相乘
     * 给出两个链表形式表示的数字,写一个函数得到这两个链表相乘乘积。
     */
    public long multiplyLists1(ListNode l1, ListNode l2) {
        String str1 = "";
        String str2 = "";

        while(l1!=null){   //当节点的值不等于null
            str1+= String.valueOf(l1.val);
            l1=l1.next;
        }
        while(l2!=null){   //当节点的值不等于null
            str2+=String.valueOf(l2.val);
            l2=l2.next;
        }
        long i1 =Integer.parseInt(str1);
        long i2 = Integer.parseInt(str2);
        return i1*i2;
    }

    /**
     * lintCode:756 · 两数相乘
     * 给出两个链表形式表示的数字,写一个函数得到这两个链表相乘乘积。
     */
    public long multiplyLists2(ListNode l1, ListNode l2) {

        long num1 = 0;
        while(l1 != null) {    //第一次循环:               第二次循环:
            num1 *= 10;      //0                            60
            num1 += l1.val;  //6    num1=num1+p.val          67
            l1 = l1.next;    //下一个节点
        }
        l1 = l2;
        long num2 = 0;
        while(l1 != null) {
            num2 *= 10;
            num2 += l1.val;
            l1 = l1.next;
        }
        return num1 * num2;
    }

    public static void main(String[] args) {

        ListNode listNode5=new ListNode(5);
        ListNode listNode4=new ListNode(4,listNode5);
        ListNode listNode3=new ListNode(3,listNode4);

        ListNode listNode2=new ListNode(2,listNode3);

        ListNode listNode1=new ListNode(1,listNode2);
        System.out.println("链表:"+listNode1);
        ListNode reNode = new Sulotion().oddEvenList(listNode1);
        System.out.println("奇偶排序后链表:"+reNode);


        ListNode listNode7=new ListNode(7);
        ListNode listNode6=new ListNode(6,listNode7);

        ListNode listNode9=new ListNode(9);
        ListNode listNode8=new ListNode(8,listNode9);
        long multiplyLists1 = new Sulotion().multiplyLists1(listNode8, listNode6);//return 45*67
        System.out.println(multiplyLists1);
        long multiplyLists2 = new Sulotion().multiplyLists2(listNode8, listNode6);//return 45*67
        System.out.println(multiplyLists2);

    }
}

class ListNode {
     int val;           //存储的数据对象
     ListNode next;    //下一个节点对象的引用
    public ListNode(){}
    ListNode(int x) {
        val = x;
        next = null;
    }
    ListNode(int x, ListNode listNode) {
        val = x;
        next = listNode;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
