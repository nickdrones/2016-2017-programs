package com.qualcomm.ftcrobotcontroller.opmodes;

public class meccanumStateMachine10_19_16 extends Error404_Hardware_Tier2

{
  private int state = 0;
  public meccanumStateMachine10_19_16()
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
      try {
        Thread.sleep(1000);                 //1000 milliseconds is one second.
      } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      state++;
      break;
      case 1:

        slide_sideways("RUE",0.5,"R",0);
        if (is_encoder_reached(5000, leftFront)) {
          state++;
        }
        break;
     case 2:
       set_power(0,rightFront);
       set_power(0,leftFront);
       set_power(0,rightRear);
       set_power(0,leftRear);
        break;
    case 3:
//        pointTurn("RUE", 1.0, "L", 0);
//        if (is_encoder_reached(5000, rightFront))
//        {
//          state++;
//        }
        break;
      case 4:
//        resetAllEncoders_noWait();
//        if(is_encoder_reset(leftFront)&&is_encoder_reset(rightFront))
//          state++;
        break;
      case 5:
//        state++;
        break;
      default:
        break;


    }
    telemetry.addData("RightFront: ", get_position(rightFront));
    telemetry.addData("LeftFront: ", get_position(leftFront));
    telemetry.addData("RightRear: ", get_position(rightRear));
    telemetry.addData("LeftRear: ", get_position(leftRear));
    telemetry.addData("State: ", state);

  } // loop



} //
