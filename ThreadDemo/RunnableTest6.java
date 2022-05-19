package ThreadDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全的第三种方案  加锁
 * lock
 * 
 * java.util.concurrent.locks.Lock接口
 * Lock 实现提供了比使用 synchronized 方法和语句可获得的更广泛的锁定操作。此实现允许更灵活的结构，
 * 可以具有差别很大的属性，可以支持多个相关的 Condition 对象。 
 * 
 * Lock接口中的方法:
 *    void  lock  获取锁
 *    void unlock  释放锁 
 *   
 *   使用步骤1.通过实现类  创建接口对象  在成员位置创建一个  ReentrantLock对象(Lock的是实现类)
 *           2.在可能出现线程安全的代码前调用lock接口中lock获取锁
 *           3在可能出现线程安全的代码后调用lock接口中unlock释放锁
 * @author XuYang
 *
 */
public class RunnableTest6 implements Runnable {

	//定义一个多个线程共享的票数
		private int ticket=100;
		
		Lock lockT=new ReentrantLock();//创建是实现类对象
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				//安全前
				lockT.lock();
				//任务卖票
				if (ticket>0) {//判断是否有余票
					try {
						//Thread.sleep(3000);//判断邮票 出票需要三秒
						
						System.out.println(Thread.currentThread().getName()+"--->正在卖第"+ticket+"张票");
						ticket--;
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					finally {
						
						
						//安全后
						lockT.unlock();//释放锁/资源 写在finally中
					}
				}	
			}
			
		}
}
