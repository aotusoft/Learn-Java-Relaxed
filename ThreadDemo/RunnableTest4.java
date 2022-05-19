package ThreadDemo;

import java.util.Random;

/**
 * 解决线程安全问题的第一种方案:使用同步代码块
 * 格式:
 *     synchronized(对象){
 *        可能会出现线程安全问题的代码(访问了共享数据的代码导致安全问题)
 *     }
 *     
 *     注意事项:1.通过代码块中的锁对象  可以使用任意对象 Object
 *              2 但是必须保证多个线程使用的锁对象是同一个
 *              3 锁对象的作用:
 *                把同步代码块 锁住  只让一个线程在同步代码块执行 
 * @author XuYang
 *
 */
public class RunnableTest4 implements Runnable {

	
	Object  obj=new Object();
    //定义一个多个线程共享的票数
	private int ticket=100;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			
			synchronized (obj) {
				
				//任务卖票
				if (ticket>0) {//判断是否有余票
					try {
						int [] arr={1000,2000,3000,4000};
						Thread.sleep(100);//判断邮票 出票需要三秒
						//3000  100 三个线程都在抢
						
						// cpu有关系 有睡眠的时候 抢的不明显
						//切换本身也是消耗 
						//执行一个线程
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println(Thread.currentThread().getName()+"--->正在卖第"+ticket+"张票");
					ticket--;
				}			
			}
		}
		
	}

}
