package sort;

import java.util.Arrays;
import java.util.Random;

public class RadixSort {

	public static void main(String[] args) {
		int[] a= {123,154,66,245,12,57,88,1,1,0};
		radixSort(a);
		System.out.println("基数排序后："+Arrays.toString(a));
//		int[] a=new int[8000000];
//		Random r=new Random();
//		for(int i=0;i<a.length;i++) {
//			a[i]=r.nextInt(80000);
//		}
//		Long l1=System.currentTimeMillis();
//		System.out.println("排序前时间："+l1);
//		int[] temp=new int[a.length];
//		radixSort(a);
//		Long l2=System.currentTimeMillis();
//		System.out.println("排序后时间："+l2);
//		System.out.println("耗时："+(l2-l1)/1000.0+"秒");//8000000数据用时0.46
	}

	//基数排序
	public static void radixSort(int[] data) {
		int max=0;//获取数组中最大值
		for(int i=0;i<data.length;i++) {
			if(data[i]>max) {
				max=data[i];
			}
		}
		int maxLength=(max+"").length();//获取最大值位数
		int[][] bucket=new int[10][data.length];//装临时数据的桶
		int[] bucketLength=new int[10];//每个桶的数据长度
		int record=0;//记录数据的位数
		int index=0;//用来遍历原数组
		for(int i=0,n=1;i<maxLength;i++,n*=10) {//数据最大值几位数就进行几次排序
			//把数据某位大小放入相应大小的桶中
			for(int j=0;j<data.length;j++) {
				record=data[j]/n%10;//求比较位数的值
				bucket[record][bucketLength[record]]=data[j];
				bucketLength[record]++;//放入一个数，桶长+1
			}
			//按顺序把桶中数据放入原数组
			for(int j=0;j<bucketLength.length;j++) {
				if(bucketLength[j]>0) {
					for(int k=0;k<bucketLength[j];k++) {
						data[index]=bucket[j][k];
						index++;
					}
					bucketLength[j]=0;//某个桶内数据全部放回时，桶长归0
				}
			}
			index=0;//桶内放回原数组时，遍历长度归0
		}
	}
}
