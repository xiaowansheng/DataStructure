package linkedList;

//约瑟夫问题
//小孩围成一圈，从第一个小孩开始数，第n个数的小孩淘汰，淘汰的下一个变成第一个继续，直到剩余最后一个孩子
public class SingleCircleLinkedListDemo {
	public static void main(String[] args) {
		SingCircleLinkedList list = new SingCircleLinkedList(5);
		list.showCondition();
//		list.startGame(2);
//		list.startGame2(2);
//		list.startGame(3);
//		list.startGame2(3);
		list.nextStep(2);
		list.showCondition();
		list.nextStep(5);
		list.showCondition();
		list.nextStep(3);
	}
}

class SingCircleLinkedList {
	int numberOfPeople;// 记录小朋友总数
	Child first;// 编号为1的小朋友
	Child last;// 编号在末尾的小朋友（方便插入数据）

	public SingCircleLinkedList(int numberOfPeople) {
		super();
		this.numberOfPeople=numberOfPeople;
		createChildren(numberOfPeople);
	}

	// 创建一群孩子
	public void createChildren(int numberOfPeople) {
		if (numberOfPeople < 2) {
			throw new RuntimeException("人数不能小于1人，否则游戏无法进行。");
		}
		first = new Child(1, "小1");
		last = first;
		for (int i = 2; i <= numberOfPeople; i++) {
			Child child = new Child(i, "小" + i);
			last.next = child;
			last = child;
			last.next = first;
		}
	}

	// 开启一轮游戏,输入需要循环的数字,循环结束则输出结果
	//优化：输入数字可以大于0
	public void startGame(int number) {
		if(number<1) {
			System.out.println("输入的数字不能小于1，否则无效.");
			return;
		}
		Child child = last;// 记录开始循环的节点的上一个节点，用来辅助循环
		int count = 0;
		// 只剩自己一个数据则结束游戏
		while (child.next.next != child.next) {
			count++;
			if (count == number) {
				System.out.println(child.next + "——————淘汰！");
				child.next = child.next.next;
				last = child;
				first = child.next;
				count = 0;
				continue;
			}
			child = child.next;
		}
		// 只剩一个小朋友
		System.out.println("游戏赢家：" + first);
		first = null;
		last = null;
		createChildren(numberOfPeople);
		System.out.println("数据重置成功！");
	}

	// 开启一轮游戏,输入需要循环的数字，循环结束则输出结果
	//代码经过优化效率略高，输入数字大于1
	public void startGame2(int number) {
		if(number<2) {
			System.out.println("输入的数字不能小于2，否则无效.");
			return;
		}
		Child child = first;// 用来辅助循环
		int count = 1;
		// 只剩自己一个数据则结束游戏
		while (child.next != child) {
			while (count + 1 != number) {
				child = child.next;
				count++;
			}
			System.out.println(child.next + "——————淘汰！");
			child.next = child.next.next;
			last = child;
			first = child.next;
			child=first;
			//重置计数
			count = 1;
		}
		// 只剩一个小朋友
		System.out.println("游戏赢家：" + first);
		first = null;
		last = null;
		createChildren(numberOfPeople);
		System.out.println("数据重置成功！");
	}

	// 手动下一步，需要传入每一步需要循环的数，每次的数字可不同
	public void nextStep(int number) {
		// 只剩自己一个数据则结束游戏,并重新刷新人数
		if (first.next == first) {
			System.out.println("游戏赢家：" + first);
			first = null;
			last = null;
			createChildren(numberOfPeople);
		} else {
			Child child = first;// 用来辅助循环
			int count = 1;
			// 找到目标前一个便退出循环
			while (count + 1 != number) {
				child = child.next;
				count++;
			}
			System.out.println(child.next + "——————淘汰！");
			child.next = child.next.next;
			last = child;
			first = child.next;
			// 获取下一个结果则退出
			// return;
		}
	}

	// 展示当前小孩的情况
	public void showCondition() {
		Child child = first;
		while (child != null && child.next.next != first.next) {
			System.out.println(child);
			child = child.next;
		}
		System.out.println(child);
	}
}

//孩子节点
class Child {
	int id;
	String name;
	Child next;

	public Child(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "孩子 [id=" + id + ", name=" + name + "]";
	}
}