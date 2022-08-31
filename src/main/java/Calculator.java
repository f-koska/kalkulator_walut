package main.java;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Calculator {

    private static final String FILENAME = "src/eurofxref-daily.xml";
    public static final String ExceptionBadCurrency = "There is no currency like: ";
    public static final String ExceptionNegativeNumber = "Value can't be less than 0";

    private static Map<String, Double> currencies = new TreeMap<>();
    private String currency;
    private double value;

    public Calculator(double value, String currency) throws Exception {
        this.currency = currency;
        setValue(value);
        readXmlFile();
    }
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) throws Exception {
        if(value < 0){
            throw new Exception(ExceptionNegativeNumber);
        }
        this.value = value;
    }

    public BigDecimal getValueOfCurrencyConversion() throws Exception {
        if(!getCurrencies().containsKey(getCurrency())) {
            throw new Exception(ExceptionBadCurrency + getCurrency());
        }
        double value = getValue() * getCurrencies().get(getCurrency());
        return BigDecimal.valueOf(value).setScale(3, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    public static Map<String, Double> getCurrencies() {
        return currencies;
    }

    public static void setCurrencies(Map<String, Double> currencies) {
        Calculator.currencies = currencies;
    }

    public static void readXmlFile() {
        File file = new File(FILENAME);
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("Cube");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String currency = eElement.getAttribute("currency");
                    String rate = eElement.getAttribute("rate");

                    if (!currency.isEmpty() && !rate.isEmpty() && checkIfNumber(rate)) {
                        currencies.put(currency, Double.valueOf(rate));
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean checkIfNumber(String number){
        try{
            Double.parseDouble(number);
        }catch (NumberFormatException exception){
            return false;
        }
        return true;
    }

}
