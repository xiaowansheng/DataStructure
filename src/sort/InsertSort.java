package sort;

import java.util.Arrays;
import java.util.Random;

public class InsertSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] a= {8,6,7,2,4,3,1,0,5,0};
//		insertSort(a);
//		System.out.println(Arrays.toString(a));
		int[] a=new int[80000];
		Random r=new Random();
		for(int i=0;i<a.length;i++) {
			a[i]=r.nextInt(80000);
		}
		Long l1=System.currentTimeMillis();
		System.out.println("排序前时间："+l1);
		insertSort(a);
		Long l2=System.currentTimeMillis();
		System.out.println("排序后时间："+l2);
		System.out.println("耗时："+(l2-l1)/1000.0+"秒");
	}

	public static void insertSort(int[] data) {
		//把第一个数据值当有序，后面所有数据当无序
		//每次记录无序第一个值在有序中进行排序
		int index=0;//有序的最后一个值下标
		int insertData=0;//记录无序的第一个值
		for(int i=0;i<data.length-1;i++) {
			index=i;
			insertData=data[i+1];
			while(index>=0&&insertData<data[index]) {
				data[index+1]=data[index];
				index--;
			}
			data[index+1]=insertData;
			//输出每次排序的情况
			//System.out.println(Arrays.toString(data));
		}
	}
}
