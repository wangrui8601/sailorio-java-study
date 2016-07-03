package study.java.datastruct;

/**
 * @author wangrui
 *
 */
public class MySeqList {
	char[] array;
	final int capacity = 100;
	int length;
	
	public MySeqList(String line){
		length = line.length();
		array = new char[capacity];
		for(int index = 0; index < length; ++index){
			array[index] = line.charAt(index);
		}
	}
	
	//在指定位置插入一个元素
	public void insertIndex(int index, char ch){
		if(length == capacity){
			System.err.println("hit the cell");
			return;
		}
		if(index < 0 || index > length){
			System.err.println("out of range");
			return;
		}
		for(int i = length - 1; i >= index; --i){
			array[i + 1] = array[i];
		}
		array[index] = ch;
		length = length + 1;
	}
	
	//删除指定位置元素
	public void deleteIndex(int index){
		if(index < 0 || index > length - 1){
			System.err.println("out of range");
			return;
		}
		
		for(int i = index; i < length - 1; ++i){
			array[i] = array[i + 1];
		}
		length = length - 1;
	}
	
	//删除列表中重复的元素
	public void uniqList(){
		int i = 0;
		int j = 0;
		while(i < length - 1){
			j = i + 1;
			while(j < length){
				if(array[i] == array[j]){
					deleteIndex(j);
				}else{
					++j;
				}
			}
			++i;
		}
	}
	
	//删除顺序表中指定元素
	public void deleteItem1(char ch){
		int i = 0;
		while(i < length){
			if(array[i] == ch){
				deleteIndex(i);
			} else{
				++i;
			}
		}
	}

	//删除顺序表中指定的元素
	public void deleteItem2(char ch){
		int d = 0;
		int index = 0;
		while(index < length){
			if(array[index] == ch){
				++d;
			} else{
				if(d != 0){
					array[index - d] = array[index];
				}
			}
			++index;
		}
		length = length - d;
	}
	
	//插入排序
	public void sort(){
		char temp = '0';
		for(int i = 1; i < length; ++i){
			temp = array[i];
			int j = i - 1;
			for(; j >= 0; --j){
				if(array[j] > temp){
					array[j + 1] = array[j];
				} else{
					break;
				}
			}
			if(j != i - 1){
				array[j + 1] = temp;
			}
		}
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(int index = 0; index < length; ++index){
			builder.append(array[index]);
		}
		return builder.toString();
	}
	
	public static void main(String[] args){
		MySeqList list = new MySeqList("hello world");
		System.out.println(list);
		list.uniqList();
		System.out.println(list);
		list.insertIndex(7, 'o');
		System.out.println(list);
		list.deleteItem2('o');
		System.out.println(list);
		list.sort();
		System.out.println(list);
		
	}
}
