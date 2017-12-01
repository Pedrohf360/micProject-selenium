package teste;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IPTU_GUI {

	private int tipoBusca; // 1 = ind. cadastral; 2 = cpf + cep; 3 = cnpj
	private JFrame frmEmissoGuiaIptu;
	private JTextField IndCadtextField;
	private JTextField cpfTextField;
	private JTextField cnpjtextField;
	private JTextField ceptextField;
	private JTextField captchatextField;
	private IPTU iptu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IPTU_GUI window = new IPTU_GUI();
					window.frmEmissoGuiaIptu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public IPTU_GUI() throws IOException, InterruptedException {
		
		iptu = new IPTU();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws InterruptedException 
	 */
	private void initialize() throws InterruptedException {
		frmEmissoGuiaIptu = new JFrame();
		frmEmissoGuiaIptu.setTitle("Emiss\u00E3o Guia IPTU");
		frmEmissoGuiaIptu.setBounds(100, 100, 908, 633);
		frmEmissoGuiaIptu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmissoGuiaIptu.getContentPane().setLayout(null);
		
		JLabel lblEmissoGuiaIptu = new JLabel("Emiss\u00E3o Guia IPTU");
		lblEmissoGuiaIptu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmissoGuiaIptu.setBounds(346, 11, 191, 30);
		frmEmissoGuiaIptu.getContentPane().add(lblEmissoGuiaIptu);
		
		JLabel lblASegundaVia = new JLabel("A segunda via da Guia de IPTU pela internet pode ser emitida utilizando uma das seguintes op\u00E7\u00F5es de preenchimento:");
		lblASegundaVia.setHorizontalAlignment(SwingConstants.CENTER);
		lblASegundaVia.setBounds(125, 52, 623, 30);
		frmEmissoGuiaIptu.getContentPane().add(lblASegundaVia);
		
		JLabel lblOpondice = new JLabel("Op\u00E7\u00E3o 1.   \u00CDndice Cadastral do im\u00F3vel (seq\u00FC\u00EAncia de caracteres - n\u00FAmeros ou letras - que identifica seu im\u00F3vel no cadastro da PBH).");
		lblOpondice.setBounds(163, 85, 631, 14);
		frmEmissoGuiaIptu.getContentPane().add(lblOpondice);
		
		JLabel lblOpoCpf = new JLabel("Op\u00E7\u00E3o 2.   CPF ou CNPJ do propriet\u00E1rio juntamente com o CEP do im\u00F3vel (ou do endere\u00E7o de correspond\u00EAncia do im\u00F3vel).");
		lblOpoCpf.setBounds(163, 105, 631, 14);
		frmEmissoGuiaIptu.getContentPane().add(lblOpoCpf);
		
		JLabel lblParaACorreta = new JLabel("Para a correta identifica\u00E7\u00E3o, informe apenas os n\u00FAmeros (e letras, caso necess\u00E1rias) sem espa\u00E7os em branco, pontos ou h\u00EDfens.");
		lblParaACorreta.setBounds(151, 123, 649, 30);
		frmEmissoGuiaIptu.getContentPane().add(lblParaACorreta);
		
		JLabel lblOpo = new JLabel("Op\u00E7\u00E3o 1:");
		lblOpo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOpo.setBounds(61, 189, 615, 14);
		frmEmissoGuiaIptu.getContentPane().add(lblOpo);
		
		JLabel lblndiceCadastral = new JLabel("\u00CDndice cadastral:");
		lblndiceCadastral.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblndiceCadastral.setBounds(61, 228, 615, 14);
		frmEmissoGuiaIptu.getContentPane().add(lblndiceCadastral);
		
		IndCadtextField = new JTextField();
		IndCadtextField.setBounds(61, 255, 191, 30);
		frmEmissoGuiaIptu.getContentPane().add(IndCadtextField);
		IndCadtextField.setColumns(10);
		
		cpfTextField = new JTextField();
		cpfTextField.setColumns(10);
		cpfTextField.setBounds(304, 255, 191, 30);
		frmEmissoGuiaIptu.getContentPane().add(cpfTextField);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCpf.setBounds(304, 228, 372, 14);
		frmEmissoGuiaIptu.getContentPane().add(lblCpf);
		
		JLabel lblOpo_1 = new JLabel("Op\u00E7\u00E3o 2:");
		lblOpo_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOpo_1.setBounds(304, 189, 372, 14);
		frmEmissoGuiaIptu.getContentPane().add(lblOpo_1);
		
		cnpjtextField = new JTextField();
		cnpjtextField.setColumns(10);
		cnpjtextField.setBounds(558, 255, 191, 30);
		frmEmissoGuiaIptu.getContentPane().add(cnpjtextField);
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCnpj.setBounds(558, 228, 118, 14);
		frmEmissoGuiaIptu.getContentPane().add(lblCnpj);
		
		JLabel lblOpo_2 = new JLabel("Op\u00E7\u00E3o 3:");
		lblOpo_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOpo_2.setBounds(558, 189, 118, 14);
		frmEmissoGuiaIptu.getContentPane().add(lblOpo_2);
		
		ceptextField = new JTextField();
		ceptextField.setColumns(10);
		ceptextField.setBounds(304, 336, 191, 30);
		frmEmissoGuiaIptu.getContentPane().add(ceptextField);
		
		JLabel lblCepDoImvel = new JLabel("CEP do Im\u00F3vel:");
		lblCepDoImvel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCepDoImvel.setBounds(304, 309, 372, 14);
		frmEmissoGuiaIptu.getContentPane().add(lblCepDoImvel);
		
		JLabel lblDigiteOsCaracteres = new JLabel("Digite os caracteres da figura:");
		lblDigiteOsCaracteres.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDigiteOsCaracteres.setBounds(304, 407, 214, 14);
		frmEmissoGuiaIptu.getContentPane().add(lblDigiteOsCaracteres);
		
		JLabel captchaImgLbl = new JLabel("");
		
		Image img = new ImageIcon(this.getClass().getResource("/" + this.iptu.getNomeImagemCaptcha())).getImage();
		captchaImgLbl.setIcon(new ImageIcon(img));
		
		captchaImgLbl.setBounds(550, 421, 235, 72);
		frmEmissoGuiaIptu.getContentPane().add(captchaImgLbl);
		
		captchatextField = new JTextField();
		captchatextField.setBounds(328, 444, 157, 30);
		frmEmissoGuiaIptu.getContentPane().add(captchatextField);
		captchatextField.setColumns(10);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				switch (tipoBusca) {
				
				case 1:
					iptu.ConsultarIndiceCad(captchatextField.getText(), captchatextField.getText());
					break;
					
				case 2:
					iptu.ConsultarCpf(cpfTextField.getText(), ceptextField.getText(), captchatextField.getText());
					break;
					
				case 3:
					iptu.ConsultarCpnj(cnpjtextField.getText(), ceptextField.getText(), captchatextField.getText());
					break;
				}
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBuscar.setBounds(349, 496, 118, 43);
		frmEmissoGuiaIptu.getContentPane().add(btnBuscar);
		
		JRadioButton buscaIndCadRadBtn = new JRadioButton("\u00CDndice Cadastral");
		buscaIndCadRadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tipoBusca = 1;
			}
		});
		buscaIndCadRadBtn.setBounds(61, 447, 109, 23);
		frmEmissoGuiaIptu.getContentPane().add(buscaIndCadRadBtn);
		
		JRadioButton cpfRadBtn = new JRadioButton("CPF");
		cpfRadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tipoBusca = 2;
			}
		});
		cpfRadBtn.setBounds(61, 473, 109, 23);
		frmEmissoGuiaIptu.getContentPane().add(cpfRadBtn);
		
		JRadioButton cnpjRadBtn = new JRadioButton("CNPJ");
		cnpjRadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tipoBusca = 3;
			}
		});
		cnpjRadBtn.setBounds(61, 499, 109, 23);
		frmEmissoGuiaIptu.getContentPane().add(cnpjRadBtn);
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBuscarPor.setBounds(61, 420, 109, 20);
		frmEmissoGuiaIptu.getContentPane().add(lblBuscarPor);
	}
}
