package edu.dgs.usuarios.vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import edu.dgs.usuarios.idao.UsuarioDao;
import edu.dgs.usuarios.model.Usuario;


public class VentanaLogin extends JFrame {
	public JPanel panel,panelCajadeTextos,panelBotones,panelAux;
	public JTextField usuariotxt,cajaCorreo;
	public JPasswordField contraseña;	
	public JLabel etiUsuario, etiPassword;
	public JButton btnAceptar ,btnCancelar, btnRegistrarse , btnModificar ;
	private int maxTamaño= 16 ;
	VentanaLogin(){
	this.setTitle("Login");
	this.setLocation(400, 200);
	this.setSize(350, 200);
	this.setResizable(false);
	panel=new JPanel();
	panel.setLayout(null);
	panel.setBackground(Color.black);
	this.getContentPane().add(panel);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	cargarTodosLosComponentes();
	}
	
	public void cargarTodosLosComponentes(){
		panelConBotones();
		panelConTexto();
		panelExtra();
		
	}
	//-----------------------------------------------------------

	public void panelConBotones(){
		panelBotones=new JPanel();
		btnAceptar= new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");
	
		//--------------------------------------------------
		panelBotones.setLayout(new GridLayout(1,2,3,3));
		panelBotones.setLocation(80,80);
		panelBotones.setBackground(Color.BLACK);
		panelBotones.setSize(200,15);
		//--------------------------------------------------
		btnAceptar.setBackground(Color.BLUE);
		btnAceptar.setForeground(Color.CYAN);
		eventobtnAceptar();
		//--------------------------------------------------
		btnCancelar.setBackground(Color.BLUE);
		btnCancelar.setForeground(Color.CYAN);
		eventobtnCancelar();
				
		//--------------------------------------------------
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
		//--------------------------------------------------
		panel.add(panelBotones);
	}
	public void panelConTexto(){
		panelCajadeTextos=new JPanel();
		usuariotxt=new JTextField (maxTamaño);
		contraseña = new JPasswordField (maxTamaño);
		etiUsuario =new JLabel("Usuario");
		etiPassword=new JLabel("Contraseña");
		//-------------------------------------------------
		eventoControlDeIngreso();
		eventoControlTipeoUsername();
		//--------------------------------------------------
		panelCajadeTextos.setLayout(new GridLayout(2,2,3,3));
		panelCajadeTextos.setLocation(40,20);
		panelCajadeTextos.setSize(260,40);		
		panelCajadeTextos.setBackground(Color.BLACK);
		//--------------------------------------------------
		etiUsuario.setLayout(null);
		etiUsuario.setForeground(Color.cyan);
		etiPassword.setLayout(null);
		etiPassword.setForeground(Color.cyan);
		//--------------------------------------------------
		panelCajadeTextos.add(etiUsuario);
		panelCajadeTextos.add(usuariotxt);
		panelCajadeTextos.add(etiPassword);
		panelCajadeTextos.add(contraseña);
		panel.add(panelCajadeTextos);
	}
	public void panelExtra(){
		panelAux = new JPanel();
		btnRegistrarse=new JButton("Registrarse");
		btnModificar=new JButton("Modificar");
		//--------------------------------------------------
		panelAux.setLayout(new GridLayout(2,1,3,3));
		panelAux.setBackground(Color.black);
		panelAux.setSize(130,40);
		panelAux.setLocation(105,120);
		//--------------------------------------------------
		btnRegistrarse.setLayout(null);
		btnRegistrarse.setBackground(Color.black);
		btnRegistrarse.setForeground(Color.CYAN);
		eventoBtnRegistrarse();
		//--------------------------------------------------
		btnModificar.setLayout(null);
		btnModificar.setBackground(Color.black);
		btnModificar.setForeground(Color.CYAN);
		//eventoBtnModificar();
		//--------------------------------------------------
		panelAux.add(btnRegistrarse);
		//panelAux.add(btnModificar);
		panel.add(panelAux);
	}
		
