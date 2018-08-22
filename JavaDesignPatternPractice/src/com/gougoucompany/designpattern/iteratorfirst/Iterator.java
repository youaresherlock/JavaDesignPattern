package com.gougoucompany.designpattern.iteratorfirst;

//在这里发生变化的是由不同集合类型所造成的遍历，我们可以想这些变化封装起来，我们可以利用迭代器接口生成具体的迭代器来复合不同形式的存储方式的餐厅类

//迭代器接口
public interface Iterator{
	boolean hasNext(); //返回一个布尔值，是否有下一个元素
	Object next(); // 返回下一个元素
}
 
