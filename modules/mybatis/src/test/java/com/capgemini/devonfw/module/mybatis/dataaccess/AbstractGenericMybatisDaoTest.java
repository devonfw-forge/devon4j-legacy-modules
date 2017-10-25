package com.capgemini.devonfw.module.mybatis.dataaccess;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.devonfw.module.mybatis.common.Pagination;
import com.capgemini.devonfw.module.mybatis.common.PaginationResults;
import com.capgemini.devonfw.module.mybatis.common.SearchCriteria;
import com.capgemini.devonfw.module.mybatis.mapper.GenericMybatisMapper;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * @author vapadwal
 *
 */
@SpringBootTest(classes = com.capgemini.devonfw.module.mybatis.SpringBootMybatisApp.class)
public class AbstractGenericMybatisDaoTest extends ComponentTest {

  private static final Logger LOG = LoggerFactory.getLogger(AbstractGenericMybatisDaoTest.class);

  @Mock
  GenericMybatisMapper myBatisMapper;

  @InjectMocks
  TestingMybatisDao abstractGenericMybatisDao = new TestingMybatisDao(this.myBatisMapper);

  /**
   * Before the execution of the tests
   *
   */
  @Before
  public void initMockito() {

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFetchbyId() {

    Testing testing = new Testing();
    testing.setEmail("email");
    testing.setName("name");
    when(this.myBatisMapper.fetchById(Matchers.anyObject())).thenReturn(testing);
    assertSame(testing, this.abstractGenericMybatisDao.fetchById(1));
  }

  @Test
  public void testFetch() {

    PaginationResults<Testing> pr = new PaginationResults<Testing>();
    List<Testing> testList = new ArrayList<Testing>();
    Testing testing = new Testing();
    testing.setEmail("email");
    testing.setName("name");
    testList.add(testing);

    TestingSearchCriteria sc = new TestingSearchCriteria();
    Pagination pg = new Pagination();
    pg.setCurrentPage(1);
    pg.setMaxPage(3);
    pg.setPageSize(10);
    sc.setPagination(pg);
    pr.setPagination(pg);
    pr.setResults(testList);

    when(this.myBatisMapper.fetch((SearchCriteria) Matchers.anyObject(), (RowBounds) Matchers.anyObject()))
        .thenReturn(pr.getResults());
    List<Testing> retTestingList = this.abstractGenericMybatisDao.fetch(sc).getResults();
    assertSame(testList, retTestingList);
  }

  @Test
  public void testInsert() {

    Testing testing = new Testing();
    testing.setEmail("email");
    testing.setName("name");
    Mockito.doNothing().when(this.myBatisMapper).insert(Matchers.anyObject());

    this.abstractGenericMybatisDao.insert(testing);
    Mockito.verify(this.myBatisMapper).insert(Matchers.anyObject());

  }

  @Test
  public void testUpdate() {

    Testing testing = new Testing();
    testing.setEmail("email");
    testing.setName("name");
    Mockito.doNothing().when(this.myBatisMapper).update(Matchers.anyObject());

    this.abstractGenericMybatisDao.update(testing);
    Mockito.verify(this.myBatisMapper).update(Matchers.anyObject());

  }

  @Test
  public void testDelete() {

    when(this.myBatisMapper.delete(Matchers.anyObject())).thenReturn(true);

    Boolean isDel = this.abstractGenericMybatisDao.delete(new Long(1));
    assertSame(true, isDel);

  }

  public class Testing {
    String name;

    String email;

    /**
     * @return name
     */
    public String getName() {

      return this.name;
    }

    /**
     * @param name new value of {@link #getname}.
     */
    public void setName(String name) {

      this.name = name;
    }

    /**
     * @return email
     */
    public String getEmail() {

      return this.email;
    }

    /**
     * @param email new value of {@link #getemail}.
     */
    public void setEmail(String email) {

      this.email = email;
    }

  }

  public class TestingSearchCriteria extends SearchCriteria {
    String name;

    String email;

    /**
     * @return name
     */
    public String getName() {

      return this.name;
    }

    /**
     * @param name new value of {@link #getname}.
     */
    public void setName(String name) {

      this.name = name;
    }

    /**
     * @return email
     */
    public String getEmail() {

      return this.email;
    }

    /**
     * @param email new value of {@link #getemail}.
     */
    public void setEmail(String email) {

      this.email = email;
    }
  }
}
