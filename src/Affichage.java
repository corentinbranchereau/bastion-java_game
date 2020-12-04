import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.applet.*;
import java.net.*;
import java.io.*;

public class Affichage extends JFrame implements KeyListener {

  private int largeurFenetre;
  private int hauteurFenetre;
  private int largeurCase; // largeur de la case en pixel
  private int hauteurCase; // hauteur de la case en pixel
  private int nbLigne;
  private int nbColonne;
  private Image fond;
  private ArrayList<Batiment> listeBatiments = new ArrayList<Batiment>();
  private ArrayList<Batiment> listeCanons = new ArrayList<Batiment>();
  private ArrayList<Batiment> listeMurs = new ArrayList<Batiment>();
  private ArrayList<Batiment> listeEoliennes = new ArrayList<Batiment>();
  private ArrayList<Mine> listeMines = new ArrayList<Mine>();
  private ArrayList<Texture> listeTextures = new ArrayList<Texture>();
  private ArrayList<Nuage> listeNuages = new ArrayList<Nuage>();
  private ArrayList<Projectile> listeProjectiles = new ArrayList<Projectile>();

  private ArrayList<ArrayList<Batiment>> listeTotale = new ArrayList<ArrayList<Batiment>>();

  private Case case1;
  private Case case2;
  private Coeur coeur1;
  private Coeur coeur2;
  private char gauche1, droite1, haut1, bas1, mur1, eolienne1, canon1, mortier1;
  private char gauche2, droite2, haut2, bas2, mur2, eolienne2, canon2, mortier2;
  private double energie1 = 20000;
  private double energie2 = 20000;
  private BufferedImage heart;
  private BufferedImage power;
  private double vVent = 0;
  private double vVentVise = -20;
  private double proba = 0;
  private Font stringFont = new Font("SansSerif", Font.PLAIN, 25);

  private Dimension offDimension;
  private Dimension d;
  private Image offImage;
  private Graphics offGraphics;
  private boolean fillSquare = false;
  private Graphics2D g2d;

  // CONSTRUCTEUR//
  public Affichage(int nbLigne, int nbColonne) {

    // récupération des dimension de l'ecran//
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    d = dimension;
    if ((dimension.getHeight() - 20) * 1.8 < dimension.getWidth()) {
      hauteurFenetre = (int) dimension.getHeight() - 20;
      largeurFenetre = (int) ((dimension.getHeight() - 20) * 1.8);
    } else {
      largeurFenetre = (int) dimension.getWidth();
      hauteurFenetre = (int) (dimension.getWidth() * (0.5555));
    }
    System.out.println("+++" + hauteurFenetre + "   " + largeurFenetre + "+++");

    // définition de la taille des Cases//
    largeurCase = (largeurFenetre / nbColonne);
    hauteurCase = (hauteurFenetre / nbLigne);
    this.nbLigne = nbLigne;
    this.nbColonne = nbColonne;

    // JFRAME Principal//
    this.setTitle("Bastion");
    this.setBounds(0, 0, largeurFenetre, hauteurFenetre);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // creation du JPanel qui contient les graphiques//
    JPanel panneauFond = new JPanel();
    panneauFond.setDoubleBuffered(true);
    panneauFond.setBounds(0, 0, largeurFenetre, hauteurFenetre);
    panneauFond.setLayout(null);
    panneauFond.setBackground(Color.green);

    // création des textures affichée en début de partie//
    creationTextures();

    // attribution des touches de chaque joueur//
    creationTouches();

    // création de la fenetre d'écoute des touches//
    JTextField textField = new JTextField();
    textField.setBounds(0, 0, 0, 0);
    textField.addKeyListener(this);
    this.add(textField);

    // ajout du Jpanel au JFRAME Principal//
    this.add(panneauFond);
    this.setVisible(true);
  }

  // MISE A JOUR DU JEU//

