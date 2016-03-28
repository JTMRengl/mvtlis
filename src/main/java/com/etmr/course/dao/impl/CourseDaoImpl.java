package com.etmr.course.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.etmr.course.dao.CourseDao;
import com.etmr.course.model.Course;

import helper.DatabaseHelper;
import util.JsonUtil;

@Repository
public class CourseDaoImpl implements CourseDao {

	
	private static final String DRP_HQL_FROM = "select * from Course ";
	
	private static final String DRP_HQL_FROM_BYID = DRP_HQL_FROM + " where course_id = " ;

	@Override
	public List<Course> getCoursesByPage(int firstResult, int pageSize) {	
		
		return DatabaseHelper.queryEntityList(Course.class, DRP_HQL_FROM);
	}

	@Override
	public void createCourse(Course course,Map<String, Object> map){			
		
		DatabaseHelper.insertEntity(Course.class, map);
	}

	@Override
	public void updateCourse(Course course,Map<String, Object> map) {	
		
		DatabaseHelper.updateEntity(Course.class, course.getCourse_id(), map);
	}

	@Override
	public void deleteCourse(int CourseId) {
		DatabaseHelper.deleteEntity(Course.class, CourseId);
	}

	@Override
	public Course getCourse(int CourseId) {
		return DatabaseHelper.queryEntity(Course.class, DRP_HQL_FROM_BYID + CourseId);
	}
	
}
