package com.example.spherebreak.framework;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import android.view.View;

import com.example.spherebreak.framework.ObjectPool;
import com.example.spherebreak.framework.ObjectPool.ObjectPoolFactory;
import com.example.spherebreak.framework.iInput.TouchEvent;

public class SingleTouchHandler implements iTouchHandler
{
	private boolean isTouched;
	private int touchX, touchY;
	private float scaleX, scaleY;
	
	private ObjectPool<TouchEvent> touchEventPool;
	private List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
	private List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
	
	public SingleTouchHandler(View _v, float _sx, float _sy)
	{
		ObjectPoolFactory<TouchEvent> factory = new ObjectPoolFactory<TouchEvent>()
		{
			@Override
			public TouchEvent CreateObject()
			{
				return new TouchEvent();
			}
		};
		
		touchEventPool = new ObjectPool<TouchEvent>(factory, 100);
		_v.setOnTouchListener(this);
		
		this.scaleX = _sx;
		this.scaleY = _sy;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) 
	{
		synchronized(this)
		{
			TouchEvent e = touchEventPool.AddNewObject();
			switch(event.getAction())
			{
				case MotionEvent.ACTION_DOWN:
					e.type = TouchEvent.TOUCH_DOWN;
					isTouched = true;
					break;
				case MotionEvent.ACTION_MOVE:
					e.type = TouchEvent.TOUCH_DRAGGED;
					isTouched = true;
					break;
				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
					e.type = TouchEvent.TOUCH_UP;
					isTouched = false;
					break;
			}
			
			e.x = touchX = (int)(event.getX() * scaleX);
			e.y = touchY = (int)(event.getY() * scaleY);
			touchEventsBuffer.add(e);
			
			return true;
		}
	}

	@Override
	public boolean isTouchDown(int _p) 
	{
		synchronized(this)
		{
			if(_p == 0)
				return isTouched;
			else
				return false;
		}
	}

	@Override
	public int GetTouchX(int _p) 
	{
		synchronized(this)
		{
			return touchX;
		}
	}

	@Override
	public int GetTouchY(int _p) 
	{
		synchronized(this)
		{
			return touchY;
		}
	}

	@Override
	public List<TouchEvent> GetTouchEvents() 
	{
		synchronized(this)
		{
			int l = touchEvents.size();
			for(int i = 0; i < l; i++)
			{
				touchEventPool.Free(touchEvents.get(i));
			}
			
			touchEvents.clear();
			touchEvents.addAll(touchEventsBuffer);
			touchEventsBuffer.clear();
			
			return touchEvents;
		}
	}
	
	
}
