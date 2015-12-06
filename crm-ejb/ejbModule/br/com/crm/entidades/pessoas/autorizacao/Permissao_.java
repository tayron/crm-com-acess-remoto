package br.com.crm.entidades.pessoas.autorizacao;

import br.com.crm.entidades.pessoas.EntidadeAbstrata_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-12-05T16:17:38.450-0200")
@StaticMetamodel(Permissao.class)
public class Permissao_ extends EntidadeAbstrata_ {
	public static volatile SingularAttribute<Permissao, String> modulo;
	public static volatile SingularAttribute<Permissao, String> tela;
	public static volatile SingularAttribute<Permissao, TipoPermissao> tipoPermissao;
}
