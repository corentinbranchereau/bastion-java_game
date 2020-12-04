import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Mine extends Batiment{
  Image image;

  public Mine(int x,int y,int joueur){
    super(x,y,joueur);
    this.image = new ImageIcon(getClass().getResource("./textures/eolienne/mineGif.gif")).getImage();
  }

  public void dessine(Graphics2D g2d,int largeurCase,int hauteurCase,JFrame frame){

  }

  public void falling(){
    
  }

  public Image getImage(){
    return this.image;
  }

}
