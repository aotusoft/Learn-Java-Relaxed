package ThreadDemo;

/**
 * 模拟窗口卖票
 * @author XuYang
 *
 */
public class RunnableTest3 implements Runnable {

    //定义一个多个线程共享的票数
	private int ticket=100;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			//任务卖票
			if (ticket>0) {//判断是否有余票
//				try {
//					Thread.sleep(3000);//判断邮票 出票需要三秒
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
				System.out.println(Thread.currentThread().getName()+"--->正在卖第"+ticket+"张票");
				ticket--;
			}			
		}
		
	}

	
}
