package br.com.crm.entidades.pessoas;

import br.com.crm.entidades.pessoas.autorizacao.Permissao;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-12-05T16:17:38.810-0200")
@StaticMetamodel(Usuario.class)
public class Usuario_ extends Pessoa_ {
	public static volatile SingularAttribute<Usuario, String> login;
	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile ListAttribute<Usuario, Permissao> listaPermissoes;
}
