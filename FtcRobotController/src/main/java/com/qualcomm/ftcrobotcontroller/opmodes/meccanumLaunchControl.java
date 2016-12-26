package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

public class meccanumLaunchControl extends OpMode {
  DcMotor rightFront;
  DcMotor leftFront;
  DcMotor rightRear;
  DcMotor leftRear;
  DcMotor ballcollector;
  DcMotor balllauncher1;
  DcMotor balllauncher2;
  public meccanumLaunchControl() {
  }

  @Override
  public void init() {
    balllauncher1 = hardwareMap.dcMotor.get("balllauncher1");
    balllauncher1.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    balllauncher2 = hardwareMap.dcMotor.get("balllauncher2");
    balllauncher2.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    ballcollector = hardwareMap.dcMotor.get("ballcollector");
    ballcollector.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    leftFront = hardwareMap.dcMotor.get("leftFront");
    rightFront = hardwareMap.dcMotor.get("rightFront");
    leftFront.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    rightFront.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    leftFront.setDirection(DcMotor.Direction.FORWARD);
    rightFront.setDirection(DcMotor.Direction.REVERSE);
    leftRear = hardwareMap.dcMotor.get("leftRear");
    rightRear = hardwareMap.dcMotor.get("rightRear");
    rightRear.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    leftRear.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    leftRear.setDirection(DcMotor.Direction.FORWARD);
    rightRear.setDirection(DcMotor.Direction.REVERSE);
    telemetry.addData("","V 2");

  }
  @Override
  public void loop() {

    float yL_val = -gamepad1.left_stick_y*((float)0.7);            //reading raw values from the joysticks
    float xL_val = gamepad1.left_stick_x*((float)0.7);            //reading raw values from the joysticks
    float xR_val = gamepad1.right_stick_x/2;
    float collector = gamepad2.right_trigger-gamepad2.left_trigger;
    //clip the right/left values so that the values never exceed +/- 1.
    yL_val = (float) scaleInput(yL_val);
    xL_val = (float) scaleInput(xL_val);
    xR_val = (float) scaleInput(xR_val);
    collector = (float) scaleInput(collector);

    float RF =(yL_val-xR_val-xL_val);  //these are the calculations need to make a simple
    float LF =(yL_val+xR_val+xL_val);  // meaccnum drive. The left joystick controls moving
    float RR= (yL_val-xR_val+xL_val);  //straight forward/backward and straight sideways. The
    float LR =(yL_val+xR_val-xL_val);  //right joystick controls turning.

    collector = Range.clip(collector, -1, 1);
    RF = Range.clip(RF, -1, 1);
    LF = Range.clip(LF, -1, 1);
    RR = Range.clip(RR, -1, 1);
    LR = Range.clip(LR, -1, 1);

    float launcher = gamepad2.right_stick_y;
    launcher = (float) scaleInput(launcher);
/////////////////////////////////////////////////////////////
    //////LAUNCHER MOTOR 1////////////////
    /////////////////////////////////////
     /*
    The below statements allow the driver to write a constant speed to the launcher motors.
    In other statements below, the driver is able to use the left joystick to control the speed
    difference between the
     */
    float launchspeed1;
    ballcollector.setPower(collector);

    if(gamepad2.a){                   //Preset values for motor speeds for ball launcher
      launchspeed1=85;
    }
    else if(gamepad2.y){
      launchspeed1=-100;
    }
    else if(gamepad2.x){
      launchspeed1=100;
    }
    else if(gamepad2.b){
      launchspeed1=70;
    }
    else{
      launchspeed1=0;
    }
    /////////////////////////////////////////////////////////////
    /////////////////LAUNCHER MOTOR 2////////////////////////////
    /////////////////////////////////////////////////////////////
    /*
    The below statements allow the driver to write a constant speed to the launcher motors.
    In other statements below, the driver is able to use the left joystick to control the speed
    difference between the
     */
    float launchspeed2=0;

    if(gamepad2.a){
      launchspeed2=85;
    }
    else if(gamepad2.y){
      launchspeed2=-100;
    }
    else if(gamepad2.x){
      launchspeed2=100;
    }
    else if(gamepad2.b){
      launchspeed2=70;
    }
    else{
      launchspeed2=0;
    }
    float launchpower1=launcher+launchspeed1;
    float launchpower2=launcher+launchspeed2;
    //next two lines control difference between launcher motors
    /*
    Taking the motor power and subtracting the value from the top and adding it to the bottom
    will allow for more fine control of pitch and spin of ball.
    */
    launchpower1=launchpower1-gamepad2.left_stick_y/4;//motor 1 is the top motor
    launchpower2=launchpower2+gamepad2.left_stick_y/4;//motor two is the top motor
    launchpower1=Range.clip(launchpower1, -1, 1);
    launchpower2=Range.clip(launchpower2, -1, 1);
    balllauncher1.setPower(launchpower1);
    balllauncher2.setPower(launchpower2);
    rightFront.setPower(RF);
    leftFront.setPower(LF);
    rightRear.setPower(RR);
    leftRear.setPower(LR);
    telemetry.addData( "01", "Left Drive: " + leftFront.getPower ());
    telemetry.addData( "02", "Right Drive: " + rightFront.getPower ());
    telemetry.addData ("05", "Gamepad1 Left Y: " + -gamepad1.left_stick_y);
    telemetry.addData ("06", "Gamepad1 left X: " + -gamepad1.left_stick_x);
    telemetry.addData ("07", "Gamepad1 Right X: " + -gamepad1.right_stick_x);
    telemetry.addData ("08", "LF:  " +LF + "   RF:  "+RF+"   RR:  "+RR+"   LR:  "+LR);
    telemetry.addData ("09", "LFa: " +leftFront.getCurrentPosition() + "   RFa: "+rightFront.getCurrentPosition()+"   RRa: "+rightRear.getCurrentPosition()+"   LRa: "+leftRear.getCurrentPosition());
  }
  @Override
  public void stop() {
  }
  double scaleInput(double dVal) {
    double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
            0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};
    // get the corresponding index for the scaleInput array.
    int index = (int) (dVal * 16.0);

    // index should be positive.
    if (index < 0) {
      index = -index;
    }

    // index cannot exceed size of array minus 1.
    if (index > 16) {
      index = 16;
    }

    // get value from the array.
    double dScale;
    if (dVal < 0) {
      dScale = -scaleArray[index];
    } else {
      dScale = scaleArray[index];
    }

    // return scaled value.
    return dScale;
  }
}