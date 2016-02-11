package controle.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dominio.Perfil;
import dominio.dao.PerfilDAO;

@FacesConverter(value="perfil-converter", forClass=Perfil.class)
public class PerfilConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componente,
			String valor) {

		if (valor == null || valor.length() == 0)
			return null;

		Long id ;
		try{
			id = new Long(valor);
		}
		catch(Exception e){
			return null;
		}
		
		PerfilDAO dao = new PerfilDAO();
		Perfil perfil = dao.lerPorId(new Long(id));

		return perfil;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente,
			Object objeto) {

		if (objeto instanceof Perfil)
			return ((Perfil) objeto).getId().toString();

		return null;
	}

}
