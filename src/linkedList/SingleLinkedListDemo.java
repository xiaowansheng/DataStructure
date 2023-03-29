package linkedList;

import java.util.Scanner;

import javax.xml.crypto.Data;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingleLinkedList list=new SingleLinkedList();
		list.addData(1, "哈");
		list.addData(3, "哈哈哈");
		list.addData(2, "哈哈");
		list.addData(5, "哈哈哈哈哈");
		list.addData(4, "哈哈哈哈");
//		list.showDatas();
//		list.insertData(3, 3,"新数据3");
//		list.showDatas();
//		list.insertData(6, 3,"新数据6");
//		list.showDatas();
		//Scanner s=new Scanner(System.in);
		//System.out.println("请输入一个下标：");
		//int n=s.nextInt();
		//System.out.println("下标数="+n+"的数据为："+list.indexOf(n));
		//list.showDatas();
		//list.reservePrint();
		list.sort();
		//两个有序表合并
		//list.leadInsert(0, "0");
		//list.reverseList();
		//list.showDatas();
		System.out.println("链表长度="+list.length);
		//System.out.println("取出数据：["+list.getData()+"],剩余个数:"+list.length);
		//System.out.println("链表是否为空："+list.isEmpty());
		SingleLinkedList list1=new SingleLinkedList();
		list1.addData(8, "a");
		list1.addData(5, "a");
		list1.addData(6, "a");
		list1.addData(15, "a");
		list1.addData(19, "a");
		list1.addData(11, "a");
		list1.addData(12, "a");
		list1.addData(21, "a");
		//合并
		SingleLinkedList newList = mergeOrderList(list, list1);
		newList.showDatas();
	}
	
	//合并有序链表
	public static SingleLinkedList mergeOrderList(SingleLinkedList list1,SingleLinkedList list2) {
		if(list1==null||list2==null||list1.isEmpty()||list2.isEmpty()) {
			throw new RuntimeException("有链表为空或者没有数据，不能合并。");
		}
		//先排序，让两个链表都是有序链表
		//list1.sort();
		//list2.sort();
		//进行合并
		DataNode data1=list1.getLead().next;
		DataNode data2=list2.getLead().next;
		SingleLinkedList list=new SingleLinkedList();
		while(data1!=null) {
			list.orderInsert(data1.getId(),	data1.getName());
			data1=data1.next;
		}
		//list.showDatas();
//		//data1=;
//		DataNode temp=null;//记录当前要排序的数据
//		DataNode temp1=list.getLead();//用于遍历第一个链表
//		DataNode temp2=null;//
		//int count=0;//记录比较次数
		while(data2!=null) {
			list.orderInsert(data2.getId(), data2.getName());
			data2=data2.next;
//			temp=new DataNode(data2.getId(), data2.getName());
//			temp1=list.getLead();
//			data2=data2.next;
//			for(int i=0;i<list.length;i++) {
//				if(temp.getId()<temp1.next.getId()) {
//					temp.next=temp1.next;
//					temp1.next=temp;
//					list.length++;
//					break;
//				}
//				if()
//				temp1=temp1.next;
//			}
		}
		return list;
	}
}

class SingleLinkedList{
	private DataNode lead;//头节点
	public int length;//链表长度
	public DataNode getLead() {
		return lead;
	}

	public SingleLinkedList() {
		super();
		lead=new DataNode(0, "");
		length=0;
	}
	
	//添加数据，尾插法
	public void addData(int id,String name) {
		DataNode data=lead;
		while(true) {
			if(data.next!=null) {
				data=data.next;
			}else {
				data.next=new DataNode(id, name);
				break;
			}
		}
		length++;
		System.out.println("数据：["+data.next+"],添加成功。");
	}
	
	//按从小到大顺序插入数据
	public void orderInsert(int id,String name) {
		DataNode data=new DataNode(id, name);
		if(length==0) {
			lead.next=data;
		}else {
			DataNode temp=lead;
			int count=0;
			while(temp.next!=null) {
				count++;
				if(data.getId()<temp.next.getId()) {
					data.next=temp.next;
					temp.next=data;
					break;
				}else if(count==length) {
					temp.next.next=data;
					break;
				}
				temp=temp.next;
			}
		}
		length++;
	}
	
