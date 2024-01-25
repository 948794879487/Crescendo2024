// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ShooterConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private CANSparkMax intakeMotor;

  private CANSparkMax intakePivotMotorL;
  private CANSparkMax intakePivotMotorR;

  private PIDController intakePivotController;

  /** Creates a new Intake. */
  public Intake() {
    intakePivotMotorL = new CANSparkMax(IntakeConstants.CAN_INTAKE_PIVOT_L, MotorType.kBrushless);
    intakePivotMotorR = new CANSparkMax(IntakeConstants.CAN_INTAKE_PIVOT_R, MotorType.kBrushless);
    intakePivotMotorL.restoreFactoryDefaults();
    intakePivotMotorR.restoreFactoryDefaults();
    intakePivotMotorL.burnFlash();
    intakePivotMotorR.burnFlash();
    intakePivotMotorL.setSmartCurrentLimit(20);
    intakePivotMotorR.setSmartCurrentLimit(20);

    intakeMotor = new CANSparkMax(IntakeConstants.CAN_INTAKE, MotorType.kBrushless);
    intakeMotor.restoreFactoryDefaults();
    intakeMotor.burnFlash();
    intakeMotor.setSmartCurrentLimit(20);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getIntakeRpm() {
    return intakeMotor.getEncoder().getVelocity();
  }
  
  public void setIntakeRpm(double setPoint) {
    intakeMotor.set(intakePivotController.calculate(getIntakeRpm(),setPoint));
  }
}
