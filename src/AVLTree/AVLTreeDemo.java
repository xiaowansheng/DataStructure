package AVLTree;

public class AVLTreeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 4, 3, 6, 5, 7, 8 ,12,13,14,56,47,89,52,18,99,120,130,145,264};
		// int[] a = { 10, 12, 8, 9, 7, 6 };
//		/*
//		 *          4
//		 *      3       6
//		 *            5    7
//		 *                    8
//		 *    
//		 */
		AVLTree av = new AVLTree();
		for (int i = 0; i < a.length; i++) {
			av.add(new Node(a[i]));
		}
		av.inorderTraversal();
		int h = av.height(av.getRoot());
		System.out.println(h);
		System.out.println(av.height(av.getRoot().right));
		System.out.println(av.height(av.getRoot().left));
		av.inorderTraversal();
	}

}

//二叉排序树
//中序遍历是有序的
class AVLTree {
	private Node root;

	public AVLTree() {
		super();
		this.root = null;
	}

	public Node getRoot() {
		return root;
	}

	// 右旋转
	/**
	 * 以node节点为根，右旋转
	 * 
	 * @param node 需要右旋转的节点
	 */
	private void rightRotation(Node node) {
		// 步骤说明和左旋转类似
		Node newNode = new Node(node.getValue());
		Node temp = node.left.right;
		newNode.right = node.right;
		node.setValue(node.left.getValue());
		node.left = node.left.left;
		node.right = newNode;
		newNode.left = temp;
	}

	// 左旋转
	/**
	 * 以node节点为跟，左旋转
	 * 
	 * @param node 需要旋转的根节点
	 */
	private void leftRotation(Node node) {
		Node newNode = new Node(node.getValue());// 记录node节点的数据
		Node temp = node.right.left;// 记录node节点的右节点的左节点
		newNode.left = node.left;// 新node节点的左节点是原node节点的左节点
		node.setValue(node.right.getValue());// node节点数据变为node的右节点的数据
		node.right = node.right.right;// node节点指向node右节点的右节点
		node.left = newNode;// 改完数据的node节点的左节点指向新node节点
		newNode.right = temp;// 新node节点的右节点指向记录的temp
	}

	/**
	 * 以该节点为根节点的树的高度
	 * 
	 * @param node 计算该节点的高度
	 * @return 返回高度值
	 */
	public int height(Node node) {
		if (node == null) {
			return 0;
		}
		return Math.max(node.left == null ? 0 : height(node.left), node.right == null ? 0 : height(node.right)) + 1;
	}

	// 重载方便调用递归
	public void add2(Node data) {
		if (root == null) {
			root = data;
			return;
		} else {
			add2(root, data);
		}
	}

	// 递归添加数据并判断平衡
	private void add2(Node temp, Node data) {
		if (data.getValue() < temp.getValue()) {
			if (temp.left == null) {
				temp.left = data;
			} else {
				add2(temp.left, data);
			}
		} else {
			if (temp.right == null) {
				temp.right = data;
			} else {
				add2(temp.right, data);
			}
		}
		// 每次添加节点后，都会从添加的节点层往上层层判断是否满足平衡二叉树
		// （递归结束，每个递归的add方法弹栈前都会执行检查是否满足平衡二叉树再弹栈，直到最后，弹到根节点检查到方法完全结束）
		// 左子树高度-右子树高度>=2，不平衡
		if (height(temp.left) - height(temp.right) > 1) {
			// 再判断不平衡的结构是在左子树的左子树，还是左子树的右子树。
			if (height(temp.left.left) - height(temp.left.right) > 0) {
				// 在左子树的右子树上，满足LR，先左旋转，再右旋转
				leftRotation(temp.left);
				rightRotation(temp);
			} else {
				// 在左子树的左子树上，满足LL，直接右旋转
				rightRotation(temp);
			}
		}
		// 要么，右子树高度-左子树高度>=2，不平衡
		else if (height(temp.right) - height(temp.left) > 1) {
			// 再判断不平衡的结构是在右子树的左子树，还是右子树的右子树。
			if (height(temp.right.left) - height(temp.right.right) > 0) {
				// 是在右子树的左子树上，满足RL，先左旋转，再右旋转
				rightRotation(temp.right);
				leftRotation(temp);
			} else {
				// 在右子树的右子树上，满足RR，直接左旋转
				leftRotation(temp);
			}
		}
	}

