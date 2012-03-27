package javaPlayExtras;

import java.awt.Rectangle;
import javaPlay.GameObject;

public abstract class ObjetoComGravidade extends GameObject {

    public static int GRAVIDADE = 5;

    protected int yVelocidade = 0;
    protected boolean estaNoChao = false;

    public void step(long timeEllapsed) {
        this.controlePulo();
    }

    public void impulso(int forca) {
        this.yVelocidade = forca;
        this.estaNoChao = false;
    }

    public void paraSubida() {
        if (this.yVelocidade > 0) {
            this.yVelocidade = 0;
        }
    }

    private void controlePulo() {
        if (!this.estaNoChao) {
            this.y -= this.yVelocidade;
            this.yVelocidade -= GRAVIDADE;
        }
    }

    public void chegouChao() {
        this.estaNoChao = true;
        this.yVelocidade = 0;
    }

    public void saiuChao() {
        this.estaNoChao = false;        
    }       

    public boolean estaDescendo() {
        return (this.yVelocidade < 0);
    }

    public boolean estaSubindo() {
        return (this.yVelocidade > 0);
    }
}
