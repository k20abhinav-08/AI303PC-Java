import java.util.Scanner;

class SAnalyser2 {
    public static void main( String... args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String str = sc.nextLine();

        str = str.trim();

        
        String[] words = str.split(" ");
        int wordCount = words.length;

        System.out.println("Word Count: " + wordCount);

        
        int firstSpace = str.indexOf(" ");
        String firstWord = str.substring(0, firstSpace);

        
        int lastSpace = str.lastIndexOf(" ");
        String lastWord = str.substring(lastSpace + 1);

        System.out.println("First Word: " + firstWord);
        System.out.println("Last Word: " + lastWord);

        
        int firstIndex = str.indexOf('J');
        int lastIndex = str.lastIndexOf('J');

        System.out.println("First 'J' index: " + firstIndex);
        System.out.println("Last 'J' index: " + lastIndex);

       
        int comparison = firstWord.compareTo(lastWord);

        System.out.println("Comparison (first vs last word): " + comparison);

        
        char[] characters = str.toCharArray();

       
        char firstChar = characters[0];

        String firstCharString = String.valueOf(firstChar);

       
        int asciiValue = firstChar;

        IO.println("First character: " + firstCharString);
        IO.println("ASCII value: " + asciiValue);
    }
}