package com.dac.commons;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ExecTester {
	public static void main(String... args)
            throws IOException {
		
        final SSHClient ssh = new SSHClient();
        ssh.loadKnownHosts();

        ssh.connect("192.168.130.74");
        try {
            ssh.authPassword(Constants.U, Constants.P);
            final Session session = ssh.startSession();
            try {
                final Command cmd = session.exec("tail /log/241.66/tops/tops-front-purchaser(membermix-a1.nh.travelzen.cn)-debug-current.log > /tmp/0000.1");
                //final Command cmd = session.exec("ls");
                System.out.println(IOUtils.readFully(cmd.getInputStream()).toString());
                cmd.join(5000, TimeUnit.MINUTES);
                System.out.println("\n** exit status: " + cmd.getExitStatus());
            } finally {
                session.close();
            }
        } finally {
            ssh.disconnect();
        }
    }


}
