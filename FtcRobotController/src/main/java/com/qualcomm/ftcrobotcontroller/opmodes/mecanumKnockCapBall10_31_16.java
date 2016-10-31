package com.qualcomm.ftcrobotcontroller.opmodes;

public class mecanumKnockCapBall10_31_16 extends Error404_Hardware_Tier2

{
    private int state = 0;
    private int motorDelta = 0;
    public mecanumKnockCapBall10_31_16()
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
                driveStright("RUE",0.7,"f",0);
                if (is_encoder_reached(motorDelta + distance2encoder(60,4,1.5), leftFront)) {
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
