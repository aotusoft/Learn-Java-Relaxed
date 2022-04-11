package com.my.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Test;

public class DaoAspect {
    //前置通知
    public void before(JoinPoint jP) {
        System.out.print("\033[32m" + "前置通知 目标：");
        System.out.println(jP.getTarget());
        System.out.print("\033[32m" + "方法名称");
        System.out.println(jP.getSignature().getName());
    }

    //后置通知
    public void after(JoinPoint jP) {
        System.out.print("后置通知 目标：");
        System.out.println(jP.getTarget());
        System.out.print("方法名称");
        System.out.println(jP.getSignature().getName());
    }

    /*环绕通知
     * 必须返回Object类型的值
     * 必须接受参数 ProceedingJoinPoint
     * 必须抛出异常
     * */
    public Object around(ProceedingJoinPoint pJP) throws Throwable {
        System.out.print("\033[32m" + "环绕开始");
        Object obj = pJP.proceed();
        System.out.print("\033[32m" + "环绕开始");
        return obj;
    }
    //异常通知
    public void throwing(JoinPoint jP, Throwable e) {
        System.out.print("\033[32m" + "异常：" + e.getMessage());
    }

    //最终通知
    public void end(JoinPoint jP, Throwable e) {
        System.out.print("\033[32m" + "最终通知");
    }
}
