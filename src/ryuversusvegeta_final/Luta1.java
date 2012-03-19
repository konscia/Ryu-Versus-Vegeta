package ryuversusvegeta_final;

import java.awt.Color;
import java.awt.Graphics;
import javaPlay.GameStateController;

public class Luta1 implements GameStateController{

  private Vegeta vegeta;
  private Ryu ryu;

  public void load() {
    this.vegeta = new Vegeta();
    this.ryu = new Ryu();
  }

  public void step(long timeElapsed) {
     this.vegeta.step(timeElapsed);
     this.ryu.step(timeElapsed);
     
     if(this.vegeta.atacaSoco( this.ryu.getRectangle() )){
         this.ryu.levaSoco();
     }
  }

  public void draw(Graphics g) {
      g.setColor(Color.CYAN);
      g.fillRect(0, 0, 800, 600);

      g.setColor(Color.BLACK);
      g.fillRect(0, 460, 800, 140);
      
      this.vegeta.draw(g);
      this.ryu.draw(g);
  }

  public void unload() {}
  public void start() {}
  public void stop() {}

}
