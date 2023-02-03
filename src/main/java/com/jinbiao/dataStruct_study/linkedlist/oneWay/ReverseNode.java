package com.jinbiao.dataStruct_study.linkedlist.oneWay;//package cn.how2j.springcloud.shujujiegou.linkedlist.oneWay;
/**
 * @author Jinbiao
 * @date 2022/3/7
 * @apiNote 翻转链表
 */
public class ReverseNode {

    Node head;
    Node tail;
    public Node genarateNode(int [] arr){
        for(int i=0;i<arr.length;i++){
            if(i==0){
                Node curNode = new Node(arr[i]);   //  1
                head = curNode;
                tail = curNode;
            }else{
                Node curNode = new Node(arr[i]);   //   2, 3, 4, 5
                tail.next = curNode;
                tail = curNode;
            }

        }
        return head;
    }

    //public Node reverseNode(Node head){
    //  //1,2,3,4,5
    //  if(head == null || head.next == null){
    //    return head;
    //  }
    //  Node ans = reverseNode(head.next);//递归调用
    //  head.next.next = head;//让当前节点的下一个节点的next指针指向当前节点
    //  head.next = null;  //同时让当前节点的next指针指向NULL, 从而实现从链表尾部开始的局部反转
    //  System.out.println(ans);
    //  return ans;
    //}

    public Node reverseNode(Node curNode){
      //1,2,3,4,5
      if(curNode.next == null){
        return curNode;
      }
      Node ans = reverseNode(curNode.next);//递归调用
      curNode.next.next = curNode;//让当前节点的下一个节点的next指针指向当前节点
      curNode.next = null;  //同时让当前节点的next指针指向NULL, 从而实现从链表尾部开始的局部反转
      System.out.println(ans);
      return ans;
    }

    public static void main(String[] args) {
        int [] arr={1,2,3,4,5};
        ReverseNode reverseNode = new ReverseNode();
        Node node = reverseNode.genarateNode(arr);
        System.out.println(node);

        reverseNode.reverseNode(node);
    }
}

class Node<T>{
  private T val;
  Node next;
  public Node(){

  }
  public Node(T val){
    this.val = val;
  }

  @Override
  public String toString() {
    return "Node{" +
        "val=" + val +
        ", next=" + next +
        '}';
  }
}
