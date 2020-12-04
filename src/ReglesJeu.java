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

public class ReglesJeu extends JFrame implements ActionListener{
  public int hauteurFenetre;
	public int largeurFenetre;
	public Menu M;
	public JFrame FenetreRegle;
	public JButton retour;
	public JLabel m;
	public Image fond;
	public Image parchemin;

  public ReglesJeu(){
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

		 try {  parchemin = ImageIO.read(new File("./textures/scroll.png"));  }
		catch (IOException e) {  e.printStackTrace();  }

        System.out.println("+++"+hauteurFenetre+"   "+largeurFenetre+"+++");


   FenetreRegle = new JFrame();
   FenetreRegle.setSize(largeurFenetre, hauteurFenetre);
    FenetreRegle.setLocation(0,0);


    ImageIcon image1 = new ImageIcon("./textures/Background/button.png");
      ImageIcon image2 = new ImageIcon("./textures/scroll.png");


    JLabel monEtiquette = new JLabel("<html>---------------------------------------------------Bienvenue dans BASTION,--------------------------------------<br><br>Le principe: oeil pour oeil, dent pour dent! En effet, l'objectif ici est de rendre coup pour coup afin de triompher de l'ennemi.<br>Chacun des joueurs possede un reacteur a proteger et la partie se finira lorsque celui d'un des 2 joueurs sera detruit.<br>Pour cela vous pouvez construire des murs pour le proteger ou des canons pour au contraire detruire celui de l'adversaire.<br>Pour realiser toutes ces actions, vous aurez besoin d'energie, que ce soit pour construire un mur, un canon ou encore tirer.<br>Cette energie augmente automatiquement durant la partie, mais il est possible d'augmenter sa production en construisant des eoliennes ou des mines qu'il faut placer sur les extraits de minerai.<br>C'est en gerant strategiquement son energie, et en variant entre defense , attaque et production d'energie que vous pourrez triompher de votre adversaire.<br>Au final il n'en restera qu'un, que le sort vous soit favorable!</html>");
    monEtiquette.setBounds(largeurFenetre/5, hauteurFenetre/10,5*largeurFenetre/10, 7*hauteurFenetre/10);
    monEtiquette.setVisible(true);



    Font police2 = new Font(" Calibri ",Font.BOLD,35);

    retour = new JButton("retour",image1);
	retour.setBounds((1*largeurFenetre/20),1*hauteurFenetre/20,150,60);
	retour.setVisible(true) ;
    retour.setFont(police2);
    retour.setForeground(Color.white);
	retour.addActionListener(this);
    retour.setVerticalTextPosition(JButton.CENTER);
    retour.setHorizontalTextPosition(JButton.CENTER);

		m = new JLabel(new ImageIcon(fond));
		m.setBounds(0,0,largeurFenetre,hauteurFenetre);

    JPanel monConteneur = new JPanel() ;
    monConteneur.setBounds(0,0,largeurFenetre,hauteurFenetre);
    monConteneur.setLayout(null) ;
      monConteneur.add(monEtiquette) ;
    monConteneur.add(m);
    monConteneur.add(retour);

    monConteneur.setVisible(true);


   FenetreRegle.add(monConteneur) ;
   monConteneur.repaint();

   FenetreRegle.setVisible(true);
   FenetreRegle.repaint();
}

    public void actionPerformed(ActionEvent e){
			if  (e.getSource() == retour){
				FenetreRegle.setVisible(false);
				Menu M=new Menu();


  }
}
}
