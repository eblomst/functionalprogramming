/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emmablomsterberg
 */
public class CalculatorMain {

    static ArrayStack<Integer> as = new ArrayStack();

    public static void main(String[] args) {

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter values" + "\n");
            String s = " ";
            
            while (!s.equals("stop")) {
                s = br.readLine();

                String[] arr = s.split(" ");

                cal(arr);
            }

        } catch (IOException ex) {
            Logger.getLogger(CalculatorMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void print(ArrayStack<Integer> list) {
        if (list.isEmpty()) {
            return;
        }
       
        for (int i = 0; i < list.size(); i++) {
            System.out.println("> " + list.get(i)); 
        }
        
        list.clear();
    }

    public static void cal(String[] arr) {
        ArrayList<String> sym = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals("+") && !arr[i].equals("-") && !arr[i].equals("/") && !arr[i].equals("*")) {
                as.push(arr[i]);
            }
            sym.add(arr[i]);
        }
       
        for (int i = 0; i < sym.size(); i++) {

            String oper = sym.get(i);
            sym.remove(i);
            i = 0;

            switch (oper) {

                case "+":
                    Integer sumfirstStack = Integer.parseInt(as.pop().toString());
                    Integer sumnextStack = Integer.parseInt(as.pop().toString());
                    as.push(sumfirstStack + sumnextStack);
                    break;
                case "-":
                    int subfirstStack = Integer.parseInt(as.pop().toString());
                    int subnextStack = Integer.parseInt(as.pop().toString());
                    as.push(subfirstStack - subnextStack);
                    break;
                case "*":
                    int addfirstStack = Integer.parseInt(as.pop().toString());
                    int addnextStack = Integer.parseInt(as.pop().toString());
                    as.push(addfirstStack * addnextStack);
                    break;

                case "/":
                    int divfirstStack = Integer.parseInt(as.pop().toString());
                    int divnextStack = Integer.parseInt(as.pop().toString());
                    as.push(divfirstStack / divnextStack);
                    break;
            }
           
        }

        print(as);
    }
}
