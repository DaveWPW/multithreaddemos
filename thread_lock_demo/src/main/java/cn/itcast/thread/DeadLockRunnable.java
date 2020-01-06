package cn.itcast.thread;

/**
 * @Author: Dave
 * @Date: 2019/12/27 15:28
 * @Description: TODO
 */
public class DeadLockRunnable implements Runnable{

    private int flag; //决定线程走向的标记
    //obj1定义成static静态变量，使线程可以共享实例
    private static Object obj1 = new Object(); //锁对象1
    //obj2定义成static静态变量，使线程可以共享实例
    private static Object obj2 = new Object(); //锁对象2

    public DeadLockRunnable(int flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        if(flag == 1){
            //线程1执行代码
            synchronized (obj1){
                //获取到obj1资源
                System.out.println(Thread.currentThread().getName()+"已获取到资源obj1, 请求obj2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //再请求obj2资源（因obj2资源给占用, 只能在等待）
                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName()+"已获取到资源obj1和obj2");
                }
            }
        }else{
            //线程2执行代码
            synchronized (obj2){
                //获取到obj2资源
                System.out.println(Thread.currentThread().getName()+"已获取到资源obj2, 请求obj1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //再请求obj1资源（因obj1资源给占用, 只能在等待）
                synchronized (obj1){
                    System.out.println(Thread.currentThread().getName()+"已获取到资源obj2和obj1");
                }
            }
        }
    }
}
