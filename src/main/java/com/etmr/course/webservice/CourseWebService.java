package com.etmr.course.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * 课程服务
 * @author renguoliang
 * 2016 03-28 15:15
 */
@Path("/course")
public interface CourseWebService {
	
	/**
	 * 课程分页
	 * 
	 * @param firstResult
	 * @param pageSize
	 * @return
	 */
	@GET
	@Path("/getCoursesByPage")
	@Produces("*/*")
	public String getCoursesByPage(@QueryParam("firstResult") int firstResult,
			@QueryParam("pageSize") int pageSize);	

	/**
	 * 平台创建课程
	 */
	@POST
	@Path("/createCourse")
	public String createCourse(String course);
	
	
	/**
	 * 平台修改课程
	 */	
	@POST
	@Path("/updateCourse")
	public String updateCourse(String course);
	
	/**
	 * 平台删除课程
	 */
	@GET
	@Path("/deleteCourse")
	public String deleteCourse(@QueryParam("courseId") int courseId);	
	
	/**
	 * 平台查询课程
	 */
	@GET
	@Path("/getCourse")
	public String getCourse(@QueryParam("courseId") int courseId);	
}
