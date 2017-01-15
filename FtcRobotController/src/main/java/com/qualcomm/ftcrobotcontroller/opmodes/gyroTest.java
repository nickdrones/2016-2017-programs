package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.Range;

/*
 * You can use the X button on either gamepad to turn the LED on and off.
 */
public class gyroTest extends LinearOpMode {
    GyroSensor gyro;
    //Servo tester;
    @Override
  public void runOpMode() throws InterruptedException {
    // write some device information (connection info, name and type)
    // to the log file.
    hardwareMap.logDevices();
    // Assumes config file matches these deignations
      //ie: mr, ods, touch, ir
        gyro = hardwareMap.gyroSensor.get("gyro");
       // tester = hardwareMap.servo.get("tester");

      //////////////////////////////////
      //    Color Sensor preps       //
      // turn the RGB LED on in the beginning, just so user will know that the sensor is active.
      // hsvValues is an array that will hold the hue, saturation, and value information.
      // bPrevState and bCurrState represent the previous and current state of the button.
      //////////////////////////////////////////////////////////////////////////////////

    waitOneFullHardwareCycle();
        gyro.calibrate();
        while (gyro.isCalibrating())  {
            Thread.sleep(50);           //
        }
    // wait for the start button to be pressed.

    waitForStart();
        while (opModeIsActive()) {

            int xVal = gyro.rawX();
            int yVal = gyro.rawY();
            int zVal = gyro.rawZ();
            int heading = gyro.getHeading();
            telemetry.addData("14. h", String.format("%03d", heading));
            // wait a hardware cycle before iterating.
      waitOneFullHardwareCycle();
    }
  }
}
