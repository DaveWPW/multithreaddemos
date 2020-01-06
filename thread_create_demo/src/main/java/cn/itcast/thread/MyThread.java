package cn.itcast.thread;

import java.util.Date;

/**
 * 自定义类继承Thread类
 *
 * @Author: Dave
 * @Date: 2019/12/13 17:19
 * @Description: TODO
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            System.out.println("mythread线程正在执行"+i+":"+new Date().getTime());
        }
    }

}
