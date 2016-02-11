package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controle.util.JSFUtil;
import dominio.Cidade;
import dominio.Estado;
import dominio.dao.CidadeDAO;
import dominio.dao.EstadoDAO;

@ManagedBean(name = "cidadeMB")
@RequestScoped
public class CidadeMB {
	
	private Estado filtroEstado = null;
	private List<Estado> estados = null;

	private Cidade cidade = new Cidade();
	private CidadeDAO dao = new CidadeDAO();
	
	private List<Cidade> cidades = null;

	public List<Cidade> getCidades() {
		//if (this.cidades == null)
			this.cidades = this.dao.filtrarPorEstado(filtroEstado);

		return this.cidades;
	}
	
	public List<Estado> getEstados() {
		if (this.estados == null)
			this.estados = new EstadoDAO().lerTodos();

		return this.estados;
	}
	
	public Estado getFiltroEstado() {
		return filtroEstado;
	}

	public void setFiltroEstado(Estado filtroEstado) {
		this.filtroEstado = filtroEstado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	/**
	 * 
	 */
	public String acaoListar() {
		return "cidadeListar";
	}

	/**
	 * 
	 */
	public String acaoAbrirInclusao() {
		// limpar o objeto da página
		this.setCidade(new Cidade());
		this.filtroEstado = null;

		return "cidadeEditar";
	}

	/**
	 * 
	 */
	public String acaoAbrirAlteracao() {
		// pega o ID escolhido que veio no parâmetro
		Long id = JSFUtil.getParametroLong("itemId");
		Cidade objetoDoBanco = this.dao.lerPorId(id);
		this.setCidade(objetoDoBanco);
		
		/*if (this.cidade.getCidade() != null)
			this.filtroUf = this.cidade.getCidade().getEstado().getUf();
*/
		return "cidadeEditar";
	}

	/**
	 * 
	 */
	public String acaoSalvar() {
		/**
		 * Deve limpar o ID com valor zero, pois o JSF sempre converte o campo
		 * vazio para um LONG = 0.
		 */
		if ((this.getCidade().getId() != null)
				&& (this.getCidade().getId().longValue() == 0))
			this.getCidade().setId(null);

		this.dao.salvar(this.getCidade());
		// limpa a lista
		this.cidades = null;

		// limpar o objeto da página
		this.setCidade(new Cidade());

		return "cidadeListar";
	}

	/**
	 * 
	 */
	public String acaoCancelar() {
		// limpar o objeto da página
		this.setCidade(new Cidade());

		return "cidadeListar";
	}

	/**
	 * 
	 */
	public String acaoExcluir() {
		Long id = JSFUtil.getParametroLong("itemId");
		Cidade objetoDoBanco = this.dao.lerPorId(id);
		this.dao.excluir(objetoDoBanco);

		// limpar o objeto da página
		this.setCidade(new Cidade());
		// limpa a lista
		this.cidades = null;

		return "cidadeListar";
	}

}
