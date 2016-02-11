package dominio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import controle.util.JpaDAO;
import dominio.Cliente;
import dominio.Veiculo;

public class VeiculoDAO extends JpaDAO<Veiculo>
{

	public VeiculoDAO()
	{
		super();
	}

	public VeiculoDAO(EntityManager manager)
	{
		super(manager);
	}

	public Veiculo lerPorPlaca(String placa)
	{
		Veiculo resultado;

		Query consulta = this.getEntityManager().createQuery("from Veiculo p where p.placa = :placa");
		consulta.setParameter("placa", placa);

		try
		{
			resultado = (Veiculo) consulta.getSingleResult();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;
	}

	public List<Veiculo> filtrarPorCliente(Cliente cliente) {
		List<Veiculo> resultado;
		
		StringBuilder comando = new StringBuilder("from Veiculo v ");
		
		if (cliente != null)
			comando.append(" where v.cliente.id = :vCliente");

		comando.append(" order by v.placa");

		Query consulta = this.getEntityManager().createQuery(comando.toString());
		if (cliente != null)
			consulta.setParameter("vCliente", cliente.getId());

		try
		{
			resultado = (List<Veiculo>) consulta.getResultList();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;

	}
	
}
