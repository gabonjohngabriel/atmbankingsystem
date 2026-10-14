package bsit2e_g1.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {

	private static final Scanner s = new Scanner(System.in);

	public static String readString(String prompt) {
		System.out.print(prompt);
		return s.nextLine().trim();
	}

	public static double readDouble(String prompt, double minAmount) {
		while (true) {
			System.out.print(prompt);

			try {
				double value = s.nextDouble();
				s.nextLine(); // Clear buffer

				if (value >= minAmount) {
					return value;
				}

				System.out.println();

				System.out.printf("[ALABPoint] Amount must be at least PHP %.2f.\n", minAmount);

				System.out.println();
			} catch (InputMismatchException e) {
				s.nextLine(); // Clear buffer

				System.out.println();

				System.out.println("[ALABPoint] Invalid input. Please enter a valid numerical amount.\n");
			}
		}
	}

	public static int readInt(String prompt, int min, int max) {
		while (true) {
			System.out.print(prompt);
			try {
				int choice = s.nextInt();
				s.nextLine(); // Clear buffer

				if (choice >= min && choice <= max) {
					return choice;
				}

				System.out.println();

				System.out.printf("[ALABPoint] Please choose between %d and %d.\n", min, max);

				System.out.println();
			} catch (InputMismatchException e) {
				s.nextLine(); // Clear buffer

				System.out.println();

				System.out.println("[ALABPoint] Invalid input. Please enter a valid number.\n");
			}
		}
	}

	public static boolean choice(String message) {
		System.out.println("[ALABPoint] " + message);
		System.out.println("[1] Yes");
		System.out.println("[2] No");
		System.out.println();
		return readInt("Choice: ", 1, 2) == 1;
	}

	public static String choice(String message, String... choices) {
		System.out.println("[ALABPoint] " + message);

		for (int i = 0; i < choices.length; i++) {
			System.out.printf("[%d] %s\n", i + 1, choices[i]);
		}

		System.out.println();
		return choices[readInt("Choice: ", 1, choices.length) - 1];
	}
}
