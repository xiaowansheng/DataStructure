package tree;

public class ThreadedBinaryTreeDemo {

	public static void main(String[] args) {
		/*
		 * 测试二叉树 1 2 3 4 5 6
		 */
		StudentNode3 s1 = new StudentNode3(1, "xiao1");
		StudentNode3 s2 = new StudentNode3(2, "xiao2");
		StudentNode3 s3 = new StudentNode3(3, "xiao3");
		StudentNode3 s4 = new StudentNode3(4, "xiao4");
		StudentNode3 s5 = new StudentNode3(5, "xiao5");
		StudentNode3 s6 = new StudentNode3(6, "xiao6");
		StudentThreadedBinaryTree t = new StudentThreadedBinaryTree(s1);
		s1.left = s2;
		s2.left = s4;
		s2.right = s5;
		s1.right = s3;
		s3.left = s6;
//		/*
//		 * 二叉树模型
//		 *        1 
//		 *     2     3
//		 *   4   5  6
//		 */
		// t.preorderTraversal();// 124536
		// t.inorderTraversal();// 425163
		t.postorderTraversal();// 452361
		// 线索化二叉树
//		System.out.println("前序线索化二叉树后：");
//		t.preorderThreadedBinaryTree();
		// 用某个节点检验线索化效果
		System.out.println("s4的前驱节点：" + s4.left + ";后继节点：" + s4.right);// 前(2,5)
		System.out.println("s5的前驱节点：" + s5.left + ";后继节点：" + s5.right);// 前(4,3)
		System.out.println("s3的前驱节点：" + s3.left + ";后继节点：" + s3.right);// 前(6,6)

		// System.out.println("中序线索化二叉树后：");
		// t.threadedBinaryTree();
		// 用某个节点检验线索化效果
//		System.out.println("s5的前驱节点：" + s5.left+";后继节点：" + s5.right);//中(2,1)
//		System.out.println("s4的前驱节点：" + s4.left+";后继节点：" + s4.right);//中(null,2)
//		System.out.println("s3的前驱节点：" + s3.left+";后继节点：" + s3.right);//中(6,null)
		// System.out.println(s1.getLeftType());
		System.out.println("后序线索化二叉树后：");
		t.postorderThreadedBinaryTree();
		;
		System.out.println("s6的后继节点：" + s6.right);// s3
		// System.out.println("线索化中序遍历：");
		// t.inorderThreadedTraversal();//425163
//		System.out.println("线索化前序遍历：");
//		t.preorderThreadedTraversal();
		System.out.println("线索化后序遍历：");
		t.postorderTheardedTraversal();// 452631
	}

}

//线索化二叉树
class StudentThreadedBinaryTree {
	private StudentNode3 root;// 根节点
	private StudentNode3 pre;// 线索化时，当前节点的上一个节点

	public StudentThreadedBinaryTree(StudentNode3 root) {
		super();
		this.root = root;
	}

	// 中序线索化二叉树
	public void threadedBinaryTree() {
		if (root != null) {
			threadedBinaryTree(root);
		} else {
			System.out.println("二叉树中没有数据。");
		}
	}

	// 中序线索化二叉树
	private void threadedBinaryTree(StudentNode3 student) {
		if (student.left != null) {
			threadedBinaryTree(student.left);
		}
		// 如果前驱节点不为空，当前节点左节点为空
		// 则将左节点指向前驱节点
		if (student.left == null) {
			student.left = pre;
			student.setLeftType(1);
		}
		if (pre != null && pre.right == null) {
			pre.right = student;
			pre.setRightType(1);
		}
		pre = student;
		if (student.right != null) {
			threadedBinaryTree(student.right);
		}
	}

	// 中序遍历线索化二叉树
	public void inorderThreadedTraversal() {
		StudentNode3 temp = root;
		while (temp != null) {
			while (temp.getLeftType() != 1) {
				temp = temp.left;
			}
			// 输出左节点
			System.out.println("==>" + temp);
			// 右节点线索化值为1，则下一个节点是后继节点
			while (temp.getRightType() == 1) {
				temp = temp.right;
				System.out.println("==>" + temp);
			}
			temp = temp.right;
		}
	}

