package cn.itcast.thread;

/**
 * 造成死锁实例
 *
 * @Author: Dave
 * @Date: 2019/12/27 15:38
 * @Description: TODO
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        //1.创建两个线程
        Thread thread1 = new Thread(new DeadLockRunnable(1), "runnbale1");
        Thread thread2 = new Thread(new DeadLockRunnable(2), "runnbale2");
        //2.运行两个线程
        thread1.start();
        thread2.start();
    }
}
