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
package com.devonfw.sample.general.dataaccess.api.dao;

import io.oasp.module.jpa.dataaccess.api.GenericRevisionedDao;
import io.oasp.module.jpa.dataaccess.api.RevisionedDao;
import io.oasp.module.jpa.dataaccess.api.MutablePersistenceEntity;

/**
 * Interface for all {@link GenericRevisionedDao DAOs} (Data Access Object) of this application.
 *
 * @author jmetzler
 *
 * @param <ENTITY> is the type of the managed entity.
 */
public interface ApplicationRevisionedDao<ENTITY extends MutablePersistenceEntity<Long>> extends
    ApplicationDao<ENTITY>, RevisionedDao<ENTITY> {

}
