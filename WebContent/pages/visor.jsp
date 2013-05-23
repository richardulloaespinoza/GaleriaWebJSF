<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<f:view>
			<h:form>
				<h:panelGrid columns="2" width="70%, 30%">
					<h:panelGrid columns="1" width="70%">
						<h:outputText value="Título: #{galeria.imagenActual.titulo}"/>
						<h:graphicImage value="#{galeria.imagenActual.ruta}"/>
						<h:panelGrid columns="3">
							<h:commandButton action="#{galeria.anterior}" value="Anterior" />
							<h:commandButton action="#{galeria.aleatorio}" value="Aleatorio" />
							<h:commandButton action="#{galeria.siguiente}" value="Siguiente" />
						</h:panelGrid>
					</h:panelGrid>
					<h:panelGrid columns="1">
						<h:commandButton value="Administración" action="#{galeria.administracion}"/>
					</h:panelGrid>
				</h:panelGrid>
			</h:form>
		</f:view>
	</body>	
</html>  
