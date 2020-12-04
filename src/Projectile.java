public abstract class Projectile {
	protected  int taille;
	protected final int joueur;
	protected int x;
	protected int y;
	protected  double constante;
	protected double masse;
	protected double vx, vy;
	protected double force;

public Projectile(int x, int y, int angle,int force, int j) {
	this.x = x;
	this.y=y;
	this.joueur = j;
	this.force=force;
}

public double getMasse() {return masse;}
public int getTaille() {return taille;}
public int getJoueur() {return this.joueur;}
public int getPositionX() {return this.x;}
public int getPositionY() {return this.y;}
public double getVx(){return vx;}
public double getVy(){return vy;}

public abstract void deplacement(double vVent);



}
