
package edu.ipn.cecyt9.calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * 
 * Interfaz para nuestra calculadora basica
 * 
 * @author:  Juan Carlos Nevarez Tovar& Juan Ignacio Monroy Gonzalez 
 * @version:  15/08/2017/A
 * @date: 06-09-2015 
 */
public class Calculadora extends JFrame {

	/**
	 * generado
	 */
	private static final long serialVersionUID = 1583724102189855698L;

	/** numero tecleado */
	JTextField pantalla;

	/** guarda el resultado de la operacion anterior o el número tecleado */
	double resultado;

        double resultado2;
	/** para guardar la operacion a realizar */
	String operacion;

	/** Los paneles donde colocaremos los botones */
	JPanel panelNumeros, panelOperaciones;

	/** Indica si estamos iniciando o no una operación */
	boolean nuevaOperacion = true;

	/**
	 * Constructor. Crea los botones y componentes de la calculadora
	 */
	public Calculadora() {
		super();
		setSize(400, 400);
		setTitle("Calculadora Simple");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		// Vamos a dibujar sobre el panel
		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());

		pantalla = new JTextField("0", 20);
		pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
		pantalla.setFont(new Font("Arial", Font.BOLD, 25));
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setEditable(false);
		pantalla.setBackground(Color.WHITE);
		panel.add("North", pantalla);

		panelNumeros = new JPanel();
		panelNumeros.setLayout(new GridLayout(5, 3));
		panelNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));

		for (int n = 9; n >= 0; n--) {
			nuevoBotonNumerico("" + n);
		}

		nuevoBotonNumerico(".");

		panel.add("Center", panelNumeros);

		panelOperaciones = new JPanel();
		panelOperaciones.setLayout(new GridLayout(6, 1));
		panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));

		nuevoBotonOperacion("+");
		nuevoBotonOperacion("-");
		nuevoBotonOperacion("*");
		nuevoBotonOperacion("/");
		nuevoBotonOperacion("=");
                //En esta parte se crean los botones de las 5 nuevas operaciones
                nuevoBotonOperacion("!");
                nuevoBotonOperacion("^2");
                nuevoBotonOperacion("%");
                nuevoBotonOperacion("sin");
                nuevoBotonOperacion("cos");
                
		nuevoBotonOperacion("CE");

		panel.add("East", panelOperaciones);

		validate();
	}

	/**
	 * Crea un boton del teclado numérico y enlaza sus eventos con el listener
	 * correspondiente
	 * 
	 * @param digito
	 *            boton a crear
	 */
	private void nuevoBotonNumerico(String digito) {
		JButton btn = new JButton();
		btn.setText(digito);
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				numeroPulsado(btn.getText());
			}
		});

		panelNumeros.add(btn);
	}

	/**
	 * Crea un botón de operacion y lo enlaza con sus eventos.
	 * 
	 * @param operacion
	 */
	private void nuevoBotonOperacion(String operacion) {
		JButton btn = new JButton(operacion);
		btn.setForeground(Color.RED);

		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				operacionPulsado(btn.getText());
			}
		});

		panelOperaciones.add(btn);
	}

	/**
	 * Gestiona las pulsaciones de teclas numéricas
	 * 
	 * @param digito
	 *            tecla pulsada
	 */
	private void numeroPulsado(String digito) {
		if (pantalla.getText().equals("0") || nuevaOperacion) {
			pantalla.setText(digito);
		} else {
			pantalla.setText(pantalla.getText() + digito);
		}
		nuevaOperacion = false;
	}

	/**
	 * Gestiona el gestiona las pulsaciones de teclas de operación
	 * 
	 * @param tecla
	 */
	private void operacionPulsado(String tecla) {
		if (tecla.equals("=")) {
			calcularResultado();
		} else if (tecla.equals("CE")) {
			resultado = 0;
			pantalla.setText("");
			nuevaOperacion = true;
		} else {
			operacion = tecla;
			if ((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			} else {
				resultado = new Double(pantalla.getText());
			}
		}

		nuevaOperacion = true;
	}

	/**
	 * Calcula el resultado y lo muestra por pantalla
	 */
	private void calcularResultado() {
		if (operacion.equals("+")) 
                {       
			resultado += new Double(pantalla.getText());
		} 
                else if (operacion.equals("-")) 
                {
			resultado -= new Double(pantalla.getText());
		} 
                else if (operacion.equals("/")) 
                {
			resultado /= new Double(pantalla.getText());
		} 
                else if (operacion.equals("*")) 
                {
			resultado *= new Double(pantalla.getText());
		}
                else if (operacion.equals("!")) 
                {
                        //Se declaran las variables; entrada que es el resultado de lo puesto en pantalla y el factorial que inicializaremos en uno
                        double Entrada = new Double (pantalla.getText());
                        double factorial = 1;
                        //Creamos un ciclo en el cual la entrada de pantalla va a disminuir en uno 
                        //Mientras el factorial se va multiplicando por la variable entrada
                        while(Entrada!=0){
                            factorial = factorial*Entrada;
                            Entrada--;
                        }
                       	resultado = factorial;
		}
                else if (operacion.equals("^2")) 
                {
                        //Declaramos la variable potencia que de igual forma sera el resultado
                        double potencia;
                        //Mediante la funcion math calculamos el valor del cuadrado del numero dijitado
                        potencia = Math.pow(new Double (pantalla.getText() ), 2);
			resultado = potencia;
		}
                else if (operacion.equals("%")) 
                {
			resultado = new Double(pantalla.getText()) / 100;
                        pantalla.setText(pantalla.getText() + "%");
		}
                else if (operacion.equals("sin")) 
                {
                        //En la variable x guardamos el valor del numero en pantalla
                        double x = new Double(pantalla.getText());
                        //Con la funcion de math "sin" calculamos el seno mandando como parametro x
			resultado = Math.sin(x);
		}
                else if (operacion.equals("cos")) 
                {
                        
                        //Declaramos la variable x que sera igual a lo contenido en pantalla
			double x = new Double(pantalla.getText());
                          /**
                           * Con la funcion de math "cos" calsculamos el coseno, asi como con el seno mandamos de parametro x
                           *@param x
                        */
			resultado = Math.cos(x);
		}

		pantalla.setText("" + resultado);
		operacion = "";
	}
}
