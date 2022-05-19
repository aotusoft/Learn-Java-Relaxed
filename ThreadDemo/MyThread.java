package ThreadDemo;


/**
 * 多线程 
 * 多线程本身不会提高执行效率,但是可以提高程序运行效率,提高cpu的使用率
 * 
 * 创建多线程
 * 创建多线程第一种方式:
 *   Java.lang.Thread类:描述线程的类  我们想要实现多线程 必须继承Thread类
 *   
 *   实现多线程的步骤
 *   1创建一个Thread的子类  
 *   2.在Thread子类中重写Thread的run方法，设置线程任务(线程要干什么?)
 *   
 *   3.创建线程的子类对象
 *   4调用thread中start方法  开启新线程  执行run方法  
 * @author XuYang
 *
 */
public class MyThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			System.out.println("run:"+i);
		}
		String name=getName();
		
		System.out.println(name);
	}
	
	
	public MyThread(){
		
	}
	/**
	 * 给线程设置名字
	 * @param name
	 */
	public MyThread(String name){
		super(name);
	}
}
