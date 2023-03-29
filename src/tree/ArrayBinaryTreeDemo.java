package tree;

public class ArrayBinaryTreeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentNode2[] stu=new StudentNode2[10];
		for(int i=1;i<=10;i++) {
			stu[i-1]=new StudentNode2(i, "xiao"+i);
		}
		ArrayBinaryTree tree=new ArrayBinaryTree(stu);
		tree.orderTraversal();
	}

}

class ArrayBinaryTree {
	private StudentNode2[] students;//可以看作完全二叉树，数据顺序排列

	public ArrayBinaryTree(StudentNode2[] students) {
		super();
		this.students = students;
	}
	
	//重载
	//方便递归调用
	public void orderTraversal() {
		if(students!=null) {
			if(students.length==0) {
				System.out.println("数组二叉树中没有数据。");
			}else {
				orderTraversal(0);
			}
		}else {
			System.out.println("数组二叉树不存在。");
		}
	}
	
	//遍历数组二叉树顺序输出
	private void orderTraversal(int index) {
		//输出父节点
		System.out.println(students[index]);
		//左节点位于父节点的索引的2倍+1
		if(index*2+1<students.length) {
			orderTraversal(index*2+1);
		}
		///右节点位于父节点的索引的2倍+2
		if(index*2+2<students.length) {
			orderTraversal(index*2+2);
		}
	}
}

class StudentNode2 {
	private int id;// 学生id，
	private String name;// 学生姓名

	public StudentNode2(int id, String name) {
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

}