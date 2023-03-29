package huffmanTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class HuffmanTreeDemo {
	public static void main(String[] args) {
//		int[] data= {2,4,6,1,3,8,9};
		int[] data={13,7,8,3,29,6,1};
		/*
		 *      
		 * 
		 * 
		 */
//		Random r=new Random();
//		for(int i=0;i<data.length;i++) {
//			data[i]=r.nextInt(100);
//		}
		HuffmanTree h=new HuffmanTree(data);
		System.out.println("前序遍历：");
		h.preorderTraversal();
	}
}

class HuffmanTree{
	private DataNode root;
	
	//传入数组，根据值，构建一个哈夫曼树，并返回
	public HuffmanTree(int[] data) {
		super();
		this.root = createHuffmanTree(data);
	}

	/**
	 * 
	 * @param data 需要创建哈夫曼树的数组
	 * @return 创建好哈夫曼树后返回的root节点
	 */
	public DataNode createHuffmanTree(int[] data) {
		ArrayList<DataNode> arr=new ArrayList<DataNode>(data.length);
		for (int d:data) {
			arr.add(new DataNode(d));
		}
		System.out.println(arr);
		Collections.sort(arr);
		System.out.println(arr);
		while(arr.size()>1) {
			DataNode leftNode=arr.get(0);
			DataNode rightNode=arr.get(1);
			DataNode parent=new DataNode(leftNode.getValue()+rightNode.getValue());
			parent.left=leftNode;
			parent.right=rightNode;
			arr.remove(0);
			arr.remove(0);
			arr.add(parent);
			Collections.sort(arr);
		}
		return arr.get(0);
	}
	
	public void preorderTraversal() {
		if(root!=null) {
			preorderTraversal(root);
		}else {
			System.out.println("二叉树为空。");
		}
	}
	
	private void preorderTraversal(DataNode data) {
		System.out.println(data);
		if(data.left!=null) {
			preorderTraversal(data.left);
		}
		if(data.right!=null) {
			preorderTraversal(data.right);
		}
	}
}

class DataNode implements Comparable<DataNode> {
	private int value;// 节点值
	public DataNode left;
	public DataNode right;

	public DataNode(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "[value=" + value + "]";
	}

	@Override
	public int compareTo(DataNode o) {
		// TODO Auto-generated method stub
		return this.value-o.value;
	}

}