	// 前序线索化二叉树
	public void preorderThreadedBinaryTree() {
		if (root != null) {
			preorderThreadedBinaryTree(root);
		} else {
			System.out.println("二叉树为空。");
		}
	}

	// 前序线索化二叉树
	private void preorderThreadedBinaryTree(StudentNode3 student) {
		if (student == null) {
			return;
		}
		if (student.left == null) {
			student.left = pre;
			student.setLeftType(1);
		}
		if (pre != null && pre.right == null) {
			pre.right = student;
			pre.setRightType(1);
		}
		pre = student;
		if (student.getLeftType() != 1) {
			// 遍历下一个节点
			preorderThreadedBinaryTree(student.left);
		}
		if (student.getRightType() != 1) {
			preorderThreadedBinaryTree(student.right);
		}
	}

	// 前序线索化遍历
	public void preorderThreadedTraversal() {
		StudentNode3 temp = root;
		while (temp != null) {
			System.out.println(temp);
			while (temp.left != null && temp.getLeftType() != 1) {
				temp = temp.left;
				System.out.println(temp);
			}
			while (temp.getRightType() == 1) {
				temp = temp.right;
				System.out.println(temp);
			}
			temp = temp.right;
		}
	}

	// 后序线索化二叉树
	public void postorderThreadedBinaryTree() {
		if (root != null) {
			postorderThreadedBinaryTree(root);
		} else {
			System.out.println("没有数据。");
		}
	}

	// 后序线索化二叉树
	public void postorderThreadedBinaryTree(StudentNode3 student) {
		if (student.left != null) {
			postorderThreadedBinaryTree(student.left);
		}
		if (student.right != null) {
			postorderThreadedBinaryTree(student.right);
		}
		if (pre != null) {
			student.left = pre;
			student.setLeftType(1);
		}
		if (pre != null && pre.right == null) {
			pre.right = student;
		}
		pre = student;
	}

	// 后序线索化遍历二叉树
	public void postorderTheardedTraversal() {
		if (root != null) {
			postorderTheardedTraversal(root);
		} else {
			System.out.println("二叉树中没有数据。");
		}
	}

	// 后序线索化遍历二叉树
	private void postorderTheardedTraversal(StudentNode3 student) {
		if (student == null) {
			return;
		}
		if (student.left != null) {
			postorderTheardedTraversal(student.left);
		}
		while (student.getRightType() == 1) {
			student = student.right;
			System.out.println(student);
		}
		System.out.println(student);
	}

	/*
	 * // 添加学生信息 public void add(StudentNode3 student) { StudentNode3 stu = new
	 * StudentNode3(student.getId(), student.getName()); if (root != null) {
	 * StudentNode3 temp = root; while (temp != null) { // 假设id唯一 if (temp.getId() >
	 * stu.getId()) { if (temp.left != null) { temp = temp.left; } else { temp.left
	 * = stu; return; } } else { if (temp.right != null) { temp = temp.right; } else
	 * { temp.right = stu; return; } } } } else { root = stu; } }
	 */

	// 前序遍历二叉树
	public void preorderTraversal() {
		if (root != null) {
			root.preorder(root);
		} else {
			System.out.println("学生信息二叉树为空。");
		}
	}

	// 中序遍历二叉树
	public void inorderTraversal() {
		if (root != null) {
			root.inorder(root);
		} else {
			System.out.println("学生信息二叉树为空。");
		}
	}

	// 后续遍历
	public void postorderTraversal() {
		if (root != null) {
			root.postorder(root);
			;
		} else {
			System.out.println("学生信息二叉树为空。");
		}
	}

	// 前序遍历查找
	public StudentNode3 preorderSearch(int id) {
		if (root != null) {
			return root.preorderSearch(id);
		} else {
			System.out.println("学生信息二叉树中没有信息。");
			return null;
		}
	}

