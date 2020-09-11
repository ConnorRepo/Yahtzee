//import this swing package
import javax.swing.*;
//a new import statement for the package that ActionListener and ActionEvent are in
import java.awt.*;
import java.awt.event.*;

//Implement the interface
public class YahtzeeGui{
//Declare and initialize the frame and panel which will contain the entire game
  JFrame base = new JFrame();
  JPanel background = new JPanel();
//Declare and initialze the panels which hold the components of the game
  JPanel scoresheet = new JPanel();
//Generates a random roll value
  Roll_Dice roller = new Roll_Dice();
//Declare and intialize the components
  JPanel buttons = new JPanel();
  JPanel[] first_images = new JPanel[5];
  JPanel[] second_images = new JPanel[5];
//Declare array which tracks whether die should be re-rolled or not
  boolean rollagain[] = new boolean [5];
  Score_Keeper get_score = new Score_Keeper();
//Declare array which tracks dice values
  int[] dice_values = new int[5];

//Instantiate dice selection buttons
  JButton one = new JButton("Keep");
  JButton two = new JButton("Keep");
  JButton three = new JButton("Keep");
  JButton four = new JButton("Keep");
  JButton five = new JButton("Keep");

//Declare and intialize integers which track die rolls and rounds
  int die_roll = 0;
  static int round = 10;
  static int num_yahtzee = 0;

//Declare and initialize booleans to track what categories are used
  static boolean ones_u = false;
  static boolean twos_u = false;
  static boolean threes_u = false;
  static boolean fours_u = false;
  static boolean fives_u = false;
  static boolean sixes_u = false;
  static boolean three_kind_u = false;
  static boolean four_kind_u = false;
  static boolean full_house_u = false;
  static boolean sm_straight_u = false;
  static boolean lg_straight_u = false;
  static boolean chance_u = false;
  static boolean yahtzee_u = false;

//Declare and intialize all integers which will hold the scores
  static int ones        = 0;
  static int twos        = 0;
  static int threes      = 0;
  static int fours       = 0;
  static int fives       = 0;
  static int sixes       = 0;
  static int upper_total = 0;
  static int bonus       = 0;
  static int three_kind  = 0;
  static int four_kind   = 0;
  static int full_house  = 0;
  static int sm_straight = 0;
  static int lg_straight = 0;
  static int yahtzee     = 0;
  static int chance      = 0;
  static int lower_total = 0;
  static int grand_total = 0;

//Declare and instantiate all labels for the scoresheet
  JLabel YAHTZEE       = new JLabel("YAHTZEE!");
  JLabel ones_l        = new JLabel("Aces: " + ones);
  JLabel twos_l        = new JLabel("Twos: " + twos);
  JLabel threes_l      = new JLabel("Threes: " + threes);
  JLabel fours_l       = new JLabel("Fours: " + fours);
  JLabel fives_l       = new JLabel("Fives: " + fives);
  JLabel sixes_l       = new JLabel("Sixes: " + sixes);
  JLabel upper_total_l = new JLabel("Upper Total: " + upper_total);
  JLabel bonus_l       = new JLabel("Bonus: " + bonus);
  JLabel three_kind_l  = new JLabel("Three of a Kind: " + three_kind);
  JLabel four_kind_l   = new JLabel("Four of a Kind: " + four_kind);
  JLabel full_house_l  = new JLabel("Full House: " + full_house);
  JLabel sm_straight_l = new JLabel("Small Straight: " + sm_straight);
  JLabel lg_straight_l = new JLabel("Large Straight: " + lg_straight);
  JLabel yahtzee_l     = new JLabel("Yahtzee: " + yahtzee);
  JLabel chance_l      = new JLabel("Chance: " + chance);
  JLabel lower_total_l = new JLabel("Lower Total: " + lower_total);
  JLabel grand_total_l = new JLabel("Grand Total: " + grand_total);

//Declare and initialize object which rolls all five dice
  public static void main(String[] args) {
    YahtzeeGui gui = new YahtzeeGui();
    gui.play();
  }

