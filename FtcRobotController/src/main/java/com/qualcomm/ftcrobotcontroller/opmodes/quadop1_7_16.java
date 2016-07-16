


        package com.qualcomm.ftcrobotcontroller.opmodes;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.Range;

public class quadop1_7_16 extends OpMode {

  //writing variables to the min and max values of the servos on the robot.
  final static double ARM_MIN_RANGE  = 0.0;
  final static double ARM_MAX_RANGE  = 1.0;
  final static double LEFT_FLIPPER_MAX_RANGE = 1.0;
  final static double LEFT_FLIPPER_MIN_RANGE = 0.0;
  final static double RIGHT_FLIPPER_MAX_RANGE = 1.0;
  final static double RIGHT_FLIPPER_MIN_RANGE = 0.0;
  final static double SHOULDER_MAX_RANGE = 1.0;
  final static double SHOULDER_MIN_RANGE = 0.0;
  final static double ELBOW_MAX_RANGE = 1.0;
  final static double ELBOW_MIN_RANGE = 0.0;
  final static double SPIN_MIN_RANGE  = 0.20;
  final static double SPIN_MAX_RANGE  = 0.55;
  final static double GRABBER_MIN_RANGE  = 0.0;
  final static double GRABBER_MAX_RANGE  = 1.0;


  double armPosition=0.67;               // arm = climber bucket

  double leftFlipperposition;
  double rightFlipperposition;

  // amount to change the arm servo+ position. 1 = 180deg rotation.
  double armDelta = 1.0;
  double tapePosition;
  /*
     this line sets the values of the servos for the arm the servos will automatically go to these
     specified positions when the program is started.
      */
  double shoulderPosition=0.54;
  double elbowPosition=0.99;
  double spinPosition=0.23;
  double grabberPosition=1.00;


  DcMotor backRight;
  DcMotor frontRight;
  DcMotor backLeft;
  DcMotor frontLeft;
  DcMotor tapemeasure;
  //  Servo arm;            //servo for climber bucket dumper
  Servo leftFlipper;   //debris pushers
  Servo rightFlipper;
  Servo shoulder;
  Servo elbow;
  Servo spin;
  Servo grabber;
  Servo tapearm;




  public quadop1_7_16() {

  }


