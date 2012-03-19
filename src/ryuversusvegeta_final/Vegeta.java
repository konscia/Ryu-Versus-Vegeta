package ryuversusvegeta_final;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.Keyboard;
import javaPlay.Sprite;
import javax.swing.JOptionPane;

public class Vegeta extends ObjetoComGravidade {

    protected Imagem imgNormal;
    protected Imagem imgTras;
    protected Imagem imgFrente;
    protected Imagem imgPulo;
    protected Imagem imgApanhando;
    protected Imagem imgSoco;
    protected Imagem imgChute;

    //Esta é a imagem que sempre será desenhada e utilizada para colisão
    //Ela é uma referência para uma das imagens acima.
    protected Imagem imgAtual;

    protected int velocidade = 10;
    protected int vida = 100;

    protected EstadoPersonagem estado;
    protected int forcaPulo = 38;

    public Vegeta() {
        try {
            this.imgNormal = new Imagem("resources/vegeta/normal.png");
            this.imgTras = new Imagem("resources/vegeta/tras.png");
            this.imgFrente = new Imagem("resources/vegeta/frente.png");
            this.imgPulo = new Imagem("resources/vegeta/pulo.png");
            this.imgSoco = new Imagem("resources/vegeta/soco_esquerda.png");
            this.imgChute = new Imagem("resources/vegeta/chute.png");
            this.imgApanhando = new Imagem("resources/vegeta/apanhando.png");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Exceção: "+ex.getMessage());
            System.exit(0);
        }

        this.imgAtual = this.imgNormal;
        this.x = 20;

        //Essencial par o objetoComGravidade poder trabalhar
        this.setAltura( this.imgAtual.pegaAltura() );
        this.posicionaNoChao();
    }

    public void step(long timeElapsed) {
        //Executa step da classe ObjetoComMovimento
        super.step(timeElapsed);

        Keyboard keyboard = GameEngine.getInstance().getKeyboard();

        if( keyboard.keyDown(Keys.DIREITA) ){
          this.frente();
        } else if( keyboard.keyDown(Keys.ESQUERDA) ){
          this.tras();
        } else if( keyboard.keyDown(Keys.N) ){
          this.soco();
        } else if( keyboard.keyDown(Keys.M) ){
          this.chute();
        } else if( keyboard.keyDown(Keys.ESPACO)){
          this.pula();
        }else {
          this.normal();
        }
    }


    public void alteraImagem(Imagem novaImagem){
        this.imgAtual = novaImagem;
        this.setAltura( this.imgAtual.pegaAltura() );

        if(!this.estaPulando()){
            this.posicionaNoChao();
        }
    }

    public void normal() {
        
        if(this.estado == EstadoPersonagem.APANHANDO ){
            this.alteraImagem( this.imgApanhando );
        } else if (this.estaPulando()) {
            this.alteraImagem( this.imgPulo );
        } else {
            this.estado = EstadoPersonagem.NORMAL;
            this.alteraImagem( this.imgNormal );
        }
    }

    public void pula() {        
        if(this.estaPulando()){
            return;
        }

        this.alteraImagem(this.imgPulo);
        this.impulso(this.forcaPulo);
    }

    public void soco() {
        this.alteraImagem(this.imgSoco);
        this.estado = EstadoPersonagem.SOCO;
    }

    public void chute() {
        this.alteraImagem(this.imgChute);
        this.estado = EstadoPersonagem.CHUTE;
    }

    public void frente() {
        this.alteraImagem(this.imgFrente);
        this.x += this.velocidade;        
    }

    public void tras() {
        this.alteraImagem(this.imgTras);
        this.x -= this.velocidade;
    }
    
    public boolean atacaSoco(Rectangle retangulo){
        if(this.estado != EstadoPersonagem.SOCO){
            return false;
        }
        
        if(this.getRectangle().intersects(retangulo)){
            return true;
        }
        
        return false;
    }

    public void levaSoco() {
        this.diminuiVida( 20 );
        this.x -= 20;
        this.alteraImagem( this.imgApanhando );
        this.estado = EstadoPersonagem.APANHANDO;
    }

    public void diminuiVida(int numPontos) {
        this.vida -= numPontos;
    }
   
    public boolean estaMorto() {
        return (this.vida <= 0);
    }



    public Rectangle getRectangle() {
        return new Rectangle(this.x, this.y, this.imgAtual.pegaLargura(), this.imgAtual.pegaAltura());
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.drawString(this.vida + "", this.x + 20, this.y - 10);

        g.drawRect(this.x, this.y, this.imgAtual.pegaLargura(), this.imgAtual.pegaAltura());
        
        this.imgAtual.draw(g, this.x, this.y);
    }
}

