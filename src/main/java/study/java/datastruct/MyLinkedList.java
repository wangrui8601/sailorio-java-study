package study.java.datastruct;

class Node{
	char data;
	Node next;
	public Node(char ch){
		data = ch;
		next = null;
	}
}
public class MyLinkedList {
	private Node header;
	
	public MyLinkedList(String line){
		Node p = null;
		for(int index = 0; index < line.length(); ++index){
			char ch = line.charAt(index);
			if(header == null){
				header = new Node(ch);
				p = header;
			} else{
				p.next = new Node(ch);
				p = p.next;
			}
		}
	}

	//非递归反转链表	1
	public void reverse1(){
		Node p = null;
		Node q = header;
		Node r = null;
		while(q != null){
			r = q.next;
			q.next = p;
			p = q;
			q = r;
		}
		header = p;
	}
	
	//非递归反转链表2
	public void reverse2(){
		if(header == null)
			return;
		Node p = header.next;
		header.next = null;
		Node q = null;
		while(p != null){
			q = p.next;
			p.next = header;
			header = p;
			p = q;
		}
	}
	
	//递归反转链表
	public Node recursionReverse(Node p){
		if(p == null){
			return null;
		}
		if(p.next == null){
			header = p;
			return p;
		} else{
			recursionReverse(p.next).next = p;
			p.next = null;
			return p;
		}
	}
	
	public void reverse3(){
		recursionReverse(header);
	}
	public String toString(){
		StringBuilder builder = new StringBuilder();
		Node p = header;
		while(p != null){
			builder.append(p.data);
			p = p.next;
		}
		return builder.toString();
	}
	
	
	public static void main(String[] args){
		MyLinkedList list = new MyLinkedList("hello world");
		System.out.println(list);
		list.reverse1();
		System.out.println(list);
		list.reverse2();
		System.out.println(list);
		list.reverse3();
		System.out.println(list);
	}
}