	// 循环添加数据
	public void add(Node data) {
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
		// 添加数据后，根据数据的值，从根节点往下，直到添加数据的位置，不断判断这条路径上的每个子树是否满足平衡二叉树
		//找到不平衡的子树，调整后，退出
		//如果没有不平衡的，则最后temp为空
		temp=root;
		while (temp!=null) {
			if (height(temp.left) - height(temp.right) > 1) {
				// 再判断不平衡的结构是在左子树的左子树，还是左子树的右子树。
				if (height(temp.left.left) - height(temp.left.right) > 0) {
					// 在左子树的右子树上，满足LR，先左旋转，再右旋转
					leftRotation(temp.left);
					rightRotation(temp);
				} else {
					// 在左子树的左子树上，满足LL，直接右旋转
					rightRotation(temp);
				}
				break;
			}
			// 要么，右子树高度-左子树高度>=2，不平衡
			else if (height(temp.right) - height(temp.left) > 1) {
				// 再判断不平衡的结构是在右子树的左子树，还是右子树的右子树。
				if (height(temp.right.left) - height(temp.right.right) > 0) {
					// 是在右子树的左子树上，满足RL，先左旋转，再右旋转
					rightRotation(temp.right);
					leftRotation(temp);
				} else {
					// 在右子树的右子树上，满足RR，直接左旋转
					leftRotation(temp);
				}
				break;
			}
			if(data.getValue()>=temp.getValue()) {
				temp=temp.right;
			}else {
				temp=temp.left;
			}
		}

	}

	// 删除节点
	// 思路：首先二叉排序树中的每一个节点都大于它的左子树中所有值，小于右子树中所有值。它位于中间。
	// 所以，删除一个节点，则可以把他左子树中最大值放到该删除节点处，如果没有左子树，则还可以把右子树中最小值放在该删除节点处，
	// 如果左删除节点的右右子树都为空，则是叶子节点，将父节点指向该节点的指向置为空，是根节点则根节点置为空。
	// 注意：正确的删除节点后，剩下的数据任是二叉排序树
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
		// 先查找删除节点的左子树最大值
		Node temp = searchLeftNodeMax(node);
		int m = 0;
		// 要删除节点左右子树都不为空，则将左子树中最大值放在删除位置
		if (temp != null) {
			/// 如果左子树不为空
			// 记录左子树接节点中最大值，等下放到删除节点处
			m = temp.getValue();
			// 左子树中最大值则转移到删除节点处
			// 然后删除掉左子树中最大值
			delete(m);
			// 把上面记录的左子树节点最大值放在删除节点处，删除的原节点则不存在
			node.setValue(m);
		} else if ((temp = searchRightNodeMin(node)) != null) {
			// 左子树为空，右子树不为空，则查找删除节点右子树
			// 记录右子树接节点中最大值，等下放到删除节点处
			m = temp.getValue();
			// 删除右子树中最小值
			delete(m);
			// 把上面记录的右子树最小值放在删除节点处
			node.setValue(m);
		} else {
			// 否则，要删除的节点是叶子节点
			// 判断是否是根节点
			if (nodeParent != null) {
				// 不是，则让父节点的指向为空
				if (nodeParent.left == node) {
					nodeParent.left = null;
				} else {
					nodeParent.right = null;
				}
			} else {
				// 是根节点，则让根节点为空
				root = null;
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
		if ((node.left != null && node.left.getValue() == value)
				|| (node.right != null && node.right.getValue() == value)) {
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