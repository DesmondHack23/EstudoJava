package br.com.treinaweb.java.cadastropessoas.componentes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import br.com.treinaweb.java.basico.cadastropessaos.model.Pessoa;

public class PessoaTabelModel extends AbstractTableModel {

	private static final long serialVersionUID = -551250854495873258L;
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private String[] columnNames = { "id", "Nome", "Idade" };
	private Class<?>[] columTypes = { Integer.class, String.class, Integer.class };

	public void load(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
		fireTableDataChanged();
	}

	public Pessoa getPessoaAt(int index) {
		if (this.pessoas.size() <= 0) {
			return null;
		}
		return this.pessoas.get(index);
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return this.pessoas.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		if (this.pessoas.size() <= 0) {
			return null;
		}
		Pessoa p = this.pessoas.get(arg0);
		switch (arg1) {
		case 0:
			return p.getId();
		case 1:
			return p.getNome();
		case 2:
			return p.getIdade();

		}
		return null;
	}

}