  // Mise a Jour tour par tour//
  public void update() {

    // ajout d'énergie//
    updateEnergie();

    // Mise a jour de la liste Totale//
    for (int i = 0; i < listeTotale.size(); i++) {
      listeTotale.remove(i);
    }
    listeTotale.add(listeMurs);
    listeTotale.add(listeCanons);
    listeTotale.add(listeEoliennes);

    // Mur//
    for (int i = 0; i < listeTotale.size(); i++) {
      for (int j = 0; j < listeTotale.get(i).size(); j++) {
        listeTotale.get(i).get(j).setPorteur(false);
      }
    }
    for (int i = 0; i < listeMurs.size(); i++) {
      Gravite();
    }
    for (int i = 0; i < listeTotale.size(); i++) {
      for (int j = 0; j < listeTotale.get(i).size(); j++) {
        listeTotale.get(i).get(j).deplacement();
        listeTotale.get(i).get(j).falling();
      }
    }

    // vent//
    proba++;
    if (Math.random() < (proba / 500000)) {
      vVentVise = (Math.random() * 60) - 30;
      proba = 0;
    }
    if (vVentVise > 0 && vVent < vVentVise) {
      vVent = vVent + 0.05;
    }
    if (vVentVise < 0 && vVent > vVentVise) {
      vVent = vVent - 0.05;
    }

    // deplacement des nuages//
    for (int i = 0; i < listeNuages.size(); i++) {
      listeNuages.get(i).deplacementNuage(largeurFenetre, vVent);
      // repaint(listeNuages.get(i).getPositionX(),listeNuages.get(i).getPositionY(),listeNuages.get(i).getLargeur(),listeNuages.get(i).getHauteur());
    }
    // rotation des canons//
    for (int i = 0; i < listeCanons.size(); i++) {
      Canon canon = (Canon) listeCanons.get(i);
      if (canon.getRotation() == true) {
        canon.tourne();
        canon.puissance();
        // repaint(canon.getPositionX()-2*largeurCase,canon.getPositionY()-5*largeurCase/2,5*largeurCase,6*hauteurCase);
      }
      if (canon.getChargement()) {
        canon.tempsChargement();
        // repaint(canon.getPositionX()-2*largeurCase,canon.getPositionY()-5*largeurCase/2,5*largeurCase,6*hauteurCase);

      }
    }
    // deplacement des projectiles//
    for (int i = 0; i < listeProjectiles.size(); i++) {
      int a = listeProjectiles.get(i).getPositionX() - 4 * listeProjectiles.get(i).getTaille();
      int b = listeProjectiles.get(i).getPositionY() - 4 * listeProjectiles.get(i).getTaille();
      int c = 8 * listeProjectiles.get(i).getTaille();
      actionProjectile(i);
      // repaint(a,b,c,c);
    }
  }

