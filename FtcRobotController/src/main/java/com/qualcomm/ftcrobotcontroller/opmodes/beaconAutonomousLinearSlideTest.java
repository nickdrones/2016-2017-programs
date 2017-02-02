package com.qualcomm.ftcrobotcontroller.opmodes;

import static com.qualcomm.robotcore.hardware.Servo.Direction.FORWARD;

public class beaconAutonomousLinearSlideTest extends Error404_Hardware_Tier2

{
  ///////////////////////////////////////////////////////////////////
  private int state = 40;
  private int encoder=0;
  private int tempval=0;
  private int backupEncoder=0;
  private double powervalue;
  private int gyrovalatslow=0;
    private int gyroafterstraight;
  int zeroPoint=0;
    double rightVal=0.15;   // initial position of beacon pusher servos
    double leftVal=0.15;

    public beaconAutonomousLinearSlideTest()
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
     setServoPos(rightPush, rightVal);  // beacon pusher
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
        case 40: // blue case
            leftPush.setPosition(.5);
            state++;
            break;
        case 41: // red case    ///////////////////////////////////////////////
            rightPush.setPosition(.5);
            state=55;
            break;

        case 55:
            try {
                Thread.sleep(1500);                 //1.5 seconds
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            leftPush.setPosition(0.15);
            rightPush.setPosition(0.15);
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
