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

public abstract class Objet{
    protected double positionX;
    protected double positionY;
    protected BufferedImage texture;

  public Objet(int x,int y){
    positionX=x;
    positionY=y;
  }

  public int getPositionX(){
    return (int) positionX;
  }

  public int getPositionY(){
    return (int) positionY;
  }

  public BufferedImage getTexture(){
    return texture;
  }
}
