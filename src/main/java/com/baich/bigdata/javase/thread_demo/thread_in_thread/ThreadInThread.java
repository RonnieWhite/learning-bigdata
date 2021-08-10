package com.baich.bigdata.javase.thread_demo.thread_in_thread;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-02
 * Time : 18:36
 * Description:
 * Modified By:
 * version : v1.0
 */
public class ThreadInThread {
 /*   private static int maxTimeout = 20;

    public static void main(String[] args) {
        Thread work = new Thread((Runnable) () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    new Worker("task_" + i).workerThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        work.start();
        System.out.println("Main waiting");
        try {
            work.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main exit");
    }

    static class Worker extends Thread {
        private String taskName;

        public Worker(String taskName) {
            this.taskName = taskName;
        }

        public void workerThread() throws InterruptedException {
            System.out.println("start " + taskName);
            Date start = new Date();
            Date stop = new Date(start.getTime() + maxTimeout * 1000);
            SubWorker worker = new SubWorker(taskName);
            worker.start();
            do {
                Thread.sleep(2000);
                System.out.println(taskName + "work i" + start.toString() + ", max:" + stop.toString());
            } while (!worker.finished && (new Date()).before(stop));
            System.out.println("worker id finished? " + worker.finished + "," + new Timestamp(new Date().getTime()).toString());
            if (!worker.finished) {
                System.out.println("work timeout ! interrupted");
                worker.stop();
            }
            System.out.println("stop" + taskName);
        }

        @Override
        public void run() {
            try {
                workerThread();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    class SubWorker extends Thread {
        private String taskName;

        public SubWorker(String name) {
            this.taskName = name;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        private boolean finished;

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }

        public void run() {
            System.out.println("SubWorker start!");
            doSomething();
            this.setFinished(true);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("SubWorker stop!");
        }

        private void doSomething() {
            Random rnd = new Random();
            for (int i = 0, n = rnd.nextInt(15) + 1; i < n; i++) {
                System.out.println("---------" + this.getTaskName() + "--------start" + i + "execute sql in (" + n + ")");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("--------" + this.getTaskName() + "--------stop" + i + "execute sql in (" + n + ")");
            }
        }
    }*/
}
