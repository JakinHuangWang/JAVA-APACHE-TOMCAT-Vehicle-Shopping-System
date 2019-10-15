package server;

public class Server implements Servable{
	public void serve(int port) {
		DefaultServerSocket DSS = new DefaultServerSocket(port);
		DSS.start();
	}

}
