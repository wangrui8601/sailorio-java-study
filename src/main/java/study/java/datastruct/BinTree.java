package study.java.datastruct;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/*
 * 树节点
 */
class TreeNode{
	char data; //树节点的值
	boolean flag; //是否被访问的标志位
	TreeNode lchild; //左子树的根节点
	TreeNode rchild; //右子树的根节点
	public TreeNode(char d){
		data = d;
		flag = false;
		lchild = null;
		rchild = null;
	}
	
	public String toString(){
		return String.valueOf(data);
	}
	
	public void visit(){
		System.out.printf("%s ", data);
	}
}

public class BinTree {
	private TreeNode root; //树的根节点
	
	
	public BinTree() throws IOException{
		
	}
	
	public void loadData() throws IOException{
		root = recursionPreOrderCreate();
	}
	
	//建立二叉树  
	//非递归（广义表），已知非空二叉树采用广义表形式作为输入，根据输入建立二叉树的二叉链表存储结构，例如A(B(D,E(G)),C(F(,H)))@  
	public BinTree(String line){
		int length = line.length();
		TreeNode p = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		char ch;
		//保存左右子树标志，左括号出现(置0）则表示将处理左子树， 逗号出现(置1)则表示要处理右子树。 
		int flag = 0;
		
		for(int index = 0; index < length; ++index){
			ch = line.charAt(index);
			switch(ch){
			case '(':
				flag = 0;
				stack.push(p);
				break;
			case ')'://表示当前子树处理完毕， 将子树的根节点出栈
				stack.pop();
				break;
			case ',':
				flag = 1;
				break;
			case '@':
				return;
			default://字母，作为二叉树的节点
				p = new TreeNode(ch);
				if(root == null){
					root = p;
				} else{
					if(flag == 0){
						stack.peek().lchild = p;
					} else{
						stack.peek().rchild = p;
					}
				}
				break;
			}
		}
	}
	
	//递归前序遍历创建二叉树
	public TreeNode recursionPreOrderCreate() throws IOException{
		DataInputStream in = new DataInputStream(System.in);
		char ch = (char) in.read();
		in.read();//还有一个回车符
		
		if(ch == ' '){
			return null;
		} else{
			TreeNode node = new TreeNode(ch);
			node.lchild = recursionPreOrderCreate();
			node.rchild = recursionPreOrderCreate();
			return node;
		}
	}
	
	//递归前序遍历
	public void recursionPreOrder(){
		recursionPreOrder(root);
	}
	
	private void recursionPreOrder(TreeNode node){
		if(node != null){
			node.visit();
			recursionPreOrder(node.lchild);
			recursionPreOrder(node.rchild);
		}
	}

	//递归中序遍历
	public void recursionInOrder(){
		recursionInOrder(root);
	}
	private void recursionInOrder(TreeNode node){
		if(node != null){
			recursionInOrder(node.lchild);
			node.visit();
			recursionInOrder(node.rchild);
		}
	}
	
	//递归后序遍历
	public void recursionPostOrder(){
		recursionPostOrder(root);
	}
	private void recursionPostOrder(TreeNode node){
		if(node != null){
			recursionPostOrder(node.lchild);
			recursionPostOrder(node.rchild);
			node.visit();
		}
	}
	
	//非递归前序遍历
	public void PreOrder(){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		while(!stack.isEmpty() || p != null){
			while(p != null){
				p.visit();
				stack.push(p);
				p = p.lchild;
			}
			
			p = stack.pop();
			p = p.rchild;
		}
	}
	
	//非递归中序遍历
	public void InOrder(){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		while(!stack.isEmpty() || p != null){
			while(p != null){
				stack.push(p);
				p = p.lchild;
			}
			
			p = stack.pop();
			p.visit();
			p = p.rchild;
		}
	}
	
	//非递归后序遍历
	public void PostOrder(){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		while(!stack.isEmpty() || p != null){
			while(p != null){
				stack.push(p);
				p = p.lchild;
			}
			
			p = stack.peek();
			if(!p.flag){
				p.flag = true;
				p = p.rchild;
			} else {
				p.visit();
				stack.pop();
				p = null;
			}
		}
	}
	
	//非递归层次遍历
	public void levelOrder(){
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode p = root;
		if(p != null)
			queue.add(p);
		while(!queue.isEmpty()){
			p = queue.remove();
			p.visit();
			if(p.lchild != null)
				queue.add(p.lchild);
			if(p.rchild != null)
				queue.add(p.rchild);
		}
		
	}
	
