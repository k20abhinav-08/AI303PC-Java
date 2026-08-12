import java.util.Scanner;

class SecureTextFormatter {
    public static void main(String... args) {

        Scanner sc = new Scanner(System.in);

        IO.print("Enter full name: ");
        String fullName = sc.nextLine();

        IO.print("Enter email: ");
        String email = sc.nextLine();

        IO.print("Enter sentence: ");
        String sentence = sc.nextLine();

        // 1. Title Case
        String[] nameWords = fullName.toLowerCase().split(" ");
        String formattedName = "";

        for (String word : nameWords) {
            if (!word.isBlank()) {
                formattedName += word.substring(0, 1).toUpperCase()
                        + word.substring(1);
                formattedName += " ";
            }
        }

        formattedName = formattedName.trim();

        
        int atIndex = email.indexOf("@");
        String maskedEmail;

        if (atIndex >= 3) {
            String firstThree = email.substring(0, 3);
            String remaining = "*".repeat(atIndex - 3);
            maskedEmail = firstThree + remaining + email.substring(atIndex);
        } else {
            maskedEmail = email;
        }

        
        boolean validEmail = email.matches(
                "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        );

        
        String[] words = sentence.split(" ");
        String reversedWords = "";

        for (String word : words) {
            String reverse = "";

            char[] chars = word.toCharArray();

            for (int i = chars.length - 1; i >= 0; i--) {
                reverse += chars[i];
            }

            reversedWords += reverse + " ";
        }

        reversedWords = reversedWords.trim();

        // 5. Count vowels
        int vowelCount = 0;

        char[] sentenceChars = sentence.toLowerCase().toCharArray();

        for (char ch : sentenceChars) {
            if (ch == 'a' || ch == 'e' || ch == 'i' ||
                ch == 'o' || ch == 'u') {
                vowelCount++;
            }
        }
        IO.println("\nFormatted Name: " + formattedName);
        IO.println("Masked Email: " + maskedEmail);
        IO.println("Valid Email: " + validEmail);
        IO.println("Reversed Words: " + reversedWords);
        IO.println("Vowel Count: " + vowelCount);
        sc.close();
    }
}