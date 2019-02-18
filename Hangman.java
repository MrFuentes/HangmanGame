import java.util.Scanner;
import java.util.Random;

public class Hangman {


	public static String [] PrevGuesses = new String[26];
	public static int CountGuesses = 0;
	public static Scanner in = new Scanner(System.in);
	public static String [] validChars = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"}; 
	public static String ProgressString;

	public static void main(String[] args){
		initialize();
	}


	public static void initialize() {
		for (int i = 0; i < 26; i++) {
			PrevGuesses[i] = null;
		}
		CountGuesses = 0;
		String word = getWord();
		String [] Progress = new String[word.length()];
		for (int i = 0; i < word.length(); i++) {
			Progress[i] = "_";
		}
		clearScreen();
		System.out.println("The word is");
		String ProgressString = String.join("", Progress);
		System.out.println(ProgressString);
		run(Progress, word);
	}


	public static void run(String[] Progress, String word) {
		clearScreen();
		ProgressString = String.join(" ", Progress);
		System.out.println(ProgressString);
		System.out.println("Guess a letter: ");
		if (CountGuesses > 0) {
			System.out.print("You've already guessed: ");
			for (int i = 0; i < CountGuesses - 1; i++) {
				System.out.print(PrevGuesses[i] + ", ");
			}
			System.out.print(PrevGuesses[CountGuesses - 1]);
			System.out.println();
		}
		String guess = in.next();
		if (guess.length() > 1) {
			System.out.println("Please only one letter");
			run(Progress, word);
		}
		boolean valid = false;
		for (int i = 0; i < validChars.length; i++) {
			if (guess.equals(validChars[i])) {
				valid = true;	
			}
		}
		if (valid == false){
			System.out.println(guess + " is not a valid character");
			run(Progress, word);
		}
		for (int i = 0; i < CountGuesses; i++) {
			if (guess.equals(PrevGuesses[i])){
				System.out.println("You already guessed " + guess + ".");
				run(Progress, word);
			}
		}
		PrevGuesses[CountGuesses] = guess;
		for (int i = 0; i < word.length(); i++) {
			if (word.substring(i, i+1).equals(guess)) {
				Progress[i] = "" + guess;
			}
		}	
		ProgressString = String.join("", Progress);
		if (ProgressString.equals(word)) {
			clearScreen();
			System.out.println("Well Done, the word was " + word + ".");
			System.out.println("It took you " + CountGuesses + " guesses.");
			again();
		}
		CountGuesses++;
		run(Progress, word);
	}


	public static void again() {
		System.out.print("Do you want to play again? (Y/N) ");	
		String answer = in.next();
		if (answer.equals(null)) {
			again();
		}
		if (answer.equals("Y") || answer.equals("y") || answer.equals("yes") || answer.equals("Yes")) {
			initialize();
		}
		else if (answer.equals("N") || answer.equals("n") || answer.equals("No") || answer.equals("no")) {
			clearScreen();
			System.exit(0);
		}
		else {
			again();
		}
	}



	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	

	public static String getWord() {
		Random rand = new Random();
		int RandLine = rand.nextInt(48);
		String[] PossibleWords = {
			"awkward",
			"bagpipes",
			"banjo",
			"bungler",
			"croquet",
			"cryptic",
			"dwarves",
			"frevid",
			"fishhook",
			"fjord",
			"gazebo",
			"gypsy",
			"haiku",
			"haphazard",
			"hyphen",
			"ivory",
			"jazz",
			"jiffy",
			"jinx",
			"jukebox",
			"kiosk",
			"kayak",
			"klutz",
			"memento",
			"mystify",
			"numbskull",
			"ostracize",
			"oxygen",
			"pyjama",
			"phlegm",
			"pixel",
			"polka",
			"quad",
			"quip",
			"rhythmic",
			"rogue",
			"sphinx",
			"squawk",
			"swivel",
			"toady",
			"twelfth",
			"unzip",
			"waxy",
			"wildebeest",
			"yacht",
			"zealous",
			"zigzag",
			"zippy",
			"zombie"
		};
		return PossibleWords[RandLine];
	}
}
