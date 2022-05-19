package ThreadDemo;


/*线程之间的通信  无限期等待 通知结束等待
 * 创建一个顾客线程(消费者)告知老板要的包子钟类和数量 调用wait方法 进入无限期等待
 * 创建一个老板线程（生产者）:花时间做包子  做好包子之后  调用Notify方法  唤醒顾客
 * 
 * 注意:
 *   顾客和老本之间必须使用同步代码块裹起来，保证等待和唤醒只能一个在执行
 *   同步使用的锁对象  必须保证唯一 (因为两个线程共享资源)
 *   只有锁对象才能调用wait和Notify方法 
 *   
 *   
 *   Object类中的方法
 *   void  wait()
 *        在其他线程调用此对象的Notify方法或则和NotifyAll方法前 导致前线程阻塞
 *   void Notify()
 *        唤醒在此对象监视器不上的等待的单个线程
 *        
 *   void  NotifyAll
 *       唤起多个线程  
 *       
 *   
 * 
 */
public class WaitTest {

	public static void main(String[] args) {
		Object  obj=new Object();
		//创建线程内唯一锁对象
		
		//创建一个顾客线程
		
		new  Thread(){
			
			@Override
			public void run() {
				while (true) {
					
					synchronized(obj){
						System.out.println("告诉老板要的包子的数量和钟类");
						try {
							obj.wait();//没有参数的是无限期等待
							
							//obj.wait(5000);//wait可以不用无限期等待的方法
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//停止无限期等待之后的后续代码
						System.out.println(" 顾客1 包子已经做好了 开吃");
						System.out.println("=============================");
						
					}
				}
				}
				// TODO Auto-generated method stub
		}.start();
		
		//顾客2
		
		new  Thread(){
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					

				synchronized(obj){
					System.out.println("告诉老板要的包子的数量和钟类");
					try {
						obj.wait();//没有参数的是无限期等待
						
						//obj.wait(5000);//wait可以不用无限期等待的方法
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// 停止无限期等待之后的后续代码
						System.out.println("顾客2 包子已经做好了 开吃");
						System.out.println("=============================");

					}
				}
			}
		}.start();
		
		//创建一个老板的线程
		
		new  Thread(){
			
			@Override
			public void run() {
				while (true) {
					
					try {
						Thread.sleep(3000);//老板做包子花费3秒钟
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					//通知消费者做好了包子
					synchronized (obj) {
						System.out.println("包子做好了 可以来拿了");
						obj.notifyAll();//和wait调用者是同一个对象 调用Notify通知结束等待
					}
				}
			}
		}.start();
	}
	
	
}
