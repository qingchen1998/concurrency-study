package in.qingchen.concurrency.Day01;

/**
 * @author Created by ChenJiaQiang on 2020/7/4
 */
public class InterruptTest {

    private Runnable runnable = ()->{
        System.out.println("Into " + Thread.currentThread().getName());
        int i = 0;
        try {
            while (i < 1000) {
                System.out.println("This is " + Thread.currentThread().getName());
                // 睡个半秒钟再执行
                Thread.sleep(500);
                System.out.println(i++);
            }
        } catch (InterruptedException e) {
            // 判断该阻塞线程是否还在
            System.out.println(Thread.currentThread().isAlive());
            // 判断该线程的中断标志位状态
            System.out.println(Thread.currentThread().isInterrupted());
            System.out.println("In Runnable");
            e.printStackTrace();
        }
    };

    public static void main(String[] args) {
        System.out.println("This is " + Thread.currentThread().getName());
        InterruptTest t1 = new InterruptTest();
        Thread thread = new Thread(t1.runnable);
        thread.start();
        try {
            // main线程睡3秒
            System.out.println("Main sleep yet");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("In main");
            e.printStackTrace();
        }
        // 设置中断
        thread.interrupt();
    }
}
