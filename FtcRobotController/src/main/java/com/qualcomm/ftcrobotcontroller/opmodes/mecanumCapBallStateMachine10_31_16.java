package com.qualcomm.ftcrobotcontroller.opmodes;

public class mecanumCapBallStateMachine10_31_16 extends Error404_Hardware_Tier2

{
    private int state = 0;
    private int motorDelta = 0;
    public mecanumCapBallStateMachine10_31_16()
    {
    }
    @Override public void start(){
        gyroCalibrate();
        resetAllEncoders_withWait();
    }
    @Override public void loop ()
    {
        switch (state)
        {
            case 0:
                driveStright("RUE",0.2,"f",0);
                if (is_encoder_reached(distance2encoder(36,4,1.5), leftFront)) {
                    set_power(0, leftFront);
                    set_power(0, rightFront);
                    set_power(0, leftRear);
                    set_power(0, rightRear);
                    motorDelta = get_position(leftFront);
                    state++;
                }
                break;
            case 1:
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
            case 2:
                driveStright("RUE",0,"f",0);
                motorDelta = get_position(leftFront);
                state++;
                break;
            case 3:
                set_power(1,ballCollector);
                driveStright("RUE",.1,"f",0);
                if (is_encoder_reached((motorDelta + distance2encoder(18,4,1.5)), leftFront))
                {
                    set_power(0, leftFront);
                    set_power(0, rightFront);
                    set_power(0, leftRear);
                    set_power(0, rightRear);
                    set_power(0, ballCollector);
                    state++;
                }
                break;
            case 4:
                driveStright("RUE",0,"r",0);
                motorDelta = get_position(leftFront);
                state++;
                break;
            case 5:
                driveStright("RUE",.1,"r",0);
                if(((motorDelta) - distance2encoder(3,4,1.5)) > get_position(leftFront));
                {
                    set_power(0, leftFront);
                    set_power(0, rightFront);
                    set_power(0, leftRear);
                    set_power(0, rightRear);
                    motorDelta = get_position(leftFront);
                    state++;
                }
                break;
//            case 6:
//                pointTurn("RUE", .15, "r", 0);
//                if (gyro.getHeading()> 40 && gyro.getHeading() < 50)
//                {
//                    state++;
//                }
//                break;
//            case 7:
//                set_power(0, leftFront);
//                set_power(0, rightFront);
//                set_power(0, leftRear);
//                set_power(0, rightRear);
//                break;
//            case 8:
//                driveStright("RUE",.1,"f",0);
//                if(is_encoder_reached(distance2encoder(7,4,1.5),leftFront))
//                {
//                    state++;
//                }
//                break;
//            case 9:
//                set_power(0, leftFront);
//                set_power(0, rightFront);
//                set_power(0, leftRear);
//                set_power(0, rightRear);
//                break;
//            case 10:
//                pointTurn("RUE", .15, "r", 0);
//                if (gyro.getHeading() < 330 && gyro.getHeading() > 10)
//                {
//                    state++;
//                }
//                break;
//            case 11:
//                driveStright("RUE",1,"f",0);
//                if(is_encoder_reached(distance2encoder(1,4,1.5),leftFront))
//                {
//                    state++;
//                }
//                break;
//            case 12:
//                driveStright("RUE",1,"r",0);
//                if(is_encoder_reached(distance2encoder(4,4,1.5),leftFront))
//                {
//                    state++;
//                }
//                break;
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