	//利用层次遍历交换左右节点
	public void exchangeChild(){
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode p = root;
		if(p != null){
			queue.add(p);
		}
		
		while(!queue.isEmpty()){
			p = queue.remove();
			TreeNode q = p.lchild;
			p.lchild = p.rchild;
			p.rchild = q;
			
			if(p.lchild != null)
				queue.add(p.lchild);
			if(p.rchild != null)
				queue.add(p.rchild);
		}
	}
	
	//后序遍历计算节点所在层次
	public int getNodeLevel(char data){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		
		while(!stack.isEmpty() || p != null){
			while(p != null){
				stack.push(p);
				p = p.lchild;
			}
			
			p = stack.peek();
			if(!p.flag){
				p.flag = true;
				p = p.rchild;
			}else{
				if(p.data == data){
					return stack.size();
				}
				stack.pop(); 
				p = null;
			}
			
		}
		return -1;
	}
	
	//递归前序遍历求二叉树的深度
	private int getDeep(TreeNode node){
		if(node == null)
			return 0;
		int lDeep = getDeep(node.lchild);
		int rDeep = getDeep(node.rchild);
		return (lDeep >= rDeep) ? 1 + lDeep : 1 + rDeep;
	}
	public int recursionGetDeep(){
		return getDeep(root);
	}
	
	//非递归中序遍历求二叉树的深度
	public int inOrderGetDeep(){
		Stack<TreeNode> stackNode = new Stack<TreeNode>();
		Stack<Integer> stackLevel = new Stack<Integer>();
		TreeNode p = root;
		int curLevel = 0;
		int maxLevel = 0;
		while(!stackNode.isEmpty() || p != null){
			while(p != null){
				stackNode.push(p);
				curLevel = curLevel + 1;
				stackLevel.push(curLevel);
				p = p.lchild;
			}
			
			p = stackNode.pop();
			curLevel = stackLevel.pop();
			if(curLevel > maxLevel){
				maxLevel = curLevel;
			}
			
			p = p.rchild;
		}
		
		return maxLevel;
	}
	
	//非递归后序遍历求二叉树的深度
	public int postOrderGetDeep(){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		int maxDeep = 0;
		
		while(!stack.isEmpty() || p != null){
			while(p != null){
				stack.push(p);
				p = p.lchild;
			}
			
			p = stack.peek();
			if(!p.flag){
				p.flag = true;
				p = p.rchild;
			} else{
				if(stack.size() > maxDeep){
					maxDeep = stack.size();
				}
				
				stack.pop();
				p = null;
			}
		}
		return maxDeep;
	}
	
	//前序遍历重置访问标志
	public void resetFlag(){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		while(!stack.isEmpty() || p != null){
			while(p != null){
				p.flag = false;
				stack.push(p);
				p = p.lchild;
			}
			
			p = stack.pop();
			p = p.rchild;
		}
	}
	//递归后序遍历销毁指定根节点的树
	private void destroy(TreeNode node){
		if(node != null){
			destroy(node.lchild);
			destroy(node.rchild);
			node = null;
		}
	}
	public void recursionDestroy(){
		destroy(root);
	}
	
	//非递归后序遍历销毁指定根节点的树
	public void postOrderDestroy(TreeNode node){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = node;
		while(!stack.isEmpty() || p != null){
			while(p != null){
				stack.push(p);
				p = p.lchild;
			}
			p = stack.peek();
			if(!p.flag){
				p.flag = true;
				p = p.rchild;
			} else{
				if(p.lchild != null){
					p.lchild = null;
				}
				if(p.rchild != null){
					p.rchild = null;
				}
				stack.pop();
				p = null;
			}
		}
	}
	
	public void postOrderDestroy(){
		postOrderDestroy(root);
		root = null;
	}
	
	//非递归前序遍历找出指定关键字的父节点（指定关键字节点非根节点）
	public TreeNode[] getParentNode(char key){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		TreeNode q = null;
		while(!stack.isEmpty() || p != null){
			while(p != null){
				if(key == p.data){
					return new TreeNode[]{q, p};
				}
				stack.push(p);
				q = p;
				p = p.lchild;
			}
			p = stack.pop();
			q = p;
			p = p.rchild;
		}
		return null;
	}
	
