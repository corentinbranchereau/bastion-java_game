public class Obus extends Projectile {

  public Obus(int x, int y,int angle, int force, int j) {
    super(x,y,angle,force,j);
    taille=20;
    masse=300;
    constante=0.000026;
    double a=0;
    if(joueur==1){
     a=Math.toRadians(angle+60);
    }
    if(joueur==2){
    a=Math.toRadians(angle);
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
