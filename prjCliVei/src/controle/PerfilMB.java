package controle;

	import java.util.List;

	import javax.faces.bean.ManagedBean;
	import javax.faces.bean.RequestScoped;

	import controle.util.JSFUtil;
	import dominio.Perfil;
	import dominio.dao.PerfilDAO;

	@ManagedBean(name = "perfilMB")
	@RequestScoped
	public class PerfilMB
	{
		private Perfil perfil = new Perfil();
		private PerfilDAO dao = new PerfilDAO();

		private List<Perfil> perfis = null;

		public List<Perfil> getPerfis()
		{
			if (this.perfis == null)
				this.perfis = this.dao.lerTodos();

			return this.perfis;
		}

		public Perfil getPerfil()
		{
			return perfil;
		}

		public void setPerfil(Perfil perfil)
		{
			this.perfil = perfil;
		}

		/**
		 * 
		 */
		public String acaoListar()
		{
			return "perfilListar";
		}

		/**
		 * 
		 */
		public String acaoAbrirInclusao()
		{
			// limpar o objeto da página
			this.setPerfil(new Perfil());

			return "perfilEditar";
		}

		/**
		 * 
		 */
		public String acaoAbrirAlteracao()
		{
			// pega o ID escolhido que veio no parâmetro
			Long id = JSFUtil.getParametroLong("itemId");
			Perfil objetoDoBanco = this.dao.lerPorId(id);
			this.setPerfil(objetoDoBanco);

			return "perfilEditar";
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
			if ((this.getPerfil().getId() != null) && (this.getPerfil().getId().longValue() == 0))
				this.getPerfil().setId(null);

			this.dao.salvar(this.getPerfil());
			// limpa a lista
			this.perfis = null;

			// limpar o objeto da página
			this.setPerfil(new Perfil());

			return "perfilListar";
		}

		/**
		 * 
		 */
		public String acaoCancelar()
		{
			// limpar o objeto da página
			this.setPerfil(new Perfil());

			return "perfilListar";
		}

		/**
		 * 
		 */
		public String acaoExcluir()
		{
			Long id = JSFUtil.getParametroLong("itemId");
			Perfil objetoDoBanco = this.dao.lerPorId(id);
			this.dao.excluir(objetoDoBanco);

			// limpar o objeto da página
			this.setPerfil(new Perfil());
			// limpa a lista
			this.perfis = null;

			return "perfilListar";
		}

}
