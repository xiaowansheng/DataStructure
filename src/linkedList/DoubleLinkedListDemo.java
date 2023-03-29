package linkedList;

public class DoubleLinkedListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DoubleLinkedList list = new DoubleLinkedList();
		list.addLastData(1, "一");
		list.addLastData(2, "二");
		list.addLastData(3, "三");
		list.addLastData(4, "四");
		list.addLastData(5, "五");
		System.out.println("index=3" + list.index(3));
		System.out.println("从开头取出:"+list.popLeadData());
		System.out.println("展示剩余数据：");
		list.showDatas();
		System.out.println("从末尾取出："+list.popLastData());
		System.out.println("展示数据：");
		list.showDatas();
		System.out.println("头插法.");
		list.addLeadData(0, "零");
		System.out.println("展示数据：");
		list.showDatas();
		System.out.println("链表数据个数="+list.length());
		System.out.println("某下标位置添加数据。");
		list.insertData(3, 3, "new3");
		System.out.println("展示数据：");
		list.showDatas();
		System.out.println("链表数据个数="+list.length());
		System.out.println("清空链表。");
		list.clear();
		System.out.println("展示数据：");
		list.showDatas();
		System.out.println("链表数据个数="+list.length());
	}
}

//双向链表
class DoubleLinkedList {
	private DataNode2 lead;// 头节点
	private DataNode2 last;// 尾节点
	private int length;// 链表长度

	public DoubleLinkedList() {
		super();
		this.lead = new DataNode2(0, "头节点");
	}

	// 获取链表长度
	public int length() {
		return length;
	}

	/// 获取头节点
	public DataNode2 getLead() {
		return lead;
	}

	// 获取尾节点
	public DataNode2 getLast() {
		return last;
	}
	
	//判断是否为空
	public boolean isEmpty() {
		return length==0;
	}

	// 在末尾添加数据
	public void addLastData(int id, String name) {
		DataNode2 data = new DataNode2(id, name);
		if (length == 0) {
			lead.next = data;
			data.pre = lead;
			last = data;
		} else {
			last.next=data;
			data.pre=last;
			last=data;
		}
		length++;
	}

	// 从头节点插入数据
	public void addLeadData(int id, String name) {
		DataNode2 data = new DataNode2(id, name);
		data.next=lead.next;
		lead.next.pre=data;
		lead.next=data;
		data.pre=lead;
		length++;
	}

	//在某个下标处插入数据
	public void insertData(int index,int id,String name) {
		if (index > length - 1 || index < 0) {
			throw new RuntimeException("下标越界.");
		}
		DataNode2 data=new DataNode2(id, name);
		DataNode2 temp = null;
		// 查找的下标小于中位数从开头找，大于从末尾找
		int number = (length - 1) / 2;
		int count = 0;
		if (index <= number) {
			temp = lead.next;
			while (count != index) {
				temp = temp.next;
				count++;
			}
		} else {
			temp = last;
			count = length - 1;
			while (count != index) {
				temp = temp.pre;
				count--;
			}
		}
		data.next=temp;
		data.pre=temp.pre;
		temp.pre.next=data;
		temp.pre=data;
		length++;
	}
	
	// 返回某个下标的数据,从0开始
	public DataNode2 index(int index) {
		if (index > length - 1 || index < 0) {
			throw new RuntimeException("下标越界.");
		}
		DataNode2 temp = null;
		// 查找的下标小于中位数从开头找，大于从末尾找
		int number = (length - 1) / 2;
		int count = 0;
		if (index <= number) {
			temp = lead.next;
			while (count != index) {
				temp = temp.next;
				count++;
			}
		} else {
			temp = last;
			count = length - 1;
			while (count != index) {
				temp = temp.pre;
				count--;
			}
		}
		//返回一个副本数据，防止链表数据被破坏
		DataNode2 data=new DataNode2(temp.id, temp.name);
		return data;
	}

	// 取出末尾数据
	public DataNode2 popLastData() {
		if (last == null) {
			throw new RuntimeException("没有数据可以取出。");
		}
		DataNode2 data = last;
		last = last.pre;
		last.next = null;
		if(last==lead) {
			last=null;
		}
		data.pre=null;
		length--;
		return data;
	}

	// 取出开头数据
	public DataNode2 popLeadData() {
		if (lead.next == null) {
			throw new RuntimeException("没有数据可以取出。");
		}
		DataNode2 data = lead.next;
		lead.next = lead.next.next;
		lead.next.pre = lead;
		data.next=null;
		data.pre=null;
		if(last==data) {
			last=null;
		}
		data.next=null;
		data.pre=null;
		length--;
		return data;
	}

	// 展示数据
	public void showDatas() {
		if (length == 0) {
			System.out.println("链表中没有数据。");
			return;
		}
		DataNode2 temp = lead.next;
		while (temp != null) {
			System.out.println(temp);
			temp = temp.next;
		}
	}
	
	//清空链表
	public void clear() {
		if(length!=0) {
			lead.next=null;
			last=null;
			length=0;
		}
		System.out.println("链表已清空。");
	}
}

class DataNode2 {
	public int id;
	public String name;
	public DataNode2 pre;// 上一个节点
	public DataNode2 next;// 下一个节点

	public DataNode2(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "DataNode [id=" + id + ", name=" + name + "]";
	}

}
