import java.util.Scanner;

class ChatBox1 {
    public static void main(String...args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String str = sc.nextLine();

      
        str = str.trim();

     
        if (str.isEmpty()) {
            System.out.println("EMPTY_STRING");
            return;
        }


        if (str.equalsIgnoreCase("admin")) {
            System.out.println("RESERVED_WORD");
            return;
        }


        str = str.replaceAll(" ", " ");

        if (str.toLowerCase().startsWith("temp") &&
            str.toLowerCase().endsWith("user")) {

            str = str + "_FLAGGED";
        }


        System.out.println(str);
    }
}