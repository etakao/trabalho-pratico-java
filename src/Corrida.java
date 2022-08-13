package src;

import screens.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Corrida extends Thread {
  private int nroCarros;
  private int nroVoltas;
  private int probQuebra;
  private int probAbast;

  private int voltaAtual;

  private List<Corredor> corredores;
  public static List<Corredor> podio = new ArrayList<>();

  public Corrida(int nroCarros, int nroVoltas, int probQuebra, int probAbast) {
    this.nroCarros = nroCarros;
    this.nroVoltas = nroVoltas;
    this.probQuebra = probQuebra;
    this.probAbast = probAbast;
    this.voltaAtual = 1;
  }

  public void prepararCorredores() {
    corredores = new ArrayList<>();

    for (int i = 0; i < nroCarros; i++) {
      corredores.add(new Corredor("Corredor_" + i, nroVoltas, probQuebra, probAbast));
    }
  }

  public void iniciarCorrida() {
    if (corredores.size() == 0) {
      System.out.println("Não há nenhum corredor pronto para a largarda!");
    }

    Frame.printStatus("___________________");
    Frame.printStatus("Foi dada a largada!");
    Frame.printStatus("-------------------");
    Frame.printStatus("");

    Frame.printStatus("___________________");
    Frame.printStatus("VOLTA " + voltaAtual);
    Frame.printStatus("-------------------");

    for (Corredor corredor : corredores) {
      corredor.start();
    }
  }

  public void mostrarPodioNaVolta(List<Corredor> corredores) {
    Collections.sort(corredores);

    if (corredores.get(0).getDistanciaPercorrida() > voltaAtual) {

      voltaAtual++;

      Frame.printStatus("1° Lugar: " + corredores.get(0).getNome());
      Frame.printStatus("2° Lugar: " + corredores.get(1).getNome());
      Frame.printStatus("3° Lugar: " + corredores.get(2).getNome());

      if (voltaAtual <= nroVoltas) {
        Frame.printStatus("___________________");
        Frame.printStatus("VOLTA " + voltaAtual);
        Frame.printStatus("-------------------");
      }
    }
  }

  public void exibirPodio() {
    if (voltaAtual < nroVoltas) {
      Frame.printStatus("A corrida ainda não terminou!");
    } else {
      Frame.printStatus("________________________________");
      Frame.printStatus("Fim da corrida! Confira o pódio: ");
      Frame.printStatus("--------------------------------");

      Frame.printStatus("1° Lugar: " + podio.get(0).getNome());
      Frame.printStatus("2° Lugar: " + podio.get(1).getNome());
      Frame.printStatus("3° Lugar: " + podio.get(2).getNome());
    }
  }

  @Override
  public void run() {
    prepararCorredores();

    iniciarCorrida();

    while (podio.size() < 3) {
      mostrarPodioNaVolta(corredores);

      try {
        sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    exibirPodio();
  }
}
