package queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {

	public static void main(String[] args) {
		CircleArrayQueue queue=new CircleArrayQueue(5);
		char key=' ';
        Scanner scan=new Scanner(System.in);  //标准输入，拿到一个扫描器
        boolean loop=true;  //默认死循环
        while(loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            System.out.println("e(exit)：退出程序");
            key=scan.next().charAt(0);

            switch(key) {
                case's':
                    queue.showQueue();
                    break;
                case'a':
                    System.out.println("请输入一个数字：");
                    int value=scan.nextInt();
                    queue.addQueue(value);
                    break;
                case'g':
                    try {
                        int res=queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {

                        System.out.println(e.getMessage());
                    }
                    break;
                case'h':
                    try {
                        //int res=queue.headQueue();
                        //System.out.printf("队列头的数据是：%d\n",res);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());
                    }
                    break;
                case'e':
                    scan.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
	}
}

class CircleArrayQueue{
	private int maxSize;//队列最大容量
	private int front;//队列头
	private int rear;//队列尾
	private int[] arr;//存放数据，模拟队列
	
	public CircleArrayQueue(int arrMaxSize) {
		maxSize=arrMaxSize;
		arr=new int[maxSize];
		front=0;//指向队列头部
		rear=0;//指向队列尾部
	}
	
	//判断队列是否满
	public boolean isFull() {
		return (rear+1)%maxSize==front;
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return rear==front;
	}
	
	//添加数据
	public void addQueue(int data) {
		if(isFull()) {
			System.out.println("队列已满，插入失败！");
			return;
		}
		arr[rear]=data;
		rear=(rear+1)%maxSize;
	}
	
	//取出数据
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空，没有数据可以取出。");
		}
		int data=arr[front];
		front=(front+1)%maxSize;
		return data;
	}
	
	//显示队列所有数据
	public void showQueue() {
		if(isEmpty()) {
			System.out.println("队列为空，没有数据可展示。");
			return;
		}
		for(int i=front;i<=size();i++) {
			System.out.print("第"+i%maxSize+"个数="+arr[i%maxSize]+"\t");
		}
		System.out.println();
	}
	
	//队列有效数据
	public int size() {
		return (rear-front+maxSize)%maxSize;
	}
}
