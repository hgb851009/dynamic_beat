<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" 
page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
request.setCharacterEncoding("UTF-8");
%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!<br>
	헬로 월드<br>
	お早う御座います。おはようございます。<br>
	<!-- 
	12-20 01:09
	1. 1.5 까지 진행 서버 동작하지않음
	2. porm.xml 추가 
	3. 메인폼 디자인 페이지 구성 플랫폼 떠오기
	4. 깃허브 프로젝트 올리고 가져오기 숙련 
	https://www.popit.kr/%EC%8B%A0%EC%9E%85-%EA%B0%9C%EB%B0%9C%EC%9E%90-%ED%95%99%EC%83%9D%EC%9D%84-%EC%9C%84%ED%95%9C-spring-mvc-setting-1%ED%8E%B8 -->
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>

