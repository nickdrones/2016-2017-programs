package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
/**
 * Created by optim on 7/13/2016.
 * This is a test comment inserted by coach Belbas.  hello
 *
 */
public class Error404_Hardware_14July16 extends OpMode {
    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftRear;
    DcMotor rightRear;

    public void init() {
        hardware_map();
    }

    public void start() {
        set_direction(rightFront, "F");
        set_power(1.0, rightFront);
        set_mode(rightFront, "RTP");
        set_position(rightFront, 1140);
        rf_Reset_Encoder();
        set_power(.2, rightFront);
        set_position(rightFront, 1140);
    }

    public void loop() {
        telemetry.addData("00 rf Power", rf_Get_Power_Tele());
        telemetry.addData("02 rf Direction", rf_Get_Direction_Tele());
        telemetry.addData("03 rf Mode", rf_Get_Mode_Tele());
        telemetry.addData("01 rf Position", rf_Get_Position_Tele());
    }

    public void stop() {
    }

    ///////////////////////////////////////////////
    /* all these "get" methods send telemetry to //
    //  driver station.  If device isn't found   //
    //      the telemetry will print "null".     //
    *//////////////////////////////////////////////
    public String lf_Get_Power_Tele() {
        String motor_return = "";

        if (leftFront != null) {
            motor_return += leftFront.getPower();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;

    }  //get power for leftFront motor

    public String rf_Get_Power_Tele() {

        String motor_return = "";
        if (rightFront != null) {
            motor_return += rightFront.getPower();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;

    }  //get power for rightFront motor

    public String lr_Get_Power_tele() {

        String motor_return = "";

        if (leftRear != null) {
            motor_return += leftRear.getPower();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;

    }  //get power for leftRear motor

    public String rr_Get_Power_Tele() {
        String motor_return = "";
        if (rightRear != null) {
            motor_return += rightRear.getPower();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }  //get power for rightRear motor

    public String lf_Get_Position_Tele() {
        String motor_return = "";

        if (leftFront != null) {
            motor_return += leftFront.getCurrentPosition();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String rf_Get_Position_Tele() {
        String motor_return = "";

        if (rightFront != null) {
            motor_return += rightFront.getCurrentPosition();
            return motor_return;
        }
        motor_return += "null";
        return motor_return;
    }

    public String lr_Get_Position_Tele() {
        String motor_return = "";

        if (leftRear != null) {
            motor_return += leftRear.getCurrentPosition();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }


    public String rr_Get_Position_Tele() {
        String motor_return = "";

        if (rightRear != null) {
            motor_return += rightRear.getCurrentPosition();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String lf_Get_Mode_Tele() {
        String motor_return = "";
        if (leftFront != null) {
            motor_return += leftFront.getMode();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String rf_Get_Mode_Tele() {
        String motor_return = "";
        if (rightFront != null) {
            motor_return += rightFront.getMode();
            return motor_return;
        }
        motor_return += "null";
        return motor_return;
    }

    public String lr_Get_Mode_Tele() {
        String motor_return = "";
        if (leftRear != null) {
            motor_return += leftRear.getMode();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String rr_Get_Mode_Tele() {
        String motor_return = "";
        if (rightRear != null) {
            motor_return += rightRear.getMode();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String lf_Get_Direction_Tele() {
        String motor_return = "";
        if (leftFront != null) {
            motor_return += leftFront.getDirection();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String rf_Get_Direction_Tele() {
        String motor_return = "";
        if (rightFront != null) {
            motor_return += rightFront.getDirection();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String lr_Get_Direction_Tele() {
        String motor_return = "";
        if (leftRear != null) {
            motor_return += leftRear.getDirection();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String rr_Get_Direction_Tele() {
        String motor_return = "";
        if (rightRear != null) {
            motor_return += rightRear.getDirection();
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

    int lf_Get_Power() {
        int motor_return = 0;
        if (leftFront != null) {
            motor_return += leftFront.getPower();
            return motor_return;
        }
        return motor_return;
    }

    int rf_Get_Power() {
        int motor_return = 0;
        if (rightFront != null) {
            motor_return += rightFront.getPower();
            return motor_return;
        }
        return motor_return;
    }

    int lr_Get_Power() {
        int motor_return = 0;
        if (leftRear != null) {
            motor_return += leftRear.getPower();
            return motor_return;
        }
        return motor_return;
    }

    int rr_Get_Power() {
        int motor_return = 0;
        if (rightRear != null) {
            motor_return += rightRear.getPower();
            return motor_return;
        }
        return motor_return;
    }

    public int lf_Get_Position() {
        int motor_return = 0;

        if (leftFront != null) {
            motor_return += leftFront.getCurrentPosition();
            return motor_return;
        }
        return motor_return;
    }

    public int rf_Get_Position() {
        int motor_return = 0;

        if (rightFront != null) {
            motor_return += rightFront.getCurrentPosition();
            return motor_return;
        }
        return motor_return;
    }

    public int lr_Get_Position() {
        int motor_return = 0;

        if (leftRear != null) {
            motor_return += leftRear.getCurrentPosition();
            return motor_return;
        }
        return motor_return;
    }

    public int rr_Get_Position() {
        int motor_return = 0;

        if (rightRear != null) {
            motor_return += rightRear.getCurrentPosition();
            return motor_return;
        }
        return motor_return;
    }

    public String lf_Get_Mode() {
        String motor_return = "";
        if (leftFront != null) {
            motor_return += leftFront.getMode();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String rf_Get_Mode() {
        String motor_return = "";
        if (rightFront != null) {
            motor_return += rightFront.getMode();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String lr_Get_Mode() {
        String motor_return = "";
        if (leftRear != null) {
            motor_return += leftRear.getMode();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String rr_Get_Mode() {
        String motor_return = "";
        if (rightRear != null) {
            motor_return += rightRear.getMode();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String lf_Get_Direction() {
        String motor_return = "";
        if (leftFront != null) {
            motor_return += leftFront.getDirection();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String rf_Get_Direction() {
        String motor_return = "";
        if (rightFront != null) {
            motor_return += rightFront.getDirection();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String lr_Get_Direction() {
        String motor_return = "";
        if (leftRear != null) {
            motor_return += leftRear.getDirection();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String rr_Get_Direction() {
        String motor_return = "";
        if (rightRear != null) {
            motor_return += rightRear.getDirection();
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

    public boolean lf_Is_Encoder_Reached(int goal)
    {
            int encoder_count = lf_Get_Position();
            if(encoder_count == goal)
            {return true;}
            else
            {return false;}
    }

    public boolean rf_Is_Encoder_Reached(int goal)
    {
            int encoder_count = rf_Get_Position();
            if(encoder_count == goal)
            {return true;}
            else
            {return false;}
    }

    public boolean lr_Is_Encoder_Reached(int goal)
    {
            int encoder_count = lr_Get_Position();
            if(encoder_count == goal)
            {return true;}
            else
            {return false;}
    }

    public boolean rr_Is_Encoder_Reached(int goal)
    {
            int encoder_count = rr_Get_Position();
            if(encoder_count == goal)
            {return true;}
            else
            {return false;}
    }

    //////////////////////////////////////////////
    /*  methods that check if motors are reset  //
    //      if found, else returns false        //
    */////////////////////////////////////////////


    public boolean lf_Is_Encoder_Reset()
    {
            if(lf_Get_Position() == 0)
            {return true;}
            else
            {return false;}
    }

    public boolean rf_Is_Encoder_Reset()
    {
            if(rf_Get_Position() == 0)
            {return true;}
            else
            {return false;}
    }


    public boolean lr_Is_Encoder_Reset()
    {
            if(lr_Get_Position() == 0)
            {return true;}
            else
            {return false;}
    }

    public boolean rr_Is_Encoder_Reset()
    {
            if(rr_Get_Position() == 0)
            {return true;}
            else
            {return false;}
    }


    ///////////////////////////////////////////
    /* methods that reset encoders if found, //
    //          else does nothing.           //
    *//////////////////////////////////////////
    public void lf_Reset_Encoder() {
        if (leftFront != null) {
            leftFront.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }  //reset leftFront encoder

    public void rf_Reset_Encoder() {
        if (rightFront != null) {
            rightFront.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    public void lr_Reset_Encoder() {
        if (leftRear != null) {
            leftRear.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    public void rr_Reset_Encoder() {
        if (rightRear != null) {
            rightRear.setMode(DcMotorController.RunMode.RESET_ENCODERS);
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
            rightFront.setDirection(DcMotor.Direction.REVERSE);
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
    int temp =0;
        temp= (int)(((3.14159265)*(wheel_diameter))/1140);
        temp=(int)(temp*gear_ratio);
        temp*=inches;
        return temp;
    }
}