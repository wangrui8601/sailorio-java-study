package study.java.datastruct;
/*
 * 将使用链表表示的整数相加返回链表表示的的整数
 * 如：         9->4->8
 *    +     7->2
 *   =1->0->2->0
 *   
 *   
 * 方法：首先将相加的两个链表逆置，分别得到
 *       8->4->9
 *       2->7
 * 将上述逆转后的链表相加，即从头节点开始遍历，并需要记录进位信息，与下一个节点值进行相加
 */
class MyNode{
	int data;
	MyNode next;
	public MyNode(int data){
		this.data = data;
		this.next = null;
	}
}
public class LinkedDataSum {
	public static void main(String[] args){
		MyNode arg1 = getLinkedList("998");
		MyNode arg2 = getLinkedList("7");
		print(arg1);
		System.out.println();
		print(arg2);
		System.out.println();
		arg1 = reverse(arg1);
		arg2 = reverse(arg2);
		print(arg1);
		System.out.println();
		print(arg2);
		MyNode arg3 = getSum(arg1, arg2);
		System.out.println();
		arg3 = reverse(arg3);
		print(arg3);
	}
	
	//将链表相加并生成新链表
	public static MyNode getSum(MyNode arg1, MyNode arg2){
		//保存返回链表的信息
		MyNode head = null;
		MyNode r = null;
		
		//两个相加链表的信息
		MyNode p = arg1;
		MyNode q = arg2;
		//进位信息
		int flag = 0;
		while(p != null || q != null){
			int val1 = 0;
			if(p != null){
				val1 = p.data;
				p = p.next;
			}
			
			int val2 = 0;
			if(q != null){
				val2 = q.data;
				q = q.next;
			}
			
			int sum = flag + val1 + val2;
			flag = sum / 10;
			MyNode node = new MyNode(sum % 10);
			if(head == null){
				head = node;
				r = head;
			} else{
				r.next = node;
				r = r.next;
			}
		}
		if(flag != 0){
			r.next = new MyNode(flag);
			r = r.next;
		}
		/*while(p != null && q != null){
			int sum = p.data + q.data + flag;
			flag = sum / 10;
			MyNode node = new MyNode(sum % 10);
			
			if(head == null){
				head = node;
				r = head;
			} else{
				r.next = node;
				r = r.next;
			}
			
			p = p.next;
			q = q.next;
		}
		
		if(p == null && q == null){
			if(flag != 0){
				MyNode node = new MyNode(flag);
				r.next = node;
				r = r.next;
			}
		} else{
			if(q != null){
				p = q;
			}
			while(p != null){
				int sum = p.data + flag;
				flag = sum / 10;
				MyNode node = new MyNode(sum % 10);
				r.next = node;
				r = r.next;
				
				p = p.next;
			}
			if(flag != 0){
				r.next = new MyNode(flag);
				r = r.next;
			}
		}*/
		return head;
	}
	//按字符串生成链表
	public static MyNode getLinkedList(String arg){
		MyNode head = null;
		MyNode p = null;
		
		for(int index = 0; index < arg.length(); ++index){
			char ch = arg.charAt(index);
			int data = ch - '0';
			
			if(head == null){
				head = new MyNode(data);
				p = head;
			} else{
				p.next = new MyNode(data);
				p = p.next;
			}
				
		}
		return head;
	}
	
	//链表逆置
	public static MyNode reverse(MyNode arg){
		MyNode q = arg;
		MyNode p = null;
		MyNode r = null;
		while(q != null){
			r = q.next;
			q.next = p;
			p = q;
			q = r;
		}
		return p;
	}
	
	//打印链表
	public static void print(MyNode arg){
		MyNode p = arg;
		while(p != null){
			System.out.print(p.data + " ");
			p = p.next;
		}
	}
}
