<ul id="menuhor">
	<li>
		<a href="<%=request.getContextPath()%> /ServletTelaPrincipal?cmd=pages/TelaPrincipal.jsp">
			Principal
		</a>
	</li>
	<li>
		<a href="<%=request.getContextPath()%>/ServletTelaPrincipal?cmd=pages/TelaLogin.jsp">
			Login
		</a>
	</li>
	<li>
		<a href="<%=request.getContextPath()%>/ServletTelaPrincipal?cmd=pages/TelaUsuario.jsp">
			Usuario
		</a>
	</li>
	<li>
		<a href="<%=request.getContextPath()%>/ServletTelaPrincipal?cmd=pages/TelaCliente.jsp">
			Cliente
		</a>
	</li>
</ul>
<br>
<p>Usuario Logado: <%=application.getAttribute("usuarioLogado")%></p>