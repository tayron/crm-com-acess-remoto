package br.com.crm.entidades.pessoas;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-12-05T16:17:38.809-0200")
@StaticMetamodel(Pessoa.class)
public class Pessoa_ extends EntidadeAbstrata_ {
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, String> cpf;
	public static volatile SingularAttribute<Pessoa, String> endereco;
	public static volatile SingularAttribute<Pessoa, Boolean> ativo;
}
