package controle;

	import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controle.util.JSFUtil;
import dominio.Estado;
import dominio.dao.EstadoDAO;

	@ManagedBean(name = "estadoMB")
	@RequestScoped
	public class EstadoMB
	{
		
		private static List<String> listarRegiao = new ArrayList<String>();

		static {
			listarRegiao.add("NORTE");
			listarRegiao.add("NORDESTE");
			listarRegiao.add("CENTRO-OESTE");
			listarRegiao.add("SUDESTE");
			listarRegiao.add("SUL");
		}
		
		private Estado estado = new Estado();
		private EstadoDAO dao = new EstadoDAO();

		private List<Estado> estados = null;

		public List<Estado> getEstados()
		{
			if (this.estados == null)
				this.estados = this.dao.lerTodos();

			return this.estados;
		}

		public Estado getEstado()
		{
			return estado;
		}

		public void setEstado(Estado estado)
		{
			this.estado = estado;
		}
		
		public List<String> getListaRegiao() {
			return EstadoMB.listarRegiao;
		}

		/**
		 * 
		 */
		public String acaoListar()
		{
			return "estadoListar";
		}

		/**
		 * 
		 */
		public String acaoAbrirInclusao()
		{
			// limpar o objeto da página
			this.setEstado(new Estado());

			return "estadoEditar";
		}

		/**
		 * 
		 */
		public String acaoAbrirAlteracao()
		{
			// pega o ID escolhido que veio no parâmetro
			Long id = JSFUtil.getParametroLong("itemId");
			Estado objetoDoBanco = this.dao.lerPorId(id);
			this.setEstado(objetoDoBanco);

			return "estadoEditar";
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
			if ((this.getEstado().getId() != null) && (this.getEstado().getId().longValue() == 0))
				this.getEstado().setId(null);

			this.dao.salvar(this.getEstado());
			// limpa a lista
			this.estados = null;

			// limpar o objeto da página
			this.setEstado(new Estado());

			return "estadoListar";
		}

		/**
		 * 
		 */
		public String acaoCancelar()
		{
			// limpar o objeto da página
			this.setEstado(new Estado());

			return "estadoListar";
		}

		/**
		 * 
		 */
		public String acaoExcluir()
		{
			Long id = JSFUtil.getParametroLong("itemId");
			Estado objetoDoBanco = this.dao.lerPorId(id);
			this.dao.excluir(objetoDoBanco);

			// limpar o objeto da página
			this.setEstado(new Estado());
			// limpa a lista
			this.estados = null;

			return "estadoListar";
		}

}
