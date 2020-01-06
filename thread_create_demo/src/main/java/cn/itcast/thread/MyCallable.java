package cn.itcast.thread;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * 自定义类实现Callable接口
 *
 * 1.Callable接口需要知道“泛型”返回值
 * 2.重写call()方法
 * @Author: Dave
 * @Date: 2019/12/14 12:31
 * @Description: TODO
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i=0; i<10; i++){
            System.out.println(Thread.currentThread().getName()+"执行"+i+":"+new Date().getTime());
        }
        return "MyCallable执行完毕！";
    }
}
