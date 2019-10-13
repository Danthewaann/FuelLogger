package fuellogger;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.io.IOException;

/**
 *
 * @author Daniel
 */
public class FuelLogger 
{
    public static void main(String[] args) 
    {
        System.out.println("-------------------------------------------------------"); 
        System.out.println("                  FUEL LOGGER PROGRAM                  "); 
        System.out.println("-------------------------------------------------------");

        File transactionsFile = new File(System.getProperty("user.dir") + File.separator + "TransactionDetails.txt");
        Scanner consoleInput = new Scanner(System.in);
        FuelTransaction[] transactions = loadTransactions(transactionsFile);

        System.out.println("                   Key in number to          ");
        System.out.println("                   Select an option          ");
        System.out.println("                                       ");
        System.out.println("Option 1. Add new fill-up");
        System.out.println("Option 2. Report fuel quantity bought between two dates");
        System.out.println("Option 3. Report total cost of fuel between two dates");
        System.out.println("Option 4. List of transaction records between two dates");
        System.out.println("Option 5. Exit");
        System.out.println("");

        System.out.print("Select option: ");
        int optionValue = consoleInput.nextInt();
        String date;
        String[] parts;
        switch (optionValue)
        {    
            case 1:
                System.out.println("");
                System.out.println("-------------------------------------------------------"); 
                System.out.println("                  ADD NEW FILL-UP                      "); 
                System.out.println("-------------------------------------------------------");
                System.out.println("-- Date of fill-up --");
                System.out.print("Date (yyyy/mm/dd): ");
                date = consoleInput.next();
                parts = date.split("/");
                GregorianCalendar fillUpDate = new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));               
                System.out.print("Car mileage: ");
                int carMileage = consoleInput.nextInt();
                System.out.print("Number of litres bought: ");
                double numLitresBought = consoleInput.nextDouble();
                System.out.print("Cost per litre: ");
                double costPerLitre = consoleInput.nextDouble();
                
                FuelTransaction transaction = new FuelTransaction(fillUpDate, carMileage, numLitresBought, costPerLitre);
                transactions = addTransactionToArray(transactions, transaction);
                saveTransactions(transactions, transactionsFile);
                break;

            case 2:
                GregorianCalendar firstDate, secondDate;
                System.out.println("");
                System.out.println("-------------------------------------------------------"); 
                System.out.println("           FUEL QUANTITY BETWEEN TWO DATES             "); 
                System.out.println("-------------------------------------------------------");
                System.out.println("-- First Date -- ");
                System.out.print("Date (yyyy/mm/dd): ");
                date = consoleInput.next();
                parts = date.split("/");
                firstDate = new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                System.out.println("-- Second Date -- ");
                System.out.print("Date (yyyy/mm/dd): ");
                date = consoleInput.next();
                parts = date.split("/");
                secondDate = new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                double fuelQuantity = getFuelQuantityBetweenTwoDates(transactions, firstDate, secondDate);
                System.out.println("Fuel quantity = " + fuelQuantity);
                break;

            case 3:
                System.out.println("");
                System.out.println("-------------------------------------------------------"); 
                System.out.println("        TOTAL COST OF FUEL BETWEEN TWO DATES           "); 
                System.out.println("-------------------------------------------------------");
                System.out.println("-- First Date -- ");
                System.out.print("Date (yyyy/mm/dd): ");
                date = consoleInput.next();
                parts = date.split("/");
                firstDate = new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                System.out.println("-- Second Date -- ");
                System.out.print("Date (yyyy/mm/dd): ");
                date = consoleInput.next();
                parts = date.split("/");
                secondDate = new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                double totalCost = getTotalCostOfFuelBetweenTwoDates(transactions, firstDate, secondDate);
                System.out.println("Total cost of fuel = " + totalCost);
                break;

            case 4:
                System.out.println("");
                System.out.println("-------------------------------------------------------"); 
                System.out.println("    LIST OF TRANSACTION RECORDS BETWEEN TWO DATES      "); 
                System.out.println("-------------------------------------------------------");
                System.out.println("-- First Date -- ");
                System.out.print("Date (yyyy/mm/dd): ");
                date = consoleInput.next();
                parts = date.split("/");
                firstDate = new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                System.out.println("-- Second Date -- ");
                System.out.print("Date (yyyy/mm/dd): ");
                date = consoleInput.next();
                parts = date.split("/");
                secondDate = new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                FuelTransaction[] transactionsBetweenTwoDates = getListOfTransactionsBetweenTwoDates(transactions, firstDate, secondDate);
                for (FuelTransaction t : transactionsBetweenTwoDates) {
                    if (t != null) {
                        System.out.println(t.toString());
                    }
                }
                break;

