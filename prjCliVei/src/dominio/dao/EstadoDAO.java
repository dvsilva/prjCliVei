package dominio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import controle.util.JpaDAO;
import dominio.Cidade;
import dominio.Cliente;
import dominio.Estado;
import dominio.Perfil;

public class EstadoDAO extends JpaDAO<Estado>
{

	public EstadoDAO()
	{
		super();
	}

	public EstadoDAO(EntityManager manager)
	{
		super(manager);
	}
	
	@Override
	public List<Estado> lerTodos() {
		List<Estado> resultado;

		Query consulta = this.getEntityManager().createQuery("from Estado e order by e.nome");

		try
		{
			resultado = (List<Estado>) consulta.getResultList();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;
	}

}

