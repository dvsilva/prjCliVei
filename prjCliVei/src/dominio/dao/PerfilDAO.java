package dominio.dao;

import javax.persistence.EntityManager;

import controle.util.JpaDAO;
import dominio.Perfil;

public class PerfilDAO extends JpaDAO<Perfil>
{

	public PerfilDAO()
	{
		super();
	}

	public PerfilDAO(EntityManager manager)
	{
		super(manager);
	}

}

