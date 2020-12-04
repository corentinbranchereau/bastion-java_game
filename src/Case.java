import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;


public class Case{
  //déclaration des variables//

  public int x; //coordonnée X du premier pixel de la case (en haut a gauche)
  public int y;//coordonnée Y du premier pixel de la case (en haut a gauche)
  public int largeur; //largeur de la case en pixel
  public int hauteur; //hauteur de la case en pixel
  public int largeurFenetre;
  public int hauteurFenetre;
  public int joueur;



  //CONSTRUCTEUR//
  public Case(int hauteurP,int largeurP,int nbColonne,int nbLigne,int positionX,int positionY,int joueur){


    largeurFenetre=largeurP;
    hauteurFenetre=hauteurP;
    this.largeur=(largeurP/nbColonne);
    this.hauteur=(hauteurP/nbLigne);
    this.x=(largeur*positionX);
    this.y=(hauteur*positionY);
    this.joueur=joueur;
    System.out.println("position"+positionX+"  "+positionY);





    //this.image.setIcon(new ImageIcon(new ImageIcon("./textures/canon.png").getImage().getScaledInstance(largeur,hauteur, Image.SCALE_DEFAULT)));

  }

  //METHODES UTILES AU JEU//

  public void deplacement (int dx, int dy){

    if(joueur==1){
      if(dx==1 && (this.x+2*this.largeur)<=(largeurFenetre)/2){
        this.x=this.x+this.largeur;
      }

      if(dx==-1&& (this.x-this.largeur)>=0){
          this.x=this.x-this.largeur;
      }
      if(dy==-1 && (this.y-this.hauteur)>=1){
            this.y=this.y-this.hauteur;
      }
      if(dy==1 && (this.y+2*this.hauteur)<=hauteurFenetre){
              this.y=this.y+this.hauteur;
      }
    }
    if(joueur==2){
      if(dx==1 && (this.x+2*this.largeur)<=(largeurFenetre)){
        this.x=this.x+this.largeur;
      }

      if(dx==-1&& (this.x-this.largeur)>=(largeurFenetre/2)){
          this.x=this.x-this.largeur;
      }
      if(dy==-1 && (this.y-this.hauteur)>=1){
            this.y=this.y-this.hauteur;
      }
      if(dy==1 && (this.y+2*this.hauteur)<=hauteurFenetre){
              this.y=this.y+this.hauteur;
      }
    }
  }

  //GETTEURS ET SETTEURS//

  public int getLargeur(){
    return largeur;
  }

  public int getHauteur(){
    return hauteur;
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public int getJoueur(){
    return joueur;
  }

}
