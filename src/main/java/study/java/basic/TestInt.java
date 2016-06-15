package study.java.basic;

/*
 * 很奇怪吧：这就归结于java对于Integer与int的自动装箱与拆箱的设计，是一种模式：叫享元模式（flyweight）
 * 为了加大对简单数字的重利用，java定义：在自动装箱时对于值从–128到127之间的值，它们被装箱为Integer对象后，会存在内存中被重用，始终只存在一个对象
 * 而如果超过了从–128到127之间的值，被装箱后的Integer对象并不会被重用，即相当于每次装箱时都新建一个 Integer对象；明白了吧
 * 以上的现象是由于使用了自动装箱所引起的，如果你没有使用自动装箱，而是跟一般类一样，用new来进行实例化，就会每次new就都一个新的对象；
 */
public class TestInt {
	public static void testInt() {
		Integer num1 = 297;
		Integer num2 = 297;
		System.out.println("num1==num2: " + (num1 == num2));
		System.out.println("num1 equal num2: " + (num1.equals(num2)));
		// 在-128~127 之内的数
		Integer num3 = 97;
		Integer num4 = 97;
		System.out.println("num3==num4: " + (num3 == num4));
		System.out.println("num3 equal num4: " + (num3.equals(num4)));
	}

	public static void main(String[] args) {
		testInt();
	}
}
