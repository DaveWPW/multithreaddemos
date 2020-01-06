package cn.itcast.thread;

import java.util.concurrent.Semaphore;

/**
 * Semaphore方式
 * 用于控制线程数对某组资源数的访问权限
 *
 * 8个工人使用3台机器工作，机器为互斥资源（即每次只能一个人使用）
 *
 * @Author: Dave
 * @Date: 2020/1/2 19:15
 * @Description: TODO
 */
public class SemaphoreDemo {
    static class Work implements Runnable{
        private int workerNum;//工人的工号(线程号)
        private Semaphore semaphore;//机器数

        public Work(int workerNum, Semaphore semaphore) {
            this.workerNum = workerNum;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                //1.工人要去获取机器
                semaphore.acquire();//获取资源
                //2.开始工作（打印工人获取到机器）
                String name = Thread.currentThread().getName();
                System.out.println(name+"获取到机器，开始工作...");
                //3.线程睡眠1000毫秒，模拟工人使用机器工作过程
                Thread.sleep(1000);
                //4.使用完毕，释放机器（打印工人使用完毕，释放机器）
                semaphore.release();//释放资源
                System.out.println(name+"使用完毕，释放机器！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        int woroker = 8;//代表工人数8个
        Semaphore semaphore = new Semaphore(3);//代表机器数3个
        for(int i=0; i<woroker; i++){
            new Thread(new Work(i, semaphore), "线程"+i).start();
        }
    }

}
