package in.qingchen.concurrency.Day02;

import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Created by ChenJiaQiang on 2020/7/5
 */
public class AtomicTest {

    public static class Count{
        private AtomicInteger count = new AtomicInteger();
        public void increase(){
            count.incrementAndGet();
        }
        public Integer get(){
            return count.get();
        }
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        Count count = new Count();
        for (int i=1;i<=100000;i++){
            pool.execute(()->{count.increase();});
        }
        pool.shutdown();
        System.out.println(count.get());
    }
}
