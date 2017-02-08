package com.qualcomm.ftcrobotcontroller.opmodes;

import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.BACK_UP_FROM_BEACON1;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.BACK_UP_FROM_BEACON2;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.CHECK_COLOR_LOGIC1;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.CHECK_COLOR_LOGIC2;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.DRIVE_QUICKLY_STRAIGHT_TO_LINE;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.DRIVE_SLOWLY_TILL_FIND_LINE;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.DRIVE_STRAIGHT_FROM_WALL;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.DRIVE_TILL_TOUCH;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.DRIVE_TILL_TOUCH2;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.DRIVE_TO_VORTEX;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.SLIDE_SLOWLY_TILL_LINE;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.SLIDE_TO_OTHER_BEACON;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.STOP1;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.STOP2;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.STOP3;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.STOP4;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.STOP_AFTER_BACKING_UP;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.STOP_FINAL;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.STOP_ON_LINE;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.STOP_ON_WALL;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.STOP_RESET_CAPTURE_POSITION;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.TURN_TOWARD_LINE;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.TURN_TOWARD_VORTEX;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.TURN_TOWARD_WALL;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.WAIT_FOR_PUSH1;
import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousREDLinearSlideShorterRevA.State.WAIT_FOR_PUSH2;


public class beaconAutonomousREDLinearSlideShorterRevA extends Error404_Hardware_Tier2

{
  ///////////////////////////////////////////////////////////////////
    public enum State {
      DRIVE_STRAIGHT_FROM_WALL,
      STOP1,
      TURN_TOWARD_LINE,
      STOP2,
      DRIVE_QUICKLY_STRAIGHT_TO_LINE,
      DRIVE_SLOWLY_TILL_FIND_LINE,
      STOP3,
      TURN_TOWARD_WALL,
      STOP4,
      DRIVE_TILL_TOUCH,
      STOP_RESET_CAPTURE_POSITION,
      CHECK_COLOR_LOGIC1,
      WAIT_FOR_PUSH1,
      BACK_UP_FROM_BEACON1,
      STOP_AFTER_BACKING_UP,
      SLIDE_TO_OTHER_BEACON,
      SLIDE_SLOWLY_TILL_LINE,
      STOP_ON_LINE,
      DRIVE_TILL_TOUCH2,
      STOP_ON_WALL,
      CHECK_COLOR_LOGIC2,
      WAIT_FOR_PUSH2,
      BACK_UP_FROM_BEACON2,
      TURN_TOWARD_VORTEX,
      DRIVE_TO_VORTEX, STOP_FINAL,

  }

    private State state = DRIVE_STRAIGHT_FROM_WALL;
    private int encoder=0;
    private int tempval=0;
    private int backupEncoder=0;
    private double powervalue;
    private int gyrovalatslow=0;
    private int gyroafterstraight;
    int zeroPoint=0;
    double rightVal=0.2;
    double leftVal=0.15;

    public beaconAutonomousREDLinearSlideShorterRevA()
  {
  }
   @Override public void init(){
       super.init();
       RGB.enableLed(true); //not sure why these are needed here.  Seems to help reset the LEDS so the next enable commands are obeyed.
       telemetry.addData("Out Red: ", beacon.red());
       telemetry.addData("Out Blue: ", beacon.blue());
       telemetry.addData("Down White: ", RGB.alpha());

       gyroCalibrate();
       telemetry.addData("Gyro: ", gyro.getHeading());
       telemetry.addData("","V 5");
       if(touch.isPressed()){
           telemetry.addData("Touch 1 is pressed","");
       }
       if(touch2.isPressed()){
           telemetry.addData("Touch 2 is pressed","");
       }
       setServoPos(rightPush, rightVal);  // nitialize servos to initial positions
       setServoPos(leftPush, leftVal);

   }
  @Override public void start(){
      driveStright("RWOE", 0.0, "F", 0);
      resetAllEncoders_withWait();
     RGB.enableLed(false); //not sure why these are needed here.  Seems to help reset the LEDS so the next enable commands are obeyed.
      beacon.enableLed(false);
  }


