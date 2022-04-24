# Long.valueOf()与Long.parseLong()

1.8的源码显示

1. 如果入参为字符串，Long.valueOf()的返回为：return Long.valueOf(parseLong(s, 10)); 也就是说会先调用一次parseLong，所以效率上来讲，Long.parseLong()
   是高于Long.valueOf()的
2. 如果入参为一个数（二进制、八进制、十进制等类型），Long.valueOf()调用的LongCache缓存机制会提升效率（缓存命中），这比调用Long.parseLong()（需要先将入参转为字符串）效率更高
3. 从返回结果方面考虑，Long.valueOf()返回的是Long类型，而Long.parseLong()返回的是long类型，所以如果抛开缓存命中机制的话，Long.parseLong()的效率更胜一筹。

# static

1. 当static修饰类变量时，如果该变量是public的话，表示该变量任何类都可以直接访问，而且无需初始化类，直接使用类名.static 变量 这种形式访问即可。
2. 当static修饰方法时，代表该方法和类实例（对象）无关，任何类都可以直接访问（如果权限是public的话）。该方法内部也只能调用static修饰的方法
3. static修饰代码块后，代码块变为静态代码块，常常用于类启动之前，初始化一些值。
4. 被static修饰的方法，在类初始化的时候并不会初始化，只有当自己被调用时，才会被初始化

# final

1. 被final修饰的类，表明该类是无法继承的；
2. 被final修饰的方法，表明该方法是无法覆写的；
3. 被final修饰的变量，说明该变量在声明的时候就必须初始化完成，而且以后也不能修改起内存地址。

# volatile

常用来修饰某个共享变量，意思是当共享变量的值被修改后，会及时通知到其它线程上，其他线程就能知道当前共享变量的值已经被修改。

# transient

常用来修饰类变量，意思是当前变量是无需进行序列化的，在序列化时就会忽略该变量。

# default

一般用在接口的方法上，意思是对于该接口，子类是无需强制实现的，但自己必须有默认实现

# ArrayList

底层设计为简单数组
DEFAULT_CAPACITY：新建一个ArrayList，不传入initialCapacity时走无参构造，此时列表的初始容量为0，当执行add的时候才会进行扩容size：表示ArrayList的大小，类型为int，没有使用
volatile 修饰，非线程安全modCount：统计当前数组被修改的版本次数，数组结构有变动，就会+1
根据源码类描述，有：

1. 允许put null值，会自动扩容；
2. size isEmpty get set add等方法时间复杂度都是O(1);
3. 是非线程安全的，多线程情况下推荐使用线程安全类
4. 增强for循环，或者使用迭代器迭代过程中，如果大小被改变，会快速失败，抛出异常。
   方法注释：
5. 扩容是后的大小是原来容量的1.5倍
6. ArrayList中列表的最大值是Integer.MAX_VALUE, 超过这个值，JVM就不会给数组分配内存空间了
7. 新增时，没有对值进行严格较验，也是允许null值的

# LinkedList

底层数据结构是一个双向链表
只要机器内存足够强大，是没有大小限制的。
链表中的元素叫做Node
hash值 % n(数组大小) == (n-1) & hash

# Set API其实就是对Map API的组合。巧妙利用了Map的结构特点

初始容量（大小）= max((期望的值/0.75 + 1),16)

# 集合的坑

1. 当集合的元素是自定义类时，自定义类强制实现equals和hashCode方法，并且两个都要实现。
2. 所有集合类，在for循环进行删除时，直接使用集合类的remove方法进行删除会报ConcurrentModificationException错误，所以任意循环删除的场景下，最好用迭代器进行删除。
3. 数组转化为集合时，使用的Arrays.asList(array),这个方法有两个坑：
   -- 数组被修改后，会直接影响到新List的值
   -- 不能对新List进行add、remove等操作，否则会报UnsupportedOperationException错误
   形成原因是Arrays.asList方法返回的List并不是java.util.ArrayList,而是自己内部的一个静态类，该静态类直接持有数组的引用，并且没有实现add、remove等方法
4. 集合List转化为数组，通常使用的toArray方法，如果申明的数组大小小于List的大小的话，会得到一个元素均为null的数组。

*并发List、Map使用最多的是CopyOnWriteArrayList和ConcurrentHashMap，这两个并发类在安全和性能方面都很好*
*链表阻塞队列LinkedBlockingQueue底层数据结构是链表，并且队列是可阻塞的。*

