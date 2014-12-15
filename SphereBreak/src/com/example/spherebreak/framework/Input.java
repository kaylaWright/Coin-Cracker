package com.example.spherebreak.framework;

import java.util.List;

import android.content.Context;
//import android.os.Build.VERSION;
import android.view.View;

import com.example.spherebreak.framework.iInput;

public class Input implements iInput
{
	KeyboardHandler keyHandler;
	SingleTouchHandler touchHandler;
	//not using the accelerometer; no need to both with those shenanigans.

	public Input(Context _context, View _view, float _sx, float _sy)
	{
		keyHandler = new KeyboardHandler(_view);
		touchHandler = new SingleTouchHandler(_view, _sx, _sy);
	}

	//keyboard input.
	@Override
	public boolean IsKeyPressed(int _code) 
	{
		return keyHandler.IsKeyPressed(_code);
	}
	
	@Override
	public List<KeyEvent> GetKeyEvents() 
	{
		return keyHandler.GetKeyEvents();
	}

	//touch input.
	@Override
	public boolean IsTouchDown(int _pointer) 
	{
		return touchHandler.isTouchDown(_pointer);
	}

	@Override
	public int GetTouchX(int _pointer) 
	{
		return touchHandler.GetTouchX(_pointer);
	}

	@Override
	public int GetTouchY(int _pointer) 
	{
		return touchHandler.GetTouchY(_pointer);
	}

	@Override
	public List<TouchEvent> GetTouchEvents() 
	{
		return touchHandler.GetTouchEvents();
	}
	
	//reimplement if accelerometer does need use. 
	/*@Override
	public float GetAccelX() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float GetAccelY() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float GetAccelZ() 
	{
		// TODO Auto-generated method stub
		return 0;
	}*/
	
}
