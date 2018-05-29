/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.module.i18n.common.api.exception;

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
