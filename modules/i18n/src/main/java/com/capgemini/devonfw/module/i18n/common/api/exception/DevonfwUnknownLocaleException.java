package com.capgemini.devonfw.module.i18n.common.api.exception;

/**
 * TODO vkiran This type ...
 *
 * @author vkiran
 */
public class DevonfwUnknownLocaleException extends Exception {
  /**
   * The constructor.
   *
   * @param msg is the Error Message thrown
   */
  public DevonfwUnknownLocaleException(String msg) {
    super();
    this.msg = msg;
  }

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  /**
   * @return msg
   */
  public String getMsg() {

    return this.msg;
  }

  /**
   * @param msg the msg to set
   */
  public void setMsg(String msg) {

    this.msg = msg;
  }

  private String msg;
}