  public void play() {


//--------------------------SETUP THE STARTING SCREEN-----------



  //enlarge the font of the labels
    Font bigFont   = new Font("times", Font.PLAIN, 20);
    Font titleFont = new Font("times", Font.BOLD, 40);
    YAHTZEE.setFont(titleFont);
    ones_l.setFont(bigFont);
    twos_l.setFont(bigFont);
    threes_l.setFont(bigFont);
    fours_l.setFont(bigFont);
    fives_l.setFont(bigFont);
    sixes_l.setFont(bigFont);
    upper_total_l.setFont(bigFont);
    bonus_l.setFont(bigFont);
    three_kind_l.setFont(bigFont);
    four_kind_l.setFont(bigFont);
    full_house_l.setFont(bigFont);
    sm_straight_l.setFont(bigFont);
    lg_straight_l.setFont(bigFont);
    yahtzee_l.setFont(bigFont);
    chance_l.setFont(bigFont);
    lower_total_l.setFont(bigFont);
    grand_total_l.setFont(bigFont);

  //Set the frame to close and end the program when the 'X' is clicked
    base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //Override the panel's layout manager so the labels are stacked
    scoresheet.setLayout(new BoxLayout(scoresheet, BoxLayout.Y_AXIS));
  //Override the background panel's layout manager
    background.setLayout(null);

  //Display the scoresheet as a series of lines of text
    scoresheet.add(YAHTZEE);
    scoresheet.add(ones_l);
    scoresheet.add(twos_l);
    scoresheet.add(threes_l);
    scoresheet.add(fours_l);
    scoresheet.add(fives_l);
    scoresheet.add(sixes_l);
    scoresheet.add(upper_total_l);
    scoresheet.add(bonus_l);
    scoresheet.add(three_kind_l);
    scoresheet.add(four_kind_l);
    scoresheet.add(full_house_l);
    scoresheet.add(sm_straight_l);
    scoresheet.add(lg_straight_l);
    scoresheet.add(yahtzee_l);
    scoresheet.add(chance_l);
    scoresheet.add(lower_total_l);
    scoresheet.add(grand_total_l);

  //Add scoresheet panel to frame
    base.setContentPane(background);
    base.setSize(1375,875);
    base.setVisible(true);
    background.add(scoresheet);
  //Set the position and size of the scoresheet panel so it is exactly right
  //order = x, y, width, height
    scoresheet.setBounds(4, 4, 200, 490);

//-------------------SETUP FIRST ROLL-------------------------------


    for(int i = 0; i < 5; i++) {
    //Add a random die value to the tracking array
      dice_values[i] = roller.roll();
    //Instantiate the object which displays the die
      if(dice_values[i] == 1) {
        first_images[i] = new DrawDieOne();
      }
      if(dice_values[i] == 2) {
        first_images[i] = new DrawDieTwo();
      }
      if(dice_values[i] == 3) {
        first_images[i] = new DrawDieThree();
      }
      if(dice_values[i] == 4) {
        first_images[i] = new DrawDieFour();
      }
      if(dice_values[i] == 5) {
        first_images[i] = new DrawDieFive();
      }
      if(dice_values[i] == 6) {
        first_images[i] = new DrawDieSix();
      }
    }

    background.add(first_images[0]);
    first_images[0].setBounds(208,4, 220, 220);
    background.add(first_images[1]);
    first_images[1].setBounds(428,4, 220, 220);
    background.add(first_images[2]);
    first_images[2].setBounds(652,4, 220, 220);
    background.add(first_images[3]);
    first_images[3].setBounds(876,4, 220, 220);
    background.add(first_images[4]);
    first_images[4].setBounds(1100,4, 220, 220);


//---------------------------ADD BUTTONS----------------------


  //Override the layout manager for buttons to NULL
    buttons.setLayout(null);

  //Declare and intialize all JButtons used for selecting which dice to re-roll
    one.addActionListener(new OneListener());
    one.setBounds(4,4,210,30);
    two.addActionListener(new TwoListener());
    two.setBounds(230,4,210,30);
    three.addActionListener(new ThreeListener());
    three.setBounds(450,4,210,30);
    four.addActionListener(new FourListener());
    four.setBounds(679,4,210,30);
    five.addActionListener(new FiveListener());
    five.setBounds(900,4,210,30);

  //Declare buttons to re-roll dice and to score dice
    JButton roll_b = new JButton("Re-Roll Selected Dice");
    roll_b.addActionListener(new roll_bListener());
    roll_b.setBounds(350, 68, 200, 30);
    JButton score_b = new JButton("Score Dice");
    score_b.addActionListener(new score_bListener());
    score_b.setBounds(554, 68, 200, 30);

  //Declare button to end the game
    JButton end_game = new JButton("End Game");
    end_game.addActionListener(new end_game_bListener());
    end_game.setBounds(4, 500, 150, 50);

  //Add all five buttons to the panel for them
    buttons.add(one);
    buttons.add(two);
    buttons.add(three);
    buttons.add(four);
    buttons.add(five);
    buttons.add(roll_b);
    buttons.add(score_b);

  //Add the buttons panel to the background panel
    background.add(buttons);
    background.add(end_game);
  //Specify the location and size of the buttons panel
    buttons.setBounds(208, 224, 1100, 200);

  //Declare an array which will store whether a die will be rolled again
    for(int i = 0; i < 5; i++) {
      rollagain[i] = false;
    }
  }

//----------------SUPPORTING CLASSES-------------------------


//----------DIE DRAWING--------------------

