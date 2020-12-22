# Executor框架 
## 一、Executor框架简介  
1. Executor框架的两级调度模型  
在HotSpot VM的线程模型中，Java线程（java.lang.Thread）被一对一映射为本地操作系统线程。Java线程启动时会创建一个本地操作系统线程；当该Java线程终止时，这个操作系统线程也会被回收。操作系统会调度所有线程并将它们分配给可用的CPU。  
在上层，Java多线程程序通常把应用分解为若干个任务，然后使用用户级的调度器（Executor框架）将这些任务映射为固定数量的线程；在底层，操作系统内核将这些线程映射到硬件处理器上。这种两级调度模型的示意图如图所示。  
![任务的两级调度模型](https://cdn.jsdelivr.net/gh/xxkasi/image/img/20201222190727.png)
从图中可以看出，应用程序通过Executor框架控制上层的调度；而下层的调度由操作系统内核控制，下层的调度不受应用程序的控制。  

2. Executor框架的结构  
Executor框架主要由3大部分组成如下。  
·任务。包括被执行任务需要实现的接口：Runnable接口或Callable接口。  
·任务的执行。包括任务执行机制的核心接口Executor，以及继承自Executor的ExecutorService接口。Executor框架有两个关键类实现了ExecutorService接口（ThreadPoolExecutor和ScheduledThreadPoolExecutor）。  
·异步计算的结果。包括接口Future和实现Future接口的FutureTask类。  

3. Executor框架包含的主要的类与接口  
·Executor是一个接口，它是Executor框架的基础，它将任务的提交与任务的执行分离开来。  
·ThreadPoolExecutor是线程池的核心实现类，用来执行被提交的任务。  
·ScheduledThreadPoolExecutor是一个实现类，可以在给定的延迟后运行命令，或者定期执行命令。 ScheduledThreadPoolExecutor比Timer更灵活，功能更强大。  
·Future接口和实现Future接口的FutureTask类，代表异步计算的结果。  
·Runnable接口和Callable接口的实现类，都可以被ThreadPoolExecutor或Scheduled-ThreadPoolExecutor执行。  

4. Executor框架的成员  
本节将介绍Executor框架的主要成员：ThreadPoolExecutor、ScheduledThreadPoolExecutor、Future接口、Runnable接口、Callable接口和Executors。  
> 1. ThreadPoolExecutor
ThreadPoolExecutor通常使用工厂类Executors来创建。Executors可以创建3种类型的ThreadPoolExecutor：SingleThreadExecutor、FixedThreadPool和CachedThreadPool。
>> 1. FixedThreadPool适用于为了满足资源管理的需求，而需要限制当前线程数量的应用场景，它适用于负载比较重的服务器。
>>2. SingleThreadExecutor适用于需要保证顺序地执行各个任务；并且在任意时间点，不会有多个线程是活动的应用场景。  
>>3. CachedThreadPool是大小无界的线程池，适用于执行很多的短期异步任务的小程序，或者是负载较轻的服务器。
>2. ScheduledThreadPoolExecutor
ScheduledThreadPoolExecutor通常使用工厂类Executors来创建。Executors可以创建2种类型的ScheduledThreadPoolExecutor，如下。  
·ScheduledThreadPoolExecutor。包含若干个线程的ScheduledThreadPoolExecutor。  
·SingleThreadScheduledExecutor。只包含一个线程的ScheduledThreadPoolExecutor。  
>>1. ScheduledThreadPoolExecutor适用于需要多个后台线程执行周期任务，同时为了满足资源管理的需求而需要限制后台线程的数量的应用场景。  
>> 2. SingleThreadScheduledExecutor适用于需要单个后台线程执行周期任务，同时需要保证顺序地执行各个任务的应用场景。  
>3. Future接口  
Future接口和实现Future接口的FutureTask类用来表示异步计算的结果。当我们把Runnable接口或Callable接口的实现类提交（submit）给ThreadPoolExecutor或ScheduledThreadPoolExecutor时，ThreadPoolExecutor或ScheduledThreadPoolExecutor会向我们返回一个FutureTask对象。
>4. Runnable接口和Callable接口
Runnable接口和Callable接口的实现类，都可以被ThreadPoolExecutor或Scheduled-ThreadPoolExecutor执行。它们之间的区别是Runnable不会返回结果，而Callable可以返回结果。  
除了可以自己创建实现Callable接口的对象外，还可以使用工厂类Executors来把一个Runnable包装成一个Callable。  




