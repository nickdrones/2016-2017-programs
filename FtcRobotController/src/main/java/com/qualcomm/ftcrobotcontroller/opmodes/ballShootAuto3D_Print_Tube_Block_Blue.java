package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.SETUP;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.DRIVE_FORWARD_SLIGHTLY;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.START_LAUNCHER_WHEELS;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.SHOOT_PARTICLES;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.STOP_LAUNCHER;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.GET_TO_OPTIMAL_SPEED;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.SHOOT_PARTICLES_AGAIN;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.STOP_SHOOTING_AGAIN;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.DRIVE_TO_BORDER;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.STOP_DRIVING;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.WAIT_UNTIL_10_SECONDS;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.DRIVE_PAST_CENTER;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.STOP_PAST_CENTER;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.TURN_TO_WALL;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.STOP_TURNING;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.DRIVE_TO_WALL;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.STOP_ON_WALL;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.REVERSE_TO_BLOCK;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.STOP_AND_BLOCK;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.WAIT_UNTIL_27_SECONDS;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.RETURN_TO_CENTER;
import static com.qualcomm.ftcrobotcontroller.opmodes.ballShootAuto3D_Print_Tube_Block_Blue.State.STOP_ON_CENTER;


public class ballShootAuto3D_Print_Tube_Block_Blue extends Error404_Hardware_Tier2

{
  ///////////////////////////////////////////////////////////////////
    ///  This autonomous program will shoot two balls and drive across the field to block.
    ///////////////////////////////////////////////////////////////////
  private int encoder=0;
  private int test=0;

    public enum State {
        SETUP, DRIVE_FORWARD_SLIGHTLY, START_LAUNCHER_WHEELS,
        SHOOT_PARTICLES, STOP_LAUNCHER, GET_TO_OPTIMAL_SPEED,
        SHOOT_PARTICLES_AGAIN, STOP_SHOOTING_AGAIN, DRIVE_TO_BORDER,
        STOP_DRIVING, WAIT_UNTIL_10_SECONDS, DRIVE_PAST_CENTER,
        STOP_PAST_CENTER, TURN_TO_WALL, STOP_TURNING, DRIVE_TO_WALL,
        STOP_ON_WALL, REVERSE_TO_BLOCK, STOP_AND_BLOCK, WAIT_UNTIL_27_SECONDS,
        RETURN_TO_CENTER, STOP_ON_CENTER
    }
    private State state = SETUP;
        public ballShootAuto3D_Print_Tube_Block_Blue()
  {
  }
   @Override public void init(){
    super.init();
    telemetry.addData("Out Red: ", beacon.red());
    telemetry.addData("Out Blue: ", beacon.blue());
    gyroCalibrate();
    telemetry.addData("Gyro: ", gyro.getHeading());
       /// print gyro reading after the calibration is complete.
    telemetry.addData("","V 1");
       rightPush.setPosition(0.5);
       leftPush.setPosition(0.5);
   }

  @Override public void start(){
    resetAllEncoders_withWait();
    RGB.enableLed(false);
      //Not sure why this is needed here. Seems to reset LEDS so the next enable cmds are obeyed.
    beacon.enableLed(false);
  }

