package com.example.spherebreak.framework;

import java.util.List;

import android.view.View.OnTouchListener;

import com.example.spherebreak.framework.iInput.TouchEvent;

public interface iTouchHandler extends OnTouchListener
{
	public boolean isTouchDown(int _p);
	public int GetTouchX(int _p);
	public int GetTouchY(int _p);
	public List<TouchEvent> GetTouchEvents();
}
