package sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = { 2, 10, 11,8, 22, 34, 5, 12, 28, 21, 11,11};
		//int[] data = { 2, 10, 8, 22, 34, 5, 12, 28, 21, 11,5,7,15,3};
		//int[] data = { 2,3,1};
		System.out.println("初始的数据值为:"+Arrays.toString(data));
		quickSort(data, 0, data.length - 1);
//		int[] a=new int[80000];
//		Random r=new Random();
//		for(int i=0;i<a.length;i++) {
//			a[i]=r.nextInt(80000);
//		}
//		Long l1=System.currentTimeMillis();
//		System.out.println("排序前时间："+l1);
//		quickSort(a, 0, a.length-1);
//		Long l2=System.currentTimeMillis();
//		System.out.println("排序后时间："+l2);
//		System.out.println("耗时："+(l2-l1)/1000.0+"秒");//0.035
	}

	// 快速排序
	// 传入二维数组和二维数组的开头和末尾索引
	public static void quickSort(int[] data,int indexBegin,int indexEnd) {
		System.out.println("本次数据段为索引值："+indexBegin+"——"+indexEnd);
		int demarcation=data[indexEnd];//用来划分的界限值，默认最后一个
		System.out.println("分隔值："+demarcation);
		int begin=indexBegin;//开头
		int end=indexEnd;//末尾
		int temp=0;//用于临时转换
		//前面部分从头索引，后面部分从后面索引，索引值逐渐接近到相等,相等时，相等的索引处的值就是该次的分隔值
		while(begin<end) {
			//从前面找到一个值大于分界的
			while(begin<end&&data[begin]<demarcation) {
				begin++;
			}
			//从后面找到一个值小于分界值
			while(end>begin&&data[end]>demarcation) {
				end--;
			}
			if(begin==end) {
				//相等时，说明此时索引对应的值为中间值
				break;
			}else if(data[begin]==data[end]){
				//值相等会无线循环，所以令其中一个+1
				begin++;
			}else {
				//否则更换找到的两个位置
				temp=data[begin];
				data[begin]=data[end];
				data[end]=temp;	
				//System.out.println("每次交换后数据:"+Arrays.toString(data));
			}
		}
		//前面的数据大于1则进入，即（尾-头>1）begin-indexBegin>1
		//小于等于1则不用排序了
		System.out.println("============");
		if(begin-1>indexBegin) {
			quickSort(data, indexBegin, begin-1);
		}
		if(end+1<indexEnd) {
			quickSort(data, end+1, indexEnd);
		}
	}
}
