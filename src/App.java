import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

public class App {

	public static void main(String[] args) throws IOException {

		criaArquivoInput();
		Path path = Paths.get("texto.txt");

		List<String> lstLinhas = Files.readAllLines(path, StandardCharsets.UTF_8);
		StringBuilder sb = new StringBuilder();

		for (String linha : lstLinhas) {
			if (linha != null) {
				if (validadorLinha(linha)) {
					System.out.println(linha + " - OK");
					sb.append(linha + " - OK\n");
				} else {
					System.out.println(linha + " - INVÁLIDO");
					sb.append(linha + " - INVÁLIDO\n");
				}
			}
		}
		criaArquivoOutput(sb.toString());
	}

	private static void criaArquivoOutput(String string) throws IOException {
		File arquivoOut = new File("check-texto.txt");

		if (!arquivoOut.exists()) {
			arquivoOut.createNewFile();
		} else {
			arquivoOut.delete();
			arquivoOut.createNewFile();
		}
		FileWriter writer2 = new FileWriter(arquivoOut);
		writer2.write(string);
		writer2.close();
	}

	private static void criaArquivoInput() throws IOException {
		File arquivo = new File("texto.txt");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		} else {
			arquivo.delete();
			arquivo.createNewFile();
		}
		FileWriter writer = new FileWriter(arquivo);
		writer.write("{[]}\n" + "([)]\n" + "[{()()}[]]\n" + "{}()[()]]\n" + ")[{}]()(\n" + "(()[)]\n" + "<<[]()>>\n"
				+ "<<()[<>}>\n");
		writer.close();
	}

	public static boolean validadorLinha(String linha) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < linha.length(); i++) {

			if (linha.charAt(i) == '(' || linha.charAt(i) == '[' || linha.charAt(i) == '{' || linha.charAt(i) == '<') {
				stack.push(linha.charAt(i));
			} else {
				if (stack.isEmpty()) {
					return false;
				} else {
					if (linha.charAt(i) == ')' && stack.peek() == '(' || linha.charAt(i) == ']' && stack.peek() == '['
							|| linha.charAt(i) == '}' && stack.peek() == '{'
							|| linha.charAt(i) == '>' && stack.peek() == '<') {
						stack.pop();
					} else {
						return false;
					}
				}
			}
		}
		if (stack.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}