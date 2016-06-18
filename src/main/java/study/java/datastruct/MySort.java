package study.java.datastruct;

/*
 * 内排序有以下几类：
 * 	插入排序：直接插入排序， 二分法插入排序， 希尔排序
 * 	选择排序：简单选择排序， 堆排序
 * 	交换排序：冒泡排序， 快速排序
 * 	归并排序：
 * 	基数排序：
 */
public class MySort {
	
	
	public static void main(String[] args){
		int[] array = {9,8,0, 10, 1 ,7,6,5,4,3,2,1};
		print(array);
		quickSort(array);
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
 	
	/*
	 * 直接插入排序
	 * 最好的情况：原始序列是一个按值递增序列时，时间复杂度O(n)
	 * 最坏的情况：原始序列是一个按值递减序列时，时间复杂度O(n^2)
	 * 平均时间复杂度O(n^2)
	 * 整个排序过程只需要一个辅助空间temp，所以空间复杂度O(1),就地排序
	 * 属于稳定性排序方法  
	 */
	public static void straightInsertSort(int[] array){
		//获取数组长度
		int length = array.length;
		//保存待插入到有序序列的值
		int temp;
		//趟循环， 从第二个元素开始到最后一个元素为止
		for(int i = 1; i < length; ++i){
			temp = array[i];
			int j;
			//从后向前遍历待插入元素之前的有序序列
			//如果当前元素大于待插入元素， 则继续比较直到序列中的第一个元素； 否则退出循环。
			for(j = i - 1; j >= 0; --j){
				if(array[j] > temp){
					array[j + 1] = array[j];
				} else{
					break;
				}
			}
			//j==i-1说明待插入元素比有序序列中最大的元素还大，则保持其位置不变；
			//否则将待插入元素插入到对应位置
			if(j != i - 1){
				array[j+1] = temp;
			}
		}
	}
	
	/*
	 * 二分查找排序
	 */
	public static void binInsertSort(int[] array){
		//获取数组长度
		int length = array.length;
		//保存待插入到有序序列中的元素
		int temp;
		//趟循环，从第二个元素开始到最后一个元素为止， 分别插入到其之前的有序序列中
		for(int i = 1; i < length; ++i){
			temp = array[i];
			//定义有序序列二分查找的最低 最高和中间位置
			int low = 0;
			int high = i - 1;
			int mid = 0;
			//循环比较最低和最高位置， 直到最高位置比最低位置小然后退出
			while(low <= high){
				//获取当前的中间位置
				mid = (low + high)/2;
				//用当前中间位置的元素和待插入元素进行比较
				//若当前中间位置的元素大于待插入元素， 说明待插入的位置应该在low到mid-1之间， 则将high赋值成mid-1，继续上述循环
				//相反则把low值赋值成mid+1,继续上述循环
				//需要注意的一点是当前中间位置的元素等于待插入元素的情况。这时需要将low值赋值成mid+1以保证排序的稳定性
				if(array[mid] > temp){
					high = mid - 1;
				} else{
					low = mid + 1;
				}
			}
			//查找到待插入的位置，并向后移动序列
			for(int j = i - 1; j > high; --j){
				array[j + 1] = array[j];
			}
			//插入元素
			array[high + 1] = temp;
		}
	}
	
	/*
	 * 希尔排序
	 * 希尔排序的时间性能优于直接插入排序的原因 
	 * 	1.当原始序列基本有序时直接插入排序所需的比较和移动次数均较少 
	 *  2.当n值较小时，n和n^2的差别也较小，即直接插入排序的最好时间复杂度O(n)和最坏时间复杂度O(n^2)差别不大  
	 *  3.在希尔排序开始时增量d=n/2较大，每组的记录数目少，故各组内直接插入较快，后来增量d逐渐缩小，各组的记录数目逐渐增多，但是经过上次的排序，使序列接近于有序状态，所以新的一趟排序过程也较快  
	 *  希尔排序属于不稳定性排序 
	 */
	public static void shellSort(int[] array){
		int length = array.length;
		int temp = 0;
		//最外层循环， 将待排序序列分组， 步长d从length/2到1，将序列分成d个组， 然后在组内排序
		for(int d = length/2; d >= 1; d = d/2){
			//将d个组进行组内排序,使用直接插入排序
			for(int i = d; i < length; ++i){
				temp = array[i];
				int j = 0;
				for(j = i - d; j >= 0; j = j - d){
					if(array[j] > temp){
						array[j + d] = array[j];
					} else{
						break;
					}
				}
				if(j != i - 1){
					array[j + d] = temp;
				}
			}
		}
	}
	
	/*
	 * 简单选择排序
	 * 无论序列中元素的初始排列状态如何，第i趟排序要找出值最小元素都需要进行n-i次元素之间的比较，总共的比较次数为n*(n-1)/2。
	 * 这说明选择排序法所进行的元素之间的比较次数与序列的原始状态无关，可以确定算法的时间复杂度为O(n^2) 
	 * 选择排序法属于不稳定性排序方法  
	 */
	public static void selectSort(int[] array){
		//不稳定排序， 例如5 8 5 2 9
		int length = array.length;
		//循环， 在待排序的序列中找出最小元素与待排序序列的第一个元素交换， 以第一个元素的位置作为循环条件
		for(int i = 0; i < length - 1; ++i){
			//将待排序序列中的第一个元素作为当前最小元素
			int minpos = i;
			//循环比较待排序序列，与当前最小元素相比，如果比当前最小元素小，则修改最小元素的标志
			for(int j = i + 1; j < length; ++j){
				if(array[j] < array[minpos]){
					minpos = j;
				}
			}
			//与当前待排序序列中的第一个元素交换
			if(minpos != i){
				int temp = array[minpos];
				array[minpos] = array[i];
				array[i] = temp;
			}
		}
	}
	
	/*
	 * 堆排序
	 * 时间复杂度O(nlgn)
	 * 空间复杂度O(1)
	 * 属于不稳定性排序  
	 */
	//以当前节点i为根节点的子树不是堆，但是节点i的左右子树都是堆时，算法adjust把第i个节点作为根据点的子树调整为一个新的堆积
	//即完成array[i]与其左右子树根节点array[2i+1]与array[2i+2]中最大值交换位置；若交换位置以后破坏了子树的堆特性，则最对这棵子树重复交换位置的操作，
	//直到以节点i为根节点的子树成为堆
	public static void adjust(int[] array, int length, int i){
		int temp = array[i];
		int j = 2 * i + 1;
		while(j < length){
			if(j + 1 < length && array[j + 1] > array[j]){
				j = j + 1;
			}
			if(array[j] <= temp){
				break;
			}
			array[(j - 1)/2] = array[j];
			j = 2 * j + 1;
		}
		array[(j - 1)/2] = temp;
	}
	
	//堆排序的关键是：
	//1.将待排序序列构造成堆，包括如何将原始序列构造成一个初始堆
	//2.如何将移走了最大值元素以后的剩余元素组成的序列再构造成一个新的堆
	public static void heapSort(int[] array){
		int length = array.length;
		//从最右边的中间节点(最后一个节点的父节点)开始至第一个节点为止，循环调用函数adjust，将初始待排序序列调整为堆
		for(int i = (length-2)/2; i >= 0; --i){
			adjust(array, length, i);
		}
		//从序列的最后一个元素开始至第二个元素为止，循环将第一个元素与此元素交换，然后调整第一个元素至此元素之前元素成为一个堆
		for(int i = length - 1; i >= 1; --i){
			int temp = array[i];
			array[i] = array[0];
			array[0] = temp;
			
			adjust(array, i, 0);
		}
	}
	
	
	/*
	 * 冒泡排序 
	 * 最好的情况：原始序列是一个按值递增序列时，时间复杂度O(n)
	 * 最坏的情况：原始序列是一个按值递减序列时，时间复杂度O(n^2)
	 * 平均时间复杂度O(n^2)
	 * 空间复杂度O(1),就地排序  
	 * 属于稳定性排序方法  
	 */
	//每趟排序都从第一个元素开始一次比较相邻的两个元素， 若两个元素位置与其顺序相反则交换它们的位置，
	//这样一趟排序之后最大的元素会被移到待排序的最后，
	//第一趟排序从序列的第一个元素开始至第length-1个元素依次与之后面相邻的元素比较， 这样最大的元素被放在第length的位置
	//第二趟排序从序列的第一个元素开始至第length-2个元素···
	//设置一个交换标志位，若一趟排序中未发生交换动作，说明序列已经有序，可以减少比较的次数
	public static void bubbleSort(int[] array){
		int length = array.length;
		boolean swap = false;
		int count = 0;
		for(int i = length - 2; i >= 0; --i){
			++count;
			for(int j = 0; j <= i; ++j){
				if(array[j] > array[j + 1]){
					swap = true;
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
			if(!swap)
				break;
			swap = true;
		}
		System.out.println(count);
	}
	
	/*
	 * 快速排序
	 * 最好的情况：每趟排序后，分界元素正好定位在序列的中间，从而把当前参加排序的序列分成大小相等的前后两个子序列，每次划分比较次数不大于n，需要经过lgn次的划分，所以最好的情况下，时间复杂度为O(nlgn)  
	 * 最坏的情况：原始序列在有序的情况下，快速排序的方法用时最长，此时，第一趟排序经过n-1次比较，将第一个元素仍确定在原来的位置上，并得到一个长度为n-1的子序列；第二趟排序经过n-2次比较，将第2个元素确定在它原来的位置上，又得到一个长度为n-2的子序列；以此类推，其最坏的i时间复杂度为O(n^2)  
	 * 平均时间复杂度为O(nlgn)  
	 * 空间复杂度：快速排序在系统内部需要一个栈来实现递归。若每次划分较为均匀，则其递归树的高度为O(lgn)，故递归后需栈空间为O(lgn)。最坏情况下，递归树的高度为O(n)，所需的栈空间为O(n)。  
	 * 属性不稳定性排序方法  
	 */
	//1.在待排序序列中选择第一个元素作为当前元素， 然后将剩余元素从两端开始向中间聚拢，依次与当前元素比较
	//2.先从高位向低位移动，找到小于当前元素的元素，将其放到低位位置
	//3.再从低位向高位移动，找到大于当前元素的元素，将其放到高位位置
	//4.重复上述步骤2和3，一直到高位与低位相等，说明这个位置的左边序列小于当前元素， 而右边的序列都大于当前元素，将当前元素放到这个位置上，并返回位置
	public static int getSite(int[] array, int low, int high){
		int l = low;
		int h = high;
		int temp = array[l];
		while(l != h){
			while(l != h && array[h] >= temp){
				--h;
			}
			if(l != h){
				array[l] = array[h];
				++l;
			}
			
			while(l != h && array[l] <= temp){
				++l;
			} 
			if(l != h){
				array[h] = array[l];
				--h;
			}
		}
		array[l] = temp;
		return l;
	}
	
	//递归，如果低位小于高位，则调用上述getSite方法，找到一个合适的位置，使得这个位置上的元素大于它左边的序列小于右边的序列
	//以这个位置为分割点，依次将左序列和右序列排序
	public static void quick(int[] array, int low, int high){
		if(low < high){
			int index = getSite(array, low, high);
			quick(array, low, index - 1);
			quick(array, index + 1, high);
		}
	}
	
	public static void quickSort(int[] array){
		int length = array.length;
		quick(array, 0, length - 1);
	}
}
