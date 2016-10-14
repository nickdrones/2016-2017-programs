package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

public class Error404_Hardware_Tier2 extends Error404_Hardware_Tier1 {

     public void driveStright(String mode, double power, String direction, int position) {
        position=distance2encoder(position,6,1);
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
            left_set_power(power);
            right_set_power(power);

        }
        public void left_set_power(double power)
    {
        set_power(power, leftFront);
        set_power(power, leftRear);
    }

    public void right_set_power(double power)
    {
        set_power(power, rightFront);
        set_power(power, rightRear);
    }
    public void resetAllEncoders_withWait(){
        int count=0;
        reset_encoder(rightFront);
        reset_encoder(rightRear);
        reset_encoder(leftFront);
        reset_encoder(leftRear);
        while (get_position(rightFront)!= 0 && get_position(rightRear)!= 0 && get_position(leftFront)!= 0 && get_position(leftRear)!= 0){
            count++;
        telemetry.addData("count: ", count);
        }
    }
    public void resetAllEncoders_noWait(){
        reset_encoder(rightFront);
        reset_encoder(rightRear);
        reset_encoder(leftFront);
        reset_encoder(leftRear);
    }

    
    //Direction is either l "L" for left or r for right, instead of F for forward and B for backward
    public void pointTurn(String mode, double power, String direction, int position){
       position=distance2encoder(position,6,1);
        if (direction.toLowerCase().equals("r")) {
            set_direction(leftFront, "f");
            set_direction(leftRear, "f");
            set_direction(rightFront, "f");
            set_direction(rightRear, "f");
        }
        if (direction.toLowerCase().equals("l")) {
            set_direction(leftFront, "r");
            set_direction(leftRear, "r");
            set_direction(rightFront, "r");
            set_direction(rightRear, "r");
        }
        //sets mode to what is sent in with the "mode" parameter
        set_mode(leftFront, mode);
        set_mode(leftRear, mode);
        set_mode(rightFront, mode);
        set_mode(rightRear, mode);
        //sets target position to parameter "position"
        set_position(leftFront, position);
        set_position(leftRear,position);
        set_position(rightFront,position);
        set_position(rightRear,position);
        
        left_set_power(power);
        right_set_power(power);
    }
    

    public void swing_turn(String mode, double powerLeft, double powerRight, String direction, int position)
        {
            position = distance2encoder(position, 6, 1);
            set_direction(leftFront, "f");
            set_direction(leftRear, "f");
            set_direction(rightFront, "r");
            set_direction(rightRear, "r");
            set_mode(leftFront, mode);
            set_mode(leftRear, mode);
            set_mode(rightFront, mode);
            set_mode(rightRear, mode);
            if(direction.toLowerCase().equals("r"))
                {
                    set_position(rightFront, position);
                    set_position(rightRear, position);
                    double temp = powerLeft - powerRight;
                    temp += 1;
                    position *= temp;
                    set_position(leftFront, position);
                    set_position(leftRear, position);
                }
            if(direction.toLowerCase().equals("l"))
                {
                    set_position(leftFront, position);
                    set_position(leftRear, position);
                    double temp = powerRight - powerLeft;
                    temp += 1;
                    position *= temp;
                    set_position(rightFront, position);
                    set_position(rightRear, position);
                }
            left_set_power(powerLeft);
            right_set_power(powerRight);
    }

    public void pivot_turn(String mode, double power, String direction, int position){
        position = distance2encoder(position, 6, 1);
        set_direction(leftFront, "f");
        set_direction(leftRear, "f");
        set_direction(rightFront, "r");
        set_direction(rightRear, "r");
        set_mode(leftFront, mode);
        set_mode(leftRear, mode);
        set_mode(rightFront, mode);
        set_mode(rightRear, mode);
        if (direction.toLowerCase().equals("l")) {
            set_position(rightFront, position);
            set_position(rightRear, position);
            right_set_power(power);
        }
        if (direction.toLowerCase().equals("r")) {
            set_position(leftFront, position);
            set_position(leftRear, position);
            left_set_power(power);
        }
    }
    public void slide_sideways(String mode, double power, String direction, int position){
        position = distance2encoder(position, 4, 1);
        position=position*2; //because the wheels on the meccanum wheels are at 45', multiply the encoder counts by 2
        set_mode(leftFront, mode);
        set_mode(leftRear, mode);
        set_mode(rightFront, mode);
        set_mode(rightRear, mode);
        if (direction.toLowerCase().equals("r")) {
            set_direction(leftFront, "f");
            set_direction(rightRear, "r");
            set_direction(rightFront, "f");
            set_direction(leftRear, "r");
            set_position(rightFront, position);
            set_position(rightRear, position);
            set_position(leftFront, position);
            set_position(leftRear, position);
            set_power(power, rightRear);
            set_power(power, rightFront);
            set_power(power, leftFront);
            set_power(power, leftRear);

        }
        if (direction.toLowerCase().equals("l")) {
            set_direction(leftFront, "r");
            set_direction(rightRear, "r");
            set_direction(rightFront, "f");
            set_direction(leftRear, "f");
            set_position(leftFront, position);
            set_position(leftRear, position);
            left_set_power(power);
            set_position(rightFront, position);
            set_position(rightRear, position);
            right_set_power(power);
        }
    }

    public void motorTelemetry(DcMotor motor)
        {
            if(motor != null)
                {
                    telemetry.addData("00", get_direction(motor));
                    telemetry.addData("01", get_mode(motor));
                    telemetry.addData("02", get_power_tele(motor));
                    telemetry.addData("03", get_position_tele(motor));
                }
        }

}
