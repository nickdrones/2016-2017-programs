package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;


import static com.qualcomm.ftcrobotcontroller.opmodes.beaconAutonomousBLUELinearSlideShorterRevA2_7.State.SLIDE_SLOWLY_TILL_LINE;



public class slideSidewayGyroTesting extends Error404_Hardware_Tier2

{
  ///////////////////////////////////////////////////////////////////
    public enum State {
      SLIDE_SLOWLY_TILL_LINE,

  }

    private State state = State.SLIDE_SLOWLY_TILL_LINE;
    private int encoder=0;
    private int tempval=0;
    private int backupEncoder=0;
    private double powervalue;
    private int gyrovalatslow=0;
    private int gyroafterstraight;
    int zeroPoint=0;
    double rightVal=0.15;
    double leftVal=0.15;

    public slideSidewayGyroTesting()
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
       telemetry.addData("","V 6");

       setServoPos(rightPush, rightVal);  // initialize servos to initial positions
       setServoPos(leftPush, leftVal);

   }
  @Override public void start(){
      driveStright("RWOE", 0.0, "F", 0);
      resetAllEncoders_withWait();
     RGB.enableLed(false); //not sure why these are needed here.  Seems to help reset the LEDS so the next enable commands are obeyed.
      beacon.enableLed(false);
      beacon2.enableLed(false);
      int zeropoint=gyro.getHeading();
  }


  @Override public void loop ()
  {
    RGB.enableLed(true);
    beacon.enableLed(false);
    beacon2.enableLed(false);
      float[] hsvValues2 = {0F, 0F, 0F};
      Color.RGBToHSV(beacon2.red()*8, beacon2.green()*8, beacon2.blue()*8, hsvValues2);

      float[] hsvValues = {0F, 0F, 0F};
      Color.RGBToHSV(beacon.red()*8, beacon.green()*8, beacon.blue()*8, hsvValues);

      switch (state) {
          case SLIDE_SLOWLY_TILL_LINE:
            slide_sideways_gyro("RUE", 0.2, "r", zeroPoint);
            if (leftFront.getCurrentPosition()>3000) {
                left_set_power(0.0);
                right_set_power(0.0);
            }
            break;
        default:
            break;
    }
    //telemetry.addData("RightFront: ", get_position(rightFront));
   // telemetry.addData("2. HUE Right: ", hsvValues[0]);
    //telemetry.addData("3. HUE Left: ", hsvValues2[0]);
      //telemetry.addData("4. Red Left: ", beacon2.red());
      //telemetry.addData("6. Blue Left: ", beacon2.blue());
   // telemetry.addData("Zero point: ", zeroPoint);
    //telemetry.addData("LeftFront: ", get_position(leftFront));
    //telemetry.addData("RightRear: ", get_position(rightRear));
    //telemetry.addData("LeftRear: ", get_position(leftRear));
    telemetry.addData("2.          Hue   Red   Blue", "");
    telemetry.addData("3. right  "+ hsvValues[0] +"   "+ beacon.red() +"   "+ beacon.blue(), "");
    telemetry.addData("4. left   "+ hsvValues2[0] +"   "+ beacon2.red() +"   "+ beacon2.blue(), "");

    telemetry.addData("7. State: ", state);
    //telemetry.addData("7. White down: ", RGB.alpha());
    //telemetry.addData("5. Red Right: ", beacon.red());
    //telemetry.addData("7. Blue Right: ", beacon.blue());
    telemetry.addData("1. Gyro: ", gyro.getHeading());
   // telemetry.addData("left: ", leftPush.getPosition());
    //telemetry.addData("right: ", rightPush.getPosition());
    //telemetry.addData("right power: ", rightFront.getPower());
    //telemetry.addData("left power: ", leftFront.getPower());
  }
}