package ryuversusvegeta_1;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import javaPlay.GameStateController;
import javaPlayExtras.CenarioComColisao;
import javax.swing.JOptionPane;

public class Fase1 implements GameStateController{

  private CenarioComColisao cenario;

  public void load() {
     try {
         
        this.cenario = new CenarioComColisao("resources/cenario1.scn");
        
    } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
  }

  public void step(long timeElapsed) {
     this.cenario.step(timeElapsed);
  }

  public void draw(Graphics g) {
      g.setColor(Color.CYAN);
      g.fillRect(0, 0, 800, 600);

      this.cenario.draw(g);
  }

  public void unload() {}
  public void start() {}
  public void stop() {}

}
