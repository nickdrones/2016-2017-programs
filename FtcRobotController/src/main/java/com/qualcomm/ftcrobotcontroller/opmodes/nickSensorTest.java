package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
/*
 * You can use the X button on either gamepad to turn the LED on and off.
 */
public class nickSensorTest extends LinearOpMode {
  ColorSensor RGB;
    Servo tester;
    @Override
  public void runOpMode() throws InterruptedException {
    // write some device information (connection info, name and type)
    // to the log file.
    hardwareMap.logDevices();
    // Assumes config file matches these deignations
      //ie: mr, ods, touch, ir
     RGB = hardwareMap.colorSensor.get("mr");
        tester = hardwareMap.servo.get("tester");

      //////////////////////////////////
      //    Color Sensor preps       //
      // turn the RGB LED on in the beginning, just so user will know that the sensor is active.
      // hsvValues is an array that will hold the hue, saturation, and value information.
      // bPrevState and bCurrState represent the previous and current state of the button.
      //////////////////////////////////////////////////////////////////////////////////
        RGB.enableLed(true);
       float hsvValues[] = {0F,0F,0F};
//      boolean bPrevState = false;
//      boolean bCurrState = false;
        // wait one cycle.
    waitOneFullHardwareCycle();

    // wait for the start button to be pressed.
    waitForStart();

        while (opModeIsActive()) {
            /////////////////////////////////////////////////////////
            //     Color sensor actions                            //
            ////////////////////////////////////////////////////////
            // check the status of the x button on gamepad1.
//            bCurrState = gamepad1.x;
//            // check for button state transitions.
//            if (bCurrState == true && bCurrState != bPrevState)  {
//                // button is transitioning to a pressed state.
//                // update previous state variable.
//                bPrevState = bCurrState;
//                // turn on the LED.
//                RGB.enableLed(true);
//            } else if (bCurrState == false && bCurrState != bPrevState)  {
//                // button is transitioning to a released state.
//                // update previous state variable.
//                bPrevState = bCurrState;
//                // turn off the LED.
//                RGB.enableLed(false);
//            }
            // convert the RGB values to HSV values.
            Color.RGBToHSV(RGB.red()*8, RGB.green()*8, RGB.blue()*8, hsvValues);

            if(RGB.red()>RGB.blue())
            {
                telemetry.addData("","It's Red");
                tester.setPosition(1);
            }
            if(RGB.blue()>RGB.red())
            {
                telemetry.addData("","It's blue");
                tester.setPosition(0);
            }
      telemetry.addData("01 - White", RGB.alpha());
      telemetry.addData("02 - Red  ", RGB.red());
      telemetry.addData("03 - Green", RGB.green());
      telemetry.addData("04 - Blue ", RGB.blue());
      telemetry.addData("05 - Hue", hsvValues[0]);
            // wait a hardware cycle before iterating.
      waitOneFullHardwareCycle();
    }
  }
}