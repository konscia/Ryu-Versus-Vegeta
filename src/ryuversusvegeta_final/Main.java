package ryuversusvegeta_final;

import javaPlay.GameEngine;

public class Main {

  public static void main(String[] args) {
    
    GameEngine.getInstance().addGameStateController(1, new Luta1());
    
    GameEngine.getInstance().setStartingGameStateController(1);

    //Executa um som ".wav". MP3 não funciona
    //AudioPlayer.play( "sounds/comeon.wav" );
    
    GameEngine.getInstance().setFramesPerSecond(30);
    GameEngine.getInstance().run();
    
  }
}
