import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class Nuage extends Objet{
  int largeur;
  int hauteur;


  public Nuage(int x,int y,int largeur,int hauteur){
    super(x,y);
    this.largeur=largeur;
    this.hauteur=hauteur;

    double a=Math.random();
    if(a>0.66){
      try {super.texture = ImageIO.read(new File("./textures/Clouds/nuage1.png"));}
        catch (IOException e) {e.printStackTrace();} ;
    }
    else if(a>0.33){
      try {texture = ImageIO.read(new File("./textures/Clouds/nuage2.png"));}
        catch (IOException e) {e.printStackTrace();} ;
    }
    else{
      try {texture = ImageIO.read(new File("./textures/Clouds/nuage3.png"));}
        catch (IOException e) {e.printStackTrace();} ;
    }
  }


  public int getLargeur(){
    return largeur;
  }

  public int getHauteur(){
    return hauteur;
  }

  public void deplacementNuage(int largeur,double vVent){

      positionX=(positionX+(vVent*0.1));
      if(positionX>largeur+400){
        positionX=-400;
      }
      if(positionX<-400){
        positionX=largeur+400;
      }
  }
}
