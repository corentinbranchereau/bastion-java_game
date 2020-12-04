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

public class Menu extends JFrame implements ActionListener{
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



	public Menu(){
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

		try {  fond = ImageIO.read(new File("./textures/Background/background.png"));  }
		catch (IOException e) {  e.printStackTrace();  }
		image1 = new ImageIcon("./textures/Background/button.png");



		maFenetre = new JFrame();
		maFenetre.setSize(largeurFenetre, hauteurFenetre);
		maFenetre.setLocation(0,0);


		JLabel monEtiquette = new JLabel();
		Font police = new Font(" Arial ",Font.BOLD,60);
		monEtiquette.setFont(police);
		//monEtiquette.setText("Bastion");
		monEtiquette.setSize(300,100);
		monEtiquette.setLocation((largeurFenetre/2)-120,hauteurFenetre/10) ;

		m = new JLabel(new ImageIcon(fond));
		m.setBounds(0,0,largeurFenetre,hauteurFenetre);

		Font police2 = new Font(" Calibri ",Font.BOLD,35);
		Color couleur=new Color(100,0,40);

		monBouton1 = new JButton("jouer",image1);
		monBouton1.setBounds((largeurFenetre/2)-150,2*hauteurFenetre/6,300,100);
		monBouton1.setFont(police2);
		monBouton1.setBackground(couleur);
		monBouton1.setForeground(Color.white);
		monBouton1.setVerticalTextPosition(JButton.CENTER);
    monBouton1.setHorizontalTextPosition(JButton.CENTER);
		monBouton1.setVisible(true) ;
		monBouton1.addActionListener(this);





		monBouton2 = new JButton("Regle du jeu",image1);
		monBouton2.setFont(police2);
		monBouton2.setBackground(couleur);
		monBouton2.setForeground(Color.white);
		monBouton2.setBounds((largeurFenetre/2)-150,3*hauteurFenetre/6,300,100);
		monBouton2.setVerticalTextPosition(JButton.CENTER);
		monBouton2.setHorizontalTextPosition(JButton.CENTER);
		monBouton2.setVisible(true) ;
		monBouton2.addActionListener(this);


		monBouton3 = new JButton("Parametres",image1);
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
		monConteneur.add(monBouton1) ;
		monConteneur.add(monEtiquette) ;
		monConteneur.add(monBouton2) ;
		monConteneur.setBackground(Color.blue);
		monConteneur.add(monBouton3) ;
		monConteneur.setBounds(0,0,largeurFenetre,hauteurFenetre);
		monConteneur.add(m);
		maFenetre.add(monConteneur) ;


		maFenetre.setVisible(true) ;
		repaint();
	}

		public void paint(Graphics g){
					 System.out.println("salut");
		this.g.drawImage(IFB, (largeurFenetre/2)-150,2*hauteurFenetre/6,300,100,this);
			this.g.fillOval(50,50,400,200) ;
		 System.out.println("salut");
	 }


		public void actionPerformed(ActionEvent e){
			if  (e.getSource() == monBouton1){
				Jeu J=new Jeu(15,27);
				maFenetre.setVisible(false) ;
			}
			if  (e.getSource() == monBouton2){
				ReglesJeu R= new ReglesJeu();
				maFenetre.setVisible(false) ;
				System.out.println("regle");



			}
			if  (e.getSource() == monBouton3){
					Parametre P= new Parametre();
					maFenetre.setVisible(false) ;
					System.out.println("parametre");
			}
		}

}
