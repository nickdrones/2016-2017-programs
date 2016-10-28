package com.qualcomm.ftcrobotcontroller.opmodes;

public class strafeSidewaysTestStateMachine extends Error404_Hardware_Tier2

{
  @Override public void init()
  {
    super.init();
    double before=gyro.getHeading();

    gyro.calibrate();
    //while (gyro.isCalibrating()) telemetry.addData("Gyro: ", gyro.getHeading());
    while(gyro.getHeading()!=0)
    {
      telemetry.addData("Gyro: ", gyro.getHeading());
    }
    telemetry.addData("Gyro Calibrated","");
    telemetry.addData("Before: ",before);
    telemetry.addData("After: ", gyro.getHeading());
  }
  ///////////////////////////////////////////////////////////////////
  private int state = 0;
  public strafeSidewaysTestStateMachine()
  {
  }
  @Override public void start(){
    driveStright("RWOE", 0.0, "F", 0);
    resetAllEncoders_withWait();
  }
  @Override public void loop ()
  {
    switch (state)
    {   case 0:
            resetAllEncoders_withWait();
      state++;
      break;
      case 1:
          slide_sideways_gyro("RUE",0.1,"l",0); //drive away from line
        if (is_encoder_reached(2000, leftFront)) {
          state++;
        }
        break;
     case 2:
       set_power(0,rightFront);
       set_power(0,leftFront);
       set_power(0,rightRear);
       set_power(0,leftRear);
       state++;
        break;
      default:
        break;


    }
    telemetry.addData("RightFront: ", get_position(rightFront));
    telemetry.addData("LeftFront: ", get_position(leftFront));
    telemetry.addData("RightRear: ", get_position(rightRear));
    telemetry.addData("LeftRear: ", get_position(leftRear));
    telemetry.addData("State: ", state);
    telemetry.addData("Alpha: ", RGB.alpha());
    telemetry.addData("Gyro: ", gyro.getHeading());
    telemetry.addData("right rear power: ",rightRear.getPower());
    telemetry.addData("right front power: ",rightFront.getPower());
  } // loop



} //
