package com.example.spherebreak.framework;

//java-specific imports.
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

//android specific imports.
import android.content.res.AssetManager;
import android.os.Environment;

//my framework subpackage imports.
import com.example.spherebreak.framework.iFileIO;

public class FileIO implements iFileIO
{
	AssetManager assets;
	String externalStorage;
	
	public FileIO(AssetManager _assets)
	{
		this.assets = _assets;
		this.externalStorage = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}
	
	public InputStream ReadAsset(String _name) throws IOException
	{
		return assets.open(_name);
	}
	
	public InputStream ReadFile(String _name) throws IOException
	{
		return new FileInputStream(externalStorage + _name);
	}
	
	public OutputStream WriteFile(String _name) throws IOException
	{
		return new FileOutputStream(externalStorage + _name);
	}
}
