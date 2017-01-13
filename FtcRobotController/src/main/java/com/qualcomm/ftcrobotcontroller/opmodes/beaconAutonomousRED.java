package com.qualcomm.ftcrobotcontroller.opmodes;

public class beaconAutonomousRED extends Error404_Hardware_Tier2

{
  ///////////////////////////////////////////////////////////////////
  private int state = 0;
  private int encoder=0;
  private int test=0;
  int zeroPoint = 0;
    public beaconAutonomousRED()
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
    telemetry.addData("","V 4");
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
    {   case 0:
            resetAllEncoders_withWait();
      state++;
      break;
      case 1:
          driveStright("RUE",0.2,"r",0); //drive away from wall
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
        pointTurn("RUE",0.1,"l",0); //turn towards line
        if (gyro.getHeading()<336 && gyro.getHeading()>180) {   //the <180 is to compensate if the robot turns slightly to the left
          state++;
        }
        break;
      case 4:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        state++;
        encoder=leftFront.getCurrentPosition();
        break;
      case 5:
        driveStright("RUE",0.4,"r",0); //drive to line's general area
        if (is_encoder_reached((2200+encoder), leftFront)) {
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
        state++;
        break;
      case 8:
        driveStright("RUE",0.1,"r",0); //drive until line is seen
        if(RGB.alpha()>5)
        {
        state++;
        }
        break;

        case 9:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            driveStright("RUE",0.0,"f",0);
            encoder=leftFront.getCurrentPosition();
            break;

        case 10:
            driveStright("RUE",0.1,"f",0); //drive slightly past line
            if (is_encoder_reached((100+encoder), leftFront)) {
                state++;
            }
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
        state++;
        break;
      case 15:
        pointTurn("RUE",0.1,"l",0); //turn onto line
        if (gyro.getHeading()<280 && gyro.getHeading()>180) {
          state++;
        }
        break;
      case 16:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        zeroPoint = gyro.getHeading();
        state++;
        break;
      case 17:
        driveStright("RUE",0,"f",0);
        state++;
        break;
      case 18:
        driveStright("RUE",0.2,"r",0); //drive until robot presses against wall
        if(touch.isPressed()){
          state++;
        }
        break;
      case 19:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        state++;
        break;
      case 20:
        driveStright("RUE",0,"f",0);
        state++;
        encoder=leftFront.getCurrentPosition();
        break;
      case 21:
        driveStright("RUE",0.3,"f",0); //drive back from pressing wall
        if (is_encoder_reached((encoder+200), leftFront)) {
          state++;
        }
        break;
      case 22:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        state++;
        break;
      case 23:
        slide_sideways("RUE",0,"l",0);
        encoder=leftFront.getCurrentPosition();
       state++;
        break;
      case 24:
        slide_sideways("RUE",0.1,"l",0); //slide sideways until pusher is in front of first beacon button
        if (is_encoder_reached(encoder+220, leftFront)) {
          state++;
        }
        break;
      case 25:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        state++;
     case 26:
        driveStright("RUE",0,"r",0);
        encoder=leftFront.getCurrentPosition();
        state=31;
        break;
      case 31:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        state++;
        break;
      case 32:
        driveStright("RUE",0,"f",0);
        encoder=leftFront.getCurrentPosition();
        state++;
        break;
      case 33:
          state = 37;
        break;
     case 37:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
         slide_sideways("RUE",0,"l",0);
         encoder=leftFront.getCurrentPosition();
        state++;
        break;
        case 38:
            if(beacon.blue()>beacon.red()) //measure color of beacon
            {
                telemetry.addData("It's BLUE!!!",""); //if it is blue, jump to state 41
                state = 41;
            }
            else if(beacon.red()>beacon.blue())
            {
                telemetry.addData("It's RED!!!",""); //if it is blue, jump to state 40
                state=40;
            }
            else {
                state=39; // if both color values are the same (ie: Blue and Red are both zero), jump to state 39
                encoder=leftFront.getCurrentPosition();
            }
            break;
        case 39:
            slide_sideways("RUE",0.1,"l",0); //continue moving sideways until it sees the beacon
            if (is_encoder_reached(encoder+100, leftFront)) {
                state=38;
            }
            break;
        case 40: // blue case
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state=50;
            break;
        case 50:
            driveStright("RUE",0,"r",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 51:
            driveStright("RUE",0.05,"r",0); //drive to push in beacon
            if (is_encoder_reached(encoder+250, leftFront)) {
                state = 52;
            }
            break;
        case 52:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state = 56;
            break;
        case 41: // red case
            slide_sideways("RUE",0,"l",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 42:
            slide_sideways("RUE",0.1,"l",0); //slide sideways to other beacon button
            if (is_encoder_reached(encoder+300, leftFront)) {
                state++;
            }
            break;
        case 43:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            break;
        case 44:
            driveStright("RUE",0,"r",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 45:
            driveStright("RUE",0.2,"r",0); //push in button
            if (is_encoder_reached(encoder+250, leftFront)) {
                state = 46;
            }
            break;
        case 46:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state = 56;
            break;
        case 56:
            //////////////////////////////
            driveStright("RUE",0,"f",0);
            encoder=leftFront.getCurrentPosition();
            state = 58;
            break;
        case 58:
            driveStright("RUE",0.25,"f",0); //back up from pushing
            if (is_encoder_reached(encoder+450, leftFront)) {
                state = 60;
            }
            break;
        case 60:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            break;
        case 61:
            if (gyro.getHeading()>zeroPoint){
                state=63;
                break;
            }
            else if(gyro.getHeading()<zeroPoint)
            {
                state=62;
                break;
            }
            else
            {
                state = 64;
            }
        case 62:
            pointTurn("RUE",0.05,"r",0); //turn right
            if (gyro.getHeading()<277){
                state=64;
            }
            break;
        case 63:
            pointTurn("RUE",0.05,"l",0); //turn left
            if (gyro.getHeading()>275) {
                state=64;
            }
            break;
        case 64:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            slide_sideways("RUE",0,"l",0);
            state++;
            encoder=leftFront.getCurrentPosition();
            break;
        case 65:
            slide_sideways("RUE",0.5,"l",0); //slide sideways to other beacon
            if (is_encoder_reached(encoder+2000, leftFront)) {
                state++;
            }
            break;
        case 66:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            slide_sideways("RUE",0,"l",0);
            state++;
            encoder=leftFront.getCurrentPosition();
            break;
        case 67:
            slide_sideways("RUE",0.1,"l",0); //slowly slide to other line
            if (RGB.alpha()>4) {
                state++;
            }
            break;
        case 68:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            slide_sideways("RUE",0,"r",0); //drive to line's general area
            state++;
            encoder=leftFront.getCurrentPosition();
            break;
        case 69:
            slide_sideways("RUE",0.1,"r",0); //slide slightly past line
            if (is_encoder_reached(encoder+110, leftFront)) {
                state++;
            }
            break;
        case 70:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            slide_sideways("RUE",0,"l",0);
            state++;
            encoder=leftFront.getCurrentPosition();
            break;
        case 71:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            break;
        case 72:
            driveStright("RUE",0,"f",0);
            state++;
            break;
        case 73:
            driveStright("RUE",0.2,"r",0); //drive until robot touches wall
            if(touch.isPressed()){
                state++;
            }
            break;
        case 74:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            break;
        case 75:
            driveStright("RUE",0,"f",0);
            state++;
            encoder=leftFront.getCurrentPosition();
            break;
        case 76:
            driveStright("RUE",0.1,"f",0); //slightly back up from wall
            if (is_encoder_reached((encoder+200), leftFront)) {
                state++;
            }
            break;
        case 77:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            break;
        case 78:
            slide_sideways("RUE",0,"l",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 79:
            slide_sideways("RUE",0.1,"l",0); //slide sideways to other line
            if (is_encoder_reached(encoder+270, leftFront)) {
                state++;
            }
            break;
        case 80:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
        case 81:
            driveStright("RUE",0,"r",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 82:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            break;
        case 83:
            driveStright("RUE",0,"f",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 84:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            slide_sideways("RUE",0,"l",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        //Logic of beacon pressing is same as earlier
        case 85:
            if(beacon.blue()>beacon.red())
            {
                telemetry.addData("It's BLUE!!!","");
                state = 91;
            }
            else if(beacon.red()>beacon.blue())
            {
                telemetry.addData("It's RED!!!","");
                state=87;
            }
            else {
                state=86;
                encoder=leftFront.getCurrentPosition();
            }
            break;
        case 86:
            slide_sideways("RUE",0.1,"l",0); //drive to line's general area
            if (is_encoder_reached(encoder+100, leftFront)) {
                state=85;
            }
            break;
        case 87: // blue case
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            break;
        case 88:
            driveStright("RUE",0,"r",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 89:
            driveStright("RUE",0.2,"r",0); //drive to line's general area
            if (is_encoder_reached(encoder+250, leftFront)) {
                state = 90;
            }
            break;
        case 90:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state = 97;
            break;
        case 91: // red case
            ///////////////////////////////////////////////
            slide_sideways("RUE",0,"l",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 92:
            slide_sideways("RUE",0.1,"l",0); //drive to line's general area
            if (is_encoder_reached(encoder+400, leftFront)) {
                state++;
            }
            break;
        case 93:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            break;
        case 94:
            driveStright("RUE",0,"r",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 95:
            driveStright("RUE",0.2,"r",0); //drive to line's general area
            if (is_encoder_reached(encoder+250, leftFront)) {
                state = 96;
            }
            break;
        case 96:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state = 97;
            break;
        case 97:
            //////////////////////////////
            driveStright("RUE",0,"f",0);
            encoder=leftFront.getCurrentPosition();
            state = 98;
            break;
        case 98:
            driveStright("RUE",0.3,"f",0); //drive to line's general area
            if (is_encoder_reached(encoder+840, leftFront)) {
                state = 99;
            }
            break;
        case 99:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            slide_sideways("RUE",0,"r",0);
            state++;
            break;
        case 100:
            slide_sideways("RUE",0,"r",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 101:
            slide_sideways("RUE",0.8,"r",0); //drive to line's general area
            if (is_encoder_reached(encoder+4800, leftFront)) {
                state++;
            }
            break;
        case 102:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
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
