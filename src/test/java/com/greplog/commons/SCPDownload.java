package com.greplog.commons;

import java.io.IOException;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.xfer.FileSystemFile;

public class SCPDownload {
    public static void main(String[] args)
            throws IOException {
        SSHClient ssh = new SSHClient();
        // ssh.useCompression(); // Can lead to significant speedup (needs JZlib in classpath)
        ssh.loadKnownHosts();
        ssh.connect("192.168.130.74");
        try {
        	ssh.authPassword("shui.ren", "OwdyeepIj3");
            ssh.newSCPFileTransfer().download("/log/241.66/tops/tops-front-purchaser(membermix-a1.nh.travelzen.cn)-debug-current.log", new FileSystemFile("/tmp/"));
        } finally {
            ssh.disconnect();
        }
    }
}
