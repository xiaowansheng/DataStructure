package stack;

public class LinkedStackDemo {
	public static void main(String[] args) {
		LinkedStack stack = new LinkedStack();
		DataNode data = null;
		for (int i = 1; i <= 5; i++) {
			stack.push(i);
		}
		stack.showData();
		System.out.println("栈内数据个数=" + stack.length);
		System.out.println("弹出数据：" + stack.pop());
		System.out.println("栈内数据个数=" + stack.length);
		stack.showData();

	}
}


//下面的计算器实现用到
class LinkedStack {
	private DataNode front;// 栈顶
	private DataNode rear;// 栈底(没有用处)
	int length;// 栈长度

	public LinkedStack() {
		super();
		this.front = null;
		this.rear = null;
		this.length = 0;
	}

	// 判断是否为空
	public boolean isEmpty() {
		return length == 0;
	}

	// 添加数据
	public void push(int number) {
		if (isEmpty()) {
			front = new DataNode(number);
			rear = front;
			length++;
		} else {
			DataNode temp = new DataNode(number);
			temp.next = front;
			front = temp;
			length++;
		}
	}

	// 弹出栈顶数据
	public DataNode pop() {
		if (isEmpty()) {
			throw new RuntimeException("栈内没有数据。");
		}
		DataNode data = front;
		front = front.next;
		length--;
		return data;
	}
	
	//查看栈顶元素数据值
	public int getTopData() {
		if(isEmpty()) {
			throw new RuntimeException("栈内没有数据。");
		}
		return front.number;
	}

	// 从头到尾展示栈里所有数据
	public void showData() {
		if (isEmpty()) {
			System.out.println("没有数据可以展示.");
		} else {
			DataNode temp = front;
			while (temp != null) {
				System.out.println(temp);
				temp = temp.next;
			}
		}
	}

	// 清空栈内数据
	public void clear() {
		if (!isEmpty()) {
			front = null;
			rear = null;
			length = 0;
		}
	}
}

class DataNode{
	int number;
	//String name;
	DataNode next;
	public DataNode(int id) {
		super();
		this.number = id;
		next=null;
	}
	
	@Override
/*	public String toString() {
		return "DataNode [id=" + id + ", name=" + name + "]";
	}*/
	
	public String toString() {
		return String.valueOf(number);
	}
}