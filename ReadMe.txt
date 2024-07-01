Yaniv Kaveh - Shtul : 206768004
Elad Sztejnworcel : 204296495

Part 1 - 	Creation of the zoo visitors manager system.
		A worker can login into the system and order a ticket for a visitor.
		There are to types of zoo tickets: one time use \ annually.
		

		Need to know!!! :
			There is a known issue when starting main (from previous course), please try starting again a couple of time until it starts.	

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


	
			
	
		