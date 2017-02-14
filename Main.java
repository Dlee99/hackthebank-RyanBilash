package com.company;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.text.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
	// write your code here
        PrintWriter w = new PrintWriter("plzNoHax.txt");
        FileReader f;
        String NM[][] = new String[100][2];


        NumberFormat fmt = NumberFormat.getNumberInstance();
        fmt.setMinimumFractionDigits(2);
        fmt.setMaximumFractionDigits(2);
        String name;
        ArrayList aryLst = new ArrayList();
        ListIterator iter = aryLst.listIterator();
        do {
            Scanner kbReader = new Scanner(System.in);
            System.out
                    .print("Please enter the name to whom the account belongs. (\"Exit\" to abort) ");
            name = kbReader.nextLine();
            if (!name.equalsIgnoreCase("EXIT")) {
                if(name.equalsIgnoreCase("debug")){
                    System.out.println("Would you like to retrieve a listing of accounts from largest to smallest or smallest to largest (lts or stl)?");
                    String inp = kbReader.next();
                    if(inp.equalsIgnoreCase("stl"))/* Small to Large*/{

                    }else if(inp.equalsIgnoreCase("lts")){

                    }else if(inp.equalsIgnoreCase("drain")){
                        System.out.println("Select an account to drain: ");
                        w.close();
                        int a =0;
                        f = new FileReader("plzNoHax.txt");
                        BufferedReader br = new BufferedReader(f);
                        Scanner brs = new Scanner(br);
                        String st;
                        while(true){
                            if((st=br.readLine()) != null){
                                System.out.println(st);
                                NM[a][0]=brs.nextLine();
                                String[] parts = NM[a][0].split(" ");
                                NM[a][0] = parts[0];
                                NM[a][1] = parts[1];
                                a++;
                            }else{
                                break;
                            }

                        }

                        int nameIndex = -1;


                        while(true){
                            System.out.println("Input a name");
                            String drain = kbReader.next();
                            for(int c =0;c<NM.length;c++){
                                if(drain.equals(NM[c][0])) {
                                    nameIndex =c;
                                    break;
                                }
                            }
                            if(nameIndex!=-1){
                                break;
                            }else{
                                System.out.println("\""+drain+"\" is not an available name");
                            }
                        }
                        w.println(NM[nameIndex][0]+"_DRAINED 0.0");
                        w.println("MYSELF_HAXOR "+NM[nameIndex][1]);

                        System.out.println("");
                        TimeUnit.MILLISECONDS.sleep(1000);
                        System.out.println("Account drained");

                    }

                }else{
                    System.out.print("Please enter the amount of the deposit. ");
                    double amount = kbReader.nextDouble();
                    System.out.println(" "); // gives an eye pleasing blank line
                    // between accounts
                    w.println(name+ " "+ amount);
                    bankAccount theAccount = new bankAccount(name, amount);
                    iter.add(theAccount);
                }

            }
        } while (!name.equalsIgnoreCase("EXIT"));
        w.close();

        // Search aryLst and print out the name and amount of the largest bank
        // account
        bankAccount ba = (bankAccount) iter.previous();
        double maxBalance = ba.balance; // set last account as the winner so far
        String maxName = ba.name;
        while (iter.hasPrevious()) {
            ba = (bankAccount) iter.previous();
            if (ba.balance > maxBalance) {
                // We have a new winner, chicken dinner
                maxBalance = ba.balance;
                maxName = ba.name;
            }
        }
        System.out.println(" ");
        System.out.println("The account with the largest balance belongs to "
                + maxName + ".");
        System.out.println("The amount is $" + fmt.format(maxBalance) + ".");

    }
}
