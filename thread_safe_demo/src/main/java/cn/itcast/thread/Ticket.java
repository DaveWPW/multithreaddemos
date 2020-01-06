package cn.itcast.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Dave
 * @Date: 2019/12/20 17:21
 * @Description: TODO
 */
public class Ticket implements Runnable {
    //全局变量（存在线程不安全）
    private int ticktNum = 100;

    //1.同步代码块, 定义锁对象（只有拿到这个对象的线程才能执行以下代码, 同时只能一个线程可以获取）
//    private Object obj = new Object();

    //3.同步锁, （Lock接口）
    // 参数是否是公平锁：
    // true 公平锁, 多个线程都公平拥有执行权（每个线程都会公平的获取锁执行）；
    // 默认 false 非公平锁（独占锁）, 只有一个线程可以获取锁, 如果不主动释放或没有执行完毕会永远使用这个锁；
    // ReentrantLock（重入锁）当用该锁时再去请求这个锁, 任然可以拿个该锁
    private Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while(true){
            //1.同步代码块（synchronized代表锁, 代表obj钥匙）
//            synchronized (obj){
//                if(ticktNum > 0){
//                    //1.模拟出票时间
//                    try {
//                        //因sleep()方法阻塞没有执行之后的ticktNum--,导致其它线程执行该类操作获取一样的ticktNum值
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    //2.打印进程号和票号，票数减1
//                    String name = Thread.currentThread().getName();
//                    System.out.println("线程"+name+"售票："+ticktNum--);
//                }
//            }
            //当执行完代码就会释放obj对象（释放锁钥匙）

            //2.同步方法
//            saleTickt();

            //3.同步锁
            if(ticktNum > 0){
                lock.lock();//加同步锁
                try {//使用加锁一定有释放锁, 必须使用finally执行释放锁（如果不释放锁, 会导致死锁）
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String name = Thread.currentThread().getName();
                    System.out.println("线程"+name+"售票："+ticktNum--);
                } finally {
                    lock.unlock();//释放同步锁
                }
            }
        }
    }

    /**
     * 2.同步方法
     * 2.1.对于static方法,同步锁是当前方法所在类的字节码对象(类名.class)
     *   private static synchronized(Ticket.class) void saleTickt(){}
     *   默认写法 private static synchronized void saleTickt(){}
     * 2.2.对于非static方法,同步锁就是this
     *   private synchronized(new Ticket) void saleTickt(){}
     */
//    private synchronized void saleTickt(){
//        if(ticktNum > 0){
//            //1.模拟出票时间
//            try {
//                //因sleep()方法阻塞没有执行之后的ticktNum--,导致其它线程执行该类操作获取一样的ticktNum值
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            //2.打印进程号和票号，票数减1
//            String name = Thread.currentThread().getName();
//            System.out.println("线程"+name+"售票："+ticktNum--);
//        }
//    }

}
