import java.awt.Rectangle;

public class frog extends Sprite{

	public frog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public frog(int x, int y, int height, int width, String image) {
		super(x, y, height, width, image);
		// TODO Auto-generated constructor stub
	}
	
	 public Rectangle getBounds() {
	        return new Rectangle(x, y, width, height);
	    }

}
