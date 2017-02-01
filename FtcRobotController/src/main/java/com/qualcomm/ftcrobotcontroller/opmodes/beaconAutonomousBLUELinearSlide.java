package com.qualcomm.ftcrobotcontroller.opmodes;

public class beaconAutonomousBLUELinearSlide extends Error404_Hardware_Tier2

{
  ///////////////////////////////////////////////////////////////////
  private int state = 0;
  private int encoder=0;
  private int tempval=0;
  private int backupEncoder=0;
  private double powervalue;
  private int gyrovalatslow=0;
    private int gyroafterstraight;
  int zeroPoint=0;
    double rightVal=0.05;
    double leftVal=0;

    public beaconAutonomousBLUELinearSlide()
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
setServoPos(rightPush, 0.05);
setServoPos(leftPush, 0.0);

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
          turn_gyro_power(23, 0.1, 0.6, "r");
        if (gyro.getHeading()>28 && gyro.getHeading()<180) {   //the <180 is to compensate if the robot turns slightly to the left
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
          tempval=gyro.getHeading();
        encoder=leftFront.getCurrentPosition();
          break;
      case 5:
          driveStright("RUE",0.4,"r",0); //drive to line's general area
        if (is_encoder_reached((2200+encoder), leftFront)) {
          state++;
        }
        break;
        case 6:
        gyroafterstraight=gyro.getHeading();
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
        if(RGB.alpha()>5)
        {
        state=11;
        }
        break;
        case 11:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            break;

      case 12:
          pointTurn("RUE",0.1,"r",0); //turn onto line
          if (gyro.getHeading()>80) {
              state++;
          }
        break;
      case 13:
          set_power(0,rightFront);
          set_power(0,leftFront);
          set_power(0,rightRear);
          set_power(0,leftRear);
          zeroPoint = gyro.getHeading();
          state++;
        break;
        case 14:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            //resetAllEncoders_noWait();
            state=18;
//            break;
//        case 15:
//            set_power(0,rightFront);
//            set_power(0,leftFront);
//            set_power(0,rightRear);
//            set_power(0,leftRear);
//            slide_sideways("RUE",0,"r",0);
//            encoder=leftFront.getCurrentPosition();
//            //resetAllEncoders_withWait();
//            state++;
//            break;
//        case 16:
//            slide_sideways("RUE",0.1,"r",0); //drive to line's general area
//            if (is_encoder_reached(encoder+100, leftFront)) {
//                state++;
//            }
//            break;
//
//        case 17:
//        driveStright("RUE",0,"f",0);
//        state++;
//        break;
      case 18:
        driveStright("RUE",0.2,"r",0);
        if(touch.isPressed()&&touch2.isPressed()){
          state++;
        }
        break;
      case 19:
        set_power(0,rightFront);
        set_power(0,leftFront);
        set_power(0,rightRear);
        set_power(0,leftRear);
        //state=38;
        setServoPos(rightPush, 0.2);
          state=55;
       break;
//      case 20:
//        driveStright("RUE",0,"f",0);
//        //resetAllEncoders_noWait();
//        state++;
//        encoder=leftFront.getCurrentPosition();
//        break;
//      case 21:
//        driveStright("RUE",0.3,"f",0); //drive to line's general area
//        if (is_encoder_reached((encoder+200), leftFront)) {
//          state++;
//        }
//        break;
//      case 22:
//        set_power(0,rightFront);
//        set_power(0,leftFront);
//        set_power(0,rightRear);
//        set_power(0,leftRear);
//        //resetAllEncoders_noWait();
//        state++;
//        break;
//      case 23:
//        slide_sideways("RUE",0,"l",0);
//        encoder=leftFront.getCurrentPosition();
//        //resetAllEncoders_withWait();
//       state++;
//        break;
//      case 24:
//        slide_sideways("RUE",0.1,"l",0); //drive to line's general area
//        if (is_encoder_reached(encoder+330, leftFront)) {
//          state++;
//        }
//        break;
//      case 25:
//        set_power(0,rightFront);
//        set_power(0,leftFront);
//        set_power(0,rightRear);
//        set_power(0,leftRear);
//        //resetAllEncoders_noWait();
//        state++;
//     case 26:
//        driveStright("RUE",0,"r",0);
//        encoder=leftFront.getCurrentPosition();
////        //resetAllEncoders_withWait();
//        state=31;
//        break;
//      case 31:
//        set_power(0,rightFront);
//        set_power(0,leftFront);
//        set_power(0,rightRear);
//        set_power(0,leftRear);
//        //resetAllEncoders_noWait();
//        state++;
//        break;
//      case 32:
//        driveStright("RUE",0,"f",0);
//        encoder=leftFront.getCurrentPosition();
//        backupEncoder=leftRear.getCurrentPosition();
//        state++;
//        break;
//      case 33:
//          state = 37;
//        break;
//     case 37:
//        set_power(0,rightFront);
//        set_power(0,leftFront);
//        set_power(0,rightRear);
//        set_power(0,leftRear);
//         slide_sideways("RUE",0,"l",0);
//         encoder=leftFront.getCurrentPosition();
//        state++;
//        break;
        case 38:
            if(beacon.blue()>beacon.red())
        {
            telemetry.addData("It's BLUE!!!","");
            state = 40;
        }
            else if(beacon.red()>beacon.blue())
            {
                telemetry.addData("It's RED!!!","");
                state=41;
            }
            else
            {
               state=41;
            }
            break;
        case 40: // blue case
            leftPush.setPosition(leftVal);
            state=55;
            break;
//        case 50:
//            driveStright("RUE",0,"r",0);
//            encoder=leftFront.getCurrentPosition();
//            backupEncoder=leftRear.getCurrentPosition();
//            state++;
//            break;
//        case 51:
//            driveStright("RUE",0.1,"r",0); //drive to line's general area
//            if (is_encoder_reached(encoder+280, leftFront)||is_encoder_reached(backupEncoder+300, leftRear)||touch.isPressed()||touch2.isPressed()) {
//                state = 52;
//            }
//            break;
//        case 52:
//            set_power(0,rightFront);
//            set_power(0,leftFront);
//            set_power(0,rightRear);
//            set_power(0,leftRear);
//            state = 56;
//            break;
        case 41: // red case
            ///////////////////////////////////////////////
            rightPush.setPosition(rightVal);
            state=55;
            break;
//        case 42:
//            slide_sideways("RUE",0.1,"l",0); //drive to line's general area
//            if (is_encoder_reached(encoder+410, leftFront)) {
//                state++;
//            }
//            break;
//        case 43:
//            set_power(0,rightFront);
//            set_power(0,leftFront);
//            set_power(0,rightRear);
//            set_power(0,leftRear);
//            state++;
//            break;
//        case 44:
//            driveStright("RUE",0,"r",0);
//            encoder=leftFront.getCurrentPosition();
//            backupEncoder=leftRear.getCurrentPosition();
//            state++;
//            break;
//        case 45:
//            driveStright("RUE",0.1,"r",0); //drive to line's general area
//            if (is_encoder_reached(encoder+280, leftFront)||is_encoder_reached(backupEncoder+300, leftRear)||touch.isPressed()||touch2.isPressed()) {
//                state = 46;
//            }
//            break;
//        case 46:
//            set_power(0,rightFront);
//            set_power(0,leftFront);
//            set_power(0,rightRear);
//            set_power(0,leftRear);
//            state = 56;
//            break;
        case 55:
            try {
                Thread.sleep(1500);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            state++;
            break;
        case 56:
            //////////////////////////////
            driveStright("RUE",0,"f",0);
            encoder=leftFront.getCurrentPosition();
            state = 58;
            break;
        case 58:
            driveStright("RUE",0.25,"f",0); //drive to line's general area
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
                //state=63;
                state=64;
                break;
            }
            else if(gyro.getHeading()<zeroPoint)
            {
                //state=62;
                state=64;
                break;
            }
            else
            {
                state = 64;
            }
        case 62:
            pointTurn("RUE",0.03,"r",0); //turn right
            if (gyro.getHeading()>83){
                state=64;
            }
            break;
        case 63:
            pointTurn("RUE",0.03,"l",0); //turn left
            if (gyro.getHeading()<85) {
                state=64;
            }
            break;
        case 64:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            slide_sideways("RUE",0,"r",0); //drive to line's general area
            state++;
            encoder=leftFront.getCurrentPosition();
            break;
        case 65:
            slide_sideways("RUE",0.5,"r",0); //drive to line's general area
            if (is_encoder_reached(encoder+2000, leftFront)) {
                state++;
            }
            break;
        case 66:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            slide_sideways("RUE",0,"r",0); //drive to line's general area
            state++;
            encoder=leftFront.getCurrentPosition();
            break;
        case 67:
            slide_sideways("RUE",0.1,"r",0); //drive to line's general area
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
            state=71;
            encoder=leftFront.getCurrentPosition();
            break;
//        case 69:
//            slide_sideways("RUE",0.1,"r",0); //drive to line's general area
//            if (is_encoder_reached(encoder+90, leftFront)) {
//                state++;
//            }
//            break;
//        case 70:
//            set_power(0,rightFront);
//            set_power(0,leftFront);
//            set_power(0,rightRear);
//            set_power(0,leftRear);
//            slide_sideways("RUE",0,"r",0); //drive to line's general area
//            state++;
//            encoder=leftFront.getCurrentPosition();
//            break;
        case 71:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            //resetAllEncoders_noWait();
            state++;
            break;
        case 72:
            driveStright("RUE",0,"f",0);
            state++;
            break;
        case 73:
            driveStright("RUE",0.2,"r",0);
//        if ((ODS.getLightDetected()*100)>50) {
            if(touch.isPressed() && touch2.isPressed()){
                state++;
            }
            break;
        case 74:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            //resetAllEncoders_noWait();
            state++;
            break;
        case 75:
            driveStright("RUE",0,"f",0);
            //resetAllEncoders_noWait();
            state=96;
            encoder=leftFront.getCurrentPosition();
            break;
//        case 76:
//            driveStright("RUE",0.1,"f",0); //drive to line's general area
//            if (is_encoder_reached((encoder+200), leftFront)) {
//                state++;
//            }
//            break;
//        case 77:
//            set_power(0,rightFront);
//            set_power(0,leftFront);
//            set_power(0,rightRear);
//            set_power(0,leftRear);
//            //resetAllEncoders_noWait();
//            state++;
//            break;
//        case 78:
//            slide_sideways("RUE",0,"l",0);
//            encoder=leftFront.getCurrentPosition();
//            //resetAllEncoders_withWait();
//            state++;
//            break;
//        case 79:
//            slide_sideways("RUE",0.1,"l",0); //drive to line's general area
//            if (is_encoder_reached(encoder+300, leftFront)) {
//                state++;
//            }
//            break;
//        case 80:
//            set_power(0,rightFront);
//            set_power(0,leftFront);
//            set_power(0,rightRear);
//            set_power(0,leftRear);
//            //resetAllEncoders_noWait();
//            state++;
//        case 81:
//            driveStright("RUE",0,"r",0);
//            encoder=leftFront.getCurrentPosition();
////        //resetAllEncoders_withWait();
//            state++;
//            break;
//        case 82:
//            set_power(0,rightFront);
//            set_power(0,leftFront);
//            set_power(0,rightRear);
//            set_power(0,leftRear);
//            //resetAllEncoders_noWait();
//            state++;
//            break;
//        case 83:
//            driveStright("RUE",0,"f",0);
//            encoder=leftFront.getCurrentPosition();
//            state++;
//            break;
//        case 84:
//            set_power(0,rightFront);
//            set_power(0,leftFront);
//            set_power(0,rightRear);
//            set_power(0,leftRear);
//            slide_sideways("RUE",0,"l",0);
//            encoder=leftFront.getCurrentPosition();
//            state++;
//            break;
//        case 85:
//            if(beacon.blue()>beacon.red())
//            {
//                telemetry.addData("It's BLUE!!!","");
//                state = 87;
//            }
//            else if(beacon.red()>beacon.blue())
//            {
//                telemetry.addData("It's RED!!!","");
//                state=91;
//            }
//            else {
//                state=86;
//                encoder=leftFront.getCurrentPosition();
//            }
//            break;
//        case 86:
//            slide_sideways("RUE",0.1,"l",0); //drive to line's general area
//            if (is_encoder_reached(encoder+140, leftFront)) {
//                state=85;
//            }
//            break;
//        case 87: // blue case
//            set_power(0,rightFront);
//            set_power(0,leftFront);
//            set_power(0,rightRear);
//            set_power(0,leftRear);
//            state++;
//            break;
//        case 88:
//            driveStright("RUE",0,"r",0);
//            encoder=leftFront.getCurrentPosition();
//            backupEncoder=leftRear.getCurrentPosition();
//            state++;
//            break;
//        case 89:
//            driveStright("RUE",0.1,"r",0); //drive to line's general area
//            if (is_encoder_reached(encoder+280, leftFront)||is_encoder_reached(backupEncoder+300, leftRear)||touch.isPressed()||touch2.isPressed()) {
//                state = 90;
//            }
//            break;
//        case 90:
//            set_power(0,rightFront);
//            set_power(0,leftFront);
//            set_power(0,rightRear);
//            set_power(0,leftRear);
//            state = 97;
//            break;
//        case 91: // red case
//            ///////////////////////////////////////////////
//            slide_sideways("RUE",0,"l",0);
//            encoder=leftFront.getCurrentPosition();
//            state++;
//            break;
//        case 92:
//            slide_sideways("RUE",0.1,"l",0); //drive to line's general area
//            if (is_encoder_reached(encoder+430, leftFront)) {
//                state++;
//            }
//            break;
//        case 93:
//            set_power(0,rightFront);
//            set_power(0,leftFront);
//            set_power(0,rightRear);
//            set_power(0,leftRear);
//            state++;
//            break;
//        case 94:
//            driveStright("RUE",0,"r",0);
//            encoder=leftFront.getCurrentPosition();
//            backupEncoder=leftRear.getCurrentPosition();
//            state++;
//            break;
//        case 95:
//            driveStright("RUE",0.1,"r",0); //drive to line's general area
//            if (is_encoder_reached(encoder+280, leftFront)||is_encoder_reached(backupEncoder+300, leftRear)||touch.isPressed()||touch2.isPressed()) {
//                state = 96;
//            }
//            break;
        case 96:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state = 97;
            break;
        case 97:
            /////////////////////////
           try
           {
               Thread.sleep(1500);                 //1000 milliseconds is one second.
            }
           catch(InterruptedException ex)
           {
                Thread.currentThread().interrupt();
            }
            ////////////////
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
            pointTurn("RUE",0,"l",0);
            state++;
            break;
        case 100:
            pointTurn("RUE",0,"l",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 101:
            pointTurn("RUE",0.2,"l",0); //drive to line's general area
            if (gyro.getHeading()<60 && gyro.getHeading()>1) {
                state++;
            }
            break;
        case 102:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            break;

        case 103:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            break;
        case 104:
            driveStright("RUE",0,"f",0);
            encoder=leftFront.getCurrentPosition();
            state++;
            break;
        case 105:
            driveStright("RUE",0.3,"f",0); //drive to line's general area
            if (is_encoder_reached(encoder+2500, leftFront)) {
                state++;
            }
            break;
        case 106:
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
