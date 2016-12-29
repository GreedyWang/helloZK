package zookeeper.mylock;


public class Main {

    private void test(int v) {
        DistributedLock lock = new DistributedLock("localhost:3181", "lock");
        lock.lock();
        //共享资源
        if (lock != null) {
            System.out.println(v);
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(
                () -> {
                    Main m = new Main();
                    m.test(finalI);
                }
            ).start();
        }

    }
}
