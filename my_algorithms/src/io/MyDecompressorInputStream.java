package io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @file  MyDecompressorInputStream.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class takes byte array and deompress it to a stream
 * 
 * @date    02/09/2016
 */

public class MyDecompressorInputStream extends InputStream { 

	private InputStream in;

	/**
	 * C-Tor
	 * 
	 * @param in - the stream in (to read from)
	 */
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
	}

	@Override
	public int read() throws IOException {
		return in.read();
	}

	/**
	 * This function is overloading of the previous function. 
	 * It actually read from the file and de-compress the byteArray
	 * 
	 * @outBuffer the out param (byte array) to fill with the de-compressed maze
	 * 
	 * @return 0 for success, -1 for failed
	 */
	@Override
	public int read(byte[] b) throws IOException{
		int count;
		byte status;
		int bcounter = 0;
		while(in.available() > 0) // Decompressing the data
		{
			count = in.read();
			status = (byte)in.read();
			for(int j = 0; j < count; j++)
			{
				b[bcounter+j] = status;
			}
			bcounter += count;
		}
		
		return 1;
	}
}