	// 中序遍历查找
	public StudentNode3 inorderSearch(int id) {
		if (root != null) {
			return root.inorderSearch(id);
		} else {
			System.out.println("学生信息二叉树中没有信息。");
			return null;
		}
	}

	// 后序遍历查找
	public StudentNode3 postorderSearch(int id) {
		if (root != null) {
			return root.postorderSearch(id);
		} else {
			System.out.println("学生信息二叉树中没有信息。");
			return null;
		}
	}

	// 递归删除节点
	// 连同该节点子节点一起删除
	public void delete(int id) {
		if (root != null) {
			if (root.getId() != id) {
				root.delete(id);
			} else {
				root = null;
			}

		} else {
			System.out.println("学生信息二叉树没有信息。");
		}
	}

}

//二叉树数据节点
class StudentNode3 {
	private int id;// 学生id，默认唯一
	private String name;// 学生姓名
	public StudentNode3 left;// 左节点
	public StudentNode3 right;// 右节点
	// 0：普通节点
	// 1：线索化节点
	private int leftType;// 当前节点左节点的类型
	private int rightType;// 当前节点右节点的类型

	public StudentNode3(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		leftType = 0;
		rightType = 0;
	}

	public int getLeftType() {
		return leftType;
	}

	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}

	public int getRightType() {
		return rightType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + "]";
	}

	// 后序遍历
	// 递归
	public void postorder(StudentNode3 student) {
		if (student.left != null) {
			postorder(student.left);
		}
		if (student.right != null) {
			postorder(student.right);
		}
		System.out.println("==>" + student);
	}

	// 中序遍历
	// 递归
	public void inorder(StudentNode3 student) {
		if (student.left != null) {
			inorder(student.left);
		}
		System.out.println("==>" + student);
		if (student.right != null) {
			inorder(student.right);
		}
	}

	// 前序遍历
	// 递归
	public void preorder(StudentNode3 student) {
		System.out.println("==>" + student);
		if (student.left != null) {
			preorder(student.left);
		}
		if (student.right != null) {
			preorder(student.right);
		}
	}

	// 前序遍历查找
	public StudentNode3 preorderSearch(int id) {
		System.out.println("前序遍历查找···");
		if (this.id == id) {
			return this;
		}
		StudentNode3 stu = null;
		if (this.left != null) {
			stu = this.left.preorderSearch(id);
		}
		if (stu != null) {
			return stu;
		}
		if (this.right != null) {
			stu = this.right.preorderSearch(id);
		}
		return stu;
	}

	// 中序遍历查找
	public StudentNode3 inorderSearch(int id) {
		StudentNode3 stu = null;
		if (this.left != null) {
			stu = this.left.inorderSearch(id);
		}
		if (stu != null) {
			return stu;
		}
		System.out.println("中序遍历查找···");
		if (this.id == id) {
			return this;
		}
		if (this.right != null) {
			stu = this.right.inorderSearch(id);
		}
		return stu;
	}

	// 后序遍历查找
	public StudentNode3 postorderSearch(int id) {
		StudentNode3 stu = null;
		if (this.left != null) {
			stu = this.left.postorderSearch(id);
		}
		if (stu != null) {
			return stu;
		}
		if (this.right != null) {
			stu = this.right.postorderSearch(id);
		}
		if (stu != null) {
			return stu;
		}
		System.out.println("后序遍历查找···");
		if (this.id == id) {
			return this;
		}
		return stu;
	}

	// 删除学生信息
	// 要求：删除节点，若该节点有子节点也一并删除
	public void delete(int id) {
		if (this.left != null && this.left.id == id) {
			this.left = null;
			return;
		}
		if (this.right != null && this.right.id == id) {
			this.right = null;
			return;
		}
		if (this.left != null) {
			this.left.delete(id);
		}
		if (this.right != null) {
			this.right.delete(id);
		}
	}
}