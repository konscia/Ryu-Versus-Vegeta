package ryuversusvegeta_2;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javaPlay.GameStateController;
import javaPlayExtras.CenarioComColisao;
import javax.swing.JOptionPane;

public class Fase2 implements GameStateController{

  private CenarioComColisao cenario;
  private Ryu ryu;

  public void load() {

     this.ryu = new Ryu();    

     try {
        this.cenario = new CenarioComColisao("resources/cenario2.scn");
        this.cenario.adicionaObjeto(ryu); //Aqui, o controle de colisão ´é transferido para o cenario
    } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     
  }

  public void step(long timeElapsed) {          
     this.ryu.step(timeElapsed);

     if(this.cenario.temColisaoComTile(ryu, 4)){
         JOptionPane.showMessageDialog(null, "Parabéns, você venceu.");
         System.exit(0);
     }
    
     this.cenario.step(timeElapsed);
  }

  public void draw(Graphics g) {
      g.setColor(Color.CYAN);
      g.fillRect(0, 0, 800, 600);

      
      this.cenario.draw(g);
      this.ryu.draw(g);
  }

  public void unload() {}
  public void start() {}
  public void stop() {}

}
