package cinema;

import java.util.Scanner;

public class Cinema {
    private static int currentIncome = 0;

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfRowsSeats = scanner.nextInt();
        String[][] seatsArray = new String[numberOfRows][numberOfRowsSeats];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfRowsSeats; j++) {
                seatsArray[i][j] = "S";
            }
        }

        System.out.println();

        loop: while (true) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            int menuOption = scanner.nextInt();

            switch (menuOption) {
                case 0:
                    break loop;
                case 1:
                    showSeats(numberOfRows, numberOfRowsSeats, seatsArray);
                    break;
                case 2:
                    buyTicket(numberOfRows, numberOfRowsSeats, seatsArray);
                    break;
                case 3:
                    showStatistics(numberOfRows, numberOfRowsSeats, seatsArray);
                    break;
                default:
                    System.out.println("Unknown option");
                    break;
            }
            System.out.println();
        }
    }

    public static void showSeats(int row, int column, String[][] array) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1 ; i < column + 1; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0 ; j < column; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyTicket(int numberOfRows, int numberOfRowsSeats, String[][] seatsArray) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int rowSeat = scanner.nextInt();

            try {
                if (seatsArray[row - 1][rowSeat - 1].equals("B")) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    seatsArray[row - 1][rowSeat - 1] = "B";
                    System.out.println();
                    if (numberOfRows * numberOfRowsSeats <= 60) {
                        currentIncome += 10;
                        System.out.println("Ticket price: $" + 10);
                    } else {
                        if (row <= numberOfRows / 2) {
                            currentIncome += 10;
                            System.out.println("Ticket price: $" + 10);
                        } else {
                            currentIncome += 8;
                            System.out.println("Ticket price: $" + 8);
                        }
                    }
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("\nWrong input!");
            }
        }
    }

    public static void showStatistics(int row, int column, String[][] array) {
        System.out.println();

        int numberOfPurchasedTickets = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0 ; j < column; j++) {
                if (array[i][j].equals("B")) {
                    numberOfPurchasedTickets++;
                }
            }
        }

        System.out.printf("Number of purchased tickets: %d\n", numberOfPurchasedTickets);
        System.out.printf("Percentage: %1.2f%%\n", (float) numberOfPurchasedTickets / (row * column) * 100);
        System.out.printf("Current income: $%d\n", currentIncome);

        if (row * column <= 60) {
            System.out.println("Total income: $" + row * column * 10);
        } else {
            int frontRows = row / 2;
            int frontRowsIncome = frontRows * column * 10;
            int backRows = (row - frontRows) * column * 8;
            int total = frontRowsIncome + backRows;
            System.out.println("Total income: $" + total);
        }
    }
}