package com.revature;

import java.util.Random;
import java.util.Scanner;

public class ScannerDriver {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		menu();
	}

	/*
	 * Create a menu that gives a user multiple options: - 1: get a random number -
	 * 2: Using StringBuffer, reverse a word of the user's choice - 3: exit the
	 * program This menu should repeat until the user decides to exit.
	 */

	public static void menu() {
		// while true keep looping through this menu
		// if user inputs 1 get a random number
		// if user inputs 2, using stringbuffer, reverse a word of user's choice
		// if user inputs 3, exit while loop and notify user somehow
		while (true) {
			System.out.println("Enter 1 for a random number, 2 to reverse a string, or 3 to exit");
			int userInput = sc.nextInt();
			if (userInput == 3) {
				System.out.println("Exit triggered!");
				break;
			}
			else if (userInput == 1) {
				System.out.println(ScannerDriver.randomNumberBetweenZeroAndUpperBound(2147483647));
			}
			else if (userInput == 2) {
				System.out.println("Enter a string to reverse, wrapped with quotations");
				sc.nextLine(); // flush
				String str = sc.nextLine();
				System.out.println(ScannerDriver.reverseString(str));
			}			
		}
		sc.close();

	}

	public static String reverseString(String str) {
		StringBuffer result = new StringBuffer("");
		for (int i = str.length() - 1; i >= 0; i--) {
			result = result.append(str.charAt(i));
		}
		return result.toString();
	}

	public static int randomNumberBetweenZeroAndUpperBound(int upperBound) {
		Random rand = new Random();
		return rand.nextInt(upperBound);
	}
}