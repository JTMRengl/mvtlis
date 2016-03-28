package com.etmr.course.service;

import java.util.List;
import java.util.Map;

import com.etmr.course.model.Course;

public interface CourseService{

	/**
	 * 分页
	 * 
	 * @param firstResult
	 * @param pageSize
	 * @return
	 */
	public List<Course> getCoursesByPage(int firstResult, int pageSize);

	/**
	 * 创建课程
	 */
	public void createCourse(Course course,Map<String, Object> map);
	
	/**
	 * 修改课程
	 */	
	public void updateCourse(Course course,Map<String, Object> map);
	
	/**
	 * 删除课程
	 */	
	public void deleteCourse(int courseId);	
	
	/**
	 * 平台查询课程
	 */
	public Course getCourse(int courseId);		
}
