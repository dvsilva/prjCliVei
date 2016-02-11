package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controle.util.JSFUtil;
import dominio.Cliente;
import dominio.Veiculo;
import dominio.dao.ClienteDAO;
import dominio.dao.VeiculoDAO;

@ManagedBean(name = "veiculoMB")
@RequestScoped
public class VeiculoMB
{
	private Cliente filtroCliente = null;
	private List<Cliente> clientes = null;
	
	private Veiculo veiculo = new Veiculo();
	private VeiculoDAO dao = new VeiculoDAO();

	private List<Veiculo> veiculos = null;
	
	public List<Veiculo> getVeiculos() {
		//if (this.pessoas == null)
			this.veiculos = this.dao.filtrarPorCliente(filtroCliente);

		return this.veiculos;
	}

	public List<Cliente> getClientes() {
		if (this.clientes == null)
			this.clientes = new ClienteDAO().lerTodos();

		return this.clientes;
	}

	public Veiculo getVeiculo()
	{
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo)
	{
		this.veiculo = veiculo;
	}

	
	public Cliente getFiltroCliente() {
		return filtroCliente;
	}

	public void setFiltroCliente(Cliente filtroCliente) {
		this.filtroCliente = filtroCliente;
	}

	/**
	 * 
	 */
	public String acaoListar()
	{
		return "veiculoListar";
	}

	/**
	 * 
	 */
	public String acaoAbrirInclusao()
	{
		// limpar o objeto da página
		this.setVeiculo(new Veiculo());

		return "veiculoEditar";
	}

	/**
	 * 
	 */
	public String acaoAbrirAlteracao()
	{
		// pega o ID escolhido que veio no parâmetro
		Long id = JSFUtil.getParametroLong("itemId");
		Veiculo objetoDoBanco = this.dao.lerPorId(id);
		this.setVeiculo(objetoDoBanco);

		return "veiculoEditar";
	}

	/**
	 * 
	 */
	public String acaoSalvar()
	{
		/**
		 * Deve limpar o ID com valor zero, pois o JSF sempre converte o campo
		 * vazio para um LONG = 0.
		 */
		if ((this.getVeiculo().getId() != null) && (this.getVeiculo().getId().longValue() == 0))
			this.getVeiculo().setId(null);

		this.dao.salvar(this.getVeiculo());
		// limpa a lista
		this.veiculos = null;

		// limpar o objeto da página
		this.setVeiculo(new Veiculo());

		return "veiculoListar";
	}

	/**
	 * 
	 */
	public String acaoCancelar()
	{
		// limpar o objeto da página
		this.setVeiculo(new Veiculo());

		return "veiculoListar";
	}

	/**
	 * 
	 */
	public String acaoExcluir()
	{
		Long id = JSFUtil.getParametroLong("itemId");
		Veiculo objetoDoBanco = this.dao.lerPorId(id);
		this.dao.excluir(objetoDoBanco);

		// limpar o objeto da página
		this.setVeiculo(new Veiculo());
		// limpa a lista
		this.veiculos = null;

		return "veiculoListar";
	}

}
