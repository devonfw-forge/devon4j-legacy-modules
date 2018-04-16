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
package ${package}.general.dataaccess.api;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import io.oasp.module.jpa.dataaccess.api.MutablePersistenceEntity;

/**
 * Abstract Entity for all Entities with an id and a version field.
 *
 */
@MappedSuperclass
public abstract class ApplicationPersistenceEntity implements ApplicationEntity, MutablePersistenceEntity<Long> {

  private static final long serialVersionUID = 1L;

  /** @see ${symbol_pound}getId() */
  private Long id;

  /** @see ${symbol_pound}getModificationCounter() */
  private int modificationCounter;

  /** @see ${symbol_pound}getRevision() */
  private Number revision;

  /**
   * The constructor.
   */
  public ApplicationPersistenceEntity() {

    super();
  }

  @Override
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {

    return this.id;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setId(Long id) {

    this.id = id;
  }

  @Override
  @Version
  public int getModificationCounter() {

    return this.modificationCounter;
  }

  @Override
  public void setModificationCounter(int version) {

    this.modificationCounter = version;
  }

  @Override
  @Transient
  public Number getRevision() {

    return this.revision;
  }

  /**
   * @param revision the revision to set
   */
  @Override
  public void setRevision(Number revision) {

    this.revision = revision;
  }

  @Override
  public String toString() {

    StringBuilder buffer = new StringBuilder();
    toString(buffer);
    return buffer.toString();
  }

  /**
   * Method to extend {@link ${symbol_pound}toString()} logic.
   *
   * @param buffer is the {@link StringBuilder} where to {@link StringBuilder${symbol_pound}append(Object) append} the string
   *        representation.
   */
  protected void toString(StringBuilder buffer) {

    buffer.append(getClass().getSimpleName());
    if (this.id != null) {
      buffer.append("[id=");
      buffer.append(this.id);
      buffer.append("]");
    }
  }
}
