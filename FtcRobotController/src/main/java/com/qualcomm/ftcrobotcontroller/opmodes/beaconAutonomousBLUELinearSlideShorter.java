package com.qualcomm.ftcrobotcontroller.opmodes;

public class beaconAutonomousBLUELinearSlideShorter extends Error404_Hardware_Tier2

{
  ///////////////////////////////////////////////////////////////////
  private int state = 0;
  private int encoder=0;
  private int tempval=0;
  private int backupEncoder=0;
  private double powervalue;
  private int gyrovalatslow=0;
    private int gyroafterstraight;
  int zeroPoint=0;
    double rightVal=0.2;
    double leftVal=0.15;

    public beaconAutonomousBLUELinearSlideShorter()
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
     case 0:
        state++;
        break;
      case 1:  // Drive straight away from wall
         driveStright("RUE",0.2,"r",0);
        if (is_encoder_reached(200, leftFront)) {
          state++;
         }
        break;
     case 2:  // stop motors
         left_set_power(0.0);
         right_set_power(0.0);
         state++;
         break;
      case 3:  //turn about 28 degrees to head to line
          turn_gyro_power(28, 0.1, 0.6, "r");
         if (gyro.getHeading()>27 && gyro.getHeading()<180) {   //the <180 is to compensate if the robot turns slightly to the left
          state++;
         }
         break;
      case 4: // stop motors
          left_set_power(0.0);
          right_set_power(0.0);
          driveStright("RUE",0,"r",0);
          encoder=leftFront.getCurrentPosition();
          state++;
          break;
      case 5:
          driveStright("RUE",0.4,"r",0); //drive to line's general area
         if (is_encoder_reached((2200+encoder), leftFront)) {
          state=8;
         }
         break;
      case 8://drive slowly until line is seen
        driveStright("RUE",0.1,"r",0);
        if(RGB.alpha()>5)
        {
        state=11;
        }
        break;
      case 11:     // stop on line
          left_set_power(0.0);
          right_set_power(0.0);
           state++;
           break;
      case 12:
          pointTurn("RUE",0.1,"r",0); //turn onto line
          if (gyro.getHeading()>80) {
              state++;
          }
         break;
      case 13:
          left_set_power(0.0);
          right_set_power(0.0);
          state=18;
          break;
      case 18:  //drive till both touch sensors pressed on wall
        driveStright("RUE",0.2,"r",0);
        if(touch.isPressed()&&touch2.isPressed()){
          state++;
        }
        break;
      case 19:
          left_set_power(0.0);
          right_set_power(0.0);
          zeroPoint = gyro.getHeading();  //capture gyro reading for gyro correction after backing away from beacon
          driveStright("RUE",0,"f",0); // this needs to be here because if we change direction of the motors, the encoder readings also change to correspond with the new direction.
          encoder=leftFront.getCurrentPosition();
          state=38;
          break;
       case 38:  //assumes color sensor is on right pusher
            if(beacon.blue()>beacon.red())
            {
              telemetry.addData("It's BLUE!!!","");
              leftPush.setPosition(.5);
                state=55;
            }
            else if(beacon.red()>beacon.blue())
            {
              telemetry.addData("It's RED!!!","");
              rightPush.setPosition(.45);
                state=55;
            }
             else
            {
                state= 55;
            };  //if see no beacon color, then just stop program
             break;
        case 55: //wait one sec for pushers to finish moving and pressing button
            try {
                Thread.sleep(900);                 // one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            state++;
            break;
         case 56: // back away from beacon slightly and retract beacon pushers
             driveStright("RUE",0.25,"f",0);
            rightPush.setPosition(0.2);
             leftPush.setPosition(0.15);
             if (is_encoder_reached(encoder+450, leftFront)) {
              state = 60;
            }
            break;
        case 60:
            left_set_power(0.0);
            right_set_power(0.0);
            slide_sideways("RUE",0,"r",0);
            encoder=leftFront.getCurrentPosition();
            state=65;
            break;
        case 65:
            slide_sideways("RUE",0.5,"r",0); //drive to general area of second beacon line
            if (is_encoder_reached(encoder+2000, leftFront)) {
                state=67;
            }
            break;
        case 67:
            slide_sideways("RUE",0.1,"r",0); //drive slowly till see line
            if (RGB.alpha()>4) {
                state++;
            }
            break;
        case 68:        //stop on line
            left_set_power(0.0);
            right_set_power(0.0);
            state=73;
            break;
        case 73:            // drive straight till both touch sensors see the wall
            driveStright("RUE",0.2,"r",0);
            if(touch.isPressed() && touch2.isPressed()){
                state++;
            }
            break;
        case 74:
            left_set_power(0.0);
            right_set_power(0.0);
            driveStright("RUE",0,"f",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 75://assumes color sensor is on right pusher
            if(beacon.blue()>beacon.red())
            {
                telemetry.addData("It's BLUE!!!","");
                leftPush.setPosition(.5);
                state=76;
            }
            else if(beacon.red()>beacon.blue())
            {
                telemetry.addData("It's RED!!!","");
                rightPush.setPosition(.45);
                state=76;
            }
            else
            {
                state=76;
            };  //if see no beacon color, then just stop program
            break;
        case 76: //wait one sec for pushers to finish moving and pressing button
            try {
                Thread.sleep(900);                 // one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            state++;
            break;
        case 77: // back away from beacon slightly and retract beacon pushers
            driveStright("RUE",0.25,"f",0);
            rightPush.setPosition(0.15);
            leftPush.setPosition(0.15);
            if (is_encoder_reached(encoder+450, leftFront)) {
              state = 100;
            }
            break;
       case 100:              // turn slightly to face center vortex
            pointTurn("RUE",0.2,"l",0);
            if (gyro.getHeading()<60 && gyro.getHeading()>1) {
                state=105;
            }
            break;
       case 105:
            driveStright("RUE",0.3,"f",0); //drive to line's general area
            if (is_encoder_reached(encoder+2500, leftFront)) {
                state++;
            }
            break;
        case 106:
            left_set_power(0.0);
            right_set_power(0.0);
            state++;
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

  } // loop
} //
