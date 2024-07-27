Yaniv Kaveh - Shtul : 206768004
Elad Sztejnworcel : 204296495

Part 1 - 	Creation of the zoo visitors manager system.
		A worker can login into the system and order a ticket for a visitor.
		There are to types of zoo tickets: one time use \ annually.
		

		Need to know!!! WARNING:
			There is a known issue when starting main (from previous course), please try starting again a couple of time until it starts.	
			
			
		Interfaces:
		
			1. AuthenticationSystemInterface - Includes methods for creating new accounts, logging in, retrieving accounts, and checking for existing usernames and IDs.
			2. MenuInterface - provides methods for displaying and managing the main menu, exiting the application, and printing the menu options.
			3. ItemTypeInterface - defines methods for getting and setting the name, price, and index of an item type.
			4. VisitorSystemInterface - provides methods for managing tickets and subscriptions, including purchasing, canceling, finding by visitor ID, retrieving purchase history, and updating items.
			
			
		Input System:
			Input class - provides various methods for safely obtaining user inputs, including phone numbers, names, integers, dates, IDs, and booleans, with validation and error handling.
			
		NotificationSystem:
			Listener types: 
				1. SMSListener - for visitors only.
				2. MobileAppListener - for workers app only.
				
			Events:
				1. NEW_ITEM: 			New item created 	-> 	Visitors & Workers.
				2. SALE:	 			New Sale (discount) ->	created -> Visitors & Workers.
				3. ITEM_UPDATE			item price updated (if the price decreased sale event will be pushed also) -> Only workers.
				4. CUSTOMER_MESSAGE		SMS for all registered customers -> Customers only.
				5. WORKER_MESSAGE		Workers app notification -> workers only.
				
		Ticket package:
			1. ItemAbstract: Abstract class of tickets and subscriptions.
			2. Subscription: One year subscription - can be cancelled only before first using. can be issued unlimited times before expire date.
			3. Ticket: Entrance ticket - can be cancelled only before using. can be issued only once.
			
			Tickets.Types:
				1. SubscriptionType:  type of subscription - if its for a couple you can add second visitor ID.
				2. TicketType: 		  type of ticket.	
				3. CustomType:		  User's custom types - can be created and deleted.
				
				NOTE: 
				1. All types implemets ItemTypeInterface.
				2. All types has index, name, price fields.
				3. All types can be updated (change price only).
		
		Classes in packages tree:
		
			zoo:
				* main
			
			visitor_system:
				* Ticket
				* VisitorSystem
			
			interfaces:
				* TicketManager
				* visitorTicketManager
			
			human:
				* Visitor
				* Worker
			
			database:
				* VisitorDataBase
				* VisitorsManager
				* WorkerDataBase
				* WorkersManager


Part 2 - We changed the program file structure to apply the SOLID principle:

	The packages:
		
	 ZooAplication - main , Users.Worker, Users.Visitor, Users.Interfaces, User.Abstracts, Tickets.types.IDGenerators, Tickets.Types, Tickets, NotificationSystem.
			

	In order to make only one instance of the authentication system  we used the singleton design pattern.
	
	We made a notification system (NotificationSystem package) with the observer design pattern (Listener).

	In part one we already made the ticket usage option. (Users.Visitor -> VisitorManagmentSystem)

Part 3 - We added 2 new design pattern: Logger observer and factory.

	 We added a class diagram.


	
			
	
		
