package client;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client implements Clientable{
	public void receive(int port) {
		InetAddress local = null;
		try {
			local = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.err.println("Identity Crisis!!!");
			System.exit(0);
		}
		DefaultSocketClient DSC = new DefaultSocketClient(local.getHostName(), port);
		DSC.start();
	}
}
