package bean;

public class Usuario {
	
	private String login;
	private String senha;
	private Long id;
	
	public Usuario() {
		super();
	}

	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	public boolean validarSenha(String login, String senha) {
		if(login.equals("admin") && senha.equals("admin123")) {
			return true;
		} else {
			return false;
		}
	}
}
