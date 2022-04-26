package jinbiao.dataStruct_study.linkedlist.oneWay;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinbiao
 * @date 2021/12/8
 */
public class Sulotion2 {

    List<Integer> res = new ArrayList<>();

    /**
     * lintCode:822 · 相反的顺序存储
     * 给出一个链表，并将链表的值以倒序存储到数组中。
     * 您不能改变原始链表的结构。
     */
    public List<Integer> reverseStore() {

        ListNode listNode5=new ListNode(5);
        ListNode listNode4=new ListNode(4,listNode5);
        ListNode listNode3=new ListNode(3,listNode4);

        ListNode listNode2=new ListNode(2,listNode3);

        ListNode listNode1=new ListNode(1,listNode2);
        store(listNode1);    //传入一个链表1->2->3->4->5
        return res;
    }
    private void store(ListNode p) {   //递归,自己调用自己.
        if (p == null) {
            return;   //如果节点为null,一层一层返回,return：从被调用函数返回到主调函数中继续执行，并非一遇到return整个递归结束
        }

        store(p.next);       //递归,一直到第五个节点才停止递归,因为第五个节点的next为null,不满足递归ListNode类型的入参,
        res.add(p.val);     //res.add 5、4、3、2、1
    }

    /**
     * lintCode:219 · 在排序链表中插入一个节点
     * 1->4->6->8->null   插入5节点
     * 别人的参考答案:
     */
    public ListNode insertNode(ListNode head, int val) {
        ListNode newNode = new ListNode(val);
        // 添加最小临时节点
        ListNode temp = new ListNode(0);
        temp.next = head;     //temp:0->1->4->6->8->null
        // 根节点
        head = temp;

        //----------  head=head.next,下一个节点赋值给当前节点,并不会影响初始化链表tem的值----------
        while(head.next!=null&&head.next.val<=val){      //只有当head不为null并且当前节点的下一个节点的值小于传进来的值val
            head=head.next;
        }
        //----------在当前节点为第四个节点时结束循环----------
        newNode.next = head.next;   //一定要把5节点后面排6先排完,  如果把这一步放到下下方,会造成5节点后面为null值
        head.next=newNode;  //head为第4个节点，  4节点后面排5
        // 忽略临时节点
        return temp.next;
    }
    /**  自己理解后修改--
     * lintCode:219 · 在排序链表中插入一个节点
     * 1->4->6->8->null   插入5节点
     */
    public ListNode insertNode2(ListNode head, int val) {
        if(head ==null){
            return null;
        }
       ListNode temp=head;        //1->4->6->8
       ListNode listNode5=new ListNode(val);  //创建第五个节点
        while(temp.next!=null&&temp.next.val<=val){
            temp=temp.next;
        }
        //结束是temp为4节点
        listNode5.next=temp.next;
        temp.next=listNode5;
        System.out.println(head);
        return head;
    }



    public static void main(String[] args) {
        ListNode listNode8=new ListNode(8);
        ListNode listNode6=new ListNode(6,listNode8);

        ListNode listNode4=new ListNode(4,listNode6);

        ListNode listNode1=new ListNode(1,listNode4);
        List<Integer> list = new Sulotion2().reverseStore();   //倒序排序链表
        System.out.println(list);

//        ListNode listNode = new Sulotion2().insertNode(listNode1, 5);//参考答案:在排序链表中插入一个节点
//        System.out.println("insertNode:"+listNode);

        new Sulotion2().insertNode2(listNode1,5);  //自己修改:在排序链表中插入一个节点

    }
}



