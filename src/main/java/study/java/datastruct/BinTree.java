package study.java.datastruct;

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
	
	public void visit(){
		System.out.printf("%s ", data);
	}
}

public class BinTree {
	private TreeNode root; //树的根节点
	
	
	public BinTree(){
		root = new TreeNode('A');
		TreeNode nodeB = new TreeNode('B');
		TreeNode nodeC = new TreeNode('C');
		TreeNode nodeD = new TreeNode('D');
		TreeNode nodeE = new TreeNode('E');
		TreeNode nodeF = new TreeNode('F');
		TreeNode nodeG = new TreeNode('G');
		TreeNode nodeH = new TreeNode('H');
		
		root.lchild = nodeB;
		root.rchild = nodeC;
		
		nodeB.lchild = nodeD;
		nodeB.rchild = nodeE;
		
		nodeC.lchild = nodeF;
		
		nodeE.lchild = nodeG;
		
		nodeF.rchild = nodeH;
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
	
	public static void main(String[] args){
		BinTree tree = new BinTree();
		tree.recursionPreOrder();
		System.out.println();
		tree.PreOrder();
		System.out.println();
		tree.recursionInOrder();
		System.out.println();
		tree.InOrder();
		System.out.println();
		tree.recursionPostOrder();
		System.out.println();
		tree.PostOrder();

	}
}
