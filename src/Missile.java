public class Missile extends Projectile {

public Missile(int x, int y, int angle,int force, int j) {
  super(x,y,angle,force,j);
  taille=12;
  masse=100;
  constante=0.000026;
  double a=0;
	if(joueur==1){
	 a=Math.toRadians(angle);
	}
	if(joueur==2){
	a=Math.toRadians(angle+180);
	}
	this.vx=force*Math.cos(a);
	this.vy=force*Math.sin(a);
}


public void deplacement(double vVent){

	if(joueur==1){
		vx = vx - constante*Math.pow(vx-vVent,2) ;
		vy = vy*(1 + vy*constante)+ 9.81/60 ;
		x = x + (int)(vx) ;
		y = y + (int)(vy) ;
	}
	if(joueur==2){
		vx = vx- constante*Math.pow(vx-vVent,2) ;
		vy = vy*(1 + vy*constante)+ 9.81/60 ;
		x = x + (int)(vx) ;
		y = y + (int)(vy) ;
	}
}


}
