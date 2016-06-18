package study.java.datastruct;

import java.util.LinkedList;
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
		
		//nodeE.lchild = nodeG;
		
		//nodeF.rchild = nodeH;
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
		/*tree.PostOrder();
		System.out.println();*/
		tree.levelOrder();
		System.out.println();
		/*tree.exchangeChild();
		tree.levelOrder();
		System.out.println();
		tree.InOrder();
		System.out.println();
		*/
		//System.out.println(tree.getNodeLevel('H')); 
		System.out.println(tree.recursionGetDeep());
		System.out.println(tree.inOrderGetDeep());
		System.out.println(tree.postOrderGetDeep());
	}
}
