package hashTable;

import java.util.Scanner;

public class HashTableDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTable h=new HashTable(5);
		System.out.println("欢迎使用学生管理系统！");
		System.out.println("请按照提示进行操作：");
		Scanner s = new Scanner(System.in);
		String order = null;
		while (true) {
			System.out.println("'add':添加学生信息。");
			System.out.println("'find':查找学生信息。");
			System.out.println("'del':删除学生信息。");
			System.out.println("'show':展示所有学生信息。");
			System.out.println("'exit':退出系统。");
			System.out.println("请输入操作命令：");
			order = s.next();
			switch (order) {
			case "add":
				System.out.println("请输入学生id:");
				int id1=s.nextInt();
				System.out.println("请输入学生姓名：");
				String name=s.next();
				Student stu=new Student(id1, name);
				h.add(stu);
				break;
			case "find":
				System.out.println("请输入想要查找的id:");
				int id2=s.nextInt();
				Student stu2=h.find(id2);
				if(stu2!=null) {
					System.out.println(stu2);
				}
				break;
			case "del":
				System.out.println("请输入需要删除的学生id：");
				h.delete(s.nextInt());
				break;
			case "show":
				h.show();
				break;
			case "exit":
				return;
				default:
					System.out.println("没有相关指令。");
					System.out.println("请重新选择。");
					break;
			}
		}

	}

}

//学生哈希表
class HashTable {
	private StudentLinkedList[] studentLinkedListArray;// 链表数组
	private int size;// 记录数组长度

	public HashTable(int size) {
		super();
		this.size = size;
		studentLinkedListArray = new StudentLinkedList[size];
		for (int i = 0; i < size; i++) {
			// 初始化数组的每个链表
			studentLinkedListArray[i] = new StudentLinkedList();
		}
	}

	// 简单散列函数，使用简单取模法
	public int delivery(int id) {
		return id % size;
	}

	// 添加数据
	public void add(Student student) {
		Student data = new Student(student.getId(), student.getName());
		int delivery = delivery(data.getId());
		studentLinkedListArray[delivery].add(data);
	}

	// 根据id查找某同学
	// 假设id唯一
	public Student find(int id) {
		int delivery = delivery(id);
		return studentLinkedListArray[delivery].find(id);
	}

	//删除学生数据
	public void delete(int id) {
		int delivery=delivery(id);
		studentLinkedListArray[delivery].delete(id);
	}
	
	// 展示哈希表所有数据
	public void show() {
		for (int i = 0; i < size; i++) {
			System.out.print("第" + (i + 1) + "个链表:");
			studentLinkedListArray[i].show();
		}
	}
}

//学生链表
class StudentLinkedList {
	private Student head;// 头节点
	private int length;// 链表长度

	public StudentLinkedList() {
		super();
		head = new Student(0, "头节点");
		length = 0;
	}

	public int getLength() {
		return length;
	}

	// 添加学生信息
	// 添加在末尾
	public void add(Student student) {
		Student temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = student;
		System.out.println("数据"+student+"添加成功。");
	}

	// 根据id查找数据
	public Student find(int id) {
		Student temp = head.next;
		while (temp != null) {
			if (temp.next.getId() == id) {
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}
	
	//删除某个学生记录
	public void delete(int id) {
		Student temp=head;
		while(temp.next!=null) {
			if(temp.next.getId()==id) {
				Student data=temp.next.next;
				temp.next=data;
				System.out.println("学号为"+id+"的学生已删除。");
				return;
			}
			temp=temp.next;
		}
		System.out.println("该学生id["+id+"]不存在。");
	}

	// 输出链表所有数据
	public void show() {
		if (head.next == null) {
			System.out.println("没有数据···");
			return;
		}
		Student temp = head.next;
		while (temp != null) {
			System.out.print("==>" + temp);
			temp = temp.next;
		}
		System.out.println();
	}
}

//学生节点类
class Student {
	private int id;// 学生id
	private String name;
	public Student next;// 下一个节点

	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}// 学生姓名

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