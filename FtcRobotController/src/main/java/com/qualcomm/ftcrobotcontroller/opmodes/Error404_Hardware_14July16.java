package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
/**
 * Created by optim on 7/13/2016.
 * This is a test comment inserted by coach Belbas.
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
    }

    public void loop() {
        telemetry.addData("00 rf Power", rf_Get_Power());
        telemetry.addData("02 rf Direction", rf_Get_Direction());
        telemetry.addData("03 rf Mode", rf_Get_Mode());
        telemetry.addData("01 rf Position", rf_Get_Position());
    }

    public void stop() {
    }

    ///////////////////////////////////////////////
    /* all these "get" methods send telemetry to //
    //  driver station.  If device isn't found   //
    //      the telemetry will print "null".     //
    *//////////////////////////////////////////////
    String lf_Get_Power() {
        String motor_return = "";

        if (leftFront != null) {
            motor_return += leftFront.getPower();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;

    }  //get power for leftFront motor

    String rf_Get_Power() {

        String motor_return = "";
        if (rightFront != null) {
            motor_return += rightFront.getPower();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;

    }  //get power for rightFront motor

    String lr_Get_Power() {

        String motor_return = "";

        if (leftRear != null) {
            motor_return += leftRear.getPower();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;

    }  //get power for leftRear motor

    String rr_Get_Power() {
        String motor_return = "";
        if (rightRear != null) {
            motor_return += rightRear.getPower();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }  //get power for rightRear motor

    public String lf_Get_Position() {
        String motor_return = "";

        if (leftFront != null) {
            motor_return += leftFront.getCurrentPosition();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }

    public String rf_Get_Position() {
        String motor_return = "";

        if (rightFront != null) {
            motor_return += rightFront.getCurrentPosition();
            return motor_return;
        }
        motor_return += "null";
        return motor_return;
    }

    public String lr_Get_Position() {
        String motor_return = "";

        if (leftRear != null) {
            motor_return += leftRear.getCurrentPosition();
            return motor_return;
        }
        motor_return += "NULL";
        return motor_return;
    }


    public String rr_Get_Position() {
        String motor_return = "";

        if (rightRear != null) {
            motor_return += rightRear.getCurrentPosition();
            return motor_return;
        }
        motor_return += "NULL";
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
        motor_return += "null";
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
    void rf_set_power(double power)

    {
        if (rightFront != null) {
            rightFront.setPower(power);
        }
    }

    void lf_set_power(double power) {
        if (leftFront != null) {
            leftFront.setPower(power);
        }
    }

    void lr_set_power(double power) {
        if (leftRear != null) {
            leftRear.setPower(power);
        }
    }

    void rr_set_power(double power) {
        if (rightRear != null) {
            rightRear.setPower(power);
        }
    }
    ///////////////////////////////////////////////////
    // These set mode methods take a motor as a      //
    // parameter and will first check if the motor   //
    // is null. If not, it will set the desired mode.//
    ///////////////////////////////////////////////////

    void set_mode_withEncoders(DcMotor motor) {
        if (motor != null) {
            motor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }

    }

    void set_mode_withoutEncoders(DcMotor motor) {
        if (motor != null) {
            motor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        }
    }

    void set_mode_runtoposition(DcMotor motor) {
        if (motor != null) {
            motor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        }
    }

    ///////////////////////////////////////////////////////////
    //This set direction method takes a parameter for        //
    //the motor and a boolean. True if the motor is reversed,//
    //false if the motor is going forward.                   //
    ///////////////////////////////////////////////////////////
    void set_direction(DcMotor motor, boolean reversed) {
        if (motor != null) {
            if (reversed) {
                motor.setDirection(DcMotor.Direction.REVERSE);
            }
            else {
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