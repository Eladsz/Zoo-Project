package Tickets.Types;

import interfaces.ItemTypeInterface;

public class CustomTicketType implements ItemTypeInterface{
		private String name;
		private int    price;
		private final int    index;
	
		CustomTicketType(int index, String name, int price) {
			this.name = name;
			this.price = price;
			this.index = index;
		}
	
		@Override
		public String getName() {
			return name;
		}
	
		@Override
		public int getPrice() {
			return price;
		}
	
		@Override
		public int getIndex() {
			return index;
		}

		@Override
		public void setPrice(int newPrice) {
			this.price = newPrice;
			
		}

		@Override
		public void setName(String newName) {
			this.name = newName;
			
		}
}
