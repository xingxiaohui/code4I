# 详解java中的锁
## 一、Lock接口
1. Lock介绍与使用  
> Java SE 5之后，并发包中新增了Lock接口（以及相关实现类）用来实现锁功能，它提供了与synchronized关键字类似的同步功能，只是在使用时需要显式地获取和释放锁。虽然它缺少了（通过synchronized块或者方法所提供的）隐式获取释放锁的便捷性，但是却拥有了锁获取与释放的可操作性、可中断的获取锁以及超时获取锁等多种synchronized关键字所不具备的同步特性。
```java 
// Lock 的用法
Lock lock = new ReentrantLock();
lock.lock();
try {
} finally {
    lock.unlock();
}
```
2. Lock接口提供的synchronized关键字不具备的主要特性  

![Lock接口提供的synchronized关键字不具备的主要特性](https://cdn.jsdelivr.net/gh/xxkasi/image/img/20201210163906.png)   

3. Lock是一个接口，它定义了锁获取和释放的基本操作，Lock的API如表所示。

![Lock的API](https://cdn.jsdelivr.net/gh/xxkasi/image/img/20201210164210.png)  

##  二、队列同步器
1. 队列同步器介绍
> 队列同步器AbstractQueuedSynchronizer（以下简称同步器），是用来构建锁或者其他同步组件的基础框架，它使用了一个int成员变量表示同步状态，通过内置的FIFO队列来完成资源获取线程的排队工作。 

2. 队列同步器的接口与示例  
同步器可重写的方法
![同步器可重写的方法](https://cdn.jsdelivr.net/gh/xxkasi/image/img/20201210165506.png)  
![同步器提供的模板方法](https://cdn.jsdelivr.net/gh/xxkasi/image/img/20201210165640.png)  
同步器提供的模板方法基本上分为3类：独占式获取与释放同步状态、共享式获取与释放同步状态和查询同步队列中的等待线程情况。自定义同步组件将使用同步器提供的模板方法来实现自己的同步语义。

