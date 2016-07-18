package study.java.basic;

class User implements Cloneable{
	String name;
	int age;
	public User(String name, int age){
		System.out.printf("name=%s, age=%d\n", this.name, this.age);
		this.name = name;
		this.age = age;
	}
	
	public Object clone() throws CloneNotSupportedException{
		User user = null;
		user = (User)super.clone();
		if(name != null){
			user.name = new String(name);
		}
		return user;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(name).append(",").append(age);
		return builder.toString();
	}
}

class Account implements Cloneable{
	User user = new User("wangrui", 30);
	long balance;
	
	public Account(String name, int age, long bal){
		System.out.println("user " + user + "balance " + balance);
		this.user = new User(name, age);
		this.balance = bal;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Account account = null;
		account = (Account)super.clone();
		if(user != null){
			account.user = (User)user.clone();
		}
		return account;
	}
}

public class TestClone {
	public static void main(String[] args) throws CloneNotSupportedException{
		Account account = new Account("wangrui", 30, 1000);
		Account a1 = (Account)account.clone();
		System.out.println("over");
	}
}
