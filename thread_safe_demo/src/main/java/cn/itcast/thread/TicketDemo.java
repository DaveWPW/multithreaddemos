package cn.itcast.thread;

/**
 * 线程安全问题（多个线程修改全局变量，静态变量引起的问题）
 * 解决线程安全问题（线程同步实例）
 * 1.同步代码块，2.同步方法，3.同步锁
 *
 * @Author: Dave
 * @Date: 2019/12/20 17:21
 * @Description: TODO
 */
public class TicketDemo {
    public static void main(String[] args){
        Ticket ticket = new Ticket();
        Thread thread1 = new Thread(ticket, "窗口1");
        Thread thread2 = new Thread(ticket, "窗口2");
        Thread thread3 = new Thread(ticket, "窗口3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
