package dk.cphbusiness.virtualcpu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the awesome CPU program");
        //Program program = new Program("00101001", "00001111", "10101010", "MOV B +3");
        Program factProgram = new Program("01000010", "00010000", "01001010",
                "00010000", "00001100", "11001000", "00010010", "00001111",
                "00110010", "00000111", "10001100", "00011001",
                "00110101", "00000010", "00100010", "00110010",
                "00010111", "00100001", "00001100", "10001000");
        Machine machine = new Machine();
        // machine.load(program);
        machine.load(factProgram);
        machine.print(System.out);
        machine.tick();
        machine.print(System.out);

        for (int line : factProgram) {
            System.out.println(">>> " + line);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Press enter to run next line");
        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("quit")) {
            machine.tick();

            if (machine.getCpu().isRunning() == false) {
                break;
            }

            machine.print(System.out);
            System.out.println("Press enter to run next line");
            input = scanner.nextLine();
        }

    }
}
