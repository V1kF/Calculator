import java.io.IOException;
import java.util.regex.*;
import java.util.Scanner;


public class Main{

    public static void main(String[] args) {

        Main.getExpression();

    }

    public static void calc(String firstNumber,String secondNumber,String operation,boolean isArabicNumbers) {

        int firstValue=Integer.parseInt(firstNumber);
        int secondValue=Integer.parseInt(secondNumber);

        switch (operation){
            case "+":
                outputResult(firstValue+secondValue,isArabicNumbers);
                break;
            case "-":
                outputResult(firstValue-secondValue,isArabicNumbers);
                break;
            case "/":
                outputResult(firstValue/secondValue,isArabicNumbers);
                break;
            case "*":
                outputResult(firstValue*secondValue,isArabicNumbers);
                break;
        }
    }
    public static void outputResult(int result,boolean isArabicNumbers) {

        int[] decimalValue = {100,90,50,40,10,9,5,4,1};

        String[] romanValue = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        String romanResult="";

        if (isArabicNumbers){

            System.out.println("answer:"+Integer.toString(result));

        }else {

            if(result<1){

                try {
                    throw new IOException();
                }

                catch (IOException exception){
                    System.out.println("the result is less than one!");
                }
            }
            else{

                for (int i = 0; i < decimalValue.length; i++) {

                    while (decimalValue[i]<=result){

                        romanResult+=romanValue[i];

                        result=result-decimalValue[i];
                    }
                }
                System.out.println("answer:"+romanResult);
            }

        }
    }

    public static void getExpression() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a mathematical expression: ");

        String mathExpression = scanner.nextLine();


        Matcher checkRomanNumbers = Pattern.compile("^(IX|IV|V?I{0,3}|X|){1} (\\+|-|/|\\*) (IX|IV|V?I{0,3}|X|){1}$").matcher(mathExpression);
        Matcher checkArabicNumbers = Pattern.compile("^(\\d?|10){1} (\\+|-|/|\\*) (\\d?|10){1}$").matcher(mathExpression);


        if (checkArabicNumbers.find()) {


            String firstNumber = checkArabicNumbers.group(1);

            String operation = checkArabicNumbers.group(2);

            String secondNumber = checkArabicNumbers.group(3);


            Main.calc(firstNumber,secondNumber,operation,true);

        }else if(checkRomanNumbers.find()){


            String firstNumber = checkRomanNumbers.group(1);

            String operation = checkRomanNumbers.group(2);

            String secondNumber = checkRomanNumbers.group(3);


            ProcessingExpression(firstNumber,secondNumber,operation);

        } else{

            try {
                throw new IOException();
            }

            catch (IOException exception){
                System.out.println("you entered incorrect data");
            }

        }
    }
    public static void ProcessingExpression(String firstNumber,String secondNumber,String operation){

        String[] romanNumbers = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        for (int i = 0; i < romanNumbers.length; i++) {

            if (romanNumbers[i].equals(firstNumber)) {
                firstNumber = Integer.toString(i);
            }

            if (romanNumbers[i].equals(secondNumber)) {
                secondNumber=Integer.toString(i);
            }
        }
        Main.calc(firstNumber, secondNumber, operation , false );

    }

}







