public class Sprite {

	protected int x, y; //upper left, top positions
	protected int height, width;
	protected String image;
	
	public Sprite() {
		super();
	}
	
	public Sprite(int x, int y, int height, int width, String image) {
	super();
	this.x = x;
	this.y = y;
	this.height = height;
	this.width = width;
	this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}
	
	public String getImage() {
		return image;
	}
	
	public int getHeight() {
		return height;
	}


	public void setWidth(int width) {
		this.width = width;
	}

	

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}
