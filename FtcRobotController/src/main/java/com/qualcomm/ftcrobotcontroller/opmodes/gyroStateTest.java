package com.qualcomm.ftcrobotcontroller.opmodes;

public class gyroStateTest extends Error404_Hardware_Tier2

{
  private int state = 0;
    double rightVal=0.2;
    double leftVal=0.15;

    public gyroStateTest()
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
    switch (state)
    {
     case 0:
        state++;
        break;
      case 1:
          turn_gyro_power(28, 0.1, 0.6, "r");
         if (gyro.getHeading()>27 && gyro.getHeading()<180) {   //the <180 is to compensate if the robot turns slightly to the left
          state++;
         }
         break;

        default:
            break;
    }
    telemetry.addData("Gyro: ", gyro.getHeading());
    telemetry.addData("Case: ", state);

  } // loop
} //