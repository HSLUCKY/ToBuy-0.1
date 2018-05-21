<%@page
	import="org.springframework.web.servlet.handler.DispatcherServletWebRequest"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	request.setCharacterEncoding("UTF-8");

	String view = request.getParameter("view");
	String method = request.getParameter("method");

	RequestDispatcher rp = request.getRequestDispatcher("/" + view + "/" + method);
	rp.forward(request, response);
%>