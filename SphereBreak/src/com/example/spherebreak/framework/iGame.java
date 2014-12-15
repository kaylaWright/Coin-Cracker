package com.example.spherebreak.framework;

public interface iGame 
{
	public Input GetInput();
	public FileIO GetFileIO();
	public Graphics GetGraphics();
	public Audio GetAudio();
	
	public void SetScreen(Screen _screen);
	public Screen GetCurrentScreen();
	public Screen GetStartScreen();
}
