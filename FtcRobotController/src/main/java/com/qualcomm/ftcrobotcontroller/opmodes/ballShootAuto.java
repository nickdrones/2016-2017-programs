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
    resetAllEncoders_withWait();
    //gyroCalibrate();
    RGB.enableLed(false); //not sure why these are needed here.  Seems to help reset the LEDS so the next enable commands are obeyed.
    beacon.enableLed(false);


  }
  @Override public void loop ()
  {
    switch (state)
    {
      case 0:
          balllauncher1.setDirection(DcMotor.Direction.FORWARD);
          balllauncher2.setDirection(DcMotor.Direction.REVERSE);
          ballCollector.setDirection(DcMotor.Direction.REVERSE);

          encoder=balllauncher1.getCurrentPosition();
          state++;
          break;
        case 1:
            balllauncher1.setPower(0.8);
            balllauncher2.setPower(0.8);
            if (is_encoder_reached((2000+encoder), balllauncher1)) {
                state++;
            }
            break;
        case 2:
            ballCollector.setPower(0.2);

            if (is_encoder_reached((3000+encoder), balllauncher1)) {
                state++;
            }
            break;
        case 3:
            ballCollector.setPower(0);
            encoder=balllauncher1.getCurrentPosition();
            state++;
            break;
        case 4:
            balllauncher1.setPower(0.8);
            balllauncher2.setPower(0.8);
            if (is_encoder_reached((2000+encoder), balllauncher1)) {
                state++;
            }
            break;
        case 5:
            ballCollector.setPower(0.2);

            if (is_encoder_reached((3000+encoder), balllauncher1)) {
                state++;
            }
            break;
        case 6:
            ballCollector.setPower(0);
            balllauncher1.setPower(0);
            balllauncher2.setPower(0);
            state++;
            break;
        case 7:
            driveStright("RUE",0.2,"r",0); //drive away from line

            if (is_encoder_reached(1400, leftFront)) {

                state++;
                encoder=leftFront.getCurrentPosition();
            }
            break;
        case 8:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            break;
        case 9:
            pointTurn("RUE",0.1,"r",0); //turn towards line
            if (gyro.getHeading()>180 && gyro.getHeading()<300) {   //the <180 is to compensate if the robot turns slightly to the left
                state++;
            }
            break;
        case 10:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            driveStright("RUE",0,"f",0); //drive to line's general area
            encoder=leftFront.getCurrentPosition();
            break;
        case 11:
            driveStright("RUE",0.4,"f",0); //drive to line's general area
            ballCollector.setPower(1);
            if (is_encoder_reached((2200+encoder), leftFront)) {
                state++;
            }
            break;
        case 12:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            ballCollector.setPower(0);
            state++;
            encoder=leftFront.getCurrentPosition();
            break;
        case 13:
            pointTurn("RUE",0.1,"l",0); //turn towards line
            if (gyro.getHeading()<95 && gyro.getHeading()>300) {   //the <180 is to compensate if the robot turns slightly to the left
                state++;
            }
            break;
        case 14:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state++;
            driveStright("RUE",0,"f",0); //drive to line's general area
            encoder=leftFront.getCurrentPosition();
            break;
        case 15:
            driveStright("RUE",0.4,"f",0); //drive to line's general area
            ballCollector.setPower(1);
            if (is_encoder_reached((2200+encoder), leftFront)) {
                state++;
            }
            break;
        case 16:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            ballCollector.setPower(0);
            state++;
            encoder=leftFront.getCurrentPosition();
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
    telemetry.addData("3. Gyro: ", gyro.getHeading());
    //telemetry.addData("3. Blue out: ", beacon.blue());
    //telemetry.addData("Gyro: ", gyro.getHeading());
  } // loop
} //
