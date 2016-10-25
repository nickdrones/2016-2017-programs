package com.qualcomm.ftcrobotcontroller.opmodes;

public class beaconAutonomous10_23 extends Error404_Hardware_Tier2

{
  private int state = 0;
  public beaconAutonomous10_23()
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
          driveStright("RUE",0.2,"r",0);
       // slide_sideways("RUE",0.5,"R",0);
        if (is_encoder_reached(2500, leftFront)) {
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
    case 3:
      resetAllEncoders_noWait();
//        pointTurn("RUE", 1.0, "L", 0);
//        if (is_encoder_reached(5000, rightFront))
//        {
          state++;
//        }
        break;
      case 4:
        driveStright("RUE",0.1,"r",0);
        if(RGB.alpha()>15)
        {
        state++;
        }
        break;
      case 5:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        resetAllEncoders_noWait();
        state++;
        break;
      case 6:
        driveStright("RUE",0,"f",0);
        state++;
        break;

      case 7:
        state++;
        break;

      case 8:
        state++;
        break;
      case 9:
//        driveStright("RUE",0.1,"f",0);
//        if (is_encoder_reached(300, leftFront)) {
          state++;
        //}
        break;
      case 10:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        resetAllEncoders_noWait();
        state++;
        break;
      case 11:
        pointTurn("RUE",0.1,"r",0);
        if (is_encoder_reached(600, leftFront)) {
          state++;
        }
        break;
      case 12:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        resetAllEncoders_noWait();
        state++;
        break;
      case 13:
        driveStright("RUE",0,"f",0);
        state++;
        break;
      case 14:
        driveStright("RUE",0.1,"r",0);
        if ((ODS.getLightDetected()*100)>50) {
          state++;
        }
        break;
      case 15:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        resetAllEncoders_noWait();
        state++;
        break;
      case 16:
        driveStright("RUE",0,"f",0);
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
    telemetry.addData("ALpha: ", RGB.alpha());

  } // loop



} //
