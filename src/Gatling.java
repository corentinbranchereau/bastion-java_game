import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;


public class Gatling extends Canon{

  //constructeur//
  public Gatling(int positionX, int positionY,int joueur){

    super(positionX, positionY,joueur);
    angle=0;
    force=16;

    //assigment des textures//
    if(joueur==1){
    try {super.texture = ImageIO.read(new File("./textures/canona.png"));}
      catch (IOException e) {e.printStackTrace();} ;
    try {super.jauge = ImageIO.read(new File("./textures/jauge1.png"));}
      catch (IOException e) {e.printStackTrace();} ;

    }
    if(joueur==2){
    try {super.texture = ImageIO.read(new File("./textures/canonb.png"));}
      catch (IOException e) {e.printStackTrace();} ;
    try {super.jauge = ImageIO.read(new File("./textures/jauge2.png"));}
      catch (IOException e) {e.printStackTrace();} ;
    }
  }

  //Canons//
  public void tourne(){


    if((angle>-90 && angle<90) && sensHoraire==true){
        angle=angle+2;

    }
    if((angle>-90 && angle<90) && sensHoraire==false){
        angle=angle-2;

    }
    if(angle<=-90){
      sensHoraire=true;
      angle=-89;
    }
    if(angle>=90){
      sensHoraire=false;
      angle=89;
    }

  }

  public void puissance(){

  }

  public void dessine(Graphics2D g2d,int largeurCase,int hauteurCase,JFrame frame){
    AffineTransform old = g2d.getTransform();
    g2d.rotate(Math.toRadians(angle),positionX+(largeurCase/2),positionY+(hauteurCase/2));
    g2d.drawImage(texture,positionX,positionY,largeurCase,hauteurCase,frame);

      if(joueur==1 && rotation==true){
          g2d.drawLine(positionX+(largeurCase/2),positionY+(hauteurCase/2),positionX+(7*largeurCase/2),positionY+(hauteurCase/2));
      }
      if(joueur==2 && rotation==true){
          g2d.drawLine(positionX+(largeurCase/2),positionY+(hauteurCase/2),positionX-(5*largeurCase/2),positionY+(hauteurCase/2));
      }
    g2d.setTransform(old);

      if(joueur==1 && rotation==true){
          g2d.drawImage(jauge,positionX+largeurCase/2,positionY-5*hauteurCase/2,3*largeurCase,6*hauteurCase,frame);
      }
      if(joueur==2 && rotation==true){
          g2d.drawImage(jauge,positionX-5*largeurCase/2,positionY-5*hauteurCase/2,3*largeurCase,6*hauteurCase,frame);
      }
      if(chargement){
          g2d.setColor(Color.white);
          g2d.fillRect(positionX,positionY+9*hauteurCase/10,largeurCase,hauteurCase/10);
          g2d.setColor(Color.black);
          g2d.fillRect(positionX,positionY+9*hauteurCase/10,largeurCase*tempsDeCharge/300,hauteurCase/10);
      }

  }


}
