package ThreadDemo;

import java.util.logging.MemoryHandler;

/**
 * 多线程 就是使用 Thread类 
 * 使用方式1.创建一个子类对象
 *         2.调用start开启线程
 *         
 *         
 *         
 *         
 *线程中常用的方法
 *getname  获取当前线程名字的方法
 *currentThread 获取当前线程的
 *setName  设置线程的名字的方法(了解)
 *sleep  睡眠
 *主线程  main
 *新线程 Thread-0  Thread-1  Thread 2
 * @author XuYang
 *
 */
public class MyThreadTest2 {

	public static void main01(String[] args) {
		MyThread mt=new MyThread();
		
		mt.start();//Thread-0
		new MyThread().start();//Thread-1
		new MyThread().start();//Thread-2
		
		System.out.println(Thread.currentThread().getName()+"122");
		//
	}
	public static void main02(String[] args) {
		MyThread mt=new MyThread("1001线程");
		mt.setName("007线程");
		mt.start();
	}
	
	public static void main03(String[] args) {
		for (int i = 0; i < 60; i++) {
			System.out.println(i);//模拟秒表  
			
			//使用Thread类的sleep方法 让程序睡眠1秒钟
			try {
				Thread.sleep(1000);//睡眠1000毫秒
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main04(String[] args) {

//		Thread thread1=new MyThread();  
//		thread1.start();  
		//换一个任务  new  MyThread2()  必须创建两外一个线程
		
		
		Runnable run=new RunnableTest();//任务
		Runnable run1=new RunnableTest2();
		//创建实现类对象		
		//public Thread(Runnable target)
		Thread  thread=new Thread(run1);//1换任务
		//换一个任务  更换Thread中的参数  
		//创建线程对象
		thread.start();
//		for (int i = 0; i < 20; i++) {
//			System.out.println(Thread.currentThread().getName()+i);
//		}
	}
	public static void main05(String[] args) {
		//创建线程的方式  1.创建子类对象 2.创建实现类对象 
		
		//匿名内部类:当一个实现类或子类只使用一次的时候 
		//匿名内部类方式实现多线程
		
		//匿名:没有名字
		//内部类：写在其他类内部
		
		//匿名内部类的作用
		//1把子类继承父类 重写父类的方法 创建子类对象 一步完成
		//2把实现类实现接口 重写接口中的方法 创建实现对象 一步完成
		//匿名内部类的最终产物   子类/实现类对象  子类这个类没有名字 
		
		//格式   new  父类/接口(){
		    //重写父类/接口中的方法
	    //};
		
		//线程的父类是Thread
		//多线程第一种方式
//		Thread myThread=new MyThread();
//		myThread.start();
		
		new  Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i <20; i++) {
					System.out.println(Thread.currentThread().getName()+" "+i);
				}
			}
		}.start();//匿名对象 匿名类
		//myThread.start();
		
		new Thread(){
			@Override
			public void run() {
				
				System.out.println("执行任务");
			};
		}.start();
		
		for (int i = 0; i < 20; i++) {
			System.out.println("main "+i);
		}
	}


	public static void main06(String[] args) {
		//创建多线程第二种方式的匿名内部类实现
//		Runnable runnable=new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				for (int i = 0; i <20; i++) {
//					System.out.println(Thread.currentThread().getName()+" "+i);
//				}
//			}
//		};
//		
//		
//		new Thread(runnable).start();
		
		
		//最终版
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i <20; i++) {
					System.out.println(Thread.currentThread().getName()+" "+i);
				}
			}
		}).start();//匿名内部类和匿名对象
		

		
	}
	
	   

	//匿名内部类 当需要创建唯一一个接口或者父类的实现类或者子类的时候
	/**
	 * Thread-1--->正在卖第100张票
       Thread-2--->正在卖第100张票
	 * @param args
	 */
	
	public static void main(String[] args) {
		Runnable test3=new RunnableTest5();
		//创建实现类对象
		Thread t1=new Thread(test3);
		Thread t2=new Thread(test3);
		Thread t3=new Thread(test3);
		
		t1.start();
		t2.start();
		t3.start();
	}
}
