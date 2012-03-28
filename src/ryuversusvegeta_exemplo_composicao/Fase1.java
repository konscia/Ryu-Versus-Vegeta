package ryuversusvegeta_exemplo_composicao;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Keyboard;
import javaPlay.Keys;
import javaPlayExtras.CenarioComColisao;
import javax.swing.JOptionPane;

public class Fase1 implements GameStateController{

  private CenarioComColisao cenario;
  private Inimigo inimigo;
  private Ryu ryu;

  public Fase1(Ryu personagem){
     //Composição tipo 3
     //A fase é composta, dentre outras coisas,
     //Por um objeto da classe Ryu
     //Este objeto vem de fora da classe.
     this.ryu = personagem;
  }

  public void load() {
     
     this.inimigo = new Inimigo(600, 200);     
     //Composição tipo 2.
     //O Inimigo é composto por um GameObject que ele persegue.
     //Porém, o objeto perseguido pode ser informado em qualquer ponto do programa.
     //Isso me permite por exemplo,
     //Que um inimigo persiga e pare de perseguir o Ryu conforme minha ordem
     //Para ver funcionando, pressione P para perseguir e L largar.
     //E confira o código no método Step

     try {
        this.cenario = new CenarioComColisao("resources/cenario1.scn");
        this.cenario.adicionaObjeto(ryu); //Aqui, o controle de colisão ´é transferido para o cenario
        this.cenario.adicionaObjeto(this.inimigo);
    } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     
  }

  public void step(long timeElapsed) {

      Keyboard teclado = GameEngine.getInstance().getKeyboard();
      if(teclado.keyDown( Keys.P )){
          this.inimigo.persegueObjeto(ryu);
      } else if (teclado.keyDown( Keys.L )){
          this.inimigo.persegueObjeto(null);
      }

     if(this.ryu.estaMorto()){
         JOptionPane.showMessageDialog(null, "Perdeu.");
         System.exit(0);
     }

     this.ryu.step(timeElapsed);
     this.inimigo.step(timeElapsed);

     if(this.ryu.temColisao( this.inimigo )){
         this.ryu.perdeVida(10);
         this.ryu.setX(200);
     }

     if(this.cenario.temColisaoComTile(ryu, 4)){
         GameEngine.getInstance().setNextGameStateController(2);
     }
     
     this.cenario.step(timeElapsed);    
  }

  public void draw(Graphics g) {
      g.setColor(Color.CYAN);
      g.fillRect(0, 0, 800, 600);

      
      this.cenario.draw(g);
      this.ryu.draw(g);
      this.inimigo.draw(g);
  }

  public void unload() {}
  public void start() {}
  public void stop() {}

}
