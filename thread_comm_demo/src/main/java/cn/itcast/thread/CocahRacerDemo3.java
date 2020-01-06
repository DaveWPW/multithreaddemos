package cn.itcast.thread;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch这个类能够使一个线程等待其他线程完成各自的工作后再执行。
 *
 * 教练和运动员集合案例
 *
 * @Author: Dave
 * @Date: 2020/1/1 21:47
 * @Description: TODO
 */
public class CocahRacerDemo3 {
    //设置要等待的运动员的数量3个
    private CountDownLatch countDownLatch = new CountDownLatch(3);
    /**
     * 运动员方法，由运动员线程调用
     */
    public void racer(){
        //1.获取运动员名称（线程名）
        String threadName = Thread.currentThread().getName();
        //2.运动员开始准备（打印准备信息）
        System.out.println(threadName+"正在准备...");
        //3.运动员在准备（线程睡眠1000毫秒（1秒））
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //4.运动员准备完毕（打印准备完毕信息）
        System.out.println(threadName+"准备完毕！");
        //进行计算操作（计数-1）
        countDownLatch.countDown();
    }

    /**
     * 教练方法，由教练线程调用
     */
    public void cocah(){
        //1.获取教练名称（线程名）
        String threadName = Thread.currentThread().getName();
        //2.教练等待所有的运动员准备完毕（打印等待信息）
        System.out.println(threadName+"等待运动员准备完毕...");
        //3.调用CountDownLatch的await方法等待其它线程执行完毕（在其它线程未执行完毕之前都被阻塞）
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //4.所有运动员已准备就绪，教练开始训练（打印训练信息）
        System.out.println(threadName+"开始训练运动员！");
    }

    public static void main(String[] args) {
        //创建CocahRacerDemo3实例
        final CocahRacerDemo3 cocahRacerDemo3 = new CocahRacerDemo3();
        //创建一个线程对象，调用CocahRacerDemo3的cocah方法（教练）
        Thread TA = new Thread(new Runnable() {
            @Override
            public void run() {
                cocahRacerDemo3.cocah();
            }
        }, "教练");
        //创建三个线程对象，调用CocahRacerDemo3的racer方法（运动员）
        Thread T1 = new Thread(new Runnable() {
            @Override
            public void run() {
                cocahRacerDemo3.racer();
            }
        }, "运动员1");
        Thread T2 = new Thread(new Runnable() {
            @Override
            public void run() {
                cocahRacerDemo3.racer();
            }
        }, "运动员2");
        Thread T3 = new Thread(new Runnable() {
            @Override
            public void run() {
                cocahRacerDemo3.racer();
            }
        }, "运动员3");
        TA.start();
        T1.start();
        T2.start();
        T3.start();
    }
}
