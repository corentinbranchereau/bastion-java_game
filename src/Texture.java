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

public class Texture extends Objet{
  protected int largeur;
  protected int hauteur;
  protected boolean ressource=false;


  public Texture(int x,int y,int largeur, int hauteur,int t){
    //Blocs herbe//
    super(x,y);
    this.largeur=largeur;
    this.hauteur=hauteur;

    if(t==1){
      try {super.texture = ImageIO.read(new File("./textures/grass.png"));}
        catch (IOException e) {e.printStackTrace();} ;
    }
    if(t==2){
      double a=Math.random();
        if(a>0.2){
          try {super.texture = ImageIO.read(new File("./textures/dirt.png"));}
            catch (IOException e) {e.printStackTrace();} ;
        }
        else{
          ressource=true;
          try {super.texture = ImageIO.read(new File("./textures/ressource.png"));}
            catch (IOException e) {e.printStackTrace();} ;
        }
    }
    if(t==11){
      try {texture = ImageIO.read(new File("./textures/grassLeft.png"));}
        catch (IOException e) {e.printStackTrace();} ;
    }
    if(t==12){
      try {texture = ImageIO.read(new File("./textures/grassRight.png"));}
        catch (IOException e) {e.printStackTrace();} ;
    }
    if(t==21){
      try {texture = ImageIO.read(new File("./textures/dirtRight.png"));}
        catch (IOException e) {e.printStackTrace();} ;
    }
    if(t==22){
      try {texture = ImageIO.read(new File("./textures/dirtLeft.png"));}
        catch (IOException e) {e.printStackTrace();} ;
    }

  }

  public int getLargeur(){
    return largeur;
  }

  public int getHauteur(){
    return hauteur;
  }

  public boolean getRessource(){
    return ressource;
  }
}
