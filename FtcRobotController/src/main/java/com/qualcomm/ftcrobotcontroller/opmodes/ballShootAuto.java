package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

public class ballShootAuto extends Error404_Hardware_Tier2

{
  ///////////////////////////////////////////////////////////////////
  private int state = 0;
  private int encoder=0;
  private int test=0;
  public ballShootAuto()
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
    {
      case 0:
          balllauncher.setPower(-0.7);
          ballCollector.setPower(1);
          break;
        case 1:
            set_power(0,leftFront);
            set_power(0,leftRear);
            state++;
            break;
        case 2:
            balllauncher.setPower(0);
            ballCollector.setPower(0);
            leftFront.setDirection(DcMotor.Direction.REVERSE);
            leftRear.setDirection(DcMotor.Direction.REVERSE);
            encoder=leftFront.getCurrentPosition();
            state=9;
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
