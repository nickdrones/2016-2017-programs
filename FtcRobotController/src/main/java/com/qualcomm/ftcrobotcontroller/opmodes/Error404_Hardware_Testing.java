package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Error404_Hardware_Testing extends Error404_Hardware_Tier2 {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;

    public void init() {
        hardware_map();
    }

    public void start() {
        set_direction(rightFront, "F");
        set_power(1.0, rightFront);
        set_mode(rightFront, "RTP");
        set_position(rightFront, 1140);
        reset_encoder(rightFront);
        set_power(.2, rightFront);
        set_position(rightFront, 1140);
    }

    public void loop() {
        telemetry.addData("00 rf Power", get_power_tele(rightFront));
        telemetry.addData("02 rf Direction", get_direction(rightFront));
        telemetry.addData("03 rf Mode", get_mode(rightFront));
        telemetry.addData("01 rf Position", get_position_tele(rightFront));
    }

    public void stop() {
    }
}