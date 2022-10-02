package edu.dgs.usuarios.controller;

import java.util.ArrayList;

import edu.dgs.usuarios.idao.IUsuarioDao;
import edu.dgs.usuarios.idao.UsuarioDao;
import edu.dgs.usuarios.model.Usuario;

public class UsuarioController {
	
	//private ClienteView vista= new ClienteView();
	private IUsuarioDao usuarioDao= new  UsuarioDao();
	
	public UsuarioController() {
	}
	
	// Insert de un Usuario
	public int insertUsuario(Usuario usuario) {
		int filas = usuarioDao.insertUsuario(usuario);
		
		if (filas > 0 && usuario.getId() == null){
			// Recupera el id del cliente isnertado
			int id = usuarioDao.getUsuarioMaxId();
			if (id > 0){
				filas = id;
				usuario.setId(id);
			}
		}
		return filas;
	}
	
	// Update de un Usuario
	public int updateUsuario(Usuario usuario) {
		int filas = usuarioDao.updateUsuario(usuario);
		return filas;
	}

	// Delete de un Usuario
	public int deleteUsuario(Usuario usuario) {
		int filas = usuarioDao.deleteUsuario(usuario);
		return filas;
	}


	// Valida usuario en el login
	public Usuario getUsuarioLogin(Usuario usuario) {
		Usuario usuarioLogin = null;
		usuarioLogin = usuarioDao.getUsuarioLogin(usuario);
		return usuarioLogin;
	}
	
	// Usuario por su ID
	public Usuario getUsuarioByIdUsuario(Usuario usuario) {

		Usuario usuarioById = null;
		usuarioById = usuarioDao.getUsuarioById(usuario);
		
		return usuarioById;
	}
	
	// Usuario por su nombre
	public Usuario getUsuarioByUsername(Usuario usuario) {

		Usuario usuarioByUsername = null;
		usuarioByUsername = usuarioDao.getUsuarioByUsername(usuario);
		
		return usuarioByUsername;
	}

	// Valida campos unicos en la tabla
	public ArrayList<Usuario> getUsuarioValidate(Usuario usuario) {
		ArrayList<Usuario> usuarios = usuarioDao.getUsuarioValidate(usuario);
		return usuarios;
	}

	// Ultimo id de la tabla
	public int getUsuarioMaxId(){
		int id = 0;
		id = usuarioDao.getUsuarioMaxId();
		return id;
	}

}
