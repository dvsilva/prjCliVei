package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controle.util.JSFUtil;
import dominio.Cidade;
import dominio.Cliente;
import dominio.Estado;
import dominio.Perfil;
import dominio.dao.CidadeDAO;
import dominio.dao.ClienteDAO;
import dominio.dao.EstadoDAO;
import dominio.dao.PerfilDAO;

@ManagedBean(name = "clienteMB")
@RequestScoped
public class ClienteMB {
	
	private List<Estado> estados = null;

	private Perfil filtroPerfil = null;
	private List<Perfil> perfis = null;

	private Cliente cliente = new Cliente();
	private ClienteDAO dao = new ClienteDAO();
	
	private List<Cliente> clientes = null;
	private List<Cidade> cidades = null;
	private Estado filtroEstado = null;

	public List<Cliente> getClientes() {
		//if (this.clientes == null)
			this.clientes = this.dao.filtrarPorPerfil(filtroPerfil);

		return this.clientes;
	}
	
	public List<Perfil> getPerfis() {
		if (this.perfis == null)
			this.perfis = new PerfilDAO().lerTodos();

		return this.perfis;
	}

	public List<Cidade> getCidades() {
		if (this.cidades == null)
		{
			this.cidades = new CidadeDAO().filtrarPorEstado(this.filtroEstado);
		}

		return this.cidades;
	}

	public List<Estado> getEstados() {
		if (this.estados == null)
		{		
			this.estados = new EstadoDAO().lerTodos();
		}

		return this.estados;
	}

	public Perfil getFiltroPerfil() {
		return filtroPerfil;
	}

	public void setFiltroPerfil(Perfil filtroPerfil) {
		this.filtroPerfil = filtroPerfil;
	}

	public Cliente getcliente() {
		return cliente;
	}

	public void setcliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Estado getFiltroEstado() {
		return filtroEstado;
	}

	public void setFiltroEstado(Estado filtroEstado) {
		this.filtroEstado = filtroEstado;
	}

	/**
	 * 
	 */
	public String acaoListar() {
		return "clienteListar";
	}

	/**
	 * 
	 */
	public String acaoAbrirInclusao() {
		// limpar o objeto da página
		this.setcliente(new Cliente());
		this.filtroEstado = null;

		return "clienteEditar";
	}

	/**
	 * 
	 */
	public String acaoAbrirAlteracao() {
		// pega o ID escolhido que veio no parâmetro
		Long id = JSFUtil.getParametroLong("itemId");
		Cliente objetoDoBanco = this.dao.lerPorId(id);
		this.setcliente(objetoDoBanco);
		
		if (this.cliente.getCidade() != null)
			this.filtroEstado = this.cliente.getCidade().getEstado();

		return "clienteEditar";
	}

	/**
	 * 
	 */
	public String acaoSalvar() {
		/**
		 * Deve limpar o ID com valor zero, pois o JSF sempre converte o campo
		 * vazio para um LONG = 0.
		 */
		if ((this.getcliente().getId() != null)
				&& (this.getcliente().getId().longValue() == 0))
			this.getcliente().setId(null);

		this.dao.salvar(this.getcliente());
		// limpa a lista
		this.clientes = null;

		// limpar o objeto da página
		this.setcliente(new Cliente());

		return "clienteListar";
	}

	/**
	 * 
	 */
	public String acaoCancelar() {
		// limpar o objeto da página
		this.setcliente(new Cliente());

		return "clienteListar";
	}

	/**
	 * 
	 */
	public String acaoExcluir() {
		Long id = JSFUtil.getParametroLong("itemId");
		Cliente objetoDoBanco = this.dao.lerPorId(id);
		this.dao.excluir(objetoDoBanco);

		// limpar o objeto da página
		this.setcliente(new Cliente());
		// limpa a lista
		this.clientes = null;

		return "clienteListar";
	}

}
