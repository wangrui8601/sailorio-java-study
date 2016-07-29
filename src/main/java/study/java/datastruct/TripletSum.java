package study.java.datastruct;

import java.util.Arrays;

/*
 * 这是PairSum算法的延伸
 * 从数组中找出三个数之和等于给定值的所有组合
 * 首先将数组按从小到大排序
 * 然后从下标0至length-1循环固定一个值，从当前值的下一个到最后一个元素这个区间寻找sum-固定值的所有组合
 */
public class TripletSum {
	public static void main(String[] args){
		int[] array = {2,1,3,6,5,8,9,0,4,7};
		System.out.println(Arrays.toString(array));
		printTripletSum(array, 10);
	}
	
	public static void printTripletSum(int[] array, int sum){
		Arrays.sort(array);
		int length = array.length;
		for(int index = 0; index < length - 3; ++index){
			int low = index + 1;
			int high = length -1;
			while(low < high){
				int temp = array[low] + array[high];
				if(temp == sum - array[index]){
					System.out.printf("[%s, %s, %s]", array[index], array[low], array[high]);
					--high;
					++low;
				}else{
					if(temp > sum - array[index]){
						--high;
					} else{
						++low;
					}
				}
			}
		}
	}

}