  @Override
  public void init() {



    backRight = hardwareMap.dcMotor.get("backRight");                  //linking all the motors/servos from what they are called in the programming (far left)
    backLeft = hardwareMap.dcMotor.get("backLeft");                    //with what they are called in the config file (in green)
    frontLeft= hardwareMap.dcMotor.get("frontLeft");
    frontRight=hardwareMap.dcMotor.get("frontRight");
    tapemeasure=hardwareMap.dcMotor.get("tapemeasure");
        /*
These lines check for the motors in the conifguration file. The program applies the name of the motor in the program (typed first) to the name of the motor
in the configuration file (in green).
 */
    backLeft.setDirection(DcMotor.Direction.REVERSE);                  //setting the motors on the left to be reversed so positive power makes them turn backward.
    frontLeft.setDirection(DcMotor.Direction.REVERSE);
    backRight.setDirection(DcMotor.Direction.FORWARD);
    frontRight.setDirection(DcMotor.Direction.FORWARD);

    //arm = hardwareMap.servo.get("bucket");
    tapearm = hardwareMap.servo.get("tapearm");

    rightFlipper=hardwareMap.servo.get("rightFlipper");
    leftFlipper=hardwareMap.servo.get("leftFlipper");
    shoulder = hardwareMap.servo.get("shoulder");
    elbow = hardwareMap.servo.get("elbow");
    spin = hardwareMap.servo.get("spin");
    grabber = hardwareMap.servo.get("grabber");


    armPosition = 0.67;
    tapePosition = 0.2;


    leftFlipperposition=0.6;               //if gamepad 2 button x is pressed, move the debris flippers back to the starting position
    rightFlipperposition=0.0;

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
    float measure = gamepad2.right_stick_y;


    // clip the right/left values so that the values never exceed +/- 1.
    right = Range.clip(right, -1, 1);
    left = Range.clip(left, -1, 1);
    measure = Range.clip(measure, -1, 1);







    // scale the joystick value to make it easier to control
    // the robot more precisely at slower speeds.
    right = (float)scaleInput(right);
    left =  (float)scaleInput(left);
    measure = (float)scaleInput(measure);
    //writing the value of the joystick on the second remote to the winch motor



    backRight.setPower(right);                   //writing the power values to the motors
    frontRight.setPower(right);
    backLeft.setPower(left);
    frontLeft.setPower(left);
    tapemeasure.setPower(measure);
    // update the position of the arm.

    if (gamepad2.right_trigger>0)  //exact copy of other code, but all commands in this loop are sped up 2x.
    {                               // Normal code is below the if loop
      if (gamepad1.a) {
        // if the A button is pushed on gamepad1, increment the position of
        // the arm servo.
        armPosition += .001;
      }

      if (gamepad1.y) {
        armPosition-= .001;
        // if the Y button is pushed on gamepad1, decrease the position of
        // the arm servo.
      }
      if (gamepad2.dpad_up)
      {
        shoulderPosition+=0.002; // if the Dpad up on gamepad 2 is pressed, add to the position of the shoulder servo.
      }



      if (gamepad2.dpad_down)
      {
        shoulderPosition-=0.002; // if the dpad down on gamepad 2 is pressed, subtract from the position of the shoulder servo.
      }

      if (gamepad2.dpad_left)
      {
        spinPosition+=0.002; // if the dpad left on gamepad 2 is pressed, add to the position of the base servo.
      }

      if (gamepad2.dpad_right)
      {
        spinPosition-=0.002; // if the dpad right on gamepad 2 is pressed, subtract from the position of the base servo.
      }
      if (gamepad2.a)
      {
        elbowPosition+=0.002; // if button A on gamepad 2 is pressed, add the the position of the elbow servo.
      }
      if (gamepad2.y)
      {
        elbowPosition-=0.002; // if button Y on gamepad 2 is pressed, subtract from the position of the elbow servo.
      }
      if (gamepad2.left_bumper)
      {
        grabberPosition+=1.0; // if the left bumper on gamepad 2 is pressed, open the grabber.
      }
      if (gamepad2.right_bumper)
      {
        grabberPosition-=0.0; //if the right bumper on gamepad 2 is pressed, close the grabber.
      }

      if (gamepad1.dpad_right)
      {
        rightFlipperposition=0.35;                     //if gamepad 2 button a is pressed, move the debris flippers such that they make a triangle in front of the robot
        leftFlipperposition=0.25;
      }
      if (gamepad1.dpad_up)
      {
        rightFlipperposition=0;                    //if gamepad 2 button y is pressed, move the debris flippers out to their max positions
        leftFlipperposition= 0.6;
      }

      if (gamepad1.dpad_down)
      {
        leftFlipperposition=0;               //if gamepad 2 button x is pressed, move the debris flippers back to the starting position
        rightFlipperposition=0.6;
      }
      if (gamepad1.left_trigger>0)
      {
        rightFlipperposition=0; //if the left trigger on gamepad 1 is pressed, open up the right flipper to its full position.
      }
      if (gamepad1.right_trigger>0)
      {
        leftFlipperposition=0.6; //if the right trigger on gamepad 1 is pressed, open the left flipper to its full position.
      }

    }
    else {


      if (gamepad1.a) {
        // if the A button is pushed on gamepad1, increment the position of
        // the arm servo.
        armPosition += .001;
      }

      if (gamepad1.y) {
        armPosition-= .001;
        // if the Y button is pushed on gamepad1, decrease the position of
        // the arm servo.
      }
      if (gamepad1.dpad_right) {
        rightFlipperposition = 0.3;                     //if gamepad 2 button a is pressed, move the debris flippers such that they make a triangle in front of the robot
        leftFlipperposition = 0.3;
      }
      if (gamepad1.dpad_up) {
        rightFlipperposition = 0;                    //if gamepad 2 button y is pressed, move the debris flippers out to their max positions
        leftFlipperposition = 0.6;
      }

      if (gamepad1.dpad_down) {
        leftFlipperposition = 0;               //if gamepad 2 button x is pressed, move the debris flippers back to the starting position
        rightFlipperposition = 0.6;

      }

      if (gamepad2.dpad_up) {
        shoulderPosition += 0.001; // if the dpad up on gamepad 2 is pressed, add to the position of the shoulder servo.
      }


      if (gamepad2.dpad_down) {
        shoulderPosition -= 0.001; // if the dpad down on gamepad 2 is pressed, subtract from the position of the shoulder servo.
      }

      if (gamepad2.dpad_left) {
        spinPosition += 0.001; // if the dpad left on gamepad 2 is pressed, add to the position of the base servo.
      }

      if (gamepad2.dpad_right) {
        spinPosition -= 0.001; // if the dpad right on gamepad 2 is pressed, add to the position of the base servo.
      }
      if (gamepad2.a) {
        elbowPosition += 0.001; //if the button a on gamepad 2 is pressed, add to the position of the elbow servo
      }
      if (gamepad2.y) {
        elbowPosition -= 0.001; // if the button a on gamepad 2 is pressed, subtract from the position of the elbow servo.
      }
      if (gamepad2.left_bumper) {
        grabberPosition += 1.0; // if the left bumper on gamepad 2 is pressed, open the grabber.
      }
      if (gamepad2.right_bumper) {
        grabberPosition -= 1.0; // if the right bumper on gamepad 2 is pressed, close the grabber.
      }

      if (gamepad1.left_trigger>0)
      {
        rightFlipperposition=0; //if the left trigger on gamepad 1 is pressed, open the right flapper all the way.
      }
      if (gamepad1.right_trigger>0)
      {
        leftFlipperposition=0.6; // if the right trigger on gamepad 1 is pressed, open the left flapper all the way.
      }
    }



    // clip the position values so that they never exceed their allowed range
    armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
    rightFlipperposition=Range.clip(rightFlipperposition, RIGHT_FLIPPER_MIN_RANGE, RIGHT_FLIPPER_MAX_RANGE);
    leftFlipperposition=Range.clip(leftFlipperposition, LEFT_FLIPPER_MIN_RANGE, LEFT_FLIPPER_MAX_RANGE);
    spinPosition = Range.clip(spinPosition, SPIN_MIN_RANGE, SPIN_MAX_RANGE);
    shoulderPosition = Range.clip(shoulderPosition, SHOULDER_MIN_RANGE, SHOULDER_MAX_RANGE);
    elbowPosition=Range.clip(elbowPosition, ELBOW_MIN_RANGE, ELBOW_MAX_RANGE);
    grabberPosition=Range.clip(grabberPosition, GRABBER_MIN_RANGE, GRABBER_MAX_RANGE);


    // write position values to the wrist and claw servo
    //arm.setPosition(armPosition);
    tapearm.setPosition(armPosition);
    rightFlipper.setPosition(rightFlipperposition);
    leftFlipper.setPosition(leftFlipperposition);
    shoulder.setPosition(shoulderPosition);
    spin.setPosition(spinPosition);
    elbow.setPosition(elbowPosition);
    grabber.setPosition(grabberPosition);

    // slider.setPosition(sliderPosition);

		/*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */
    telemetry.addData("Text", "*** Robot Data***");
    telemetry.addData("Grabber: ", "grabber is at   " + String.format("%.2f", grabber.getPosition()));
    telemetry.addData("spin", "spin:  " + String.format("%.2f", spinPosition));
    telemetry.addData("elbow", "position:  " + String.format("%.2f", elbowPosition));
    telemetry.addData("shoulder", "position:  " + String.format("%.2f", shoulderPosition));
    telemetry.addData("tapearm", "position:  " + String.format("%.2f", armPosition));

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
  double scaleInput(double dVal)  {
    double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
            0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

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
    double dScale = 0.0;
    if (dVal < 0) {
      dScale = -scaleArray[index];
    } else {
      dScale = scaleArray[index];
    }

    // return scaled value.
    return dScale;
  }

}
