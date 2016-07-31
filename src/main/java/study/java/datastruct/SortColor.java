package study.java.datastruct;

import java.util.Arrays;

/*
 * 给定一个数组，其中有三类元素：0 1 2，且乱序分布在数组中
 * 给出一个方法只遍历一次就可以将相同元素放在一起
 */
public class SortColor {
	public static void main(String[] args){
		int[] array = {0,1,2,0,2,1,1,0,2};
		System.out.println(Arrays.toString(array));
		sortColor(array);
		System.out.println(Arrays.toString(array));
		
	}
	
	public static void sortColor(int[] array){
		int cur = 0;
		int low = 0;
		int high = array.length - 1;
		while(cur <= high){
			if(array[cur] == 0){
				swap(array, cur, low);
				++cur;
				++low;
			} else{
				if(array[cur] == 1){
					++cur;
				} else{
					swap(array, cur, high);
					--high;
				}
			}
		}
	}
	
	public static void swap(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
