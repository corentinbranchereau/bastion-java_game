import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Eolienne extends Batiment{

  public Eolienne(int x,int y,int joueur){
    super(x,y,joueur);
  }

  public void falling(){
    if(fallingBlock==true){
      this.positionY+=2;
    }
  }

  public void dessine(Graphics2D g2d,int largeurCase,int hauteurCase,JFrame frame){
    Image icon = new ImageIcon(getClass().getResource("./textures/eolienne/MoulinGif2.gif")).getImage();
    g2d.drawImage(icon,positionX,positionY,largeurCase,hauteurCase,frame);

  }
}
