package in.qingchen.concurrency.Day02;

import java.util.concurrent.*;

/**
 * @author Created by ChenJiaQiang on 2020/7/5
 */
public class ThreadPoolTest {

    /**
     * 第三种：通过Callable 和 Future
     */
    public static class MyThread3 implements Callable<Integer> {

        private int num;

        public MyThread3(int num){
            this.num = num;
        }
        /**
         * 重写call方法
         * @return
         * @throws Exception
         */
        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int x = 1; x <= num; x++) {
                sum+=x;
            }
            return sum;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread3(100));
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread3(200));
        pool.submit(futureTask1);
        pool.submit(futureTask2);
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        pool.shutdown();
    }
}
