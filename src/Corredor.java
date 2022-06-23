package src;

public class Corredor {
  private String nome;
  private int posicao;
  private float distanciaPercorrida;

  private float volta;

  public Corredor(String nome, float distanciaPercorrida) {
    this.nome = nome;
    this.distanciaPercorrida = distanciaPercorrida;
  }

  // @Override
  // public int compareTo(Corredor corredor) {
  // return this.distanciaPercorrida.compareTo(corredor.getDistanciaPercorrida());
  // }

  public String getNome() {
    return nome;
  }

  public int getPosicao() {
    return posicao;
  }

  public void setPosicao(int posicao) {
    this.posicao = posicao;
  }

  public float getDistanciaPercorrida() {
    return distanciaPercorrida;
  }

  // contabiliza a "distancia" que o corredor correu
  public void correu(int nroVoltas) {
    volta = (float) (0.8 + (float) (Math.random() * 1.2));
    distanciaPercorrida += volta;
  }

  // aplica a probabilidade de quebra do carro
  public boolean quebrou(int probQuebra) {
    int prob = (int) (Math.random() * 100);

    if (prob <= probQuebra) {
      return true;
    } else
      return false;
  }

  // aplica a probabilidade de abastecimento do carro
  public boolean abasteceu(int probAbast, int nroVoltas) {
    // aplica a probabilidade de abastecimento somente apos metade das voltas
    if (distanciaPercorrida > nroVoltas / 2) {
      int prob = (int) (Math.random() * 100);

      if (prob <= probAbast) {
        return true;
      } else
        return false;
    }

    return false;
  }
}