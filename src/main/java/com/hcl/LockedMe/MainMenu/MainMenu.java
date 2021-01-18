package com.hcl.LockedMe.MainMenu;

import java.util.Scanner;
import com.hcl.LockedMe.FileHandling.FileHandling;

public class MainMenu {
	private static Scanner scanner;

	public static void StartPoint() {
		WelcomeScreen();
		displayMainMenu();
	}

	// method to display the welcome screen and developer details
	private static void WelcomeScreen() {
		System.out.println("----------------------------");
		System.out.println("Application: LockedMe.com");
		System.out.println("Developer: Anita Shaji");
		System.out.println("----------------------------");
	}

	// method to display the Main Menu of the application containing 3 operations
	private static void displayMainMenu() {
		System.out.println("-------- MAIN MENU ---------");
		System.out.println("1) Show Files in Ascending Order");
		System.out.println("2) Perform File Operations");
		System.out.println("3) Close the Application");
		System.out.println("----------------------------");
		MainMenuOptions();
	}

	/*
	 * The option to be proceeded is taken from the user. The user can display the
	 * files in ascending order, perform file operations or close the application.
	 * If the user provides an option that is not listed, the application gives
	 * invalid input warning and asks the user to input any of the given options.
	 */
	public static void MainMenuOptions() {
		System.out.println("Please choose 1, 2 or 3:");
		scanner = new Scanner(System.in);
		String option = scanner.nextLine();
		switch (option) {
		case "1":
			FileHandling.filesInAscendingOrder();
			break;
		case "2":
			FileHandling.displayFileOperations();
		case "3":
			System.out.println("Thanks for using Lockedme.com. Closing application.");
			System.exit(0); // closes the application
			break;
		default:
			System.out.println("Invalid input provided, please choose 1, 2 or 3.");
		}
		displayMainMenu();
	}

	/*
	 * The program steps into this method when the user chooses File Operations from
	 * the Main Menu. The user is given options to add a file to the directory,
	 * delete a file from the directory and to search for a file in the directory.
	 * The user can also go back to the main menu from this method. If the user
	 * provides an option that is not listed, the application gives invalid input
	 * warning and asks the user to input any of the given options.
	 */
	public static void FileOperations() {
		System.out.println("Please choose 1, 2, 3 or 4:");
		scanner = new Scanner(System.in);
		String option = scanner.nextLine();
		switch (option) {
		case "1":
			FileHandling.addFile();
			break;
		case "2":
			FileHandling.deleteFile();
			break;
		case "3":
			FileHandling.searchFile();
			break;
		case "4":
			displayMainMenu();
			break;
		default:
			System.out.println("Invalid input provided, please choose 1, 2, 3 or 4.");
		}
		FileHandling.displayFileOperations();
	}

}
