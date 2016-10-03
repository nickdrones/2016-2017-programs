package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class meccanum extends OpMode {
  DcMotor rightFront;
  DcMotor leftFront;
  DcMotor rightRear;
  DcMotor leftRear;
  public meccanum() {
  }
  @Override
  public void init() {
    leftFront = hardwareMap.dcMotor.get("leftFront");
    rightFront = hardwareMap.dcMotor.get("rightFront");
    leftFront.setDirection(DcMotor.Direction.FORWARD);
    rightFront.setDirection(DcMotor.Direction.REVERSE);
    leftRear = hardwareMap.dcMotor.get("leftRear");
    rightRear = hardwareMap.dcMotor.get("rightRear");
    leftRear.setDirection(DcMotor.Direction.FORWARD);
    rightRear.setDirection(DcMotor.Direction.REVERSE);
  }
  @Override
  public void loop() {
    float yL_val = -gamepad1.left_stick_y;            //reading raw values from the joysticks
    float yR_val = gamepad1.right_stick_y;
    float xL_val = gamepad1.left_stick_x;            //reading raw values from the joysticks
    float xR_val = gamepad1.right_stick_x;
    //clip the right/left values so that the values never exceed +/- 1.
    yL_val = Range.clip(yL_val, -1, 1);
    yR_val = Range.clip(yR_val, -1, 1);
    xL_val = Range.clip(xL_val, -1, 1);
    xR_val = Range.clip(xR_val, -1, 1);
    yL_val = (float) scaleInput(yL_val);
    yR_val = (float) scaleInput(yR_val);
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
