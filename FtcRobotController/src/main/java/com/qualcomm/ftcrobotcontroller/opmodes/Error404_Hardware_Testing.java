package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Error404_Hardware_Testing extends Error404_Hardware_Tier1 {

     public void start() {
        set_mode(rightFront, "RTP");
        set_direction(leftFront, "F");
        set_power(1.0, rightFront);
        set_position(rightFront, 1140);
        }

    public void loop() {
        motor_telemetry(rightFront);
        }

    public void stop() {
       }
}