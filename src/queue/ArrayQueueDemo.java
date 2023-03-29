package queue;

public class ArrayQueueDemo {

	public static void main(String[] args) {
		ArrayQueue queue=new ArrayQueue(5);
		queue.addQueue(1);
		queue.addQueue(2);
		queue.addQueue(3);
		queue.addQueue(4);
		queue.addQueue(5);
		queue.addQueue(6);
		queue.showQueue();
		queue.getQueue();
		queue.getQueue();
		queue.getQueue();
		queue.getQueue();
		queue.getQueue();
		//queue.getQueue();
		queue.showQueue();
		System.out.println(queue.isEmpty());
	}

}

class ArrayQueue{
	private int maxSize;//队列最大容量
	private int front;//队列头
	private int rear;//队列尾
	private int[] arr;//存放数据，模拟队列
	
	public ArrayQueue(int arrMaxSize) {
		maxSize=arrMaxSize;
		arr=new int[maxSize];
		front=-1;//指向队列头部
		rear=-1;//指向队列尾部
	}
	
	//判断队列是否满
	public boolean isFull() {
		return rear==maxSize-1;
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return rear==front&&front!=0;
	}
	
	//添加数据
	public void addQueue(int data) {
		if(isFull()) {
			System.out.println("队列已满，插入失败！");
			return;
		}
		rear++;
		arr[rear]=data;
		System.out.println("成功插入数据："+data);
		if(rear==0) {
			front++;
		}
	}
	
	//取出数据
	public int getQueue() {
		if(rear==-1) {
			throw new RuntimeException("队列为空，没有数据可以取出。");
		}
		int data=arr[front];
		for(int i=maxSize;i<maxSize;i++) {
			arr[i]=arr[i-1];
		}
		rear--;
		if(rear==-1) {
			front--;
		}
		return data;
	}
	
	//显示队列所有数据
	public void showQueue() {
		if(isEmpty()) {
			System.out.println("队列为空，没有数据可展示。");
			return;
		}
		for(int i=0;i<=rear;i++) {
			System.out.print(arr[i]+"\t");
		}
		System.out.println();
	}
}
