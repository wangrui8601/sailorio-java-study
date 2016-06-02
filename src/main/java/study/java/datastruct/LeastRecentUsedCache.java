package study.java.datastruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/*
 * 实现LeastRecentUsedCache， 提供如下功能
 * 1.插入数据(key, value): 
 * 		如果当前Cache存在待插入的关键字
 * 			则更新数据并将此条数据作为最新访问的数据
 * 		否则
 * 			如果当前Cache已满
 * 				删除最早访问的数据， 插入数据并将此条数据作为最新访问的数据，然后更新map
 * 			否则
 * 				直接插入数据并将此条数据作为最新访问的数据， 然后更新map和size
 * 		
 * 2.获取数据(key):
 *		如果当前Cache存在待查询的数据
 *			返回对应的value值， 并将此条数据作为最新访问的数据
 *		否则
 *			返回null
 *
 * 基于双向链表实现上述要求，使用节点的位置来表示数据被访问的先后次序
 * 另外使用map保存key和数据所在结点的引用， 这样查找数据时可以节省遍历链表的时间
 */

//链表中的节点
class DataNode{
	Integer key_; //保存key
	Integer value_; //保存value
	DataNode pre_node_; //保存链表中下一个节点的引用
	DataNode next_node_; //保存链表中上一个节点的引用
}	

public class LeastRecentUsedCache {
	DataNode head_; //保存头结点
	DataNode tail_; //保存尾结点
	Integer capacity_; //保存该链表容量, 默认是10
	Integer size_; //保存该链表当前的结点个数
	Map<Integer, DataNode> site_map_; //保存关键字和存储对应数据结点引用的映射
	
	//构造函数
	public LeastRecentUsedCache(){
		this(10);
	}
	
	public LeastRecentUsedCache(Integer capacity){
		this.head_ = null;
		this.tail_ = null;
		this.capacity_ = capacity;
		this.size_ = 0;
		this.site_map_ = new HashMap<Integer, DataNode>();
	}
	
	//获取数据
	public Integer Get(Integer key){
		if(this.site_map_.containsKey(key)){
			//如果当前Cache存在待查询的数据，返回对应的value值， 并将此条数据作为最新访问的数据
			DataNode node = this.site_map_.get(key);
			Integer value = node.value_;
			this.delete(node);
			this.insert_tail(node);
			return value;
		}else{
			//否则返回null
			return null;
		}
	}
	
	//插入数据
	public void Insert(Integer key, Integer value){
		if(this.site_map_.containsKey(key)){
			//如果当前Cache存在待插入的关键字,则更新数据并将此条数据作为最新访问的数据
			DataNode node = this.site_map_.get(key);
			node.value_ = value;
			this.delete(node);
			this.insert_tail(node);
		} else{
			//否则
			if(this.size_ == this.capacity_){
				//如果当前Cache已满，删除最早访问的数据， 插入数据并将此条数据作为最新访问的数据， 然后更新map
				DataNode del_node = this.head_;
				this.delete(del_node);
				DataNode node = new DataNode();
				node.key_ = key;
				node.value_ = value;
				this.insert_tail(node);
				this.site_map_.remove(del_node.key_);
				this.site_map_.put(key, node);
			} else{
				//否则，直接插入数据并将此条数据作为最新访问的数据， 然后更新map和size
				DataNode node = new DataNode();
				node.key_ = key;
				node.value_ = value;
				this.insert_tail(node);
				this.site_map_.put(key, node);
				this.size_ = this.size_ + 1;
			}
		}
	}
	
	//判断当前Cache是否为空
	public boolean IsEmpty(){
		if(this.head_ == null && this.tail_ == null){
			return true;
		}
		return false;
	}
	
	//打印当前Cache的元素
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		DataNode node = this.head_;
		while(node != null){
			builder.append(node.key_).append("=").append(node.value_).append(", ");
			node = node.next_node_;
		}
		
		return builder.toString();
	}
	
	
	//将一个节点插入到cache的末尾
	private void insert_tail(DataNode node){
		if(this.IsEmpty()){
			//当前cache为空
			this.head_ = node;
			this.tail_ = node;
		} else{
			this.tail_.next_node_ = node;
			node.pre_node_ = this.tail_;
			this.tail_ = node;
		}
	}
	
	//将一个节点从cache中删除
	private void delete(DataNode node){
		if(node.pre_node_ == null){
			//如果指定删除的节点为头结点
			DataNode next_node = node.next_node_;
			next_node.pre_node_ = null;
			this.head_ = next_node;
			node.next_node_ = null;
		} else{
			if(node.next_node_ == null){
				//如果指定删除的节点为尾节点
				DataNode pre_node = node.pre_node_;
				pre_node.next_node_ = null;
				this.tail_ = pre_node;
				node.pre_node_ = null;
			} else{
				//正常节点
				DataNode pre_node = node.pre_node_;
				DataNode next_node = node.next_node_;
				pre_node.next_node_ = next_node;
				next_node.pre_node_ = pre_node;
				node.pre_node_ = null;
				node.next_node_ = null;
			}
		}
	}
	
	public static void main(String[] args){
		LeastRecentUsedCache cache = new LeastRecentUsedCache();
		
		System.out.println(cache);
		cache.Insert(1, 10);
		cache.Insert(2, 20);
		cache.Insert(3, 30);
		System.out.println(cache);//1,2,3
		cache.Insert(1, 5);
		cache.Insert(4, 40);
		System.out.println(cache.Get(0));//null
		System.out.println(cache);//2,3,1,4
		System.out.println(cache.Get(2));
		System.out.println(cache);//3,1,4,2
		cache.Insert(5, 50);
		cache.Insert(6, 60);
		cache.Insert(7, 70);
		cache.Insert(8, 80);
		cache.Insert(9, 90);
		cache.Insert(0, 0);
		System.out.println(cache.Get(0));//0
		System.out.println(cache.Get(3));//30
		System.out.println(cache);//1,4,2,5,6,7,8,9,0,3
		cache.Insert(11, 110);
		System.out.println(cache);//4,2,5,6,7,8,9,0,3,11
	
		System.out.println(GetKeyCount("hello world"));
		System.out.println(GetOrder(GetKeyCount("hello world")));
	}
	
	public static Map<Character, Integer> GetKeyCount(String line) {
		Map<Character, Integer> key_count = new HashMap<Character, Integer>();
		int size = line.length();
		for (int index = 0; index < size; ++index) {
			char ch = line.charAt(index);
			if (key_count.containsKey(ch)) {
				Integer count = key_count.get(ch);
				key_count.put(ch, count + 1);
			} else {
				key_count.put(ch, 1);
			}
		}
		return key_count;
	}
	
	
	public static List<Entry<Character, Integer>> GetOrder(
			Map<Character, Integer> map) {
		List<Entry<Character, Integer>> list = new ArrayList<Entry<Character, Integer>>(
				map.entrySet());
		Collections.sort(list, new Comparator<Entry<Character, Integer>>() {
			public int compare(Entry<Character, Integer> arg0,
					Entry<Character, Integer> arg1) {
				return arg1.getValue().compareTo(arg0.getValue());
			}
		});
		return list;
	}
	
}