  class DrawDieOne extends JPanel {
    public void paintComponent(Graphics g) {
    Image image = new ImageIcon("Die_1.jpg") .getImage();

    g.drawImage(image, 3, 4, this);
    }
  }

  class DrawDieTwo extends JPanel {
    public void paintComponent(Graphics g) {
    Image image = new ImageIcon("Die_2.jpg") .getImage();

    g.drawImage(image, 3, 4, this);
    }
  }

  class DrawDieThree extends JPanel {
    public void paintComponent(Graphics g) {
    Image image = new ImageIcon("Die_3.jpg") .getImage();

    g.drawImage(image, 3, 4, this);
    }
  }

  class DrawDieFour extends JPanel {
    public void paintComponent(Graphics g) {
    Image image = new ImageIcon("Die_4.jpg") .getImage();

    g.drawImage(image, 3, 4, this);
    }
  }

  class DrawDieFive extends JPanel {
    public void paintComponent(Graphics g) {
    Image image = new ImageIcon("Die_5.jpg") .getImage();

    g.drawImage(image, 3, 4, this);
    }
  }

  class DrawDieSix extends JPanel {
    public void paintComponent(Graphics g) {
    Image image = new ImageIcon("Die_6.jpg") .getImage();

    g.drawImage(image, 3, 4, this);
    }
  }

//-----------BUTTON LISTENING--------------


