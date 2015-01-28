package com.dac.commons;

import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class JschExecTester {
	 public static void main(String[] arg){
		    try{
		      JSch jsch=new JSch();  
		      Session session=jsch.getSession(Constants.U, Constants.H, 22);
		      UserInfo ui=new MyUserInfo();
		      session.setUserInfo(ui);
		      session.connect();
		      Channel channel=session.openChannel("exec");
		      ((ChannelExec)channel).setCommand("grep \"\\[00:00:\" /log/241.66/tops/tops-front-purchaser\\(membermix-a1.nh.travelzen.cn\\)-debug-current.log");
		      channel.setInputStream(null);
		      ((ChannelExec)channel).setErrStream(System.err);
		      InputStream in=channel.getInputStream();
		      channel.connect();
		      byte[] tmp=new byte[1024];
		      while(true){
		        while(in.available()>0){
		          int i=in.read(tmp, 0, 1024);
		          if(i<0)break;
		          System.out.print(new String(tmp, 0, i));
		        }
		        if(channel.isClosed()){
		          if(in.available()>0) continue;
		          System.out.println("exit-status: "+channel.getExitStatus());
		          break;
		        }
		        try{Thread.sleep(1000);}catch(Exception ee){}
		      }
		      channel.disconnect();
		      session.disconnect();
		    }
		    catch(Exception e){
		      System.out.println(e);
		    }
		  }

		  public static class MyUserInfo implements UserInfo{
			String passwd;
		    public String getPassword(){ return passwd; }
		    public boolean promptYesNo(String str){
		       return true;
		    }
		    public String getPassphrase(){ return null; }
		    public boolean promptPassphrase(String message){ return true; }
		    public boolean promptPassword(String message){
		        passwd=Constants.P;
		        return true;
		    }
		    public void showMessage(String message){
		    	System.out.println(message);
		    }
		    
		  }
}
