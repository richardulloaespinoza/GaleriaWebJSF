<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<html>
	<head>
		<title>Imagen</title>
	</head>
	<body>
		<f:view>
			
		<h:form>
			<h:panelGrid columns="1">
				<h:panelGrid columns="2">
					<h:outputLabel for="titulo">
						Título
					</h:outputLabel>
					<h:inputText id="titulo" value="#{galeria.imagenActual.titulo}"/>
					
					<h:outputLabel for="ruta">
						Ruta
					</h:outputLabel>
					<h:inputText id="ruta" value="#{galeria.imagenActual.ruta}"/>
				</h:panelGrid>
				<h:commandButton value="Guardar" action="#{galeria.guardar}"/>
			</h:panelGrid>
		</h:form>
		</f:view>
	</body>	
</html>  
