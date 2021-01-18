package com.hcl.LockedMe.FileHandling;

import com.hcl.LockedMe.MainMenu.MainMenu;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class FileHandling {
	/*
	 * A folder called HCLFolder is created and it can have some files in it as well
	 * as empty.The path to the Folder is defined. TreeSet() constructor is used to
	 * build an empty TreeSet object, here sorted, in which elements will get stored
	 * in default natural sorting order.
	 */

	final static String FOLDER = "src/main/resources/HCLFolder";
	private static Scanner scanner;
	private static Set<String> sorted = new TreeSet<>();

	// File operations are displayed in this method
	public static void displayFileOperations() {
		System.out.println("----------------------------");
		System.out.println("1) Add a File");
		System.out.println("2) Delete a File");
		System.out.println("3) Search for a File");
		System.out.println("4) Back to Main Menu");
		System.out.println("----------------------------");
		MainMenu.FileOperations();
	}

	/*
	 * The createset method creates a treeset of all the files in our folder. The
	 * File(FOLDER).listFiles() method creates a list of pathnames of all the files
	 * in the FOLDER and makes it to an array 'files' which now contains all the
	 * files in the FOLDER. The for loop checks if every element in the folder is a
	 * file, and if it returns true the file is added to the treeset sorted.
	 */
	public static void createset() {
		File[] files = new File(FOLDER).listFiles();
		for (File file : files) {
			if (!file.isFile()) {
				continue;
			}
			sorted.add(file.getName());
		}
	}

	/*
	 * The filesInAscending order takes the treeset with all the files in the order
	 * from the createset method, since a treeset sorts the list of files in its
	 * natural order already, you just have to print the files in this method.
	 */
	public static void filesInAscendingOrder() {
		createset();
		System.out.println("----------------------------");
		System.out.println("Showing files in ascending order");
		sorted.forEach(System.out::println);
		System.out.println("----------------------------");
	}

	/*
	 * The following alternate method shows how the same sorting can be done using
	 * Array instead of a Treeset. The arraylist has a time complexity of O(n) for
	 * contains(), that comes up later, whereas the same for Treeset is O(logn). For
	 * the input of size n, an algorithm of O (n) will perform steps proportional to
	 * n, while another algorithm of O (log (n)) will perform steps roughly log (n).
	 * Clearly log (n) is smaller than n hence algorithm of complexity O (log (n))
	 * is better. Since it will be much faster.
	 */

	// The following code is just to show an alternate method to carry out the same
	// procedure.

	/*
	 * File fileDir = new File (FOLDER); List<String> listFile
	 * =Arrays.asList(fileDir.list()); Collections.sort(listFile);
	 * System.out.println("----------------------------");
	 * System.out.println("Sorting by filename in ascending order"); for(String
	 * s:listFile){ System.out.println(s);
	 * System.out.println("----------------------------");
	 */

	/*
	 * The addFile method is to add a file to the folder. The file to be added is
	 * specified by its path and is given by the user. The method checks if the path
	 * exists and if it returns true, the file is added to the folder.
	 */

	public static void addFile() throws InvalidPathException {
		System.out.println("Please provide a file path:");
		scanner = new Scanner(System.in);
		String filePath = scanner.nextLine();
		Path path = Paths.get(filePath);

		if (!Files.exists(path)) {
			System.out.println("File does not exist");
			return;
		}

		String newFilePath = FOLDER + "/" + path.getFileName();
		int inc = 0;
		while (Files.exists(Paths.get(newFilePath))) {
			inc++;
			newFilePath = FOLDER + "/" + inc + "_" + path.getFileName();
		}
		try {
			Files.copy(path, Paths.get(newFilePath));
		} catch (IOException e) {
			System.out.println("Unable to copy file to " + newFilePath);
		}
	}

	/*
	 * The set of files in the folder is initialized using the createset method. The
	 * file to be deleted is then taken from the user. The .contains() method checks
	 * whether the given file exist in the folder and if this returns true, the file
	 * is deleted using file.delete(). the deleted file is also removed from our set
	 * using .remove(). Case sensitivity of the file to be deleted is taken into
	 * account. The method returns a FNF ("File not found") if the file does not
	 * exist in the folder.
	 */

	public static void deleteFile() {
		createset();
		System.out.println("File to be deleted:");
		scanner = new Scanner(System.in);
		String fileName = scanner.nextLine();
		// Path path = Paths.get(filePath);
		if (sorted.contains(fileName)) {
			File file = new File(FOLDER + "/" + fileName);

			// delete file
			if (file.delete()) {
				System.out.println("File deleted");
				sorted.remove(fileName);
			} else {
				System.out.println("File was not deleted");
			}
		} else {
			System.out.println("File not found.");
		}
	}

	/*
	 * The set of files in the folder is initialized using the createset method. The
	 * file to be searched for is then taken from the user. The .contains() method
	 * checks whether the given file exist in the folder and if this returns true,
	 * output "File Found in Directory" is printed and the path of the same file is
	 * displayed alongside. If the return value is false, the output
	 * "File not Found in Directory" is printed.
	 */
	public static void searchFile() {
		createset();
		System.out.println("File to be searched for:");
		scanner = new Scanner(System.in);
		String fileName = scanner.nextLine();
		if (sorted.contains(fileName)) {
			System.out.println("File Found in Directory :" + FOLDER);
		} else
			System.out.println("File not Found in Directory. ");

	}

}
