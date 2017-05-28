package dk.cphbusiness.virtualcpu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the awesome CPU program");
        //Program program = new Program("00101001", "00001111", "10101010", "MOV B +3");
        Program factProgram = new Program("01001010", "00010000", "00001100", "11000110", "00010010",
                                            "00001111", "00110010", "00000111", "10001100",
                                            "01000010", "00100001", "00011000", "00010000",
                                            "00010111", "00010000", "00001100", "11000110",
                                            "00010011", "00010010", "00000010", "10001100",
                                            "00011000");
        
        //Factorial of 5 - Tail Recursion
        Program tailFactProgram = new Program("01000010", "00010000", "01001010",
                "00010000", "00001100", "11001000", "00010010", "00001111",
                "00110010", "00000111", "10001100", "00011001",
                "00110101", "00000010", "00100010", "00110010",
                "00010111", "00100001", "00001100", "10001000");
        
       // Program test = new Program("00001100","10000101");
        Machine machine = new Machine();
        //machine.load(factProgram);
        machine.load(tailFactProgram);
        machine.print(System.out);
        //machine.tick();
        // machine.print(System.out);

        while (machine.getCpu().isRunning()) {
            machine.tick();
            machine.print(System.out);
        }

    }
}
