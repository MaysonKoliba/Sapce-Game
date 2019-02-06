package SpacePackage;

public class gameElements {
	public int ballPosX = -100;
	public int ballPosY = -100;
	public int ballDirX = 0;
	public int ballDirY = 0;
	public int ballHits = 0;
	public boolean ballFired = false;
	
	public int astroPosX = -300;
	public int astroPosY = -300;
	public int astroDirX = 3;
	public int astroDirY = 1;
	public boolean astroPassLeft = false;
	public boolean astroPassRight = false;
	public int astroPassLeftX = -50;
	public int astroPassRightX = 1400;
	public int astroHits = 0;
	public int astroBounce = 0;
	public boolean astroDeployed = false;
}
