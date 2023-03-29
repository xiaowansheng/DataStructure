package sort;

import java.util.Arrays;
import java.util.Random;

public class SelectionSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] a= {5,15,20,2,45,6,-1,2,3,0};
		int[] a=new int[80000];
		Random r=new Random();
		for(int i=0;i<a.length;i++) {
			a[i]=r.nextInt(80000);
		}
		Long l1=System.currentTimeMillis();
		System.out.println("排序前时间："+l1);
		selectionSort(a);
		Long l2=System.currentTimeMillis();
		System.out.println("排序后时间："+l2);
		System.out.println("耗时："+(l2-l1)/1000.0+"秒");
		//System.out.println(Arrays.toString(a));
	}

	public static void selectionSort(int[] data) {
		int temp=0;//记录每一趟排序的最小值
		for(int i=0;i<data.length-1;i++) {
			temp=i;
			for(int j=i;j<data.length-1;j++) {
				if(data[temp]>data[j+1]) {
					temp=j+1;
				}
			}
			if(temp!=i) {
				//如果最后得出当前这次的最小值不相等则交换值
				int s=data[temp];
				data[temp]=data[i];
				data[i]=s;
				//输出每一次排序情况
				//System.out.println(Arrays.toString(data));
			}
		}
	}
}
