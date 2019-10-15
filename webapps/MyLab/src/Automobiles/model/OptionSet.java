package model;
import exception.AutoException;
import java.util.ArrayList;

public class OptionSet implements java.io.Serializable{
	private ArrayList<Option> opt;
	private ArrayList<Option> choice;
	private String name;
	
	public OptionSet(){
		this.name = new String();
		opt = new ArrayList<Option>();
		choice = new ArrayList<Option>();
	}
	public OptionSet(String name, ArrayList<Option> opt) {
		this.opt = opt;
		this.name = name;
		choice = new ArrayList<Option>();
	}
	protected OptionSet(String n) {
		opt = new ArrayList<Option>();
		choice = new ArrayList<Option>();
		name = n;
	}
	protected OptionSet(int size, String n){
		opt = new ArrayList<Option>();
		choice = new ArrayList<Option>();
		name = n;
		for(int i = 0; i < size; i++)
			opt.add(new Option());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	protected void addOption(String name, float price) {
		opt.add(new Option(name, price));
	}
	
	protected void SetOption(int index, String str, float price) {
		Option op = opt.get(index);
		op.setName(str);
		op.setPrice(price);
		opt.set(index, op);
	}
	protected void SetOption(int index, float price) {
		Option option = opt.get(index);
		option.setPrice(price);
		opt.set(index, option);
	}
	protected void SetOption(int index, String str) {
		Option option = opt.get(index);
		option.setName(str);
		opt.set(index, option);
	}
	protected void UpdateOption(int index, String str, float price) {
		this.SetOption(index, price);
		this.SetOption(index, str);
	}
	protected void UpdateOption(String oldName, String Newname) throws AutoException {
		int index = this.findOption(oldName);
		if(index != -1)
			this.SetOption(index, Newname);
		else
			throw new AutoException(77, "Option " + oldName + " Not Found");
		
	}
	protected void UpdateOption(String currentName, float newprice) throws AutoException {
		int index = this.findOption(currentName);
		if(index != -1)
			this.SetOption(index, newprice);
		else
			throw new AutoException(89, "Option " + currentName + " Not Found");
	}
	protected void UpdateOption(int index, Option op) {
		opt.set(index, op);
	}
	
	protected void addOption(String name) {
		Option op = new Option(name);
		opt.add(op);
	}
	
	public int findOption(String name) {
		for(int i = 0; i < opt.size(); i++)
			if(opt.get(i).getName().toLowerCase().equals(name.toLowerCase()))
				return i;
		return -1;
	}
	@Override
	public String toString() {
		String str = '\n' + new String(this.name) + '\n';
		for(int i = 0; i < opt.size(); i++)
			str += opt.get(i).toString();
		return str;
	}
	
	public ArrayList<Option> getOpt() {
		return opt;
	}
	public void setOpt(ArrayList<Option> opt) {
		this.opt = opt;
	}
	
	public void addOption(Option opt) {
		for(int i = 0; i < this.opt.size(); i++) {
			if(this.opt.get(i).getName().contentEquals(opt.getName())) {
				this.opt.set(i, opt);
				return;
			}
		}
		this.opt.add(opt);
	}
	
	public void setOptionChoice( String optionName){
		int index = this.findOption(optionName);
		if(index != -1) {
			if(choice.size() == 0)
				choice.add(opt.get(index));
			else
				choice.set(0, opt.get(index));
		} else 
			System.out.println(optionName + "Doesn't Exist !!!'");
	}	
	public String getOptionChoice() {
		if(choice.size() > 0)
			return choice.get(0).getName();
		else
			return null;
	}
	
	public float getOptionChoicePrice() {
		if(choice.size() > 0)
			return choice.get(0).getPrice();
		else
			return 0;
	}
	
	public void deleteOption(int index) {
		opt.remove(index);
	}
	protected void deleteOption(String optName) {
		opt.remove(this.findOption(optName));
	}
}
