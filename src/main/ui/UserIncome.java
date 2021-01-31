package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class UserIncome {

    private String filename = "userIncome.txt";
    private int income;
    private Scanner scanner = new Scanner(System.in);
    private List<String> lines;

    {
        try {
            lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserIncome() {
        this.income = 0;
    }

    public void setIncome() throws FileNotFoundException, UnsupportedEncodingException {
        if (income == 0) {
            System.out.println("Enter your monthly income: ");
            this.income = scanner.nextInt();
            scanner.nextLine();
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.println(income);
            writer.close();
        }
    }

    public void load() {
        income = Integer.parseInt(lines.get(0));
    }

    public int getIncome() {
        return income;
    }

}
