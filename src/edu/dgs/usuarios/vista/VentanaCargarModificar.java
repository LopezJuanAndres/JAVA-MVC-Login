package edu.dgs.usuarios.vista;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.dgs.usuarios.idao.UsuarioDao;
import edu.dgs.usuarios.model.Usuario;
import edu.dgs.usuarios.vista.VentanaLogin;
import edu.dgs.usuarios.validator.EmailValidator;
import edu.dgs.usuarios.validator.PasswordValidator;

public class VentanaCargarModificar extends VentanaLogin{ 
	public JCheckBoxMenuItem checkAdmin;
	
	public JLabel etiCorreo;
	public JButton btnGuardar;
	private int maxCantCaracteres =50;
	
	private EmailValidator emailValidator = new EmailValidator();
	private PasswordValidator passwordValidator = new PasswordValidator();
	VentanaCargarModificar(){
		this.setTitle("Registrar");
		this.setLocation(400, 100);
		this.setSize(180,300);
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.black);
		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cargarDiseño();
	}
	private void cargarDiseño(){
		panelConBotones();
		panelConTexto();
	}
	@Override
	public void panelConBotones() {
		super.panelConBotones();
		btnGuardar =new JButton("Insertar");
		panelBotones.setLayout(new GridLayout(2,1,1,1));
		panelBotones.setSize(150,40);
		panelBotones.remove(btnAceptar);
		panelBotones.add(btnGuardar);
		panelBotones.add(btnCancelar);
		panelBotones.setLocation(10,210);
		//-------------------------------------
		btnGuardar.setBackground(Color.BLUE);
		btnGuardar.setForeground(Color.CYAN);
		eventoBtnGuardar();
	}
	@Override
	public void panelConTexto() {
		super.panelConTexto();
		panelCajadeTextos.setLayout(new GridLayout(8,1,5,10));
		panelCajadeTextos.setSize(140,200);
		panelCajadeTextos.setLocation(10,10);
		//------------------------------------------------------------
		cajaCorreo = new JTextField(maxCantCaracteres);
		contraseña.setEchoChar((char)0);
		//------------------------------------------------------------

		etiCorreo= new JLabel("Correo Electronico");
		checkAdmin =new JCheckBoxMenuItem("Administrador");
		//------------------------------------------------------------
		etiCorreo.setLayout(null);
		etiCorreo.setForeground(Color.CYAN);
		
		//------------------------------------------------------------
		checkAdmin.setLayout(null);
		checkAdmin.setBackground(Color.black);
		checkAdmin.setForeground(Color.CYAN);
		checkAdmin.setSize(100,15);
		//------------------------------------------------------------

		panelCajadeTextos.add(etiCorreo);
		panelCajadeTextos.add(cajaCorreo);
		panelCajadeTextos.add(checkAdmin);
	}
//-------------------------------------------------
	
	public void mostrarVentanaLogin(){
		   VentanaLogin ventanacargar = new VentanaLogin();
		   ventanacargar.setVisible(true);
		   this.dispose();
	   }	
