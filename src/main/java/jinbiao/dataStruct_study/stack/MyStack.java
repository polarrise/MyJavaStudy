package jinbiao.dataStruct_study.stack;

/**
 * @author jinbiao
 * @date 2021/12/22
 * @apiNote
 */
public class MyStack {
    public class Node{
        public int value;
        public Node next;

        public Node(int item){
            value = item;
            next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }
    private Node first;
    private Node last;

    public MyStack() {
    }

    /**
     *push(val) 将 val 压入栈
     */
    public void push(int number) {
        Node newNode=new Node(number);
        if(first==null){
            first=newNode;
            last=newNode;
        }else{
            newNode.next=first;
            first=newNode;
        }
        System.out.println(first);
    }
    /**
     *pop() 将栈顶元素弹出, 并返回这个弹出的元素
     */
    public int pop() {
        Node tail=first;
        if(first.next==null){  //只有一个节点的情况
             first=null;
             last=null;
        }else {
            first = first.next;
        }
        System.out.println(tail.value);
        return tail.value;
    }

    /**
     *min() 返回栈中元素的最小值
     */
    public int min() {
        int min = 0;
        Node tail1=first;
        Node tail2 =first;
        if(tail1.next==null){
            min=first.value;
            System.out.println("min:"+min);
            return min;
        }
        while (tail1!=null&&tail2!=null){  //tail1:{3->1->2},  tail2:{1->3->2}
            if(tail1.value-tail2.value<=0){  //第一个节点的值大于第二个节点的值        //{3,2}  {3,2}
               min=tail1.value;   //1
               tail2=tail2.next;
            }else{
               min=tail2.value;
               tail1=tail1.next;
            }
        }

        System.out.println("min:"+min);
        return min;
    }

    public static void main(String[] args) {
        MyStack minStack=new MyStack();
        //minStack.push(1);
        //minStack.min();
        //minStack.push(2);
        //minStack.min();
        //minStack.push(3);
        //minStack.min();
        //minStack.pop();
        //System.out.println(minStack.first);
        minStack.push(1);  // first:1. last:1
        minStack.pop();            //输出:1
        minStack.push(2);  //2
        minStack.push(3);  //3,2
        minStack.min();           //输出:2
        minStack.push(1);  //1,3,2
        minStack.min();          //输出:1
    }
}
