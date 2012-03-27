package ryuversusvegeta_3;

import java.awt.Color;
import java.awt.Graphics;
import javaPlayExtras.ObjetoComGravidade;

public class Inimigo extends ObjetoComGravidade {

    private int velocidade;

    public Inimigo(int x, int y){
        this.x = x;
        this.y = y;
        this.largura = 32;
        this.altura = 32;
        this.velocidade = 5;
    }

    public void step(long timeElapsed){
        super.step(timeElapsed);
        this.x -= this.velocidade;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x, y, this.largura, this.altura);
    }

}
