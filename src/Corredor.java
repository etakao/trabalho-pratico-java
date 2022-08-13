package src;

import javax.swing.JProgressBar;

import screens.*;

public class Corredor extends Thread implements Comparable<Corredor> {
  private String nome;
  private int posicao;
  private double distanciaPercorrida;

  private int nroVoltas;
  private int probQuebra;
  private int probAbast;

  private double volta;

  private JProgressBar carProgress;

  public Corredor(String nome, int nroVoltas, int probQuebra, int probAbast) {
    this.nome = nome;
    this.nroVoltas = nroVoltas;
    this.probQuebra = probQuebra;
    this.probAbast = probAbast;

    carProgress = new JProgressBar(0, 100);
    carProgress.setStringPainted(true);
    carProgress.setString(nome);
    
    Frame.raceProgressPanel.add(carProgress);
    Frame.raceProgressPanel.revalidate();
    distanciaPercorrida = 0;
  }

  @Override
  public int compareTo(Corredor corredor) {
    if (this.getDistanciaPercorrida() < corredor.getDistanciaPercorrida()) {
      return 1;
    } else if (this.getDistanciaPercorrida() == corredor.getDistanciaPercorrida()) {
      return 0;
    } else {
      return -1;
    }
  }

  public String getNome() {
    return nome;
  }

  public int getPosicao() {
    return posicao;
  }

  public void setPosicao(int posicao) {
    this.posicao = posicao;
  }

  public double getDistanciaPercorrida() {
    return distanciaPercorrida;
  }

  // contabiliza a "distancia" que o corredor correu
  public void correu() {
    volta = (double) ((double) (Math.random() * 1));
    distanciaPercorrida += volta;

    carProgress.setValue((int) Math.round((100 * distanciaPercorrida) / nroVoltas));
  }

  // aplica a probabilidade de quebra do carro
  public boolean quebrou() {
    int prob = (int) (Math.random() * 100);

    if (prob < probQuebra) {
      Frame.printStatus("!! O carro do " + nome + " quebrou e foi ao pitstop !!");
      return true;
    } else
      return false;
  }

  // aplica a probabilidade de abastecimento do carro
  public boolean abasteceu() {
    // aplica a probabilidade de abastecimento somente apos metade das voltas
    if (distanciaPercorrida > (nroVoltas / 2)) {
      int prob = (int) (Math.random() * 100);

      if (prob < probAbast) {
        Frame.printStatus("! O " + nome + " parou para abastecer !");
        return true;
      } else
        return false;
    }

    return false;
  }

  // método run da thread
  @Override
  public void run() {
    while (distanciaPercorrida < nroVoltas) {
      if (abasteceu()) {
        try {
          sleep(200);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      if (quebrou()) {
        try {
          sleep(400);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      correu();

      // tempo de espera para verificar a volta atual
      try {
        sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    if (Corrida.podio.size() < 3) {
      Corrida.podio.add(this);
    }
  }
}