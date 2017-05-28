package dk.cphbusiness.virtualcpu;

import java.io.PrintStream;

public class Machine {

    private Cpu cpu = new Cpu();
    private Memory memory = new Memory();

    public Cpu getCpu() {
        return cpu;
    }

    public void load(Program program) {
        int index = 0;
        for (int instr : program) {
            memory.set(index++, instr);
        }
    }

    public void tick() {
        int instr = memory.get(cpu.getIp());
        if (instr == 0b0000_0000) { //NOP
            System.out.println("NOP");
            cpu.incIp();
        } else if (instr == 0b0000_0001) { //ADD
            System.out.println("ADD");
            cpu.setA(cpu.getA() + cpu.getB());
            cpu.incIp();
        } else if (instr == 0b0000_0010) { //MUL
            System.out.println("MUL");
            cpu.setA(cpu.getA() * cpu.getB());
            cpu.incIp();
        } else if (instr == 0b0000_0011) { //DIV
            System.out.println("DIV");
            cpu.setA(cpu.getA() / cpu.getB());
            cpu.incIp();
        } else if (instr == 0b0000_0100) { //ZERO
            System.out.println("ZERO");
            cpu.setFlag(cpu.getA() == 0);
            cpu.incIp();
//            if (cpu.getA() == 0) {
//                cpu.setFlag(true);
//                cpu.incIp();
//            } else {
//                cpu.incIp();
//            }
        } else if (instr == 0b0000_0101) { //NEG
            System.out.println("NEG");
            cpu.setFlag(cpu.getA() < 0);
            cpu.incIp();
//            if (cpu.getA() < 0) {
//                cpu.setFlag(true);
//                cpu.incIp();
//            } else {
//                cpu.incIp();
//            }
        } else if (instr == 0b0000_0110) { //POS
            System.out.println("POS");
            cpu.setFlag(cpu.getA() > 0);
            cpu.incIp();
//            if (cpu.getA() > 0) {
//                cpu.setFlag(true);
//                cpu.incIp();
//            } else {
//                cpu.incIp();
//            }
        } else if (instr == 0b0000_0111) { //NZERO
            System.out.println("NZERO");
            cpu.setFlag(cpu.getA() != 0);
            cpu.incIp();

        } else if (instr == 0b0000_1000) { //EQ
            System.out.println("EQ");
            cpu.setFlag(cpu.getA() == cpu.getB());
            cpu.incIp();
//            if (cpu.getA() == cpu.getB()) {
//                cpu.setFlag(true);
//                cpu.incIp();
//            } else {
//                cpu.incIp();
//            }
        } else if (instr == 0b0000_1001) { //LT
            System.out.println("LT");
            cpu.setFlag(cpu.getA() < cpu.getB());
            cpu.incIp();
//            if (cpu.getA() < cpu.getB()) {
//                cpu.setFlag(true);
//                cpu.incIp();
//            } else {
//                cpu.incIp();
//            }
        } else if (instr == 0b0000_1010) { //GT
            System.out.println("GT");
            if (cpu.getA() > cpu.getB()) {
                cpu.setFlag(true);
                cpu.incIp();
            } else {
                cpu.incIp();
            }
        } else if (instr == 0b0000_1011) { //NEQ
            System.out.println("NEQ");
            cpu.setFlag(cpu.getA() != cpu.getB());
            cpu.incIp();
//            if (cpu.getA() != cpu.getB()) {
//                cpu.setFlag(false);
//                cpu.incIp();
//            } else {
//                cpu.incIp();
//            }
        } else if (instr == 0b0000_1100) { //ALWAYS
            System.out.println("ALWAYS");
            cpu.setFlag(true);
            cpu.incIp();
        } else if (instr == 0b0000_1111) { //HALT
            System.out.println("HALT");
            cpu.setRunning(false);
        } else if ((instr & 0b1111_1110) == 0b0001_0000) { //PUSH r
            int r = (instr & 0b0000_0001);
            System.out.println("PUSH " + r);
            cpu.decSp();
            if (r == cpu.B) {
                memory.set(cpu.getSp(), cpu.getB());
            } else {
                memory.set(cpu.getSp(), cpu.getA());
            }
            cpu.incIp();
        } else if ((instr & 0b1111_1110) == 0b0001_0010) { //POP r
            int r = (instr & 0b0000_0001);
            System.out.println("POP " + r);
            if (r == cpu.B) {
                cpu.setB(memory.get((cpu.getSp() + 1)));
            } else {
                cpu.setA(memory.get((cpu.getSp() + 1)));
            }
            cpu.setSp(cpu.getSp() + 1);
            cpu.incIp();
        } else if (instr == 0b0001_0100) { //MOV A B
            System.out.println("MOV A: " + cpu.getA() + " to B");
            cpu.setB(cpu.getA());
            cpu.incIp();
        } else if (instr == 0b0001_0101) { //MOV B A
            System.out.println("MOV B: " + cpu.getB() + " to A");
            cpu.setA(cpu.getB());
            cpu.incIp();
        } else if (instr == 0b0001_0110) { //INC
            System.out.println("INC");
            cpu.setA(cpu.getA() + 1);
            cpu.incIp();
        } else if (instr == 0b0001_0111) { //DEC
            System.out.println("DEC");
            cpu.setA(cpu.getA() - 1);
            cpu.incIp();
        } else if ((instr & 0b1111_1000) == 0b0001_1000) { //RTN
            int o = (instr & 0b0000_0111);
            System.out.println("RTN " + o);
            cpu.setIp(memory.get((cpu.getSp() + 1)));
//            cpu.setSp((cpu.getSp() + o));
            cpu.setSp((cpu.getSp() + o + 1));
            cpu.setRunning(false);
            //cpu.incIp();
            
        } else if ((instr & 0b1111_0000) == 0b0010_0000) { //MOV r o
            int r = (instr & 0b0000_1000) >> 3;
            int o = (instr & 0b0000_0111);
            System.out.println("MOV " + r + ": to " + o);
            if (r == 1) {
                memory.set((cpu.getSp() + o), cpu.getB());
                //cpu.setB(memory.get((cpu.getSp() + o)));
            } else {
                memory.set((cpu.getSp() + o), cpu.getA());
                //cpu.setA(memory.get((cpu.getSp() + o)));
            }
            cpu.incIp();
        } else if ((instr & 0b1111_0000) == 0b0011_0000) { //MOV o r
            int r = (instr & 0b0000_0001);
            int o = (instr & 0b0000_1110) >> 1;
            System.out.println("MOV " + o + " to " + r);
            System.out.println("SP " + (cpu.getSp() + o));
            if (r == cpu.B) {
                System.out.println("in if");
                cpu.setB(memory.get((cpu.getSp() + o)));
            } else {
                System.out.println("in else");
                cpu.setA(memory.get((cpu.getSp() + o)));
            }
            cpu.incIp();
        } else if ((instr & 0b1100_0000) == 0b0100_0000) { //MOV v r
            int v = (instr & 0b0011_1110) >> 1;
            int r = (instr & 0b0000_0001);
            System.out.println("MOV " + v + " to " + r);
            if (r == cpu.B) {
                cpu.setB(v);
            } else {
                cpu.setA(v);
            }
            cpu.incIp();
        } else if ((instr & 0b1100_0000) == 0b1000_0000) { //JMP #a

            if (cpu.isFlag()) {
                int a = (instr & 0b0011_1111);
                System.out.println("JMP " + a);
                cpu.setIp(a);
            } else {
                System.out.println("NO JMP");
                cpu.incIp();
            }
        } else if ((instr & 0b1100_0000) == 0b1100_0000) { //CALL #a
            if (cpu.isFlag()) {
                int a = (instr & 0b0011_1111);
                System.out.println("CALL " + a);
                cpu.decSp();
                memory.set(cpu.getSp(), cpu.getIp());
                System.out.println("Call mem " + memory.get(cpu.getSp()));
                cpu.setIp(a);
            } else {
                System.out.println("NO CALL");
                cpu.incIp();
            }
        }
    }

    public void print(PrintStream out) {
        memory.print(out);
        out.println("-------------");
        cpu.print(out);
    }

}
