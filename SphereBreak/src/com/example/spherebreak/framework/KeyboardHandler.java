package com.example.spherebreak.framework;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnKeyListener;

import com.example.spherebreak.framework.iInput.KeyEvent;
import com.example.spherebreak.framework.ObjectPool;
import com.example.spherebreak.framework.ObjectPool.ObjectPoolFactory;

public class KeyboardHandler implements OnKeyListener
{
	private boolean[] pressedkeys = new boolean[128];
	private ObjectPool<KeyEvent> keyEventPool;
	private List<KeyEvent> keyEventsBuffer = new ArrayList<KeyEvent>();
	private List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();
	
	public KeyboardHandler(View _view)
	{
		ObjectPoolFactory<KeyEvent> factory = new ObjectPoolFactory<KeyEvent>()
		{
			@Override
			public KeyEvent CreateObject()
			{
				return new KeyEvent();
			}
		};
		
		keyEventPool = new ObjectPool<KeyEvent>(factory, 100);
		_view.setOnKeyListener(this);
		_view.setFocusableInTouchMode(true);
		_view.requestFocus();
	}
	
	public boolean onKey(View _v, int _key, android.view.KeyEvent _e)
	{
		if(_e.getAction() == android.view.KeyEvent.ACTION_MULTIPLE)
		{
			return false;
		}
		
		synchronized(this)
		{
			KeyEvent event = keyEventPool.AddNewObject();
			event.keyCode = _key;
			event.keyChar = (char)_e.getUnicodeChar();
			
			if(_e.getAction() == android.view.KeyEvent.ACTION_DOWN)
			{
				event.type = KeyEvent.KEY_DOWN;
				if(_key > 0 && _key < 127)
				{
					pressedkeys[_key] = true;
				}
			}
			
			if(_e.getAction() == android.view.KeyEvent.ACTION_UP)
			{
				event.type = KeyEvent.KEY_UP;
				if(_key > 0 && _key < 127)
				{
					pressedkeys[_key] = false;
				}
			}
			
			keyEventsBuffer.add(event);
		}
		
		return false;
	}
	
	public boolean IsKeyPressed(int _key)
	{
		if(_key < 0 || _key > 127)
			return false;
		
		return pressedkeys[_key];
	}
	
	public List<KeyEvent> GetKeyEvents()
	{
		synchronized(this)
		{
			int l = keyEvents.size();
			for(int i = 0; i < l; i++)
			{
				keyEventPool.Free(keyEvents.get(i));
			}
			
			keyEvents.clear();
			keyEvents.addAll(keyEventsBuffer);
			keyEventsBuffer.clear();
			
			return keyEvents;
		}
	}
	
}
