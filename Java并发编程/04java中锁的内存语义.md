# java中锁的内存语义
## 一、java中锁的内存语义
1. 锁释放的内存语义：当线程释放锁时，JMM会把该线程对应的本地内存中的共享变量刷新到主内存中。
2. 锁获取的内存语义：当线程获取锁时，JMM会把该线程对应的本地内存置为无效。从而使得被监视器保护的临界区代码必须从主内存中读取共享变量。
> 对比锁释放-获取的内存语义与volatile写-读的内存语义可以看出：锁释放与volatile写有相同的内存语义；锁获取与volatile读有相同的内存语义。
## 二、锁内存语义的实现 
1. 借助ReentrantLock的源代码，来分析锁内存语义的具体实现机制：
> 在ReentrantLock中，调用lock()方法获取锁；调用unlock()方法释放锁。
ReentrantLock的实现依赖于Java同步器框架AbstractQueuedSynchronizer（本文简称之为AQS）。AQS使用一个整型的volatile变量（命名为state）来维护同步状态。

>从ReentrantLock源代码中我们可以看出，公平锁在释放锁的最后写volatile变量state，在获取锁时首先读这个volatile变量。根据volatile的happens-before规则，释放锁的线程在写volatile变量之前可见的共享变量，在获取锁的线程读取同一个volatile变量后将立即变得对获取锁的线程可见。  

对公平锁和非公平锁的内存语义做个总结。  
>·公平锁和非公平锁释放时，最后都要写一个volatile变量state。  
·公平锁获取时，首先会去读volatile变量。  
·非公平锁获取时，首先会用CAS更新volatile变量，这个操作同时具有volatile读和volatile写的内存语义。 

从本文对ReentrantLock的分析可以看出，锁释放-获取的内存语义的实现至少有下面两种方式。  
>1）利用volatile变量的写-读所具有的内存语义。  
2）利用CAS所附带的volatile读和volatile写的内存语义。

## 三、CAS介绍
1. Java的compareAndSet()方法调用简称为CAS。JDK文档对该方法的说明如下：如果当前状态值等于预期值，则以原子方式将同步状态设置为给定的更新值。此操作具有volatile读和写的内存语义。  

2. 阅读sun.misc.Unsafe类的compareAndSwapInt()方法的源代码，可以看出这是一个本地方法调用，其底层实现是带有lock前缀的cmpxchg指令。
>1. 如果程序是在多处理器上运行，就为cmpxchg指令加上lock前缀（Lock Cmpxchg）。反之，如果程序是在单处理器上运行，就省略lock前缀（单处理器自身会维护单处理器内的顺序一致性，不需要lock前缀提供的内存屏障效果）。

3. intel的手册对lock前缀的说明
> 1. 确保对内存的读-改-写操作原子执行,底层通过总线锁或缓存锁实现。
> 2. 禁止该指令，与之前和之后的读和写指令重排序。
> 3. 把写缓冲区中的所有数据刷新到内存中。

## 四、concurrent包的实现
>Java的CAS会使用现代处理器上提供的高效机器级别的原子指令，这些原子指令以原子方式对内存执行读-改-写操作，这是在多处理器中实现同步的关键。同时，volatile变量的读/写和CAS可以实现线程之间的通信。把这些特性整合在一起，就形成了整个concurrent包得以实现的基石。如果我们仔细分析concurrent包的源代码实现，会发现一个通用化的实现模式。  
>>首先，声明共享变量为volatile。  
>>然后，使用CAS的原子条件更新来实现线程之间的同步。  
>>同时，配合以volatile的读/写和CAS所具有的volatile读和写的内存语义来实现线程之间的通信。  
>>AQS，非阻塞数据结构和原子变量类（java.util.concurrent.atomic包中的类），这些concurrent包中的基础类都是使用这种模式来实现的，而concurrent包中的高层类又是依赖于这些基础类来实现的。  

![concurrent包的实现示意图](https://cdn.jsdelivr.net/gh/xxkasi/image/img/052.jpg)  
