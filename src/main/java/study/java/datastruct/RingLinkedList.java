package study.java.datastruct;

/*
 * 判断链表是否有环
 * 求切入点
 * 环长
 * 甩尾长
 */
public class RingLinkedList {
	static class Node{
		int data;
		Node next;
		public Node(int data){
			this.data = data;
		}
		public String toString(){
			return String.valueOf(data);
		}
	}
	
	public static void main(String[] args){
		Node head = createRingLinkedList();
		Node node = isLoop(head);
		System.out.println(getRingLength(node));
		System.out.println(getTailLength(head, node));
	}

	//判断链表中是否存在环
	public static Node isLoop(Node head){
		Node slow = head;
		Node quick = head;
		while(quick != null && quick.next != null){
			slow = slow.next;
			quick = quick.next.next;
			if(slow == quick){
				return slow;
			}
		}
		return null;
	}
	
	//求环的长度
	public static int getRingLength(Node node){
		int length = 0;
		
		Node slow = node;
		Node quick = node;
		while(true){
			slow = slow.next;
			quick = quick.next.next;
			++length;
			if(slow == quick)
				break;
		}
		return length;
	}
	
	//求甩尾长度
	public static int getTailLength(Node head, Node node){
		int length = 0;
		Node p = head;
		Node q = node;
		while(p != q){
			p = p.next;
			q = q.next;
			++length;
		}
		System.out.println(q);
		return length;
		
	}
	
	public static Node createRingLinkedList(){
		Node head = new Node(0);
		Node p = head;
		for(int index = 1; index < 10; ++index){
			p.next = new Node(index);
			p = p.next;
		}
		p.next = head.next.next;
		
		return head;
	}
	
	public static void print(Node head){
		Node p = head;
		while(p != null){
			System.out.print(p.data + " ");
			p = p.next;
		}
		
	}

}