	//判断是否为空
	public boolean isEmpty() {
		return lead.next==null;
	}
	
	//取出数据
	public DataNode getData() {
		if(length==0) {
			throw new RuntimeException("已经没有数据可以取出了。");
		}
		DataNode temp=lead;
		for(int i=0;i<length-1;i++) {
			temp=temp.next;
		}
		DataNode data=temp.next;
		temp.next=null;
		length--;
		return data;
	}
	
	//第某个数据位置插入数据
	public void insertData(int location,int id,String name) {
		if(length+1<location) {
			System.out.println("插入位置越界。");
			return;
		}
		DataNode data=lead;
		for(int i=0;i<location-1;i++) {
			data=data.next;
		}
		DataNode newNode=new DataNode(id, name);
		newNode.next=data.next;
		data.next=newNode;
		length++;
		System.out.println("插入位置："+location+",插入数据：["+newNode+"],插入成功。");
	}
	
	//返回某个下标数据
	public DataNode indexOf(int index) {
		if(index>length-1||index<0) {
			throw new RuntimeException("下标越界。");
		}
		DataNode data=lead;
		for(int i=0;i<=index;i++) {
			data=data.next;
		}
		return data;
	}
	
	//展示链表所有数据
	public void showDatas() {
		if(length==0) {
			System.out.println("没有数据可以展示了。");
			return;
		}
		DataNode data=lead;
		for(int i=0;i<length;i++) {
			data=data.next;
			System.out.println(data);
		}
	}
	
	//链表反转
	public void reverseList() {
		if(length==0) {
			System.out.println("链表为空，无效操作。");
			return;
		}
		DataNode data = lead.next.next;
		lead.next.next=null;
		DataNode temp=null;
		for(int i=0;i<length-1;i++) {
			temp=lead.next;
			lead.next=data;
			data=data.next;
			lead.next.next=temp;
		}
//		temp=lead.next;
//		while(temp!=null) {
//			System.out.println("输出："+temp);
//			temp=temp.next;
//		}
		System.out.println("链表反转成功。");
	}
	
	//头插法插入数据
	public void leadInsert(int id,String name) {
		DataNode data=lead.next;
		lead.next=new DataNode(id, name);
		lead.next.next=data;
		length++;
		System.out.println("头部插入数据：["+lead.next+"],插入成功。");
	}
	
	//倒序输出
	public void reservePrint() {
		if(length==0) {
			System.out.println("链表为空，无法打印。");
			return;
		}
		DataNode[] arr=new DataNode[length];
		DataNode data=lead.next;
		for(int i=0;i<length;i++) {
			arr[i]=data;
			data=data.next;
		}
		//打印
		for(int i=arr.length-1;i>=0;i--) {
			System.out.println(arr[i]);
		}
	}
	
	//链表排序(通过Id大小)
	public void sort() {
		if(length==0) {
			System.out.println("没有数据可以排序。");
			return;
		}
		DataNode data=lead.next.next;//标识用来排序的链表
		DataNode temp=lead;//标识排好序的链表
		lead.next.next=null;//排好序的链表的最后一个数据的next为空
		DataNode saveData=null;//用于临时保存需要进行排序的某个数据
		int n=1;//记录已经排好序的数据个数
		int m=0;//用于记录比较的次数
		while(data!=null) {
			temp=lead;
			m=0;
			for(int i=n;i>0;i--) {
				m++;
				saveData = data;//记录需要排序的数据
				if(saveData.getId()<temp.next.getId()) {
					data = data.next;
					saveData.next=temp.next;
					temp.next=saveData;//数据插入合适位置
					break;
				}else if(n==m){
					data = data.next;
					temp.next.next=saveData;
					saveData.next=null;
					break;
				}
				//逐个比较，小于且不是最后一个则跳到后一个数据进行比较
				temp=temp.next;
			}
			//排序成功，则有序的数据个数+1
			n++;
		}
	}
}

class DataNode{
	private int id;
	private String name;
	DataNode next;
	
	public DataNode(int data,String name) {
		super();
		this.id = data;
		this.name=name;
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
		return "id=" + id + ", name=" + name;
	}
	
	
}
