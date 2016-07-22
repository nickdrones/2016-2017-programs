package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

public class Error404_Hardware_Tier1 extends OpMode {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;

    public void init() {}
    public void start() {}
    public void loop() { }
    public void stop() { }

    public void hardware_map() {

        ////////////////////////////////////////////////////////////////////
        /* Attempting a hardware map of the dc motors; servos, sensors-TBD. //
        // If the device cannot be found in the config file, an error     //
        // message shows on the driver station telemetry.                 //
        *//////////////////////////////////////////////////////////////////
        try {
            leftFront = hardwareMap.dcMotor.get("leftFront");
        } catch (Exception p_exeception) {
            telemetry.addData("leftFront not found in config file", 0);
            leftFront = null;
        }
        try {
            rightFront = hardwareMap.dcMotor.get("rightFront");
        } catch (Exception p_exeception) {
            telemetry.addData("rightFront not found in config file", 0);
            rightFront = null;
        }
        try {
            leftRear = hardwareMap.dcMotor.get("leftRear");
        } catch (Exception p_exeception) {
            telemetry.addData("leftRear not found in config file", 0);
            leftRear = null;
        }
        try {
            rightRear = hardwareMap.dcMotor.get("rightRear");
        } catch (Exception p_exeception) {
            telemetry.addData("rightRear not found in config file", 0);
            rightRear = null;
        }
    }

    ////////////////////////////////////////////
    /*     raw data methods that are used     //
    //        for decision making, and        //
    //    telemetry methods to print on the   //
    //          driver station phone          //
    *///////////////////////////////////////////

    public double get_power(DcMotor motor)
    {
        double motorReturn = 0;
        if(motor != null)
        {
            motorReturn = motor.getPower();
            return motorReturn;
        }
        return motorReturn;
    }

    public String get_power_tele(DcMotor motor)
    {
        String motorReturn = "";
        if(motor != null){
            motorReturn += motor.getPower();
            return motorReturn;
        }
        motorReturn += "NULL";
        return motorReturn;
    }

    public int get_position(DcMotor motor)
    {
        int motorReturn = 0;
        if (motor != null)
        {
            motorReturn = motor.getCurrentPosition();
            return motorReturn;
        }
        return motorReturn;
    }

    public String get_position_tele(DcMotor motor)
    {
        String motorReturn = "";
        if(motor != null)
        {
            motorReturn += motor.getCurrentPosition();
            return motorReturn;
        }
        motorReturn += "NULL";
        return motorReturn;
    }

    public String get_mode(DcMotor motor) {
        String motorReturn = "";
        if (motor != null) {
            motorReturn += motor.getMode();
            return motorReturn;
        }
        motorReturn += "NULL";
        return motorReturn;
    }

    public String get_direction(DcMotor motor) {
        String motorReturn = "";
        if (motor != null) {
            motorReturn += motor.getDirection();
            return motorReturn;
        }
        motorReturn += "NULL";
        return motorReturn;
    }

    //////////////////////////////////////////
    /* Has motor encoder reached goal?      //
    // If yes, return true.                 //
    */////////////////////////////////////////

    public boolean is_Encoder_Reached(int goal, DcMotor motor)
    {
        int encoder_count = get_position(motor);
        if(encoder_count == goal)
        {return true;}
        else
        {return false;}
    }

    //////////////////////////////////////////////
    /*  method that checks if motors are reset  //
    //      if found, else returns false        //
    */////////////////////////////////////////////

    public boolean is_encoder_reset(DcMotor motor)
    {
        if(get_position(motor) == 0)
        {return true;}
        else
        {return false;}
    }

    ///////////////////////////////////////////
    /* methods that resets encoders if found, //
    //          else does nothing.           //
    *//////////////////////////////////////////

    public void reset_encoder(DcMotor motor)
    {
        if(motor != null)
        {
            motor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }


    ////////////////////////////////////////////////
    // Set power only if the motor is found by the//
    // hardware map method.                      //
    ///////////////////////////////////////////////
      void set_power(double power, DcMotor motor){
          if (motor != null) {
            motor.setPower(power);
        }
    }
    ///////////////////////////////////////////////////////
    // If the motor is not null, the mode will be set to://
    //RTP= Run to Position       //////////////////////////
    //RUE= Run using encoders    //
    //RWOE= Run without encoders //
    ///////////////////////////////
    void set_mode(DcMotor motor, String modetoset){
        modetoset=modetoset.toUpperCase();
        if (motor != null){
            if (modetoset.equals("RTP")){
                motor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            }
            if (modetoset.equals("RUE")){
                motor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            }
            if (modetoset.equals("RWOE")){
                motor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
        }
    }
    ///////////////////////////////////////////////////////////
    // If the motor is not null, the direction is set to     //
    //forward (F) or reverse (R).                                      //
    ///////////////////////////////////////////////////////////
    void set_direction(DcMotor motor, String direction) {
        if (motor != null) {
            direction=direction.toLowerCase();
            if (direction.equals("r")) {
                motor.setDirection(DcMotor.Direction.REVERSE);
            }
            if (direction.equals("f")) {
                motor.setDirection(DcMotor.Direction.FORWARD);
            }
        }
    }
    //////////////////////////////////////////
    //This method takes two parameters, one //
    //for the motor and one for the desired //
    //position. It then sets the position   //
    //to the motor if the motor is not null.//
    //////////////////////////////////////////
    void set_position(DcMotor motor, int position)
    {
        if (motor != null){
            motor.setTargetPosition(position);
        }
    }
    //////////////////////////////////////////////////
    //Input the desired distance in inches, the     //
    //wheel diameter in inches, and the gear        //
    //ratio. This method will then calculate the    //
    //needed number of encoder ticks needed to      //
    //drive the distance input.                     //
    //////////////////////////////////////////////////
    int distance2encoder(int inches, int wheel_diameter, double gear_ratio) {
    double temp =0;
        temp= (int)(((3.14159265)*(wheel_diameter)));
        temp=(int)(temp*gear_ratio);
        temp=inches/temp;
        return (int)temp*1140;
    }

    ///////////////////////////////////
    //This scale motor power method  //
    //takes the raw power input from //
    //joysticks and converts it to   //
    //floats. With these set values, //
    //the motor power will ramp up   //
    //instead of being sudden & jerky//
    //////////////////////////////// //
    float scale_motor_power (float p_power)
    {
        float l_scale = 0.0f;
        float l_power = Range.clip(p_power, -1, 1);
        float[] l_array =
                { 0.00f, 0.05f, 0.09f, 0.10f, 0.12f
                        , 0.15f, 0.18f, 0.24f, 0.30f, 0.36f
                        , 0.43f, 0.50f, 0.60f, 0.72f, 0.85f
                        , 1.00f, 1.00f
                };
        int l_index = (int)(l_power * 16.0);
        if (l_index < 0)
        {
            l_index = -l_index;
        }
        else if (l_index > 16)
        {
            l_index = 16;
        }

        if (l_power < 0)
        {
            l_scale = -l_array[l_index];
        }
        else
        {
            l_scale = l_array[l_index];
        }

        return l_scale;

    }
}