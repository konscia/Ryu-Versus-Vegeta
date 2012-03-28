package ryuversusvegeta_exemplo_composicao;

import java.awt.Color;
import java.awt.Graphics;
import javaPlay.GameObject;
import javaPlayExtras.ObjetoComGravidade;

public class Inimigo extends ObjetoComGravidade {

    private GameObject objetoPerseguido;
    private int velocidade;

    public Inimigo(int x, int y) {
        this.x = x;
        this.y = y;
        this.largura = 32;
        this.altura = 32;
        this.velocidade = 5;
    }

    public void step(long timeElapsed) {
        super.step(timeElapsed);

        if (this.objetoPerseguido != null) {
            this.persegue(this.objetoPerseguido.getX(), this.objetoPerseguido.getY());
        }
    }

    //Composição Tipo 2.
    //O objeto que compõe a clase Inimigo
    //é inicializado através de um método qualquer da classe Inimigo
    public void persegueObjeto(GameObject obj){
        this.objetoPerseguido = obj;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x, y, this.largura, this.altura);
    }

    public void persegue(int xPerseguido, int yPerseguido) {
        if (this.x < xPerseguido) {
            this.x += 5;
        } else {
            this.x -= 5;
        }
    }
}
