package tree;

import java.util.Arrays;
import java.util.Random;

public class HeapSortDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = { 5, 1, 3, 7, 4, 6, 2, 9};
//		/*
//		 *        5
//		 *     1     3
//		 *  7    4  6
//		 */
//		/*
//		 *        5
//		 *     1     3
//		 *  7    4  6   2
//		  9  12 5 
//		 */
		HeapSort h = new HeapSort();
		h.heapSort(data);
//		int[] data=new int[8000000];//3.013
//		Random r=new Random();
//		for(int i=0;i<data.length;i++) {
//			data[i]=r.nextInt(80000);
//		}
//		Long l1=System.currentTimeMillis();
//		System.out.println("排序前时间："+l1);
//		int[] temp=new int[data.length];
//		h.heapSort(data);
//		Long l2=System.currentTimeMillis();
//		System.out.println("排序后时间："+l2);
//		System.out.println("耗时："+(l2-l1)/1000.0+"秒");//0.012
	}

}

//堆排序
//原理：构建堆，堆顶是最大数，最大数往后面排列，依次反复即可
class HeapSort {
	public void heapSort(int[] data) {
		//从最后一个非叶子节点(索引length/2-1)开始往上构建堆
		//因为是从下往上，从右往左构建，所以i节点之后的所有节点对应的子树都是堆
		//而只有该点i存在与子节点交换，破环了子节点的堆，才会在方法adjustHeap()中从交换的子节点开始往下重新构建堆
		//否则继续往上构建最大堆
		for (int i = data.length / 2 - 1; i >= 0; i--) {
			adjustHeap(data, i, data.length);
		}
		//在这里，整个二叉树已经构建成了最大堆
		System.out.println("第一次最大堆：" + Arrays.toString(data));
		//for循环是为了把最大堆的堆顶与堆最后的值交换(也就是构建堆，最大数往最后往前排列，交换的节点处重新构建堆，循环过程，数据就变成有序数组了)
		for (int i = data.length - 1; i > 0; i--) {//交换n-1次，最后一次,自己和自己不用交换
			//将最大堆的最大数与堆最后一个数据交换
			swop(data, i);
			//这里是堆顶与最后的数交换后
			System.out.println("第" + (data.length - i) + "交换：" + Arrays.toString(data));
			//交换完，除了最后排列好的数据，其它数据继续构建最大堆
			//每次交换后，把剩下的数继续构建堆
			adjustHeap(data, 0, i);
		}
		//反复构建堆，最大往后排列，重新构建堆往后排列
		//最终数据是有序数组
		System.out.println("堆排序后："+Arrays.toString(data));
	}

	//交换堆中最大元素(及堆顶)和数组索引index的值
	private void swop(int[] data, int index) {
		int temp = data[0];
		data[0] = data[index];
		data[index] = temp;
	}

	/**
	 * 
	 * @param data 原数组
	 * @param index 从该索引处开始，构建与该点对应的子树的堆
	 * @param length 构建的堆中数据的数量。(因为每次最大值往后排列，前面构建的堆的数据数量就-1)
	 */
	private void adjustHeap(int[] data, int index, int length) {
		//和叶子节点比较大小，大的放到父节点处
		for (int i = index,k = i; i < length; i=k) {
			//k是记录最大值的索引，初始记录i节点
			//与左节点比较，记录较大值的索引
			if (i * 2 + 1 < length && data[i] < data[i * 2 + 1]) {
				k = i * 2 + 1;
			}
			//与右节点比较，记录较大值
			if (i * 2 + 2 < length && data[k] < data[i * 2 + 2]) {
				k = i * 2 + 2;
			}
			//最大值索引与原索引比较，不同就需要交换最大值
			//如果存在数值交换，则交换后小的值对应的子树可能堆被打乱，所以继续往下构建堆
			if (k != i) {
				int temp = data[k];
				data[k] = data[i];
				data[i] = temp;
			}else {
				//如果不存在数值交换，则以下的数都已经构成堆
				break;
			}
		}
	}
}
