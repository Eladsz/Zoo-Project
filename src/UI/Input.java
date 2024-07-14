package UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import UI.Logger.LogLevel;
import UI.Logger.Logger;

public class Input {
	
	static Scanner scan = new Scanner(System.in);

    public static String getPhoneNumber() {
    	final String PHONE_PATTERN = "^[0-9]+$";
        String phoneNumber = "";
        boolean rep;
        do {
            rep = false;
            Logger.log("Enter your phone number: ");
            phoneNumber = scan.nextLine();
            if (!phoneNumber.matches(PHONE_PATTERN)) {
                rep = true;
                Logger.log("Error: The phone number must contain only digits\nTry Again: ", LogLevel.ERROR);
            }
        } while (rep);
        return phoneNumber;
    }
	
    public static String getName(String fieldName) {
        String name = "";
        final String NAME_PATTERN = "^[a-zA-Z ]+$";
        boolean rep;
        do {
            rep = false;
            Logger.log("Enter your "+ fieldName + ": ");
            name = scan.nextLine();
            if (!name.matches(NAME_PATTERN)) {
                rep = true;
                Logger.log("Error: The name must contain only alphabetic characters and spaces\nTry Again: ",LogLevel.ERROR);
            }
        } while (rep);
        return name;
    }
	
	public static int getInt() { 
		
		int number = 0;
		boolean rep;
		do {
			rep = false;
			try {
				number = scan.nextInt();
			} catch (InputMismatchException ex) { 
				rep = true;
				Logger.log("Error: The input must be an Integer\nTry Again: ");
				scan.nextLine();
			} catch (Exception ex) {
				Logger.log(ex.getMessage(),LogLevel.ERROR);
			}
		} while (rep);
		scan.nextLine();
		return number;
	}
	
	public static int getNumberInRange(int from, int to) {
		int num = 0;
		boolean rep;
		do {
			rep = false;
			try {
				num = scan.nextInt();
				
				if (num < from || num > to) {
					rep = true;
					scan.nextLine();
					Logger.log("Error: number out of range, you have to choose a number between "+ from + " - " + to + "\nTry Again: ",LogLevel.ERROR);
				}
				
			} catch(InputMismatchException ex) {
				rep = true;
				Logger.log("Error: The input must be an Integer\nTry Again: ");
				scan.nextLine();
			} catch (Exception ex) {
				Logger.log(ex.getMessage(),LogLevel.ERROR);
			}
			
		}while (rep);
		scan.nextLine();
		return num;
	}
	
	public static int getNumberInRange(int from, int to, String msg) {
		int num = 0;
		boolean rep;
		do {
			Logger.log(msg + ": ");
			rep = false;
			try {
				num = scan.nextInt();
				
				if (num < from || num > to) {
					rep = true;
					scan.nextLine();
					Logger.log("Error: number out of range, you have to choose a number between "+ from + " - " + to + "\nTry Again: ",LogLevel.ERROR);
				}
				
			} catch(InputMismatchException ex) {
				rep = true;
				Logger.log("Error: The input must be an Integer\nTry Again: ");
				scan.nextLine();
			} catch (Exception ex) {
				Logger.log(ex.getMessage(),LogLevel.ERROR);
			}
			
		}while (rep);
		scan.nextLine();
		return num;
	}
	
    public static int getIDFromUser() {
        int num = 0;
        boolean rep;
        do {
            rep = false;
            Logger.log("Enter your ID (a 9-digit number): ");
            try {
                num = scan.nextInt();
                
                // Check if the number has exactly 9 digits
                if (String.valueOf(num).length() != 9) {
                    rep = true;
                    scan.nextLine(); // Clear the buffer
                    Logger.log("Error: The number must have exactly 9 digits\nTry Again: ", LogLevel.ERROR);
                }
                
            } catch (InputMismatchException ex) {
                rep = true;
                Logger.log("Error: The input must be an Integer\nTry Again: ");
                scan.nextLine(); // Clear the buffer
            } catch (Exception ex) {
                Logger.log(ex.getMessage(),LogLevel.ERROR);
            }
            
        } while (rep);
        scan.nextLine(); // Clear the buffer
        return num;
    }
    
    public static LocalDate getPastDate(String description) {
        LocalDate birthdate = null;
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        boolean rep;
        do {
            rep = false;
            Logger.log("Enter your "+ description + " (dd-MM-yyyy): ");
            String input = scan.nextLine();
            try {
                birthdate = LocalDate.parse(input, formatter);
                if (birthdate.getYear() > LocalDate.now().getYear())
                {
                	Logger.log("Error: Invalid date. Please Try Again:\n");
                	rep = true;
                }
                
            } catch (DateTimeParseException ex) {
                rep = true;
                Logger.log("Error: The input must be a valid date in the format dd-MM-yyyy. Please Try Again: ");
            } catch (Exception ex) {
                Logger.log(ex.getMessage(),LogLevel.ERROR);
            }
            
        } while (rep);
        return birthdate;
    }
    
    public static LocalDate getTicketDate() {
        LocalDate date = null;
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        boolean rep;
        do {
            rep = false;
            Logger.log("Enter the ticket's date (dd-MM-yyyy): ");
            String input = scan.nextLine();
            try {
                date = LocalDate.parse(input, formatter);
                if (date.getYear() < LocalDate.now().getYear())
                {
                	Logger.log("Error: Invalid date. Please Try Again:");
                	rep = true;
                }
                
            } catch (DateTimeParseException ex) {
                rep = true;
                Logger.log("Error: The input must be a valid date in the format dd-MM-yyyy. Please Try Again: ");
            } catch (Exception ex) {
                Logger.log(ex.getMessage(),LogLevel.ERROR);
            }
            
        } while (rep);
        return date;
    }
    
    
	
	public static boolean getBoolean() {
		boolean rep;
		boolean val = false;
		do {
			rep = false;
			try {
				val = scan.nextBoolean();
			} catch (InputMismatchException ex) {
				rep = true;
				Logger.log("Error: invalid input\nPlease Enter 'True' or 'False':");
				scan.nextLine();
			} catch (Exception ex) {
				Logger.log(ex.getMessage(), LogLevel.ERROR);
			}
		} while (rep);
		
		return val;
	}
	
    
    public static String getString(String prompt) {
        Logger.log(prompt);
        return scan.nextLine();
    }
    
	public static String getTicketType() {
		Logger.log("Please enter the ticket type:");
        String type = scan.nextLine().trim().toLowerCase();
        if (type.isEmpty()) {
            return "";
        }
        return type.substring(0, 1).toUpperCase() + type.substring(1);
	}

}
