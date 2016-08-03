package study.java.datastruct;
import java.util.List;
import java.util.ArrayList;
/*
 * 一个数组中有10万个数值，给出10万个下标范围，要求用最快的方式求出这些范围的和
 * 
 * 思路： 首先对数组进行预处理， 遍历数组， 分别求出前i个元素的总和，
 *  		再遍历给定的下标范围， 使用sum[j]-sum[i]求得对应范围的sum值
 * 		
 */
public class RangeSum {
	static class Range{
		int start;
		int end;
		Range(int start, int end){
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args){
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		List<Range> ranges = new ArrayList<Range>();
		ranges.add(new Range(2, 5));
		ranges.add(new Range(3, 6));
		List<Integer> list = getRangeSum(array, ranges);
		System.out.println(list);
	}
	
	public static List<Integer> getRangeSum(int[] array, List<Range> ranges){
		int length = array.length;
		int[] temp = new int[length];
		
		for(int index = 0; index < length; ++index){
			if(index == 0){
				temp[0] = array[0];
			} else{
				temp[index] = temp[index - 1] + array[index];
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		for(Range range : ranges){
			int start = range.start;
			int end = range.end;
			list.add(temp[end] - temp[start - 1]);
		}
		return list;
	}
}
