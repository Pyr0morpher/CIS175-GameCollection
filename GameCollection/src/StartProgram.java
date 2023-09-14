/**
 * @publisher Corbin Goodman
 *  CIS175 - Fall 2023
 *  9/13/2023
 */

import java.util.List;
import java.util.Scanner;
import controller.ListGameHelper;
import model.ListGame;

public class StartProgram {
	// Creates a scanner and ListGameHelper object
	static Scanner in = new Scanner(System.in);
	static ListGameHelper lih = new ListGameHelper();

	// Adds a game
	private static void addAGame() {
		// Gets games title and publisher 
		System.out.print("Enter the games title: ");
		String title = in.nextLine();
		System.out.print("Enter the games publisher: ");
		String publisher = in.nextLine();

		// Creates a ListGame object
		ListGame toAdd = new ListGame(title, publisher);

		// Inserts the ListGame object into the database
		lih.insertGame(toAdd);
	}

	// Deletes a game
	private static void deleteAGame() {
		// Gets the games title and publisher
	    System.out.print("Enter the game title to delete: ");
	    String title = in.nextLine();
	    System.out.print("Enter the game publisher to delete: ");
	    String publisher = in.nextLine();
	    
	    // Creates a ListGame object
	    ListGame toDelete = new ListGame(title, publisher);

	    // Calls the deleteGame method to remove the game from the database
	    lih.deleteGame(toDelete);
	}
	
	// Edits a game
	private static void editAGame() {
		// Searches for game by either title or publisher
		System.out.println("How would you like to search your collection? ");
		System.out.println("1 : Search by Game Title");
		System.out.println("2 : Search by Game Publisher");
		int searchBy = in.nextInt();
		in.nextLine();

		// Searches for the game
		List<ListGame> foundGames;
		if (searchBy == 1) {
			System.out.print("Enter the Game Title: ");
			String titleName = in.nextLine();
			foundGames = lih.searchForGameByTitle(titleName);
		} else {
			System.out.print("Enter the Publisher's Name: ");
			String publisherName = in.nextLine();
			foundGames = lih.searchForGameByPublisher(publisherName);
		}
		
		// if the game exists
		if (!foundGames.isEmpty()) {
			System.out.println("Found Results.");
			for (ListGame l : foundGames) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();
			in.nextLine();

			ListGame toEdit = lih.searchForGameById(idToEdit);
			System.out.println("Retrieved " + toEdit.getTitle() + " by " + toEdit.getPublisher());
			System.out.println("1 : Update Title");
			System.out.println("2 : Update Publisher");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Title: ");
				String newTitle = in.nextLine();
				toEdit.setTitle(newTitle);
			} else if (update == 2) {
				System.out.print("New Publisher: ");
				String newPublisher = in.nextLine();
				toEdit.setPublisher(newPublisher);
			}
			lih.updateGame(toEdit);
		} else {
			System.out.println("---- No results found");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();
	}
	
	// create a menu 
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Check Out the Game Collection! ---");
		while (goAgain) {
			System.out.println("* Select an item:");
			System.out.println("* 1 -- Add a game");
			System.out.println("* 2 -- Edit a game");
			System.out.println("* 3 -- Delete a game");
			System.out.println("* 4 -- View the list");
			System.out.println("* 5 -- Exit the Collection");
			System.out.print("* Your selection is: ");
			int selection = in.nextInt();
			in.nextLine();

			switch (selection) {
		    case 1:
		        addAGame();
		        break;
		    case 2:
		        editAGame();
		        break;
		    case 3:
		        deleteAGame();
		        break;
		    case 4:
		        viewTheList();
		        break;
		    default:
		        lih.cleanUp();
		        System.out.println("Later!");
		        goAgain = false;
		        break;
		}
		}
	}
	
	// view the list
	private static void viewTheList() {
		List<ListGame> allGames = lih.showAllGames();

		for (ListGame singleGame : allGames) {
			System.out.println(singleGame.returnGameDetails());
		}
	}
}
