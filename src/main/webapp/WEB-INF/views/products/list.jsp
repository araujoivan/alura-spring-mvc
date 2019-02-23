<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${message}</h1>
        <table>
            <tr>
                <td>Title</td>
                <td>Description</td>
                <td>Pages</td>
            </tr>
            <c:forEach items="${products}" var="product">
               <tr>  
                    <td><a href="${s:mvcUrl('PC#details').arg(0, product.id).build()}" >${product.title}</a></td>
                    <td>${product.description}</td>
                    <td>${product.pages}</td>
                </tr>  
            </c:forEach>
        </table>
    </body>
</html>
