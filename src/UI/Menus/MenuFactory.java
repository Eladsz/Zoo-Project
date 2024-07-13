package UI.Menus;

public class MenuFactory {
	public enum MenuType {
		MAIN,
		LOGIN,
		ADD_NEW_ITEM,
		REMOVE_ITEM,
		UPDATE_ITEM_PRICE
		
	}
	
	public static Menu getMenu(MenuType type) {
		switch(type) {
			
		case MAIN:
			return new MainVisitorSystemMenu();
		case ADD_NEW_ITEM:
			return new AddNewItemMenu();
		case LOGIN:
			return new LoginMenu();
		case REMOVE_ITEM:
			return new RemoveItemMenu();
		case UPDATE_ITEM_PRICE:
			return new UpdateItemMenu();
		}
		System.out.println("ERROR: MenuFactory.getMenu() - returns null");
		return null;
	}
}
