package cn.itcast.thread;

/**
 * 线程通讯
 * 休眠唤醒方式（Object）
 *
 * 说明：需要依赖 synchronized 关键字
 * @Author: Dave
 * @Date: 2019/12/27 19:16
 * @Description: TODO
 */
public class OddEvenDemo {

    private int i = 0;//需要打印的数

    //1.Object方式
    private Object obj = new Object();

    /**
     * 奇数打印方法，由奇数线程调用
     */
    public void odd(){
        //1.判断i是否小于10
        while (i < 10){
            synchronized (obj){
                //2.<10打印奇数
                if(i%2 == 1){
                    System.out.println("奇数:"+i);
                    i++;
                    obj.notify();//通知偶数线程打印
                } else {
                    try {
                        obj.wait();//（等待）执行偶数线程打印完毕
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 偶数打印方法，由偶数线程调用
     */
    public void even(){
        //1.判断i是否小于10
        while (i < 10){
            synchronized (obj){
                //2.<10打印偶数
                if(i%2 == 0){
                    System.out.println("偶数:"+i);
                    i++;
                    obj.notify();//通知奇数线程打印
                } else {
                    try {
                        obj.wait();//（等待）执行奇数线程打印完毕
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        final OddEvenDemo oddEvenDemo = new OddEvenDemo();
        //1.1.创建奇数线程
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                oddEvenDemo.odd();
            }
        });
        //1.2.创建偶数线程
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                oddEvenDemo.even();
            }
        });
        //2.1.开启奇数线程
        thread1.start();
        //2.2.开启偶数线程
        thread2.start();
    }

}
