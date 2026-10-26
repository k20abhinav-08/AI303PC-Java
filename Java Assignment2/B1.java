
import java.util.*;
import java.io.IO;

class B1 {

    void main() {

        /*
         * Problem Statement:
         * Maintain an inventory of products using the List interface.
         * Perform insertion, updating, removal, searching, sorting,
         * traversal and array conversion using collection methods.
         */

        ArrayList<Product> products = new ArrayList<>();

        // Adding six products.
        products.add(new Product(101, "Keyboard", 499, 5));
        products.add(new Product(102, "Wireless Mouse", 799, 3));
        products.add(new Product(103, "Monitor", 8500, 2));
        products.add(new Product(104, "USB Cable", 199, 0));
        products.add(new Product(105, "Headphones", 1299, 4));
        products.add(new Product(106, "Webcam", 2199, 0));

        IO.println("INITIAL INVENTORY");
        displayUsingIterator(products);

        // Insert a product at the second position.
        products.add(1, new Product(107, "Laptop Stand", 999, 6));

        IO.println("\nAFTER INSERTION");
        displayUsingForEach(products);

        // Update the price of the product with ID 105.
        for (Product product : products) {
            if (product.getProductId() == 105) {
                product.setPrice(1199);
                break;
            }
        }

        IO.println("\nAFTER PRICE UPDATE");
        products.forEach(product -> IO.println(product));

        // Remove products with zero quantity using Iterator.
        int removedCount = 0;

        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();

            if (product.getQuantity() == 0) {
                iterator.remove();
                removedCount++;
            }
        }

        IO.println("\nREMOVED " + removedCount
                + " products with zero quantity");

        // Search using contains() and indexOf().
        Product searchProduct =
                new Product(0, "Wireless Mouse", 0, 0);

        int searchIndex = products.indexOf(searchProduct);

        if (products.contains(searchProduct) && searchIndex != -1) {
            IO.println("SEARCH: Found 'Wireless Mouse'");
            IO.println(products.get(searchIndex));
        } else {
            IO.println("Product not found");
        }

        // Sort by price in ascending order using Comparator.
        products.sort(Comparator.comparingDouble(Product::getPrice));

        IO.println("\nSORTED BY PRICE");
        displayUsingIterator(products);

        // Sort alphabetically using Comparable.
        Collections.sort(products);

        IO.println("\nSORTED BY NAME");
        displayUsingForEach(products);

        // Display using forEach() and a lambda expression.
        IO.println("\nDISPLAY USING LAMBDA");
        products.forEach(product ->
                IO.println(product.getProductName()
                        + " : " + product.getPrice()));

        // Convert the list into an array.
        Product[] productArray = products.toArray(new Product[0]);

        IO.println("\nARRAY CONTENTS");

        for (Product product : productArray) {
            IO.println(product);
        }

        IO.println("LIST SIZE: " + products.size());
        IO.println("ARRAY LENGTH: " + productArray.length);

        // Copy the ArrayList into a LinkedList.
        LinkedList<Product> linkedProducts =
                new LinkedList<>(products);

        IO.println("\nLINKEDLIST INVENTORY");
        displayUsingIterator(linkedProducts);

        // Perform an insertion on the LinkedList.
        linkedProducts.addFirst(
                new Product(108, "Phone Charger", 399, 2));

        IO.println("\nLINKEDLIST AFTER INSERTION");
        displayUsingForEach(linkedProducts);

        // Remove zero-quantity products from LinkedList using Iterator.
        Iterator<Product> linkedIterator = linkedProducts.iterator();

        while (linkedIterator.hasNext()) {
            if (linkedIterator.next().getQuantity() == 0) {
                linkedIterator.remove();
            }
        }

        // Search in LinkedList.
        int linkedIndex = linkedProducts.indexOf(searchProduct);

        IO.println("\nLINKEDLIST SEARCH");

        if (linkedIndex != -1) {
            IO.println("Found: " + linkedProducts.get(linkedIndex));
        } else {
            IO.println("Product not found");
        }

        // Sort the LinkedList by price.
        linkedProducts.sort(
                Comparator.comparingDouble(Product::getPrice));

        IO.println("\nLINKEDLIST SORTED BY PRICE");
        displayUsingIterator(linkedProducts);

        IO.println("\nCOMPARISON");
        IO.println("ArrayList provides fast index-based access.");
        IO.println("LinkedList supports efficient insertion and removal "
                + "at its ends.");
        IO.println("Both preserve element order and support List methods.");

        /*
         * Sample Input:
         * Products are added directly in the program.
         *
         * Sample Output:
         * REMOVED 2 products with zero quantity
         * SEARCH: Found 'Wireless Mouse'
         * SORTED BY PRICE: USB Cable, Keyboard, ...
         * LIST SIZE: 5
         * ARRAY LENGTH: 5
         *
         * API Note:
         * ArrayList and LinkedList implement List.
         * add() inserts elements; get() accesses by index;
         * contains() and indexOf() search for elements;
         * Iterator traverses and safely removes elements;
         * sort() orders the list; toArray() converts it to an array.
         *
         * ArrayList is suitable for frequent index-based access.
         * LinkedList can be useful for frequent operations at the ends.
         */

    }

    // Display using Iterator.
    void displayUsingIterator(List<Product> products) {

        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            IO.println(iterator.next());
        }
    }

    // Display using the enhanced for-each loop.
    void displayUsingForEach(List<Product> products) {

        for (Product product : products) {
            IO.println(product);
        }
    }

    static class Product implements Comparable<Product> {

        private int productId;
        private String productName;
        private double price;
        private int quantity;

        Product(int productId, String productName,
                double price, int quantity) {

            this.productId = productId;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        int getProductId() {
            return productId;
        }

        String getProductName() {
            return productName;
        }

        double getPrice() {
            return price;
        }

        void setPrice(double price) {
            this.price = price;
        }

        int getQuantity() {
            return quantity;
        }

        @Override
        public int compareTo(Product other) {
            return this.productName.compareToIgnoreCase(
                    other.productName);
        }

        /*
         * equals() compares products by name so contains() and
         * indexOf() can find a product using its name.
         */
        @Override
        public boolean equals(Object object) {

            if (this == object) {
                return true;
            }

            if (!(object instanceof Product)) {
                return false;
            }

            Product other = (Product) object;

            return productName.equalsIgnoreCase(other.productName);
        }

        @Override
        public int hashCode() {
            return productName.toLowerCase(Locale.ROOT).hashCode();
        }

        @Override
        public String toString() {
            return productId + " | " + productName
                    + " | Price: " + price
                    + " | Quantity: " + quantity;
        }
    }
}
