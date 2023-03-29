package sparseArray;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;


public class SparseArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建一个原始的二维数组11*11
		//0：没有棋子，1：黑子，2：蓝子
		int[][] chessArray=new int[11][11];;
		chessArray[5][6]=1;
		chessArray[6][1]=2;
		chessArray[6][3]=1;
		chessArray[4][5]=2;
		chessArray[8][7]=2;
		System.out.println("棋盘：");
		for(int[] a:chessArray) {
			for(int c:a) {
				System.out.print(c+"  ");
			}
			System.out.println();
		}
		//将二维数组转换为稀疏数组
		//记录棋子数
		//1、先遍历数组得到非0数的数量
		int sum=0;
		for(int i=0;i<chessArray.length;i++) {
			for(int j=0;j<chessArray[0].length;j++) {
				if(chessArray[i][j]!=0) {
					sum++;
				}
			}
		}
		System.out.println("棋子数："+sum);
		
		//2、创建稀疏数组
		int[][] squarseArray=new int[sum+1][3];
		//用于记录 第几个非0数
		int n=0;
		//给数组赋值
		squarseArray[0][0]=chessArray.length;
		squarseArray[0][1]=chessArray[0].length;
		squarseArray[0][2]=sum;
		for(int i=0;i<chessArray.length;i++) {
			for(int j=0;j<chessArray[0].length;j++) {
				if(chessArray[i][j]!=0) {
					n++;
					squarseArray[n][0]=i;
					squarseArray[n][1]=j;
					squarseArray[n][2]=chessArray[i][j];
				}
			}
		}
		System.out.println("稀疏数组：");
		for(int[] a:squarseArray) {
			for(int c:a) {
				System.out.print(c+"\t");
			}
			System.out.println();
		}
		//将稀疏数组保存到文件
		//FileWriter out=null;
		ObjectOutput out=null;
		try {
			out=new ObjectOutputStream(new FileOutputStream("D:\\java\\eclipse-workspace\\DataStructure\\src\\sparseArray\\chess.data"));
			out.writeObject(squarseArray);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(out!=null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		//3、恢复成原棋盘
		int[][] array=null;
		ObjectInput input=null;
		try {
			input=new ObjectInputStream(new FileInputStream("D:\\\\java\\\\eclipse-workspace\\\\DataStructure\\\\src\\\\sparseArray\\\\chess.data"));
			array=(int[][])input.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(input!=null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("还原稀疏数组：");
		for(int[] a:array) {
			for(int c:a) {
				System.out.print(c+"\t");
			}
			System.out.println();
		}
		
		System.out.println("还原棋盘：");
		int[][] chess=new int[squarseArray[0][0]][squarseArray[0][1]];
		for(int i=1;i<squarseArray.length;i++) {
			chess[squarseArray[i][0]][squarseArray[i][1]]=squarseArray[i][2];
		}
		for(int[] a:chess) {
			for(int c:a) {
				System.out.print(c+"  ");
			}
			System.out.println();
		}
	}
}
