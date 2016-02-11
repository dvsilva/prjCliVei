package controle.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dominio.Estado;
import dominio.dao.EstadoDAO;

@FacesConverter(value="estado-converter", forClass=String.class)
public class EstadoConverter implements Converter {

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
		
		EstadoDAO dao = new EstadoDAO();
		Estado estado = dao.lerPorId(new Long(id));

		return estado;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente,
			Object objeto) {

		if (objeto instanceof Estado)
			return ((Estado) objeto).getId().toString();

		return null;
	}

}
