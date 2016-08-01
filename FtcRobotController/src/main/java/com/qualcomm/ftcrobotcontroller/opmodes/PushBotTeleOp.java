package com.qualcomm.ftcrobotcontroller.opmodes;

//------------------------------------------------------------------------------
//
// PushBotManual
//

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * Provide a basic manual operational mode that uses the left and right
 * drive motors, left arm motor, servo motors and gamepad input from two
 * gamepads for the Push Bot.
 *
 * @author SSI Robotics
 * @version 2015-08-01-06-01
 */
public class PushBotTeleOp extends OpMode

{

    DcMotor left;
    DcMotor right;
    //--------------------------------------------------------------------------
    //
    // PushBotManual
    //
    /**
     * Construct the class.
     *
     * The system calls this member when the class is instantiated.
     */
    public PushBotTeleOp ()

    {
        //
        // Initialize base classes.
        //
        // All via self-construction.

        //
        // Initialize class members.
        //
        // All via self-construction.

    } // PushBotManual

    public void init() {

        left = hardwareMap.dcMotor.get("left");                  //linking all the motors/servos from what they are called in the programming (far left)
        right = hardwareMap.dcMotor.get("right");

        right.setDirection(DcMotor.Direction.REVERSE);

    }

    //--------------------------------------------------------------------------
    //
    // loop
    //
    /**
     * Implement a state machine that controls the robot during
     * manual-operation.  The state machine uses gamepad input to transition
     * between states.
     *
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override public void loop ()

    {
        //----------------------------------------------------------------------
        //
        // DC Motors
        //
        // Obtain the current values of the joystick controllers.
        //
        // Note that x and y equal -1 when the joystick is pushed all of the way
        // forward (i.e. away from the human holder's body).
        //
        // The clip method guarantees the value never exceeds the range +-1.
        //
        // The DC motors are scaled to make it easier to control them at slower
        // speeds.
        //
        // The setPower methods write the motor power values to the DcMotor
        // class, but the power levels aren't applied until this method ends.
        //

        //
        // Manage the drive wheel motors.
        //
        float l_left_drive_power = -gamepad1.left_stick_y;
        float l_right_drive_power = -gamepad1.right_stick_y;

        l_left_drive_power = Range.clip (l_left_drive_power, -1, 1);
        l_right_drive_power = Range.clip(l_right_drive_power, -1, 1);

        left.setPower(l_left_drive_power);
        right.setPower(l_right_drive_power);

        telemetry.addData( "01", "Left Drive: " + left.getPower ());
        telemetry.addData( "02", "Right Drive: " + right.getPower ());

        telemetry.addData ("05", "Gamepad1 Left: " + -gamepad1.left_stick_y);
        telemetry.addData ("06", "Gamepad1 Right: " + -gamepad1.right_stick_y);

    } // loop

} // PushBotManual
