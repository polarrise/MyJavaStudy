package jinbiao.dataStruct_study.linkedlist.oneWay;//package cn.how2j.springcloud.shujujiegou.linkedlist.oneWay;
///**
// * @author Jinbiao
// * @date 2022/3/7
// * @apiNote
// */
//public class Test3 {
//
//    Node first;
//    Node last;
//    public Node genarateNode(int [] arr){
//        for(int i=0;i<arr.length;i++){
//            if(i==0){
//                Node curNode=new Node(arr[i]);   //  1
//                first=curNode;
//                last=curNode;
//            }else{
//                Node curNode=new Node(arr[i]);   //   2, 3, 4, 5
//                last.next=curNode;
//                last=curNode;
//            }
//
//        }
//        return first;
//    }
//
//
//    public Node reverseNode(){
//        Node tail = new Node();    //2->1
//        Node curNode;
//        while(first.next!=null){   //1,2,3,4,5
//            curNode=first;   //curNode指向当前节点1
//            tail=first.next;  //tail指针指向2节点
//            tail.next=curNode;  //形成2->1 链表
//            first=first.next;  //2节点
//        }
//        System.out.println(tail);
//        return tail;
//    }
//
//    public static void main(String[] args) {
//          int [] arr={1,2,3,4,5};
//        Test3 test3 = new Test3();
//        Node node = test3.genarateNode(arr);
//        System.out.println(node);
//
//        test3.reverseNode();
//    }
//}
