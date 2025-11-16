package hm3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyThreadPoolExecutor {

    private final List<Thread> workers;
    private final LinkedList<Runnable> queue = new LinkedList<>();

    private volatile Boolean isActive = true;

    public MyThreadPoolExecutor(int poolSize) {
        this.workers = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            Thread worker = new Thread(this::doWork);
            worker.start();
            workers.add(worker);
        }
    }

    public void execute(Runnable task) {
        if (!this.isActive) {
            throw new IllegalStateException("Пул потоков был выключен");
        }

        synchronized (queue) {
            queue.addLast(task);
            queue.notify();
        }
    }

    public void shutdown() {
        synchronized (queue) {
            this.isActive = false;
            queue.notifyAll();
        }
    }

    public void awaitTermination() throws InterruptedException {
        for (Thread worker : workers) {
            worker.join();
        }
    }

    private void doWork() {
        while (true) {
            Runnable task;
            synchronized (queue) {
                while (queue.isEmpty()) {
                    if (!isActive) {
                        return;
                    }

                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                task = queue.removeFirst();
            }

            if (task != null) {
                try {
                    task.run();
                } catch (Exception exception) {
                    System.out.println("Ошибка выполнения задачи в потоке: " + Thread.currentThread().getName());
                    exception.printStackTrace();
                }
            }
        }
    }
}
