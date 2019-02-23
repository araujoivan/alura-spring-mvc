<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Registration</title>
    </head>
    <body>
        <form:form action="${s:mvcUrl('PC#save').build()}" method="POST" commandName="product" enctype="multipart/form-data">
            <div>
                <label>Title</label>
                <form:input path="title" />
                <form:errors path="title" />
            </div>
            <div>
                <label>Description</label>
                <form:textarea rows="10" cols="20" path="description" />
                <form:errors path="description" />
            </div>
            <div>
                <label>Pages</label>
                <form:input path="pages" />
                <form:errors path="pages" />
            </div>
             <div>
                <label>Published Date</label>
                <form:input path="publishedDate" />
                <form:errors path="publishedDate" />
            </div>
            <div>
                <c:forEach items="${types}" var="priceType" varStatus="status">
                    <div>
                        <label>${priceType}</label>
                        <form:input path="prices[${status.index}].value" />
                        <form:input path="prices[${status.index}].type" value="${priceType}" />
                    </div>
                </c:forEach>
            </div>
            <div>
                <label>Summary</label>
                <input name="summary" type="file" /> 
            </div>
            <button type="submit" >Save</button>
        </form:form>
    </body>
</html>

