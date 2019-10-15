package scale;
import java.util.*;
import model.*;
import Adapter.*;
import exception.*;

import model.Automobile;

public class EditOptions extends proxyAutomobile implements Runnable{
	private String someModel;
	private String opSetName;
	private String oldName;
	private String newName;
	private float Optionprice;

	public EditOptions(
			String modelname, String opSetName, String oldName, String newName, float price) {
		this.someModel = modelname;
		this.opSetName = opSetName;
		this.oldName = oldName;
		this.newName = newName;
		this.Optionprice = price;
	}
	
	public void addOption(String modelname, String opSetName, String name, float price) {
		Automobile auto = AlMap.get(modelname);
		try {
		auto.addOption(opSetName, name, price);
		AlMap.put(modelname, auto);
		}catch(AutoException AE) {
			AE.fix(AE.getErrno());
		}
	}

public void changeOption(String modelname, String opSetName, String oldName, String newName, float newPrice) {
	Automobile auto = AlMap.get(modelname);
	try {
	auto.UpdateOptionPrice(opSetName, oldName, newPrice);
	auto.UpdateOptionName(opSetName, oldName, newName);
	AlMap.put(modelname, auto);
	}catch(AutoException AE) {
		AE.fix(AE.getErrno());
	}
}

public void removeOption (String modelname, String opSetName,String name) {
	Automobile auto = AlMap.get(modelname);
	auto.deleteOption(opSetName, name);
	AlMap.put(modelname, auto);
}

public void run() {
	synchronized(System.out) {
	System.out.println(Thread.currentThread().getName() + " Running...");
	changeOption(someModel, opSetName, oldName, newName, Optionprice);
	}
}
}

