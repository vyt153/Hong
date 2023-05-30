<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Cookie[] cookies = request.getCookies();
	
	String v_ = request.getParameter("value");
	String op = request.getParameter("operator");
	
	int v = 0;
	if(!v_.equals("")) v = Integer.parseInt(v_);
	
	int x = 0;
	int y = v;
	if(op.equals("=")){
		int result = 0;
		for(Cookie c:cookies){
			if(c.getName().equals("value")){
				x = Integer.parseInt(c.getValue());
				break;
			}
		}
		String operator = "";
		
		for(Cookie c:cookies){
			if(c.getName().equals("op")){
				operator = c.getValue();
				break;
			}
		}
		if(operator.equals("+")){
			result = x+y;
		} else{
			result = x-y;
		}
		response.getWriter().printf("result is : %d", result);
	} else{
		Cookie valueCookie = new Cookie("value",String.valueOf(v));
		Cookie opCookie = new Cookie("op",op);
		
		valueCookie.setPath("/");
		opCookie.setPath("/");
		response.addCookie(valueCookie);
		response.addCookie(opCookie);
		
		response.sendRedirect("calc.html");
	}
%>
</body>
</html>