package com.sofkau.test;

import com.sofkau.domain.CPU;

public class TestCPU {
    public static void main(String[] args) {
        //subrutina
        String [] subroutine = {
                "MOV 5,R00",
                "MOV 10,R01",
                "JZ 7",
                "ADD R02,R01",
                "DEC R00",
                "JMP 3",
                "MOV R02,R42"
        };


        CPU cpu = new CPU();
        cpu.iterateSubRoutines(subroutine);

        //cpu.records[2] = 4
        //cpu.MOV("5","R00");
        //cpu.MOV("R02","R42");

        //cpu.records[2] = 3;
        //cpu.records[1] = 5;
        //cpu.ADD("R02","R01");

        //cpu.records[0] = 2;
        //cpu.DEC(0);

        //cpu.INC(1);

        //System.out.println(Integer.parseUnsignedInt(String.valueOf(Math.pow(2,32) - 1)));
        //System.out.println(Math.pow(2,32) - 1);
        //System.out.println( (int) Math.round(Math.pow(2,32) - 1));


        //System.out.println(Integer.parseInt("01"));
        //System.out.println(cpu.normalizeSubroutine("MOV 5,R00")[2]);

        for (int i = 0; i < cpu.records.length ; i++) {
            System.out.println(cpu.records[i]);
        }
    }
}
