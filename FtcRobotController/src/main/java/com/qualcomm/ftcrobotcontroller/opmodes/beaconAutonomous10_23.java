package com.qualcomm.ftcrobotcontroller.opmodes;

public class beaconAutonomous10_23 extends Error404_Hardware_Tier2

{
  ///////////////////////////////////////////////////////////////////
  private int state = 0;
  private int encoder=0;
  public beaconAutonomous10_23()
  {
  }
  @Override public void start(){
    driveStright("RWOE", 0.0, "F", 0);
    resetAllEncoders_withWait();
    gyroCalibrate();
  }
  @Override public void loop ()
  {
    switch (state)
    {   case 0:
            resetAllEncoders_withWait();
      state++;
      break;
      case 1:
          driveStright("RUE",0.2,"r",0); //drive away from line

        if (is_encoder_reached(200, leftFront)) {

          state++;
          encoder=leftFront.getCurrentPosition();
        }
        break;
     case 2:
       set_power(0,rightFront);
       set_power(0,leftFront);
       set_power(0,rightRear);
       set_power(0,leftRear);
       state++;
        break;
      case 3:
        pointTurn("RUE",0.1,"r",0); //turn towards line
        if (gyro.getHeading()>24) {
          state++;
        }
        break;
      case 4:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        //resetAllEncoders_noWait();
        state++;
        encoder=leftFront.getCurrentPosition();
        break;
//    case 4:
//      resetAllEncoders_noWait();
//          state++;
//        break;
      case 5:
        driveStright("RUE",0.2,"r",0); //drive to line's general area
        if (is_encoder_reached((2500+encoder), leftFront)) {
          state++;
        }
        break;
      case 6:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        state++;
        break;
      case 7:
        //resetAllEncoders_noWait();
        state++;
        break;
      case 8:
        driveStright("RUE",0.1,"r",0); //drive until line is seen
        if(RGB.alpha()>15)
        {
        state++;
        }
        break;
      case 9:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        //resetAllEncoders_noWait();
        state++;
        break;
      case 10:
        driveStright("RUE",0,"f",0);
        state++;
        break;

      case 11:
        state++;
        break;

      case 12:
        state++;
        break;
      case 13:
          state++;
        break;
      case 14:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        //resetAllEncoders_noWait();
        state++;
        break;
      case 15:
        pointTurn("RUE",0.1,"r",0); //turn onto line
        if (gyro.getHeading()>80) {
          state++;
        }
        break;
      case 16:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        //resetAllEncoders_noWait();
        state++;
        break;
      case 17:
        driveStright("RUE",0,"f",0);
        state++;
        break;
      case 18:
        driveStright("RUE",0.1,"r",0);
//        if ((ODS.getLightDetected()*100)>50) {
        if(touch.isPressed()){
          state++;
        }
        break;
      case 19:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        //resetAllEncoders_noWait();
        state++;
        break;
      case 20:
        driveStright("RUE",0,"f",0);
        //resetAllEncoders_noWait();
        state++;
        encoder=leftFront.getCurrentPosition();
        break;
      case 21:
        driveStright("RUE",0.1,"f",0); //drive to line's general area
        if (is_encoder_reached((encoder+200), leftFront)) {
          state++;
        }
        break;
      case 22:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        //resetAllEncoders_noWait();
        state++;
        telemetry.addData("Just finished state 22","");
        break;
      case 23:
        telemetry.addData("Just started state 23","");
        slide_sideways("RUE",0,"l",0);
        encoder=leftFront.getCurrentPosition();
        telemetry.addData("Case 23 Encoder: ",encoder);

        //resetAllEncoders_withWait();
       state++;
        break;
      case 24:
        telemetry.addData("Just started state 24","");
        slide_sideways("RUE",0.1,"l",0); //drive to line's general area
        if (is_encoder_reached(encoder+200, leftFront)) {
          state++;
        }
        break;
      case 25:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        //resetAllEncoders_noWait();
        telemetry.addData("Case 25 Encoder: ",encoder);

        //state++;
        break;
//      case 26:
//        driveStright("RUE",0,"f",0);
//        encoder=leftFront.getCurrentPosition();
//        //resetAllEncoders_withWait();
//        state++;
//        break;
      default:
        break;


    }
    telemetry.addData("RightFront: ", get_position(rightFront));
    telemetry.addData("LeftFront: ", get_position(leftFront));
    telemetry.addData("RightRear: ", get_position(rightRear));
    telemetry.addData("LeftRear: ", get_position(leftRear));
    telemetry.addData("1. State: ", state);
    telemetry.addData("Alpha: ", RGB.alpha());
    telemetry.addData("Gyro: ", gyro.getHeading());

  } // loop



} //
