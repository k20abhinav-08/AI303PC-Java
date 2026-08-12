import java.util.Scanner;

class PalindromeAnagram {
    static void main(){

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String str1 = sc.nextLine();

        System.out.print("Enter second string: ");
        String str2 = sc.nextLine();

        System.out.print("Enter third string: ");
        String str3 = sc.nextLine();

        
        String clean1 = str1.replaceAll("[^a-zA-Z0-9]", "");
        String lower1 = clean1.toLowerCase();

        
        String reverse = new StringBuilder(lower1).reverse().toString();
        boolean palindrome = lower1.equals(reverse);

        
        String clean2 = str2.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String clean3 = str3.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        char[] arr2 = clean2.toCharArray();
        char[] arr3 = clean3.toCharArray();

        java.util.Arrays.sort(arr2);
        java.util.Arrays.sort(arr3);

        boolean anagram = java.util.Arrays.equals(arr2, arr3);

        
        boolean valid = clean1.matches("[a-zA-Z0-9]+");

        
        boolean containsPlan = lower1.contains("plan");

        String report = String.format(
            "[Palindrome: %b | Anagram: %b]",
            palindrome,
            anagram
        );

    
        String finalReport = String.join("\n",
            "Palindrome Check: \"" + str1 + "\" -> " + palindrome,
            "Anagram Check: \"" + str2 + "\" & \"" + str3 + "\" -> " + anagram,
            "Formatted Report: " + report,
            "Contains 'plan': " + containsPlan
        );

        System.out.println("\n" + finalReport);

        // 6. String pool using intern() and hashCode()
        String a = new String("Java");
        String b = new String("Java");

        a = a.intern();
        b = b.intern();

        boolean hashEqual = a.hashCode() == b.hashCode();

        System.out.println(
            "HashCodes equal for interned strings: " + hashEqual
        );

        sc.close();
    }
}