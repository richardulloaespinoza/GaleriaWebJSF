<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<f:view>
			<h:form>
				<h:commandButton action="#{galeria.nuevo}" value="Nuevo">
				</h:commandButton>
				<h:panelGrid columns="1">
					<h:dataTable value="#{galeria.imagenes}" var="imagen" border="1" style="background=cyan;">
						<h:column>
							<h:outputText value="#{imagen.titulo}" />
						</h:column>
						<h:column>
							<h:outputText value="#{imagen.ruta}" />
						</h:column>
						<h:column>
							<h:commandLink action="#{galeria.editar}" value="Editar">
								<f:param name="indice" value="#{imagen.indice}"/>
							</h:commandLink>
							&nbsp;|&nbsp;
							<h:commandLink action="#{galeria.borrar}" value="Borrar">
								<f:param name="indice" value="#{imagen.indice}"/>
							</h:commandLink>
						</h:column>
					</h:dataTable>
				</h:panelGrid>
			</h:form>
		</f:view>
	</body>	
</html>  