	//-----------------------------------------------------------
	
	public void mostrarVentanaCargar( String titulo){
		   VentanaCargarModificar ventanacargar = new VentanaCargarModificar();
		   ventanacargar.setVisible(true);
		   ventanacargar.setTitle(titulo);
		   this.dispose();
	   }
	//-----------------------------------------------------------

	private void eventobtnAceptar(){
		ActionListener aceptar =new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				aceptar();
				}
			};
			btnAceptar.addActionListener(aceptar);
	}
	private void aceptar() {
	String usuario= usuariotxt.getText();
	String clave= String.valueOf(contraseña.getPassword());
	UsuarioDao controlDeUsuario = new UsuarioDao();
	 Usuario usuario2= new Usuario();
	 usuario2.setUsername(usuario);
	 usuario2.setPassword(clave);
	 Usuario usu=controlDeUsuario.getUsuarioLogin(usuario2);
	if (usu!=null){
	JOptionPane.showMessageDialog(null,"Bienvenido al sistema");
	}else{
		JOptionPane.showMessageDialog(null,"Datos invalidos","Error",JOptionPane.ERROR_MESSAGE); 
	}	
}
	//-----------------------------------------------------------

	private void eventobtnCancelar(){
		ActionListener cancelar =new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);;
				 }
			};
			btnCancelar.addActionListener(cancelar);
	}
	//-----------------------------------------------------------

	/*private void eventoBtnModificar(){
		ActionListener modificar =new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
			 boolean a =verificacion();
			 if (a==false){
				mostrarVentanaCargar("Modificar");
			 }
				 }
			};
			btnModificar.addActionListener(modificar);
	}
	public boolean verificacion(){
		boolean sn=true;
		String usuario, clave;
		usuario=usuariotxt.getText();
		clave=String.valueOf(contraseña.getPassword());
		UsuarioDao verificar = new UsuarioDao();
		 Usuario usuario2= new Usuario();
		 usuario2.setUsername(usuario);
		 usuario2.setPassword(clave);
		 Usuario usu=verificar.getUsuarioByUsername(usuario2);
		if (usuario.isEmpty() & clave.isEmpty()){
			JOptionPane.showMessageDialog(null, "Ingrese un usuario existente en la base de datos");
			sn=true;
		}
		else if ( usu!=null){
			cargarDatos();
						sn=false;
			}
		else{
				JOptionPane.showMessageDialog(null,"El nombre de usuario no existe en la base de datos","Error",JOptionPane.ERROR_MESSAGE); 
				sn=true;
		}
			return sn;
	}	
	private void cargarDatos(){
		Usuario muestra =new Usuario();
		String username,clave,email;
		username=muestra.getUsername();
		clave=muestra.getPassword();
		email=muestra.getEmail();
		usuariotxt.setText(username);
		contraseña.setText(clave);
		cajaCorreo.setText(email);
	}*/
//-----------------------------------------------------------

	private void eventoBtnRegistrarse(){
		ActionListener registrarse =new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				mostrarVentanaCargar("Nuevo");
				 }
			};
			btnRegistrarse.addActionListener(registrarse);
	}	
	//-----------------------------------------------------------

	public void eventoControlDeIngreso(){
		contraseña.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent event){
				if (contraseña.getPassword().length == contraseña.getColumns()){
						event.consume();
				}
			}
			public void keyPressed(KeyEvent arg0) {
			}
		 
			public void keyReleased(KeyEvent arg0) {
			}
			}
		);
			}
	public void eventoControlTipeoUsername(){
		usuariotxt.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent event){
				if (usuariotxt.getText().length() == usuariotxt.getColumns()){
						event.consume();
				}
			}
			public void keyPressed(KeyEvent arg0) {
			}
		 
			public void keyReleased(KeyEvent arg0) {
			}
			}
		);
			}
	
	
	}
			 
				
			
				
			


