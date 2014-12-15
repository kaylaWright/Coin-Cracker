//KW --> needs to be implemented, game specific! 

package com.example.spherebreak.framework;

import java.util.List;

public interface iInput 
{
	public static class KeyEvent
	{
		public static final int KEY_DOWN = 0;
		public static final int KEY_UP = 1;
		
		public int type;
		public int keyCode;
		public char keyChar;
		
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			if(type == KEY_DOWN)
			{
				builder.append("Key Down. ");
			}
			else
			{
				builder.append("Key Up. ");
			}
			
			builder.append(keyCode);
			builder.append(", ");
			builder.append(keyChar);
			return builder.toString();
		}
	}
	
	public static class TouchEvent
	{
		public static final int TOUCH_DOWN = 0;
		public static final int TOUCH_UP = 1;
		public static final int TOUCH_DRAGGED = 2;
		
		public int type;
		public int x, y;
		public int pointer;
		
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			if(type == TOUCH_DOWN)
			{
				builder.append("DOWN. ");
			}
			else if(type == TOUCH_UP)
			{
				builder.append("UP. ");
			}
			else
			{
				builder.append("DRAG. ");
			}
			
			builder.append(pointer);
			builder.append(" , ");
			builder.append(x);
			builder.append(" , ");
			builder.append(y);
			return builder.toString();
		}
	}
	
	//overridable functions.
	public boolean IsKeyPressed(int _code);
	public boolean IsTouchDown(int _pointer);
	public int GetTouchX(int _pointer);
	public int GetTouchY(int _pointer);
	//public float GetAccelX();
	//public float GetAccelY();
	//public float GetAccelZ();
	
	public List<KeyEvent> GetKeyEvents();
	public List<TouchEvent> GetTouchEvents();
}
