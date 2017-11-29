package cs5500.project.engine.winnow;

import java.io.File;

public class TestWinnow {
  public static void main(String[] args){
    File one = new File("/Study/CS5500/Project/team-27/PhaseC/src/main/java/cs5500/project/engine/winnow/input/one.java");
    File two = new File("/Study/CS5500/Project/team-27/PhaseC/src/main/java/cs5500/project/engine/winnow/input/two.java");

    if(one.isFile() && two.isFile()) {

      Winnow nGram = new Winnow(one.getAbsolutePath(),two.getAbsolutePath());

      float similarity = nGram.getSimilarity();
    }
    else {
      System.out.println("File not found");
    }

  }
}