  public void paint(Graphics g) {
    // On update tous les graphiques dans une nouvelle image
    // que l'on affiche qu'a la fin pour éviter les scintillements (Double
    // Buffering)//
    // on utilise Graphics 2d pour simplifier les rotations//
    if ((offGraphics == null) || (d.width != offDimension.width) || (d.height != offDimension.height)) {
      offDimension = d;
      offImage = createImage(d.width, d.height);
      offGraphics = offImage.getGraphics();
    }
    g2d = (Graphics2D) offGraphics;
    g2d.drawImage(fond, 0, 0, largeurFenetre, hauteurFenetre, this);

    // affiachage Nuages//
    for (int i = 0; i < listeNuages.size(); i++) {
      g2d.drawImage(listeNuages.get(i).getTexture(), listeNuages.get(i).getPositionX(),
          listeNuages.get(i).getPositionY(), listeNuages.get(i).getLargeur(), listeNuages.get(i).getHauteur(), this);
    }

    // affichage herbe//
    for (int i = 0; i < listeTextures.size(); i++) {
      g2d.drawImage(listeTextures.get(i).getTexture(), listeTextures.get(i).getPositionX(),
          listeTextures.get(i).getPositionY(), listeTextures.get(i).getLargeur(), listeTextures.get(i).getHauteur(),
          this);
    }

    // affichage des murs//
    for (int i = 0; i < listeMurs.size(); i++) {
      g2d.drawImage(listeMurs.get(i).getTexture(), listeMurs.get(i).getPositionX(), listeMurs.get(i).getPositionY(),
          largeurCase, hauteurCase, this);
    }

    // affichage des batiments//
    for (int i = 0; i < listeBatiments.size(); i++) {
      g2d.drawImage(listeBatiments.get(i).getTexture(), listeBatiments.get(i).getPositionX(),
          listeBatiments.get(i).getPositionY(), largeurCase, hauteurCase, this);
    }

    // affichage des canons//
    for (int i = 0; i < listeCanons.size(); i++) {
      listeCanons.get(i).dessine(g2d, largeurCase, hauteurCase, this);
    }

    // Affichage des Eoliennes//
    for (int i = 0; i < listeEoliennes.size(); i++) {
      listeEoliennes.get(i).dessine(g2d, largeurCase, hauteurCase, this);
    }

    // Affichage des Mines//
    for (int i = 0; i < listeMines.size(); i++) {
      g2d.drawImage(listeMines.get(i).getImage(), listeMines.get(i).getPositionX(), listeMines.get(i).getPositionY(),
          largeurCase, hauteurCase, this);
    }

    // dessine les cases sélectionnnées//
    g2d.setColor(new Color(150, 150, 0, 100));
    g2d.fillRect(case1.getX(), case1.getY(), largeurCase, hauteurCase);
    g2d.setColor(new Color(150, 0, 150, 100));
    g2d.fillRect(case2.getX(), case2.getY(), largeurCase, hauteurCase);

    // Affichage des projectiles//
    for (int i = 0; i < listeProjectiles.size(); i++) {
      g2d.setColor(Color.red);
      g2d.fillOval(listeProjectiles.get(i).getPositionX(), listeProjectiles.get(i).getPositionY(),
          listeProjectiles.get(i).getTaille(), listeProjectiles.get(i).getTaille());
    }

    // Points de Vie//
    g2d.setColor(Color.white);
    g2d.fillRect(60, 80, 300, 20);
    g2d.fillRect(largeurFenetre - 360, 80, 300, 20);
    g2d.setColor(Color.white);
    g2d.drawRect(60, 80, 300, 20);
    g2d.drawRect(largeurFenetre - 360, 80, 300, 20);
    g2d.setColor(new Color(20, 0, 100, 200));
    g2d.fillRect(60, 80, ((int) (300 * (coeur1.getPointsDeVie() / 60000))), 20);
    g2d.fillRect(largeurFenetre - 360, 80, ((int) (300 * (coeur2.getPointsDeVie() / 60000))), 20);
    g2d.drawImage(heart, 50, 70, 40, 40, this);
    g2d.drawImage(heart, largeurFenetre - 370, 70, 40, 40, this);

    // Energie//
    String s = "Energie:  " + (int) energie1;
    g2d.setColor(Color.black);
    g2d.setFont(stringFont);
    g2d.drawString(s, 100, 150);

    String s2 = "Energie:  " + (int) energie2;
    g2d.setColor(Color.black);
    g2d.setFont(stringFont);
    g2d.drawString(s2, largeurFenetre - 320, 150);

    g2d.drawImage(power, 40, 120, 60, 40, this);
    g2d.drawImage(power, largeurFenetre - 380, 120, 60, 40, this);

    // on affiche l'image déjà dessinée//
    g.drawImage(offImage, 0, 0, this);

  }

  public void updateEnergie() {
    energie1++;
    energie2++;
    for (int i = 0; i < listeEoliennes.size(); i++) {
      if (listeEoliennes.get(i).getJoueur() == 1) {
        energie1 += (Math.abs(vVent) / 30) * (1 - (listeEoliennes.get(i).getPositionY() / hauteurFenetre));
      }
      if (listeEoliennes.get(i).getJoueur() == 2) {
        energie2 += (Math.abs(vVent) / 30) * (1 - (listeEoliennes.get(i).getPositionY() / hauteurFenetre));
      }
    }
    for (int i = 0; i < listeMines.size(); i++) {
      if (listeMines.get(i).getJoueur() == 1) {
        energie1 += 0.2;
      }
      if (listeMines.get(i).getJoueur() == 2) {
        energie2 += 0.2;
      }
    }
  }

