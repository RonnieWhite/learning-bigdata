package com.baich.bigdata.javase.queue_demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class queueDemo3 {
    static class Product implements Runnable {
        private final BlockingQueue queue;

        public Product(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println("begin put");
                long beginTime = System.currentTimeMillis();
                queue.put(new DelayedDTO(System.currentTimeMillis() + 2000L, beginTime));
                queue.put(new DelayedDTO(System.currentTimeMillis() + 5000L, beginTime));
                queue.put(new DelayedDTO(System.currentTimeMillis() + 2000L * 10, beginTime));
                System.out.println("end put");
            } catch (InterruptedException e) {
                System.err.println("" + e);
            }
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue queue;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println("Consumer begin");
                ((DelayedDTO) queue.take()).run();
                ((DelayedDTO) queue.take()).run();
                ((DelayedDTO) queue.take()).run();
                System.out.println("Consumer end");
            } catch (InterruptedException e) {
                System.err.println("" + e);
            }
        }
    }

    static class DelayedDTO implements Delayed {
        Long s;
        Long beginTime;

        public DelayedDTO(Long s, Long beginTime) {
            this.s = s;
            this.beginTime = beginTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(s - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        public void run() {
            System.out.println((System.currentTimeMillis() - beginTime)/1000);
        }
    }

    public static void main(String[] args) {
        BlockingQueue q = new DelayQueue();
        queueDemo3.Product p = new queueDemo3.Product(q);
        queueDemo3.Consumer c = new queueDemo3.Consumer(q);
        new Thread(c).start();
        new Thread(p).start();
    }
}
