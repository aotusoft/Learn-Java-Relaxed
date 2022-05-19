package ThreadDemo;

/**
 * 解决线程安全问题的第二种方案;同步方法
 * 同步方法语法:
 * public synchronized  void  payTicket()
 * @author XuYang
 *
 */
public class RunnableTest5 implements Runnable {

	// 定义一个多个线程共享的票数
	private static int ticket = 100;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			//买票封装到一个 synchronized  修饰的方法中
			pay();
		}

	}
	
	public  synchronized void  payTicket(){
		//锁对象
//		synchronized(this){			一个原理
			
			if (ticket > 0) {// 判断是否有余票
				try {
					// Thread.sleep(3000);// 判断邮票 出票需要三秒
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "--->正在卖第" + ticket + "张票");
				ticket--;
			}
		//}
		// 任务卖票
	}
	
	
	//连接
	public static synchronized  void  pay(){
		synchronized (RunnableTest5.class) {//锁自己的类
			// 任务卖票
			if (ticket > 0) {// 判断是否有余票
				try {
					// Thread.sleep(3000);// 判断邮票 出票需要三秒
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "--->正在卖第" + ticket + "张票");
				ticket--;
			}
			
		}
	}
	
}
