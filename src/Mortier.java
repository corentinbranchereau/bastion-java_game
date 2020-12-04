import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class Mortier extends Canon{

  //déclaration des variables//


  //constructeur//
  public Mortier(int positionX, int positionY,int joueur){

    super(positionX, positionY,joueur);
    angle=-120;
    force=15;


    //assigment des textures//
    if(joueur==1){
    try {super.texture = ImageIO.read(new File("./textures/mortier.png"));}
      catch (IOException e) {e.printStackTrace();} ;
    try {super.jauge = ImageIO.read(new File("./textures/trajectoire.png"));}
      catch (IOException e) {e.printStackTrace();} ;

    }
    if(joueur==2){
    try {super.texture = ImageIO.read(new File("./textures/mortierB.png"));}
      catch (IOException e) {e.printStackTrace();} ;
    try {super.jauge = ImageIO.read(new File("./textures/trajectoire2.png"));}
      catch (IOException e) {e.printStackTrace();} ;
    }
  }

  //Canons//
  public void tourne(){

  }

  public void puissance(){
    if((force>10 && force<20) && sensHoraire==true){
        force+=0.15;

    }
    if((force>10 && force<20) && sensHoraire==false){
      force-=0.15;

    }
    if(force<=10){
      sensHoraire=true;
      force=11;
    }
    if(force>=20){
      sensHoraire=false;
      force=19;
    }
  }

  public void dessine(Graphics2D g2d,int largeurCase,int hauteurCase,JFrame frame){
      g2d.drawImage(texture,positionX,positionY,largeurCase,hauteurCase,frame);
      if(chargement){
          g2d.setColor(Color.white);
          g2d.fillRect(positionX,positionY+9*hauteurCase/10,largeurCase,hauteurCase/10);
          g2d.setColor(Color.black);
          g2d.fillRect(positionX,positionY+9*hauteurCase/10,largeurCase*tempsDeCharge/300,hauteurCase/10);
      }
      if (rotation) {
          g2d.setColor(Color.white);
          g2d.fillRect(positionX-largeurCase/2,positionY-4*hauteurCase/5,2*largeurCase,hauteurCase/5);
          g2d.setColor(Color.red);
          g2d.fillRect(positionX-largeurCase/2,positionY-4*hauteurCase/5,(int)(2*largeurCase*(force-10)/10),hauteurCase/5);
        }

  }




}
