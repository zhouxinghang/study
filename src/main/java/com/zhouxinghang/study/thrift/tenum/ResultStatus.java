/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.zhouxinghang.study.thrift.tenum;


public enum ResultStatus implements org.apache.thrift.TEnum {
  SUCCESS(0),
  SYSTEM_ERROR(999);

  private final int value;

  private ResultStatus(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static ResultStatus findByValue(int value) { 
    switch (value) {
      case 0:
        return SUCCESS;
      case 999:
        return SYSTEM_ERROR;
      default:
        return null;
    }
  }
}
