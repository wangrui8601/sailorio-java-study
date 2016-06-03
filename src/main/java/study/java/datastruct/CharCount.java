package study.java.datastruct;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Comparator;
/*
 * 给出一个字符串，统计其中字符出现的次数， 并按照出现次数从大到小排序
 */
public class CharCount {
	
	public static void main(String[] args){
		String line = "hello world, I'm 王銳";
		Map<Character, Integer> map = GetCharCount(line);
		System.out.println(map);
		List<Entry<Character, Integer>> list = GetCountOrder(map);
		System.out.println(list);
	}
	
	//统计给定字符串中字符的出现次数， 保存在map中
	public static Map<Character, Integer> GetCharCount(String line){
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int index = 0; index < line.length(); ++index){
			Character c = line.charAt(index);
			if(map.containsKey(c)){
				map.put(c, map.get(c) + 1);
			} else{
				map.put(c, 1);
			}
		}
		return map;
	}
	
	//将map中的元素以Entry的类型保存在list中， 并实现一个Comparator对Entry按照value值进行排序
	public static List<Entry<Character, Integer>> GetCountOrder(Map<Character, Integer> map){
		//将map中的元素保存在list中
		List<Entry<Character, Integer>> list = new ArrayList<Entry<Character, Integer>>(map.entrySet());
		//实现Comparator, 比较Entry中的value值
		Collections.sort(list, new Comparator<Entry<Character, Integer>>(){
			public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2){
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return list;
	}

}
