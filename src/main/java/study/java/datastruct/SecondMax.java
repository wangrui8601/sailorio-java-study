package study.java.datastruct;

public class SecondMax {
	public static void main(String[] args){
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		System.out.println(getSecondMax(array));
	}
	
	public static int getSecondMax(int[] array){
		int length = array.length;
		int max = 0;
		int max_sec = 0;
		if(array[0] > array[1]){
			max_sec = 1;
			max = 0;
		} else{
			max_sec = 0;
			max = 1;
		}
		
		for(int index = 2; index < length; ++index){
			if(array[index] > array[max]){
				max_sec = max;
				max = index;
			} else{
				if(array[index] > array[max_sec])
				{
					max_sec = index;
				}
			}
		}
		return array[max_sec];
	}
}
