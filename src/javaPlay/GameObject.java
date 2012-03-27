/*
 * GameObject
 */
package javaPlay;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javaPlayExtras.ObjetoComGravidade;

/**
 * @author VisionLab/PUC-Rio
 */
public abstract class GameObject {

    protected int x;
    protected int y;

    protected int altura;
    protected int largura;

    public abstract void step(long timeElapsed);
    public abstract void draw(Graphics g);

    public int getX() {
        return x;
    }

    public int getMaxX(){
        return (this.x + this.largura -1 );
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getMaxY(){
        return (this.y+this.altura -1);
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public Point getPontoCentral(){
        Point ponto = new Point();
        ponto.x = ( this.getX()/2 + this.getMaxX()/2 );
        ponto.y = ( this.getY()/2 + this.getMaxY()/2 );
        return ponto;
    }

    public Point getPontoMin(){
        return new Point(this.x, this.y);
    }

    public Point getPontoMax(){
        //O uso do -1 é para melhorar o cálculo da colisão
        return new Point(this.getMaxX() - 1, this.getMaxY() - 1);
    }

    public Rectangle getRetangulo() {
        return new Rectangle(this.x, this.y, this.largura, this.altura);
    }

    public boolean temColisao(Rectangle retangulo) {
        return this.getRetangulo().intersects(retangulo);
    }

    public boolean temColisao(ObjetoComGravidade obj) {
        return this.getRetangulo().intersects(obj.getRetangulo());
    }
}
