package ryuversusvegeta_1;

import javaPlay.GameEngine;

public class Main {

  public static void main(String[] args) {
    
    GameEngine.getInstance().addGameStateController(1, new Fase1());
    
    GameEngine.getInstance().setStartingGameStateController(1);

    //Executa um som ".wav". MP3 não funciona
    //AudioPlayer.play( "sounds/comeon.wav" );
    
    GameEngine.getInstance().setFramesPerSecond(5);
    GameEngine.getInstance().run();
    
  }
}
