package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

public class twoMotorTest extends OpMode {
  DcMotor rightmotor;
  DcMotor leftmotor;
  public twoMotorTest() {
  }
  @Override
  public void init() {
    rightmotor = hardwareMap.dcMotor.get("right");                  //linking all the motors/servos from what they are called in the programming (far left)
    leftmotor = hardwareMap.dcMotor.get("left");                    //with what they are called in the config file (in green)
    rightmotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    leftmotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

  }
  @Override
  public void loop() {
    float left = gamepad1.left_stick_y;            //reading raw values from the joysticks
    float right = gamepad1.right_stick_y;
    // clip the right/left values so that the values never exceed +/- 1.
    right = Range.clip(right, -1, 1);
    left = Range.clip(left, -1, 1);
    right = (float) scaleInput(right);
    left = (float) scaleInput(left);

       rightmotor.setPower(right);                   //writing the power values to the motors
       leftmotor.setPower(left);

telemetry.addData("power: ", right);
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
