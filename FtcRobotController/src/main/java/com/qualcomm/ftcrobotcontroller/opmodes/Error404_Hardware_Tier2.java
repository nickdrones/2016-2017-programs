package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Error404_Hardware_Tier2 extends Error404_Hardware_Tier1 {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;

    public void init() {}
    public void start() {}
    public void loop() {}
    public void stop() {}

    public void left_Set_Power(double power)
    {
        set_power(power, leftFront);
        set_power(power, leftRear);
    }

    public void right_Set_Power(double power)
    {
        set_power(power, rightFront);
        set_power(power, rightRear);
    }

    /////////////////////////////////////////////////
    // Do not use this method in a looping OpMode. //
    // Could be used in a Linear opMode.           //
    /////////////////////////////////////////////////

    public void resetAllEncoders_withWait(){
        reset_encoder(rightFront);
        reset_encoder(rightRear);
        reset_encoder(leftFront);
        reset_encoder(leftRear);
        while (get_position(rightFront)!= 0 && get_position(rightRear)!= 0
            && get_position(leftFront)!= 0 && get_position(leftRear)!= 0){ }
    }

    /////////////////////////////////////////////////////
    // User's final OpMode should provide conditional  //
    // to ensure time for resetting to complete.       //
    /////////////////////////////////////////////////////

    public void resetAllEncoders_noWait(){
        reset_encoder(rightFront);
        reset_encoder(rightRear);
        reset_encoder(leftFront);
        reset_encoder(leftRear);
    }
    //////////  Need comments for this method ////////////

    public void driveStright(String mode, double power, String direction, int position) {
        if (direction.toLowerCase().equals("f")) {
            set_direction(leftFront, "f");
            set_direction(leftRear, "f");
            set_direction(rightFront, "r");
            set_direction(rightRear, "r");
        }
        if (direction.toLowerCase().equals("r")) {
            set_direction(leftFront, "r");
            set_direction(leftRear, "r");
            set_direction(rightFront, "f");
            set_direction(rightRear, "f");
        }
        set_mode(leftFront, mode);
        set_mode(leftRear, mode);
        set_mode(rightFront, mode);
        set_mode(rightRear, mode);
        set_position(leftFront, position);
        set_position(leftRear,position);
        set_position(rightFront,position);
        set_position(rightRear,position);
        left_Set_Power(power);
        right_Set_Power(power);
    }


    //Direction is either l for left or r for right, instead of F for forward and B for backward
    public void pointTurn(String mode, double power, String direction, int position){

    }

    public void swingTurn(String mode, double powerLeft, double powerRight, String direction, int position)
        {
            set_direction(leftFront, "f");
            set_direction(leftRear, "f");
            set_direction(rightFront, "f");
            set_direction(rightRear, "f");
            set_mode(leftFront, mode);
            set_mode(leftRear, mode);
            set_mode(rightFront, mode);
            set_mode(rightRear, mode);
            if(direction.toLowerCase().equals("l"))
                {
                    double temp = powerRight - powerLeft;
                    temp += 1;
                    position *= temp;
                }
            if(direction.toLowerCase().equals("r"))
                {
                    double temp = powerLeft - powerRight;
                    temp += 1;
                    position *= temp;
                }
            set_position(leftFront, position);
            set_position(leftRear, position);
            set_position(rightFront, position);
            set_position(rightRear, position);
            left_Set_Power(powerLeft);
            right_Set_Power(powerRight);
    }

    public void pivotTurn(String mode, double power, String direction, int position){
        set_direction(leftFront, "f");
        set_direction(leftRear, "f");
        set_direction(rightFront, "f");
        set_direction(rightRear, "f");
        set_mode(leftFront, mode);
        set_mode(leftRear, mode);
        set_mode(rightFront, mode);
        set_mode(rightRear, mode);
        if (direction.toLowerCase().equals("l")) {
            set_position(rightFront, position);
            set_position(rightRear, position);
            right_Set_Power(power);
        }
        if (direction.toLowerCase().equals("r")) {
            set_position(leftFront, position);
            set_position(leftRear, position);
            left_Set_Power(power);
        }
    }

}