//思路：解题思路大概是这样的，开启三个线程，每个线程一次打印一个字母，并且按照一定的顺序打印，
//当打印A的时候，其他线程处于阻塞状态，打印完A以后，将线程解锁，让打印B的那个线程开启，其他线程处于阻塞状态，同理打印C的时候，阻塞其他线程，这三个线程顺序循环，就达到顺序多次打印ABC的目的了。

public class PrintABC {
    public static Boolean isThreadA = true;
    public static Boolean isThreadB = false;
    public static Boolean isThreadC = false;
    public static void main(String[] args) {
        System.out.println("\n**********");
        final PrintABC abc = new PrintABC();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (abc) {
                        while (!isThreadA) {
                            try {
                                abc.wait();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        System.out.print("A");
                        isThreadA = false;
                        isThreadB = true;
                        isThreadC = false;
                        abc.notifyAll();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (abc) {
                        while (!isThreadB) {
                            try {
                                abc.wait();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        System.out.print("B");
                        isThreadA = false;
                        isThreadB = false;
                        isThreadC = true;
                        abc.notifyAll();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (abc) {
                        while (!isThreadC) {
                            try {
                                abc.wait();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        System.out.print("C");
                        isThreadA = true;
                        isThreadB = false;
                        isThreadC = false;
                        abc.notifyAll();
                    }
                }
            }
        }).start();
    }
}
