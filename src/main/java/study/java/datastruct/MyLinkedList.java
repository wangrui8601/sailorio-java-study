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
	
	//从字符串构建链表
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
	
	//非递归求链表的长度
	public int getLength1(){
		Node p = header;
		int length = 0;
		while(p != null){
			++length;
			p = p.next;
		}
		return length;
	}
	
	//递归求链表的长度
	public int recursionGetLength(Node p){
		if(p == null)
			return 0;
		return 1 + recursionGetLength(p.next);
	}
	
	public int getLength2(){
		return recursionGetLength(header);
	}
	
	//判断链表是否为空
	public boolean isEmpty(){
		return header==null;
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
	
	//查找指定元素所在的位置
	public Node find(char ch){
		Node p = header;
		while(p != null){
			if(p.data == ch){
				return p;
			}else{
				p = p.next;
			}
		}
		return null;
	}
	
	//在非空链表的第一个节点前插入指定元素
	public void insertElement1(char ch){
		Node p = new Node(ch);
		p.next = header;
		header = p;
	}
	
	//在非空链表的末尾插入指定元素
	public void insertElement2(char ch){
		Node p = new Node(ch);
		Node q = header;
		while(q.next != null){
			q = q.next;
		}
		q.next = p;
	}
	
	//在链表中的指定节点后插入一个元素
	public void insertElement3(Node q, char ch){
		Node p = new Node(ch);
		if(header == null){
			header = p;
		} else{
			p.next = q.next;
			q.next = p;
		}
	}
	
	//在链表的第i个元素后插入指定元素
	public void insertElement4(int i, char ch){
		int j = 0;
		Node p = header;
		while(p != null && j < i){
			p = p.next;
			++j;
		}
		if(p == null){
			System.err.println("out of range");
		}
		insertElement3(p, ch);
		
	}
	
	//在按值有序的链表中插入一个元素使之仍为有序
	public void insertElement5(char ch){
		Node r = null;
		Node q = header;
		Node p = new Node(ch);
		while(q != null && q.data < ch){
			r = q;
			q = q.next;
		}
		if(r != null){
			p.next = r.next;
			r.next = p;
		} else{
			p.next = header;
			header = p;
		}
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
		System.out.println(list.getLength1());
		System.out.println(list.getLength2());
		if(list.isEmpty()){
			System.out.println("list.isEmpty");
		}
		System.out.println(list.find('h'));
		System.out.println(list.find('b'));
		list.insertElement1('o');
		System.out.println(list);
		list.insertElement2('l');
		System.out.println(list);
		Node q = list.find('h');
		list.insertElement3(q, 'r');
		System.out.println(list);
		list.insertElement4(2, 'p');
		System.out.println(list);
		
		MyLinkedList list1 = new MyLinkedList("bcdf");
		list1.insertElement5('a');
		System.out.println(list1);
		list1.insertElement5('e');
		System.out.println(list1);
	}
}
