package binarySortTree;

public class BinarySortTreeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 7, 3, 10, 12, 5, 1, 9 };
		BinarySortTree b = new BinarySortTree();
		for (int i = 0; i < a.length; i++) {
			b.add(new Node(a[i]));
		}
		b.inorderTraversal();
		b.delete(7);
		b.delete(3);
		b.delete(12);
		b.delete(9);
		b.delete(5);
		b.delete(1);
		b.delete(10);
		System.out.println("==========");
		b.inorderTraversal();
		
	}

}

//二叉排序树
//中序遍历是有序的
class BinarySortTree {
	private Node root;
	private int size;

	public BinarySortTree() {
		super();
		this.root = null;
		this.size = 0;
	}

	public int size() {
		return size;
	}

	// 添加数据
	public void add(Node data) {
		size++;
		if (root == null) {
			root = data;
			return;
		}
		Node temp = root;
		while (true) {
			if (data.getValue() < temp.getValue()) {
				if (temp.left == null) {
					temp.left = data;
					break;
				} else {
					temp = temp.left;
				}
			} else {
				if (temp.right == null) {
					temp.right = data;
					break;
				} else {
					temp = temp.right;
				}
			}
		}
	}

	// 删除节点
	//思路：首先二叉排序树中的每一个节点都大于它的左子树中所有值，小于右子树中所有值。它位于中间。
	//		所以，删除一个节点，则可以把他左子树中最大值放到该删除节点处，如果没有左子树，则还可以把右子树中最小值放在该删除节点处，
	//		如果左删除节点的右右子树都为空，则是叶子节点，将父节点指向该节点的指向置为空，是根节点则根节点置为空。
	//注意：正确的删除节点后，剩下的数据任是二叉排序树
	//提示:交换最大（最小）值时，是交换的数据而不是数据的对象
	public void delete(int value) {
		if (root == null) {
			System.out.println("二叉排序树为空。");
			return;
		}
		Node node = searchNode(value);
		if (node == null) {
			System.out.println("删除的节点不存在。");
			return;
		}
		Node nodeParent = searchParentNode(value);
		//先查找删除节点的左子树最大值
		Node temp = searchLeftNodeMax(node);
		int m=0;
		//要删除节点左右子树都不为空，则将左子树中最大值放在删除位置
		if (temp != null) {
			///如果左子树不为空
			//记录左子树接节点中最大值，等下放到删除节点处
			m=temp.getValue();
			//左子树中最大值则转移到删除节点处
			//然后删除掉左子树中最大值
			delete(m);
			//把上面记录的左子树节点最大值放在删除节点处，删除的原节点则不存在
			node.setValue(m);
		} else if ((temp = searchRightNodeMin(node)) != null) {
			//左子树为空，右子树不为空，则查找删除节点右子树
			//记录右子树接节点中最大值，等下放到删除节点处
			m=temp.getValue();
			//删除右子树中最小值
			delete(m);
			//把上面记录的右子树最小值放在删除节点处
			node.setValue(m);
		} else {
			//否则，要删除的节点是叶子节点
			//判断是否是根节点
			if (nodeParent != null) {
				//不是，则让父节点的指向为空
				if (nodeParent.left == node) {
					nodeParent.left = null;
				} else {
					nodeParent.right = null;
				}
			}else {
				//是根节点，则让根节点为空
				root=null;
			}
		}
	}

	// 查找传入节点的右子树中的最小节点
	private Node searchRightNodeMin(Node node) {
		Node temp = node.right;
		while (temp != null) {
			if (temp.left == null) {
				return temp;
			}
			temp = temp.left;
		}
		return null;
	}

	// 查找传入节点的左子树中的最大节点
	private Node searchLeftNodeMax(Node node) {
		Node temp = node.left;
		while (temp != null) {
			if (temp.right == null) {
				return temp;
			}
			temp = temp.right;
		}
		return null;
	}

	private Node searchNode(int value) {
		return searchNode(root, value);
	}

	// 查找传入该值对应的节点
	private Node searchNode(Node node, int value) {
		if (node == null) {
			return null;
		}
		if (node.getValue() == value) {
			return node;
		}
		if (node.getValue() > value) {
			return searchNode(node.left, value);
		} else {
			return searchNode(node.right, value);
		}
	}

	// 查找传入该值对应节点的父节点
	private Node searchParentNode(int value) {
		return searchParentNode(root, value);
	}

	// 查找传入该值对应节点的父节点
	private Node searchParentNode(Node node, int value) {
		if (node == null) {
			return null;
		}
		if ((node.left!=null&&node.left.getValue() == value )|| (node.right!=null&&node.right.getValue() == value)) {
			return node;
		}
		if (node.getValue() > value) {
			return searchParentNode(node.left, value);
		} else {
			return searchParentNode(node.right, value);
		}
	}

	// 中序遍历二叉树
	public void inorderTraversal() {
		if (root != null) {
			inorderTraversal(root);
		} else {
			System.out.println("二叉排序树为空。");
		}
	}

	private void inorderTraversal(Node data) {
		if (data.left != null) {
			inorderTraversal(data.left);
		}
		System.out.println(data);
		if (data.right != null) {
			inorderTraversal(data.right);
		}
	}
}

class Node {
	private int value;
	public Node left;
	public Node right;

	public Node(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "[value=" + value + "]";
	}

}