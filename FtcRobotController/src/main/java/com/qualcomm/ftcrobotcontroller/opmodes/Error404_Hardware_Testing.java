package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Error404_Hardware_Testing extends Error404_Hardware_Tier2 {

     public void start() {
         slide_sideways("RTP",1,"r",5);
     }

    public void loop() {
        motorTelemetry(rightFront);
        }

    public void stop() {
       }
}