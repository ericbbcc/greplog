package com.greplog.util;

import java.io.File;

public class PersistenceUtilsTest {

	public static void main(String[] args) throws Exception{
		//PersistenceUtils.serialize("chen.lu", new File("/Users/andy/greplog/.tmp"), "tmpfile");
		System.out.println(PersistenceUtils.deserialize(new File("/Users/andy/greplog/.tmp"), "tmpfile"));
	}

}
