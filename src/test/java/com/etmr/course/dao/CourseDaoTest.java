package com.etmr.course.dao;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
//单元测试的数据一半没有意义,所以一般不破坏数据,事务进行回滚
@Transactional
@Repository("courseDao")
public class CourseDaoTest {
	
	@Autowired
	private CourseDao courseDao;	

	@Test
	public void testgetCoursesByNL(){
		
		List list = courseDao.getCoursesByPage(0,5);
		System.out.println(list.size());
	}
}
