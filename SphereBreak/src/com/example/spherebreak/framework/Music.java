package com.example.spherebreak.framework;

//java imports.
import java.io.IOException;

//android imports.
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class Music implements OnCompletionListener
{
	MediaPlayer player;
	boolean isReady = false;
	
	public Music(AssetFileDescriptor _desc, boolean _isLooping)
	{
		player = new MediaPlayer();
		try
		{
			player.setDataSource(_desc.getFileDescriptor(), _desc.getStartOffset(), _desc.getLength());
			player.prepare();
			isReady = true;
			player.setOnCompletionListener(this);
			player.setLooping(_isLooping);
		}
		catch (Exception _e)
		{
			throw new RuntimeException("Couldn't load music.");
		}
	}
	
	public void Dispose()
	{
		if(player.isPlaying())
		{
			player.stop();
		}
		player.release();
	}
	
	public boolean IsLooping()
	{
		return player.isLooping();
	}
	
	public boolean IsPlaying()
	{
		return player.isPlaying();
	}
	
	public boolean IsStopped()
	{
		return !isReady;
	}
	
	public void Pause()
	{
		if(player.isPlaying())
			player.pause();
	}
	
	public void Play()
	{
		if(player.isPlaying())
			return;
		
		try
		{
			synchronized(this)
			{
				if(!isReady)
				{
					player.prepare();
				}
				player.start();
			}
		}
		catch (IllegalStateException _e)
		{
			_e.printStackTrace();
		}
		catch (IOException _e)
		{
			_e.printStackTrace();
		}
	}
	
	public void SetLooping(boolean _looping)
	{
		player.setLooping(_looping);
	}
	
	public void SetVolume(float _vol)
	{
		player.setVolume(_vol, _vol);
	}
	
	public void Stop()
	{
		player.stop();
		synchronized(this)
		{
			isReady = false;
		}
	}
	
	@Override
	public void onCompletion(MediaPlayer _player)
	{
		synchronized(this)
		{
			isReady = false;
		}
	}
}
