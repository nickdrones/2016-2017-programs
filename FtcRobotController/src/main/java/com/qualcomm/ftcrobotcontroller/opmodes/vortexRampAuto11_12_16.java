package com.qualcomm.ftcrobotcontroller.opmodes;

public class vortexRampAuto11_12_16 extends Error404_Hardware_Tier2
{
    private int state = 0;
    private int motorDelta = 0;
    public vortexRampAuto11_12_16()
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
                driveStright("RUE",0.2,"f",0);
                if (is_encoder_reached(motorDelta + 2300, leftFront)) {
                    set_power(0, leftFront);
                    set_power(0, rightFront);
                    set_power(0, leftRear);
                    set_power(0, rightRear);
                    motorDelta = get_position(leftFront);
                    state++;
                }
                break;
            case 1:
                pointTurn("RUE",0.2,"r",0); //turn towards line
                if (gyro.getHeading()>80&&gyro.getHeading()<270) {
                    state++;
                }
                break;
            case 2:
                set_power(0,rightFront);
                set_power(0,leftFront);
                set_power(0,rightRear);
                set_power(0,leftRear);
                //resetAllEncoders_noWait();
                state++;
                motorDelta=leftFront.getCurrentPosition();
                break;
            case 3:
                driveStright("RUE",0.2,"f",0); //drive to line's general area
                if (is_encoder_reached((500+motorDelta), leftFront)) {
                    state++;
                }
                break;
            case 4:
                set_power(0,rightFront);
                set_power(0,leftFront);
                set_power(0,rightRear);
                set_power(0,leftRear);
                state++;
                break;
            case 5:
                pointTurn("RUE",0.2,"r",0); //turn towards line
                if (gyro.getHeading()>120&&gyro.getHeading()<270) {
                    state++;
                }
                break;
            case 6:
                set_power(0,rightFront);
                set_power(0,leftFront);
                set_power(0,rightRear);
                set_power(0,leftRear);
                //resetAllEncoders_noWait();
                state++;
                motorDelta=leftFront.getCurrentPosition();
                break;
            case 7:
                driveStright("RUE",0.2,"f",0); //drive to line's general area
                if (is_encoder_reached((2900+motorDelta), leftFront)) {
                    state++;
                }
                break;
            case 8:
                set_power(0,rightFront);
                set_power(0,leftFront);
                set_power(0,rightRear);
                set_power(0,leftRear);
                state++;
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