//------------------------------------------------------
	
	public void insertar() {
		
		String usuario= usuariotxt.getText();
		String clave= String.valueOf(contraseña.getPassword());
		String Correo=  cajaCorreo.getText();
		boolean administrador = checkAdmin.getState();
		UsuarioDao controlDeUsuario = new UsuarioDao();
		 Usuario usuario2= new Usuario(usuario,Correo,clave,administrador);
		 int usu=controlDeUsuario.insertUsuario(usuario2);
		if (usu!=0){
		JOptionPane.showMessageDialog(null,"Datos Registrados Correctamente ");
		}else{
			JOptionPane.showMessageDialog(null,"Error al Intentar Registrar datos","Error",JOptionPane.ERROR_MESSAGE); 
		}	
	}
	private void eventoBtnGuardar(){
		 ActionListener control =new ActionListener(){
				public void actionPerformed(ActionEvent ae) {
					eventoControlarTodo();
					}
				};
				btnGuardar.addActionListener(control);	
	 }
	//------------------------------------------------------
	public void eventoControlarTodo(){
		boolean c=eventoControlUsername();
		boolean b=eventoControlPassword ();
		boolean a=	eventoControlarCorreo();
		if (a==true & b==true & c==true){
			usuariotxt.setBackground(Color.WHITE);
			cajaCorreo.setBackground(Color.WHITE);
			contraseña.setBackground(Color.WHITE);
			insertar();
			usuariotxt.setText("");
			cajaCorreo.setText("");
			contraseña.setText("");	
			checkAdmin.setState(false);
		}
	}
	public boolean eventoControlarCorreo(){
				boolean verify ,ok ;
				ok=false;
				String message = "";
				String email="";
				email=cajaCorreo.getText();
			// Validaciobnes
			if (email.isEmpty()){ // Verifica que no sea nulo
				message = "El email es un campo obligatorio";
				cajaCorreo.requestFocus();
				cajaCorreo.selectAll();
				JOptionPane.showMessageDialog(null, message);
				ok=false;}
			else if(email.length() > cajaCorreo.getColumns()){ // Verifica la longitud , este caso nunca se va cumplir
				message = "El email no puede superar los " +
						cajaCorreo.getColumns() + " caracteres";
				cajaCorreo.requestFocus();
				cajaCorreo.selectAll();
				JOptionPane.showMessageDialog(null, message);
				ok=false;}
			else{  
				// Verifica el formato
				verify = emailValidator.verifyMail(email);
				if (verify){
					message = "El email es correcto";
					ok=true;
					cajaCorreo.setBackground(Color.green);
				}
				else{
					message = "El texto ingresado no corresponde a un email";
					JOptionPane.showMessageDialog(null, message);
					ok=false;
					}
				}
			return ok;
		}
	public boolean eventoControlUsername(){
	String username;
	boolean ok1;
	String message = "";
	JOptionPane mostrar = new JOptionPane();
	ok1=false;
	username =usuariotxt.getText();
	if (username.isEmpty()){ // Verifica que no sea nulo
		message = "Usuario es un campo obligatorio";
		usuariotxt.requestFocus();
		usuariotxt.selectAll();
		ok1=false;
		mostrar.showMessageDialog(null, message);
	}
	else {
		usuariotxt.setBackground(Color.green);
		ok1=true;
	}
	
	return ok1;
	
}
	public boolean eventoControlPassword (){
	        char[] passwordChar;
	        String password;
			boolean verify,ok2;
			ok2=false;
			String message = "";
			passwordChar=contraseña.getPassword();
			password = String.valueOf(passwordChar);
			
			// Validaciobnes
			
			if (password.isEmpty()){ // Verifica que no sea nulo
				message = "Contraseña es un campo obligatorio";
				contraseña.requestFocus();
				contraseña.selectAll();
				ok2=false;
				JOptionPane.showMessageDialog(null, message);
			}
			else if(password.length() < (contraseña.getColumns()/2)
					||
					password.length() > contraseña.getColumns()
					){ // Verifica la longitud
				message = "Password debe contener entre " +
						contraseña.getColumns()/2 +
						" y " + 
						contraseña.getColumns() +
						" caracteres";
				contraseña.requestFocus();
				contraseña.selectAll();
				JOptionPane.showMessageDialog(null, message);
				ok2=false;
			}
			else{  // Verifica el formato
				verify = passwordValidator.verifyPassword(password);
				if (verify){
					message = "La clave ingresada cumple con los requerimientos";
					ok2=true;
					contraseña.setBackground(Color.green);
				}
				else{
					message = "La clave ingresada NO cumple con los requerimientos";
					JOptionPane.showMessageDialog(null, message);
					ok2=false;
				}
			}
			return ok2;
			
		}
	//------------------------------------------------------

	}
