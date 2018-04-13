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
#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.foo.service.api;

import java.io.Serializable;

/**
 */
public class FooMessageTo implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  /**
   * json property
   */
  private String msg;

  /**
   * The default constructor.
   */
  public FooMessageTo() {
  }
  
  /**
   * The constructor.
   *
   * @param msg
   */
  public FooMessageTo(String msg) {
    setMsg(msg);
  }

  /**
   * @return msg
   */
  public String getMsg() {

    return this.msg;
  }

  /**
   * @param msg new value of {@link ${symbol_pound}getMsg}.
   */
  public void setMsg(String msg) {

    this.msg = msg;
  }
}
