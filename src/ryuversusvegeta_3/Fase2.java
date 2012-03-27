package ryuversusvegeta_3;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import javaPlay.GameStateController;
import javaPlayExtras.CenarioComColisao;
import javax.swing.JOptionPane;

public class Fase2 implements GameStateController{

  private CenarioComColisao cenario;
  private ArrayList<Inimigo> inimigos;
  private Ryu ryu;

  private long contadorTempo;

  public void load() {

     this.ryu = new Ryu();
     this.inimigos = new ArrayList<Inimigo>();

     try {
        this.cenario = new CenarioComColisao("resources/cenario2.scn");
        this.cenario.adicionaObjeto(ryu); //Aqui, o controle de colisão ´é transferido para o cenario
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
     for(Inimigo inimigo : this.inimigos){
         if(this.ryu.temColisao( inimigo )){
             this.ryu.perdeVida(10);
             this.ryu.setX(200);
         }         
         inimigo.step(timeElapsed);         
     }     

     if(this.cenario.temColisaoComTile(ryu, 4)){
         JOptionPane.showMessageDialog(null, "Parabéns, você venceu.");
         System.exit(0);
     }
    
     this.cenario.step(timeElapsed);

     contadorTempo += timeElapsed;
     if(contadorTempo > 3000){ //tres segundos
         Inimigo novo = new Inimigo(750, 50);
         this.inimigos.add( novo );
         this.cenario.adicionaObjeto( novo );
         contadorTempo -= 3000;
     }
  }

  public void draw(Graphics g) {
      g.setColor(Color.CYAN);
      g.fillRect(0, 0, 800, 600);

      
      this.cenario.draw(g);
      this.ryu.draw(g);
      for(Inimigo inimigo : this.inimigos){
          inimigo.draw(g);
      }
  }

  public void unload() {}
  public void start() {}
  public void stop() {}

}
