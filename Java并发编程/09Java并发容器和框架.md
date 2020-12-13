# Java并发容器和框架
## 一、ConcurrentHashMap的实现原理与使用
1. 使用ConcurrentHashMap的原因
>在并发编程中使用HashMap可能导致程序死循环。而使用线程安全的HashTable效率又非常低下，基于以上两个原因，便有了ConcurrentHashMap的登场机会  
>在多线程环境下，使用HashMap进行put操作会引起死循环，导致CPU利用率接近100%，所以在并发情况下不能使用HashMap。