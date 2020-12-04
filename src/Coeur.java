import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Coeur extends Batiment{


	//constructeur//
  public Coeur(int positionx, int positiony,int joueur){
    super(positionx, positiony,joueur);
    super.pointsDeVie=60000;


    try {super.texture = ImageIO.read(new File("./textures/coeur.png"));}
      catch (IOException e) {e.printStackTrace();} ;
  }

  public void dessine(Graphics2D g2d,int largeurCase,int hauteurCase,JFrame frame){

  }
  public void falling(){

  }



}
