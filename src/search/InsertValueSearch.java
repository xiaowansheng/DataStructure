package search;

public class InsertValueSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data= {1,2,4,6,7,9,11,13,16,20};
		System.out.println("插值查找："+insertValueSearch(data, 7));
//		int[] data=new int[10000];
//		for(int i=0;i<data.length;i++) {
//			data[i]=i*2;
//		}
//		System.out.println(insertValueSearch(data, 1000));
	}

	//插值查找
	//二分查找优化
	//二分查找:mid=(left+right)/2=left/2+right/2=left-left/2+right/2=left+(right-left)/2=left+(right-left)*(1/2)
	//二分查找选取中间位置，插值查找则通过查找值判定大概位于序列的哪个位置比例。
	//插入查找：选择下标 = left + (right - left) * (key - arr[left])/(arr[right] - arr[left])
	public static int insertValueSearch(int[] data,int value) {
		if(value<data[0]||value>data[data.length-1]) {
			return -1;
		}
		int begin=0;
		int end=data.length-1;
		int middle=begin+(end-begin)*(value-data[begin])/(data[end]-data[begin]);
		//int middle=(begin+end)/2;
		while(begin<=end) {
			System.out.println("插值查找中······");
			if(data[middle]>value) {
				end=middle-1;
			}else if(data[middle]<value) {
				begin=middle+1;
			}else {
				return middle;
			}
			middle=begin+(end-begin)*(value-data[begin])/(data[end]-data[begin]);
			//middle=(begin+end)/2;
		}
		return -1;
	}
}
