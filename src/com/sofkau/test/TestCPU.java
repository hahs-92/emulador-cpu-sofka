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
        cpu.printMatriz();

    }
}
