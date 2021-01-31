package ui;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CurrencyExchangeAPI extends Observable {

    private String currencyType = "CAD";
    private String currencyTypeBase = "CAD";
    private String filename = "currencyType.txt";
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

    public static void main(String[] args) {

    }

    public String getCurrencyType() {
        return currencyType;
    }

    public double getCurrencyExchange(String currencyType) throws IOException {
        this.currencyTypeBase = this.currencyType;
        this.currencyType = currencyType;
        BufferedReader br;

//        try {
        String theURL = "https://api.exchangeratesapi.io/latest?base=" + currencyTypeBase + "&symbols=" + currencyType; //this can point to any URL
        URL url = new URL(theURL);
        br = new BufferedReader(new InputStreamReader(url.openStream()));

        String line;

        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null) {

            sb.append(line);
            sb.append(System.lineSeparator());
        }
        save(sb);

        //System.out.println(sb);
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        finally {
//
//            if (br != null) {
//                br.close();
//            }
//        }
        return parseCurrency();
    }

    private void save(StringBuilder sb) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("JSON.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.println(sb.toString());
        pw.close();
    }

    private double parseCurrency() throws IOException {
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader("JSON.json"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) obj;
        Map rates = ((Map)jo.get("rates"));
        Iterator<Map.Entry> itr1 = rates.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            //System.out.println(pair.getKey() + " : " + pair.getValue());
        }
        //System.out.println(rates.get(currencyType));
        return (double) rates.get(currencyType);
    }

    public void setCurrencyType() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filename, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(currencyType);
        writer.close();
        notifyObservers();
    }

    public void load() {
        currencyType = lines.get(0);
    }

}