  @Override public void loop ()
  {
    RGB.enableLed(true);
    beacon.enableLed(false);
    switch (state)
    {
     case DRIVE_STRAIGHT_FROM_WALL:
         driveStright("RUE",0.2,"r",0);
        if (is_encoder_reached(200, leftFront)) {
          state=STOP1;
         }
        break;
      case STOP1:
         left_set_power(0.0);
         right_set_power(0.0);
         state=TURN_TOWARD_LINE;
         break;
       case TURN_TOWARD_LINE:
           pointTurn("RUE",0.1,"l",0); //turn towards line
           if (gyro.getHeading()<336 && gyro.getHeading()>180) {   //the <180 is to compensate if the robot turns slightly to the left
               state=STOP2;
           }
         break;
      case STOP2:
          left_set_power(0.0);
          right_set_power(0.0);
          driveStright("RUE",0,"r",0);
          encoder=leftFront.getCurrentPosition();
          state=DRIVE_QUICKLY_STRAIGHT_TO_LINE;
          break;
      case DRIVE_QUICKLY_STRAIGHT_TO_LINE:
          driveStright("RUE",0.4,"r",0);
         if (is_encoder_reached((2200+encoder), leftFront)) {
          state=DRIVE_SLOWLY_TILL_FIND_LINE;
         }
         break;
      case DRIVE_SLOWLY_TILL_FIND_LINE:
        driveStright("RUE",0.1,"r",0);
        if(RGB.alpha()>5)
        {
        state=STOP3;
        }
        break;
      case STOP3:
          left_set_power(0.0);
          right_set_power(0.0);
           state = TURN_TOWARD_WALL;
           break;
      case TURN_TOWARD_WALL:
          pointTurn("RUE",0.1,"l",0); //turn onto line
          if (gyro.getHeading()<280 && gyro.getHeading()>180) {
              state=STOP4;
          }
         break;
        case STOP4:
          left_set_power(0.0);
          right_set_power(0.0);
          state=DRIVE_TILL_TOUCH;
          break;
      case DRIVE_TILL_TOUCH:  //drive till both touch sensors pressed on wall
        driveStright("RUE",0.2,"r",0);
        if(touch.isPressed()&&touch2.isPressed()){
          state=STOP_RESET_CAPTURE_POSITION;
        }
        break;
      case STOP_RESET_CAPTURE_POSITION:
          left_set_power(0.0);
          right_set_power(0.0);
          zeroPoint = gyro.getHeading();  //capture gyro reading for gyro correction after backing away from beacon
          driveStright("RUE",0,"f",0); // needed cause if change direction of the motors, the encoder readings also change to correspond with the new direction.
          state=CHECK_COLOR_LOGIC1;
          encoder=leftFront.getCurrentPosition();
          break;
        case CHECK_COLOR_LOGIC1:  //assumes color sensor is on right pusher
            if(beacon.blue()>beacon.red())
            {
              telemetry.addData("It's BLUE!!!","");
              rightPush.setPosition(.5);
                state=WAIT_FOR_PUSH1;
            }
            else if(beacon.red()>beacon.blue())
            {
              telemetry.addData("It's RED!!!","");
              leftPush.setPosition(.45);
                state=WAIT_FOR_PUSH1;
            }
             else
            {
                state= WAIT_FOR_PUSH1;
            }  //if see no beacon color, then just stop program
             break;
        case WAIT_FOR_PUSH1: //wait one sec for pushers to finish moving and pressing button
            try {
                Thread.sleep(900);                 // one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            state=BACK_UP_FROM_BEACON1;
            break;
        case BACK_UP_FROM_BEACON1: // back away from beacon slightly and retract beacon pushers
             driveStright("RUE",0.25,"f",0);
            rightPush.setPosition(0.2);
             leftPush.setPosition(0.15);
             if (is_encoder_reached(encoder+450, leftFront)) {
              state=STOP_AFTER_BACKING_UP;
            }
            break;
        case STOP_AFTER_BACKING_UP:
            left_set_power(0.0);
            right_set_power(0.0);
            slide_sideways("RUE",0,"l",0);
            encoder=leftFront.getCurrentPosition();
            state=SLIDE_TO_OTHER_BEACON;
            break;
        case SLIDE_TO_OTHER_BEACON:
            slide_sideways("RUE",0.5,"l",0);
            if (is_encoder_reached(encoder+2000, leftFront)) {
                state=SLIDE_SLOWLY_TILL_LINE;
            }
            break;
        case SLIDE_SLOWLY_TILL_LINE:
            slide_sideways("RUE",0.1,"l",0);
            if (RGB.alpha()>4) {
                state=STOP_ON_LINE;
            }
            break;
        case STOP_ON_LINE:
            left_set_power(0.0);
            right_set_power(0.0);
            state=DRIVE_TILL_TOUCH2;
            break;
        case DRIVE_TILL_TOUCH2:            // drive straight till both touch sensors see the wall
            driveStright("RUE",0.2,"r",0);
            if(touch.isPressed() && touch2.isPressed()){
                state=STOP_ON_WALL;
            }
            break;
        case STOP_ON_WALL:
            left_set_power(0.0);
            right_set_power(0.0);
            state=CHECK_COLOR_LOGIC2;
            driveStright("RUE",0,"f",0);
            encoder=leftFront.getCurrentPosition();
            break;
        case CHECK_COLOR_LOGIC2://assumes color sensor is on right pusher
            if(beacon.blue()>beacon.red())
            {
                telemetry.addData("It's BLUE!!!","");
                rightPush.setPosition(.5);
            }
            else if(beacon.red()>beacon.blue())
            {
                telemetry.addData("It's RED!!!","");
                leftPush.setPosition(.45);
            }
            state=WAIT_FOR_PUSH2;
            break;
        case WAIT_FOR_PUSH2: //wait one sec for pushers to finish moving and pressing button
            try {
                Thread.sleep(900);                 // one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            state=BACK_UP_FROM_BEACON2;
            break;
        case BACK_UP_FROM_BEACON2: // back away from beacon slightly and retract beacon pushers
            driveStright("RUE",0.25,"f",0);
            rightPush.setPosition(0.15);
            leftPush.setPosition(0.15);
            if (is_encoder_reached(encoder+450, leftFront)) {
              state=TURN_TOWARD_VORTEX;
            }
            break;
       case TURN_TOWARD_VORTEX:              // turn slightly to face center vortex
           pointTurn("RUE",0.2,"r",0);
           if (gyro.getHeading()>300 && gyro.getHeading()<360) {
               state=DRIVE_TO_VORTEX;
           }
           driveStright("RUE",0,"f",0);
           encoder=leftFront.getCurrentPosition();
            break;
       case DRIVE_TO_VORTEX:
            driveStright("RUE",0.3,"f",0); //drive to line's general area
            if (is_encoder_reached(encoder+2500, leftFront)) {
                state=STOP_FINAL;
            }
            break;
        case STOP_FINAL:
            left_set_power(0.0);
            right_set_power(0.0);
            break;

        default:
            break;


    }
    telemetry.addData("RightFront: ", get_position(rightFront));
    telemetry.addData("Zero point: ", zeroPoint);
    telemetry.addData("LeftFront: ", get_position(leftFront));
    telemetry.addData("RightRear: ", get_position(rightRear));
    telemetry.addData("LeftRear: ", get_position(leftRear));
    telemetry.addData("1. State: ", state);
    telemetry.addData("2. White down: ", RGB.alpha());
    telemetry.addData("3. Red out: ", beacon.red());
    telemetry.addData("3. Blue out: ", beacon.blue());
    telemetry.addData("Gyro: ", gyro.getHeading());
    telemetry.addData("left: ", leftPush.getPosition());
    telemetry.addData("right: ", rightPush.getPosition());
    telemetry.addData("right power: ", rightFront.getPower());
    telemetry.addData("left power: ", leftFront.getPower());

  } // loop
} //