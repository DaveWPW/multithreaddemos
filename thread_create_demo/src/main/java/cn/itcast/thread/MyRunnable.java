package cn.itcast.thread;

import java.util.Date;

/**
 * 自定义类实现Runnable接口
 *
 * @Author: Dave
 * @Date: 2019/12/14 12:12
 * @Description: TODO
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        for(int i=0; i<10; i++){
            System.out.println(Thread.currentThread().getName()+"执行"+i+":"+new Date().getTime());
        }
    }
}
