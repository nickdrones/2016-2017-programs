package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class ServoBallLifterTest extends OpMode {
    private Servo liftLeft1;
    private Servo liftLeft2;
    private Servo liftLeft3;
    private Servo liftLeft4;
    private Servo liftRight1;
    private Servo liftRight2;
    private Servo liftRight3;
    private Servo liftRight4;

  public ServoBallLifterTest() {
  }
  @Override
  public void init() {
      telemetry.addData ("0", "I AM HERE");
      liftLeft1 = hardwareMap.servo.get("liftLeft1");
      liftLeft2 = hardwareMap.servo.get("liftLeft2");
      liftLeft3 = hardwareMap.servo.get("liftLeft3");
      liftLeft4 = hardwareMap.servo.get("liftLeft4");
      liftRight1 = hardwareMap.servo.get("liftRight1");
      liftRight2 = hardwareMap.servo.get("liftRight2");
      liftRight3 = hardwareMap.servo.get("liftRight3");
      liftRight4 = hardwareMap.servo.get("liftRight4");

      liftLeft1.setPosition(.5);
      liftLeft2.setPosition(.5);
      liftLeft3.setPosition(.5);
      liftLeft4.setPosition(.5);
      liftRight1.setPosition(.5);
      liftRight2.setPosition(.5);
      liftRight3.setPosition(.5);
      liftRight4.setPosition(.5);
  }
  @Override
  public void loop() {

      if(gamepad1.a)
      {
          liftLeft1.setPosition(.5);
          liftLeft2.setPosition(.5);
          liftLeft3.setPosition(.5);
          liftLeft4.setPosition(.5);
          liftRight1.setPosition(.5);
          liftRight2.setPosition(.5);
          liftRight3.setPosition(.5);
          liftRight4.setPosition(.5);
      }
      if(gamepad1.b)
      {
          liftLeft1.setPosition(.25);
          liftLeft2.setPosition(.25);
          liftLeft3.setPosition(.25);
          liftLeft4.setPosition(.25);
          liftRight1.setPosition(.25);
          liftRight2.setPosition(.25);
          liftRight3.setPosition(.25);
          liftRight4.setPosition(.25);
      }
      if(gamepad1.x)
      {
          liftLeft1.setPosition(.75);
          liftLeft2.setPosition(.75);
          liftLeft3.setPosition(.75);
          liftLeft4.setPosition(.75);
          liftRight1.setPosition(.75);
          liftRight2.setPosition(.75);
          liftRight3.setPosition(.75);
          liftRight4.setPosition(.75);
      }

      if(gamepad1.y)
      {
          liftLeft1.setPosition(1);
          liftLeft2.setPosition(1);
          liftLeft3.setPosition(1);
          liftLeft4.setPosition(1);
          liftRight1.setPosition(1);
          liftRight2.setPosition(1);
          liftRight3.setPosition(1);
          liftRight4.setPosition(1);
      }

      if(gamepad1.dpad_right)
      {
          liftLeft1.setPosition(.15);
          liftLeft2.setPosition(.15);
          liftLeft3.setPosition(.15);
          liftLeft4.setPosition(.15);
          liftRight1.setPosition(.15);
          liftRight2.setPosition(.15);
          liftRight3.setPosition(.15);
          liftRight4.setPosition(.15);
      }

  }
  @Override
  public void stop() {
  }

}