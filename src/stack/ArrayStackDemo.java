package stack;

public class ArrayStackDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayStack stack=new ArrayStack(5);
		for(int i=1;i<7;i++) {
			stack.push(i);
		}
		stack.showData();
		for(int i=4;i>=0;i--) {
			stack.pop();
		}
		
		System.out.println(stack.pop());
		System.out.println(stack.length);
		stack.showData();
	}
}

class ArrayStack{
	int[] datas;
	int length;//栈长度
	//初始化，构造栈
	public ArrayStack(int length) {
		super();
		this.datas = new int[length];
		this.length = 0;
	}
	
	//判断是否为空
	public boolean isEmpty() {
		return length==0;
	}
	
	//判断是否满
	public boolean isFull() {
		return datas.length==length;
	}
	
	//添加数据
	public void push(int data) {
		if(isFull()) {
			System.out.println("栈满，无法添加数据。");
		}else {
			datas[length++]=data;
		}
	}
	
	//弹出栈顶数据
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("栈内没有数据可以弹出。");
		}
		return datas[--length];
	}
	
	//展示栈内所有数据
	public void showData() {
		for(int i=length-1;i>-1;i--) {
			System.out.println(datas[i]);
		}
	}
}
