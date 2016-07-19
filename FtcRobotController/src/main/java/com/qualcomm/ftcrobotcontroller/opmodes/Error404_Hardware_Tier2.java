package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Error404_Hardware_Tier2 extends Error404_Hardware_Tier1 {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;

    public void init() {}
    public void start() {}
    public void loop() {}
    public void stop() {}

    public void left_Set_Power(double power)
    {
        set_power(power, leftFront);
        set_power(power, leftRear);
    }

    public void right_Set_Power(double power)
    {
        set_power(power, rightFront);
        set_power(power, rightRear);
    }


}