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

public abstract class Batiment{

  public BufferedImage texture;
  protected int positionX;
  protected int positionY;
  protected double pointsDeVie;
  protected boolean fallingBlock=false;
  protected int joueur;
  protected boolean porteur=true;



  //Constructeur//
  public Batiment(int x, int y,int joueur){

    positionX = x;
    positionY = y;
    pointsDeVie = 100;

    this.joueur=joueur;
  }

  //Deplacement//

  public void deplacement(){
    if(porteur==false){
      fallingBlock=true;
    }else{
      fallingBlock=false;
    }
  }

  public boolean Collisions(Projectile p,int l,int h){
    if( p.getJoueur()!= joueur ){
      if( (p.getPositionX() >= positionX) && (p.getPositionY()>=positionY) && (p.getPositionX()<positionX+l-p.getTaille()) && (p.getPositionY()<positionY+h-p.getTaille()) ){
        pointsDeVie=pointsDeVie- (int)(0.5*p.getMasse()*(p.getVx()*p.getVx() + p.getVy()*p.getVy() ));
        return true;
      }
      return false;
    }
    return false;
  }

  public abstract void falling();

  public abstract void dessine(Graphics2D g2d,int largeurCase,int hauteurCase,JFrame frame);

//Getteurs et Setteurs Généraux//
  public double getPointsDeVie(){
	  return this.pointsDeVie;
  }

  public void setPointsDeVie(double p){
	  this.pointsDeVie=p;

  }

  public int getPositionX(){
    return positionX;
  }

  public int getPositionY(){
    return positionY;
  }

  public int getJoueur(){
    return joueur;
  }

  public BufferedImage getTexture(){
     return texture;
   }

  public void setTexture(BufferedImage i){
    this.texture=i;
  }

  public boolean getFallingBlock(){
    return fallingBlock;
  }

  public void setFallingBlock(boolean b){
    this.fallingBlock=b;
  }

  public boolean getPorteur(){
    return porteur;
  }

  public void setPorteur(boolean b){
    porteur=b;
  }

}
