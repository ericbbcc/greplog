package com.greplog.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.greplog.exception.FileException;
/**
 * 持久化字符串工具，不适合大量数据
 * @author andy
 *
 */
public class PersistenceUtils {
	public static String deserialize(File fileDir, String fileName) throws Exception{
		if(!fileDir.exists())
			throw new FileException("file not exists");
		File f = new File(fileDir.getPath(), fileName);
		if(!f.exists())
			throw new FileException("file not exists");
		FileInputStream in = new FileInputStream(f);
		FileChannel channel = in.getChannel();
		ByteBuffer b = ByteBuffer.allocate(1024);
		StringBuilder sb = new StringBuilder();
		while(true){
			b.clear();
			int i = channel.read(b);;
			if(i == -1)
				break;
			b.flip();
			sb.append(new String(b.array()));
		}
		channel.close();
		in.close();
		return sb.toString();
	}
	
	public static void serialize(String data, File fileDir, String fileName) throws Exception{
		if(!fileDir.exists()){
			if(!fileDir.mkdir())
				throw new FileException("create directory failed!");
		}
		File f = new File(fileDir.getPath(), fileName);
		FileOutputStream o = new FileOutputStream(f);
		ByteBuffer b = ByteBuffer.wrap(data.getBytes());
		FileChannel channel = o.getChannel();
		channel.write(b);
		channel.close();
		o.close();
	}
}
