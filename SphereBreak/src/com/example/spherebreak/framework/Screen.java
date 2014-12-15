package com.example.spherebreak.framework;

import com.example.spherebreak.framework.iInput.TouchEvent;

public abstract class Screen 
{
	protected final Game game;
	
	public Screen(Game _game)
	{
		this.game = _game;
	}
	
	public abstract void update(float _dt);
	public abstract void present(float _dt);
	public abstract void pause();
	public abstract void resume();
	public abstract void dispose();
	
	protected boolean InBounds(TouchEvent _e, int _x, int _y, int _x2, int _y2)
	{
		if(_e.x > _x && _e.x < _x + _x2 - 1)
		{
			if(_e.y > _y && _e.y < _y + _y2 - 1)
			{
				return true;
			}
		}
		return false;
	}
}
