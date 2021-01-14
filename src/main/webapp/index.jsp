<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ImageClopedia</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<link rel="stylesheet" href="/css/style.css">
</head>
<body class="bg-dark">

	<div id="app">
		<nav class="navbar navbar-expand-md navbar-success bg-success text-white">
			<div class="container-fluid d-flex justify-content-between">
				<a href="/" class="navbar-brand text-white me-4">ImageClopedia</a>
				<div class="me-auto">
					<a class="text-white me-2" style="text-decoration:none;">Feed</a>
					<a class="text-white" style="text-decoration:none;">Most liked</a>
				</div>
				<div class="me-auto">
					<button type="button" class="btn btn-outline-light" data-bs-toggle="modal" data-bs-target="#exampleModal">
						Sube una imágen
					</button>
					
				</div>
				<div class="d-flex">
					<input class="form-control me-2" type="text" placeholder="Buscar imagenes"/>
					<button class="btn btn-outline-light" @click="isSearching = true" type="button">Buscar</button>
				</div>
			</div>
		</nav>
	
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content bg-dark text-white">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Sube una imágen</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <form:form action="/postMeme" modelAttribute="meme">
			      <div class="modal-body">
			        <div class="container-fluid">
			        	
		        		<div class="row">
			        		<div class="col-md-12 form-group ">
			        			<h4>Título</h4>
			        			<form:input type="text" class="form-control" path="title" id="title" required="required"/>
			        		</div>
		        		</div>
		        		<div class="row mt-3">
		        			<div class="col-md-12 form-group">
		        				<h4>Url de la imágen</h4>
		        				<form:input type="text" v-model="urlInput" class="form-control" path="imageUrl" id="imageUrl" required="required"/>
		        				<div v-if="urlInput != null">
		        					<img class="mt-3" alt="memePreview" v-bind:src="urlInput"/>
		        				</div>
		        				<div v-else>
		        					<p>Ingresa la url de tu imágen</p>
		        				</div>
		        			</div>
		        		</div>
		        		<div class="row mt-1">
		        			<div class="col-md-12 form-group">
		        				<h4>Categoría</h4>
		        				<form:select path="category" class="form-control" id="category">
		        					<form:option value="pol" label="Política"/>
		        					<form:option value="spo" label="Deporte"/>
		        					<form:option value="tec" label="Tecnología"/>
		        					<form:option value="pai" label="paisaje"/>
		        					<form:option value="ran" label="Random"/>
		        				</form:select>
		        			</div>
		        		</div>		
			        </div>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal">Cancelar</button>
			        <button type="submit" :disabled="disableButton" @click="setTimeout(disable,300)" class="btn btn-outline-light">Enviar</button>
			      </div>
		      </form:form>
		    </div>
		  </div>
		</div>
		<div v-for="meme in memes" class="d-inline-flex" id="memeWrapper">
			<div class="text-center text-white">
				<img :alt="meme.title" :src="meme.imageUrl" style="height:14rem;width:14rem;"/>
				<h5 class="card-title mt-2">{{meme.title}}</h5>
			</div>	
		</div>
	</div>
		
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.js" integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
	<script type="text/javascript" src="/js/control.js"></script>
</body>
</html>