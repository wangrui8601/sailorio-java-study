package study.java.datastruct;
/*
 * 提供两个函数：
 * 	给定一个IP地址，将其转换为整数
 * 		将IP地址按照'.'分割成四个元素，最高位元素乘以2^24(相当于将二进制数左移24位)，
 * 		第二高位元素乘以2^16(相当于将二进制数左移16位)
 * 		第三高位元素乘以2^8(相当于将二进制数左移8位)
 * 		最低位元素不变，将上述移位后的数值相加
 * 		因为java中没有无符号int型，所以使用long保存数值
 * 
 * 给定一个长整数，将其转换为IP
 * 		将此长整数			右移24位，并转为字符串
 * 		将此长整数的高8位置零，然后右移16位，并转为字符串
 * 		将此长整数的高16位置零，然后右移8位，并转为字符串
 * 		将此长整数的高24位置零，		  并转为字符串
 * 		使用StringBuilder将上述字符串连接
 */
public class IPManager {
	public static void main(String[] args){
		
		System.out.println(IPToLong("255.255.255.255"));
		
		System.out.println(LongToIP(4294967294L));
	}
	public static long IPToLong(String ip){
		String[] strs = ip.split("\\.");
		long v1 = Integer.parseInt(strs[0]);
		long v2 = Integer.parseInt(strs[1]);
		long v3 = Integer.parseInt(strs[2]);
		long v4 = Integer.parseInt(strs[3]);
		long ret = (long)((v1<<24) + (v2<<16) + (v3<<8) + (v4));
		//long ret = (long) (v1 * Math.pow(2, 24) + v2 * Math.pow(2, 16) + v3 * Math.pow(2, 8) + v4);
		return ret;
	}
	
	public static String LongToIP(long val){
		StringBuilder builder = new StringBuilder();
		builder.append(String.valueOf(val>>>24)).append(".");
		builder.append(String.valueOf((val&0x00FFFFFF)>>>16)).append(".");
		builder.append(String.valueOf((val&0x0000FFFF)>>>8)).append(".");
		builder.append(String.valueOf(val&0x000000FF));
		return builder.toString();
	}

}
