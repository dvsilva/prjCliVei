package dominio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import controle.util.JpaDAO;
import dominio.Perfil;
import dominio.Cliente;

public class ClienteDAO extends JpaDAO<Cliente>
{

	public ClienteDAO()
	{
		super();
	}

	public ClienteDAO(EntityManager manager)
	{
		super(manager);
	}

	public Cliente lerPorNome(String nome)
	{
		Cliente resultado;

		Query consulta = this.getEntityManager().createQuery("from Cliente p where p.nome = :nome");
		consulta.setParameter("nome", nome);

		try
		{
			resultado = (Cliente) consulta.getSingleResult();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;
	}

	@Override
	public List<Cliente> lerTodos() {
		List<Cliente> resultado;

		Query consulta = this.getEntityManager().createQuery("from Cliente p order by p.nome");

		try
		{
			resultado = (List<Cliente>) consulta.getResultList();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;
	}
	
	public List<Cliente> filtrarPorPerfil(Perfil perfil) {
		List<Cliente> resultado;
		
		StringBuilder comando = new StringBuilder("from Cliente c ");
		
		if (perfil != null)
			comando.append(" where c.perfil.id = :cPerfil");

		comando.append(" order by c.nome");

		Query consulta = this.getEntityManager().createQuery(comando.toString());
		if (perfil != null)
			consulta.setParameter("cPerfil", perfil.getId());

		try
		{
			resultado = (List<Cliente>) consulta.getResultList();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;

	}
	
	

}
