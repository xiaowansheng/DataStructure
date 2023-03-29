package search;

import java.util.ArrayList;

public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] a= {1,2,3,4,5,12,15,22,45};
//		System.out.println(binarySearch(a, 3));
		int[] a= {1,2,3,3,3,4,4,5,5,12,15,22,45};
		System.out.println(binarySearch2(a, 3).toString());
//		int[] a=new int[80000];
//		for(int i=0;i<a.length;i++) {
//			a[i]=i+1;
//		}
//		Long l1=System.currentTimeMillis();
//		System.out.println("排序前时间："+l1);
//		System.out.println("找到该值的下标："+binarySearch(a, 45561));
//		Long l2=System.currentTimeMillis();
//		System.out.println("排序后时间："+l2);
//		System.out.println("耗时："+(l2-l1)/1000.0+"秒");//8000000数据用时0.46
	}

	//二分查找
	//查找某个值
	//传入的数组中的数据必须排好序
	public static int binarySearch(int[] data,int value) {
		int begin=0;
		int end=data.length-1;
		int middle=(begin+end)/2;
		while(begin<=end) {
			if(data[middle]>value) {
				end=middle-1;
			}else if(data[middle]<value) {
				begin=middle+1;
			}else {
				return middle;
			}
			middle=(begin+end)/2;
		}
		return -1;
	}
	
	//二分查找(当多个值重复)
	//查找某个值
	//传入的数组中的数据必须排好序
	public static ArrayList<Integer> binarySearch2(int[] data,int value) {
		int begin=0;
		int end=data.length-1;
		int middle=(begin+end)/2;
		while(begin<=end) {
			if(data[middle]>value) {
				end=middle-1;
			}else if(data[middle]<value) {
				begin=middle+1;
			}else {
				ArrayList<Integer> list=new ArrayList<Integer>();
				list.add(middle);
				int temp=middle-1;
				//查看左边有没有重复值,有则加入集合
				while(data[temp]==value) {
					list.add(temp);
					temp--;
				}
				temp=middle+1;
				//查看右边有没有重复值,有则加入集合
				while(data[temp]==value) {
					list.add(temp);
					temp++;
				}
				return list;
			}
			middle=(begin+end)/2;
		}
		return new ArrayList<Integer>();
	}
}