# Spark SQL的性能优化技术

1. 内存列存储(in-memory columnar storage)
   Spark SQL不使用Java对象的方式进行存储，而是使用面向列的内存存储的方式进行存储。每一列作为一个数据存储的单位从而大大优化了内存使用的效率。减少了对内存的消耗，也避免了gc大量数据的性能开销。
2. 字节码生成技术(byte-code generation)
   Spark SQL在其catalust模块的expressions中增加了codegen模块，对于SQL语句中的计算表达式，比如：SELECT num+num FROM t这种sql，就可以使用动态字节码生成技术来优化其性能
3. Scala代码编写的优化
   对于Scala代码的编写，可能会造成较大性能开销的地方，自己重写，使用更加复杂的方式来获取更好的性能

# 队列

1.

队列本身是个容器，底层有不同的数据结构。比如LinkedBlockingQueue底层是链表结构，所以可以维持先入先出的顺序，比如DelayQueue底层可以是队列或堆栈，所以可以保证先入先出或者先入后出的顺序等等，底层的数据结构不同，也造成了操作实现不同；

2. 部分队列（如LinkedBlockingQueue），提供了暂时存储的功能，我们可以往队列里放数据，同时也可以从队列里拿数据，两者可以同时进行。
3. 队列把生产数据的一方和消费数据的一方进行解耦，生产者只管生产，消费者只管消费，两者之间没有必然联系，队列就像生产者和消费者之间的数据通道（LinkedBlockingQueue）
4. 队列还可以对消费者和生产者进行管理，比如队列满了，有生产者还在不停投递数据时，队列可以使生产者阻塞住，让其不再能投递，比如队列空时，有消费者过来拿数据时，队列可以让消费者holder住，等有数据时，唤醒消费者，让消费者拿数据返回
   如：ArrayBlockingQueue
5. 队列还提供阻塞的功能，比如我们从队列拿数据，但队列中没数据时，线程会一直阻塞到队列有数据可拿时才返回

# 队列和集合的区别

1.

相同点：队列（部分例外）和集合都提供了数据存储的功能，底层的存储数据结构是有些相似的，比如说LinkedBlockingQueue和LinkedHashMap，底层都是用了链表，ArrayBlockingQueue和ArrayList底层使用的都是数组。

2. 区别：部分队列和部分集合底层的存储结构很相似，但两者为了完成不同的事情，提供的API和其底层的操作实现是不同的队列提供了阻塞功能，队列解耦了生产者和消费者

# 队列的阻塞

队列阻塞主要提供了两种阻塞功能：

1.

LinkedBlockingQueue链表阻塞队列和ArrayBlockQueue数组阻塞队列是一类，前者容量是Integer的最大值，后者数组大小固定两个阻塞队列都可以指定容量大小，当队列满时，如果有线程put数据，线程会阻塞住，直到其他线程进行消费数据后，才会唤醒阻塞线程继续put。当队列空时，如果有线程take数据，线程会阻塞到队列不空时，继续take

2. SynchronousQueue同步队列，当线程put时，必须有对应线程把数据消费掉，put线程才能返回，当线程take时，需要有对应线程进行put数据时，take才能返回，反之则阻塞
   阻塞的底层实现：
   队列本事并没有实现阻塞功能，而是利用Condition的等待唤醒机制，阻塞底层实现就是更改线程的状态为沉睡
   LinkedBlockingQueue和ArrayBlockingQueue的区别：
   相同点：两者的阻塞机制大体相同，比如在队列满、空时，线程都会阻塞住
   不同点：LinkedBlockingQueue底层是链表结构，容量默认是Integer的最大值
   ArrayBlockingQueue底层是数组，容量必须在初始化时指定
   两者的底层结构不同，所以take put remove的底层实现也就不同
   当队列满时，使用put方法，会一直阻塞到队列不满为止，当队列空时，使用take方法，会一直阻塞到队列有数据为止
   put take都是无限阻塞的方法，容易使得线程全部都阻塞住，在大流量时，容易导致机器无线程可用，所以在流量大时，
   可用offer和poll方法来代替两者。只需要设置好超时阻塞的时间

# Java虚拟机中整数的四种类型

byte short int long 分别表示8位、16位、32位、64位有符号的整数

# 浮点型的两种类型

float double 分别表示32位、64位的浮点数

# synchronized 的缺陷

