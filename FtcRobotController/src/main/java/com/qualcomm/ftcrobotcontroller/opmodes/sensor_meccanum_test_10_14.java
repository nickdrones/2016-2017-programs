package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class sensor_meccanum_test_10_14 extends LinearOpMode{
  DcMotor rightFront;
  DcMotor leftFront;
  DcMotor rightRear;
  DcMotor leftRear;
  public sensor_meccanum_test_10_14() {
  }
  @Override
  public void runOpMode() throws InterruptedException {
//  @Override
//  public void init() {
    leftFront = hardwareMap.dcMotor.get("leftFront");
    rightFront = hardwareMap.dcMotor.get("rightFront");
    leftFront.setDirection(DcMotor.Direction.FORWARD);
    rightFront.setDirection(DcMotor.Direction.REVERSE);
    leftRear = hardwareMap.dcMotor.get("leftRear");
    rightRear = hardwareMap.dcMotor.get("rightRear");
    leftRear.setDirection(DcMotor.Direction.FORWARD);
    rightRear.setDirection(DcMotor.Direction.REVERSE);
    float yL_val = 0;
    float xL_val =-100;
    float xR_val = 0;
    float RF =(yL_val-xR_val-xL_val);
    float LF =(yL_val+xR_val+xL_val);
    float RR= (yL_val-xR_val+xL_val);
    float LR =(yL_val+xR_val-xL_val);

    RF = Range.clip(RF, -1, 1);
    LF = Range.clip(LF, -1, 1);
    RR = Range.clip(RR, -1, 1);
    LR = Range.clip(LR, -1, 1);

    waitForStart();

    rightFront.setPower(RF);
    leftFront.setPower(LF);
    rightRear.setPower(RR);
    leftRear.setPower(LR);
    wait(1000);
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
