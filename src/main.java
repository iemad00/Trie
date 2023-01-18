import java.util.Scanner;
public class main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Trie trie = null;
		
	
		int choice = input();

		while(choice != 7) {
			System.out.println("==================================================");

			if(choice == 1) {
				trie = new Trie();
				System.out.println("		Done: Trie created\n");
				choice = input();

			}
			else if(choice == 2) {
				System.out.print("Enter the initial list of letters: ");
				String word = input.nextLine().replace(" ", "").toUpperCase();
				char[] litters = word.toCharArray();
				trie = new Trie(litters);
				System.out.printf("		Done: Trie with initial list of letters (%s) created\n\n",word);
				choice = input();


			}	
			else if(choice == 3) {
				
				if(trie == null) 
					System.out.println("Error: You haven't create the trie yet!" + "\n");
				else {
					System.out.print("Enter a word to insert: ");
					String word = input.nextLine().replace(" ", "").toUpperCase();
					trie.insert(word);
				}
				System.out.println();
				choice = input();

			}
			else if(choice == 4) {
				if(trie == null) 
					System.out.println("Error: You haven't create the trie yet!" + "\n");
				else {
					System.out.print("Enter a word to delete: ");
					String word = input.nextLine().replace(" ", "").toUpperCase();
					trie.delete(word);
				}
				choice = input();				
			}
			else if(choice == 5) {
				if(trie == null) 
					System.out.println("Error: You haven't create the trie yet!" + "\n");
				else {
					System.out.print("Enter a prefix: ");
					String word = input.nextLine().replace(" ", "").toUpperCase();
					System.out.print("Found the following words: ");
					for(String prefix: trie.allWordsPrefix(word))
						System.out.print(prefix + " ");
					System.out.println("\n");
				}
				choice = input();

			}
			else if(choice == 6) {
				if(trie == null) 
					System.out.println("Error: You haven't create the trie yet!" + "\n");
				else
					System.out.println("The size= " + trie.size() + "\n");
				choice = input();

			}
		}
		System.out.println("==================================================");
		System.out.println("		Program Ended");
		System.out.println("==================================================");

	}
		


	
	
	public static void choices() {
		System.out.println("TRIE PROJECT: Enter your choice?");
		System.out.println("	1) Create an empty trie");
		System.out.println("	2) Create a trie with initial letters");
		System.out.println("	3) Insert a word");
		System.out.println("	4) Delete a word");
		System.out.println("	5) List all words that begin with a prefix");
		System.out.println("	6) Size of the trie");
		System.out.println("	7) End");
	}
	
	public static int input() {
		Scanner input = new Scanner(System.in);
		choices();
		int choice = input.nextInt();
		
		while(choice < 1 || choice > 7) {
			System.out.println("Error: please enter a number from the choices!");
			choices();
			choice = input.nextInt();
		}
		return choice;
	}

}