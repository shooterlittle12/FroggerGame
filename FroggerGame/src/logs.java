import javax.swing.JLabel;

public class logs extends Sprite implements Runnable {

    // Variables
    private boolean visible;
    private boolean moving;
    private Thread t;
    int a;
    private int speed; 

    private JLabel logsLabel;

    // Constructors
    public logs() {
        super();
    }

    public logs(boolean visible, boolean moving) {
        super();
        this.visible = visible;
        this.moving = moving;
    }

    public logs(int x, int y, int height, int width, String image, boolean visible, boolean moving, int speed, int a) {
        super(x, y, height, width, image);
        this.visible = visible;
        this.moving = moving;
        this.speed = speed;
        this.a = a;
    }

    
    // Getters and setters
    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    

    @Override
    public void run() {
        System.out.println("log thread started");

        while (this.moving) {
            // Moving code
            int x = this.x;
            //System.out.println("Car moving");

           // x += GameProperties.CHARACTER_STEP;
            x += this.speed;

            if (x >= GameProperties.SCREEN_WIDTH) {
                x = -1 * this.width;
                //System.out.println("Car reset");
            }

            this.x = x;
            logsLabel.setLocation(this.x, this.y);

            try {
                Thread.sleep(300);
                //System.out.println("x, y: " + this.x + "," + this.y);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("log thread stopped");
    }

    // Start function to call thread
    public void startThread() {
        if (!this.moving) {
            this.moving = true;
            t = new Thread(this, "log Thread");
            t.start();
        }
    }

    public void stopThread() {
        this.moving = false;
    }

    // Function to pass JLabel reference to the class
    public void setlogsLabel(JLabel temp) {
    	logsLabel = temp;
    }
    
    public JLabel getlogsLabel() {
        return this.logsLabel;
    }
}
