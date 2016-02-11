package dominio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import controle.util.JpaDAO;
import dominio.Cidade;
import dominio.Estado;
import dominio.Veiculo;

public class CidadeDAO extends JpaDAO<Cidade> {

	public CidadeDAO() {
		super();
	}

	public CidadeDAO(EntityManager manager) {
		super(manager);
	}

	public List<Cidade> filtrarPorEstado(Estado estado) {

		List<Cidade> resultado;
		
		StringBuilder comando = new StringBuilder("from Cidade c ");
		
		if (estado != null)
			comando.append(" where c.estado.id = :cEstado");

		comando.append(" order by c.nome");

		Query consulta = this.getEntityManager().createQuery(comando.toString());
		if (estado != null)
			consulta.setParameter("cEstado", estado.getId());

		try
		{
			resultado = (List<Cidade>) consulta.getResultList();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;

	}
	

}
