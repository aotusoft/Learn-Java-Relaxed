package cn.my.Dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

public class Tests {
    @Test
    public void test() {
        ApplicationContext aC = new ClassPathXmlApplicationContext("beans.xml");
        AccountService aS = aC.getBean("aSProxy", AccountService.class);
        Random r = new Random(20001008);
        int x = r.nextInt(6) + 31;
        int y = r.nextInt(6) + 41;
        int z = r.nextInt(1000);
        aS.transfer("jack", "rose", z);
        printSingleColor(x, y, z + " Paid Success");
//        System.out.println(z + "￥ Paid Success");
    }

    /**
     * @param x       颜色代号：背景颜色代号(41-46)；前景色代号(31-36)
     * @param y       数字+m：1加粗；3斜体；4下划线
     * @param content 要打印的内容
     */
    public static void printSingleColor(int x, int y, String content) {
        System.out.format("\33[%d;%dm%s%n", x, y, content);
    }
}
