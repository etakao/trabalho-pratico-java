package screens;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import src.Corrida;

public class Frame extends JFrame {
  private int carsAmount, lapsAmount, crashProb, fuelProb;

  private JButton startRaceButton;
  private JScrollPane scroll;
  private JTextField cars, laps, crashProbability, fuelProbability;

  private static JTextArea statusTextArea;

  public static JPanel inputsPanel, raceStatusPanel, raceProgressPanel, raceTextStatusPanel;

  public static Frame frame = new Frame();
  private GridBagConstraints gbc = new GridBagConstraints();
  public static GridBagConstraints gbc2 = new GridBagConstraints();

  public Frame() {
    setLayout(new GridBagLayout());

    inputsPanel = new JPanel();
    inputsPanel.setLayout(new GridBagLayout());
    inputsPanel.setSize(500, 200);

    raceStatusPanel = new JPanel();
    raceStatusPanel.setLayout(new GridLayout(1, 2));
    raceStatusPanel.setPreferredSize(new Dimension(800, 500));

    raceProgressPanel = new JPanel();
    raceProgressPanel.setPreferredSize(new Dimension(350, 500));
    raceProgressPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    raceTextStatusPanel = new JPanel();
    raceTextStatusPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    cars = new JTextField(8);
    laps = new JTextField(8);
    crashProbability = new JTextField(8);
    fuelProbability = new JTextField(8);

    startRaceButton = new JButton("Iniciar corrida");

    statusTextArea = new JTextArea(30, 30);
    statusTextArea.setEditable(false);
    statusTextArea.setLineWrap(true);
    scroll = new JScrollPane(statusTextArea);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    // gbc.insets = new Insets(5,5,5,5);
    gbc.gridx = 0;
    gbc.gridy = 0;
    inputsPanel.add(new JLabel("Quantidade de carros: "), gbc);
    gbc.gridx = 0;
    gbc.gridy = 1;
    inputsPanel.add(cars, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    inputsPanel.add(new JLabel("Quantidade de voltas: "), gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    inputsPanel.add(laps, gbc);
    gbc.gridx = 2;
    gbc.gridy = 0;
    inputsPanel.add(new JLabel("Probabilidade de quebra: "), gbc);
    gbc.gridx = 2;
    gbc.gridy = 1;
    inputsPanel.add(crashProbability, gbc);
    gbc.gridx = 3;
    gbc.gridy = 0;
    inputsPanel.add(new JLabel("Probabilidade de abastecimento: "), gbc);
    gbc.gridx = 3;
    gbc.gridy = 1;
    inputsPanel.add(fuelProbability, gbc);
    gbc.gridx = 2;
    gbc.gridy = 2;
    gbc.gridwidth = 3;

    inputsPanel.add(startRaceButton, gbc);
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(inputsPanel, gbc);
    /* fim do painel dos inputs */

    raceTextStatusPanel.add(scroll);

    raceStatusPanel.add(raceProgressPanel);
    raceStatusPanel.add(raceTextStatusPanel);

    gbc.gridx = 0;
    gbc.gridy = 1;
    add(raceStatusPanel, gbc);

    startRaceButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        lapsAmount = Integer.parseInt(laps.getText().trim());
        carsAmount = Integer.parseInt(cars.getText().trim());
        fuelProb = Integer.parseInt(fuelProbability.getText().trim());
        crashProb = Integer.parseInt(crashProbability.getText().trim());

        Corrida corrida = new Corrida(carsAmount, lapsAmount, crashProb, fuelProb);
        corrida.start();
      }
    });
  }

  public static void printStatus(String args) {
    statusTextArea.append(args + "\n");
  }
}
