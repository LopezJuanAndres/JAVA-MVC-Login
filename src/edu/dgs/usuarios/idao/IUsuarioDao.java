package edu.dgs.usuarios.idao;

import java.util.ArrayList;

import edu.dgs.usuarios.model.Usuario;

public interface IUsuarioDao {
	
	public int insertUsuario(Usuario usuario);
	public int updateUsuario(Usuario usuario);
	public int deleteUsuario(Usuario usuario);
	public Usuario getUsuarioLogin(Usuario usuario);
	public Usuario getUsuarioById(Usuario usuario);
	public Usuario getUsuarioByUsername(Usuario usuario);
	public ArrayList<Usuario> getUsuarioValidate(Usuario usuario);
	public Integer getUsuarioMaxId();
}
