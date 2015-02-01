package com.greplog.ssh;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.greplog.cfg.CacheHolder;
import com.greplog.cfg.Robot;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class SSHExec {
	private CacheHolder cache;
	private Session session;
	private Channel channel;
	private String COMMOND = "exec";
	private InputStream in;
	

	public SSHExec() {
		super();
		cache = CacheHolder.newInstance();
	}
	
	public static void grep(String grep, File file) throws Exception{
		SSHExec exe = new SSHExec();
		exe.doGrep(grep, file);
	}

	@SuppressWarnings("resource")
	public void doGrep(String grep, File file) throws Exception{
		FileChannel fc = null;
		try{
			initSession();
			initChannel(grep);
			byte[] tmp = new byte[1024];
			fc = new FileOutputStream(file).getChannel();
			while(true){
			        while(in.available()>0){
			          int i=in.read(tmp, 0, 1024);
			          if(i<0)
			        	  break;
			          fc.write(ByteBuffer.wrap(tmp, 0, i));
			        }
			        if(channel.isClosed()){
			          if(in.available()>0) 
			        	  continue;
			          System.out.println("exit-status: "+channel.getExitStatus());
			          break;
			        }
			        try{Thread.sleep(1000);}catch(Exception ee){}
			 }
		}catch(Exception e){
			System.out.println(e);
		}finally{
			if(fc != null)
				fc.close();
			destory();
		}
	}
	
	private void destory(){
		channel.disconnect();
		session.disconnect();
	}
	
	private void initChannel(String grep) throws Exception{
		channel=session.openChannel(COMMOND);
	    ((ChannelExec)channel).setCommand(grep);
	    channel.setInputStream(null);
	    ((ChannelExec)channel).setErrStream(System.err);
	    channel.connect();
	}
	
	private void initSession()  throws Exception {
		Robot robot = cache.getUserInfo();
		JSch jsch=new JSch();  
		session = jsch.getSession(robot.getUsername(), robot.getPassword(), robot.getPort());
		UserInfo ui = (UserInfo)robot;
	    session.setUserInfo(ui);
	    session.connect();
	}
}
