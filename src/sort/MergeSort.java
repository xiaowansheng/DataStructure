package sort;

import java.util.Arrays;
import java.util.Random;

public class MergeSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a= {1,1,0,5,4,3,6,9,2,8,7,2,0,2,2,0};
		int[] temp=new int[a.length];
		mergeSort(a, 0, a.length-1, temp);
		System.out.println("排序后："+Arrays.toString(a));
//		int[] a=new int[80000];
//		Random r=new Random();
//		for(int i=0;i<a.length;i++) {
//			a[i]=r.nextInt(80000);
//		}
//		Long l1=System.currentTimeMillis();
//		System.out.println("排序前时间："+l1);
//		int[] temp=new int[a.length];
//		mergeSort(a, 0, a.length-1, temp);
//		Long l2=System.currentTimeMillis();
//		System.out.println("排序后时间："+l2);
//		System.out.println("耗时："+(l2-l1)/1000.0+"秒");//0.012
	}

	// 分+合
	public static void mergeSort(int[] data, int begin, int end, int[] temp) {
		if (begin < end) {
			int middle = (begin + end) / 2;
			mergeSort(data, begin, middle, temp);
			mergeSort(data, middle+1, end, temp);
			merger(data, begin, middle, end, temp);
		}
	}

	// 合并
	/**
	 * 
	 * @param data   原始数组
	 * @param begin  开始索引
	 * @param middle 中间索引
	 * @param end    末尾索引
	 * @param temp   中间转换数组
	 */
	public static void merger(int[] data, int begin, int middle, int end, int[] temp) {
		int left = begin;
		int right = middle + 1;
		int t = 0;// temp数组当前索引
		while (left <= middle && right <= end) {
			if (data[left] <= data[right]) {
				temp[t++] = data[left];// 把小的值放入中间数组
				left++;// 左边索引+1
			}
			// 当左边大于右边
			// 遍历右边
			else {
				temp[t++] = data[right];
				right++;
			}
		}
		// 某一边有剩余值则全部放入temp数组
		while (left <= middle) {
			temp[t++] = data[left];
			left++;
		}
		while (right <= end) {
			temp[t++] = data[right];
			right++;
		}
		// 将temp数组拷贝到data数组
		int index = 0;// 索引temp
		while (begin <= end) {
			data[begin++] = temp[index++];
		}
	}
}
