package in.qingchen.concurrency.Day01;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author Created by ChenJiaQiang on 2020/7/4
 */
public class CreateMultipleThreads {

    /**
     * 第一种：继承Thread类
     */
    public static class MyThread1 extends Thread{
        /**
         * 重写run方法
         */
        @Override
        public void run() {
            for (int x = 0; x < 100; x++) {
                System.out.println(x);
            }
        }
    }

    /**
     * 第二种：实现runnable接口
     */
    public static class MyThread2 implements Runnable{
        @Override
        public void run() {
            for (int x = 0; x < 100; x++) {
                System.out.println(x);
            }
        }
    }

    /**
     * 第三种：通过Callable 和 Future
     */
    public static class MyThread3 implements Callable<String> {
        /**
         * 重写call方法
         * @return
         * @throws Exception
         */
        @Override
        public String call() throws Exception {
            for (int x = 0; x < 100; x++) {
                System.out.println(x);
            }
            return "call方法执行完毕，我是返回值";
        }
    }

    public static void main(String[] args) throws Exception {
        // 测试第一种
//        MyThread1 my1 = new MyThread1();
//        MyThread1 my2 = new MyThread1();
//        my1.start();
//        my2.start();
        /**
         * 第一种可以发现，线程在交替运行
         * 例如一个线程从0-51 然后另外一个线程接着从0开始
         */

        // 测试第二种
//        MyThread2 myThread2 = new MyThread2();
//        Thread t1 = new Thread(myThread2);
//        Thread t2 = new Thread(myThread2);
//        t1.start();
//        t2.start();
        /**
         * 第二种可以发现，跟第一种的运行结果是一样的
         */

        // 第二种可以简化为
//        Thread t = new Thread(()->{
//            for (int x = 0; x < 100; x++) {
//                System.out.println(x);
//            }
//        },"给线程取个名字吧");
//        t.start();

        // 第二种可进一步简化为
//        new Thread(()->{
//            for (int x = 0; x < 100; x++) {
//                System.out.println(x);
//            }
//        },"取个名字").start();

        // 测试第三种
        MyThread3 myThread3 = new MyThread3();
        FutureTask<String> futureTask1 = new FutureTask<>(myThread3);
        FutureTask<String> futureTask2 = new FutureTask<>(myThread3);
        new Thread(futureTask1,"futureTask1").start();
        new Thread(futureTask2,"futureTask2").start();
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
    }
}
