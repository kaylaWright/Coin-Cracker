package com.example.spherebreak.framework;

import java.util.ArrayList;
import java.util.List;

public class ObjectPool<T> 
{
	public interface ObjectPoolFactory<T>
	{
		public T CreateObject();
	}
	
	private final List<T> freeObjects;
	private final ObjectPoolFactory<T> factory;
	private final int maxSize;
	
	public ObjectPool(ObjectPoolFactory<T> _factory, int _max)
	{
		this.factory = _factory;
		this.maxSize = _max;
		this.freeObjects = new ArrayList<T>(maxSize);
	}
	
	public T AddNewObject()
	{
		T temp = null;
		
		if(freeObjects.size() == 0)
		{
			temp = factory.CreateObject();
		}
		else
		{
			temp = freeObjects.remove(freeObjects.size() - 1);
		}
		
		return temp;
	}
	
	public void Free(T _obj)
	{
		if(freeObjects.size() < maxSize)
		{
			freeObjects.add(_obj);
		}
	}
}
