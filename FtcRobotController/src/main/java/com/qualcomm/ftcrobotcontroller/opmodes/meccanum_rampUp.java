package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

public class meccanum_rampUp extends Error404_Hardware_Tier2 {
  DcMotor rightFront;
  DcMotor leftFront;
  DcMotor rightRear;
  DcMotor leftRear;
  DcMotor ballcollector;
  public meccanum_rampUp() {
  }
  @Override
  public void init() {
   // ballcollector = hardwareMap.dcMotor.get("ballcollector");
    ballcollector.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
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

  }
  @Override
  public void loop() {

    double LFPOWER=0;
    double RFPOWER=0;
    double LRPOWER=0;
    double RRPOWER=0;

    float yL_val = -gamepad1.left_stick_y/2;            //reading raw values from the joysticks
    float xL_val = gamepad1.left_stick_x/2;            //reading raw values from the joysticks
    float xR_val = gamepad1.right_stick_x/2;
    //clip the right/left values so that the values never exceed +/- 1.
//    yL_val = Range.clip(yL_val, -1, 1);
//    xL_val = Range.clip(xL_val, -1, 1);
//    xR_val = Range.clip(xR_val, -1, 1);
    yL_val = (float) scaleInput(yL_val);
    xL_val = (float) scaleInput(xL_val);
    xR_val = (float) scaleInput(xR_val);
    float RF =(yL_val-xR_val-xL_val);
    float LF =(yL_val+xR_val+xL_val);
    float RR= (yL_val-xR_val+xL_val);
    float LR =(yL_val+xR_val-xL_val);

    RF = Range.clip(RF, -1, 1);
    LF = Range.clip(LF, -1, 1);
    RR = Range.clip(RR, -1, 1);
    LR = Range.clip(LR, -1, 1);

    float ballcollectorspeed = gamepad1.right_trigger-gamepad1.left_trigger;

    ballcollectorspeed = Range.clip(ballcollectorspeed, -1, 1);
    ballcollectorspeed = (float) scaleInput(ballcollectorspeed);

    ballcollector.setPower(ballcollectorspeed);

//    RF=(float)(ramp_up(rightFront.getPower(),RF,RFPOWER));
//    LF=(float)(ramp_up(leftFront.getPower(),LF,LFPOWER));
//    RR=(float)(ramp_up(rightRear.getPower(),RR,RRPOWER));
//    LR=(float)(ramp_up(leftRear.getPower(),LR,LRPOWER));

    RF=(float)(rampUpMethod(rightFront.getPower(),RF,0.1));
    LF=(float)(rampUpMethod(leftFront.getPower(),RF,0.1));
    RR=(float)(rampUpMethod(rightRear.getPower(),RF,0.1));
    LR=(float)(rampUpMethod(leftRear.getPower(),RF,0.1));
    rightFront.setPower(RF);
    leftFront.setPower(LF);
    rightRear.setPower(RR);
    leftRear.setPower(LR);

    telemetry.addData( "01", "Left Drive: " + leftFront.getPower ());
    telemetry.addData( "02", "Right Drive: " + rightFront.getPower ());
    telemetry.addData ("05", "Gamepad1 Left: " + -gamepad1.left_stick_y);
    telemetry.addData ("06", "Gamepad1 Right: " + -gamepad1.right_stick_y);
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
