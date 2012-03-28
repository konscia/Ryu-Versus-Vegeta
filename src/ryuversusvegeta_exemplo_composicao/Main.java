package ryuversusvegeta_exemplo_composicao;

import javaPlay.GameEngine;
import javaPlay.GameStateController;

public class Main {

  public static void main(String[] args) {

    //Composição Tipo 3
    //O objeto parte é passado para o construtor do objeto
    //que o contém.
    Ryu ryu = new Ryu();
    GameStateController fase1 = new Fase1(ryu);
    GameStateController fase2 = new Fase2(ryu);

    GameEngine.getInstance().addGameStateController(1, fase1);
    GameEngine.getInstance().addGameStateController(2, fase2);
    
    GameEngine.getInstance().setStartingGameStateController(1);

    //Executa um som ".wav". MP3 não funciona
    //AudioPlayer.play( "sounds/comeon.wav" );
    
    GameEngine.getInstance().setFramesPerSecond(30);
    GameEngine.getInstance().run();
    
  }
}
