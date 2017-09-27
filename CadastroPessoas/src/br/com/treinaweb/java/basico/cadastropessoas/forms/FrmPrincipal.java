package br.com.treinaweb.java.basico.cadastropessoas.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import br.com.treinaweb.java.basico.cadastropessaos.model.Pessoa;
import br.com.treinaweb.java.basico.cadastropessoas.dao.PessoaDao;
import br.com.treinaweb.java.cadastropessoas.componentes.PessoaJTable;

public class FrmPrincipal extends JFrame {

	public FrmPrincipal() {
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(200, 150);
		this.setTitle("TreinaWeb - Cadastro de Pessoas");

		JPanel panel = new JPanel();
		panel.setLayout(null);

		// ID
		JLabel lblId = new JLabel("Id: ");
		lblId.setBounds(10, 10, 50, 20);
		panel.add(lblId);
		JTextField txtId = new JTextField("");
		txtId.setBounds(55, 10, 515, 20);
		txtId.setEnabled(false);
		panel.add(txtId);

		// Name
		JLabel lblName = new JLabel("Nome: ");
		lblName.setBounds(10, 35, 50, 20);
		panel.add(lblName);
		JTextField txtName = new JTextField("");
		txtName.setBounds(55, 35, 515, 20);
		panel.add(txtName);

		// Age
		JLabel lblAge = new JLabel("Idade: ");
		lblAge.setBounds(10, 60, 50, 20);
		panel.add(lblAge);
		JTextField txtAge = new JTextField("");
		txtAge.setBounds(55, 60, 100, 20);
		txtAge.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				if (Character.isAlphabetic(key)) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		panel.add(txtAge);

		// JTabel
		PessoaJTable tblPessoas = new PessoaJTable();
		tblPessoas.setBounds(10, 110, 580, 300);
		tableLoading(tblPessoas);
		tblPessoas.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Pessoa selectedPerson = tblPessoas.getSelectedPerson();
					txtId.setText(String.valueOf(selectedPerson.getId()));
					txtName.setText(selectedPerson.getNome());
					txtAge.setText(String.valueOf(selectedPerson.getIdade()));
				}
			}
		});
		panel.add(tblPessoas);

		// Buttons
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setBounds(55, 85, 90, 20);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Pessoa newPerson = new Pessoa();
				newPerson.setNome(txtName.getText());
				newPerson.setIdade(Integer.parseInt(txtAge.getText()));
				try {
					new PessoaDao().insert(newPerson);
					JOptionPane.showMessageDialog(null, 
							"Pesssoa inserida com  Sucesso !!");
					tableLoading(tblPessoas);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e1) {

					JOptionPane.showMessageDialog(null, 
							"Houve um erro ao Inserir uma Pessoa");
				}
			}
		});
		panel.add(btnAdd);

		JButton btnAlter = new JButton("Alterar");
		btnAlter.setBounds(160, 85, 90, 20);
		btnAlter.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Pessoa alteredPerson = new Pessoa();
				alteredPerson.setId(Integer.parseInt(txtId.getText()));
				alteredPerson.setNome(txtName.getText());
				alteredPerson.setIdade(Integer.parseInt(txtAge.getText()));
				try {
					new PessoaDao().update(alteredPerson);
					JOptionPane.showConfirmDialog(null, 
							"Pessoa Alterada com Sucesso.");
					tableLoading(tblPessoas);
				} catch (ClassNotFoundException | InstantiationException 
						| IllegalAccessException | SQLException e1) {
					JOptionPane.showConfirmDialog(null, 
							"Erro ao Alterar os dados do Usuário !!");
				}
			}
		});
		panel.add(btnAlter);

		JButton btnDel = new JButton("Excluir");
		btnDel.setBounds(265, 85, 90, 20);
		btnDel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Pessoa deletedPerson = new Pessoa();
				deletedPerson.setId(Integer.parseInt(txtId.getText()));
				try {
					new PessoaDao().delete(deletedPerson);
					JOptionPane.showConfirmDialog(null, 
							"Pessoa Excluida com Sucesso");
					tableLoading(tblPessoas);
				} catch (ClassNotFoundException | InstantiationException 
						| IllegalAccessException | SQLException e1) {
					JOptionPane.showConfirmDialog(null, 
							"Erro ao tentar Excluir a Pessoa do Banco de Dados");
				}
			}
		});
		panel.add(btnDel);

		// panel view
		this.add(panel);

		this.setVisible(true);
	}

	private void tableLoading(PessoaJTable table) {
		try {
			table.load(new PessoaDao().all());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar as Pessoas do Banco de Dados.");
		}
	}
}
