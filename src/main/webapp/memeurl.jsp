<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${meme.title}</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<link rel="stylesheet" href="/css/style.css">
</head>
<body class="bg-dark" id="postBody">
	<div id="app">
		<nav class="navbar navbar-expand-md navbar-success bg-success text-white">
			<div class="container-fluid d-flex justify-content-between">
				<a href="/" class="navbar-brand text-white me-4">ImageClopedia</a>
				<div class="me-auto">
					<a class="text-white me-2" style="text-decoration:none;">Feed</a>
					<a class="text-white" style="text-decoration:none;">Most liked</a>
				</div>
				<div class="d-flex">
					<input class="form-control me-2" type="text" placeholder="Buscar imagenes"/>
					<button class="btn btn-outline-light" @click="isSearching = true" type="button">Buscar</button>
				</div>
			</div>
		</nav>
		<div class="container-fluid">
			<div class="row text-white mt-5">
				<div class="col-sm-6">
					<h1>${meme.title}</h1>
					<div class="mt-3 text-center p-1" id="postImage">
						<img alt="${meme.title}" src="${meme.imageUrl}"/>
					</div>
					<div class="border border-light rounded mt-3">
						<p class="px-2 pt-2">${meme.date} - <strong>${meme.category}</strong></p>
						<p class="px-2">${meme.memeText}</p>
					</div>
					
				</div>
				<div class="col-sm-6">
					<form:form action="/postComment" onsubmit="sendButton.disabled = true; return true;" class="text-center" modelAttribute="comment">
						<div>
							<form:textarea rows="5" cols="50" maxlength="240"  type="text" path="text" required="true"/>
						</div>
						<form:input type="number" value="${meme.id}" path="memeId" readonly="true"/>
						<button type="submit" name="sendButton" class="btn btn-outline-light">Enviar</button>
					</form:form>
					<div v-for="comment in comments">
						<div v-if="comment.memeId == ${meme.id}">
							<div class="p-2 my-3 bg-success rounded" id="commentWrapper">
								<p>{{comment.date}} - <strong>{{comment.id}}</strong></p>
								<p>{{comment.text}}</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
	<script type="text/javascript" src="/js/postcontrol.js"></script>
</body>
</html>