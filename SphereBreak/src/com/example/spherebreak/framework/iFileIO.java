package com.example.spherebreak.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//interface for reading/writing data such as high-scores, assets, etc.
public interface iFileIO 
{
	//reading files into the application
	public InputStream ReadAsset(String _name) throws IOException;
	public InputStream ReadFile(String _name) throws IOException;
	
	//outputting to files for saved data.
	public OutputStream WriteFile(String _name) throws IOException;
}