package com.sphereBreak;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.example.spherebreak.framework.FileIO;

public class Settings 
{
	//default values.
	public static boolean areSFXEnabled = true;
	public static boolean isMusicEnabled = true;
	public static int[] highScores = new int[] 
			{0, 0, 0, 0, 0, 0};
	
	public static void Load(FileIO _files)
	{
		BufferedReader in = null;
		try
		{
			in = new BufferedReader(new InputStreamReader(_files.ReadFile(".spherebreak")));
			areSFXEnabled = Boolean.parseBoolean(in.readLine());
			isMusicEnabled = Boolean.parseBoolean(in.readLine());
			for(int i = 0; i < 6; i++)
			{
				highScores[i] = Integer.parseInt(in.readLine());
			}
		}
		catch (IOException _e)
		{	}
		catch (NumberFormatException _e)
		{	}
		finally
		{
			try
			{
				if(in != null)
				{
					in.close();
				}
			}
			catch(IOException _e)
			{	}
		}
	}

	public static void Save(FileIO _files)
	{
		BufferedWriter out = null;
		
		try
		{
			out = new BufferedWriter(new OutputStreamWriter(_files.WriteFile(".spherebreak")));
			out.write(Boolean.toString(areSFXEnabled));
			out.write(Boolean.toString(isMusicEnabled));
			for(int i = 0; i < 6; i++)
			{
				out.write(Integer.toString(highScores[i]));
				out.write("\n");
			}
		}
		catch (IOException _e)
		{	}
		finally
		{
			try
			{
				if(out != null)
				{
					out.close();
				}
			}
			catch (IOException _e)
			{	}
		}
	}

	public static void AddScore(int _new)
	{
		for(int i = 0; i < 6; i++)
		{
			if(highScores[i] < _new)
			{
				for(int j = 5; j > i; i--)
				{
					highScores[j] = highScores[j - 1];
				}
				highScores[i] = _new;
				break;
			}
		}
	}
}
