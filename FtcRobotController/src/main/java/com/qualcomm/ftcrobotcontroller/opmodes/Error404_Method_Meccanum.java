package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.Range;

public class Error404_Method_Meccanum extends Error404_Hardware_Tier2 {

     public void start() {
         set_mode(rightFront, "RTP");
         set_mode(leftFront, "RTP");
         set_mode(rightRear, "RTP");
         set_mode(leftRear, "RTP");
        set_direction(leftFront, "F");
         set_direction(leftFront, "F");
         set_direction(leftFront, "F");
         set_direction(leftFront, "F");
     }
@Override
    public void loop() {
        motorTelemetry(rightFront);
        float yL_val = -gamepad1.left_stick_y;            //reading raw values from the joysticks
        float xL_val = gamepad1.left_stick_x;            //reading raw values from the joysticks
        float xR_val = gamepad1.right_stick_x;
        yL_val = Range.clip(yL_val, -1, 1);
        xL_val = Range.clip(xL_val, -1, 1);
        xR_val = Range.clip(xR_val, -1, 1);
        yL_val = (float) scaleInput(yL_val);
        xL_val = (float) scaleInput(xL_val);
        xR_val = (float) scaleInput(xR_val);
        float RF =(yL_val-xR_val-xL_val);
        float LF =(yL_val+xR_val+xL_val);
        float RR= (yL_val-xR_val+xL_val);
        float LR =(yL_val+xR_val-xL_val);
        RF = (int) Range.clip(RF, -1, 1);
        LF = (int) Range.clip(LF, -1, 1);
        RR = (int) Range.clip(RR, -1, 1);
        LR = (int) Range.clip(LR, -1, 1);
        set_power(RF, rightFront);
        set_power(RR, rightRear);
        set_power(LF, leftFront);
        set_power(LR, leftRear);

    }
    @Override
    public void stop() {
    }
        double scaleInput(double dVal) {
            double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                    0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};
            // get the corresponding index for the scaleInput array.
            int index = (int) (dVal * 16.0);

            // index should be positive.
            if (index < 0) {
                index = -index;
            }

            // index cannot exceed size of array minus 1.
            if (index > 16) {
                index = 16;
            }

            // get value from the array.
            double dScale;
            if (dVal < 0) {
                dScale = -scaleArray[index];
            } else {
                dScale = scaleArray[index];
            }

            // return scaled value.
            return dScale;
        }
       }