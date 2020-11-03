/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mochila;

import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author JANUS
 */
public class Calculo {

    int Pesos[], Costo[], lim;
    int Matriz[][];
    String razon[][];

    public int[][] Calcular(ArrayList<JTextField> p, ArrayList<JTextField> cost, int pesolim) {
        Pesos = new int[p.size()];
        Costo = new int[cost.size()];
        lim = pesolim;
        Matriz = new int[cost.size()][pesolim + 4];
        razon = new String[cost.size()][pesolim + 1];
        Llenarmatrices(p, cost);
        insercion();
        crearMatriz(pesolim);
        return Matriz;
    }

    public void insercion() {
        int i, j;
        int aux, aux2;

        for (i = 1; i < Pesos.length; i++) {

            aux = Pesos[i];
            aux2 = Costo[i];
            j = i - 1;
            while ((j > -1) && (aux < Pesos[j])) {
                Pesos[j + 1] = Pesos[j];
                Costo[j + 1] = Costo[j];
                j = j - 1;
            }
            Pesos[j + 1] = aux;
            Costo[j + 1] = aux2;
        }
    }

    private void Llenarmatrices(ArrayList<JTextField> p, ArrayList<JTextField> cost) {
        for (int i = 0; i < p.size(); i++) {
            Pesos[i] = Integer.parseInt(p.get(i).getText());
            Costo[i] = Integer.parseInt(cost.get(i).getText());
        }
    }

    private void crearMatriz(int limite) {
        for (int i = 0; i < Matriz.length; i++) {
            Matriz[i][0] = i + 1;
            Matriz[i][1] = Pesos[i];
            Matriz[i][2] = Costo[i];
        }

        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 3; j < Matriz[0].length; j++) {
                if (j - 3 < Pesos[0]) {
                    Matriz[i][j] = 0;
                    razon[i][j-3]= "0:0";
                } else {
                    Max(i, j);
                }
            }
        }
        imprimirmatriz(razon);
    }

    private void imprimirmatriz(String[][] Matriz) {
        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz[0].length; j++) {
                System.out.print(Matriz[i][j] + "    ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private void Max(int i, int j) {
        if (i == 0) {
            Matriz[i][j] = Matriz[i][2];
            razon[i][j - 3] = Integer.toString(Matriz[i][2]) + ":" + Matriz[i][0];
        } else {
            int peso = Matriz[i][1];
            int costo = Matriz[i][2];
            int superior = Matriz[i - 1][j];
            if (j - 3 >= peso) {
                if (superior > Matriz[i - 1][j - peso] + costo) {
                    Matriz[i][j] = superior;
                    razon[i][j - 3] = razon[i-1][j-3];
                } else {
                    Matriz[i][j] = Matriz[i - 1][j - peso] + costo;
                    razon[i][j - 3] =   razon[i-1][j - peso - 3]+ " + " + Integer.toString(costo) + ":" + Matriz[i][0];
                }
            } else {
                Matriz[i][j] = Matriz[i - 1][j];
                razon[i][j - 3] = razon[i - 1][j - 3];
            }

        }
    }
 
    public String[][] getStrings(){
        return razon;
    }

}
