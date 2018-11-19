<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>掲示板アプリケーション</h2>
<br>
	<form:form modelAttribute="ArticleForm" action="${pageContext.request.contextPath}/insertarticle">
			<form:errors path="name" cssClass="error" element="div"/>
			投稿者名：<form:input path="name"/><br>
			<form:errors path="content" cssClass="error" element="div"/>
			投稿内容：<form:textarea path="content" rows="5" cols="25"/><br>
			<input type="submit" value="記事投稿">
	</form:form>
		<hr>
		<c:forEach var="article" items="${articleList}">
			投稿者名：<c:out value="${article.name}"/><br>
			投稿内容：<pre><c:out value="${article.content}"/></pre>
			<form:form modelAttribute="ArticleForm" action="${pageContext.request.contextPath}/deletearticle">
				<input type="hidden" name="id" value="<c:out value="${article.id}"/>">
				<input type="submit" value="記事削除">
			</form:form>
			<br>
			<c:forEach var="comment" items="${article.commentList}">
				コメント者名：<c:out value="${comment.name}"/><br>
				コメント内容：<pre><c:out value="${comment.content}"/></pre>
			</c:forEach>
			<form:form modelAttribute="CommentForm" action="${pageContext.request.contextPath}/postcomment">
				<input type="hidden" name="articleId" value="<c:out value="${article.id}"/>">
				<c:if test="${article.id == joinedCommentForm.articleId}">
					<form:errors path="name" cssClass="error" element="div"/>
				</c:if>
				名前:<br>
				<form:input path="name"/><br>
				<c:if test="${article.id == CommentForm.articleId}">
					<form:errors path="content" cssClass="error" element="div"/>
				</c:if>
				コメント:<br>
				<form:textarea path="content"/><br>
				<input type="submit" value="コメント投稿" >
			</form:form>
			<hr>
		</c:forEach>
</body>
</html>