//包括了线程池的创建，将任务添加到线程池中，关闭线程池这3个主要的步骤。
//主线程中创建了线程池pool，线程池的容量是2。即，线程池中最多能同时运行2个线程。
//紧接着，将ta,tb,tc,td,te这3个线程添加到线程池中运行。
//最后，通过shutdown()关闭线程池。
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println("\n**********");
        // 创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        Thread ta = new MyThread();
        Thread tb = new MyThread();
        Thread tc = new MyThread();
        Thread td = new MyThread();
        Thread te = new MyThread();
        // 将线程放入池中进行执行
        pool.execute(ta);
        pool.execute(tb);
        pool.execute(tc);
        pool.execute(td);
        pool.execute(te);
        // 关闭线程池
        pool.shutdown();
        }
}
     
       
     
//    public static ExecutorService newFixedThreadPool(int nThreads) {
//        return new ThreadPoolExecutor(nThreads, nThreads,
//                                      0L, TimeUnit.MILLISECONDS,
//                                      new LinkedBlockingQueue<Runnable>());
//                                    }
//                                    
//                                    
//                                    
//    public void execute(Runnable command) {
//        // 如果任务为null，则抛出异常。
//        if (command == null)
//        throw new NullPointerException();
//        // 获取ctl对应的int值。该int值保存了"线程池中任务的数量"和"线程池状态"信息
//        int c = ctl.get();
//        // 当线程池中的任务数量 < "核心池大小"时，即线程池中少于corePoolSize个任务。
//        // 则通过addWorker(command, true)新建一个线程，并将任务(command)添加到该线程中；然后，启动该线程从而执行任务。
//        if (workerCountOf(c) < corePoolSize) {
//            if (addWorker(command, true))
//                return;
//        c = ctl.get();
//        }
//        // 当线程池中的任务数量 >= "核心池大小"时，
//        // 而且，"线程池处于允许状态"时，则尝试将任务添加到阻塞队列中。
//        if (isRunning(c) && workQueue.offer(command)) {
//            // 再次确认“线程池状态”，若线程池异常终止了，则删除任务；然后通过reject()执行相应的拒绝策略的内容。
//            int recheck = ctl.get();
//            if (! isRunning(recheck) && remove(command))
//                reject(command);
//            // 否则，如果"线程池中任务数量"为0，则通过addWorker(null, false)尝试新建一个线程，新建线程对应的任务为null。
//            else if (workerCountOf(recheck) == 0)
//                addWorker(null, false);
//        }
//        // 通过addWorker(command, false)新建一个线程，并将任务(command)添加到该线程中；然后，启动该线程从而执行任务。
//        // 如果addWorker(command, false)执行失败，则通过reject()执行相应的拒绝策略的内容。
//        else if (!addWorker(command, false))
//            reject(command);
//        }
//        
//        
//        
//        public void shutdown() {
//            final ReentrantLock mainLock = this.mainLock;
//            // 获取锁
//            mainLock.lock();
//            try {
//                // 检查终止线程池的“线程”是否有权限。
//                checkShutdownAccess();
//                // 设置线程池的状态为关闭状态。
//                advanceRunState(SHUTDOWN);
//                // 中断线程池中空闲的线程。
//                interruptIdleWorkers();
//                // 钩子函数，在ThreadPoolExecutor中没有任何动作。
//                onShutdown(); // hook for ScheduledThreadPoolExecutor
//            } finally {
//                // 释放锁
//                mainLock.unlock();
//                }
//            // 尝试终止线程池
//            tryTerminate();
//            }
//       }

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " is running.");
    }
}