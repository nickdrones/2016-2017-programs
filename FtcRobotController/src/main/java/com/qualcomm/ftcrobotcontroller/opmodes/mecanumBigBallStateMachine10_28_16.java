package com.qualcomm.ftcrobotcontroller.opmodes;

public class mecanumBigBallStateMachine10_28_16 extends Error404_Hardware_Tier2

{
    private int state = 0;
    public mecanumBigBallStateMachine10_28_16()
    {
    }
    @Override public void start(){
        resetAllEncoders_withWait();
    }
    @Override public void loop ()
    {
        switch (state)
        {   case 0:
            resetAllEncoders_withWait();
            state++;
            break;
            case 1:
                driveStright("RUE",0.1,"f",0);
                if (is_encoder_reached(distance2encoder(36,4,1.5), leftFront)) {
                    state++;
                }
                break;
            case 2:
                set_power(0, leftFront);
                set_power(0, rightFront);
                set_power(0, leftRear);
                set_power(0, rightRear);
                resetAllEncoders_noWait();
                if(is_encoder_reset(leftFront)&&is_encoder_reset(rightFront)&&is_encoder_reset(leftRear)&&is_encoder_reset(rightRear))
                    state++;
                break;
            case 3:
                pointTurn("RUE", .3, "l", 0);
                if (is_encoder_reached(distance2encoder(9,4,1.5), leftFront))
                {
                    state++;
                }
                break;
            case 4:
                set_power(0, leftFront);
                set_power(0, rightFront);
                set_power(0, leftRear);
                set_power(0, rightRear);
                resetAllEncoders_noWait();
                if(is_encoder_reset(leftFront)&&is_encoder_reset(rightFront)&&is_encoder_reset(leftRear)&&is_encoder_reset(rightRear))
                    state++;
                break;
            case 5:
                driveStright("RUE",.1,"f",0);
                set_power(1,ballCollector);
                if (is_encoder_reached(distance2encoder(18,4,1.5), leftFront))
                {
                    state++;
                }
                break;
            case 6:
                set_power(0, leftFront);
                set_power(0, rightFront);
                set_power(0, leftRear);
                set_power(0, rightRear);
                set_power(0, ballCollector);
                state++;
                break;
            default:
                break;


        }
        telemetry.addData("RightFront: ", get_position(rightFront));
        telemetry.addData("LeftFront: ", get_position(leftFront));
        telemetry.addData("RightRear: ", get_position(rightRear));
        telemetry.addData("LeftRear: ", get_position(leftRear));
        telemetry.addData("State: ", state);

    } // loop



} //
