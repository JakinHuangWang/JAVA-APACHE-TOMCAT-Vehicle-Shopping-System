
package client;

import java.io.*;
import java.util.*;
import Adapter.*;
import model.*;

public class CarModelOptionsIO extends proxyAutomobile {
	
	public Object loadPropsFile(String fname) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(fname));
		}
		catch (FileNotFoundException e) {
			System.err.println("Error in file directory ... ");
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Error reading file from directory ... ");
			System.exit(1);
		}
		return props;
	}

	public Object loadTextFile(String fname) {
		return this.ConstructAuto(fname);
	}
}
