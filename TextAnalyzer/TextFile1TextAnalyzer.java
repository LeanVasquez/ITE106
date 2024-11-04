import java.io.*;

public class TextFile1TextAnalyzer
{
	public static void main(String[] args) {
		// Gagamit tayo ng try-catch to avoid errors due to file handling
		try {
			analyzeFile();
		}
		catch (IOException e) {
			System.out.println("An error occured while trying to access file: " + e.getMessage());
		}
	} // end of main method
	
	// Throws IOException, kung magkaroon ng error sa file handling dito sa method na ito.
	// Ipapasa sa 'main' method.
	public static void analyzeFile() throws IOException {
		File file = new File("lean.txt"); // ung explanations dito nasa previous files ko nang sinend sa gc about sa text file manipulations
		
		// IF THE FILE DOES NOT EXIST - magkakaroon ng error na pwedeng e handle tulad sa baba.
		// ung (!file.exists()) na if statement block >> if not(file exists) <<
		// OR ipasa na lang natin sa main method para e handle yun sa try-catch.
		// dahil may throws IOException naman tayo dito sa analyzeFile na METHOD.
		// so ung throws na statement ay ipapasa ung error sa main method.
		
		// Tanggalin lang ang  /*  at  */
		/*
		if (!file.exists()) {
		    System.out.println("File Does Not Exist");
		    return;
		}
		*/
		
		String line;
		int wordCount = 0;
		int sentenceCount = 0;
		int longestWordLength = 0;
		String longestWord = null;
		
		// gagamitin pang basa ng file
		BufferedReader reader = new BufferedReader(new FileReader(file)); // ung explanations dito nasa previous files ko nang sinend sa gc about sa text file manipulations
		
		// will read the contents of the file - LINE BY LINE
		while ((line = reader.readLine()) != null) {
			// ang 'split()' ay METHOD ng STRING na hinihiwalay ang character BASED sa binigay na argument.
			// At ang RETURN VALUE ng 'split()' na method ay STRING ARRAY | String[].
			// E split natin lahat ng words sa LINE gamit ang argument na (" ") -
			// which means BLANK SPACES or WHITESPACES. THEN ilalagay yun sa ARRAY na wordArray
			// So kunwari " Hello World " magiging ["Hello", "World"]
			String[] wordArray = line.split("\\s+");
			wordCount += wordArray.length; // kung ilan ang laman ng wordArray, un na din ung bilang ng words
			
			// will read the LINES on the 'line' variable - WORD BY WORD
			// for-each loop ito. ung 'word' ang mag ta-traverse nung array na 'word'
			for (String word : wordArray) {
				// kung gusto niyo makita yung by word na inisplit tanggalin niyo yung  /*  ay  */  sa println statement
				/* 
				System.out.println(word);
				 */
				
				// ang 'replaceAll()' ay METHOD ng STRING na may parameters na
				// replaceAll(Regular Expression, Replacement) kung saan pag may nahanap na character -
				// na meron sa Regular Expression na ARGUMENT, ay papalitan ng Replacement na ARGUMENT
				// So dito sa baba, may Regular Expression na "[!?.]*$", at replacement na "" which is an empty string
				// Papaltan yung characters na yun ng empty string. In other words, tatanggalin or erase.
				String trimmedWord = word.replaceAll("[!?.]*$", "");
				// then check kung mas mahaba yung word sa current iteration kesa sa CURRENT LONGEST WORD (kung meron man)
				int wordLength = trimmedWord.length();
				
				// kung mas mahaba ung word sa current iteration, un na ang gawin na longest word
				if (wordLength > longestWordLength) {
				    longestWordLength = wordLength;
				    longestWord = trimmedWord;
				}
				
				// check kung may . ? or ! sa dulo ng word. Kung meron ibig sabihin -
				// end of sentence na iyon at plus 1 sa bilang ng sentence count
				// word.length() kunin ung haba ng word, pero ang index ay nagsisimula sa 0 kaya -
				// minus 1 para makuha ung last character ng word. or last element ng word
				char charSaDulo = word.charAt(word.length() - 1);
				if (charSaDulo == '.' || charSaDulo == '?' || charSaDulo == '!') {
					sentenceCount++;
				}
			}
		} // while loop end
		
		// Dito ay gumawa ako ng bagong file sa same directory.
		// Kung saan ika-copy ung content ng original na file PERO naka uppercase lahat.
		// Gagamit naman ng BufferedWriter at FileWriter indi na Reader.
		// Kung walang "hiPogiUppercase.txt" na file. Automatic na itong gagawin ng 'File' na OBJECT
		File newFile = new File("hiPogiUppercase.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
		
		// Dahil nasa dulo na ang reader, dahil ginamit sa previous while loop.
		// Reset natin ang reader sa simula ulit ng file BY closing and re-opening the reader.
		reader.close(); // Close the previous reader
		reader = new BufferedReader(new FileReader(file)); // Reopen the reader
		
		// Read again LINE BY LINE, convert to UPPERCASE, then write to the new file
		while ((line = reader.readLine()) != null) {
			writer.write(line.toUpperCase()); // Convert to uppercase then write to the new file
			writer.newLine(); // Add a new line in the new file (next line)
		}
		
		reader.close(); // Pag ginamit niyo isara niyo mwehehehe. To avoid resource leak.
		writer.close(); // Pag ginamit niyo isara niyo mwehehehe. To avoid resource leak.
		
		printResults(wordCount, sentenceCount, longestWord);
	}
	
	public static void printResults(int wordCount, int sentenceCount, String longestWord) {
		System.out.printf("==========================\n");
		System.out.printf("%-15s\t:  %d\n", "Word Count", wordCount);
		System.out.printf("%-15s\t:  %d\n", "Sentence Count", sentenceCount);
		System.out.printf("%-15s\t:  %s\n", "Longest Word", longestWord != null ? longestWord : "N/A");
		// Yung special characters sa printf, pampalinis ko lang yan sa output.
		// at yung may '?' ternary operator lang un. ung statement before ng '?' ay -
		// CONDITION. at ung statement after ng '?' ay >> if : else <<
	}
}
