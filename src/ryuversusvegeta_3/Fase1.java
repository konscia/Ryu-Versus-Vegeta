package ryuversusvegeta_3;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.CenarioComColisao;
import javax.swing.JOptionPane;

public class Fase1 implements GameStateController{

  private CenarioComColisao cenario;
  private Inimigo inimigo;
  private Ryu ryu;

  public void load() {

     this.ryu = new Ryu();
     this.inimigo = new Inimigo(600, 200);
     try {
        this.cenario = new CenarioComColisao("resources/cenario1.scn");
        this.cenario.adicionaObjeto(ryu); //Aqui, o controle de colisão ´é transferido para o cenario
        this.cenario.adicionaObjeto(this.inimigo);
    } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     
  }

  public void step(long timeElapsed) {
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
