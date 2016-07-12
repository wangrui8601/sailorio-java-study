####首先理解产品家族和产品类型的概念：
`产品家族`：顾名思义，这些产品属于一家子的，比如都是苹果的产品（苹果手机， 苹果笔记本， 苹果相机）<br>
`产品类型`：表示这些产品是什么类型，比如苹果手机， 索尼手机和三星手机都是手机类的， 而苹果笔记本， 索尼笔记本和三星笔记本都是属于笔记本类型的。<br>
产品家族和产品类型可以用坐标图来表示,[参考](http://lavasoft.blog.51cto.com/62575/11674/)
![](https://github.com/wangrui8601/sailorio-java-study/blob/master/picture/%E4%BA%A7%E5%93%81%E5%AE%B6%E6%97%8F%E4%B8%8E%E4%BA%A7%E5%93%81%E7%B1%BB%E5%9E%8B.png)
如上图所示：
* 产品家族纵坐标轴上的（苹果，索尼和三星）就是具体的工厂类，他们继承于同一个工厂父类，其中定义了工厂的公共方法，例如工厂会生产手机，相机和笔记本；
* 产品类型横坐标轴上的（手机，相机和笔记本）就是抽象的产品类型，比如手机这个父类会派生出苹果手机，索尼手机和三星手机三个子类，在手机父类中定义了公共方法（发短信）；
* 具体的工厂类（苹果工厂）只生产属于自己家族的产品（苹果手机， 苹果笔记本和苹果相机）
详细类图如下
![](https://github.com/wangrui8601/sailorio-java-study/blob/master/picture/%E7%B1%BB%E5%9B%BE.png)

####抽象工厂模式介绍
抽象工厂模式（Factory Method Pattern）中，抽象工厂提供一系列创建多个抽象产品的接口，而具体的工厂负责实现具体的产品实例。抽象工厂模式与工厂方法模式最大的区别在于`抽象工厂中每个工厂可以创建多个种类的产品`。
####抽象工厂模式角色划分
`抽象产品`，如上文类图中的Phone, Camera, NoteBook<br>
`具体产品`，如IPhone,SNPhone,SNCamera,SMCamera,INoteBook<br>
`抽象工厂`，如Factory<br>
`具体工厂`，如FactoryApple, FactorySN, FactorySM
####抽象工厂模式使用方式
在创建具体产品时，客户端通过实例化具体的工厂类，并调用其创建目标产品的方法创建具体产品类的实例。根据依赖倒置原则，具体工厂类的实例由工厂接口引用，具体产品的实例由产品接口引用。具体调用代码如下：
```java
public class test {
	public static void main(String[] args){
		Factory factory = new FactoryApple();
		Phone phone = factory.createPhone();
		phone.sendMessage("hello world");
		
		factory = new FactorySN();
		Camera camera = factory.createCamera();
		camera.photo("sailor jupiter");
		
		factory = new FactorySM();
		NoteBook note = factory.createNoteBook();
		note.compute("distince between mars and mercury");
	}

}
```
####抽象工厂模式优点
* 因为每个具体工厂类只负责创建产品，没有简单工厂中的逻辑判断，因此符合单一职责原则。
* 与简单工厂模式不同，抽象工厂并不使用静态工厂方法，可以形成基于继承的等级结构。
* 新增一个产品家族（如上文类图中苹果家族）时，只需要增加相应的具体产品和对应的具体工厂类即可。相比于简单工厂模式需要修改判断逻辑而言，抽象工厂模式更符合开-闭原则。

####抽象工厂模式缺点
* 新增产品种类（如上文类图中的phone）时，需要修改抽象工厂及所有具体工厂，此时不符合开-闭原则。抽象工厂模式对于新的产品家族符合开-闭原则而对于新的产品种类不符合开-闭原则，这一特性也被称为开-闭原则的倾斜性。
