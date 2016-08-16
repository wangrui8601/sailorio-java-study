package study.java.datastruct;

import java.util.Arrays;

/*
 * 给定一个数组有n个元素，求其中最小的k个元素
 * 
 * 	思路1：
 * 		从数组中取出前k个元素并假设是最小的k个元素
 * 		找出k个元素中最大的元素vmax
 * 		依次遍历数组中剩余的n-k个元素，并与vmax比较，若小于vmax则将元素代替vmax重新找出最大的vmax；
 * 											若大于vmax直接比较下一个元素
 * 
 * 	思路2：方法与思路1相似，不同在于使用大顶堆来记录k个元素中的最大值，
 * 						并依次与剩余的n-k个元素比较，符合条件则交换后重新调整大顶堆
 * 		
 * 		 
 * 
 */
public class MinValues {
	
	public static void main(String[] args){
		int[] array = {6,5,7,4,8,9,0,3,2,1,10};
		System.out.println(Arrays.toString(array));
		getMinValues2(array, 5);
		System.out.println(Arrays.toString(array));
		getMinValues1(array, 5);
		System.out.println(Arrays.toString(array));
	}
	
	public static void getMinValues2(int[] array, int k){
		//将数组中前k个元素调整成大顶堆
		for(int index = (k - 2) / 2; index >= 0; --index){
			adjust(array, index, k - 1);
		}
		
		int length = array.length;
		boolean  bAdjust = false;
		
		//遍历数组中后面length-k个元素
		for(int indexI = k; indexI < length; ++indexI){
			if(bAdjust){
				adjust(array, 0, k - 1);
			}
			if(array[indexI] < array[0]){
				int temp = array[0];
				array[0] = array[indexI];
				array[indexI] = temp;
				bAdjust = true;
			} else{
				bAdjust = false;
			}
		}
	}
	
	//在数组array中从元素下标start开始到下标end为止调整为大顶堆，
	//下标start到end的元素中除了根节点之外其余的左右子树都符合大顶堆
	public static void adjust(int[] array, int start, int end){
		int temp = array[start];
		int j = 2 * start + 1;
		while(j <= end){
			if(j + 1 <= end && array[j + 1] > array[j]){
				j = j + 1;
			}
			if(array[j] < temp){
				break;
			}
			array[(j - 1) / 2] = array[j];
			j = 2 * j + 1;
		}
		array[(j - 1) / 2] = temp;
	}
	
	public static void getMinValues1(int[] array, int k){
		int length = array.length;
		//遍历数组中后面length-k个元素
		int maxIndex = 0;
		
		boolean freshMaxIndex = true;
		
		for(int indexI = k; indexI < length; ++indexI){
			//在k个元素中找出最大的值
			if(freshMaxIndex){
				for(int indexJ = 0; indexJ < k; ++indexJ){
					if(array[indexJ] > array[maxIndex]){
						maxIndex = indexJ;
					}
				}
			}
			
			if(array[indexI] < array[maxIndex]){
				int temp = array[indexI];
				array[indexI] = array[maxIndex];
				array[maxIndex] = temp;
				freshMaxIndex = true;
			} else{
				freshMaxIndex = false;
			}
		}
	}
}
