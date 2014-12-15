package com.example.spherebreak.framework;

import android.media.SoundPool;

public class Sound 
{
	int id;
	SoundPool pool;
	
	public Sound(SoundPool _pool, int _id)
	{
		this.pool = _pool;
		this.id = _id;
	}
	
	public void Play(float _vol)
	{
		pool.play(id,  _vol, _vol, 0, 0, 1);
	}
	
	public void Dispose()
	{
		pool.unload(id);
	}
}
