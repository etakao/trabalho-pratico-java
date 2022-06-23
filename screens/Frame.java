package screens;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import src.Corrida;

public class Frame extends JFrame {
  private int carsAmount, lapsAmount, crashProb, fuelProb;

  private JButton button;
  private JScrollPane scroll;
  private JTextField cars, laps, crashProbability, fuelProbability;

  private static JTextArea showStatus;

  private JPanel inputsArea;
  private JPanel showArea;

  public Frame() {
    setLayout(new GridLayout(1, 2));

    inputsArea = new JPanel();
    inputsArea.setLayout(new FlowLayout(FlowLayout.CENTER));
    showArea = new JPanel();
    showArea.setLayout(new FlowLayout(FlowLayout.CENTER));

    cars = new JTextField(10);
    laps = new JTextField(10);
    crashProbability = new JTextField(10);
    fuelProbability = new JTextField(10);

    button = new JButton("Começar corrida");

    showStatus = new JTextArea(30, 30);
    showStatus.setEditable(false);
    showStatus.setLineWrap(true);

    scroll = new JScrollPane(showStatus);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    inputsArea.add(new JLabel("Quantidade de carros: "));
    inputsArea.add(cars);

    inputsArea.add(new JLabel("Quantidade de voltas: "));
    inputsArea.add(laps);

    inputsArea.add(new JLabel("Probabilidade de quebra: "));
    inputsArea.add(crashProbability);

    inputsArea.add(new JLabel("Probabilidade de abastecimento: "));
    inputsArea.add(fuelProbability);

    inputsArea.add(button);

    showArea.add(scroll);

    add(inputsArea);
    add(showArea);

    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        lapsAmount = Integer.parseInt(laps.getText().trim());
        carsAmount = Integer.parseInt(cars.getText().trim());
        fuelProb = Integer.parseInt(fuelProbability.getText().trim());
        crashProb = Integer.parseInt(crashProbability.getText().trim());

        Corrida corrida = new Corrida(carsAmount, lapsAmount, crashProb, fuelProb);
        corrida.prepararCorredores();
        corrida.iniciarCorrida();
        corrida.exibirPodio();
      }
    });
  }

  public static void printStatus(String args) {
    showStatus.append(args + "\n");
  }
}
