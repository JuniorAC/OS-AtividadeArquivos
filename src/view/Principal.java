package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {
		IArquivosController arq = new ArquivosController();
			
		
		int opcao = 0;
		while (opcao != 9) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("1: para criar o diretorório e registro" + "\n2: para imprimir o cadastro" + "\n3: para inserir cadastro" + "\n9: para sair"));

			switch (opcao) {
			case 1:
				try {
					arq.verificaDirTemp();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 2:
				String arquivoBusca = "registro.csv";
				int codigoBusca = Integer.parseInt(JOptionPane.showInputDialog("Digite o código"));
				try {
					arq.imprimeCadastro(arquivoBusca, codigoBusca);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 3:
				String arquivo = "registro.csv";
				int codigo= Integer.parseInt(JOptionPane.showInputDialog("Digite um número do código"));
				String nome = JOptionPane.showInputDialog("Digite um nome");
				String email = JOptionPane.showInputDialog("Digite um email");
				
				try {
					arq.insereCadastro(arquivo, codigo, nome, email);
				} catch (IOException e) {
					e.printStackTrace();
				
				}	
				break;
			
			case 9:
				System.out.println("Saindo");
				break;

			default:
				JOptionPane.showMessageDialog(null, "Opção Inválida");
				break;
			}
		}
		
	}

}
