package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.PrintWriter;



public class ArquivosController implements IArquivosController {

	public ArquivosController() {
		super();
	}
	static String linhaEncontrada = "";
	public void verificaDirTemp() throws IOException {
		File dir = new File("C:\\TEMP");
		File arq = new File(dir, "registro.csv");
		if (dir.exists() && dir.isDirectory()) {
			System.out.println("Diretorio e arquivo de registro ja existe");
		} else {
			dir.mkdir();
			String conteudo = "Codigo; Nome; Email"+ "\r\n";
			FileWriter filewriter = new FileWriter(arq);
			PrintWriter print = new PrintWriter(filewriter);
			print.write(conteudo);
			print.flush();
			print.close();
			filewriter.close();
			System.out.println("Arquivo de registro e diretorio criado");
		}

	}

	@Override
	public boolean verificaRegistro(String arquivo, int codigo) throws IOException {
		
		File arq = new File("C:\\TEMP", arquivo);

		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();

			while (linha != null) { 
				String[] compara = linha.split(";");
				if (compara[0].equals(String.valueOf(codigo))) {	
					linhaEncontrada = linha;
					buffer.close();
					leitor.close();
					fluxo.close();
					return true;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo inválido");
		}
		return false;
	}

	@Override
	public void imprimeCadastro(String arquivo, int codigo) throws IOException {
		File dir = new File("C:\\TEMP");
		File arq = new File("C:\\TEMP", arquivo);
		if (dir.exists() && dir.isDirectory()) {
			if (arq.exists()) {
				if (verificaRegistro(arquivo, codigo)) {
					String[] linhaSeparado = linhaEncontrada.split(";");
					System.out.println("Código: " + linhaSeparado[0] + "\nNome: " + linhaSeparado[1] + "\nEmail: "
							+ linhaSeparado[2]);
				} else {
					System.out.println("Código não encontrado");
				}
			}
		} else {
			throw new IOException("Diretório inválido");
		}

	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		File dir = new File("C:\\TEMP");
		File arq = new File("C:\\TEMP", arquivo);
		if (dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if (arq.exists()) {
				existe = true;
			}
			if (!verificaRegistro(arquivo, codigo)) {
				String conteudo = codigo + ";" + nome + ";" + email + "\r\n";
				FileWriter fileWriter = new FileWriter(arq, existe);
				PrintWriter print = new PrintWriter(fileWriter);
				print.write(conteudo);
				print.flush();
				print.close();
				fileWriter.close();
			} else {
				System.out.println("Código já existente");
			}
		} else {
			throw new IOException("Diretório inválido");
		}
	}

	}


