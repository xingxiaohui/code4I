# Java 基础知识点
## 一、打印 hello,world！
```java
public class B01_Basic {
	public static void main(String[] args){
		System.out.println("hello,world!");
	}
}
```
## 二、Java语法
1. 注释
>Java中使用双斜线//进行单行注释  
使用/**/进行多行注释
2. 关键字
![](https://cdn.jsdelivr.net/gh/xxkasi/image/img/20210102182335.png)
3. 类与方法的定义
```java
// 定义类
public class B01_Basic {
    // 定义方法
	public static void main(String[] args){
		System.out.println("hello,world!");
	}
}
```
## 三、基础数据类型
1. java提供八种基础数据类型，byte、short、int、long、float、double、boolean、char
![](https://cdn.jsdelivr.net/gh/xxkasi/image/img/20210102182448.png)
注意：  
Java 里使用 long 类型的数据一定要在数值后面加上 L，否则将作为整型解析：  
char a = 'h'char :单引号，String a = "hello" :双引号  

2. 每个基础变量都对应一个包装类，Byte、Short、Integer、Long、Float、Double、Boolean、Character  

3. 泛型
>Java 泛型（generics）是 JDK 5 中引入的一个新特性, 泛型提供了编译时类型安全检测机制，该机制允许程序员在编译时检测到非法的类型。泛型的本质是参数化类型，也就是说所操作的数据类型被指定为一个参数。  
Java 的泛型是伪泛型，这是因为 Java 在编译期间，所有的泛型信息都会被擦掉，这也就是通常所说类型擦除 。

## 四、运算符
1. 数学运算符：+、-、*、/(整除)、%(取模)
2. 逻辑运算符：&&、||、!
3. 自增自减运算符：++、--
>++和--运算符可以放在变量之前，也可以放在变量之后，当运算符放在变量之前时(前缀)，先自增/减，再赋值；当运算符放在变量之后时(后缀)，先赋值，再自增/减。

## 五、控制流
1. for循环
```java
for(int i=0; i<10; i++>){
    //do something
}
```
2. while循环
```java
while(true){
    // do something
}
```
3. continue、break、和 return 
>在循环结构中，当循环条件不满足或者循环次数达到要求时，循环会正常结束。但是，有时候可能需要在循环的过程中，当发生了某种条件之后 ，提前终止循环，这就需要用到下面几个关键词：  
continue ：指跳出当前的这一次循环，继续下一次循环。  
break ：指跳出整个循环体，继续执行循环下面的语句。  
return 用于跳出所在方法，结束该方法的运行。return 一般有两种用法：  
>>return; ：直接使用 return 结束方法执行，用于没有返回值函数的方法  
return value; ：return 一个特定值，用于有返回值函数的方法  

## 六、集合框架

## 七、并发基础