synchronized 是java中的一个关键字，也就是Java语言内置的特性，如果一个代码块被synchronized 修饰,如：synchronized(锁对象){}
或者
类方法被synchronized 修饰,如：private synchronized void method1(){}当一个线程获取了对应的锁，并执行该代码块时，其他线程便只能一直等待，等待获取锁的线程释放锁，
而这里获取锁的线程，释放锁只会有两种情况：

1. 获取锁的线程执行完了该代码块，然后线程释放对锁的占有；
2. 线程执行发生异常，此时JVM会让线程自动释放锁。
   synchronized缺点：
    1. 效率低
       锁的释放情况少，只在程序正常执行完成和抛出异常时释放锁
       试图获得锁是不能设置超时
       不能中断一个正在试图获得锁的线程
    2. 无法知道是否成功获取到锁

# Lock()

必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。相比synchronized，其提供了更多的功能*

1. java.util.concurrent.locks.Lock是一个接口，由于要实现其方法才能使用，所以一般都用java.util.concurrent.locks.ReentrantLock
2. java.util.concurrent.locks.ReentrantReadWriteLock是可重入读写锁，用于实现读写锁分离。
   *接口方法的使用*
   lock() 获取锁，如果锁被其他线程获取，则进行等待
   tryLock() 尝试获取锁，如果获取成功，则返回true，反之，则返回false
   tryLock(long time, TimeUnit unit) 尝试获取锁，在拿不到锁时会等待一定时间，之后如果获取成功，则返回true，反之，则返回false
   lockInterruptibly() 获取锁，如果线程在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态。
   unlock() 释放锁

# 创建线程池

1. 创建一个单线程化的线程池，只会用唯一的工作线程来执行任务，保证所有任务按指定顺序执行
   Executors.newSingleThreadExecutor();
2. 创建一个定长线程池，可控制线程最大并发数，支持定时及周期性任务执行
   Executors.newFixedThreadPool(5);
3. 创建一个可定期或者延时执行任务的定长线程池，支持定时及周期性任务执行
   Executors.newScheduledThreadPool(5);
4. 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
   Executors.newCachedThreadPool();

# CAS

即：Compare And Swap，比较和替换。
CAS机制当中使用了3个基本操作数：内存地址V 旧的预期值A 要修改的新值B
更新一个变量时，只有当变量的预期值A和内存地址V当中的实际值相同时，才会将内存地址V对应的值修改为B
当某一进程在对值进行修改时，如果发现预期值A与内存地址V中的实际值不相同，将会提交失败，然后自旋，继续下一次的修改，直到提交成功为止。
从思想上来说，Synchronized属于悲观锁，悲观的认为程序中的并发情况严重，所以严防死守，而CAS则属于乐观锁，乐观的认为程序中的并发情况不那么严重，所以让线程不断去尝试更新。
缺点：
CPU开销较大：在并发量比较高的情况下，如果许多线程反复尝试更新某一个变量，却又一直更新不成功，循环往复，会给CPU带来很大的压力
不能保证代码块的原子性：CAS机制所保证的只是一个变量的原子性操作，而不能保证整个代码块的原子性，比如需要保证3个变量共同进行原子性的更新，就不得不使用Synchronized
ABA问题：如果V值在其它线程中被改成B值后又改回A值，则当前线程无法分辨当前值是否被修改过。

# Java注解

早期，Java定义了一套注解，共有7个，3个在java.lang中，4个在java.lang.annotation中。

## 作用在代码的注解

@Override - 检查该方法是否是重写方法。如果发现其父类，或者事引用的接口中并没有该方法时，会报编译错误。
@Deprecated - 标记过时方法。如果使用该方法，会报编译警告。
@SuppressWarnings = 指示编译器去忽略注解中声明的警告。

## 作用在其他注解上的注解

@Retention - 标识这个注解怎么保存，是只在代码中，还是编入class文件中，或者是在运行时可以通过反射访问。
@Documented - 标识这些注解是否包含在用户文档中。
@Target - 标识这个注解应该作用于哪种Java成员。
@Inherited - 标识这个注解是继承于哪个注解类。

## 其他注解

@SafeVarargs - Java7 开始支持，忽略任何使用参数为泛型变量的方法或构造函数调用产生的警告。
@FunctionalInterface - Java8 开始支持，标识一个匿名函数或函数式接口。
@Repeatable - Java8 开始支持，标识某注解可以在同一个声明上使用多次。