            case 5:
                break;
                
            default:
                System.out.println("Input invalid, please try again");
                break;
            }

        consoleInput.close();   
        System.exit(0);
    }
    
    private static double getFuelQuantityBetweenTwoDates(FuelTransaction[] transactions, GregorianCalendar first, GregorianCalendar second) 
    {
        double fuelQuantity = 0;
        for (FuelTransaction transaction : transactions) {
            if (transaction != null) {
                if (transaction.date.after(first) && transaction.date.before(second)) {
                    fuelQuantity += transaction.litresBought;
                }
            }
        }
        return fuelQuantity;
    }
    
    private static double getTotalCostOfFuelBetweenTwoDates(FuelTransaction[] transactions, GregorianCalendar first, GregorianCalendar second) 
    {
        double totalCost = 0;
        for (FuelTransaction transaction : transactions) {
            if (transaction != null) {
                if (transaction.date.after(first) && transaction.date.before(second)) {
                    totalCost += transaction.litresBought * transaction.costPerLitre;
                }
            }
        }
        return totalCost;
    }
    
    private static FuelTransaction[] getListOfTransactionsBetweenTwoDates(FuelTransaction[] transactions, GregorianCalendar first, GregorianCalendar second)
    {
        int numOfTransactions = 0;
        FuelTransaction[] transactionsBetweenTwoDates = new FuelTransaction[transactions.length];
        for (FuelTransaction transaction : transactions) {
            if (transaction != null) {
                if (transaction.date.after(first) && transaction.date.before(second)) {
                    transactionsBetweenTwoDates[numOfTransactions++] = transaction;
                }
            }
        }
        return transactionsBetweenTwoDates;
    }
    
    
    private static FuelTransaction[] addTransactionToArray(FuelTransaction[] transactions, FuelTransaction transaction)
    {
        for (int i = 0; i < transactions.length; i++) {
            if (transactions[i] == null) {
                transactions[i] = transaction;
                break;
            }
        }
        return transactions;
    }
    

    private static FuelTransaction[] loadTransactions(File transactionsFile)
    {
        Scanner in = null;
        FuelTransaction[] transactions = new FuelTransaction[100];
        try {
            if (transactionsFile.canRead()) {
                in = new Scanner(transactionsFile); 
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    String[] lineParts = line.split(" ");
                    if (lineParts.length == 4) { // 4 tokens per line
                        int year = Integer.parseInt(lineParts[0].substring(0, 4));
                        int month = Integer.parseInt(lineParts[0].substring(4, 6));
                        int day = Integer.parseInt(lineParts[0].substring(6, 8));
                        int carMileage = Integer.parseInt(lineParts[1]);
                        double litresBought = Double.parseDouble(lineParts[2]);
                        double costPerLitre = Double.parseDouble(lineParts[3]);
                        FuelTransaction transaction = new FuelTransaction(new GregorianCalendar(year, month, day), carMileage, litresBought, costPerLitre);
                        for (int i = 0; i < transactions.length; i++) {                        
                            if (transactions[i] == null) {                    
                                transactions[i] = transaction;
                                break;
                            }
                        }
                    } 
                    else {
                        throw new IOException("Transactions file is corrupt, should have 4 items per line!");
                    }
                }
            } else {
                transactionsFile.createNewFile();
            }
        } catch(IOException f) {
            System.out.println(f.getMessage());
            System.out.println("Transactions file: " + transactionsFile.getAbsolutePath());
            System.exit(1);
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return transactions;
    }
    
    private static void saveTransactions(FuelTransaction[] transactions, File transactionsFile)
    {
        PrintWriter out = null;
        try {
            out = new PrintWriter(transactionsFile);
        } catch(FileNotFoundException f) {
            System.out.println(f.getMessage());
            System.out.println("in " + System.getProperty("user.dir"));
            System.exit(1);
        }
        for (FuelTransaction transaction : transactions) {
            if (transaction != null) {
                out.println(transaction.getRecord());        
            }
        }
        out.close();
    }
    
}
