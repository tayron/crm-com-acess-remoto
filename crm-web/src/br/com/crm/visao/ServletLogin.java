package br.com.crm.visao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.crm.servicos.execoes.ExcecaoNegocio;
import br.com.crm.servicos.interfaces.IUsuarioServices;

/**
 * Servlet que trata as requisições da tela de login do usuário
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método que trata os dados de requisição via get
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * Método que trata os dados de uma requisição vista post
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		try {
			// ejb 2.0 carregando instancia através de lookup 
			IUsuarioServices usuarioService = (IUsuarioServices) new InitialContext().
				lookup("crm-ear/UsuarioService/local");
			
			if (usuarioService.consultar(login, senha)) {
				getServletContext().setAttribute("usuarioLogado", login);
				request.getSession().setAttribute("usuario", login);

				RequestDispatcher rd = request
						.getRequestDispatcher("/pages/TelaPrincipal.jsp");
				rd.forward(request, response);
			}
		} catch (ExcecaoNegocio e) {
			PrintWriter mensagem = response.getWriter();
			mensagem.write("<html> <body>");
			mensagem.write("<h1> Falha no login: " + e.getMessage());
			mensagem.write("</h1></body></html>");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
