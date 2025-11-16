package hm3;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyThreadPoolExecutor executor = new MyThreadPoolExecutor(10);

        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> {
                System.out.println("Привет, я поток:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination();
    }
}
