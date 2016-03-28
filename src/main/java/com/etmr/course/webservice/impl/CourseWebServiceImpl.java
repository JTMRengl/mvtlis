package com.etmr.course.webservice.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.etmr.course.model.Course;
import com.etmr.course.service.CourseService;
import com.etmr.course.webservice.CourseWebService;
import util.JsonUtil;
import util.ServerIp;
@Service("courseWebServiceImpl")
public class CourseWebServiceImpl implements CourseWebService {
	
	@Resource
	private ServerIp serverIp;
	
	@Resource
	private CourseService courseService;	
	
	@Override
	public String getCoursesByPage(int firstResult, int pageSize) {
		List<Course> courses = null;
		// TODO 自动生成的方法存根
		try {
			courses = courseService.getCoursesByPage(
					firstResult, pageSize);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//转换成json返回
		return JsonUtil.toJSON(courses);
	}	

	@Override
	public String createCourse(String courseStr) {
		
		String result = "0";

		Course course = JsonUtil.fromJSON(courseStr,Course.class);
		Map<String, Object> map = JsonUtil.parseJSON2Map(courseStr);
		
		courseService.createCourse(course,map);
		return result;
	}

	@Override
	public String updateCourse(String courseStr) {
		
		String result = "0";
		//json格式课程转换成对象类型
		Course course = JsonUtil.fromJSON(courseStr,Course.class);	
		Map<String, Object> map = JsonUtil.parseJSON2Map(courseStr);
		
		courseService.updateCourse(course,map);

		return result;
	}

	@Override
	public String deleteCourse(int courseId) {
		
		String result = "0";
		courseService.deleteCourse(courseId);
		return result;
	}

	@Override
	public String getCourse(int courseId) {
		
		Course course = courseService.getCourse(courseId);
		return JsonUtil.toJSON(course);
	}
}
