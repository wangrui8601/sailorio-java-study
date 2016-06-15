package study.java.basic;

public class StringDiff {
	public static void main(String[] args){
		testString();
		testBuilder();
		TestBuilder1();
	}
	
	public static void testBuilder(){
		String str = "wangrui";
		int count = 10000;
		StringBuilder result = new StringBuilder();
		long begin = System.currentTimeMillis();
		for(int i = 0; i < count; ++i){
			result.append(str);
		}
		long end = System.currentTimeMillis();
		System.out.printf("%d, %d\n", end-begin, result.length());
	}
	
	public static void TestBuilder1(){
		String str = "wangrui";
		int count = 10000;
		long begin = System.currentTimeMillis();
		String result = "";
		for(int i = 0; i < count; ++i){
			StringBuilder tmp = new StringBuilder(result);
			tmp.append(str);
			result = tmp.toString();
		}
		long end = System.currentTimeMillis();
		System.out.printf("%d, %d\n", end-begin, result.length());
	}
	
	public static void testString(){
		String str = "wangrui";
		int count = 10000;
		String result = "";
		long begin = System.currentTimeMillis();
		for(int i = 0; i < count; ++i){
			result = result + str;
		}
		long end = System.currentTimeMillis();
		System.out.printf("%d, %d\n", end-begin, result.length());
	}
}
