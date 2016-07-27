package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

public class Error404_Hardware_Tier1 extends OpMode {
    protected DcMotor leftFront;
    protected DcMotor rightFront;
    protected DcMotor leftRear;
    protected DcMotor rightRear;

    @Override public void init() {
         /////////////////////////////////////////////////////////////////
        /* Attempting a hardware map of the motors, servos, and sensors//
        //      If the device cannot be found in the config file,      //
        //    an error message shows on the driver station telemetry.  //
        *////////////////////////////////////////////////////////////////
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
        }//init

    public DcMotor convert(int mtr) {
        if (mtr == 1) {
            telemetry.addData("test",mtr);
            return leftFront;
        }
        if (mtr == 2) return rightFront;
        if (mtr == 3) return leftRear;
        if (mtr == 4) return rightRear;
        return null;
    }

    public void start() {}
    public void loop() { }
    public void stop() { }

    ////////////////////////////////////////////
    /*   Methods that return raw data use     //
    //       for decision making and          //
    //  and String versions for printing to   //
    //        Driver's Station phone          //
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
    /*method that checks if the motor has   //
    // reached it's goal if found, else     //
    //          returns false.              //
    */////////////////////////////////////////

    public boolean is_encoder_reached(int goal, DcMotor motor)
    {
        int encoderCount = get_position(motor);
        if(encoderCount == goal)
        {return true;}
        else if((encoderCount > (goal - 10)) && (encoderCount < (goal + 10)))
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



    ////////////////////////////////////////
    // In these set power methods, the    //
    //method checks to see if the motor   //
    //is null. If so, it skips that motor.//
    //If it is not null, the power is set //
    //to that motor.                      //
    ////////////////////////////////////////
      public void set_power(double power, DcMotor motor){
          if (motor != null) {
            motor.setPower(power);
            }
       }

    ///////////////////////////////////////////////////////
    // This set mode method uses two parameters:         //
    // motor and a 3-4 letter mode abbreviation.         //
    // If the motor is not null, the mode will be set to://
    //RTP= Run to Position       //////////////////////////
    //RUE= Run using encoders    //
    //RWOE= Run without encoders //
    ///////////////////////////////
    public void set_mode(DcMotor motor, String modetoset){
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

 /*   public void set_mode(DcMotor motor, String modetoset){
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
*/
    ///////////////////////////////////////////////////////////
    //This set direction method takes two parameters: Motor  //
    // and direction. The direction is set as F for forward  //
    // and R for reversed. If the motor is not null, the     //
    //direction is set.                                      //
    ///////////////////////////////////////////////////////////
    public void set_direction(DcMotor motor, String direction) {
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
    public void set_position(DcMotor motor, int position)
    {
        if (motor != null){
            motor.setTargetPosition(position);
        }
    }

    //////////////////////////////////////////////////
    //In this method, you input the desired distance//
    //in inches, the wheel diameter, and the gear   //
    //ratio. This method will then calculate the    //
    //needed number of encoder ticks needed to      //
    //drive the distance input.                     //
    //////////////////////////////////////////////////
    public int distance2encoder(int desiredDistance, double wheel_diameter, double gear_ratio) {
        return (int) ( 1140*(desiredDistance/(((3.14159265)*(wheel_diameter))*gear_ratio)));}

    ///////////////////////////////////
    //This scale motor power method  //
    //takes the raw power input from //
    //joysticks and converts it to   //
    //floats. With these set values, //
    //the motor power will ramp up   //
    //instead of being sudden & jerky//
    //////////////////////////////// //
    public float scale_motor_power (float p_power)
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
        reset_encoder(rightFront);
        reset_encoder(rightRear);
        reset_encoder(leftFront);
        reset_encoder(leftRear);
        while (get_position(rightFront)!= 0
            && get_position(rightRear)!= 0
            && get_position(leftFront)!= 0
            && get_position(leftRear)!= 0){
        }
    }

    public void resetAllEncoders_noWait(){
        reset_encoder(rightFront);
        reset_encoder(rightRear);
        reset_encoder(leftFront);
        reset_encoder(leftRear);
    }

    //Direction is either l for left or r for right, instead of F for forward and B for backward
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

    public void motor_telemetry(DcMotor motor)
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