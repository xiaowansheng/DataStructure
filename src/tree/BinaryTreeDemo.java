package tree;

public class BinaryTreeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentNode s1 = new StudentNode(1, "xiao1");
		StudentNode s2 = new StudentNode(2, "xiao2");
		StudentNode s3 = new StudentNode(3, "xiao3");
		StudentNode s4 = new StudentNode(4, "xiao4");
		StudentNode s5 = new StudentNode(5, "xiao5");
		StudentNode s6 = new StudentNode(6, "xiao6");
		StudentBinaryTree tree=new StudentBinaryTree(s4);
		s4.left=s2;
		s2.left=s1;
		s2.right=s3;
		s4.right=s6;
		s6.left=s5;
		/*	二叉树模型
		 * 		4
		 *   2     6
		 * 1  3   5
		 */
		System.out.println("前序遍历：");
		tree.preorderTraversal();//前序遍历：421365
		System.out.println("中序遍历：");
		tree.inorderTraversal();//中序遍历：123456
		System.out.println("后序遍历：");
		tree.postorderTraversal();//132564
		System.out.println("前序遍历查找：");
		System.out.println(tree.preorderSearch(3));//4次：4213
		System.out.println("中序遍历查找：");
		System.out.println(tree.inorderSearch(3));//3次：123
		System.out.println("后序遍历查找：");
		System.out.println(tree.postorderSearch(3));//2次：13
		System.out.println("删除节点。");
		tree.delete(6);
		System.out.println("中序遍历：");
		tree.inorderTraversal();//中序遍历：123456
	}

}

//二叉树
class StudentBinaryTree {
	private StudentNode root;// 根节点

	public StudentBinaryTree(StudentNode root) {
		super();
		this.root = root;
	}

	/*
	// 添加学生信息
	public void add(StudentNode student) {
		StudentNode stu = new StudentNode(student.getId(), student.getName());
		if (root != null) {
			StudentNode temp = root;
			while (temp != null) {
				// 假设id唯一
				if (temp.getId() > stu.getId()) {
					if (temp.left != null) {
						temp = temp.left;
					} else {
						temp.left = stu;
						return;
					}
				} else {
					if (temp.right != null) {
						temp = temp.right;
					} else {
						temp.right = stu;
						return;
					}
				}
			}
		} else {
			root = stu;
		}
	}
	*/

	// 前序遍历二叉树
	public void preorderTraversal() {
		if (root != null) {
			root.preorder(root);
		} else {
			System.out.println("学生信息二叉树为空。");
		}
	}

	//中序遍历二叉树
	public void inorderTraversal() {
		if (root != null) {
			root.inorder(root);
		} else {
			System.out.println("学生信息二叉树为空。");
		}
	}

	
	//后续遍历
	public void postorderTraversal() {
		if (root != null) {
			root.postorder(root);;
		} else {
			System.out.println("学生信息二叉树为空。");
		}
	}
	
	//前序遍历查找
	public StudentNode preorderSearch(int id) {
		if(root!=null) {
			return root.preorderSearch(id);
		}else {
			System.out.println("学生信息二叉树中没有信息。");
			return null;
		}
	}
	
	//中序遍历查找
	public StudentNode inorderSearch(int id) {
		if(root!=null) {
			return root.inorderSearch(id);
		}else {
			System.out.println("学生信息二叉树中没有信息。");
			return null;
		}
	}
	
	//后序遍历查找
	public StudentNode postorderSearch(int id) {
		if(root!=null) {
			return root.postorderSearch(id);
		}else {
			System.out.println("学生信息二叉树中没有信息。");
			return null;
		}
	}
	
	//递归删除节点
	//连同该节点子节点一起删除
	public void delete(int id) {
		if (root != null) {
			if (root.getId() != id) {
				root.delete(id);
			} else {
				root=null;
			}
			
		}else {
			System.out.println("学生信息二叉树没有信息。");
		}
	}
	
}

//二叉树数据节点
class StudentNode {
	private int id;// 学生id，默认唯一
	private String name;// 学生姓名
	public StudentNode left;// 左节点
	public StudentNode right;// 右节点

	public StudentNode(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	//后序遍历
	//递归
	public void postorder(StudentNode student) {
		if(student.left!=null) {
			postorder(student.left);
		}
		if(student.right!=null){
			postorder(student.right);
		}
		System.out.println("==>"+student);
	}
	
	//中序遍历
	//递归
	public void inorder(StudentNode student) {
		if(student.left!=null) {
			inorder(student.left);
		}
		System.out.println("==>"+student);
		if(student.right!=null){
			inorder(student.right);
		}
	}
	
	//前序遍历
	// 递归
	public void preorder(StudentNode student) {
		System.out.println("==>" + student);
		if (student.left != null) {
			preorder(student.left);
		}
		if (student.right != null) {
			preorder(student.right);
		}
	}
	
	//前序遍历查找
	public StudentNode preorderSearch(int id) {
		System.out.println("前序遍历查找···");
		if(this.id==id) {
			return this;
		}
		StudentNode stu=null;
		if(this.left!=null) {
			stu=this.left.preorderSearch(id);
		}
		if(stu!=null) {
			return stu;
		}
		if(this.right!=null) {
			stu=this.right.preorderSearch(id);
		}
		return stu;
	}
	
	//中序遍历查找
	public StudentNode inorderSearch(int id) {
		StudentNode stu=null;
		if(this.left!=null) {
			stu=this.left.inorderSearch(id);
		}
		if(stu!=null) {
			return stu;
		}
		System.out.println("中序遍历查找···");
		if(this.id==id) {
			return this;
		}
		if(this.right!=null) {
			stu=this.right.inorderSearch(id);
		}
		return stu;
	}
	
	//后序遍历查找
	public StudentNode postorderSearch(int id) {
		StudentNode stu=null;
		if(this.left!=null) {
			stu=this.left.postorderSearch(id);
		}
		if(stu!=null) {
			return stu;
		}
		if(this.right!=null) {
			stu=this.right.postorderSearch(id);
		}
		if(stu!=null) {
			return stu;
		}
		System.out.println("后序遍历查找···");
		if(this.id==id) {
			return this;
		}
		return stu;
	}
	
	//删除学生信息
	//要求：删除节点，若该节点有子节点也一并删除
	public void delete(int id) {
		if(this.left!=null&&this.left.id==id) {
			this.left=null;
			return;
		}
		if(this.right!=null&&this.right.id==id) {
			this.right=null;
			return;
		}
		if(this.left!=null) {
			this.left.delete(id);
		}
		if(this.right!=null) {
			this.right.delete(id);
		}
	}
}
