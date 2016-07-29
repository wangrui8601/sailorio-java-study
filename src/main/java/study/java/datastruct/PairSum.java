package study.java.datastruct;

import java.util.Arrays;

/*
 * 给定一个整数数组，找出其中两个元素其和等于给定的整数sum，打印出这样的组合
 * 思路1： 首先将数组中的元素以及其在数组中的下标存放在map中，然后遍历给定数组，对数组中的每一个元素，在map
 * 			中查找sum-array[i]的元素
 * 思路2： 首先将数组中的元素按从小到大排序，然后遍历数组中的元素， 从这个元素的后面一个元素到最后一个元素
 * 			使用二分查找算法查找元素等于sum-array[i]的元素
 * 思路3： 首先将数组中的元素按从小到大排序，然后分别使用指针low指向数组的最低位置（下标为0）和指针high指向
 * 			数组的最高位置（下标为length-1）,在满足low<high的情况下循环，并按照条件做以下操作：
 * 			如果array[low]+array[high]==sum, 将high=high-1,low=low+1;
 * 			如果array[low]+array[high]>sum, 将high=high-1;
 * 			如果array[low]+array[high]<sum, 将low=low+1;
 * 		
 */
public class PairSum {
	public static void main(String[] args){
		int[] array = {2,1,3,6,5,8,9,0,4,7};
		System.out.println(Arrays.toString(array));
		printPairSum(array, 10);
	}
	
	public static void printPairSum(int[] array, int sum){
		Arrays.sort(array);
		int low = 0;
		int high = array.length - 1;
		while(low < high){
			int temp = array[low] + array[high];
			if(temp == sum){
				System.out.printf("[%s, %s]", array[low], array[high]);
				++low;
				--high;
			}else{
				if(temp > sum){
					--high;
				} else{
					++low;
				}
			}
		}
	}

}
