package sort;

import java.util.Arrays;
import java.util.Random;

public class BubbleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] a= {5,1,4,6,9,4,3,7,11,8,22,15};
		int[] a=new int[80000];
		Random r=new Random();
		for(int i=0;i<a.length;i++) {
			a[i]=r.nextInt(80000);
		}
		Long l1=System.currentTimeMillis();
		System.out.println("排序前时间："+l1);
		bubbleSort(a);
		Long l2=System.currentTimeMillis();
		System.out.println("排序后时间："+l2);
		System.out.println("耗时："+(l2-l1)/1000.0+"秒");
		//System.out.println(Arrays.toString(a));
	}
	
	public static void bubbleSort(int[] data) {
		boolean flag=false;//用于优化排序算法，减少不必要的排序次数
		for (int i = 0; i < data.length-1; i++) {
			for (int j = 0; j < data.length-1-i; j++) {
				if (data[j] > data[j + 1]) {
					flag=true;
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
			//System.out.println(Arrays.toString(data));
			if(flag==false) {
				//若有依次排序时没有经过任何交换则排序已经完成
				break;
			}else {
				//否则重置
				flag=false;
			}
		}
	}
}