package search;

public class LinearSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a= {1,54,12,36,45,22,14,25,36,11,5,6,8,2};
		int index=linearSearch(a, 22);
		if(index>=0) {
			System.out.println("该值的下表为："+index);
		}else {
			System.out.println("该值未找到。");
		}
	}

	//查找某个值，返回第一次找到的位置下标
	public static int linearSearch(int[] data,int value) {
		for(int i=0;i<data.length;i++) {
			if(data[i]==value) {
				return i;
			}
		}
		return -1;
	}
}
