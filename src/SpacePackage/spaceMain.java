package SpacePackage;
import javax.swing.JFrame;

public class spaceMain {
	
	public static spaceMain game;
	public JFrame window;
	public static spaceRender render = new spaceRender();
	
	public spaceMain() {
		window = new JFrame("Space Game");
		window.setSize(1300,900);
		window.setResizable(false);
		window.add(render);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public static void main(String[] args) {
		game = new spaceMain();
	}
}