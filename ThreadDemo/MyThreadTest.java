package ThreadDemo;

public class MyThreadTest {

	
	int number=100;//堆
	
	public static void main(String[] args) {
		int a=1;
		MyThread mThread=new MyThread();
		//创建Thread子类对象 		
		mThread.start();//默认执行run
		
//		new MyThread().start();
//		new MyThread().start();//三个线程
		//开启新线程 执行run方法		
		for (int i = 0; i < 20; i++) {
			int number=1/0;
			System.out.println("main"+i);
		}
		
	}
}
