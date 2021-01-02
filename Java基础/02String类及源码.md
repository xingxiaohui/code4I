# Java 中 String 相关知识点总结
## 一、String对象的创建
1. 以字面量形式赋值创建 String 对象，创建的对象被存放于字符串常量池中。
```java
String str1 = "a";
String str2 = "a";
System.out.println(str1 == str2);    // true
```
2. 使用 new 关键字创建 String 对象，创建的对象存放在堆中。
```java
String str1 = new String("a"); 
String str2 = new String("a"); 
String str3 = "a";
System.out.println(str1 == str2);    // false
System.out.println(str1 == str3);    // false
``` 
3. 使用常量拼接创建 String 对象，创建的对象存放于字符串常量池中，原理是编译优化；
```java 
String str1 = "ab";
String str2 = "a" + "b";    //编译优化后：String str1 = "ab";
final String a = "a";       // 使用final修饰的变量编译时当作常量
String str3 = a + "b";      //编译优化后：String str1 = "ab";
System.out.println(str1 == str2);    // true
System.out.println(str1 == str3);    // true
```
4. 创建 String 的拼接操作中含有一个及以上变量时，则创建的对象存放在堆中，原理是StringBuilder(jdk 1.5以前使用StringBuffer)的append操作。
```java
String a = "a";
String str1 = "ab";
String str2 = a + "b";
System.out.println(str1 == str2);   // false
```
5. 当使用String对象的intern方法时，将判断字符串常量池中是否含有对应的字符串，如果没有则在字符串常量池中创建并返回对应地址，如果有则直接返回对应地址。
```java
String str1 = new String("a");      //对象
String str2 = str1.intern();	    //字符串常量池
String str3 = "a";				    //字符串常量池
System.out.println(str1 == str3);   // false
System.out.println(str2 == str3);   // true
```
6. 使用连接符与append方法的效率对比
```java
long start = System.currentTimeMillis();
String str1 = "";
for(int i = 0; i< 100000; ++i){
    str1 += "a";
}
long end_1 = System.currentTimeMillis();
System.out.println(end_1 - start);    //3855ms

StringBuilder sb = new StringBuilder();
for(int i = 0; i< 100000; ++i){
    sb.append("a");
}
long end_2 = System.currentTimeMillis();
System.out.println(end_2 - end_1);	  //4ms
```
## 二、String的底层数据结构  
1. java9之前String底层数组的实现采用的是char数组
```java 
private final char value[];
```
2. java9之后，String底层采用byte数组和编码标识来识别
```java
private final byte value[];
private final byte coder;
```
>coder的用法：  
当检测到变量按照latin1或ISO进行标识时，会为其分配一个字节大小的空间；
当检测到变量按照utf-16进行标识时，会为其分配而两个字节大小的空间。
同样地，与String相关的StringBuilder,StringBuffer底层都采用了byte[]来实现。

## 三、String的equals方法
```java
// 底层使用 == 判断
if (this == anObject) {
    return true;
}
// 判断char数组的每一位
if (anObject instanceof String) {
    String anotherString = (String)anObject;
    int n = value.length;
    if (n == anotherString.value.length) {
        char v1[] = value;
        char v2[] = anotherString.value;
        int i = 0;
        while (n-- != 0) {
            if (v1[i] != v2[i])
                return false;
            i++;
        }
        return true;
    }
}
return false;
```
## 四、线程安全的字符串操作
StringBuilder与StringBuffer都继承自AbstractStringBuilder类，在AbstractStringBuilder中也是使用字符数组保存字符串。  
　　  1、在执行速度方面的比较：StringBuilder > StringBuffer ；   
　　  2、他们都是字符串变量，是可改变的对象，每当我们用它们对字符串做操作时，实际上是在一个对象上操作的，不像String一样创建一些对象进行操作，所以速度快；   
　  　3、 StringBuilder：线程非安全的；   
　　  4、StringBuffer：线程安全的；    

对于String、StringBuffer和StringBulider三者使用的总结：   
　　 1.如果要操作少量的数据用 = String   
　 　2.单线程操作字符串缓冲区 下操作大量数据 = StringBuilder   
　　 3.多线程操作字符串缓冲区 下操作大量数据 = StringBuffer  



