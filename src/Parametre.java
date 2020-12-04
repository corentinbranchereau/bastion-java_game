import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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

public class Parametre extends JFrame implements ActionListener{
  public int hauteurFenetre;
	public int largeurFenetre;
	public Menu M;
	public JFrame FenetreParametre;
	public JButton retour;
	public JButton decor1;
	public JButton decor2;
	public JButton decor3;
	public JLabel m;
	public Image fond;
	public JLabel c;
	public JTextField haut;
	public ImageIcon image1;
	public JButton ordi;
	public JButton majOrdi;

  public Parametre(){
    //récupération des dimension de l'ecran//
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        if((dimension.getHeight()-20)*1.8<dimension.getWidth()){
          hauteurFenetre = (int)dimension.getHeight()-20;
          largeurFenetre = (int)((dimension.getHeight()-20)*1.8);
        }
        else{
          largeurFenetre = (int)dimension.getWidth();
          hauteurFenetre = (int)(dimension.getWidth()*(0.5555));
        }
        try {  fond = ImageIO.read(new File("./textures/fond1.png"));  }
		catch (IOException e) {  e.printStackTrace();  }
		image1 = new ImageIcon("./textures/Background/button.png");

		Font police2 = new Font(" Calibri ",Font.BOLD,35);
        Font police = new Font(" Arial ",Font.BOLD,60);
		Font policeBis = new Font(" Arial ",Font.BOLD,30);


      //  System.out.println("+++"+hauteurFenetre+"   "+largeurFenetre+"+++");
	FenetreParametre = new JFrame();
    FenetreParametre.setSize(largeurFenetre, hauteurFenetre);
    FenetreParametre.setLocation(0,0);


    JLabel monEtiquette = new JLabel("Parametre");                      //Creation label parametre

	monEtiquette.setFont(police);
    monEtiquette.setBounds((1*largeurFenetre/2)-100,0,300,100);

    JLabel controle1 = new JLabel("Controles joueur 1");                   //Label Controle 1 et 2

	controle1.setFont(policeBis);
    controle1.setBounds((1*largeurFenetre/10),3*hauteurFenetre/5,300,100);

     JLabel controle2 = new JLabel("Controles joueur 2");
	controle2.setFont(policeBis);
    controle2.setBounds((2*largeurFenetre/3)-50,3*hauteurFenetre/5,300,100);


        m= new JLabel(new ImageIcon(fond));
		m.setBounds(0,0,largeurFenetre,hauteurFenetre);

    	retour = new JButton("Retour",image1);
		retour.setBounds((1*largeurFenetre/20),1*hauteurFenetre/20,150,60);
		retour.setForeground(Color.white);
		 retour.setFont(police2);
		retour.setVerticalTextPosition(JButton.CENTER);
		retour.setHorizontalTextPosition(JButton.CENTER);
		retour.setVisible(true) ;
		retour.addActionListener(this);


		decor1 = new JButton("Decor 1",image1);
		decor1.setBounds((1*largeurFenetre/10),1*hauteurFenetre/5,300,100);
		decor1.setForeground(Color.white);
		decor1.setFont(police2);
		decor1.setVerticalTextPosition(JButton.CENTER);
		decor1.setHorizontalTextPosition(JButton.CENTER); 			//DECOR 1
		decor1.setVisible(true) ;
		decor1.addActionListener(this);

		decor2 = new JButton("Decor 2",image1);
		decor2.setBounds((4*largeurFenetre/10),1*hauteurFenetre/5,300,100);
		decor2.setForeground(Color.white);
	    decor2.setFont(police2);
		decor2.setVerticalTextPosition(JButton.CENTER);
		decor2.setHorizontalTextPosition(JButton.CENTER);			//DECOR 2
		decor2.setVisible(true) ;
		decor2.addActionListener(this);

		decor3 = new JButton("Decor 3",image1);
		decor3.setBounds((7*largeurFenetre/10),1*hauteurFenetre/5,300,100);		//DECOR 3
		decor3.setForeground(Color.white);
		decor3.setFont(police2);
		decor3.setVerticalTextPosition(JButton.CENTER);
		decor3.setHorizontalTextPosition(JButton.CENTER);
		decor3.setVisible(true) ;
		decor3.addActionListener(this);

		ordi = new JButton("Jouer contre ordi",image1);
		ordi.setBounds((2*largeurFenetre/10)+50,2*hauteurFenetre/5+50,300,60);		//DECOR 3
		ordi.setForeground(Color.white);
		ordi.setFont(policeBis);
		ordi.setVerticalTextPosition(JButton.CENTER);
		ordi.setHorizontalTextPosition(JButton.CENTER);
		ordi.setVisible(true) ;
		ordi.addActionListener(this);

        majOrdi = new JButton("Maj ordi",image1);
		majOrdi.setBounds((6*largeurFenetre/10)-50,2*hauteurFenetre/5+50,300,60);		//DECOR 3
		majOrdi.setForeground(Color.white);
		majOrdi.setFont(policeBis);
		majOrdi.setVerticalTextPosition(JButton.CENTER);
		majOrdi.setHorizontalTextPosition(JButton.CENTER);
		majOrdi.setVisible(true) ;
		majOrdi.addActionListener(this);


	JTextField haut=new JTextField();					//FLECHE HAUT
	  haut.setBounds((1*largeurFenetre/25)+80,9*hauteurFenetre/12,30,30);
	  haut.setVisible(true) ;

	  JLabel b=new JLabel();
	  b.setText("Fleche haut");
	  b.setBounds((1*largeurFenetre/25),9*hauteurFenetre/12,80,30);
	  b.setVisible(true) ;

	  JTextField bas=new JTextField();					//FLECHE BAS
	  bas.setBounds((1*largeurFenetre/8)+90,9*hauteurFenetre/12,30,30);
	  bas.setVisible(true) ;

	  JLabel c=new JLabel();
	  c.setText("Fleche bas");
	  c.setBounds((1*largeurFenetre/8)+20,9*hauteurFenetre/12,80,30);
	  c.setVisible(true) ;

	  JTextField droite=new JTextField();					//FLECHE DROITE
	  droite.setBounds((2*largeurFenetre/10)+130,9*hauteurFenetre/12,30,30);
	  droite.setVisible(true) ;

	  JLabel d=new JLabel();
	  d.setText("fleche droite");
	  d.setBounds((2*largeurFenetre/10)+50,9*hauteurFenetre/12,80,30);
	  d.setVisible(true) ;

	  JTextField gauche=new JTextField();					//FLECHE GAUCHE
	  gauche.setBounds((1*largeurFenetre/25)+80,5*hauteurFenetre/6,30,30);
	  gauche.setVisible(true) ;

	  JLabel e=new JLabel();
	  e.setText("fleche gauche");
	  e.setBounds((1*largeurFenetre/30),5*hauteurFenetre/6,80,30);
	  e.setVisible(true) ;

	  JTextField canon=new JTextField();					//canon
	  canon.setBounds((1*largeurFenetre/8)+90,5*hauteurFenetre/6,30,30);
	  canon.setVisible(true) ;

	  JLabel f=new JLabel();
	  f.setText("canon");
	  f.setBounds((1*largeurFenetre/8)+40,5*hauteurFenetre/6,80,30);
	  f.setVisible(true) ;

	  JTextField mur=new JTextField();					//mur
	  mur.setBounds((2*largeurFenetre/10)+130,5*hauteurFenetre/6,30,30);
	  mur.setVisible(true) ;

	  JLabel g=new JLabel();
	  g.setText("mur");
	  g.setBounds((2*largeurFenetre/10)+100,5*hauteurFenetre/6,80,30);
	  g.setVisible(true) ;


		 JTextField mortier=new JTextField();					//canon
	  mortier.setBounds((3*largeurFenetre/10)+130,5*hauteurFenetre/6,30,30);
	  mortier.setVisible(true) ;

	  JLabel h=new JLabel();
	  h.setText("mortier");
	  h.setBounds((3*largeurFenetre/10)+80,5*hauteurFenetre/6,80,30);
	  h.setVisible(true) ;

	   JTextField eolienne=new JTextField();					//canon
	  eolienne.setBounds((3*largeurFenetre/10)+130,9*hauteurFenetre/12,30,30);
	  eolienne.setVisible(true) ;

	  JLabel i=new JLabel();
	  i.setText("eolienne");
	  i.setBounds((3*largeurFenetre/10)+80,9*hauteurFenetre/12,80,30);
	  i.setVisible(true) ;

	  //Joueur 2


	JTextField haut2=new JTextField();					//FLECHE HAUT 2
	  haut2.setBounds((13*largeurFenetre/25)+80,9*hauteurFenetre/12,30,30);
	  haut2.setVisible(true) ;

	  JLabel b2=new JLabel();
	  b2.setText("Fleche haut");
	  b2.setBounds((13*largeurFenetre/25),9*hauteurFenetre/12,80,30);
	  b2.setVisible(true) ;

	  JTextField bas2=new JTextField();					//FLECHE BAS
	  bas2.setBounds((5*largeurFenetre/8)+90,9*hauteurFenetre/12,30,30);
	  bas2.setVisible(true) ;

	  JLabel c2=new JLabel();
	  c2.setText("Fleche bas");
	  c2.setBounds((5*largeurFenetre/8)+20,9*hauteurFenetre/12,80,30);
	  c2.setVisible(true);

	  JTextField droite2=new JTextField();					//FLECHE DROITE
	  droite2.setBounds((7*largeurFenetre/10)+130,9*hauteurFenetre/12,30,30);
	  droite2.setVisible(true) ;

	  JLabel d2=new JLabel();
	  d2.setText("fleche droite");
	  d2.setBounds((7*largeurFenetre/10)+50,9*hauteurFenetre/12,80,30);
	  d2.setVisible(true) ;

	  JTextField gauche2=new JTextField();					//FLECHE GAUCHE
	  gauche2.setBounds((13*largeurFenetre/25)+80,5*hauteurFenetre/6,30,30);
	  gauche2.setVisible(true) ;

	  JLabel e2=new JLabel();
	  e2.setText("fleche gauche");
	  e2.setBounds((15*largeurFenetre/30)+10,5*hauteurFenetre/6,80,30);
	  e2.setVisible(true) ;

	  JTextField canon2=new JTextField();					//canon
	  canon2.setBounds((5*largeurFenetre/8)+90,5*hauteurFenetre/6,30,30);
	  canon2.setVisible(true) ;

	  JLabel f2=new JLabel();
	  f2.setText("canon");
	  f2.setBounds((5*largeurFenetre/8)+40,5*hauteurFenetre/6,80,30);
	  f2.setVisible(true) ;

	  JTextField mur2=new JTextField();					//mur
	  mur2.setBounds((7*largeurFenetre/10)+130,5*hauteurFenetre/6,30,30);
	  mur2.setVisible(true) ;

	  JLabel g2=new JLabel();
	  g2.setText("mur");
	  g2.setBounds((7*largeurFenetre/10)+100,5*hauteurFenetre/6,80,30);
	  g2.setVisible(true) ;

	   JTextField mortier2=new JTextField();					//cmortier
	  mortier2.setBounds((8*largeurFenetre/10)+130,5*hauteurFenetre/6,30,30);
	  mortier2.setVisible(true) ;

	  JLabel h2=new JLabel();
	  h2.setText("mortier");
	  h2.setBounds((8*largeurFenetre/10)+80,5*hauteurFenetre/6,80,30);
	  h2.setVisible(true) ;

	   JTextField eolienne2=new JTextField();					//ceolienne
	  eolienne2.setBounds((8*largeurFenetre/10)+130,9*hauteurFenetre/12,30,30);
	  eolienne2.setVisible(true) ;

	  JLabel i2=new JLabel();
	  i2.setText("eolienne");
	  i2.setBounds((8*largeurFenetre/10)+80,9*hauteurFenetre/12,80,30);
	  i2.setVisible(true) ;



    JPanel monConteneur = new JPanel() ;
    monConteneur.setLayout(null) ;
    monConteneur.add(monEtiquette) ;
    monConteneur.add(controle1) ;
    monConteneur.add(controle2) ;
    monConteneur.add(retour);
    monConteneur.add(decor1);
    monConteneur.add(decor2);
    monConteneur.add(decor3);
    monConteneur.setBounds(0,0,largeurFenetre,hauteurFenetre);
    monConteneur.add(haut);
    monConteneur.add(bas);
    monConteneur.add(droite);
    monConteneur.add(gauche);
    monConteneur.add(canon);
    monConteneur.add(mur);
    monConteneur.add(mortier);
    monConteneur.add(eolienne);
    monConteneur.add(d);
    monConteneur.add(b);
    monConteneur.add(e);
    monConteneur.add(f);
    monConteneur.add(g);
    monConteneur.add(c);
    monConteneur.add(haut2);
    monConteneur.add(bas2);
    monConteneur.add(droite2);
    monConteneur.add(gauche2);
    monConteneur.add(canon2);
    monConteneur.add(mur2);
    monConteneur.add(mortier2);
    monConteneur.add(eolienne2);
    monConteneur.add(h);
    monConteneur.add(i);
    monConteneur.add(b2);
    monConteneur.add(d2);
    monConteneur.add(ordi);
    monConteneur.add(majOrdi);
    monConteneur.add(e2);
    monConteneur.add(f2);
    monConteneur.add(g2);
    monConteneur.add(c2);
    monConteneur.add(h2);
    monConteneur.add(i2);

    monConteneur.add(m);




   FenetreParametre.add(monConteneur) ;

   FenetreParametre.setVisible(true);

}
 public void actionPerformed(ActionEvent e){
			if  (e.getSource() == retour){
				FenetreParametre.setVisible(false);
				Menu M=new Menu();
			}

  }

 }
