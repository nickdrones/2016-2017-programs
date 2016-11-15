package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class launcher extends OpMode {

  //writing variables to the min and max values of the servos on the robot.
  final static double ARM_MIN_RANGE = 0.0;
  final static double ARM_MAX_RANGE = 1.0;
  final static double LEFT_FLIPPER_MAX_RANGE = 1.0;
  final static double LEFT_FLIPPER_MIN_RANGE = 0.0;
  final static double RIGHT_FLIPPER_MAX_RANGE = 1.0;
  final static double RIGHT_FLIPPER_MIN_RANGE = 0.0;
  final static double SHOULDER_MAX_RANGE = 1.0;
  final static double SHOULDER_MIN_RANGE = 0.0;
  final static double ELBOW_MAX_RANGE = 1.0;
  final static double ELBOW_MIN_RANGE = 0.0;
  final static double SPIN_MIN_RANGE = 0.20;
  final static double SPIN_MAX_RANGE = 0.55;
  final static double GRABBER_MIN_RANGE = 0.0;
  final static double GRABBER_MAX_RANGE = 1.0;


  double armPosition = 0.65;               // arm = climber bucket

  double leftFlipperposition;
  double rightFlipperposition;

  // amount to change the arm servo+ position. 1 = 180deg rotation.
  //double armDelta = 1.0;
  double tapePosition;
  /*
     this line sets the values of the servos for the arm the servos will automatically go to these
     specified positions when the program is started.
      */
  double shoulderPosition = 0.56;
  double elbowPosition = 0.99;
  double spinPosition = 0.23;
  double grabberPosition = 1.00;


  DcMotor front;
  DcMotor back;

  public launcher() {

  }


  @Override
  public void init() {


    front = hardwareMap.dcMotor.get("front");                  //linking all the motors/servos from what they are called in the programming (far left)
    back = hardwareMap.dcMotor.get("back");                    //with what they are called in the config file (in green)


  }

  /*
   * This method will be called repeatedly in a loop
   *
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
   */
  @Override
  public void loop() {


    float left = gamepad1.left_stick_y;            //reading raw values from the joysticks
    float right = gamepad1.right_stick_y;
    double power=0;
    boolean statement=true;

    // clip the right/left values so that the values never exceed +/- 1.
    right = Range.clip(right, -1, 1);
    left = Range.clip(left, -1, 1);


    // scale the joystick value to make it easier to control
    // the robot more precisely at slower speeds.
    right = (float) scaleInput(right);
    left = (float) scaleInput(left);
    //writing the value of the joystick on the second remote to the winch motor
//    if(gamepad1.a){
//      power=(double)right;
//      statement=true;
//    }
//     else{
       back.setPower(right);                   //writing the power values to the motors
       front.setPower(right);
//     }
//
//    if (statement){
//      front.setPower(power);
//      back.setPower(power);
//    }
//
//    if(gamepad1.b)
//    {
//      statement=false;
//    }

    //back.setPower(right);                   //writing the power values to the motors
    //front.setPower(right);

    // clip the position values so that they never exceed their allowed range
    armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
    rightFlipperposition = Range.clip(rightFlipperposition, RIGHT_FLIPPER_MIN_RANGE, RIGHT_FLIPPER_MAX_RANGE);
    leftFlipperposition = Range.clip(leftFlipperposition, LEFT_FLIPPER_MIN_RANGE, LEFT_FLIPPER_MAX_RANGE);
    spinPosition = Range.clip(spinPosition, SPIN_MIN_RANGE, SPIN_MAX_RANGE);
    shoulderPosition = Range.clip(shoulderPosition, SHOULDER_MIN_RANGE, SHOULDER_MAX_RANGE);
    elbowPosition = Range.clip(elbowPosition, ELBOW_MIN_RANGE, ELBOW_MAX_RANGE);
    grabberPosition = Range.clip(grabberPosition, GRABBER_MIN_RANGE, GRABBER_MAX_RANGE);


    // write position values to the wrist and claw servo
    //arm.setPosition(armPosition);

    // slider.setPosition(sliderPosition);

		/*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */
telemetry.addData("power: ", right);
  }

  /*
   * Code to run when the op mode is first disabled goes here
   *
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
   */
  @Override
  public void stop() {

  }


  /*
   * This method scales the joystick input so for low joystick values, the
   * scaled value is less than linear.  This is to make it easier to drive
   * the robot more precisely at slower speeds.
   */
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
