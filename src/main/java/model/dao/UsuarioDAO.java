package model.dao;

import javax.persistence.EntityManager;

import util.ConnectionFactory;

public class UsuarioDAO<Usuario extends model.bean.usuario.Usuario> {

	private Class<Usuario> persistedClass;

	public UsuarioDAO(Class<Usuario> persistedClass) {
		this.persistedClass = persistedClass;
	}

	@SuppressWarnings("unchecked")
	public Usuario findByEmail(String email) {
		EntityManager manager = ConnectionFactory.getEntityManager();
		Usuario usuario;

		try {
			manager.getTransaction().begin();
			usuario = (Usuario) manager.createQuery("SELECT * FROM :usuario u WHERE u.email = :email")
					.setParameter("usuario", persistedClass.getClass().getSimpleName())
					.setParameter("email", email)
					.getSingleResult();
			manager.getTransaction().commit();
			
			return usuario;
		} catch (Exception ex) {
			manager.getTransaction().rollback();
			ex.printStackTrace();
			return null;
		}
	}
	
	public Usuario login(String email, String senha) {
		Usuario usuario = findByEmail(email);
		
		if(usuario.getSenha().equals(senha)) {
			return usuario;
		}
		return null;
	}
}
