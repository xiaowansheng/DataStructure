package sort;

import java.util.Arrays;
import java.util.Random;

public class ShellSort {
	public static void main(String[] args) {
		//int[] a = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		int[] a=new int[80000];
		Random r=new Random();
		for(int i=0;i<a.length;i++) {
			a[i]=r.nextInt(80000);
		}
		Long l1=System.currentTimeMillis();
		System.out.println("排序前时间："+l1);
		//shellSort(a);//5.8秒
		shellSort2(a);//0.017秒
		Long l2=System.currentTimeMillis();
		System.out.println("排序后时间："+l2);
		System.out.println("耗时："+(l2-l1)/1000.0+"秒");
	}

	//希尔排序————>交换法
	public static void shellSort(int[] data) {
		//System.out.println("原数据：");
		//System.out.println(Arrays.toString(data));
		int x=0;//记录比较次数
		int temp = 0;//用于交换位置
		int count=0;//记录分组次数
		// for列出分组的不同情况
		// n是组数,每次组数在上次组数上除2
		for (int n = data.length / 2; n > 0; n /= 2) {
			// for对当前情况的分组排序
			//因为分组组距都一样，所以可以对所有分组进行同样的操作
			for (int i = n; i < data.length; i++) {
				//交换次数是：总数-组距
				//-i是减已经交换过的数据
				for (int j = i - n; j >= 0; j-= n) {
					// 判断大小，大的往后移（冒泡排序）
					if (data[j] > data[j + n]) {
						temp = data[j];
						data[j] = data[j + n]; 
						data[j + n] = temp;
						// System.out.println(Arrays.toString(data));
					}
					x++;
				}
			}
			//输出每次交换情况
			//System.out.println("第"+(++count)+"次交换。");
			//System.out.println("大小比较次数："+x+"次。");
			//System.out.println(Arrays.toString(data));
		}
	}
		
	//对交换法的希尔排序进行优化————>位移法
	public static void shellSort2(int[] data) {
		int count=0;
		for (int n = data.length / 2; n > 0; n /= 2) {
			for(int i=n;i<data.length;i++) {
				int index=i;
				int inserData=data[i];
				if(inserData<data[index-n]) {
					while(index-n>=0&&data[index-n]>inserData) {
						data[index]=data[index-n];
						index=index-n;
					}
					data[index]=inserData;
				}
			}
			//输出每次交换情况
			//System.out.println("第"+(++count)+"次交换。");
			//System.out.println(Arrays.toString(data));
		}
	}
}
