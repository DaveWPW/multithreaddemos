package cn.itcast.thread;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier方式
 * 实现让一组线程等待至某个状态之后再全部同时执行。
 * (3个线程都同时在等待之后再全部同时执行)
 *
 * @Author: Dave
 * @Date: 2020/1/2 14:59
 * @Description: TODO
 */
public class CyclicBarrierDemo4 {
    //参数是参与CyclicBarrier的线程数
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    public void startThread(){
        //1.打印线程准备启动
        String name = Thread.currentThread().getName();
        System.out.println(name+"正在准备...");
        //2.调用CyclicBarrier的await方法等待线程全部准备完成（在未完成之前都被阻塞）
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        //3.打印线程启动线程启动完毕信息
        System.out.println(name+"已经执行完毕！"+new Date().getTime());
    }

    public static void main(String[] args) {
        final CyclicBarrierDemo4 cyclicBarrierDemo4 = new CyclicBarrierDemo4();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                cyclicBarrierDemo4.startThread();
            }
        }, "线程1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                cyclicBarrierDemo4.startThread();
            }
        }, "线程2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                cyclicBarrierDemo4.startThread();
            }
        }, "线程3");
        t1.start();
        t2.start();
        t3.start();
    }
}
