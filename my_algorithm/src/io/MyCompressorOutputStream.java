package io;

import java.io.IOException;
import java.io.OutputStream;
/**
 * @file  MyCompressorOutputStream.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class takes stream and compress it to a byte array
 * 
 * @date    02/09/2016
 */


public class MyCompressorOutputStream extends OutputStream {

	private OutputStream out;
	
	/**
	 * C-Tor
	 * 
	 * @param out - the stream out
	 */
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}
	

	/**
	 * OutputStream's function that must implement.
	 * It is responsible to handle single byte, check its state according to the last 
	 * incoming cell and eventually write to the outstream.
	 * 
	 * @param inpt - an new byteArray in order to compress
	 */
	@Override
	public void write(byte[] input) throws IOException {
		byte last = input[0];
		int count = 0;
		for (byte b : input) // Compressing the data
		{
			if(b == last)
			{
				count++;
			}
			else
			{
				out.write(count);
				out.write((int)last);
				count = 1;
				last = b;
			}
		}
		if(count > 0)
 		{
			out.write(count);
			out.write((int)last);
 		}
	}
	/**
	 * This function is overloading of the previous function. 
	 * It gets a byte reference and compress it.
	 * 
	 * @paran b- represents a byte
	 */
	@Override
	public void write(int b) throws IOException {
		out.write(b);
		
	}

}
