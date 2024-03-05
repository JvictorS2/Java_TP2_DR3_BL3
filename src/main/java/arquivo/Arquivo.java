package arquivo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Arquivo {
    public String getName() {
        return name;
    }

    //fields
    //Nome do arquivo a ser acessado
    protected String name;

    //Caminho do arquivo caso esteja em alguma pasta
    protected String pathFile;

    //Extensão do arquivo
    protected String extension;

    //Nome do projeto, arquivo que contem todos os arquivos deste projeto
    protected String projectName;

    // Escritor
    private FileWriter writer;
    private BufferedWriter buffWriter;

    // Leitor
    private FileReader reader;
    private BufferedReader buffReader;


    //Constructor
    public Arquivo(String projectName ,String nome, String extension) {
        this.projectName = projectName;
        this.name = nome;
        this.extension = extension;
        this.pathFile = "";
    }

    public Arquivo(String projectName, String nome, String extension, String pathFile) {
        this.projectName = projectName;
        this.name = nome;
        this.extension = extension;
        this.pathFile = pathFile;
    }

    //Methods

    // CRUD start ---------------------------------------------------
    public void GravarMensagem(String menssagem){
        try {
            // Cria um objeto FileWriter para escrever no arquivo
            this.writer = new FileWriter(pathFile, true);

            // Cria um objeto BufferedWriter para escrever no arquivo de forma eficiente
            this.buffWriter = new BufferedWriter(this.writer);

            // Escreve o conteúdo no arquivo
            this.buffWriter.write(menssagem);
            this.writer.flush();
            this.buffWriter.newLine();


            // Feche os recursos após a escrita
            this.buffWriter.close();
            this.writer.close();

        } catch (IOException e){
            System.out.println("Error ao tenta gravar uma mensagem no arquivo " + name);
        }
    }

    public void GravarMensagem(List<String> lista){
        try {
            // Cria um objeto FileWriter para escrever no arquivo
            this.writer = new FileWriter(pathFile, false);

            // Cria um objeto BufferedWriter para escrever no arquivo de forma eficiente
            this.buffWriter = new BufferedWriter(this.writer);

            // Escreve o conteúdo no arquivo
            for(int i = 0; i < lista.size(); i++)
            {
                this.buffWriter.write(lista.get(i));
                this.buffWriter.newLine();
            }

            // Feche os recursos após a escrita
            this.buffWriter.close();
            this.writer.close();



        } catch (IOException e){
            System.out.println("Error ao tenta gravar uma mensagem no arquivo " + name);
        }
    }

    public void LerArquivo(){
        try{
            this.reader = new FileReader(pathFile);
            this.buffReader = new BufferedReader(reader);

            // Variável para armazenar cada linha lida do arquivo
            String linha;

            // Loop para ler cada linha do arquivo
            while ((linha = this.buffReader.readLine()) != null) {
                // Faça o que desejar com cada linha (exibindo, processando, etc.)
                System.out.println(linha);
            }
            // Feche os recursos após a leitura
            this.buffReader.close();
            this.reader.close();

        }catch (IOException e) {
            System.out.println("Error ao tenta ler o arquivo " + name);
            System.out.println(e);
        }
    }

    public List<String> LerLinhasArquivo()  {
        /*
            Devolve uma Lista com todas as linhas do arquivo.
        */

        try{

            Path p = Paths.get(this.pathFile);

            return Files.readAllLines(p);

        }catch (IOException e) {
            System.out.println("Error ao tenta ler todas as linhas do " + name);
        }

        return null;

    }

    public void RemoveLinhaArquivo(int linha) {
        /*
            Apaga uma linha do arquivo
            parâmetro:
                linha - a linha que será apagada do arquivo
           */

        try
        {
            // Lê todas as linhas do arquivo, exceto a que queremos excluir
            var linhas = this.LerLinhasArquivo();


            // Exclui a linha desejada
            linhas.remove(linha - 1);

            // Reescreve o arquivo com as linhas restantes
            this.GravarMensagem(linhas);

        }

        catch (Exception e)
        {
            System.out.println("Error ao tenta remover a linha a "+ linha +" do arquivo " + name + "." + extension);

        }
    }

    public void AtuaLizarLinhaArquivo(int id, String mensagem) {
        /*
            Atualiza uma linha do arquivo.
            parâmetro:
                numberline - linha que deseja autlaizar
                newline - texto que irá substituir a linha escolhida
        */

        try
        {
            // Lê todas as linhas do arquivo, exceto a que queremos excluir
            var linhas = this.LerLinhasArquivo();

            linhas.set(id - 1,mensagem);

            // Reescreve o arquivo com as linhas atualizada
            this.GravarMensagem(linhas);

        }

        catch (Exception e)
        {
            System.out.println("Error ao tenta remover a linha de id "+ id +" do arquivo " + name);

        }

    }

    // CRUD end ----------------------------------------------------

    public int TotalLinha(){
        List<String> lista = LerLinhasArquivo();
        return lista.size() ;

    }
}
