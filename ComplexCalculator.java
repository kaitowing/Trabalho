import java.util.*;

import chainstructure.Pilha;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class ComplexCalculator {
    public static void main(String[] args) {
        ComplexCalculator.reader();
    }

    public static void calculator(String line, Pilha calculator){
        int n;
        float a,b;
        ComplexNumber c1,c2;
        try{
            String[] str = (line.split(" "));
            b = Integer.parseInt(str[0]);
            a = Integer.parseInt(str[1]);
            calculator.push(line);
        }catch(NumberFormatException ex){
            switch(line){
                case "+":
                   c1 = popNum(calculator);
                   c2 = popNum(calculator);
                   add(calculator, c1, c2);
                break;

                case "-":
                    c1 = popNum(calculator);
                    c2 = popNum(calculator);
                    sub(calculator, c1, c2);
                break;

                case "*":
                    c1 = popNum(calculator);
                    c2 = popNum(calculator);
                    mult(calculator, c1, c2);
                break;

                case "/":
                    c1 = popNum(calculator);
                    c2 = popNum(calculator);
                    div(calculator, c1, c2);
                break;

                case "chs":
                    c1 = popNum(calculator);
                    a = c1.getA();
                    b = c1.getB();
                    a = a - (a*2);
                    b = b - (b*2);
                    calculator.push(a + " " + b);
                break;

                case "inv":
                break;

                case "conj":
                    c1 = popNum(calculator);
                    b = c1.getB();
                    c2 = new ComplexNumber(c1.getA(), b - (b*2));
                    mult(calculator, c1, c2);
                break;

                case "abs":

                break;

                case "pop":
                    calculator.pop();
                break;

                case "dup":
                    String aux = (String) calculator.pop();
                    calculator.push(aux);
                    calculator.push(aux);
                break;

                case "swap":
                    try{
                        c1 = popNum(calculator);
                        c2 = popNum(calculator);
                        calculator.push(c1.getA() + " " + c1.getB());
                        calculator.push(c2.getA() + " " + c2.getB());
                    }catch(Exception e){
                        break;
                    }
            }
        }
    }

    public static ComplexNumber popNum(Pilha calculator){
        int a,b;
		ComplexNumber complex2;
        String[] split = ((String) calculator.pop()).split(" ");
		b = Integer.parseInt(split[0]);
		a = Integer.parseInt(split[1]);
		complex2 = new ComplexNumber(a, b);
        return complex2;
    }

    public static void add(Pilha calculator,ComplexNumber complex1,ComplexNumber complex2){
		complex1.add(complex2);
		String aux = (int) complex1.newComplex.getA() + " " + (int) complex1.newComplex.getB() ;
		calculator.push(aux);
    }

    public static void sub(Pilha calculator,ComplexNumber complex1,ComplexNumber complex2){
		complex1.subtract(complex2);
		String aux = (int) complex1.newComplex.getA() + " " + (int) complex1.newComplex.getB() ;
		calculator.push(aux);
    }

    public static void div(Pilha calculator,ComplexNumber complex1,ComplexNumber complex2){
		complex1.divide(complex2);
		String aux = (int) complex1.newComplex.getA() + " " + (int) complex1.newComplex.getB() ;
		calculator.push(aux);
    }

    public static void mult(Pilha calculator,ComplexNumber complex1,ComplexNumber complex2){
		complex1.multiply(complex2);
		String aux = (int) complex1.newComplex.getA() + " " + (int) complex1.newComplex.getB() ;
		calculator.push(aux);
    }

    public static void reader(){
        Path path = Paths.get("Trabalho\\teste.txt");
        Pilha calc = new Pilha();
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))){
            while (sc.hasNext()){
                String linha = sc.nextLine();
                calculator(linha,calc);
            }
        }catch (IOException x){
            System.err.format("Erro de E/S: %s%n", x);
        }
    }
}