  public void Gravite() {
    for (int k = 0; k < listeTotale.size(); k++) {
      for (int i = 0; i < listeTotale.get(k).size(); i++) {
        Batiment mi = listeTotale.get(k).get(i);
        int xi = listeTotale.get(k).get(i).getPositionX();
        int yi = listeTotale.get(k).get(i).getPositionY();

        boolean testPorteur = false;
        if (mi.getJoueur() == 1 && yi == ((int) (nbLigne * (hauteurCase) / 1.5)) - hauteurCase
            && xi <= nbColonne * (largeurCase / 4) + largeurCase) {

          mi.setPorteur(true);
          testPorteur = true;
        }
        if (mi.getJoueur() == 2 && yi == ((int) (nbLigne * (hauteurCase) / 1.5)) - hauteurCase
            && xi >= nbColonne * (3 * largeurCase / 4) - largeurCase) {

          mi.setPorteur(true);
          testPorteur = true;
        }

        for (int j = 0; j < listeMurs.size(); j++) {
          Mur mj = (Mur) listeMurs.get(j);
          int xj = listeMurs.get(j).getPositionX();
          int yj = listeMurs.get(j).getPositionY();
          // bloc mj au dessus//
          if (xj == xi && yj == yi - hauteurCase) {
            if (mj.getPorteur()) {
              mi.setPorteur(true);
              testPorteur = true;
            }
          }
          // bloc mj au dessous//
          if ((xj == xi && yj == yi + hauteurCase)) {
            if (mj.getPorteur()) {
              mi.setPorteur(true);
              testPorteur = true;
            }
          }
          // bloc mj a droite//
          if (xj == xi + largeurCase && yj == yi) {
            if (mj.getPorteur()) {
              mi.setPorteur(true);
              testPorteur = true;
            }
          }
          // bloc mj a gauche//
          if (xj == xi - largeurCase && yj == yi) {
            if (mj.getPorteur()) {
              mi.setPorteur(true);
              testPorteur = true;
            }
          }
        }
        if (testPorteur == false) {
          mi.setPorteur(false);
        }
      }
    }
  }

  public int estFini() {
    if (coeur1.getPointsDeVie() <= 0) {
      return 1;
    }
    if (coeur2.getPointsDeVie() <= 0) {
      return 2;
    } else {
      return 0;
    }
  }

  // CONSTRUCTION//

