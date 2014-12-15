package com.example.spherebreak.framework;

//java specific.
import java.io.IOException;

//android specific.
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

//homebrew.
import com.example.spherebreak.framework.iAudio;
import com.example.spherebreak.framework.Sound;
import com.example.spherebreak.framework.Music;

public class Audio implements iAudio
{
	AssetManager assets;
	SoundPool soundPool;
	
	//need to suppress warnings because SoundPool deprecated. 
	@SuppressWarnings("deprecation")
	public Audio(Activity _activity)
	{
		_activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = _activity.getAssets();
		
		//correct way to do this; requires API of 21. We're retrofitting, so use deprecated code.
		//AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
		//this.soundPool = new SoundPool.Builder().setAudioAttributes(attributes).build();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}

	@Override
	public Music CreateNewMusic(String _name, boolean _isLooping) 
	{
		try
		{
			AssetFileDescriptor desc = assets.openFd(_name);
			return new Music(desc, _isLooping);
		}
		catch(IOException _e)
		{
			throw new RuntimeException("Couldn't load music: " + _name + " .");
		}
	}

	@Override
	public Sound CreateNewSound(String _name) 
	{
		try
		{
			AssetFileDescriptor desc = assets.openFd(_name);
			int id = soundPool.load(desc, 0);
			return new Sound(soundPool, id);
		}
		catch(IOException _e)
		{
			throw new RuntimeException("Couldn't load sfx: " + _name + " .");
		}
	}
}