  class OneListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //Declare a JLabel which will show when a die is selected
      if(rollagain[0] == false) {
        one.setText("Roll Again");
        rollagain[0] = true;
      } else {
        one.setText("Keep");
        rollagain[0] = false;
      }
    }
  }

  class TwoListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //Declare a JLabel which will show when a die is selected
      if(rollagain[1] == false) {
        two.setText("Roll Again");
        rollagain[1] = true;
      } else {
        two.setText("Keep");
        rollagain[1] = false;
      }
    }
  }

  class ThreeListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //Declare a JLabel which will show when a die is selected
      if(rollagain[2] == false) {
        three.setText("Roll Again");
        rollagain[2] = true;
      } else {
        three.setText("Keep");
        rollagain[2] = false;
      }
    }
  }

  class FourListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //Declare a JLabel which will show when a die is selected
      if(rollagain[3] == false) {
        four.setText("Roll Again");
        rollagain[3] = true;
      } else {
        four.setText("Keep");
        rollagain[3] = false;
      }
    }
  }

  class FiveListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //Declare a JLabel which will show when a die is selected
      if(rollagain[4] == false) {
        five.setText("Roll Again");
        rollagain[4] = true;
      } else {
        five.setText("Keep");
        rollagain[4] = false;
      }
    }
  }

  class roll_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      if(die_roll < 2) {
        if(rollagain[0]) {
        //Add a random die value to the tracking array
          dice_values[0] = roller.roll();

        //Instantiate the object which displays the die
          if(dice_values[0] == 1) {
            second_images[0] = new DrawDieOne();
            background.add(second_images[0]);
            second_images[0].setBounds(208,4, 220, 220);
          }
          if(dice_values[0] == 2) {
            second_images[0] = new DrawDieTwo();
            background.add(second_images[0]);
            second_images[0].setBounds(208,4, 220, 220);
          }
          if(dice_values[0] == 3) {
            second_images[0] = new DrawDieThree();
            background.add(second_images[0]);
            second_images[0].setBounds(208,4, 220, 220);
          }
          if(dice_values[0] == 4) {
            second_images[0] = new DrawDieFour();
            background.add(second_images[0]);
            second_images[0].setBounds(208,4, 220, 220);
          }
          if(dice_values[0] == 5) {
            second_images[0] = new DrawDieFive();
            background.add(second_images[0]);
            second_images[0].setBounds(208,4, 220, 220);
          }
          if(dice_values[0] == 6) {
            second_images[0] = new DrawDieSix();
            background.add(second_images[0]);
            second_images[0].setBounds(208,4, 220, 220);
          }
        }
        if(rollagain[1]) {
        //Add a random die value to the tracking array
          dice_values[1] = roller.roll();

        //Instantiate the object which displays the die
          if(dice_values[1] == 1) {
            second_images[1] = new DrawDieOne();
            background.add(second_images[1]);
            second_images[1].setBounds(428,4, 220, 220);
          }
          if(dice_values[1] == 2) {
            second_images[1] = new DrawDieTwo();
            background.add(second_images[1]);
            second_images[1].setBounds(428,4, 220, 220);
          }
          if(dice_values[1] == 3) {
            second_images[1] = new DrawDieThree();
            background.add(second_images[1]);
            second_images[1].setBounds(428,4, 220, 220);
          }
          if(dice_values[1] == 4) {
            second_images[1] = new DrawDieFour();
            background.add(second_images[1]);
            second_images[1].setBounds(428,4, 220, 220);
          }
          if(dice_values[1] == 5) {
            second_images[1] = new DrawDieFive();
            background.add(second_images[1]);
            second_images[1].setBounds(428,4, 220, 220);
          }
          if(dice_values[1] == 6) {
            second_images[1] = new DrawDieSix();
            background.add(second_images[1]);
            second_images[1].setBounds(428,4, 220, 220);
          }
        }
        if(rollagain[2]) {
        //Add a random die value to the tracking array
          dice_values[2] = roller.roll();

        //Instantiate the object which displays the die
          if(dice_values[2] == 1) {
            second_images[2] = new DrawDieOne();
            background.add(second_images[2]);
            second_images[2].setBounds(652,4, 220, 220);
          }
          if(dice_values[2] == 2) {
            second_images[2] = new DrawDieTwo();
            background.add(second_images[2]);
            second_images[2].setBounds(652,4, 220, 220);
          }
          if(dice_values[2] == 3) {
            second_images[2] = new DrawDieThree();
            background.add(second_images[2]);
            second_images[2].setBounds(652,4, 220, 220);
          }
          if(dice_values[2] == 4) {
            second_images[2] = new DrawDieFour();
            background.add(second_images[2]);
            second_images[2].setBounds(652,4, 220, 220);
          }
          if(dice_values[2] == 5) {
            second_images[2] = new DrawDieFive();
            background.add(second_images[2]);
            second_images[2].setBounds(652,4, 220, 220);
          }
          if(dice_values[2] == 6) {
            second_images[2] = new DrawDieSix();
            background.add(second_images[2]);
            second_images[2].setBounds(652,4, 220, 220);
          }
        }
        if(rollagain[3]) {
        //Add a random die value to the tracking array
          dice_values[3] = roller.roll();

        //Instantiate the object which displays the die
          if(dice_values[3] == 1) {
            second_images[3] = new DrawDieOne();
            background.add(second_images[3]);
            second_images[3].setBounds(876,4, 220, 220);
          }
          if(dice_values[3] == 2) {
            second_images[3] = new DrawDieTwo();
            background.add(second_images[3]);
            second_images[3].setBounds(876,4, 220, 220);
          }
          if(dice_values[3] == 3) {
            second_images[3] = new DrawDieThree();
            background.add(second_images[3]);
            second_images[3].setBounds(876,4, 220, 220);
          }
          if(dice_values[3] == 4) {
            second_images[3] = new DrawDieFour();
            background.add(second_images[3]);
            second_images[3].setBounds(876,4, 220, 220);
          }
          if(dice_values[3] == 5) {
            second_images[3] = new DrawDieFive();
            background.add(second_images[3]);
            second_images[3].setBounds(876,4, 220, 220);
          }
          if(dice_values[3] == 6) {
            second_images[3] = new DrawDieSix();
            background.add(second_images[3]);
            second_images[3].setBounds(876,4, 220, 220);
          }
        }
        if(rollagain[4]) {
        //Add a random die value to the tracking array
          dice_values[4] = roller.roll();

        //Instantiate the object which displays the die
          if(dice_values[4] == 1) {
            second_images[4] = new DrawDieOne();
            background.add(second_images[4]);
            second_images[4].setBounds(1100,4, 220, 220);
          }
          if(dice_values[4] == 2) {
            second_images[4] = new DrawDieTwo();
            background.add(second_images[4]);
            second_images[4].setBounds(1100,4, 220, 220);
          }
          if(dice_values[4] == 3) {
            second_images[4] = new DrawDieThree();
            background.add(second_images[4]);
            second_images[4].setBounds(1100,4, 220, 220);
          }
          if(dice_values[4] == 4) {
            second_images[4] = new DrawDieFour();
            background.add(second_images[4]);
            second_images[4].setBounds(1100,4, 220, 220);
          }
          if(dice_values[4] == 5) {
            second_images[4] = new DrawDieFive();
            background.add(second_images[4]);
            second_images[4].setBounds(1100,4, 220, 220);
          }
          if(dice_values[4] == 6) {
            second_images[4] = new DrawDieSix();
            background.add(second_images[4]);
            second_images[4].setBounds(1108,4, 220, 220);
          }
        }
        die_roll += 1;
      }
    }
  }

  class score_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      JPanel scoring = new JPanel();
      background.add(scoring);
      scoring.setBounds(208, 400, 1100, 350);
    //Add the buttons selecting which category to score
    //Button for Aces
      JButton ones_b = new JButton("Aces");
      scoring.add(ones_b);
      ones_b.addActionListener(new ones_bListener());
      ones_b.setBounds(4, 4, 175, 50);
    //Button for twos
      JButton twos_b = new JButton("Twos");
      scoring.add(twos_b);
      twos_b.addActionListener(new twos_bListener());
      twos_b.setBounds(183, 4, 175, 50);
    //Button for threes
      JButton threes_b = new JButton("Threes");
      scoring.add(threes_b);
      threes_b.addActionListener(new threes_bListener());
      threes_b.setBounds(362, 4, 175, 50);
    //Button for fours
      JButton fours_b = new JButton("Fours");
      scoring.add(fours_b);
      fours_b.addActionListener(new fours_bListener());
      fours_b.setBounds(541, 4, 175, 50);
    //Button for fives
      JButton fives_b = new JButton("Fives");
      scoring.add(fives_b);
      fives_b.addActionListener(new fives_bListener());
      fives_b.setBounds(720, 4, 175, 50);
    //Button for sixes
      JButton sixes_b = new JButton("Sixes");
      scoring.add(sixes_b);
      sixes_b.addActionListener(new sixes_bListener());
      sixes_b.setBounds(899, 4, 175, 50);
    //Button for three of a kind
      JButton three_kind_b = new JButton("Three of a Kind");
      scoring.add(three_kind_b);
      three_kind_b.addActionListener(new three_kind_bListener());
      three_kind_b.setBounds(4, 58, 175, 50);
    //Button for four of a kind
      JButton four_kind_b = new JButton("Four of a Kind");
      scoring.add(four_kind_b);
      four_kind_b.addActionListener(new four_kind_bListener());
      four_kind_b.setBounds(183, 58, 175, 50);
    //Button for full house
      JButton fullhouse_b = new JButton("Full House");
      scoring.add(fullhouse_b);
      fullhouse_b.addActionListener(new fullhouse_bListener());
      fullhouse_b.setBounds(362, 58, 175, 50);
    //Button for small straight
      JButton sm_straight_b = new JButton("Small Straight");
      scoring.add(sm_straight_b);
      sm_straight_b.addActionListener(new sm_straight_bListener());
      sm_straight_b.setBounds(541, 58, 175, 50);
    //Button for large straight
      JButton lg_straight_b = new JButton("Large Straight");
      scoring.add(lg_straight_b);
      lg_straight_b.addActionListener(new lg_straight_bListener());
      lg_straight_b.setBounds(720, 58, 175, 50);
    //Button for chance
      JButton chance_b = new JButton("Chance");
      scoring.add(chance_b);
      chance_b.addActionListener(new chance_bListener());
      chance_b.setBounds(899, 58, 175, 50);
    //Button for Yahtzee
      JButton yahtzee_b = new JButton("YAHTZEE!");
      scoring.add(yahtzee_b);
      yahtzee_b.addActionListener(new yahtzee_bListener());
      yahtzee_b.setBounds(4, 112, 175, 50);
    }
  }


  class ones_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //check if the option has been used yet
      if(ones_u == false) {
        ones = get_score.score("ones", dice_values, num_yahtzee);
        ones_l.setText("Aces: " + ones);
        upper_total = ones + twos + threes + fours + fives + sixes;
        if(upper_total > 62) {
          bonus = 35;
          bonus_l.setText("Bonus: " + bonus);
        }
        upper_total = bonus + ones + twos + threes + fours + fives + sixes;
        upper_total_l.setText("Upper Total: " + upper_total);
        lower_total = three_kind + four_kind + sm_straight + lg_straight + full_house + chance + yahtzee;
        lower_total_l.setText("Lower Total: " + lower_total);
        grand_total = upper_total + lower_total;
        grand_total_l.setText("Grand Total: " + grand_total);
        round += 1;
        YahtzeeGui gui = new YahtzeeGui();
        gui.play();
        ones_u = true;
      }
    }
  }
  class twos_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //check if the option has been used yet
      if(twos_u == false) {
        twos = get_score.score("twos", dice_values, num_yahtzee);
        twos_l.setText("Twos: " + twos);
        upper_total = ones + twos + threes + fours + fives + sixes;
        if(upper_total > 62) {
          bonus = 35;
          bonus_l.setText("Bonus: " + bonus);
        }
        upper_total = bonus + ones + twos + threes + fours + fives + sixes;
        upper_total_l.setText("Upper Total: " + upper_total);
        lower_total = three_kind + four_kind + sm_straight + lg_straight + full_house + chance + yahtzee;
        lower_total_l.setText("Lower Total: " + lower_total);
        grand_total = upper_total + lower_total;
        grand_total_l.setText("Grand Total: " + grand_total);
        round += 1;
        YahtzeeGui gui = new YahtzeeGui();
        gui.play();
        twos_u = true;
      }
    }
  }
  class threes_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //check if the option has been used yet
      if(threes_u == false) {
        threes = get_score.score("threes", dice_values, num_yahtzee);
        threes_l.setText("Threes: " + threes);
        upper_total = ones + twos + threes + fours + fives + sixes;
        if(upper_total > 62) {
          bonus = 35;
          bonus_l.setText("Bonus: " + bonus);
        }
        upper_total = bonus + ones + twos + threes + fours + fives + sixes;
        upper_total_l.setText("Upper Total: " + upper_total);
        lower_total = three_kind + four_kind + sm_straight + lg_straight + full_house + chance + yahtzee;
        lower_total_l.setText("Lower Total: " + lower_total);
        grand_total = upper_total + lower_total;
        grand_total_l.setText("Grand Total: " + grand_total);
        round += 1;
        YahtzeeGui gui = new YahtzeeGui();
        gui.play();
        threes_u = true;
      }
    }
  }
  class fours_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //check if the option has been used yet
      if(fours_u == false) {
        fours = get_score.score("fours", dice_values, num_yahtzee);
        fours_l.setText("Fours: " + fours);
        upper_total = ones + twos + threes + fours + fives + sixes;
        if(upper_total > 62) {
          bonus = 35;
          bonus_l.setText("Bonus: " + bonus);
        }
        upper_total = bonus + ones + twos + threes + fours + fives + sixes;
        upper_total_l.setText("Upper Total: " + upper_total);
        lower_total = three_kind + four_kind + sm_straight + lg_straight + full_house + chance + yahtzee;
        lower_total_l.setText("Lower Total: " + lower_total);
        grand_total = upper_total + lower_total;
        grand_total_l.setText("Grand Total: " + grand_total);
        round += 1;
        YahtzeeGui gui = new YahtzeeGui();
        gui.play();
        fours_u = true;
      }
    }
  }
  class fives_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //check if the option has been used yet
      if(fives_u == false) {
        fives = get_score.score("fives", dice_values, num_yahtzee);
        fives_l.setText("Fives: " + fives);
        upper_total = ones + twos + threes + fours + fives + sixes;
        if(upper_total > 62) {
          bonus = 35;
          bonus_l.setText("Bonus: " + bonus);
        }
        upper_total = bonus + ones + twos + threes + fours + fives + sixes;
        upper_total_l.setText("Upper Total: " + upper_total);
        lower_total = three_kind + four_kind + sm_straight + lg_straight + full_house + chance + yahtzee;
        lower_total_l.setText("Lower Total: " + lower_total);
        grand_total = upper_total + lower_total;
        grand_total_l.setText("Grand Total: " + grand_total);
        round += 1;
        YahtzeeGui gui = new YahtzeeGui();
        gui.play();
        fives_u = true;
      }
    }
  }
  class sixes_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //check if the option has been used yet
      if(sixes_u == false) {
        sixes = get_score.score("sixes", dice_values, num_yahtzee);
        sixes_l.setText("Sixes: " + sixes);
        upper_total = ones + twos + threes + fours + fives + sixes;
        if(upper_total > 62) {
          bonus = 35;
          bonus_l.setText("Bonus: " + bonus);
        }
        upper_total = bonus + ones + twos + threes + fours + fives + sixes;
        upper_total_l.setText("Upper Total: " + upper_total);
        lower_total = three_kind + four_kind + sm_straight + lg_straight + full_house + chance + yahtzee;
        lower_total_l.setText("Lower Total: " + lower_total);
        grand_total = upper_total + lower_total;
        grand_total_l.setText("Grand Total: " + grand_total);
        round += 1;
        YahtzeeGui gui = new YahtzeeGui();
        gui.play();
        sixes_u = true;
      }
    }
  }
  class three_kind_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //check if the option has been used yet
      if(three_kind_u == false) {
        three_kind = get_score.score("3 of a kind", dice_values, num_yahtzee);
        three_kind_l.setText("Three of a Kind: " + three_kind);
        upper_total = ones + twos + threes + fours + fives + sixes;
        if(upper_total > 62) {
          bonus = 35;
          bonus_l.setText("Bonus: " + bonus);
        }
        upper_total = bonus + ones + twos + threes + fours + fives + sixes;
        upper_total_l.setText("Upper Total: " + upper_total);
        lower_total = three_kind + four_kind + sm_straight + lg_straight + full_house + chance + yahtzee;
        lower_total_l.setText("Lower Total: " + lower_total);
        grand_total = upper_total + lower_total;
        grand_total_l.setText("Grand Total: " + grand_total);
        round += 1;
        YahtzeeGui gui = new YahtzeeGui();
        gui.play();
        three_kind_u = true;
      }
    }
  }
  class four_kind_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //check if the option has been used yet
      if(four_kind_u == false) {
        four_kind = get_score.score("4 of a kind", dice_values, num_yahtzee);
        four_kind_l.setText("Four of a Kind: " + four_kind);
        upper_total = ones + twos + threes + fours + fives + sixes;
        if(upper_total > 62) {
          bonus = 35;
          bonus_l.setText("Bonus: " + bonus);
        }
        upper_total = bonus + ones + twos + threes + fours + fives + sixes;
        upper_total_l.setText("Upper Total: " + upper_total);
        lower_total = three_kind + four_kind + sm_straight + lg_straight + full_house + chance + yahtzee;
        lower_total_l.setText("Lower Total: " + lower_total);
        grand_total = upper_total + lower_total;
        grand_total_l.setText("Grand Total: " + grand_total);
        round += 1;
        YahtzeeGui gui = new YahtzeeGui();
        gui.play();
        four_kind_u = true;
      }
    }
  }
  class fullhouse_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //check if the option has been used yet
      if(full_house_u == false) {
        full_house = get_score.score("full house", dice_values, num_yahtzee);
        full_house_l.setText("Full House: " + full_house);
        upper_total = ones + twos + threes + fours + fives + sixes;
        if(upper_total > 62) {
          bonus = 35;
          bonus_l.setText("Bonus: " + bonus);
        }
        upper_total = bonus + ones + twos + threes + fours + fives + sixes;
        upper_total_l.setText("Upper Total: " + upper_total);
        lower_total = three_kind + four_kind + sm_straight + lg_straight + full_house + chance + yahtzee;
        lower_total_l.setText("Lower Total: " + lower_total);
        grand_total = upper_total + lower_total;
        grand_total_l.setText("Grand Total: " + grand_total);
        round += 1;
        YahtzeeGui gui = new YahtzeeGui();
        gui.play();
        full_house_u = true;
      }
    }
  }
  class sm_straight_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //check if the option has been used yet
      if(sm_straight_u == false) {
        sm_straight = get_score.score("sm straight", dice_values, num_yahtzee);
        sm_straight_l.setText("Small Straight: " + sm_straight);
        upper_total = ones + twos + threes + fours + fives + sixes;
        if(upper_total > 62) {
          bonus = 35;
          bonus_l.setText("Bonus: " + bonus);
        }
        upper_total = bonus + ones + twos + threes + fours + fives + sixes;
        upper_total_l.setText("Upper Total: " + upper_total);
        lower_total = three_kind + four_kind + sm_straight + lg_straight + full_house + chance + yahtzee;
        lower_total_l.setText("Lower Total: " + lower_total);
        grand_total = upper_total + lower_total;
        grand_total_l.setText("Grand Total: " + grand_total);
        round += 1;
        YahtzeeGui gui = new YahtzeeGui();
        gui.play();
        sm_straight_u = true;
      }
    }
  }
  class lg_straight_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //check if the option has been used yet
      if(lg_straight_u == false) {
        lg_straight = get_score.score("lg straight", dice_values, num_yahtzee);
        lg_straight_l.setText("Large Straight: " + lg_straight);
        upper_total = ones + twos + threes + fours + fives + sixes;
        if(upper_total > 62) {
          bonus = 35;
          bonus_l.setText("Bonus: " + bonus);
        }
        upper_total = bonus + ones + twos + threes + fours + fives + sixes;
        upper_total_l.setText("Upper Total: " + upper_total);
        lower_total = three_kind + four_kind + sm_straight + lg_straight + full_house + chance + yahtzee;
        lower_total_l.setText("Lower Total: " + lower_total);
        grand_total = upper_total + lower_total;
        grand_total_l.setText("Grand Total: " + grand_total);
        round += 1;
        YahtzeeGui gui = new YahtzeeGui();
        gui.play();
        lg_straight_u = true;
      }
    }
  }
  class chance_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //check if the option has been used yet
      if(chance_u == false) {
        chance = get_score.score("chance", dice_values, num_yahtzee);
        chance_l.setText("Chance: " + chance);
        upper_total = ones + twos + threes + fours + fives + sixes;
        if(upper_total > 62) {
          bonus = 35;
          bonus_l.setText("Bonus: " + bonus);
        }
        upper_total = bonus + ones + twos + threes + fours + fives + sixes;
        upper_total_l.setText("Upper Total: " + upper_total);
        lower_total = three_kind + four_kind + sm_straight + lg_straight + full_house + chance + yahtzee;
        lower_total_l.setText("Lower Total: " + lower_total);
        grand_total = upper_total + lower_total;
        grand_total_l.setText("Grand Total: " + grand_total);
        round += 1;
        YahtzeeGui gui = new YahtzeeGui();
        gui.play();
        chance_u = true;
      }
    }
  }
  class yahtzee_bListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
    //check if the option has been used yet
      if(yahtzee_u == false || num_yahtzee > 0 && get_score.score("YAHTZEE!", dice_values, num_yahtzee) > 0)  {
        if(yahtzee == 0) {
          yahtzee = get_score.score("YAHTZEE!", dice_values, num_yahtzee);
        } else {
          yahtzee = yahtzee + get_score.score("YAHTZEE!", dice_values, num_yahtzee);
        }
        yahtzee_l.setText("Yahtzee: " + yahtzee);
        upper_total = ones + twos + threes + fours + fives + sixes;
        if(upper_total > 62) {
          bonus = 35;
          bonus_l.setText("Bonus: " + bonus);
        }
        upper_total = bonus + ones + twos + threes + fours + fives + sixes;
        upper_total_l.setText("Upper Total: " + upper_total);
        lower_total = three_kind + four_kind + sm_straight + lg_straight + full_house + chance + yahtzee;
        lower_total_l.setText("Lower Total: " + lower_total);
        grand_total = upper_total + lower_total;
        grand_total_l.setText("Grand Total: " + grand_total);
        num_yahtzee += 1;
        round += 1;
        YahtzeeGui gui = new YahtzeeGui();
        gui.play();
        yahtzee_u = true;
      }
    }
  }

  class end_game_bListener implements ActionListener{
    public void actionPerformed(ActionEvent event) {
      FinishGame over = new FinishGame();
      over.endGame();
    }
  }



//------------------FINISH THE GAME AND SHOW FINAL SCORE--------


  class FinishGame {
    public void endGame() {
      JPanel final_screen = new JPanel();
      JFrame base_end = new JFrame();
    //Set the frame to close and end the program when the 'X' is clicked
      base_end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Add scoresheet panel to frame
      base_end.setContentPane(final_screen);
      base_end.setSize(1375,875);
      base_end.setVisible(true);
      final_screen.setLayout(null);
      Font finalFont = new Font("times", Font.BOLD, 80);
      JLabel game_over_l = new JLabel("GAME OVER");
      game_over_l.setFont(finalFont);
      final_screen.add(game_over_l);
      game_over_l.setBounds(4,4, 1370, 400);
      JLabel final_score_l = new JLabel("FINAL SCORE: " + grand_total);
      final_score_l.setFont(finalFont);
      final_screen.add(final_score_l);
      final_score_l.setBounds(4, 408, 1370, 400);
    }
  }
}
