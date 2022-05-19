package ThreadDemo;


/**
 * 主线程
 * 单线程程序:Java中只有一个线程
 * 默认从Main开始 从上到下依次执行  
 * 
 * JVM执行main方法   main方法会进入栈内存
 * JVM会找操作系统开辟一条main方法通向cpu的执行路径
 * 
 * cpu就可以通过整个路径来执行main方法  
 * 
 * 因为默认Java只有一个线程  默认名字：主线程
 * 
 * 主线程报错 程序终止 
 * @author XuYang
 *
 */
public class Test1 {
	/**
	 * 主线程
	 * 1.自上而下
	 * 2.有异常打断程序
	 * @param args
	 */

	public static void main(String[] args) {
		
		Person p=new Person("张三");
		p.run();
		int number=10/0;//Exception in thread "main"  / by zero
		//在主线程中有一个异常
		Person p1=new Person("李四");
		p1.run();
	}
}
