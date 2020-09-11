//This class takes a String instuction and an integer array and then returns the appropriate score

import java.util.*;

public class Score_Keeper{
  public int score(String inst, int[] dice, int yaht){
  //Declare and Initialize variables
    int score_num = 0;    //Score keeping variable
  //Sort the dice array in numerical order
    Arrays.sort(dice);
  //Sum of 1's
    if(inst.equals("ones")){
      for(int x = 0; x < 5; x++){
        if(dice[x] == 1){
          score_num++;
        }
      }
    }
  //Sum of 2's
    else if(inst.equals("twos")){
      for(int x = 0; x < 5; x++){
        if(dice[x] == 2){
          score_num = score_num + 2;
        }
      }
    }
  //Sum of 3's
    else if(inst.equals("threes")){
      for(int x = 0; x < 5; x++){
        if(dice[x] == 3){
          score_num = score_num + 3;
        }
      }
    }
  //Sum of 4's
    else if(inst.equals("fours")){
      for(int x = 0; x < 5; x++){
        if(dice[x] == 4){
          score_num = score_num + 4;
        }
      }
    }
  //Sum of 5's
    else if(inst.equals("fives")){
      for(int x = 0; x < 5; x++){
        if(dice[x] == 5){
          score_num = score_num + 5;
        }
      }
    }
  //Sum of 6's
    else if(inst.equals("sixes")){
      for(int x = 0; x < 5; x++){
        if(dice[x] == 6){
          score_num = score_num + 6;
        }
      }
    }
  //Three of a kind
    else if(inst.equals("3 of a kind")){
      for(int x = 0; x < 3; x++){
      //verify that the roll is in fact a three of a kind
        if(dice[x] == dice[x + 1] && dice[x] == dice[x + 2]){
          for(int y = 0; y < 5; y++){
            score_num = score_num + dice[y];
          }
        }
      }
    }
  //Four of a kind
    else if(inst.equals("4 of a kind")){
    //verify that the roll is in fact a four of a kind
      for(int x = 0; x < 2; x++){
        if(dice[x] == dice[x + 1] && dice[x] == dice[x + 2] && dice[x] == dice[x + 3]){
          for(int y = 0; y < 5; y++){
            score_num = score_num + dice[y];
          }
        }
      }
    }
  //Full House
    else if(inst.equals("full house")){
    //verify that the roll is in fact a full house
      if(dice[0] == dice[1] && dice[0] == dice[2] && dice[3] == dice[4] || dice[0] == dice[1] && dice[2] == dice[3] && dice[2] == dice[4]){
        score_num = 25;
      }
    }
  //Sm. Straight
    else if(inst.equals("sm straight")){
      if(dice[0] == dice[1] - 1 && dice[0] == dice[2] - 2 && dice[0] == dice[3] - 3 || dice[0] == dice[1] && dice[0] == dice[2] - 1 && dice[0] == dice[3] - 2 && dice[0] == dice[4] - 3 || dice[0] == dice[1] - 1 && dice[1] == dice[2] && dice[0] == dice[3] - 2 && dice[0] == dice[4] - 3  || dice[0] == dice[1] - 1 && dice[0] == dice[2] - 2 && dice[2] == dice[3] && dice[0] == dice[4] - 3 || dice[1] == dice[2] - 1 && dice[1] == dice[3] - 2 && dice[1] == dice[4] - 3 ){
        score_num = 30;
      }
    }
  //Lg. Straight
    else if(inst.equals("lg straight")){
    //verify that the roll is in fact a large straight
      if(dice[0] == dice[1] - 1 && dice[0] == dice[2] - 2 && dice[0] == dice[3] - 3 && dice[0] == dice[4] - 4){
        score_num = 40;
      }
    }
  //YAHTZEE!
    else if(inst.equals("YAHTZEE!")){
    //verify that the roll is in fact a yahtzee
      if(dice[0] == dice[1] && dice[0] == dice[2] && dice[0] == dice[3] && dice[0] == dice[4]){
      //check if it is their first yatzee
        if(yaht == 0){
          score_num = 50;
        } else {
          score_num = 100;
        }
      }
    }
  //Chance
    else if(inst.equals("chance")){
    //verify that the roll is in fact a four of a kind
      for(int x = 0; x < 5; x++){
        score_num = score_num + dice[x];
      }
    }
  //Invalid input
    else {
      score_num = -1;
    }
//return the calculated score
  return score_num;
  }
}
