package cn.itcast.thread;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 创建线程实例
 *
 * @Author: Dave
 * @Date: 2019/12/14 1:07
 * @Description: TODO
 */
public class ThreadCreateDemo {
    /**
     * main主线程和子线程并发
     */
    public static void main(String[] args) {
        //1.继承Thread类创建线程
//        //1.1.创建自定义线程实例（子线程）
//        MyThread thread = new MyThread();
//        //1.2.启动线程
//        thread.start();
//        //1.3.执行main主线程
//        for (int i=0; i<10; i++){
//            System.out.println("main主线程正在执行"+i+":"+new Date().getTime());
//        }

        //2.实现runnable接口创建线程
//        //2.1.通过Thread类执行MyRunnable实现类(线程名不添加，默认thread-0, thread-1...)
//        Thread thread = new Thread(new MyRunnable(), "MyRunnable");
//        //2.2.启动线程
//        thread.start();
//        //2.3.执行main主线程
//        for (int i=0; i<10; i++){
//            System.out.println("main主线程正在执行"+i+":"+new Date().getTime());
//        }

        //3.实现Callable接口创建线程(拥有返回值)
//        //3.1.通过创建FutureTask实例，执行MyCallable实现类
//        FutureTask<String> task = new FutureTask<String>(new MyCallable());
//        //3.2.通过Thread类执行FutureTask实例(添加MyCallable线程名)
//        Thread thread = new Thread(task, "MyCallable");
//        //3.3.启动线程
//        thread.start();
//        //3.4.执行main主线程
//        for (int i=0; i<10; i++){
//            System.out.println("main主线程正在执行"+i+":"+new Date().getTime());
//        }
//        //3.5.获取MyCallable执行结果
//        String result = null;
//        try {
//            result = task.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println(result);

        //4.使用线程池创建线程
        //4.1.使用Executors工具类获取线程池对象
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //4.2.通过线程池对象获取线程并执行Runnuble实例
        executorService.execute(new MyRunnable());
        //4.3.执行main主线程
        for (int i=0; i<10; i++){
            System.out.println("main主线程正在执行"+i+":"+new Date().getTime());
        }
    }
}
