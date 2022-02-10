package com.sofkau.domain;

public class CPU {
    public int[] records = new int[43];
    public static int instructionNumber = 0;
    public static int count = 0;

    //Main method
    public void iterateSubRoutines(String[] subRoutines) {
        for (instructionNumber = instructionNumber; instructionNumber < subRoutines.length ; instructionNumber++) {
            controller(subRoutines[instructionNumber]);
            count++;

            if(count >= 1024) {
                break;
            }
        }
    }

    //toma un string y determina que instruccion se debe hacer
    public void controller(String subroutine) {
        String[] instructionSplit = normalizeSubroutine(subroutine);

        switch (instructionSplit[0]) {
            case "MOV":
                MOV(instructionSplit[1], instructionSplit[2]);
                break;
            case "ADD":
                ADD(instructionSplit[1], instructionSplit[2]);
                break;
            case "DEC":
                DEC(Integer.parseInt(instructionSplit[1].substring(1)));
                break;
            case "INC":
                INC(Integer.parseInt(instructionSplit[1].substring(1)));
                break;
            case "INV":
                INV(Integer.parseInt(instructionSplit[1]));
                break;
            case "JMP":
                JMP(Integer.parseInt(instructionSplit[1]));
                break;
            case "JZ":
                JZ(Integer.parseInt(instructionSplit[1]));
                break;
            default:
                break;
        }
    }

    public String[] normalizeSubroutine(String instruction) {
        return instruction.split("\\W");
    }

    //INSTRUCCIONES
    //MOV => MOV Rxx,Ryy → copia el valor del registro Rxx al registro Ryy;
    // rxx y ryy son las posiciones en el registro
    public void MOV(String rxx, String ryy) {
        if(!rxx.contains("R")) {
            records[Integer.parseInt(ryy.substring(1))] = Integer.parseInt(rxx);
        } else {
            records[Integer.parseInt(ryy.substring(1))] = records[Integer.parseInt(rxx.substring(1))];
        }
    }

    //MOV d,Rxx → copia la constante numérica d (especificada como un número decimal)
    // al registro Rxx;
    //deprecate
    public void MOV2(int d, int rxx) {
        records[rxx] = d;
    }

    //ADD Rxx,Ryy → calcula (Rxx + Ryy) MOD 232 y almacena el resultado en el registro Rxx;
    public void ADD(String rxx, String ryy) {
        records[Integer.parseInt(rxx.substring(1))] =
                (int) Math.round((records[Integer.parseInt(rxx.substring(1))] +
                        records[Integer.parseInt(ryy.substring(1))]) % Math.pow(2, 32));
    }

    //DEC Rxx → disminuye el valor de Rxx en 1. Si el valor del registro es 0,
    // al disminuirlo se genera un desbordamiento y su resultado sería 232–1;
    public void DEC(int rxx) {
        if(records[rxx] == 0) {
            records[rxx] = (int) Math.round(Math.pow(2,32) - 1);
        } else  {
            records[rxx] --;
        }
    }

    //INC Rxx → aumenta el valor de Rxx en 1. Si el valor del registro es 232–1,
    // al aumentarlo se genera un desbordamiento obteniendo por resultado 0;
    public void INC(int rxx) {
        if(records[rxx] == (int) Math.round(Math.pow(2,32) - 1)) {
            records[rxx] = 0;
        } else {
            records[rxx] ++;
        }
    }

    //INV Rxx → ejecuta una inversión bit a bit del registro Rxx (convierte 1 en 0 y 0 en 1);
    public void INV(int rxx) {
        String bin = Integer.toBinaryString(records[rxx]);
        int reverse = Integer.parseInt(String.valueOf(Integer.reverse(Integer.parseInt(bin))),2);

        records[rxx] = reverse;
    }

    //JMP d → salta incondicionalmente a la instrucción número d (basado en 1).
    // Se garantiza que d es un número de instrucción válido;
    public void JMP(int d) {
        instructionNumber = d - 2;
    }

    //JZ d → salta a la instrucción d (basado en 1) sólo si el registro R00 contiene 0;
    public void JZ(int d) {
        if(records[0] == 0) {
            JMP(d);
        }
    }


    //NOP → no hace nada.

}
