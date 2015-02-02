package com.greplog.cfg;

import java.io.File;
import java.io.IOException;

public class FilePathTest {

	public static void main(String ...s) throws IOException{
		System.out.println(new File("/Users/andy/greplog/.tmp/tmpfile").getParentFile().getPath());
		System.out.println(new File("/Users/andy/greplog/.tmp/tmpfile").getAbsolutePath());
		System.out.println(new File("/Users/andy/greplog/.tmp/tmpfile").getCanonicalPath());
		System.out.println(new File("/Users/andy/greplog/.tmp/tmpfile").getName());
	}
}