  public void creationTextures() {
    int xMinGauche = 0;
    int xMaxGauche = nbColonne * (largeurCase / 4);
    int xMinDroite = (3 * nbColonne / 4) * (largeurCase);
    int xMaxDroite = nbColonne * (largeurCase);
    int yMin = (int) ((nbLigne / 1.5) * hauteurCase);
    int yMax = nbLigne * (hauteurCase);

    case1 = new Case(hauteurFenetre, largeurFenetre, nbColonne, nbLigne, nbColonne / 4, nbLigne / 2, 1);
    case2 = new Case(hauteurFenetre, largeurFenetre, nbColonne, nbLigne, nbColonne * 3 / 4, nbLigne / 2, 2);
    coeur1 = new Coeur((largeurFenetre / nbColonne) * (1 * nbColonne / 8),
        ((int) (nbLigne * (hauteurCase) / 1.5) - hauteurCase), 1);
    coeur2 = new Coeur((largeurFenetre / nbColonne) * (7 * nbColonne / 8),
        ((int) (nbLigne * (hauteurCase) / 1.5) - hauteurCase), 2);
    listeBatiments.add(coeur1);
    listeBatiments.add(coeur2);

    // cases de terre//
    for (int i = 0; i <= xMaxGauche; i = i + largeurCase) {
      for (int j = yMin; j <= yMax; j = j + hauteurCase) {

        if (i >= (xMaxGauche - largeurCase) && j == yMin) {
          listeTextures.add(new Texture(i, j, largeurCase, hauteurCase, 12));// herbe limite droite
        } else if (i >= (xMaxGauche - largeurCase) && j != yMin) {
          listeTextures.add(new Texture(i, j, largeurCase, hauteurCase, 22)); // dirt limite droite
        } else if (j == yMin) {
          listeTextures.add(new Texture(i, j, largeurCase, hauteurCase, 1));// herbe classique
        } else {
          listeTextures.add(new Texture(i, j, largeurCase, hauteurCase, 2));// dirt classique
        }
      }
    }
    for (int i = xMinDroite; i <= xMaxDroite; i = i + largeurCase) {
      for (int j = yMin; j <= yMax; j = j + hauteurCase) {
        if (i == xMinDroite && j == yMin) {
          listeTextures.add(new Texture(i, j, largeurCase, hauteurCase, 11));// herbe limite gauche
        } else if (i == xMinDroite && j != yMin) {
          listeTextures.add(new Texture(i, j, largeurCase, hauteurCase, 21)); // dirt limite gauche
        } else if (j == yMin) {
          listeTextures.add(new Texture(i, j, largeurCase, hauteurCase, 1));// herbe classique
        } else {
          listeTextures.add(new Texture(i, j, largeurCase, hauteurCase, 2));// dirt classique
        }
      }
    }

    // nuages//
    listeNuages.add(new Nuage(100, 50, 650, 250));
    listeNuages.add(new Nuage(400, 260, 650, 250));
    listeNuages.add(new Nuage(700, 150, 650, 250));
    listeNuages.add(new Nuage(1200, 230, 650, 250));
    listeNuages.add(new Nuage(1800, 100, 650, 250));

    // icons//
    try {
      heart = ImageIO.read(new File("./textures/bars/heart.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    ;
    try {
      power = ImageIO.read(new File("./textures/bars/energy.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    ;

  }

  public void creationTouches() {
    try {
      FileInputStream fis = new FileInputStream("myPerson.ser");
      ObjectInputStream ois = new ObjectInputStream(fis);
      Person p1 = (Person) ois.readObject();
      Person p2 = (Person) ois.readObject();
      gauche1 = p1.getGauche();
      droite1 = p1.getDroite();
      haut1 = p1.getHaut();
      bas1 = p1.getBas();
      mur1 = p1.getMur();
      eolienne1 = p1.getEnergie();
      canon1 = p1.getCanon();
      mortier1 = p1.getMortier();

      gauche2 = p2.getGauche();
      droite2 = p2.getDroite();
      haut2 = p2.getHaut();
      bas2 = p2.getBas();
      mur2 = p2.getMur();
      eolienne2 = p2.getEnergie();
      canon2 = p2.getCanon();
      mortier2 = p2.getMortier();

      if (p1.getFond() == 1) {
        try {
          fond = ImageIO.read(new File("./textures/fond1.png"));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (p1.getFond() == 2) {
        try {
          fond = ImageIO.read(new File("./textures/fond2.png"));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (p1.getFond() == 3) {
        try {
          fond = ImageIO.read(new File("./textures/fond4.jpg"));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      ois.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

  public void PlacerCanon(Case c, int type) {

    boolean testCanon = false;
    boolean testRotation = false;
    for (int i = 0; i < listeTotale.size(); i++) {
      for (Batiment b : listeTotale.get(i)) {
        if ((b.getPositionX() == c.getX()) && (b.getPositionY() == c.getY()) && (b.getClass() != Mur.class)) {
          testCanon = true;
        }
      }
    }
    for (int i = 0; i < listeCanons.size(); i++) {
      Canon canon = (Canon) listeCanons.get(i);
      // si le canon est déja placé et tourne, stop le canon//
      if ((canon.getPositionX() == c.getX()) && (canon.getPositionY() == c.getY()) && (canon.getRotation() == true)) {

        testCanon = true;
        if (c.getJoueur() == 1 && energie1 >= 250 && canon.getClass() == Gatling.class) {
          canon.setRotation(false);
          testRotation = true;
          listeProjectiles.add(new Missile(c.getX() + (largeurCase / 2), c.getY() + (hauteurCase / 2), canon.getAngle(),
              (int) canon.getForce(), c.getJoueur()));
          canon.setChargement(true);
          energie1 -= 250;

        }
        if (c.getJoueur() == 2 && energie2 >= 250 && canon.getClass() == Gatling.class) {
          canon.setRotation(false);
          testRotation = true;
          listeProjectiles.add(new Missile(c.getX() + (largeurCase / 2), c.getY() + (hauteurCase / 2), canon.getAngle(),
              (int) canon.getForce(), c.getJoueur()));
          canon.setChargement(true);
          energie2 -= 250;

        }
        if (c.getJoueur() == 1 && energie1 >= 250 && canon.getClass() == Mortier.class) {
          canon.setRotation(false);
          testRotation = true;
          listeProjectiles.add(new Obus(c.getX() + (largeurCase / 2), c.getY() + (hauteurCase / 2), canon.getAngle(),
              (int) canon.getForce(), c.getJoueur()));
          canon.setChargement(true);
          energie1 -= 250;

        }
        if (c.getJoueur() == 2 && energie2 >= 250 && canon.getClass() == Mortier.class) {
          canon.setRotation(false);
          testRotation = true;
          listeProjectiles.add(new Obus(c.getX() + (largeurCase / 2), c.getY() + (hauteurCase / 2), canon.getAngle(),
              (int) canon.getForce(), c.getJoueur()));
          canon.setChargement(true);
          energie2 -= 250;

        }
      }
      // si le canon est déja placé et ne tourne pas, lance la rotation//
      if ((canon.getPositionX() == c.getX()) && (canon.getPositionY() == c.getY()) && (canon.getRotation() == false)
          && testRotation == false) {
        testCanon = true;
        if (canon.getChargement() == false) {
          canon.setRotation(true);
        }
      }
    }
    // si pas de canon aux mêmes coordonnes, place un canon//
    for (int i = 0; i < listeMurs.size(); i++) {
      if (testCanon == false && (listeMurs.get(i).getPositionX() == c.getX())
          && (listeMurs.get(i).getPositionY() == c.getY())) {

        if (c.getJoueur() == 1 && type == 1 && energie1 > 2000) {
          listeCanons.add(new Gatling(c.getX(), c.getY(), c.getJoueur()));
          energie1 = energie1 - 2000;
          try {
            listeMurs.get(i).setTexture(ImageIO.read(new File("./textures/mur2.png")));
          } catch (IOException e) {
            e.printStackTrace();
          }
          ;
        }
        if (c.getJoueur() == 2 && type == 1 && energie2 > 2000) {
          listeCanons.add(new Gatling(c.getX(), c.getY(), c.getJoueur()));
          energie2 = energie2 - 2000;
          try {
            listeMurs.get(i).setTexture(ImageIO.read(new File("./textures/mur2.png")));
          } catch (IOException e) {
            e.printStackTrace();
          }
          ;
        }
        if (c.getJoueur() == 2 && type == 2 && energie2 > 2000) {
          listeCanons.add(new Mortier(c.getX(), c.getY(), c.getJoueur()));
          energie2 = energie2 - 2000;
          try {
            listeMurs.get(i).setTexture(ImageIO.read(new File("./textures/mur2.png")));
          } catch (IOException e) {
            e.printStackTrace();
          }
          ;
        }

        if (c.getJoueur() == 1 && type == 2 && energie1 > 2000) {
          listeCanons.add(new Mortier(c.getX(), c.getY(), c.getJoueur()));
          energie1 = energie1 - 2000;
          try {
            listeMurs.get(i).setTexture(ImageIO.read(new File("./textures/mur2.png")));
          } catch (IOException e) {
            e.printStackTrace();
          }
          ;
        }
      }
    }
  }

  public void PlacerMur(Case c) {

    boolean murAdj = false;
    boolean murHere = false;
    Mur ceMur = new Mur(c.getX(), c.getY(), c.getJoueur());

    for (int i = 0; i < listeMurs.size(); i++) {
      if ((listeMurs.get(i).getPositionX() == c.getX()) && (listeMurs.get(i).getPositionY() == c.getY())) {
        System.out.println("il y a deja un mur");
        murHere = true;
      }
    }

    if (murHere == false) {
      for (int i = 0; i < listeMurs.size(); i++) {
        if ((listeMurs.get(i).getPositionX() == c.getX() - largeurCase)
            && (listeMurs.get(i).getPositionY() == c.getY())) {
          murAdj = true;
        }
        if ((listeMurs.get(i).getPositionX() == c.getX() + largeurCase)
            && (listeMurs.get(i).getPositionY() == c.getY())) {
          murAdj = true;
        }
        if ((listeMurs.get(i).getPositionX() == c.getX())
            && (listeMurs.get(i).getPositionY() == c.getY() - hauteurCase)) {
          murAdj = true;
        }
        if ((listeMurs.get(i).getPositionX() == c.getX())
            && (listeMurs.get(i).getPositionY() == c.getY() + hauteurCase)) {
          murAdj = true;
        }

      }
      if (c.getJoueur() == 1 && c.getY() == ((int) (nbLigne * (hauteurCase) / 1.5) - hauteurCase)
          && c.getX() <= nbColonne * (largeurCase / 4) + largeurCase) {
        murAdj = true;
      }
      if (c.getJoueur() == 2 && c.getY() == ((int) (nbLigne * (hauteurCase) / 1.5) - hauteurCase)
          && c.getX() >= nbColonne * (3 * largeurCase / 4) - largeurCase) {
        murAdj = true;
      }

    }
    if (c.getJoueur() == 1) {
      if (murAdj == true && energie1 > 750) {
        listeMurs.add(ceMur);
        energie1 = energie1 - 750;
      }
    }
    if (c.getJoueur() == 2) {
      if (murAdj == true && energie2 > 750) {
        listeMurs.add(ceMur);
        energie2 = energie2 - 750;
      }
    }
  }

  public void PlacerEolienne(Case c) {
    boolean testEolienne = false;
    boolean testMine = false;
    for (int i = 0; i < listeTotale.size(); i++) {
      for (Batiment b : listeTotale.get(i)) {
        // Si il y a deja une eolienne, ne fait rien//
        if ((b.getPositionX() == c.getX()) && (b.getPositionY() == c.getY()) && b.getClass() != Mur.class) {
          testEolienne = true;
        }
      }
    }
    for (int i = 0; i < listeMines.size(); i++) {
      if ((listeMines.get(i).getPositionX() == c.getX()) && (listeMines.get(i).getPositionY() == c.getY())) {
        testMine = true;
      }
    }
    // si texture ressource, place une mine//
    for (int i = 0; i < listeTextures.size(); i++) {
      if (c.getJoueur() == 1 && testEolienne == false) {
        if (energie1 > 1000 && testMine == false && listeTextures.get(i).getRessource() == true
            && listeTextures.get(i).getPositionX() == c.getX() && listeTextures.get(i).getPositionY() == c.getY()) {
          listeMines.add(new Mine(c.getX(), c.getY(), c.getJoueur()));
          energie1 -= 1000;
        }
      }
      if (c.getJoueur() == 2 && testEolienne == false) {
        if (energie2 > 1000 && testMine == false && listeTextures.get(i).getRessource() == true
            && listeTextures.get(i).getPositionX() == c.getX() && listeTextures.get(i).getPositionY() == c.getY()) {
          listeMines.add(new Mine(c.getX(), c.getY(), c.getJoueur()));
          energie2 -= 1000;
        }
      }
    }

    // si pas d'eolienne aux mêmes coordonnes, place une eolienne si un mur est en
    // dessous//
    for (int i = 0; i < listeMurs.size(); i++) {
      if (c.getJoueur() == 1 && testMine == false) {
        if (energie1 > 1000 && testEolienne == false && (listeMurs.get(i).getPositionX() == c.getX())
            && (listeMurs.get(i).getPositionY() == (c.getY() + hauteurCase))) {
          System.out.println("ajout de l'eolienne");
          listeEoliennes.add(new Eolienne(c.getX(), c.getY(), c.getJoueur()));
          energie1 = energie1 - 1000;
        }
      }
      if (c.getJoueur() == 2 && testMine == false) {
        if (energie2 > 1000 && testEolienne == false && (listeMurs.get(i).getPositionX() == c.getX())
            && (listeMurs.get(i).getPositionY() == (c.getY() + hauteurCase))) {
          System.out.println("ajout de l'eolienne");
          listeEoliennes.add(new Eolienne(c.getX(), c.getY(), c.getJoueur()));
          energie2 = energie2 - 1000;
        }
      }
    }
  }

  public void actionProjectile(int positionP) {
    listeProjectiles.get(positionP).deplacement(vVent);
    boolean touche = false;

    for (int i = 0; i < listeBatiments.size(); i++) {
      if (listeBatiments.get(i).Collisions(listeProjectiles.get(positionP), largeurCase, hauteurCase)) {
        touche = true;
        if (listeBatiments.get(i).getPointsDeVie() <= 0) {
          listeBatiments.remove(i);
        }
      }
    }
    for (int i = 0; i < listeCanons.size(); i++) {
      Canon canon = (Canon) listeCanons.get(i);
      if (canon.Collisions(listeProjectiles.get(positionP), largeurCase, hauteurCase)) {
        touche = true;
        if (canon.getPointsDeVie() <= 0) {
          listeCanons.remove(i);
        }
      }
    }
    for (int i = 0; i < listeEoliennes.size(); i++) {
      if (listeEoliennes.get(i).Collisions(listeProjectiles.get(positionP), largeurCase, hauteurCase)) {
        touche = true;
        if (listeEoliennes.get(i).getPointsDeVie() <= 0) {
          listeEoliennes.remove(i);
        }
      }
    }
    for (int i = 0; i < listeMurs.size(); i++) {
      if (listeMurs.get(i).Collisions(listeProjectiles.get(positionP), largeurCase, hauteurCase)) {
        touche = true;
        if (listeMurs.get(i).getPointsDeVie() <= 0) {
          listeMurs.remove(i);
        }
      }
    }
    if (touche == true) {
      listeProjectiles.remove(positionP);
    }
  }

  // KEYEVENT//
  @Override
  public void keyReleased(KeyEvent event) {

  }

  @Override
  public void keyTyped(KeyEvent event) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int touche = e.getKeyCode();
    int x1 = case1.getX();
    int x2 = case2.getX();
    int y1 = case1.getY();
    int y2 = case2.getY();

    if (touche == haut1) { // haut joueur1
      case1.deplacement(0, -1);
    }
    if (touche == haut2) {// haut joueur2
      case2.deplacement(0, -1);
    }
    if (touche == droite2) {// droite J2
      case2.deplacement(1, 0);
    }
    if (touche == droite1) { // droite j1
      case1.deplacement(1, 0);
    }
    if (touche == gauche2) { // gauche j2
      case2.deplacement(-1, 0);
    }
    if (touche == gauche1) { // gauche j1
      case1.deplacement(-1, 0);
    }
    if (touche == bas2) { // bas j2
      case2.deplacement(0, 1);
    }
    if (touche == bas1) { // bas j1
      case1.deplacement(0, 1);
    }

    if (touche == canon2) { // Canon j2
      PlacerCanon(case2, 1);
    }
    if (touche == mortier2) { // mortier j2
      PlacerCanon(case2, 2);
    }
    if (touche == canon1) { // canon j1
      PlacerCanon(case1, 1);
    }
    if (touche == mortier1) { // mortier j1
      PlacerCanon(case1, 2);
    }

    if (touche == mur1) { // mur j1
      PlacerMur(case1);
    }
    if (touche == mur2) { // mur j2
      PlacerMur(case2);
    }
    if (touche == eolienne2) { // Energie j2
      PlacerEolienne(case2);
    }
    if (touche == eolienne1) { // energie j1
      PlacerEolienne(case1);
    }

    repaint(case1.getX() - 10, case1.getY() - 10, largeurCase + 20, hauteurCase + 20);
    repaint(case2.getX() - 10, case2.getY() - 10, largeurCase + 20, hauteurCase + 20);
    repaint(x1 - 10, y1 - 10, largeurCase + 20, hauteurCase + 20);
    repaint(x2 - 10, y2 - 10, largeurCase + 20, hauteurCase + 20);
  }

}
