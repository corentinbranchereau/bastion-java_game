import java.io.Serializable;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	public char gauche,droite,haut,bas,mur,energie,canon,mortier;
	public int fond;

	Person() {
	};

	Person(int f,char g,char d,char h,char b,char m,char e,char c1,char c2) {
		fond=f;
		gauche=g;
    droite=d;
    haut=h;
    bas=b;
    mur=m;
    energie=e;
    canon=c1;
    mortier=c2;

	}


	/**
	* Returns value of gauche
	* @return
	*/
	public char getGauche() {
		return gauche;
	}

	/**
	* Sets new value of gauche
	* @param
	*/
	public void setGauche(char gauche) {
		this.gauche = gauche;
	}

	/**
	* Returns value of droite
	* @return
	*/
	public char getDroite() {
		return droite;
	}

	/**
	* Sets new value of droite
	* @param
	*/
	public void setDroite(char droite) {
		this.droite = droite;
	}

	/**
	* Returns value of haut
	* @return
	*/
	public char getHaut() {
		return haut;
	}

	/**
	* Sets new value of haut
	* @param
	*/
	public void setHaut(char haut) {
		this.haut = haut;
	}

	/**
	* Returns value of bas
	* @return
	*/
	public char getBas() {
		return bas;
	}

	/**
	* Sets new value of bas
	* @param
	*/
	public void setBas(char bas) {
		this.bas = bas;
	}

	/**
	* Returns value of mur
	* @return
	*/
	public char getMur() {
		return mur;
	}

	/**
	* Sets new value of mur
	* @param
	*/
	public void setMur(char mur) {
		this.mur = mur;
	}

	/**
	* Returns value of energie
	* @return
	*/
	public char getEnergie() {
		return energie;
	}

	/**
	* Sets new value of energie
	* @param
	*/
	public void setEnergie(char energie) {
		this.energie = energie;
	}

	/**
	* Returns value of canon
	* @return
	*/
	public char getCanon() {
		return canon;
	}

	/**
	* Sets new value of canon
	* @param
	*/
	public void setCanon(char canon) {
		this.canon = canon;
	}

	/**
	* Returns value of mortier
	* @return
	*/
	public char getMortier() {
		return mortier;
	}

	/**
	* Sets new value of mortier
	* @param
	*/
	public void setMortier(char mortier) {
		this.mortier = mortier;
	}
	public int getFond(){
		return fond;
	}

	/**
	* Create string representation of Person for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Person [gauche=" + gauche + ", droite=" + droite + ", haut=" + haut + ", bas=" + bas + ", mur=" + mur + ", energie=" + energie + ", canon=" + canon + ", mortier=" + mortier + "]";
	}
}
