### CopyListWithRandom

Leetcode #138

一种特殊的单链表节点类描述如下：
```
Class Node {
	Int value;
	Node next;
	Node rand;
	Node (int val) {
		Value = val;
	}
}
```

rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。给定一个Node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
要求：时间复杂度O(N)，额外空间复杂度O(1)

### FindFirstIntersectNode

两个单链表相交的一系列问题，相交即共有一个节点内存地址一样

给定两个可能有环也可能无环的单链表，头节点head1和head2. 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null

### IsPalindromeList

判断一个链表是否为回文结构。给定一个单链表的头节点head，判断该链表是否是回文结构，如1-2-1，返回true，1-2-2-1，返回true。15-6-15，返回true，1-2-3，返回false

### SmallerEqualBigger

将单向链表按某值划分成左边小、中间相等、右边大的形式
给定一个单链表的头节点head，节点的值类型是整型，再给定一个整数pivot。实现一个调整链表的函数，将链表调整为左部分都是值小于pivot的节点，中间部分都是值等于pivot的节点，有部分都是值大于pivot的节点。

### RandomPool

自定义可以增删随机返回数据的数据结构

### BitMap

用普通数据类型构建一个bit Map