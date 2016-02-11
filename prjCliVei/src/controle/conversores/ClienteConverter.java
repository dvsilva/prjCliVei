package controle.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dominio.Cliente;
import dominio.dao.ClienteDAO;

@FacesConverter(value="cliente-converter", forClass=Cliente.class)
public class ClienteConverter implements Converter {

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

		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = dao.lerPorId(new Long(id));
		
		return cliente;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente,
			Object objeto) {

		if (objeto instanceof Cliente)
			return ((Cliente) objeto).getId().toString();

		return null;
	}

}
