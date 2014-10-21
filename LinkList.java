public class LinkList<T>
{
	private class Node
	{
		private T data;
		private Node next;
		public Node() {}
		public Node(T data,Node next)
		{
			this.data = data;
			this.next = next;
		}
	}
	private Node head;
	private Node tail;
	private int size;

	//构造器
	public LinkList()
	{
		head = null;
		tail = null;
	}
	public LinkList(T element)
	{
		head = new Node(element,null);
		tail = head;
		size++;
	}

	//还回链表长度
	public int length()
	{
		return size;
	}

	//查询：通过索引查询节点
	public Node getNodeByIndex(int index)
	{
		if (index < 0 || index > size - 1)
		{
			throw new IndexOutOfBoundsException("Index is out of boundary");
		}
		int i = 0;
		Node current = head;
		while (current != null)
		{
			if (i == index)
			{
				return current;
			}
			current = current.next;
			i++;
		}
		return null;
	}

	//正向查询
	public T get(int index)
	{
		return getNodeByIndex(index).data;
	}

	//反向查询
	public int locate(T element)
	{
		Node current = head;
		int index = 0;
		while (current != null)
		{
			if (current.data.equals(element))
			{
				return index;
			}
			index++;
			current = current.next;
		}
		return -1;
	}

	//插入：指定位置插入一个元素
	public void insert(T element,int index)
	{
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException("Index is out of boundary");
		}
		if (head == null)
		{
			add(element);
		}
		else
		{
			if (index == 0)
			{
				addAtHead(element);
			}
			else
			{
				Node prev = getNodeByIndex(index - 1);
				prev.next = new Node(element,prev.next);
				size++;
			}
		}
	}

	//插入：尾插法
	public void add(T element)
	{
		if (head == null)
		{
			head = new Node(element,null);
			tail = head;
		}
		else
		{
			Node newNode = new Node(element,null);
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	//插入：头插法
	public void addAtHead(T element)
	{
		head = new Node(element,head);
		if (tail == null)
		{
			tail = head;
		}
		size++;
	}

	//删除：指定位置处元素
	public T delete(int index)
	{
		if (index < 0 || index > size - 1)
		{
			throw new IndexOutOfBoundsException("Index is out of boundary");
		}
		Node delNode = null;
		if (index == 0)
		{
			delNode = head;
			head = head.next;
		}
		else
		{
			Node prev = getNodeByIndex(index - 1);
			delNode = prev.next;
			prev.next = delNode.next;
		}
		delNode.next = null;
		size--;
		return delNode.data;
	}

	//删除：最后一个元素
	public T remove()
	{
		return delete(size - 1);
	}

	//是否为空
	public boolean empty()
	{
		return size == 0;
	}

	//清空
	public void clear()
	{
		head = null;
		tail = null;
		size = 0;
	}

	//打印控制
	public String toString()
	{
		if (empty())
		{
			return "[]";
		}
		else
		{
			StringBuilder sb = new StringBuilder("[");
			for (Node current = head; current != null; 
				current = current.next)
			{
				sb.append(current.data.toString() + ", ");
			}
			int len = sb.length();
			return sb.delete(len-2,len).append("]").toString();
		}
	}

	public static void main(String[] args)
	{
		LinkList<String> list = new LinkList<>();
		list.add("aaaa");
		list.add("bbbb");
		list.add("cccc");
		list.insert("dddd",1);
		System.out.println(list);
		list.delete(2);
		System.out.println(list);
		System.out.println("where is 'cccc': "
			+ list.locate("cccc"));
		System.out.println("value of index '2': " + list.get(2));
	}
}