package com.pluralsight.raysgoldenswirl.patisserie;

import com.pluralsight.raysgoldenswirl.items.BakedDessert;
import com.pluralsight.raysgoldenswirl.items.Drink;
import com.pluralsight.raysgoldenswirl.items.FrozenYogurt;
import com.pluralsight.raysgoldenswirl.toppings.*;

import java.util.*;
import java.util.function.Function;

public class UserInterface {
    private Order order;

    private final Map<String, List<String>> toppingOptions = new LinkedHashMap<>();
    private final Map<String, Function<String, Topping>> toppingBuilders = new HashMap<>();
    private final List<String> drinkOptions;
    private final List<String> bakedDessertOptions;

    public UserInterface() {
        initializeToppingData();
        this.drinkOptions = new ArrayList<>(Arrays.asList("Sparkling Water", "Strawberry Smoothie",
                "Orange Juice", "Rootbeer Float", "Chocolate Milkshake"));
        this.bakedDessertOptions = new ArrayList<>(Arrays.asList("Brownie", "Baklava", "Cookie"));
    }

    private void initializeToppingData() {
        toppingOptions.put("Fruit", Arrays.asList("Blueberry", "Banana", "Mango", "Kiwi", "Goji Berries"));
        toppingOptions.put("Nuts", Arrays.asList("Walnut", "Pecan", "Almond", "Hazelnut"));
        toppingOptions.put("Candy", Arrays.asList("Sprinkles", "Chocolate Chips", "Marshmallows", "M&Ms",
                "Gummy Bears", "Crushed Oreo", "Jelly Beans", "Butterfinger", "Coconut Flakes"));
        toppingOptions.put("Sauce", Arrays.asList("Caramel", "Chocolate Syrup", "Strawberry Drizzle",
                "Whipped Cream", "Mango Puree", "Berry Compote"));
        toppingOptions.put("Specialty", Arrays.asList("Mochi", "Boba"));

        toppingBuilders.put("Fruit", Fruit::new);
        toppingBuilders.put("Nuts", Nuts::new);
        toppingBuilders.put("Candy", Candy::new);
        toppingBuilders.put("Sauce", Sauce::new);
        toppingBuilders.put("Specialty", Specialty::new);
    }

