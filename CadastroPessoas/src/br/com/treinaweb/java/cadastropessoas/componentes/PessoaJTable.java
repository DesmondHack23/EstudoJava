package br.com.treinaweb.java.cadastropessoas.componentes;

import java.util.List;

import javax.swing.JTable;

import br.com.treinaweb.java.basico.cadastropessaos.model.Pessoa;

public class PessoaJTable extends JTable {

	private static final long serialVersionUID = -5711928283117229967L;
	private PessoaTabelModel tableModel;

	public PessoaJTable() {
		this.tableModel = new PessoaTabelModel();
		setModel(this.tableModel);
	}

	public void load(List<Pessoa> pessoas) {
		this.tableModel.load(pessoas);
	}

	public Pessoa getSelectedPerson() {
		int index = getSelectedRow();
		return this.tableModel.getPessoaAt(index);

	}
}
