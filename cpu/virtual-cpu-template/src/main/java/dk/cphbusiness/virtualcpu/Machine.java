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
        if(instr == 0b0000_0000) { //NOP
            cpu.incIp();
        } else if(instr == 0b0000_0001) { //ADD
            cpu.setA(cpu.getA() + cpu.getB());
            cpu.incIp();
        } else if(instr == 0b0000_0010) { //MUL
            cpu.setA(cpu.getA() * cpu.getB());
            cpu.incIp();
        } else if(instr == 0b0000_0011) { //DIV
            cpu.setA(cpu.getA() / cpu.getB());
            cpu.incIp();
        }  else if(instr == 0b0000_0100) { //ZERO
            if (cpu.getA() == 0) {
                cpu.setFlag(true);
                cpu.incIp();
            } else {
                cpu.incIp();
            }
        } else if(instr == 0b0000_0101) { //NEG
            if(cpu.getA() < 0) {
                cpu.setFlag(true);
                cpu.incIp();
            } else {
                cpu.incIp();
            }
        } else if(instr == 0b0000_0110) { //POS
            if(cpu.getA() > 0) {
                cpu.setFlag(true);
                cpu.incIp();
            } else {
                cpu.incIp();
            }
        } else if(instr == 0b0000_0111) { //NZERO
            if(cpu.getA() != 0) {
                cpu.setFlag(false);
                cpu.incIp();
            } else {
                cpu.incIp();
            }
        } else if(instr == 0b0000_1000) { //EQ
            if(cpu.getA() == cpu.getB()) {
                cpu.setFlag(true);
                cpu.incIp();
            } else {
                cpu.incIp();
            }
        } else if(instr == 0b0000_1001) { //LT
            if(cpu.getA() < cpu.getB()) {
                cpu.setFlag(true);
                cpu.incIp();
            } else {
                cpu.incIp();
            }
        } else if(instr == 0b0000_1010) { //GT
            if(cpu.getA() > cpu.getB()) {
                cpu.setFlag(true);
                cpu.incIp();
            } else {
                cpu.incIp();
            }
        } else if(instr == 0b0000_1011) { //NEQ
            if(cpu.getA() != cpu.getB()) {
                cpu.setFlag(false);
                cpu.incIp();
            } else {
                cpu.incIp();
            }
        } else if(instr == 0b0000_1100) { //ALWAYS
            cpu.setFlag(true);
            cpu.incIp();
        } else if(instr == 0b0000_1111) { //HALT
            System.out.println("HALT");
        } else if((instr & 0b1111_1110) == 0b0001_0000) { //PUSH r
            int r = (instr & 0b0000_0001);
            cpu.decSp();
            if(r == 1) {
                memory.set(cpu.getSp(), cpu.getB());
            } else {
                memory.set(cpu.getSp(), cpu.getA());
            }
            cpu.incIp();
        } else if((instr & 0b1111_1110) == 0b0001_0010) { //POP r
            int r = (instr & 0b0000_0001);
            if(r == 1) {
                cpu.setSp(cpu.getSp()+1);
                cpu.setB(memory.get(cpu.getSp()));
            } else {
                cpu.setSp(cpu.getSp()+1);
                cpu.setA(memory.get(cpu.getSp()));
            }
            cpu.incIp();
        } else if(instr == 0b0001_0100) { //MOV A B
            cpu.setB(cpu.getA());
            cpu.incIp();
        } else if(instr == 0b0001_0101) { //MOV B A
            cpu.setA(cpu.getB());
            cpu.incIp();
        } else if(instr == 0b0001_0110) { //INC
            cpu.setA(cpu.getA() + 1);
            cpu.incIp();
        } else if(instr == 0b0001_0111) { //DEC
            cpu.setA(cpu.getA() - 1);
            cpu.incIp();
        } else if((instr & 0b1111_1000) == 0b0001_1000) { //RTN
            int o = (instr & 0b0000_0111);
            cpu.setSp(cpu.getSp() + 1);
            cpu.setIp(cpu.getSp());
            cpu.setSp(cpu.getSp() + o);
            cpu.incIp();
        } else if((instr & 0b1111_0000) == 0b0010_0000) { //MOV r o
            int r = (instr & 0b0000_1110) >> 1;
            int o = (instr & 0b0000_0001);
            if(r == 0) {
                cpu.setA(memory.get(cpu.getSp() + o));
            } else {
                cpu.setB(memory.get(cpu.getSp() + o));
            }
            cpu.incIp();
        } else if((instr & 0b1111_0000) == 0b0011_0000) { //MOV o r
            int r = (instr & 0b0011_0001);
            int o = (instr & 0b0011_1110) >> 1;
            if(r == 1) {
                cpu.setB(memory.get(cpu.getSp() + o));
            } else {
                cpu.setA(memory.get(cpu.getSp() + o));
            }
            cpu.incIp();
        } else if ((instr & 0b1100_0000) == 0b0100_0000) { //MOV v r
            int v = (instr & 0b0011_1110) >> 1;
            int r = (instr & 0b0000_0001);
            if(r == cpu.B) {
                cpu.setB(v);
            } else {
                cpu.setA(v);
            }
            cpu.incIp();
        } else if ((instr & 0b1100_0000) == 0b1000_0000) { //JMP #a
            
            if(cpu.isFlag()) {
                int a = (instr & 0b0011_1111);
                cpu.setIp(a);
            } else {
                cpu.incIp();
            }
        } else if ((instr & 0b1100_0000) == 0b1100_0000) { //CALL #a
            if(cpu.isFlag()) {
                int a = (instr & 0b0011_1111);
                cpu.decSp();
                memory.set(cpu.getSp(), cpu.getIp());
                cpu.setIp(a);
            } else {
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
