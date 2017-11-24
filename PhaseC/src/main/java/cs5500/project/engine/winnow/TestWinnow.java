package cs5500.project.engine.winnow;

import java.io.File;

public class TestWinnow {
  public static void main(String[] args){
    File one = new File("/Study/CS5500/Project/team-27/PhaseC/src/main/java/cs5500/project/engine/winnow/input/one.java");
    File two = new File("/Study/CS5500/Project/team-27/PhaseC/src/main/java/cs5500/project/engine/winnow/input/two.java");
    System.out.println(one.getAbsolutePath());
    if(one.isFile() && two.isFile()) {

      Winnow nGram = new Winnow(one.getAbsolutePath(),two.getAbsolutePath());

      float similarity = nGram.getSimilarity();

      System.out.println(similarity);
    }
    else {
      System.out.println("File not found");
    }

  }
}
