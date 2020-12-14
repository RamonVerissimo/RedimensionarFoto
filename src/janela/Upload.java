package janela;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class Upload extends JFrame {

	private JPanel contentPane;
	JFileChooser escolha = new JFileChooser();
	File arquivo_in;
	Image foto;
	BufferedImage imagem = null;
	String caminho;
	private JTextField textField;
	private JButton btnReduz;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Upload frame = new Upload();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Criando o Frame.
	 */
	public Upload() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 222, 464, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setBackground(Color.WHITE);
		label.setBounds(157, 11, 200, 200);
		label.setBorder(null);
		contentPane.add(label);
		
		JButton btnUpload = new JButton("Abrir Imagem");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolha.showOpenDialog(null);
				arquivo_in = escolha.getSelectedFile();
				caminho = arquivo_in.getAbsolutePath();
				textField.setText(caminho);				
				
				ImageIcon icone = new ImageIcon(arquivo_in.getPath()); //abre o arquivo de imagem usando o caminho do arquivo_in
				foto = icone.getImage(); //Tipo de arquivo Image recebe a imagem do arquivo Icone
				foto = foto.getScaledInstance(200, 200, Image.SCALE_SMOOTH); //foto usa o método de redimensionar da classe Image e sobreescreve o arquivo foto
				icone = new ImageIcon(foto);
				label.setIcon(icone);
			}
		});
		btnUpload.setBounds(107, 253, 126, 23);
		contentPane.add(btnUpload);
		
		btnReduz = new JButton("Salvar Imagem");
		btnReduz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ação de redimensionar e salvar o arquivo na pasta
				try {
					imagem = ImageIO.read(new File(caminho));
				} catch (IOException ex) {
					textField.setText("");
					textField.setText("erro na leitura do arquivo");
				}
				
				BufferedImage nova_imagem = new BufferedImage(120, 160, BufferedImage.TYPE_INT_RGB );
				Graphics2D g = nova_imagem.createGraphics();
				caminho = caminho.replaceAll(".jpg", "_novo.jpg");
				g.drawImage(imagem, 0, 0, 120, 160, null);
				try {
					ImageIO.write(nova_imagem, "jpg", new File(caminho));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					textField.setText("");
					textField.setText("erro na gravação do arquivo");	
				} finally {
					textField.setText(caminho);	
				}
			}
		});
		btnReduz.setBounds(243, 253, 126, 23);
		contentPane.add(btnReduz);

		
	}
}
