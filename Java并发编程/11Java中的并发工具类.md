# Java中的并发工具类
在JDK的并发包里提供了几个非常有用的并发工具类。CountDownLatch、CyclicBarrier和Semaphore工具类提供了一种并发流程控制的手段，Exchanger工具类则提供了在线程间交换数据的一种手段。

## 一、等待多线程完成的CountDownLatch  
1. 介绍
>JDK 1.5之后的并发包中提供的CountDownLatch也可以实现join的功能，并且比join的功能更多  

2. 示例
```java
public class CountDownLatchTest {
    staticCountDownLatch c = new CountDownLatch(2);
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
                public void run() {
                System.out.println(1);
                c.countDown();
                System.out.println(2);
                c.countDown();
            }
       }).start();
        c.await();
        System.out.println("3");
    }
}
```
>CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完成，这里就传入N。  
当我们调用CountDownLatch的countDown方法时，N就会减1，CountDownLatch的await方法会阻塞当前线程，直到N变成零。由于countDown方法可以用在任何地方，所以这里说的N个点，可以是N个线程，也可以是1个线程里的N个执行步骤。用在多个线程时，只需要把这个CountDownLatch的引用传递到线程里即可。

## 二、同步屏障CyclicBarrier
1. 介绍  
CyclicBarrier的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行。
CyclicBarrier默认的构造方法是CyclicBarrier（int parties），其参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。  
CyclicBarrier还提供一个更高级的构造函数CyclicBarrier（int parties，Runnable barrier-Action），用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景。  
2. 应用场景  
CyclicBarrier可以用于多线程计算数据，最后合并计算结果的场景。例如，用一个Excel保存了用户所有银行流水，每个Sheet保存一个账户近一年的每笔银行流水，现在需要统计用户的日均银行流水，先用多线程处理每个sheet里的银行流水，都执行完之后，得到每个sheet的日均银行流水，最后，再用barrierAction用这些线程的计算结果，计算出整个Excel的日均银行流水，  
3. CyclicBarrier和CountDownLatch的区别  
CountDownLatch的计数器只能使用一次，而CyclicBarrier的计数器可以使用reset()方法重置。所以CyclicBarrier能处理更为复杂的业务场景。例如，如果计算发生错误，可以重置计数器，并让线程重新执行一次。  
CyclicBarrier还提供其他有用的方法，比如getNumberWaiting方法可以获得Cyclic-Barrier阻塞的线程数量。isBroken()方法用来了解阻塞的线程是否被中断。  

## 三、控制并发线程数的Semaphore  
1. 介绍   
Semaphore（信号量）是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源。  
多年以来，我都觉得从字面上很难理解Semaphore所表达的含义，只能把它比作是控制流量的红绿灯。比如××马路要限制流量，只允许同时有一百辆车在这条路上行使，其他的都必须在路口等待，所以前一百辆车会看到绿灯，可以开进这条马路，后面的车会看到红灯，不能驶入××马路，但是如果前一百辆中有5辆车已经离开了××马路，那么后面就允许有5辆车驶入马路，这个例子里说的车就是线程，驶入马路就表示线程在执行，离开马路就表示线程执行完成，看见红灯就表示线程被阻塞，不能执行。  

2. 应用场景  
Semaphore可以用于做流量控制，特别是公用资源有限的应用场景，比如数据库连接。假如有一个需求，要读取几万个文件的数据，因为都是IO密集型任务，我们可以启动几十个线程并发地读取，但是如果读到内存后，还需要存储到数据库中，而数据库的连接数只有10个，这时我们必须控制只有10个线程同时获取数据库连接保存数据，否则会报错无法获取数据库连接。这个时候，就可以使用Semaphore来做流量控制。

3. 使用示例
```java 
public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;
    private static ExecutorServicethreadPool = Executors
            .newFixedThreadPool(THREAD_COUNT);
    private static Semaphore s = new Semaphore(10);
    public static void main(String[] args) {
    for (inti = 0; i< THREAD_COUNT; i++) {
    threadPool.execute(new Runnable() {
                @Override
    public void run() {
    try {
    s.acquire();
    System.out.println("save data");
    s.release();
                    } catch (InterruptedException e) {
                    }
                }
            });
        }
    threadPool.shutdown();
    }
}
```
>在代码中，虽然有30个线程在执行，但是只允许10个并发执行。Semaphore的构造方法Semaphore（int permits）接受一个整型的数字，表示可用的许可证数量。Semaphore（10）表示允许10个线程获取许可证，也就是最大并发数是10。Semaphore的用法也很简单，首先线程使用Semaphore的acquire()方法获取一个许可证，使用完之后调用release()方法归还许可证。还可以用tryAcquire()方法尝试获取许可证。

4. 其他方法  
Semaphore还提供一些其他方法，具体如下。
·intavailablePermits()：返回此信号量中当前可用的许可证数。  
·intgetQueueLength()：返回正在等待获取许可证的线程数。  
·booleanhasQueuedThreads()：是否有线程正在等待获取许可证。  
·void reducePermits（int reduction）：减少reduction个许可证，是个protected方法。  
·Collection getQueuedThreads()：返回所有等待获取许可证的线程集合，是个protected方法。  

## 四、线程间交换数据的Exchanger  
1. 介绍  
Exchanger（交换者）是一个用于线程间协作的工具类。Exchanger用于进行线程间的数据交换。它提供一个同步点，在这个同步点，两个线程可以交换彼此的数据。这两个线程通过exchange方法交换数据，如果第一个线程先执行exchange()方法，它会一直等待第二个线程也执行exchange方法，当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产出来的数据传递给对方。  
2. Exchanger的应用场景  
Exchanger可以用于遗传算法 ，遗传算法里需要选出两个人作为交配对象，这时候会交换两人的数据，并使用交叉规则得出2个交配结果。Exchanger也可以用于校对工作 ，比如我们需要将纸制银行流水通过人工的方式录入成电子银行流水，为了避免错误，采用AB岗两人进行录入，录入到Excel之后，系统需要加载这两个Excel，并对两个Excel数据进行校对，看看是否录入一致。





