package mochila;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Ventana extends JFrame implements ActionListener {

    JFrame v = new JFrame();
    JPanel panel1, panel2;
    JLabel lpesolimite, lnumarticulos, lpesos, lcostos;
    JTextField tfpesolimite, tfnumarticulos;
    JButton bCrearcampos, bCalcular, bAtras;
    ArrayList<JTextField> arraypesos, arraycostos;
    ArrayList<JLabel> Arraysol, arrayval, tablita;
    Calculo c;
    JScrollPane scroll;
    int matriz[][];

    Ventana() {
        iniciarComponentes();
        CrearVentana();
    }

    private void iniciarComponentes() {
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel1.setLayout(null);
        panel2.setLayout(null);
        scroll = new JScrollPane();
        lpesolimite = new JLabel("Ingrese el peso limite:");
        lnumarticulos = new JLabel("Ingrese el numero de articulos:");
        lpesos = new JLabel("Ingrese los pesos de los articulos");
        lcostos = new JLabel("Ingrese el costo de los articulos");
        tfpesolimite = new JTextField();
        tfnumarticulos = new JTextField();
        bCrearcampos = new JButton("Crear Campos");
        bCalcular = new JButton("Calcular");
        bAtras = new JButton("Volver");
        bCalcular.addActionListener(this);
        bCrearcampos.addActionListener(this);
        bAtras.addActionListener(this);
        arraypesos = new ArrayList();
        arraycostos = new ArrayList();
        Arraysol = new ArrayList();
        arrayval = new ArrayList();
        tablita = new ArrayList();
        lpesolimite.setBounds(20, 20, 130, 20);
        tfpesolimite.setBounds(150, 20, 20, 20);
        lnumarticulos.setBounds(20, 60, 180, 20);
        tfnumarticulos.setBounds(200, 60, 20, 20);
        bCrearcampos.setBounds(20, 100, 120, 20);
        lpesos.setBounds(20, 140, 200, 20);
        lcostos.setBounds(20, 220, 200, 20);
        bCalcular.setBounds(20, 280, 100, 20);
        c = new Calculo();
        panel1.add(lpesolimite);
        panel1.add(tfpesolimite);
        panel1.add(lnumarticulos);
        panel1.add(tfnumarticulos);
        panel1.add(bCrearcampos);
    }

    private void CrearVentana() {
        v = new JFrame();
        v.setTitle("Mochila");
        v.setSize(250, 180);
        v.setLocationRelativeTo(null);
        v.setResizable(false);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.add(panel1);
        v.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int peso = Integer.parseInt(tfpesolimite.getText());
        int numarticulos = Integer.parseInt(tfnumarticulos.getText());
        boolean bopeso = false;
        boolean bonumarticulos = false;
        if (peso > 0 && peso <= 30) {
            bopeso = true;
        } else {
            bopeso = false;
            if (peso <= 0) {
                JOptionPane.showMessageDialog(null, "El peso minimo es 1");
            } else {
                JOptionPane.showMessageDialog(null, "El peso maximo es 30");
            }
        }
        if (numarticulos > 0 && numarticulos < 9) {
            bonumarticulos = true;
        } else {
            bonumarticulos = false;
            if (numarticulos <= 0) {
                JOptionPane.showMessageDialog(null, "Minimo ingrese 1 articulo");
            } else {
                JOptionPane.showMessageDialog(null, "Maximo ingrese 8 articulos");
            }
        }
        if (bopeso && bonumarticulos) {
            if (ae.getSource().equals(bCrearcampos)) {
                añadircampos(numarticulos);
            }
            if (ae.getSource().equals(bCalcular)) {
                matriz = c.Calcular(arraypesos, arraycostos, peso);
                DibujarMatriz();
                v.setVisible(false);
                v.remove(panel1);
                v.setSize(500, 500);
                v.setLocationRelativeTo(null);
                v.add(scroll);
                v.setVisible(true);
            }
        }
        if (ae.getSource().equals(bAtras)) {
            remover();
            cambioPaneles(panel2, panel1, 250, 180);
        }

    }

    private void remover() {
        panel1.removeAll();
        panel2.removeAll();
        tfnumarticulos.setText("");
        tfpesolimite.setText("");
        panel1.add(lpesolimite);
        panel1.add(tfpesolimite);
        panel1.add(lnumarticulos);
        panel1.add(tfnumarticulos);
        panel1.add(bCrearcampos);
        arraycostos.clear();
        arraypesos.clear();
        Arraysol.clear();
    }

    private void añadircampos(int numarticulos) {
        v.setVisible(false);

        for (int i = 0; i < numarticulos; i++) {
            arraycostos.add(new JTextField());
            arraypesos.add(new JTextField());
            arraypesos.get(i).setBounds(20 + (i * 35), 180, 30, 20);
            arraycostos.get(i).setBounds(20 + (i * 35), 240, 30, 20);
            panel1.add(arraycostos.get(i));
            panel1.add(arraypesos.get(i));
        }
        panel1.add(lcostos);
        panel1.add(lpesos);
        panel1.add(bCalcular);
        v.setSize(310, 340);
        v.setVisible(true);

    }

    private void DibujarMatriz() {
        int index = 0;
        String valor[][] = c.getStrings();

        int cadena = valor[0][0].length();
        int a = matriz.length - 1, b = matriz[0].length - 1;
        for (int k = 0; k < valor.length; k++) {
            for (int l = 0; l < valor[0].length; l++) {
                int aux = valor[k][l].length();
                if (aux > cadena) {
                    cadena = aux;
                    a = k;
                    b = l;
                }
            }
        }
        String espacio[] = valor[a][b].split(":");
        String respuesta[] = valor[valor.length - 1][valor[0].length - 1].split(" ");
        for (int i = 0; i < respuesta.length; i++) {
            System.out.println(respuesta[i]);
        }

        int tabla[][] = armartabla(respuesta);
        for (int k = 0; k < valor.length; k++) {
            for (int l = 0; l < valor[0].length; l++) {
                arrayval.add(new JLabel(valor[k][l]));
                arrayval.get(index).setBounds(200 + (l * (60 + 20 * espacio.length)) + (60 * espacio.length), 80 + (60 * k), 30 * espacio.length, 30);
                panel2.add(arrayval.get(index));
                index++;
            }
        }
        index = 0;
        for (int i = -1; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (i == -1) {
                    switch (j) {
                        case 0:
                            Arraysol.add(new JLabel("Articulo"));
                            break;
                        case 1:
                            Arraysol.add(new JLabel("Peso"));
                            break;
                        case 2:
                            Arraysol.add(new JLabel("Costo"));
                            break;
                        default:
                            Arraysol.add(new JLabel(Integer.toString(j - 3)));
                            break;
                    }
                } else {
                    Arraysol.add(new JLabel(Integer.toString(matriz[i][j])));
                }
                if (j < 3) {
                    Arraysol.get(index).setBounds(20 + (j * (60 + 20 * espacio.length)), 70 + (60 * i), 50, 20);
                } else {
                    Arraysol.get(index).setBounds(20 + (j * (60 + 20 * espacio.length)), 70 + (60 * i), 30, 20);
                }
                panel2.add(Arraysol.get(index));
                index++;
            }
        }
        index = 0;
        for (int i = -1; i < tabla.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == -1) {
                    switch (j) {
                        case 0:
                            tablita.add(new JLabel("Articulo"));
                            break;
                        case 1:
                            tablita.add(new JLabel("Peso"));
                            break;
                        case 2:
                            tablita.add(new JLabel("Costo"));
                            break;
                    }
                } else {
                    tablita.add(new JLabel(Integer.toString(tabla[i][j])));

                }
                tablita.get(index).setBounds(20 + (60 * j), 50 + (65 * (matriz.length)) + (30 * i), 100, 20);
                panel2.add(tablita.get(index));
                index++;
            }
        }

        bAtras.setBounds(40, 30 + (55* (matriz.length +tabla.length )), 100, 20);
        panel2.setPreferredSize(new Dimension(matriz[0].length * (60 + 20 * espacio.length) + 30, (matriz.length + tabla.length) * 60));
        panel2.add(bAtras);
        scroll.setBounds(5, 10, 100, 100);
        scroll.setViewportView(panel2);
    }

    private void cambioPaneles(JPanel panelviejo, JPanel panelnuevo, int x, int y) {
        v.setVisible(false);
        v.remove(panelviejo);
        v.add(panelnuevo);
        v.setSize(x, y);
        v.setLocationRelativeTo(null);
        v.setVisible(true);
    }

    private int[][] armartabla(String[] respuesta) {
        String separado[];
        int tablilla[][];
        if (respuesta.length % 2 == 0) {
            tablilla = new int[respuesta.length / 2 + 1][3];
        } else {
            tablilla = new int[respuesta.length / 2 + 2][3];
        }
        int a = 0;
        int indexa;
        for (int i = 0; i < respuesta.length; i = i + 2) {
            separado = respuesta[i].split(":");
            indexa = Integer.parseInt(separado[1]);
            System.out.println("indea  = " + indexa);
            if (indexa != 0) {
                System.out.println("entra cuando indexa= " + indexa);
                tablilla[a][0] = indexa;
                tablilla[a][1] = matriz[indexa - 1][1];
                System.out.println("Matriz en peso = " + matriz[indexa - 1][1]);
                tablilla[a][2] = matriz[indexa - 1][2];
                System.out.println("Matriz en costo = " + matriz[indexa - 1][2]);
            }

            a++;
        }
        tablilla[tablilla.length - 1][0] = 0;
        tablilla[tablilla.length - 1][1] = 0;
        tablilla[tablilla.length - 1][2] = 0;
        for (int i = 0; i < tablilla.length - 1; i++) {
            tablilla[tablilla.length - 1][1] += tablilla[i][1];
            tablilla[tablilla.length - 1][2] += tablilla[i][2];
        }
        for (int i = 0; i < tablilla.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tablilla[i][j] + " ");
            }
            System.out.println("");
        }
        return tablilla;
    }

}
