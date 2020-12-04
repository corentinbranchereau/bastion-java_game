import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit; //rajout a partir de la
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

public class MenuFin extends JFrame implements ActionListener{
	public int hauteurFenetre;
	public int largeurFenetre;
	public JButton monBouton1;
	public JButton monBouton2;
	public JButton monBouton3;
	public JPanel monConteneur;
	public Jeu J;
	public ReglesJeu R;
	public Parametre P;
	public JFrame maFenetre;
	public Image fond;
	public Image IFB;
	public Graphics g;
	public JLabel m;
	public JLabel l;
	public ImageIcon image1;



	public MenuFin(int j){
		//récupération des dimension de l'ecran//
	hauteurFenetre=800;
	largeurFenetre=800;

		try {  fond = ImageIO.read(new File("./textures/fond1.png"));  }
		catch (IOException e) {  e.printStackTrace();  }
		image1 = new ImageIcon("./textures/Background/button.png");



		maFenetre = new JFrame();
		maFenetre.setSize(largeurFenetre, hauteurFenetre);
		maFenetre.setLocation(600,100);


		JLabel monEtiquette = new JLabel();
		Font police = new Font("Calibri",Font.BOLD,40);
		monEtiquette.setFont(police);
		monEtiquette.setSize(300,100);
		monEtiquette.setLocation((largeurFenetre/2)-100,hauteurFenetre/10) ;

		JLabel monEtiquette2 = new JLabel("gagne la partie!");
		monEtiquette2.setFont(police);
		monEtiquette2.setSize(300,100);
		monEtiquette2.setLocation((largeurFenetre/2)-120,hauteurFenetre/10+50) ;


		if(j==1){
			monEtiquette.setText("Le Joueur 1");
		}
		if (j==2) {
			monEtiquette.setText("Le Joueur 2");
		}

		m = new JLabel(new ImageIcon(fond));
		m.setBounds(0,0,largeurFenetre,hauteurFenetre);

		Font police2 = new Font(" Calibri ",Font.BOLD,35);
		Color couleur=new Color(100,0,40);

		monBouton3 = new JButton("Retour au menu",image1);
		monBouton3.setFont(police2);
		monBouton3.setBackground(couleur);
		monBouton3.setForeground(Color.white);
		monBouton3.setBounds((largeurFenetre/2)-150,4*hauteurFenetre/6,300,100);
		monBouton3.setVerticalTextPosition(JButton.CENTER);
		monBouton3.setHorizontalTextPosition(JButton.CENTER);
		monBouton3.setVisible(true) ;
		monBouton3.addActionListener(this);

		monConteneur = new JPanel() ;
		monConteneur.setLayout(null) ;
		monConteneur.add(monEtiquette) ;
		monConteneur.add(monEtiquette2) ;
		monConteneur.setBackground(Color.blue);
		monConteneur.add(monBouton3) ;
		monConteneur.setBounds(0,0,largeurFenetre,hauteurFenetre);
		monConteneur.add(m);
		maFenetre.add(monConteneur) ;


		maFenetre.setVisible(true) ;
		repaint();
	}


		public void actionPerformed(ActionEvent e){


			if  (e.getSource() == monBouton3){
					Menu M= new Menu();
					maFenetre.setVisible(false) ;
					System.out.println("Menu");
			}
		}

}
