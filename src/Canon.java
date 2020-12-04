import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public abstract class Canon extends Batiment{

  //déclaration des variables//
  protected double vitesseX;
  protected double vitesseY;
  protected BufferedImage jauge;
  protected boolean chargement=false;
  protected int tempsDeCharge=0;
  protected int angle;
  protected boolean rotation;
  protected boolean sensHoraire;
  protected double doubleY;
  protected double force;

  //constructeur//
  public Canon(int positionX, int positionY,int joueur){

    super(positionX, positionY,joueur);
    doubleY=positionY;
  }

  //Canons//
  public abstract void tourne();

  public abstract void puissance();

  public void tempsChargement(){
    tempsDeCharge++;
    if(tempsDeCharge>=300){
      chargement=false;
      tempsDeCharge=0;
    }
  }

  public void falling(){
    if(fallingBlock==true){
      doubleY+=0.45;
      this.positionY=(int)doubleY;

    }

  }



  //getteurs et setteurs//
  public int getAngle(){
     return angle;
   }

  public BufferedImage getJauge(){
    return jauge;
  }

  public void setAngle(int a){
     angle=a;
   }

  public void setRotation(boolean b){
     rotation=b;
  }

  public boolean getRotation(){
    return rotation;
  }

  public boolean getChargement(){
    return chargement;
  }

  public void setChargement(boolean b){
    chargement=b;
  }

  public int getTempsDeCharge(){
    return tempsDeCharge;
  }

  public double getForce(){
    return force;
  }

  public void setForce(double f){
    this.force=f;
  }



}
