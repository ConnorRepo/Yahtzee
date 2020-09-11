import javax.swing.*;
import java.awt.*;

public class MyDrawPanelDie extends JPanel {

  public void paintComponent(Graphics g) {
    Roll_Dice random = new Roll_Dice();
    int die = random.roll();

  //Declare an array which will hold the sides of all the dice
    Image sides[] = new Image[6];
  //Initialize all indexes of the 'sides' array with the proper images
    sides[0] = new ImageIcon("Die_1.jpg").getImage();
    sides[1] = new ImageIcon("Die_2.jpg").getImage();
    sides[2] = new ImageIcon("Die_3.jpg").getImage();
    sides[3] = new ImageIcon("Die_4.jpg").getImage();
    sides[4] = new ImageIcon("Die_5.jpg").getImage();
    sides[5] = new ImageIcon("Die_6.jpg").getImage();

    g.drawImage(sides[die-1], 3, 4, this);
  }
}
