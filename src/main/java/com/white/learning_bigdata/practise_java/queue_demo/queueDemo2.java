package com.white.learning_bigdata.practise_java.queue_demo;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class queueDemo2 {

    static class delayElement implements Delayed {
        public delayElement() {

        }

        @Override
        public long getDelay(TimeUnit unit) {
            return 1L;
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }
    }

    public static void main(String[] args) throws Exception {
//        Queue<String> queue = new LinkedBlockingQueue<>();
//        System.out.println(0x7fffffff);
        DelayQueue<Delayed> queue = new DelayQueue<>();
        queue.add(new delayElement());
        queue.add(new delayElement());
        queue.add(new delayElement());

        System.out.println(queue.take());

    }
}
