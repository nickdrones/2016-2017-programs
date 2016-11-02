package com.qualcomm.ftcrobotcontroller.opmodes;

public class beaconAutonomous10_31 extends Error404_Hardware_Tier2

{
  ///////////////////////////////////////////////////////////////////
  private int state = 0;
  private int encoder=0;
  private int test=0;
  public beaconAutonomous10_31()
  {
  }
  @Override public void start(){
    driveStright("RWOE", 0.0, "F", 0);
    resetAllEncoders_withWait();
    gyroCalibrate();
    RGB.enableLed(false); //not sure why these are needed here.  Seems to help reset the LEDS so the next enable commands are obeyed.
    beacon.enableLed(false);

  }
  @Override public void loop ()
  {
    RGB.enableLed(true);
    beacon.enableLed(false);
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
        break;
      case 23:
        slide_sideways("RUE",0,"l",0);
        encoder=leftFront.getCurrentPosition();
        //resetAllEncoders_withWait();
       state++;
        break;
      case 24:
        slide_sideways("RUE",0.1,"l",0); //drive to line's general area
        if (is_encoder_reached(encoder+350, leftFront)) {
          state++;
        }
        break;
      case 25:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        //resetAllEncoders_noWait();
        state++;
     case 26:
        driveStright("RUE",0,"r",0);
        encoder=leftFront.getCurrentPosition();
//        //resetAllEncoders_withWait();
        state++;
        break;
      case 27:
//        driveStright("RUE",0.05,"l",0); //drive to line's general area
//        if (is_encoder_reached(encoder+100, leftFront)) {
        state++;
        break;
      case 28:
//        if(beacon.red()>beacon.blue())
//        {

          driveStright("RUE",0.05,"r",0); //drive to line's general area
          if (is_encoder_reached(encoder+100, leftFront)||touch.isPressed()) {
            state=29;
//          }
//        }
//        else if(beacon.blue()>beacon.red())
//        {
//          driveStright("RUE",0.05,"r",0); //drive to line's general area
//          if (is_encoder_reached(encoder+100, leftFront)||touch.isPressed()) {
//            state=31;
//          }
        }
        break;
        case 29: //red case
          state=31;
        break;
//      case 30: //blue case
//        try {
//          Thread.sleep(5000);                 //1000 milliseconds is one second.
//        } catch(InterruptedException ex) {
//          Thread.currentThread().interrupt();
//        }
//        state=34;
//        break;
      case 31:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        //resetAllEncoders_noWait();
        state++;
        break;
      case 32:
        driveStright("RUE",0,"f",0);
        encoder=leftFront.getCurrentPosition();
        state++;
        break;
      case 33:
        driveStright("RUE",0.05,"f",0); //drive to line's general area
        if (is_encoder_reached(encoder+200, leftFront)) {
          state = 37;
        }
        break;
      case 34:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        //resetAllEncoders_noWait();
        state++;
        break;
//      case 35:
//        driveStright("RUE",0,"r",0);
//        encoder=leftFront.getCurrentPosition();
//        state++;
//        break;
//      case 36:
//        driveStright("RUE",0.05,"r",0); //drive to line's general area
//        if (is_encoder_reached(encoder+100, leftFront)) {
//          state = 37;
//        }
//        break;
     case 37:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        state++;
        break;
        case 38:
            if(beacon.blue()>beacon.red())
        {
            telemetry.addData("It's BLUE!!!","");
            state = 40;
        }
            else
            {
                state++;
            }
            break;
        case 39:
            if(beacon.red()>beacon.blue())
            {
                telemetry.addData("It's RED!!!","");
                state=41;
            }
            break;
        case 40: // blue case
            break;
        case 41: // red case
            try {
          Thread.sleep(5000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
            state=42;
            break;
        case 42:
            driveStright("RUE",0,"r",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 43:
            driveStright("RUE",0.05,"r",0); //drive to line's general area
            if (is_encoder_reached(encoder+200, leftFront)) {
                state = 44;
            }
            break;
        case 44:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            //resetAllEncoders_noWait();
            state++;
            break;
      default:
        break;


    }
    telemetry.addData("RightFront: ", get_position(rightFront));
    telemetry.addData("LeftFront: ", get_position(leftFront));
    telemetry.addData("RightRear: ", get_position(rightRear));
    telemetry.addData("LeftRear: ", get_position(leftRear));
    telemetry.addData("1. State: ", state);
    telemetry.addData("2. White down: ", RGB.alpha());
    telemetry.addData("3. Red out: ", beacon.red());
    telemetry.addData("3. Blue out: ", beacon.blue());
    telemetry.addData("Gyro: ", gyro.getHeading());
  } // loop
} //
