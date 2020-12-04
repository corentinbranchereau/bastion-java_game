import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;



public class Mur extends Batiment implements Comparable<Mur>{



  //Constructeur//
  public Mur(int positionX, int positionY,int joueur){

    super(positionX, positionY,joueur);
    try {super.texture = ImageIO.read(new File("./textures/mur.png"));}
      catch (IOException e) {e.printStackTrace();} ;


  }

  public void dessine(Graphics2D g2d,int largeurCase,int hauteurCase,JFrame frame){

  }

  //Comparaison//
  @Override
  public int hashCode(){
    return this.positionX;
  }

  @Override
    public boolean equals(Object obj) {
      if(this == obj) {
            return true;
      }

      if(obj == null || obj.getClass()!= this.getClass()){
            return false;
      }
        // type casting of the argument.
        Mur mur = (Mur) obj;

        // comparing the state of argument with
        // the state of 'this' Object.

        return (this.positionX==(((Mur) obj).positionX) && this.positionY==(((Mur) obj).positionY));
    }

  @Override
    public int compareTo(Mur m){
      if(positionY>m.positionY){
        return 1;
      }
      if(positionY<m.positionY){
        return -1;
      }
      if(positionY==m.positionY && positionX>m.positionX){
        return 1;
      }
      if(positionY==m.positionY && positionX<m.positionX){
        return -1;
      }
      return 0;
    }

//getteurs et setteurs//

  public void falling(){
    if(fallingBlock==true){
      this.positionY+=2;
    }
  }
}
