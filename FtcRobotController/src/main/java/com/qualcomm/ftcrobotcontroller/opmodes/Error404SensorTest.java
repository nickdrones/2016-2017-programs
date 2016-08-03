package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;

/*
 *
 * This is an example LinearOpMode that shows how to use
 * a Modern Robotics Sensors.  Includes portions from:
 * MRRGBExample
 *
 * You can use the X button on either gamepad to turn the LED on and off.
 *
 */
public class Error404SensorTest extends LinearOpMode {

  ColorSensor RGB;
    OpticalDistanceSensor ods;
    TouchSensor touch;
    IrSeekerSensor ir;
    GyroSensor gyro;

    @Override
  public void runOpMode() throws InterruptedException {

    // write some device information (connection info, name and type)
    // to the log file.
    hardwareMap.logDevices();

    // Assumes config file matches these deignations
      //ie: mr, ods, touch, ir
     RGB = hardwareMap.colorSensor.get("mr");
     ods = hardwareMap.opticalDistanceSensor.get("ods");
     touch = hardwareMap.touchSensor.get("touch");
     ir = hardwareMap.irSeekerSensor.get ("ir");
     gyro = hardwareMap.gyroSensor.get("gyro");

      //////////////////////////////////
      //    Color Sensor preps       //
      // turn the RGB LED on in the beginning, just so user will know that the sensor is active.
      // hsvValues is an array that will hold the hue, saturation, and value information.
      // bPrevState and bCurrState represent the previous and current state of the button.
      //////////////////////////////////////////////////////////////////////////////////
        RGB.enableLed(true);
       float hsvValues[] = {0F,0F,0F};
      boolean bPrevState = false;
      boolean bCurrState = false;

      ////////////////////////////////////////
      //         gyro preps
      //      calibrate, wait with robot very still till
      //  mode shows calibration is complete
      ////////////////////////////////////////////
        int xVal, yVal, zVal = 0;
        int heading, zAccumulated = 0;

        gyro.calibrate();
       while (gyro.isCalibrating())  {
            Thread.sleep(50);           //
        }

        // wait one cycle.
    waitOneFullHardwareCycle();

    // wait for the start button to be pressed.
    waitForStart();

             // while the op mode is active, loop and read the sensor data.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {


            /////////////////////////////////////////////////////////
            //     Color sensor actions                            //
            ////////////////////////////////////////////////////////
            // check the status of the x button on gamepad1.
            bCurrState = gamepad1.x;

            // check for button state transitions.
            if (bCurrState == true && bCurrState != bPrevState)  {
                // button is transitioning to a pressed state.

                // update previous state variable.
                bPrevState = bCurrState;

                // turn on the LED.
                RGB.enableLed(true);
            } else if (bCurrState == false && bCurrState != bPrevState)  {
                // button is transitioning to a released state.

                // update previous state variable.
                bPrevState = bCurrState;

                // turn off the LED.
                RGB.enableLed(false);
            }

            // convert the RGB values to HSV values.
            //Color.RGBToHSV((sensorRGB.red() * 8), (sensorRGB.green() * 8), (sensorRGB.blue() * 8), hsvValues);
            Color.RGBToHSV(RGB.red()*8, RGB.green()*8, RGB.blue()*8, hsvValues);


            /////////////////////////////////////////////////////////
            //    IR Sensor                                        //
            ////////////////////////////////////////////////////////
            double irAngle = ir.getAngle ();
            double irStrength =ir.getStrength ();
            boolean irSignalDetected=ir.signalDetected();


            /////////////////////////////////////////////////////////
            //     Light sensor actions
            // if press game pad 1 Y, LED will turn on.
            //  light detected = raw / 1023; So, 0-1.00 range
            ////////////////////////////////////////////////////////
            double lightDetected=ods.getLightDetected ();//push gamepad1Y to enable LED


            /////////////////////////////////////////////////////////
            //     Touch sensor actions
            ////////////////////////////////////////////////////////
            boolean touchPressed = touch.isPressed();


            /////////////////////////////////////////////////////////
            //     gyro sensor actions
            ////////////////////////////////////////////////////////
            // if the A and B buttons are pressed, reset Z heading.
            if(gamepad1.a && gamepad1.b)  {
                // reset heading.
                gyro.resetZAxisIntegrator();
            }

            // get the x, y, and z values (rate of change of angle) plus heading for z axis.
            xVal = gyro.rawX();
            yVal = gyro.rawY();
            zVal = gyro.rawZ();
            heading = gyro.getHeading();
         //   zAccumulated = gyro.getIntegratedZValue();


          /////////////////////////////////////////////////////////
        //     Telemetry actions
        //    Note:%03d means format integer with 3 digits, left padding with zeroes
        ////////////////////////////////////////////////////////
      // send the info back to driver station using telemetry function.
      telemetry.addData("01 - White", RGB.alpha());
      telemetry.addData("02 - Red  ", RGB.red());
      telemetry.addData("03 - Green", RGB.green());
      telemetry.addData("04 - Blue ", RGB.blue());
      telemetry.addData("05 - Hue", hsvValues[0]);
      telemetry.addData("06 - % Light Detected", lightDetected*100.0);
      telemetry.addData("07 - Touch pressed", touchPressed);
      telemetry.addData("08- IR Angle", irAngle);
      telemetry.addData("09- IR Strength", irStrength);
      telemetry.addData("10- IR Signal detected", irSignalDetected);
      telemetry.addData("11. x", String.format("%03d", xVal));
      telemetry.addData("12. y", String.format("%03d", yVal));
      telemetry.addData("13. z", String.format("%03d", zVal));
      telemetry.addData("14. h", String.format("%03d", heading));


            // wait a hardware cycle before iterating.
      waitOneFullHardwareCycle();
    }
  }
}
