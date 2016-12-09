package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

public class rampStrafeShootAuto extends Error404_Hardware_Tier2

{
  ///////////////////////////////////////////////////////////////////
  private int state = 0;
  private int encoder=0;
  private int test=0;
  public rampStrafeShootAuto()
  {
  }
   @Override public void init(){
    super.init();
    telemetry.addData("Out Red: ", beacon.red());
    telemetry.addData("Out Blue: ", beacon.blue());
    gyroCalibrate();
    telemetry.addData("Gyro: ", gyro.getHeading());
    telemetry.addData("","V 3");

   }
  @Override public void start(){
    driveStright("RWOE", 0.0, "F", 0);
    resetAllEncoders_withWait();
    //gyroCalibrate();
    RGB.enableLed(false); //not sure why these are needed here.  Seems to help reset the LEDS so the next enable commands are obeyed.
    beacon.enableLed(false);


  }
  @Override public void loop ()
  {
   // RGB.enableLed(true);
    //beacon.enableLed(false);
    switch (state)
    {   case 0:
            resetAllEncoders_withWait();
      state++;
      break;
      case 1:
          slide_sideways("RUE",0.2,"l",0); //drive away from line

        if (is_encoder_reached(1650, leftFront)) {
          state++;
        }
        break;
     case 2:
       set_power(0,rightFront);
       set_power(0,leftFront);
       set_power(0,rightRear);
       set_power(0,leftRear);
       driveStright("RUE",0,"f",0); //drive to line's general area
       encoder=leftFront.getCurrentPosition();
       state=5;
        break;
      case 5:
        driveStright("RUE",0.4,"f",0); //drive to line's general area
        if (is_encoder_reached((1000+encoder), leftFront)) {
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
          balllauncher.setPower(-0.7);
          ballCollector.setPower(1);
          if(test==200){
              state++;
          }
          test++;
          break;
        case 8:
            balllauncher.setPower(0);
            ballCollector.setPower(0);
            leftFront.setDirection(DcMotor.Direction.REVERSE);
            leftRear.setDirection(DcMotor.Direction.REVERSE);
            encoder=leftFront.getCurrentPosition();
            state=9;
            break;
      case 9:
          leftFront.setPower(0.1);
          leftRear.setPower(0.1);
          if (is_encoder_reached((300+encoder), leftFront)) {
              state++;
          }
        break;
        case 10:
            set_power(0,leftFront);
            set_power(0,leftRear);
            state++;
            break;
        case 11:
            driveStright("RUE",0,"f",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 12:
            driveStright("RUE",0.1,"f",0); //drive to line's general area
            if (is_encoder_reached((400+encoder), leftFront)) {
                state++;
            }
            break;
        case 13:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            break;
        default:
            break;


    }
    //telemetry.addData("RightFront: ", get_position(rightFront));
    //telemetry.addData("LeftFront: ", get_position(leftFront));
    //telemetry.addData("RightRear: ", get_position(rightRear));
    //telemetry.addData("LeftRear: ", get_position(leftRear));
    telemetry.addData("1. State: ", state);
    telemetry.addData("2. loops: ", test);
    //telemetry.addData("3. Red out: ", beacon.red());
    //telemetry.addData("3. Blue out: ", beacon.blue());
    //telemetry.addData("Gyro: ", gyro.getHeading());
  } // loop
} //
