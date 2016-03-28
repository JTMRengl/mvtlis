package com.etmr.course.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etmr.course.dao.CourseDao;
import com.etmr.course.model.Course;
import com.etmr.course.service.CourseService;


@Service("courseService")
@Transactional
public class CourseServiceImpl implements
		CourseService {

	@Resource
	private CourseDao courseDao;

	@Override
	public List<Course> getCoursesByPage(int firstResult, int pageSize) {
		return courseDao.getCoursesByPage(firstResult, pageSize);
	}

	@Override
	public void createCourse(Course course,Map<String, Object> map) {
		courseDao.createCourse(course,map);
	}

	@Override
	public void updateCourse(Course course,Map<String, Object> map) {
		courseDao.updateCourse(course,map);
	}

	@Override
	public void deleteCourse(int courseId) {
		
		courseDao.deleteCourse(courseId);
	}

	@Override
	public Course getCourse(int courseId) {
		
		return courseDao.getCourse(courseId);
	}
}
