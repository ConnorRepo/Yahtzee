//This class returns a random number between 1 and 6 which indicates the roll of a dice

public class Roll_Dice {
  public int roll(){
  //Declare the variable which will store the value of the roll
  //Initialize the variable with a value between 1 and 6
    int num = (int) (Math.random() * 7);
  //test if the number is between 1 and 6
    while (num < 1){
    //find a new value
      num = (int) (Math.random() * 7);
    }
  //return the verified number
    return num;
  }
}
