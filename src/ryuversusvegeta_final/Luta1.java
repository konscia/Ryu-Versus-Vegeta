package ryuversusvegeta_final;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameStateController;
import javaPlayExtras.CenarioComColisao;
import javax.swing.JOptionPane;

public class Luta1 implements GameStateController {

    private Vegeta vegeta;
    private Ryu ryu;
    private CenarioComColisao cenario;

    public void load() {
        this.vegeta = new Vegeta();
        this.ryu = new Ryu();

        try {
            this.cenario = new CenarioComColisao("resources/cenario3.scn");
            this.cenario.adicionaObjeto(ryu);
            this.cenario.adicionaObjeto(vegeta);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void step(long timeElapsed) {
        this.vegeta.step(timeElapsed);
        this.ryu.step(timeElapsed);

        if (this.vegeta.atacaSoco(this.ryu.getRetangulo())) {
            this.ryu.levaSoco();
        }

        this.cenario.step(timeElapsed);
    }

    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 800, 600);

        g.setColor(Color.BLACK);
        g.fillRect(0, 460, 800, 140);

        this.vegeta.draw(g);
        this.ryu.draw(g);
        this.cenario.draw(g);
    }

    public void unload() {
    }

    public void start() {
    }

    public void stop() {
    }
}
