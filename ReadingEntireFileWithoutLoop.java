import java.util.Scanner;
// Java Program to illustrate reading from FileReader
// using Scanner Class reading entire FileReader
// without using loop
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ReadingEntireFileWithoutLoop
{
	public static void main(String[] args)
		throws FileNotFoundException
	{
		File file =new File ("C:\\Users\\ppankaj\\Desktop\\test.txt");
		Scanner sc = new Scanner(file);
		
		// we just need to use \\z as delimiter
		sc.useDelimiter("\\z");
		
		System.out.println(sc.nextLine());
	}
}