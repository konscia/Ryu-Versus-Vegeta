
package ryuversusvegeta_final;

import javaPlay.GameObject;

public abstract class ObjetoComGravidade extends GameObject {

  public static int GRAVIDADE = 5;
  public static int yCHAO = 460;

  protected int altura;
  protected int yVelocidade = 0;
  protected boolean pulo = false;
  protected int impulso = 0;

  public void step(long timeEllapsed){      
      this.controlePulo();
  }

  public void setAltura(int altura){
      this.altura = altura;
  }

  public void posicionaNoChao(){
        /**
         * Imagens tem alturas diferentes, logo, para que os pés
         * do personagem pareçam enconstar o chão é preciso garantir que
         * a posição y + a altura da imagem sejam igual ao Chão.
         * Invertendo a equeação fica y = yCHAO - altura
         */
      this.y = yCHAO - this.altura;
    }

  public boolean estaPulando(){
      return this.pulo;
  }

  public void impulso(int forca){
      this.yVelocidade = forca;
  }

  private void controlePulo(){
    this.y -= this.yVelocidade;
    if( (this.y+this.altura) < yCHAO){
      this.yVelocidade -= GRAVIDADE;
      this.pulo = true;
    }

    if((this.y+this.altura) > yCHAO) {
      this.posicionaNoChao();
      this.yVelocidade = 0;
      this.pulo = false;
    }
  }
}
