package UI.Menus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import UI.Input;
import UI.Logger.LogLevel;
import UI.Logger.Logger;
import interfaces.MenuInterface;

public abstract class Menu implements MenuInterface{
	
	private Map<String, Consumer<Void>> options;
	private List<String> optionsNames;
	private String menuName;
	protected boolean keep = true;
	
	public Menu(String name) {
		setMenuName(name);
		options = new LinkedHashMap<String, Consumer<Void>>();
		optionsNames = new ArrayList<String>();
		AddOption("Back", v->exit());
	}
	
	public Menu(String name, String returnOption) {
		setMenuName(name);
		options = new HashMap<String, Consumer<Void>>();
		optionsNames = new ArrayList<String>();
		AddOption(returnOption, v->exit());
	}
	
	@Override
	public void display() {
		int i = 0;
		Logger.log("\n\n\n*********************************");
		Logger.log(menuName);
		for (String option : getOptionsNames()) {
			Logger.log((i++) + ". " + option);
		}
		Logger.log("*********************************");
		
	}
	
	@Override
	public void exit() {
		Logger.log("Exiting " + menuName + "...");
		return;
	}
	
	@Override
	public void mainMenu() {
		while(true) {

			display();
			Logger.log("Please choose an option:");
			int index = Input.getNumberInRange(0, getOptionsCount()-1);
			String option = getOptionsNames().get(index);
			
			try {
			  getOptions().get(option).accept(null);
				
			}
			catch (Exception e) {
				Logger.log("ERROR: " + e.getMessage(), LogLevel.ERROR);
				Logger.log("Please try again",LogLevel.ERROR);
			}
			
			if (index == 0)
				break;
			else 
				AfterAction();
			
			if(!keep)
				return;
		}
		
		
	}
	
	
	public void AfterAction() {
		
	}
	
	public void AddOption(String newOption, Consumer<Void> action) {
			optionsNames.add(newOption);
			getOptions().put(newOption, action);
	}
	
	
	
	public List<String> getOptionsNames(){
		return optionsNames;
	}
	
	public int getOptionsCount() {
		return getOptions().size();
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Map<String, Consumer<Void>> getOptions() {
		return options;
	}
	
	
	
}
