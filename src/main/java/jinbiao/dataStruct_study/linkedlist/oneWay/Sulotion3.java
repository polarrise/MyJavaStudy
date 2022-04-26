package jinbiao.dataStruct_study.linkedlist.oneWay;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinbiao
 * @date 2021/12/8
 * @apiNote
 */
public class Sulotion3 {

    /**
     * lintCode:489
     * 将一个数组变成链表
     */
    public ListNode toLinkedList(List<Integer> list) {
        ListNode head=new ListNode(-1);     //创建-1节点
        ListNode prev=head;
        for (int i=0;i<list.size();i++){
            ListNode cur=new ListNode(list.get(i));  //记录当前节点
            prev.next=cur;               //-1节点指向1,1指向2
            prev=cur;            //1节点赋值给-1， 2节点赋值给1,3节点赋值给2,4节点赋值给3
        }
        return head.next;
    }

    /**
     * 483 · 链表转数组
     * 链表转数组
     */
    public List<Integer> toArrayList(ListNode head) {   //1->2->3->null
        List<Integer> list=new ArrayList<>();
        while(head!=null &&Integer.valueOf(head.val)!=null){   //当整型值不等于空
            list.add(head.val);
            head=head.next;
        }

        return list;
    }
    public static void main(String[] args) {
        List<Integer>list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        ListNode listNode = new Sulotion3().toLinkedList(list);
        System.out.println(listNode);

        ListNode listNode4=new ListNode(4,null);
        ListNode listNode3=new ListNode(3,listNode4);

        ListNode listNode2=new ListNode(2,listNode3);

        ListNode listNode1=new ListNode(1,listNode2);
        List<Integer> integerList = new Sulotion3().toArrayList(listNode1);
        System.out.println(integerList);
    }
}
