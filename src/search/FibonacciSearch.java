package search;

import java.util.Arrays;

public class FibonacciSearch {

	public static int maxSize=30;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(fibSequence()));
		int[] data={1,3,4,5,6,7,12,45,68,95,110,125};
		System.out.println("斐波那契查找："+fibSearch(data, 125));
	}

	//斐波那契查找算法
	public static int fibSearch(int[] data,int key) {
		int[] f=fibSequence();
		int k=0;
		while(f[k]<data.length) {
			k++;
		}
		System.out.println(k);
		//把原数组扩充成黄金分割比例的个数
		//mid分割值就是黄金分割比例位置，分割后，前后的数据个数比为0.618
		int[] temp=new int[f[k]];
		for(int i=0;i<data.length;i++) {
			temp[i]=data[i];
		}
		if(f[k]>data.length) {
			for(int i=data.length;i<f[k];i++) {
				temp[i]=data[data.length-1];
			}
		}
		System.out.println(Arrays.toString(temp));
		int begin=0;
		int end=data.length-1;
		int mid=0;
		while(begin<=end) {
			mid=begin+f[k-1]-1;
			if(key<temp[mid]) {
				end=mid-1;
				k=k-1;
				//为甚是 k--
				//说明
				//1. 全部元素 = 前面的元素 + 后边元素
				//2. f[k] = f[k-1] + f[k-2]
				//因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
				//即 在 f[k-1] 的前面继续查找 k--
				//即下次循环 mid = f[k-1-1]-1
			}else if(key>temp[mid]) {
				begin=mid+1;
				k-=2;
				//为什么是k -=2
				//说明
				//1. 全部元素 = 前面的元素 + 后边元素
				//2. f[k] = f[k-1] + f[k-2]
				//3. 因为后面我们有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
				//4. 即在f[k-2] 的前面进行查找 k -=2
				//5. 即下次循环 mid = f[k - 1 - 2] - 1
			}else {
				if(mid<=end) {
					return mid;
				}else {
					//因为临时数组有末尾填充值，所以查找到的 mid可能是填充的
					//但是填充的和原数组末尾值相同
					//所以返回原数组末尾索引
					return end;
				}
			}
		}
		return -1;
	}
	
	//斐波那契数列
	public static int[] fibSequence() {
		int[] f=new int[maxSize];
		f[0]=1;
		f[1]=1;
		for(int i=2;i<f.length;i++) {
			f[i]=f[i-1]+f[i-2];
		}
		return f;
	}
}