	//删除指定关键字的结点以及以此结点为根的二叉树 
	public void deleteNode(char key){
		if(root.data == key){
			postOrderDestroy();
		} else{
			TreeNode[] nodes = getParentNode(key);
			if(nodes != null){
				TreeNode parent = nodes[0];
				TreeNode node = nodes[1];
				if(parent.lchild == node){
					parent.lchild = null;
				} else{
					parent.rchild = null;
				}
				
				postOrderDestroy(node);
			}
		}
		
	}
	
	//前序递归复制二叉树
	public TreeNode recursionPreOrderCopy(TreeNode r){
		if(r == null){
			return null;
		} else{
			TreeNode p = new TreeNode(r.data);
			p.lchild = recursionPreOrderCopy(r.lchild);
			p.rchild = recursionPreOrderCopy(r.rchild);
			return p;
		}
	}
	
	public void copyFrom(BinTree tree){
		root = recursionPreOrderCopy(tree.root);
	}
	
	//递归判断两个二叉树结构是否相同
	public boolean similar(TreeNode node1, TreeNode node2){
		if(node1 == null && node2 == null)
			return true;
		if(node1 != null && node2 != null 
				&& similar(node1.lchild, node2.lchild) 
				&& similar(node1.rchild, node2.rchild)){
			return true;
		}
		return false;
	}
	
	public boolean similarTree(BinTree tree){
		return similar(this.root, tree.root);
	}
	
	//递归判断两个二叉树是否相等，即结构相同且对应节点的值也相同
	public boolean equal(TreeNode node1, TreeNode node2){
		if(node1 == null && node2 == null)
			return true;
		if(node1 != null && node2 != null 
				&& node1.data == node2.data
				&& equal(node1.lchild, node2.lchild) && equal(node1.rchild, node2.rchild))
			return true;
		return false;
	}
	
	public boolean equalTree(BinTree tree){
		return equal(this.root, tree.root);
	}
	
	//递归求二叉树的叶节点数
	public int recursionLeafNum(TreeNode node){
		if(node == null)
			return 0;
		else{
			if(node.lchild == null && node.rchild == null)
				return 1;
			return recursionLeafNum(node.lchild) + recursionLeafNum(node.rchild);
		}
	}
	
	public int recursionLeafNum(){
		return this.recursionLeafNum(root);
	}
	
	//非递归前序遍历求二叉树的叶子节点数
	public int leafNum(){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		int count = 0;
		while(!stack.isEmpty() || p != null){
			while(p != null){
				if(p.lchild == null && p.rchild == null)
					count++;
				stack.push(p);
				p = p.lchild;
			}
			p = stack.pop();
			p = p.rchild;
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException{
		BinTree tree = new BinTree("A(B(D,E(G)),C(F(,H)))@");
		System.out.println(tree.recursionLeafNum());
		System.out.println(tree.leafNum());
		/*BinTree tree = new BinTree();
		tree.loadData();
		BinTree tree1 = new BinTree();
		tree1.copyFrom(tree);
		if(tree1.similarTree(tree)){
			System.out.println("tree1.similarTree(tree)");
		}
		if(tree1.equalTree(tree)){
			System.out.println("tree1.equalTree(tree)");
		}
		
		TreeNode[] nodes = tree1.getParentNode('F');
		nodes[0].data = 'c';
		nodes[1].data = 'f';
		if(tree1.equalTree(tree)){
			System.out.println("tree1.equalTree(tree)");
		}*/
		tree.recursionPreOrder();
		System.out.println();
		/*tree1.recursionPreOrder();
		System.out.println();*/
		tree.PreOrder();
		System.out.println();
		tree.recursionInOrder();
		System.out.println();
		tree.InOrder();
		System.out.println();
		tree.recursionPostOrder();
		System.out.println();
		tree.PostOrder();
		System.out.println();
		tree.resetFlag();
		tree.levelOrder();
		System.out.println();
		tree.exchangeChild();
		tree.levelOrder();
		System.out.println();
		tree.InOrder();
		System.out.println();
		tree.exchangeChild();
		
		System.out.println(tree.getNodeLevel('H')); 
		tree.resetFlag();
		System.out.println(tree.recursionGetDeep());
		System.out.println(tree.inOrderGetDeep());
		System.out.println(tree.postOrderGetDeep());
		tree.resetFlag();
		TreeNode[] p = null;
		if((p = tree.getParentNode('B')) != null){
			p[0].visit();
			p[1].visit();
		}
		
		tree.deleteNode('B');
		tree.postOrderDestroy();
	}
}
