<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List Videos</title>
</head>
<body>
    <center>
        <h1>List Videos</h1>
        <h2>
            <a href="listQuestions">My Questions</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Videos</h2></caption>
            <tr>
                <th>Title</th>
                <th>Post Date</th>
                <th>Url</th>
            </tr>
            <c:forEach var="video" items="${listVideos}">
                <tr>
                    <td><c:out value="${video.title}" /></td>
                    <td><c:out value="${video.postDate}" /></td>
                    <td><c:out value="${video.url}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>