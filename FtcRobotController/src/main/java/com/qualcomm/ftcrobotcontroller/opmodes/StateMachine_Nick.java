package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController.RunMode;

/**
 * Created by Nick on 8/3/2016.
 */
public class StateMachine_Nick extends Error404_Hardware_Tier2 {
    private int state = 0;

//    public NickStateMachine() {
//    }

    @Override
    public void start() {
        driveStright("RWOE", 0.0, "F", 0);
        resetAllEncoders_withWait();
    }

    @Override
    public void loop() {
        switch (state) {
            case 0:
                resetAllEncoders_withWait();
                state++;
                break;
            case 1:

                driveStright("RUE", 1.0, "F", 0);
                if (is_encoder_reached(5000, leftFront)) {
                    state++;
                }
                break;
            case 2:
                resetAllEncoders_noWait();
                if (is_encoder_reset(leftFront) && is_encoder_reset(rightFront))
                    state++;
                break;
            case 3:
                pointTurn("RUE", 1.0, "L", 0);
                if (is_encoder_reached(5000, rightFront)) {
                    state++;
                }
                break;
            case 4:
                resetAllEncoders_noWait();
                if (is_encoder_reset(leftFront) && is_encoder_reset(rightFront))
                    state++;
                break;
            case 5:
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

    }
}
