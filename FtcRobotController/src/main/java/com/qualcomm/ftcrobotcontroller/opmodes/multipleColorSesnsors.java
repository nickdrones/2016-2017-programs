package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;
import android.widget.BaseExpandableListAdapter;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsUsbDeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.I2cController;
import com.qualcomm.robotcore.hardware.I2cControllerPortDeviceImpl;
import com.qualcomm.robotcore.util.RobotLog;
import com.qualcomm.robotcore.util.TypeConversion;



/*
 * You can use the X button on either gamepad to turn the LED on and off.
 */
public class multipleColorSesnsors extends LinearOpMode {
    ColorSensor RGB;
    ColorSensor beacon;
    //Servo tester;
    @Override
    public void runOpMode() throws InterruptedException {
        // write some device information (connection info, name and type)
        // to the log file.
        //hardwareMap.logDevices();
        // Assumes config file matches these deignations
        //ie: mr, ods, touch, ir
        RGB = hardwareMap.colorSensor.get("mr");
        RGB.setI2cAddress(30);       //30 is the decimal conversion from 7 bit hexadecimal value 0x1e converted from 8 bit hexadecimal 0x3c
        beacon = hardwareMap.colorSensor.get("beacon");
        beacon.setI2cAddress(38);       //38 is the decimal conversion from 7 bit hexadecimal value 0x26 converted from 8 bit hexadecimal 0x4c

//        beacon = hardwareMap.colorSensor.get("beacon");
//        beacon.setI2cAddress(0x26);

       RGB.enableLed(false);
       beacon.enableLed(false);

        // wait one cycle.
        waitOneFullHardwareCycle();

        waitForStart();
        while (opModeIsActive()) {

            telemetry.addData("01 - White down", RGB.red());
            telemetry.addData("01.5 - I2C down", RGB.getI2cAddress());
            telemetry.addData("02 - White out", beacon.red());
            telemetry.addData("02.5 - I2C out", beacon.getI2cAddress());

            // wait a hardware cycle before iterating.
            waitOneFullHardwareCycle();
        }
    }
}