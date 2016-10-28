package com.qualcomm.ftcrobotcontroller.opmodes;

public class mecanumCapBallStateMachine10_28_16 extends Error404_Hardware_Tier2

{
    private int state = 0;
    private int que = 0;
    public mecanumCapBallStateMachine10_28_16()
    {
    }
    @Override public void start(){
        gyroCalibrate();
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
                driveStright("RUE",0.2,"f",0);
                if (is_encoder_reached(distance2encoder(36,4,1.5), leftFront)) {
                    set_power(0, leftFront);
                    set_power(0, rightFront);
                    set_power(0, leftRear);
                    set_power(0, rightRear);
                    que = get_position(leftFront);
                    state++;
                }
                break;
            case 2:
                pointTurn("RUE", .15, "l", 0);
                if (gyro.getHeading()<= 330 && gyro.getHeading() > 10)
                {
                    set_power(0, leftFront);
                    set_power(0, rightFront);
                    set_power(0, leftRear);
                    set_power(0, rightRear);
                    state++;
                }
                break;
            case 3:
                driveStright("RUE",0,"f",0);
                que = get_position(leftFront);
                state++;
                break;
            case 4:
                set_power(1,ballCollector);
                driveStright("RUE",.1,"f",0);
                if (is_encoder_reached((que + distance2encoder(18,4,1.5)), leftFront))
                {
                    set_power(0, leftFront);
                    set_power(0, rightFront);
                    set_power(0, leftRear);
                    set_power(0, rightRear);
                    set_power(0, ballCollector);
                    state++;
                }
                break;
            case 5:
                driveStright("RUE",0,"f",0);
                que = get_position(leftFront);
                state++;
                break;
            case 6:
                driveStright("RUE",.05,"r",0);
                if(is_encoder_reached (((-que) + distance2encoder(3,4,1.5)), leftFront));
                {
                    set_power(0, leftFront);
                    set_power(0, rightFront);
                    set_power(0, leftRear);
                    set_power(0, rightRear);
                    state++;
                }
                break;
//            case 5:
//                pointTurn("RUE", .15, "r", 0);
//                if (gyro.getHeading()> 40 && gyro.getHeading() < 50)
//                {
//                    state++;
//                }
//                break;
//            case 10:
//                set_power(0, leftFront);
//                set_power(0, rightFront);
//                set_power(0, leftRear);
//                set_power(0, rightRear);
//                resetAllEncoders_noWait();
//                if(is_encoder_reset(leftFront) && is_encoder_reset(rightFront) && is_encoder_reset(leftRear) && is_encoder_reset(rightRear))
//                    state++;
//                break;
//            case 11:
//                driveStright("RUE",.1,"f",0);
//                if(is_encoder_reached(distance2encoder(7,4,1.5),leftFront))
//                {
//                    state++;
//                }
//                break;
//            case 12:
//                set_power(0, leftFront);
//                set_power(0, rightFront);
//                set_power(0, leftRear);
//                set_power(0, rightRear);
//                resetAllEncoders_noWait();
//                if(is_encoder_reset(leftFront) && is_encoder_reset(rightFront) && is_encoder_reset(leftRear) && is_encoder_reset(rightRear))
//                    state++;
//                break;
//            case 13:
//                pointTurn("RUE", .15, "r", 0);
//                if (gyro.getHeading() < 330 && gyro.getHeading() > 10)
//                {
//                    state++;
//                }
//                break;
//            case 14:
//                driveStright("RUE",1,"f",0);
//                if(is_encoder_reached(distance2encoder(1,4,1.5),leftFront))
//                {
//                    state++;
//                }
//                break;
//            case 15:
//                driveStright("RUE",1,"r",0);
//                if(is_encoder_reached(distance2encoder(4,4,1.5),leftFront))
//                {
//                    state++;
//                }
//                break;
            default:
                break;


        }
        telemetry.addData("2RightFront: ", get_position(rightFront));
        telemetry.addData("2LeftFront: ", get_position(leftFront));
        telemetry.addData("2RightRear: ", get_position(rightRear));
        telemetry.addData("2LeftRear: ", get_position(leftRear));
        telemetry.addData("1State: ", state);

    } // loop



} //
