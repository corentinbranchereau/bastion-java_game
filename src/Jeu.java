public class Jeu implements Runnable {

	// déclaration des variables//
	public static long gameTime = 0;
	protected long startTime = System.nanoTime();
	public Affichage fenetre;
	public int joueurGagnant;

	// déclaration du Thread//
	public Jeu(int nbLigne, int nbColonne) {

		fenetre = new Affichage(nbLigne, nbColonne);

		Thread thread = new Thread(this);
		thread.start();
	}

	// méthode run du thread//
	@Override
	public void run() {

		long initialTime = System.nanoTime();
		int FPS = 60;
		final double timeF = 1000000000 / FPS;
		double deltaF = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		boolean fin = false;

		while (fin == false) {

			long currentTime = System.nanoTime();
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;

			if (deltaF >= 1) {
				fenetre.update();
				fenetre.repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				System.out.println(String.format("FPS:" + frames));
				frames = 0;
				timer += 1000;
			}

			if (fenetre.estFini() != 0) {
				joueurGagnant = fenetre.estFini();
				fin = true;
			}
		}
		System.out.println("le jeu est fini");
		MenuFin menu = new MenuFin(joueurGagnant);
		fenetre.setVisible(false);

	}

}
