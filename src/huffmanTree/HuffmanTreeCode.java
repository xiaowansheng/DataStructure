package huffmanTree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HuffmanTreeCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String data = "i like like like java do you like a java";
//		byte[] zipData=huffmanCodesZip(data.getBytes(),map);
//		String unzip=Arrays.toString(huffmanCodesZip(zipData, map));
//		System.out.println(unzip);
		
//		String source="E:\\haha.txt";
//		String outputPath="E:\\haha.zip";
//		zipFile(source, outputPath);
//		System.out.println("压缩完成!");
		
		String source="E:\\haha.zip";
		String outputPath="E:\\haha2.txt";
		unzipFile(source, outputPath);
		System.out.println("解压完成!");
	}
	
	public static void unzipFile(String source,String outputPath) {
		ObjectInput input=null;
		FileOutputStream output=null;
		try {
			input=new ObjectInputStream(new FileInputStream(source));
			output=new FileOutputStream(outputPath);
			//以对象的形式读取文件
			byte[] b=(byte[])input.readObject();
			Map<Byte,String> map=(Map<Byte,String>)input.readObject();
			//解压获取原来数据
			byte[] bytes=huffmanCodesUnzip(b,map);
			//并写入文件
			output.write(bytes);
			output.flush();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(output!=null) {
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(input!=null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 传入源文件的路径和输出文件的路径，将源文件压缩输出到输出文件路径
	 * @param source 源文件路径
	 * @param outputPath 输出文件路径
	 */
	public static void zipFile(String source,String outputPath) {
		FileInputStream input=null;
		ObjectOutputStream output=null;
		try {
			input=new FileInputStream(source);
			output=new ObjectOutputStream(new FileOutputStream(outputPath));
			byte[] b=new byte[input.available()];
			b=input.readAllBytes();
			//记录编码表
			Map<Byte,String> map=new HashMap<Byte, String>();
			//获取压缩以后的字符数组
			byte[] bytes=huffmanCodesZip(b,map);
			//以对象的形式写入文件
			output.writeObject(bytes);
			output.writeObject(map);
			output.flush();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(output!=null) {
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(input!=null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 *  解压压缩后的字符数组
	 * @param zipData 压缩后的字符数组
	 * @param map 编码表
	 * @return 返回原数据
	 */
	public static byte[] huffmanCodesUnzip(byte[] zipData,Map<Byte,String> map) {
		Map<String,Byte> m=new HashMap<String, Byte>(map.size());
		//将map键值对反过来存储，用于根据编码取对应值
		for(Map.Entry<Byte, String> b:map.entrySet()) {
			m.put(b.getValue(), b.getKey());
		}
		//用来记录byte数组转换出来的编码
		StringBuilder str=new StringBuilder();
		for(int i=0;i<zipData.length;i++) {
			str.append(getHuffmanCode(zipData[i], i==zipData.length-1));
		}
		System.out.println(str);
		//将编码转换成原数据
		//StringBuilder data=new StringBuilder();
		List<Byte> list=new ArrayList<Byte>();
		String s=null;
		for (int i = 0, count = 1; i < str.length();count++) {
			s=str.substring(i, i+count);
			if(m.get(s)!=null) {
				//把byte转成对应字符，并存在data字符串中
				//data.append((char)(byte)m.get(s));
				list.add(m.get(s));
				i+=count;
				count=0;
			}
		}
		byte[] bytes=new byte[list.size()];
		for(int i=0;i<bytes.length;i++) {
			bytes[i]=list.get(i);
		}
		//return data.toString().getBytes();
		return bytes;
	}
	
	/**
	 * 将字符转换为八位的二进制(即还原为原来的)
	 * @param b 传续需要转换的字符
	 * @param flag 是否是解析的最后一个字符
	 * @return 转换为二进制的字符串返回
	 */
	private static String getHuffmanCode(byte b,boolean flag) {
		//使用变量保存 b
		int temp = b; //将 b 转成 int
		//如果是正数我们还存在补高位
		if(!flag) {
			temp |= 256; //按位与 256  1 0000 0000  | 0000 0001 => 1 0000 0001
		}
		String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
		if(!flag) {
			return str.substring(str.length() - 8);
		} else {
			return str;
		}
	}

	//public static Map<Byte, String> map=null;//存编码表
	
	/**
	 * 获取经过哈夫曼编码压缩后的文件字符数组
	 * @param data 需要压缩的字符串数据
	 * @param map 传入一个新的Map集合，压缩完成会往集合内储存编码表
	 * @return 返回压缩后的字符数组数据
	 */
	public static byte[] huffmanCodesZip(byte[] data,Map<Byte,String> map) {
		//System.out.println(Arrays.toString(bytes));
		//获取数据整理后的List(存储字母及其个数)
		List<Node> list = HuffmanTreeCode.getDataList(data);
		//System.out.println(list);
		//创建对应的哈夫曼树
		Node root = createHuffmanTree(list);
		//preorderTraversal(root);
		//获取编码表
		map = getCodeSchedule(root);
		//System.out.println(map.toString());
		//获取数据压缩后的字符数组
		byte[] b=zip(map, data);
		//System.out.println(Arrays.toString(b));
		return b;
	}
	
	/**
	 * 获取压缩后的字符数组数据
	 * @param map 存储字符编码的map集合
	 * @param data 原数据转换的byte数组
	 * @return 返回压缩后的数据
	 */
	public static byte[] zip(Map<Byte,String> map,byte[] data) {
		//记录压缩的字符串
		StringBuilder str=new StringBuilder();
		//根据编码表转换字符数组
		for(byte b:data) {
			str.append(map.get(b));
		}
		//System.out.println(str);
		//用来记录压缩后的字符数组
		byte[] huffmanCodes=null;
		//根据字符串长度创建一定长度字符数组
		if(str.length()%8==0) {
			huffmanCodes=new byte[str.length()/8];
		}else {
			huffmanCodes=new byte[str.length()/8+1];
		}
		//将每八位字符串转换为一个字符(一个字符八位),缩小空间
		for(int i=0,index=0;i<str.length();i+=8,index++) {
			if(i+8<=str.length()) {
				huffmanCodes[index]=(byte)Integer.parseInt(str.substring(i, i+8),2);
			}else {
				huffmanCodes[index]=(byte)Integer.parseInt(str.substring(i),2);
			}
		}
		//返回压缩以后的字符数组
		return huffmanCodes;
	}
	
	/**
	 * 用来调用获取编码表的递归方法
	 * @param root 传入的哈夫曼树根节点
	 * @return 返回存储哈希表的map集合
	 */
	public static Map<Byte, String> getCodeSchedule(Node root) {
		Map<Byte, String> map = new HashMap<Byte, String>();
		StringBuilder strb = new StringBuilder();
		if (root != null) {
			getCodeSchedule(root.left, map, 0, strb);
			getCodeSchedule(root.right, map, 1, strb);
		} else {
			System.out.println("根节点为空。");
		}
		return map;
	}
	
	/**
	 * 用来计算哈夫曼树对应哈夫曼字符编码的递归方法
	 * @param data 传入的节点
	 * @param map 用来存储编码表的map集合
	 * @param code 路径:某个递归过程的编码值，向左+0，向右+1
	 * @param str 上个节点位置的编码值,用来拼 接路径即编码值
	 */
	private static void getCodeSchedule(Node data, Map<Byte, String> map, int code, StringBuilder str) {
		if(data==null) {
			return;
		}
		/// 创建当前节点位置的编码=上个节点编码值+当前节点编码code
		StringBuilder strb = new StringBuilder(str);
		// +当前节点code
		strb.append(code);
		// 判断是否有数据值，即是否是叶子节点
		if (data.getData() == null) {
			// 左节点不空往左节点遍历
			getCodeSchedule(data.left, map, 0, strb);
			// 右节点不空往右节点遍历
			getCodeSchedule(data.right, map, 1, strb);
		} else {
			// 是叶子节点，即有数据，则把该数据即数据的编码插入map中
			map.put(data.getData(), strb.toString());
		}
	}

	/**
	 * 创建哈夫曼树
	 * 
	 * @param nodes 传入处理好的存储LetterNode的链表
	 * @return 返回哈夫曼树的根节点
	 */
	public static Node createHuffmanTree(List<Node> nodes) {
		while (nodes.size() > 1) {
			Collections.sort(nodes);
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			Node parent = new Node(leftNode.getWeight() + rightNode.getWeight());
			parent.left = leftNode;
			parent.right = rightNode;
			nodes.remove(0);
			nodes.remove(0);
			nodes.add(parent);
		}
		return nodes.get(0);
	}

	// 前序遍历哈夫曼树
	public static void preorderTraversal(Node root) {
		if (root != null) {
			root.preorderTraversal(root);
		} else {
			System.out.println("哈夫曼树不存在。");
		}
	}

	/**
	 * 记录字母及其出现次数，并转换为LetterNode对象存放在List集合中返回
	 * 
	 * @param data 需要传输的字符数组
	 * @return 返回一个List，其中每个数据记录了单词及其权重（出现次数）
	 */
	private static List<Node> getDataList(byte[] data) {
		Map<Byte, Integer> map = new HashMap<>();
		Integer i = null;
		for (byte b : data) {
			i = map.get(b);
			if (i != null) {
				map.put(b, i + 1);
			} else {
				map.put(b, 1);
			}
		}
		List<Node> list = new ArrayList<Node>();
		for (Map.Entry<Byte, Integer> m : map.entrySet()) {
			list.add(new Node(m.getKey(), m.getValue()));
		}
		return list;
	}
}

class Node implements Comparable<Node> {
	private Byte data;// 字母，用ascill码值记录
	private int weight;// 权重
	public Node left;
	public Node right;

	public Byte getData() {
		return data;
	}

	public void setData(Byte data) {
		this.data = data;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Node(byte data, int weight) {
		super();
		this.data = data;
		this.weight = weight;
	}

	public Node(int weight) {
		super();
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.weight - o.weight;
	}

	@Override
	public String toString() {
		return "[data=" + data + ", weight=" + weight + "]";
	}

	public void preorderTraversal(Node data) {
		System.out.println(data);
		if (data.left != null) {
			preorderTraversal(data.left);
		}
		if (data.right != null) {
			preorderTraversal(data.right);
		}
	}
}