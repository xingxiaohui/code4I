## 详解 volatile
### 1. volatile的定义  
>Java语言规范对volatile的定义：如果一个字段被声明成volatile，Java线程内存模型确保所有线程看到这个变量的值是一致的。

>volatile变量自身具有下列特性。  
1. 可见性。对一个volatile变量的读，总是能看到（任意线程）对这个volatile变量最后的写入。  
2. 原子性：对任意单个volatile变量的读/写具有原子性，但类似于volatile++这种复合操作不具有原子性。
>从内存语义的角度来说，volatile的写-读与锁的释放-获取有相同的内存效果：volatile写和锁的释放有相同的内存语义；volatile读与锁的获取有相同的内存语义。  
volatile读的内存语义如下:  
&emsp;当读一个volatile变量时，JMM会把该线程对应的本地内存置为无效。线程接下来将从主内存中读取共享变量。  
volatile写的内存语义如下:   
&emsp;当写一个volatile变量时，JMM会把该线程对应的本地内存中的共享变量值刷新到主内存。  
    
### 2. volatile的JMM实现之插入内存屏障禁止指令重排序
>为了实现volatile的内存语义，编译器在生成字节码时，会在指令序列中插入内存屏障来禁止特定类型的处理器重排序。对于编译器来说，发现一个最优布置来最小化插入屏障的总数几乎不可能。为此，JMM采取保守策略。下面是基于保守策略的JMM内存屏障插入策略。  
>>·在每个volatile写操作的前面插入一个StoreStore屏障。  
·在每个volatile写操作的后面插入一个StoreLoad屏障。  
·在每个volatile读操作的后面插入一个LoadLoad屏障。  
·在每个volatile读操作的后面插入一个LoadStore屏障。  

### 3. volatile的处理器层实现  
>在X86处理器下通过工具获取JIT编译器生成的汇编指令来查看对volatile进行写操作时，CPU会做什么事情。
``` 
Java代码如下
instance = new Singleton(); // instance是volatile变量
转变成汇编代码，如下
0x01a3de1d: movb $0×0,0×1104800(%esi);0x01a3de24: lock addl $0×0,(%esp);
```
### 4. Lock前缀的指令在多核处理器下会引发了两件事情  
>01. 将当前处理器缓存行的数据写回到系统内存。  
>02. 这个写回内存的操作会使在其他CPU里缓存了该内存地址的数据无效。  

### 5. 多核处理器保证缓存可见的方式--缓存一致性协议：  
>在多处理器下，为了保证各个处理器的缓存是一致的，就会实现缓存一致性协议，每个处理器通过嗅探在总线上传播的数据来检查自己缓存的值是不是过期了，当处理器发现自己缓存行对应的内存地址被修改，就会将当前处理器的缓存行设置成无效状态，当处理器对这个数据进行修改操作的时候，会重新从系统内存中把数据读到处理器缓存里。  
### 6. 详解volatile的两条实现原则：
>1. Lock前缀指令会引起处理器缓存回写到内存 。Lock前缀指令导致在执行指令期间，声言处理器的LOCK#信号。在多处理器环境中，LOCK#信号确保在声言该信号期间，处理器可以独占任何共享内存 [2] 。但是，在最近的处理器里，LOCK＃信号一般不锁总线，而是锁缓存，毕竟锁总线开销的比较大。在8.1.4节有详细说明锁定操作对处理器缓存的影响，对于Intel486和Pentium处理器，在锁操作时，总是在总线上声言LOCK#信号。但在P6和目前的处理器中，如果访问的内存区域已经缓存在处理器内部，则不会声言LOCK#信号。相反，它会锁定这块内存区域的缓存并回写到内存，并使用缓存一致性机制来确保修改的原子性，此操作被称为“缓存锁定”，缓存一致性机制会阻止同时修改由两个以上处理器缓存的内存区域数据。

>2. 一个处理器的缓存回写到内存会导致其他处理器的缓存无效 。IA-32处理器和Intel 64处理器使用MESI（修改、独占、共享、无效）控制协议去维护内部缓存和其他处理器缓存的一致性。在多核处理器系统中进行操作的时候，IA-32和Intel 64处理器能嗅探其他处理器访问系统内存和它们的内部缓存。处理器使用嗅探技术保证它的内部缓存、系统内存和其他处理器的缓存的数据在总线上保持一致。例如，在Pentium和P6 family处理器中，如果通过嗅探一个处理器来检测其他处理器打算写内存地址，而这个地址当前处于共享状态，那么正在嗅探的处理器将使它的缓存行无效，在下次访问相同内存地址时，强制执行缓存行填充。

### 7. volatile与锁的优缺点对比
>由于volatile仅仅保证对单个volatile变量的读/写具有原子性(i++属于复合操作，volatile不能保证其原子性)，而锁的互斥执行的特性可以确保对整个临界区代码的执行具有原子性。在功能上，锁比volatile更强大；在可伸缩性和执行性能上，volatile更有优势。  

### 8.volatile的使用优化  
以下两种情况下可以使用追加字节到64位的形势来优化volatile的效率
>1. 缓存行为64字节宽的处理器   
>2. 共享变量不会被频繁地写  



