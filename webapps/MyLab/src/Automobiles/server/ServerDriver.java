package server;

import Adapter.BuildAuto;

public class ServerDriver {

	public static void main(String[] args) {
		new BuildAuto().BuildAuto("FordWagonZTW.txt");
		Server server = new Server();
		server.serve(6551);
	}

}
