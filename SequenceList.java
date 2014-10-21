import java.util.Arrays;
public class SequenceList<T>
{
	private int DEFAULT_SIZE = 16;
	private int capacity;          //顺序线性表底层数组的长度
	private Object[] elementData;  //定义数组用于保存顺序线性表的元素
	private int size = 0;          //保存顺序线性表元素的当前个数

	/**
	* 各类构造器
	* @param element 指定顺序线性表中的第一个元素
	* @param initSize 指定顺序线性表底层数组的长度
	*/
	public SequenceList()
	{
		capacity = DEFAULT_SIZE;
		elementData = new Object[capacity];
	}
	public SequenceList(T element)
	{
		this();
		elementData[0] = element;
		size++;
	}
	public SequenceList(T element,int initSize)
	{
		capacity = 1;
		while (capacity < initSize)
		{
			capacity <<= 1;
		}
		elementData = new Object[capacity];
		elementData[0] = element;
		size++;
	}

	//获取顺序线性表的大小
	public int length()
	{
		return size;
	}

	//正向查询
	public T get(int index)
	{
		if (index < 0 || index > size - 1)
		{
			throw new IndexOutOfBoundsException("Index is out of boundary");
		}
		return (T)elementData[index];
	}

	//反向查询
	public int locate(T element)
	{
		for (int index = 0; index < size; index++)
		{
			if (elementData[index].equals(element))
			{
				return index;
			}
		}
		return -1;
	}

	//扩充底层数组的长度
	private void ensureCapacity(int minCapacity)
	{
		if (minCapacity > capacity)
		{
			while (capacity < minCapacity)
			{
				capacity <<= 1;
			}
			elementData = Arrays.copyOf(elementData,capacity); //数组扩容
		}
	}

	//在顺序线性表指定索引处插入元素
	public void insert(T element,int index)
	{
		if (index < -1 || index > size)
		{
			throw new IndexOutOfBoundsException("Index is out of boundary");
		}
		ensureCapacity(size + 1);
		System.arraycopy(elementData,index,elementData,
			index + 1,size - index);
		elementData[index] = element;
		size++;
	}

	//在线性顺序表的开头处添加一个元素
	public void add(T element)
	{
		insert(element,size);
	}

	//删除顺序线性表指定索引处的元素
	public T delete(int index)
	{
		if (index < -1 || index > size - 1)
		{
			throw new IndexOutOfBoundsException("Index is out of boundary");
		}
		T oldValue = (T)elementData[index];
		int numMoved = size - index - 1;
		if (numMoved > 0)
		{
			System.arraycopy(elementData,index + 1,
				elementData,index,numMoved);
		}
		elementData[size--] = null;
		return oldValue;
	}

	//删除最后一个元素
	public T remove()
	{
		return delete(size - 1);
	}

	//判断顺序线性表是否为空
	public boolean empty()
	{
		return size == 0;
	}

	//清空顺序线性表
	public void clear()
	{
		Arrays.fill(elementData,null);
		size = 0;
	}

	//打印控制
	public String toString()
	{
		if (size == 0)
		{
			return "[]";
		}
		else
		{
			StringBuilder sb = new StringBuilder("[");
			for (int i = 0; i < size; i++)
			{
				sb.append(elementData[i].toString()
					+ ", ");
			}
			int len = sb.length();
			return sb.delete(len - 2,len).append("]").toString();
		}
	}

	public static void main(String[] args) 
	{
		SequenceList<String> list = new SequenceList<>();
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