package br.com.crm.visao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.crm.entidades.pessoas.Usuario;
import br.com.crm.servicos.execoes.ExcecaoNegocio;
import br.com.crm.servicos.interfaces.IUsuarioServices;
import br.com.crm.util.IConstantes;

/**
 * Servlet que trata as requisições da tela de usuário
 */
@WebServlet("/ServletGeral")
public class ServletGeral extends HttpServlet {
	
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
		String nomeFuncionalidade = request.getParameter("nomeFuncionalidade");
		String nomeFuncao = request.getParameter("nomeFuncao");

		
		try {
			// ejb 2.0 carregando instancia através de lookup 
			IUsuarioServices usuarioService = (IUsuarioServices) new InitialContext().
				lookup("crm-ear/UsuarioService/local");
			
			String mensagem = null;
		
			if (nomeFuncionalidade.equals(IConstantes.NOME_FUNCIONALIDADE_USUARIO)) {
		
				RequestDispatcher rd = request
						.getRequestDispatcher("pages/TelaUsuario.jsp");
		
				if (nomeFuncao.equals(IConstantes.NOME_FUNCAO_GRAVAR)) {
					mensagem = cadastrarUsuario(request, response, usuarioService, rd);
				}
		
				if (nomeFuncao.equals(IConstantes.NOME_FUNCAO_LISTAR)) {
					mensagem = listarUsuarios(request, response, usuarioService, mensagem, rd);
				}
		
				if (nomeFuncao.equals(IConstantes.NOME_FUNCAO_EXCLUIR)) {
					mensagem = excluirUsuario(request, response, usuarioService, mensagem, rd);
				}
		
				if (nomeFuncao.equals(IConstantes.NOME_FUNCAO_ALTERAR)) {
					buscarUsuario(request, response, usuarioService, mensagem);
				}
		
				if (nomeFuncao.equals(IConstantes.NOME_FUNCAO_GRAVAR_ALTERACAO)) {
					editarUsuario(request, response, usuarioService);
				}				
			}
		} catch (NamingException e) {

		}

	}

	/**
	 * Método que cadastra um novo usuário
	 * 
	 * @param request
	 * @param response
	 * @param usuarioService
	 * @param rd
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private String cadastrarUsuario(HttpServletRequest request,
			HttpServletResponse response, IUsuarioServices usuarioService,
			RequestDispatcher rd) throws ServletException, IOException {
		String mensagem = null;
		Usuario usuario = new Usuario();
		usuario.setNome(request.getParameter("nome"));
		usuario.setEndereco(request.getParameter("endereco"));
		usuario.setCpf(request.getParameter("cpf"));
		usuario.setLogin(request.getParameter("login"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setConfirmaSenha(request.getParameter("confirmaSenha"));

		if (request.getParameter("ativo") == null) {
			usuario.setAtivo(false);
		} else {
			usuario.setAtivo(true);
		}
		
		try {
			usuarioService.incluir(usuario);
			mensagem = "Usuario gravado com sucesso!";
		} catch (ExcecaoNegocio e) {
			mensagem = e.getMessage();
		} finally {
			request.setAttribute("msg", mensagem);
			rd.forward(request, response);
		}
		return mensagem;
	}

	/**
	 * Método que busca todos os usuários cadastrados
	 * 
	 * @param request
	 * @param response
	 * @param usuarioService
	 * @param mensagem
	 * @param rd
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private String listarUsuarios(HttpServletRequest request,
			HttpServletResponse response, IUsuarioServices usuarioService, String mensagem,
			RequestDispatcher rd) throws ServletException, IOException {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			listaUsuarios = usuarioService.listar();
			request.setAttribute("listaUsuarios", listaUsuarios);
		} catch (ExcecaoNegocio e) {
			mensagem = "Ocorreu o seguinte erro: " + e.getMessage();
		} finally {
			request.setAttribute("msg", mensagem);
			rd.forward(request, response);
		}
		return mensagem;
	}

	/**
	 * Método que exclui um usuário
	 * 
	 * @param request
	 * @param response
	 * @param usuarioService
	 * @param mensagem
	 * @param rd
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private String excluirUsuario(HttpServletRequest request,
			HttpServletResponse response, IUsuarioServices usuarioService, String mensagem,
			RequestDispatcher rd) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		usuario.setId(Integer.parseInt(request.getParameter("id")));

		try {
			usuarioService.excluir(usuario);
			listaUsuarios = usuarioService.listar();
			request.setAttribute("listaUsuarios", listaUsuarios);
			
		} catch (ExcecaoNegocio e) {
			mensagem = e.getMessage();
		} finally {
			request.setAttribute("msg", mensagem);
			rd.forward(request, response);
		}
		return mensagem;
	}

	/**
	 * Método que edita os dados do usuário
	 * 
	 * @param request
	 * @param response
	 * @param usuarioService
	 * @throws ServletException
	 * @throws IOException
	 */
	private void editarUsuario(HttpServletRequest request,
			HttpServletResponse response, IUsuarioServices usuarioService)
			throws ServletException, IOException {
		String mensagem = null;
		Usuario usuario = new Usuario();
		usuario.setId(Integer.parseInt(request.getParameter("id")));
		usuario.setNome(request.getParameter("nome"));
		usuario.setEndereco(request.getParameter("endereco"));
		usuario.setCpf(request.getParameter("cpf"));
		usuario.setLogin(request.getParameter("login"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setConfirmaSenha(request.getParameter("confirmaSenha"));

		if (request.getParameter("ativo") == null) {
			usuario.setAtivo(false);
		} else {
			usuario.setAtivo(true);
		}

		RequestDispatcher rdGravaAlt = null;
		try {
			usuarioService.alterar(usuario);
			List<Usuario> listaUsuarios = new ArrayList<Usuario>();
			listaUsuarios = usuarioService.listar();
			request.setAttribute("listaUsuarios", listaUsuarios);

			rdGravaAlt = request.getRequestDispatcher("pages/TelaUsuario.jsp");
			mensagem = "Usuario alterado com sucesso";

		} catch (ExcecaoNegocio e) {			
			rdGravaAlt = request.getRequestDispatcher("pages/TelaUsuarioAlterar.jsp");
			mensagem = e.getMessage();
		} finally {
			request.setAttribute("msg", mensagem);
			request.setAttribute("id", usuario.getId());
			request.setAttribute("nome", usuario.getNome());
			request.setAttribute("endereco", usuario.getEndereco());
			request.setAttribute("cpf", usuario.getCpf());
			request.setAttribute("login", usuario.getLogin());
			request.setAttribute("senha", null);						
			rdGravaAlt.forward(request, response);			
		}
	}

	/**
	 * Método que busca o usuario pelo seu id
	 * 
	 * @param request
	 * @param response
	 * @param usuarioService
	 * @param mensagem
	 * @throws ServletException
	 * @throws IOException
	 */
	private void buscarUsuario(HttpServletRequest request,
			HttpServletResponse response, IUsuarioServices usuarioService, String mensagem)
			throws ServletException, IOException {

		Usuario usuario = new Usuario();
		usuario.setId(Integer.parseInt(request.getParameter("id")));
		RequestDispatcher rdAlt = request.getRequestDispatcher("pages/TelaUsuarioAlterar.jsp");

		try {
			Usuario usuarioEncontrado = usuarioService.recuperar(usuario);

			request.setAttribute("id", usuarioEncontrado.getId());
			request.setAttribute("nome", usuarioEncontrado.getNome());
			request.setAttribute("endereco", usuarioEncontrado.getEndereco());
			request.setAttribute("cpf", usuarioEncontrado.getCpf());
			request.setAttribute("login", usuarioEncontrado.getLogin());
			request.setAttribute("senha", usuarioEncontrado.getSenha());

		} catch (ExcecaoNegocio e) {
			mensagem = e.getMessage();
		} finally {
			request.setAttribute("msg", mensagem);
			rdAlt.forward(request, response);
		}
	}
}
