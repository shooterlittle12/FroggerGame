import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.PageAttributes.ColorType;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GamePrep extends JFrame implements KeyListener, ActionListener {
    
    private frog character1;
    private frog frog;
    private cars[][] carRows; // Array for cars
    private logs[][] logRows; // Array for logs
    private Rectangle topSafeZone;
    private Rectangle middleSafeZone;
    
    // GUI variables
    private Container content;
    private JLabel character1Label;
    private ImageIcon character1Image;
    
   // private JLabel logsLabel;
  //private ImageIcon logsImage;
    
    // Buttons
    private JButton startButton;
    private JButton visibilityButton;
    
    public GamePrep() {
        // set up screen
        setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
        content = getContentPane();
        content.setBackground(Color.gray);
        setLayout(null);

       //calling functions for setup
        character1 = new frog(0, 0, 100, 100, "frogSprite40.png");
        
        topSafeZone = new Rectangle(0, 0, GameProperties.SCREEN_WIDTH, 100);
        middleSafeZone = new Rectangle(0, 350, GameProperties.SCREEN_WIDTH, 50);
        
        initializeCars();
        initializefrog();
        initializeLogs();
       
        
        addComponentsToPane();

        content.addKeyListener(this);
        content.setFocusable(true); // make the focus on the window and makes keys to always work in the window.
        
       hideObjects();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    private void initializefrog() {
    	frog = new frog();
        character1.setX(300);
        character1.setY(700);
        character1.setWidth(40);
        character1.setHeight(40);
        character1.setImage("frogSprite40.png");
        
        character1Label = new JLabel();
        character1Image = new ImageIcon(getClass().getResource("images/" + character1.getImage()));
        character1Label.setIcon(character1Image);
        character1Label.setSize(character1.getWidth(), character1.getHeight());
        character1Label.setLocation(character1.getX(), character1.getY());
    }

    private void initializeLogs() {
    	
    	    // Define the size of the array 
    	    logRows = new logs[3][2];
    	    
    	    for (int row = 0; row < logRows.length; row++) {
                for (int col = 0; col < logRows[row].length; col++) {
                    // Calculate position for each car
                    int x = 100 + col * 300; 
                    int y = 100 + row * 60;
                    
                    //System.out.println("x:" + x);
                    //System.out.println("y:" + y);

                    // Set a different speed for each log
                    int speed = (row + 1) * 10 + col * 2; 

                   
                    int a = 0;
					logRows[row][col] = new logs(x, y, 100, 300, "log.png", true, false, speed, a);

                   
                    JLabel logsLabel = new JLabel();
                    ImageIcon carImage = new ImageIcon(getClass().getResource("images/" + logRows[row][col].getImage()));
                    logsLabel.setIcon(carImage);
                    logsLabel.setSize(logRows[row][col].getWidth(), logRows[row][col].getHeight());
                    logsLabel.setLocation(x, y);

                    logRows[row][col].setlogsLabel(logsLabel); 

                    // Add the car label to the JFrame
                    add(logsLabel);
                }
            }
            
        }
  
    private void initializeCars() {
        // Define the size of the array 
        carRows = new cars[3][2];

        for (int row = 0; row < carRows.length; row++) {
            for (int col = 0; col < carRows[row].length; col++) {
                // Calculate position for each car
                int x = 200 + col * 200; 
                int y = 400 + row * 60;
                
                //System.out.println("x:" + x);
                //System.out.println("y:" + y);

                // Set a different speed for each car
                int speed = (row + 1) * 10 + col * 2; 

               
                carRows[row][col] = new cars(x, y, 100, 300, "car.png", true, false, speed);

               
                JLabel carLabel = new JLabel();
                ImageIcon carImage = new ImageIcon(getClass().getResource("images/" + carRows[row][col].getImage()));
                carLabel.setIcon(carImage);
                carLabel.setSize(carRows[row][col].getWidth(), carRows[row][col].getHeight());
                carLabel.setLocation(x, y);

                carRows[row][col].setCarsLabel(carLabel); 

                // Add the car label to the JFrame
                add(carLabel);
            }
        }
        
    }

    private void addComponentsToPane() {
        // DisappearButton
        visibilityButton = new JButton("Start");
        visibilityButton.setSize(100, 50);
        visibilityButton.setLocation(GameProperties.SCREEN_WIDTH - 100, GameProperties.SCREEN_HEIGHT - 100);
        visibilityButton.setFocusable(false);
        
        // move button
        startButton = new JButton("move button");
        startButton.setSize(100, 50);
        startButton.setLocation(GameProperties.SCREEN_WIDTH - 100, GameProperties.SCREEN_HEIGHT - 200);
        startButton.setFocusable(false);

        startButton.addActionListener(this);
        visibilityButton.addActionListener(this);
        add(startButton);
        add(visibilityButton);
        add(character1Label);
       // add(logsLabel);
    }

    // to detect collision
    private void checkCollision() {
        Rectangle frogBounds = character1Label.getBounds();
        for (int row = 0; row < carRows.length; row++) {
            for (int col = 0; col < carRows[row].length; col++) {
                Rectangle carBounds = carRows[row][col].getCarsLabel().getBounds();
                if (frogBounds.intersects(carBounds)) {
                    System.out.println("Collision Detected!");  
                    character1.setX(300);
                    character1.setY(700);
                    character1Label.setLocation(character1.getX(), character1.getY());
                }
            }
        }
    }
    
    private void checkIntersection() {
        Rectangle frogBounds = character1Label.getBounds();
        for (int row = 0; row < carRows.length; row++) {
            for (int col = 0; col < logRows[row].length; col++) {
                Rectangle logBounds = logRows[row][col].getlogsLabel().getBounds();
                if (frogBounds.intersects(logBounds)) {
                    System.out.println("intersection Detected!");  
                }
            }
        }
    }
    private void checkSafeZone() {
    	Rectangle frogBounds = character1Label.getBounds();
        if (frogBounds.intersects(middleSafeZone) || frogBounds.intersects(topSafeZone)) {
            System.out.println("Character is in the Safe Zone!");
        }
    }
    
    
    
    private void hideObjects() {
    	 character1Label.setVisible(false);
    	 
         for (cars[] carRow : carRows) {
             for (cars car : carRow) {
                 car.getCarsLabel().setVisible(false);
             }
         }
         for (logs[] logRow : logRows) {
             for (logs log : logRow) {
                 log.getlogsLabel().setVisible(false);
             }
         }
    }
    
    private void showObjects() {
    	character1Label.setVisible(true);
   	 
        for (cars[] carRow : carRows) {
            for (cars car : carRow) {
                car.getCarsLabel().setVisible(true);
            }
        }
        for (logs[] logRow : logRows) {
            for (logs log : logRow) {
                log.getlogsLabel().setVisible(true);
            }
        }
   }

    
    
    
    private void startAllObjects() {
        for (cars[] row : carRows) {
            for (cars car : row) {
                car.startThread();
            }
        }
        for (logs[] row : logRows) {
            for (logs log : row) {
                log.startThread();
            }
        }
        startButton.setText("Stop");
    }

    private void stopAllObjects() {
        for (cars[] row : carRows) {
            for (cars car : row) {
                car.stopThread();
            }
        }
        for (logs[] row : logRows) {
            for (logs log : row) {
                log.startThread();
            }
        }
        startButton.setText("Start");
    }
    
    public static void main(String[] args) {
        GamePrep myGame = new GamePrep();
        myGame.setVisible(true);
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    	//get current position
		int x = character1.getX();
		int y = character1.getY();
    	
    	//to check collision
		checkCollision();
		checkIntersection();
		//to check if the variable in top safe zone
		checkSafeZone();
		
		//detect direction and modify the coordinates
		if (e.getKeyCode() == KeyEvent.VK_UP) {
		    
		    y -= GameProperties.CHARACTER_STEP;
		    // Prevent the character from going above the top edge
		    if (y < 0) { y = 0; }
		    
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		    
		    y += GameProperties.CHARACTER_STEP;
		    // Prevent the character from going below the bottom edge
		    if (y > GameProperties.SCREEN_HEIGHT - character1.getHeight()) { 
		        y = GameProperties.SCREEN_HEIGHT - character1.getHeight(); 
		    }
		    
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		    
		    x -= GameProperties.CHARACTER_STEP;
		    // Prevent the character from going past the left edge
		    if (x < 0) { x = 0; }
		    
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    
		    x += GameProperties.CHARACTER_STEP;
		    // Prevent the character from going off the right edge
		    if (x > GameProperties.SCREEN_WIDTH - character1.getWidth()) { 
		        x = GameProperties.SCREEN_WIDTH - character1.getWidth(); 
		    }

		} else {
		    System.out.println("Invalid Operation");
		}
		
		
		//updating the new values of x and y
		character1.setX(x);
		character1.setY(y);

		//updating the character
		character1Label.setLocation(character1.getX(), character1.getY());
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == visibilityButton) {
        	boolean a = true;
            showObjects();
            startAllObjects();
            visibilityButton.setText("Reset");
            
            if (a == true);
            
        } else if (e.getSource() == startButton) {
        	System.out.println("Start pressed");
            stopAllObjects();
        }
    }
}
