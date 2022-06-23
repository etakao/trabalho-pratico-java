package src;

import screens.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Corrida {
  private int nroCarros;
  private int nroVoltas;
  private int probQuebra;
  private int probAbast;

  private int voltaAtual;

  private List<Corredor> corredores;

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
      corredores.add(new Corredor("Corredor |" + i + "|", 0));
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

    while (voltaAtual <= nroVoltas) {
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
      Collections.sort(corredores);

      Frame.printStatus("________________________________");
      Frame.printStatus("Fim da corrida! Confira o pódio: ");
      Frame.printStatus("--------------------------------");

      Frame.printStatus("1° Lugar: " + corredores.get(0).getNome());
      Frame.printStatus("2° Lugar: " + corredores.get(1).getNome());
      Frame.printStatus("3° Lugar: " + corredores.get(2).getNome());
    }
  }
}