    public void startProgram(Scanner scanner) {
        while (true) {
            System.out.println("""
                    ╭──────────────────────────────────────────────╮
                    │              RAY'S GOLDEN SWIRL              │
                    ╰──────────────────────────────────────────────╯""");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Enter your selection: ");

            if (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input.");
                scanner.nextLine();
                continue;
            }
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1 -> {
                    String name;
                    while (true) {
                        System.out.print("\nWhat is the name for the order? ");
                        name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            System.out.println("\nName cannot be empty.");
                            continue;
                        }
                        break;
                    }

                    order = new Order(name);
                    takeOrder(scanner, order);
                }
                case 0 -> {
                    System.out.println("Thank you for visiting Ray's Golden Swirl!");
                    return;
                }
                default -> System.out.println("\nInvalid selection: please enter 1 or 0.");
            }
        }
    }

    public void takeOrder(Scanner scanner, Order order) {
        ReceiptFileManager receiptManager = new ReceiptFileManager();

        int input = -1;
        while (input != 0) {
            System.out.println("\nOrder Menu");
            System.out.println("1) Add FroYo");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Baked Dessert");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Enter your selection: ");

            if (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input.");
                scanner.nextLine();
                continue;
            }
            input = scanner.nextInt();
            scanner.nextLine();

            String flavor;
            switch (input) {
                case 1 -> buildFrozenYogurt(scanner);
                case 2 -> buildDrink(scanner);
                case 3 -> buildBakedDessert(scanner);
                case 4 -> checkout(scanner, receiptManager, order);
                case 0 -> {
                    System.out.println("Order Cancelled");
                    return;
                }
                default -> System.out.println("\nInvalid selection: please enter a number 0 - 4");
            }
        }
    }

    public void buildFrozenYogurt(Scanner scanner) {
        String flavor = "";
        int input = -1;
        while (input < 1 || input > 4) {
            System.out.println("\nFlavors:");
            System.out.println("1) Vanilla");
            System.out.println("2) Strawberry");
            System.out.println("3) Chocolate");
            System.out.println("4) Sweet Potato");
            System.out.print("Enter your selection: ");


            if (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input.");
                scanner.nextLine();
                continue;
            }
            input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1 -> flavor = "Vanilla";
                case 2 -> flavor = "Strawberry";
                case 3 -> flavor = "Chocolate";
                case 4 -> flavor = "Sweet Potato";
                default -> System.out.println("\nInvalid selection: please enter a number 1 - 4");
            }
        }

        String size;
        while (true) {
            System.out.print("\nChoose a size (S/M/L): ");
            size = scanner.nextLine().trim().toUpperCase();
            if (!size.equalsIgnoreCase("s") && !size.equalsIgnoreCase("m")
                    && !size.equalsIgnoreCase("l")) {
                System.out.println("\nInvalid input.");
                continue;
            }
            break;
        }

        FrozenYogurt froyo = new FrozenYogurt(flavor, size);

        boolean addMoreToppings = true;
        while (addMoreToppings) {
            System.out.println("\nToppings:");
            List<String> categories = new ArrayList<>(toppingOptions.keySet());
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ") " + categories.get(i));
            }
            System.out.print("Enter your selection: ");

            if (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input.");
                scanner.nextLine();
                continue;
            }
            int categoryChoice = scanner.nextInt();
            scanner.nextLine();

            if (categoryChoice < 1 || categoryChoice > categories.size()) {
                System.out.println("\nInvalid selection: please enter a number 1 - " + categories.size());
                continue;
            }

            String selectedCategory = categories.get(categoryChoice - 1);

            List<String> toppingsList = toppingOptions.get(selectedCategory);
            System.out.println("\nAvailable " + selectedCategory + " toppings:");
            for (int i = 0; i < toppingsList.size(); i++) {
                System.out.println((i + 1) + ") " + toppingsList.get(i));
            }
            System.out.print("Enter your selection: ");

            int toppingChoice = -1;
            if (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input.");
                scanner.nextLine();
                continue;
            }
            toppingChoice = scanner.nextInt();
            scanner.nextLine();

            if (toppingChoice < 1 || toppingChoice > toppingsList.size()) {
                System.out.println("\nInvalid selection: please enter a number 1 - " + toppingsList.size());
                continue;
            }

            String selectedToppingName = toppingsList.get(toppingChoice - 1);
            Function<String, Topping> constructor = toppingBuilders.get(selectedCategory);
            Topping toppingObject = constructor.apply(selectedToppingName);

            int quantity = 1;
            boolean askForExtra = true;
            while (askForExtra) {
                System.out.print("Would you like extra " + selectedToppingName + "? (Y/N): ");
                String extraInput = scanner.nextLine().trim();

                if (extraInput.equalsIgnoreCase("Y")) {
                    if (froyo.getToppingsCount() + quantity + 1 <= froyo.getMaxToppings()) {
                        quantity += 1;
                    }
                } else if (extraInput.equalsIgnoreCase("N")) {
                    askForExtra = false;
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            }

            boolean success = froyo.addTopping(toppingObject, quantity);
            if (!success) {
                System.out.println("You've reached the maximum toppings.");
                break;
            }

            while (true) {
                System.out.print("Would you like to add another topping? (Y/N): ");
                String anotherInput = scanner.nextLine().trim();

                if (anotherInput.equalsIgnoreCase("Y")) {
                    break;
                } else if (anotherInput.equalsIgnoreCase("N")) {
                    addMoreToppings = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            }
        }

        String specialOption;
        while (true) {
            System.out.print("\nWould you like your Froyo Rolled? (Y/N): ");
            specialOption = scanner.nextLine().trim();
            if (!specialOption.equalsIgnoreCase("y") &&
                    !specialOption.equalsIgnoreCase("n")) {
                System.out.println("\nInvalid input.");
                continue;
            }
                froyo.setRolled(specialOption.equalsIgnoreCase("y"));
                break;
        }

        order.addItem(froyo);
        System.out.println("\nFroYo added to order!");
    }

    public void buildDrink(Scanner scanner) {
        System.out.println("\nSelect a drink:");

        for (int i = 0; i < drinkOptions.size(); i++) {
            System.out.println((i + 1) + ") " + drinkOptions.get(i));
        }

        int drinkChoice = -1;
        while (drinkChoice < 1 || drinkChoice > drinkOptions.size()) {
            if (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input.");
                scanner.nextLine();
                continue;
            }
            drinkChoice = scanner.nextInt();
            scanner.nextLine();

            if (drinkChoice < 1 || drinkChoice > drinkOptions.size()) {
                System.out.println("\nInvalid selection: please enter a number 1 - " + drinkOptions.size());
            }
        }

        String drinkType = drinkOptions.get(drinkChoice - 1);

        String size;
        while (true) {
            System.out.print("\nChoose a size (S/M/L): ");
            size = scanner.nextLine().trim().toUpperCase();
            if (!size.equalsIgnoreCase("s") && !size.equalsIgnoreCase("m")
                    && !size.equalsIgnoreCase("l")) {
                System.out.println("\nInvalid input.");
                continue;
            }
            break;
        }

        Drink drink = new Drink(drinkType, size);
        order.addItem(drink);
        System.out.println("\n" + drinkType + " added to order!");
    }

    public void buildBakedDessert(Scanner scanner) {
        System.out.println("\nSelect a baked dessert: ");

        for (int i = 0; i < bakedDessertOptions.size(); i++) {
            System.out.println((i + 1) + ") " + bakedDessertOptions.get(i));
        }

        int dessertChoice = -1;
        while (dessertChoice < 1 || dessertChoice > bakedDessertOptions.size()) {
            if (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input.");
                scanner.nextLine();
                continue;
            }
            dessertChoice = scanner.nextInt();
            scanner.nextLine();

            if (dessertChoice < 1 || dessertChoice > bakedDessertOptions.size()) {
                System.out.println("\nInvalid selection: please enter a number 1 - " + bakedDessertOptions.size());
            }
        }

        String dessertType = bakedDessertOptions.get(dessertChoice - 1);
        BakedDessert bakedDessert = new BakedDessert(dessertType);

        order.addItem(bakedDessert);
        System.out.println("\n" + dessertType + " added to order!");
    }

    public void checkout(Scanner scanner, ReceiptFileManager receiptManager, Order order) {
        System.out.println("\n---------------- CHECKOUT ----------------");

        if (order.getItems().isEmpty()) {
            System.out.println("Your order is empty.");
            return;
        }

        order.displayOrder();

        System.out.println("\n1) Confirm and Save Receipt");
        System.out.println("2) Cancel and Return to Order Menu");
        System.out.print("Enter your selection: ");

        int choice = -1;
        while (choice < 1 || choice > 2) {
            if (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input.");
                scanner.nextLine();
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > 2) {
                System.out.println("\nInvalid selection: please enter 1 or 2");
            }
        }

        if (choice == 1) {
            receiptManager.saveReceipt(order);
        } else {
            System.out.println("Checkout cancelled.");
        }
    }
}


