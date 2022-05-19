package ThreadDemo;

/**
 * 创建线程的第二种方式:实习Runnable接口
 * Runnable 接口应该由那些打算通过某一线程执行其实例的类来实现。
 * 类必须定义一个称为 run 的无参数方法。 
 * Java.lang.Runnable
 * 
 * 实现步骤:
 * 1.创建一个runnable的实现类
 * 2.在实现类中重写runnable的run方法  设置线程任何
 * 3.创建一个Runnable的实现类对象  
 * 4.创建Thread对象  构造方法中传递runnable接口实现类对象
 * 5.调用Thread方法的start  开启新线程执行run方法
 * 
 * 好处:
 *     1.避免了单继承的局限性    Thread是类  Runnable是接口
 *       一个类只能继承一个类  类继承了Thread就不能继承其他的类 
 *       
 *     2.增强了程序的拓展性 降低了程序的耦合性(解耦)
 *       实现Runnable接口的方式  把设置线程任务  和开启线程进行了分离 
 *       由继承关系 变成了组合关系  所以降低了耦合度
 *
 * @author XuYang
 *
 */
public class RunnableTest implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//设置现场任务
		for (int i = 0; i < 20; i++) {
			System.out.println(Thread.currentThread().getName()+i);
		}
		
	}

	
}
