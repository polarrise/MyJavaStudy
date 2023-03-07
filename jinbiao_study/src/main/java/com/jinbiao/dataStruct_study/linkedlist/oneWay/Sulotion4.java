package com.jinbiao.dataStruct_study.linkedlist.oneWay;

/**
 * @author com.jinbiao
 * @date 2021/12/8
 * @apiNote
 */
public class Sulotion4 {

    /**
     * lintCode:165 · 合并两个排序链表
     * 描述:将两个排序（升序）链表合并为一个新的升序排序链表
     * 样例 1:
     * 	输入: list1 = null, list2 = 0->3->3->null
     * 	输出: 0->3->3->null
     * 样例2:
     * 	输入:  list1 =  1->3->8->11->15->null, list2 = 2->null
     * 	输出: 1->2->3->8->11->15->null
     * 	注意tail指针变化即可:-1节点,1节点,2节点, 最后2.next=l1
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        //基本思想：将两条链表的节点按从小到大的顺序用一根线依次串起来，这根线就是新建一个dummy node 对应的 tail 指针，即 tail.next 不断指向两条链表中更小的一个节点，将所有节点串起来即可！
        ListNode dummy=new ListNode(-1);   //要构造的排序后链表
        ListNode tail=dummy;
        while(l1!=null &&l2!=null){
           if(l1.val<l2.val){          //让tail指针指向两条链表中的最小节点
               tail.next=l1;          //-1->1
               l1=l1.next;           //l1:3节点
           }else{
               tail.next=l2;          //第二次循环 走else,
               l2 = l2.next;
           }
           tail=tail.next;
        }
        //--------执行完第一次循环,dummy还是-1 ->1 ,l1是3节点, l2还是2节点
        if(l1!=null){
            tail.next=l1;    //-1->1
        }
        if(l2!=null){   //l2为null,
           tail.next=l2;
        }
       return dummy.next;
    }

    /**
     * 自己所写,只实现了插入一个节点的效果
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);
        head.next=l1;               //head: 0->1->3->8->11->15->null, l1:1->3->8->11->15->null
        ListNode cur=head;         //cur: 0->1->3->8->11->15->null,
        while(cur.next!=null&&cur.next.val<=l2.val){
            cur=cur.next;
        }
        //----在1节点跳出循环---
        l2.next=cur.next;   //2节点后面指向3节点
        cur.next=l2;       //1节点后面指向2节点
        return head.next;
    }

    public static void main(String[] args) {

        ListNode listNode15=new ListNode(15);
        ListNode listNode11=new ListNode(11,listNode15);
        ListNode listNode8=new ListNode(8,listNode11);

        ListNode listNode3=new ListNode(3,listNode8);

        ListNode listNode1=new ListNode(1,listNode3);

        ListNode listNode2=new ListNode(2);
        ListNode reNode = new Sulotion4().mergeTwoLists1(listNode1, listNode2);

        //ListNode listNode22=new ListNode(3);
        //ListNode listNode21=new ListNode(3,listNode22);
        //ListNode listNode20=new ListNode(0,listNode21);
        //ListNode reNode = new Sulotion4().mergeTwoLists(null, listNode2);
        System.out.println(reNode);
    }
}
