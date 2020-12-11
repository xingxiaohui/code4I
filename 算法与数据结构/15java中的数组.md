# java中的数组
## 一、数组介绍
1. 数组（Array）是有序的元素序列，数组是用于储存多个相同类型数据的集合。
2. 如何创建一个数组(一维数组)
> 格式1：  
元素类型[] 数组名 = new 元素类型[元素个数或数组长度];   
示例：int[] arr = new int[5];  
格式2：  
元素类型[] 数组名 = new 元素类型[]{元素，元素，……};  
int[] arr = new int[]{3,5,1,7};  
int[] arr = {3,5,1,7};  

>给数组分配空间时，必须指定数组能够存储的元素个数来确定数组大小。创建数组之后不能修改数组的大小。可以使用length 属性获取数组的大小。
3. 一维数组的遍历
```java
int[] arr = {1,2,3,4,5};
// for循环遍历
for(int i=0; i< arr.length; i++){
    System.out.println(arr[i]);
}
// foreach遍历
for(int i: arr){
    System.out.println(i);
}
```
4. 二维数组  
>二维数组：实质就是存储是一维数组。  
数组定义：
数组类型[][] 数组名 = new 数组类型[一维数组的个数][每一个一维数组中元素的个数];

## 二、 数组的内存分析
![数组的内存分析](https://img-blog.csdn.net/20161030125110163)

## 三、Arrays类介绍
1. 介绍
>是JDK提供的专门用于操作数组的工具类，位于java.util包中。
直接调用Arrays类的方法操作数组，无需自己编码
2. Arrays类常用方法  
```java
// 比较两个数组是否相等
boolean equals(array1，array2)
// 对数组array元素进行升序排序
void sort(array)
// 该方法将会一个数组array转换成一个字符串
String toString(array)
// 把数组array所有元素都赋值为val
void fill(array，val)
// 把数组array复制成一个长度为length的新数组
copyof(array,length)
// 查询元素值val在数组array中下标
int binarySearch(array,val)
```