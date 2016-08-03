package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class ILOVEROBOTICS_teleop extends OpMode{
DcMotor leftMotor;
DcMotor rightMotor;
    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("leftFront");
        rightMotor = hardwareMap.dcMotor.get("rightFront");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }
    @Override
    public void loop() {
        float left = gamepad1.left_stick_y;            //reading raw values from the joysticks
        float right = gamepad1.right_stick_y;
        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);
        rightMotor.setPower(right);
        leftMotor.setPower(left);
        telemetry.addData( "01", "Left Drive: " + leftMotor.getPower ());
        telemetry.addData( "02", "Right Drive: " + rightMotor.getPower ());
        telemetry.addData ("05", "Gamepad1 Left: " + -gamepad1.left_stick_y);
        telemetry.addData ("06", "Gamepad1 Right: " + -gamepad1.right_stick_y);
    }
}








