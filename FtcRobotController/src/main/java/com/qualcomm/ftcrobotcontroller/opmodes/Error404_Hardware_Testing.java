package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Error404_Hardware_Testing extends Error404_Hardware_14July16 {

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
        rf_Reset_Encoder();
        set_power(.2, rightFront);
        set_position(rightFront, 1140);
    }

    public void loop() {
        telemetry.addData("00 rf Power", rf_Get_Power_Tele());
        telemetry.addData("02 rf Direction", rf_Get_Direction_Tele());
        telemetry.addData("03 rf Mode", rf_Get_Mode_Tele());
        telemetry.addData("01 rf Position", rf_Get_Position_Tele());
    }

    public void stop() {
    }
}