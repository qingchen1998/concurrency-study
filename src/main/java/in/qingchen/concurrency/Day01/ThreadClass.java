package in.qingchen.concurrency.Day01;

/**
 * @author Created by ChenJiaQiang on 2020/7/4
 */
public class ThreadClass {

    public static class MyThread implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        /**
         * 测试打印线程名字
         */
        // 打印main线程的名字
//        System.out.println(Thread.currentThread().getName());
//        MyThread myThread = new MyThread();
//        for (int x = 0; x < 10; x++) {
//            new Thread(myThread).start();
//        }
        /**
         * 测试守护线程
         */
        System.out.println(Thread.currentThread().getName());
        MyThread myThread = new MyThread();
        new Thread(myThread,"我是Thread1").start();
        Thread thread2 = new Thread(myThread, "我是Thread2，我是个守护线程");
        thread2.setDaemon(true);
        thread2.start();
    }
}
