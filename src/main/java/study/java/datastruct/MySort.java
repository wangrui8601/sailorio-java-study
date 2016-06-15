package study.java.datastruct;

public class MySort {
	
	public static void main(String[] args){
		int[] array = {9,8,7,6,5,4,3,2,1,0};
		print(array);
		insertSort(array);
		print(array);
	}
	
	public static void print(int[] array){
		int length = array.length;
		StringBuilder builder = new StringBuilder();
		for(int index = 0; index < length; ++index){
			builder.append(array[index]);
			if(index != length - 1)
				builder.append(", ");
		}
		System.out.println(builder.toString());
	}
	
	
	
	
	public static void insertSort(int[] array){
		int length = array.length;
		int temp;
		for(int i = 1; i < length; ++i){
			temp = array[i];
			int j;
			for(j = i - 1; j >= 0; --j){
				if(array[j] > temp){
					array[j + 1] = array[j];
				} else{
					break;
				}
			}
			if(j != i - 1){
				array[j+1] = temp;
			}
		}
	}
}
