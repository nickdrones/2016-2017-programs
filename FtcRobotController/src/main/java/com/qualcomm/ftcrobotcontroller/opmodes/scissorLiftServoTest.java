package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class scissorLiftServoTest extends OpMode {
  final static double SPIN_MIN_RANGE = 0;
  final static double SPIN_MAX_RANGE = 1;
  double spinPosition = 0;
  Servo spin;
  public scissorLiftServoTest() {
  }
  @Override
  public void init() {
    spin = hardwareMap.servo.get("spin");
  }
  @Override
  public void loop() {
      if (gamepad1.a) {
        spinPosition += 0.002; // if the dpad left on gamepad 2 is pressed, add to the position of the base servo.
      }
      if (gamepad1.y) {
        spinPosition -= 0.002; // if the dpad right on gamepad 2 is pressed, subtract from the position of the base servo.
      }
       spinPosition = Range.clip(spinPosition, SPIN_MIN_RANGE, SPIN_MAX_RANGE);
    spin.setPosition(spinPosition);
    telemetry.addData("Text", "*** Robot Data***");
    telemetry.addData("spin", "spin:  " + String.format("%.2f", spinPosition));
  }
  @Override
  public void stop() {
  }
}