package src;

import screens.*;

import java.util.ArrayList;
import java.util.List;

public class Corrida {
  private int nroCarros;
  private int nroVoltas;
  private int probQuebra;
  private int probAbast;

  private int voltaAtual;
  private Corredor primeiroColocado;
  private Corredor segundoColocado;
  private Corredor terceiroColocado;

  private List<Corredor> corredores;

  public Corrida(int nroCarros, int nroVoltas, int probQuebra, int probAbast) {
    this.nroCarros = nroCarros;
    this.nroVoltas = nroVoltas;
    this.probQuebra = probQuebra;
    this.probAbast = probAbast;
    this.voltaAtual = 1;

    primeiroColocado = new Corredor("Corredor", 0);
    segundoColocado = new Corredor("Corredor", 0);
    terceiroColocado = new Corredor("Corredor", 0);
  }

  public void prepararCorredores() {
    corredores = new ArrayList<>();

    for (int i = 0; i < nroCarros; i++) {
      corredores.add(new Corredor("Corredor |" + i + "|", 0));
    }
  }

  public void ordenarPodio(List<Corredor> corredores) {
    for (Corredor corredor : corredores) {
      if (corredor.getDistanciaPercorrida() > primeiroColocado.getDistanciaPercorrida()) {
        terceiroColocado = segundoColocado;
        segundoColocado = primeiroColocado;
        primeiroColocado = corredor;
      } else if (corredor.getDistanciaPercorrida() > segundoColocado.getDistanciaPercorrida()) {
        terceiroColocado = segundoColocado;
        segundoColocado = corredor;
      } else if (corredor.getDistanciaPercorrida() > terceiroColocado.getDistanciaPercorrida()) {
        terceiroColocado = corredor;
      }
    }
  }

  public void mostrarPodioNaVolta(List<Corredor> corredores) {
    ordenarPodio(corredores);

    if (primeiroColocado.getDistanciaPercorrida() > voltaAtual) {
      voltaAtual++;

      Frame.printStatus("1° Lugar: " + primeiroColocado.getNome());
      Frame.printStatus("2° Lugar: " + segundoColocado.getNome());
      Frame.printStatus("3° Lugar: " + terceiroColocado.getNome());
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

    while (voltaAtual < nroVoltas) {
      Frame.printStatus("___________________");
      Frame.printStatus("VOLTA " + voltaAtual);
      Frame.printStatus("-------------------");

      for (Corredor corredor : corredores) {
        if (corredor.abasteceu(probAbast, nroVoltas)) {
          Frame.printStatus("! O " + corredor.getNome() +
              " parou para abastecer !");
        } else if (corredor.quebrou(probQuebra)) {
          Frame.printStatus("!! O carro do " + corredor.getNome() +
              " quebrou e foi ao pitstop !!");
        } else {
          corredor.correu(nroVoltas);
        }
      }

      mostrarPodioNaVolta(corredores);
    }
  }

  public void exibirPodio() {
    if (voltaAtual < nroVoltas) {
      Frame.printStatus("A corrida ainda não terminou!");
    } else {
      ordenarPodio(corredores);

      Frame.printStatus("________________________________");
      Frame.printStatus("Fim da corrida! Confira o pódio: ");
      Frame.printStatus("--------------------------------");

      Frame.printStatus("1° Lugar: " + primeiroColocado.getNome());
      Frame.printStatus("2° Lugar: " + segundoColocado.getNome());
      Frame.printStatus("3° Lugar: " + terceiroColocado.getNome());
    }
  }
}
