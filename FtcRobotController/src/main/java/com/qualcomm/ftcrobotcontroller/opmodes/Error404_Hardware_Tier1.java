package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Error404_Hardware_Tier1 extends OpMode {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;

    public void init() {}
    public void start() {}
    public void loop() { }
    public void stop() { }

    ///////////////////////////////////////////////
    /* all these "get" methods send telemetry to //
    //  driver station.  If device isn't found   //
    //      the telemetry will print "null".     //
    *//////////////////////////////////////////////

    public String get_Power_Tele(DcMotor motor)
    {
        String motor_return = "";
        if(motor != null){
            motor_return += motor.getPower();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String get_Position_Tele(DcMotor motor)
    {
        String motor_return = "";
        if(motor != null)
        {
            motor_return += motor.getCurrentPosition();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String get_Mode_Tele(DcMotor motor)
    {
        String motor_return = "";
        if(motor != null)
        {
            motor_return += motor.getMode();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String get_Direction_Tele(DcMotor motor)
    {
        String motor_return = "";
        if(motor != null)
        {
            motor_return += motor.getDirection();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    ////////////////////////////////////////////
    /*methods that are the hidden versions of //
    // the get methods above, doing the same  //
    //      things, but not printed out.      //
    *///////////////////////////////////////////

    public double get_Power(DcMotor motor)
    {
        double motor_return = 0;
        if(motor != null)
        {
            motor_return = motor.getPower();
            return motor_return;
        }
        return motor_return;
    }

    public int get_Position(DcMotor motor)
    {
        int motor_return = 0;
        if (motor != null)
        {
            motor_return = motor.getCurrentPosition();
            return motor_return;
        }
        return motor_return;
    }

    public String get_Mode(DcMotor motor) {
        String motor_return = "";
        if (motor != null) {
            motor_return += motor.getMode();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String get_Direction(DcMotor motor) {
        String motor_return = "";
        if (motor != null) {
            motor_return += motor.getDirection();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    //////////////////////////////////////////
    /*methods that check if the motor has   //
    // reached it's goal if found, else     //
    //          returns false.              //
    */////////////////////////////////////////

    public boolean is_Encoder_Reached(int goal, DcMotor motor)
    {
        int encoder_count = get_Position(motor);
        if(encoder_count == goal)
        {return true;}
        else
        {return false;}
    }

    //////////////////////////////////////////////
    /*  methods that check if motors are reset  //
    //      if found, else returns false        //
    */////////////////////////////////////////////

    public boolean is_Encoder_Reset(DcMotor motor)
    {
        if(get_Position(motor) == 0)
        {return true;}
        else
        {return false;}
    }

    ///////////////////////////////////////////
    /* methods that reset encoders if found, //
    //          else does nothing.           //
    *//////////////////////////////////////////

    public void reset_Encoder(DcMotor motor)
    {
        if(motor != null)
        {
            motor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    void hardware_map() {

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
    }

    ////////////////////////////////////////
    // In these set power methods, the    //
    //method checks to see if the motor   //
    //is null. If so, it skips that motor.//
    //If it is not null, the power is set //
    //to that motor.                      //
    ////////////////////////////////////////
      void set_power(double power, DcMotor motor){
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
    //This set direction method takes two parameters: Motor  //
    // and direction. The direction is set as F for forward  //
    // and R for reversed. If the motor is not null, the     //
    //direction is set.                                      //
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
    //In this method, you input the desired distance//
    //in inches, the wheel diameter, and the gear   //
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
}