  @Override public void loop ()
  {
    switch (state)
    {
      case SETUP:
          balllauncher1.setDirection(DcMotor.Direction.FORWARD);
          balllauncher2.setDirection(DcMotor.Direction.REVERSE); //Set needed  motor directions
          ballCollector.setDirection(DcMotor.Direction.REVERSE);
          encoder=leftFront.getCurrentPosition();
          state=DRIVE_FORWARD_SLIGHTLY;
          break;

      case DRIVE_FORWARD_SLIGHTLY:
        driveStright("RUE",0.05,"r",0);
        if (is_encoder_reached((1000+encoder), leftFront)) {   //drive out from wall
            driveStright("RUE", 0, "f", 0);
            state=START_LAUNCHER_WHEELS;
            encoder=balllauncher1.getCurrentPosition();
        }

        case START_LAUNCHER_WHEELS:
            balllauncher1.setPower(0.45); //Start up ball launcher wheels
            balllauncher2.setPower(0.45);
            if (is_encoder_reached((4000+encoder), balllauncher1)) { //wait until launcher wheels are up to speed
                state=SHOOT_PARTICLES;
            }
            break;

        case SHOOT_PARTICLES:
            ballCollector.setPower(0.2); //move ball collector motor to push ball into launcher wheels
            if (is_encoder_reached((5000+encoder), balllauncher1)) {
                state=STOP_LAUNCHER;
            }
            break;

        case STOP_LAUNCHER:
            ballCollector.setPower(0); //stop ball collector
            encoder=balllauncher1.getCurrentPosition();
            state=GET_TO_OPTIMAL_SPEED;
            break;

        case GET_TO_OPTIMAL_SPEED:
            balllauncher1.setPower(0.45);
            balllauncher2.setPower(0.45); //let ball launcher motors get back to optimal speed
            if (is_encoder_reached((3000+encoder), balllauncher1)) {
                state=SHOOT_PARTICLES_AGAIN;
            }
            break;

        case SHOOT_PARTICLES_AGAIN:
            ballCollector.setPower(0.2); //push balls (in case ball did not go through first time)
            if (is_encoder_reached((5000+encoder), balllauncher1)) {
                state=STOP_SHOOTING_AGAIN;
            }
            break;

        case STOP_SHOOTING_AGAIN:
            ballCollector.setPower(0);
            balllauncher1.setPower(0);
            balllauncher2.setPower(0); //stop all launcher and collector motors
            state=DRIVE_TO_BORDER;
            break;

        case DRIVE_TO_BORDER:
            driveStright("RUE",0.2,"r",0); // drive out until robot is close to center vortex

            if (is_encoder_reached(2000, leftFront)) {

                state=STOP_DRIVING;
                encoder=leftFront.getCurrentPosition();
            }
            break;

        case STOP_DRIVING:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state=WAIT_UNTIL_10_SECONDS;
            rightPush.setPosition(0.15);
            leftPush.setPosition(0.15); //retract linear servos to closed position
            break;

        case WAIT_UNTIL_10_SECONDS:
            try {
                Thread.sleep(2000);    //wait until 10 seconds have elapsed before crossing midine.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            state=DRIVE_PAST_CENTER;
            break;

        case DRIVE_PAST_CENTER:
            driveStright("RUE",0.2,"r",0); //drive completely past center beacon

            if (is_encoder_reached(5000, leftFront)) {

                state=STOP_PAST_CENTER;
                encoder=leftFront.getCurrentPosition();
            }
            break;

        case STOP_PAST_CENTER:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            state=TURN_TO_WALL;
            break;

        case TURN_TO_WALL:
            pointTurn("RUE",0.1,"l",0); //turn to face wall between opponent's beacons
            if (gyro.getHeading()>180 && gyro.getHeading()<320) {
                //the <180 is to compensate if the robot turns slightly to the left
               state=STOP_TURNING;
        }
        break;

        case STOP_TURNING:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            encoder=leftFront.getCurrentPosition();
            state=DRIVE_TO_WALL;
            break;

        case DRIVE_TO_WALL:
            driveStright("RUE",0.2,"r",0); //drive until encoders or until robot squares up on wall
            if (is_encoder_reached((encoder+5000),leftFront)||touch.isPressed()&&touch2.isPressed())
               {
                state=STOP_ON_WALL;
                encoder=leftFront.getCurrentPosition();
               }
            break;

        case STOP_ON_WALL:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            driveStright("RUE",0,"f",0);
            encoder=leftFront.getCurrentPosition();
            state=REVERSE_TO_BLOCK;
            break;

        case REVERSE_TO_BLOCK:
            driveStright("RUE",0.1,"f",0); //back up from the wall for maximum blocking possibility

            if (is_encoder_reached((encoder+500), leftFront)) {
                state=STOP_AND_BLOCK;
                encoder=leftFront.getCurrentPosition();
            }
            break;

        case STOP_AND_BLOCK:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            driveStright("RUE",0,"f",0);
            encoder=leftFront.getCurrentPosition();
            state=WAIT_UNTIL_27_SECONDS;
            break;

        case WAIT_UNTIL_27_SECONDS:
            try {
                Thread.sleep(6000);                 // wait until 27 second mark
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            state=RETURN_TO_CENTER;
            break;

        case RETURN_TO_CENTER:
            driveStright("RUE",0.2,"f",0); //drive back to center vortex
            if (is_encoder_reached((encoder+1000), leftFront)) {
                 state=STOP_ON_CENTER;
             }
             break;

        case STOP_ON_CENTER:
            set_power(0,rightFront);
            set_power(0,leftFront);
            set_power(0,rightRear);
            set_power(0,leftRear);
            encoder=leftFront.getCurrentPosition();
            break;

        default:
            break;

    }
    telemetry.addData("1. State: ", state);
    telemetry.addData("2. loops: ", test);
    telemetry.addData("3. Gyro: ", gyro.getHeading());
  } // loop
} //
