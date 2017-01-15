package com.qualcomm.ftcrobotcontroller.opmodes;

public class crossFieldTroll extends Error404_Hardware_Tier2

{
    private int state = 0;
    private int motorDelta = 0;
    public crossFieldTroll()
    {
    }
    @Override public void start(){
        gyroCalibrate();
        resetAllEncoders_withWait();
        motorDelta = get_position(leftFront);
    }
    @Override public void loop ()
    {
        switch (state)
        {   case 0:
            try {
                Thread.sleep(10000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            state++;
            break;
            case 1:
                driveStright("RUE",0.7,"f",0);
                if (is_encoder_reached(6000, leftFront)) {
                    set_power(0, leftFront);
                    set_power(0, rightFront);
                    set_power(0, leftRear);
                    set_power(0, rightRear);
                    motorDelta = get_position(leftFront);
                    state++;
                }
                break;
            default:
                break;


        }
        telemetry.addData("2RightFront: ", get_position_tele(rightFront));
        telemetry.addData("2LeftFront: ", get_position_tele(leftFront));
        telemetry.addData("2RightRear: ", get_position_tele(rightRear));
        telemetry.addData("2LeftRear: ", get_position_tele(leftRear));
        telemetry.addData("1State: ", state);

    } // loop



} //
