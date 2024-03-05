package arquivo;
import java.util.List;

public class ArquivoCsv extends Arquivo {
	

    public ArquivoCsv(String projectName, String nome, String extension) {
        super(projectName, nome, extension);
       
    }

    public ArquivoCsv(String projectName, String nome, String extension,String pathFile) {
        super(projectName, nome, extension,pathFile);
        
    }


    // CRUD -------------------------------------------------------
    public void AdcionarLinha(String[] array){
        /*
        * Recebe uma array e grava as informações dess array em uma linha do arquivo
        * */

        GravarMensagem(ConverteArrayParaString(array));
    }

    public void AdcionarLinha(String mensagem){
        /*
         * Recebe uma array e grava as informações dess array em uma linha do arquivo
         * */

        GravarMensagem(mensagem);
    }

    public void RemoveLinhaArquivoPorId(int id) {
        /*
            Apaga uma linha do arquivo
            parâmetro:
                id - id da linha que será apagada do arquivo
           */

        try
        {

            if(!(ObterLinhaPorId(id) == null)){
                this.RemoveLinhaArquivo(ObterNumeroLinhaPorId(id));

            }else{

                System.out.println("Este Id não está registrado!");
            }

        }

        catch (Exception e)
        {
            System.out.println("Error ao tenta remover a linha de id "+ id +" do arquivo " + name + ".csv");

        }

    }

    public void AtualizarLinhaArquivoPorId(int id, String mensagem){
        /*
        * Atualiza a linha do arquivo que possui o id informado
        * parâmetros:
        *      id: id da linha
        *      mensagem: Mesagem que será gravada
        * */

        try
        {

            if(!(ObterLinhaPorId(id) == null)){
                AtuaLizarLinhaArquivo(ObterNumeroLinhaPorId(id),mensagem);

            }else{

                System.out.println("Este Id não está registrado!");
            }

        }

        catch (Exception e)
        {
            System.out.println("Error ao tenta Atualizar a linha de id "+ id +" do arquivo " + name + ".csv");

        }
    }

    // CRUD acaba --------------------------------------------------

    //Métodos de apoio ---------------------------------------------

    //Id
    public int ObterAtualId() {
        /*
            Retrona o valor do Id da ultima linha do arquivo.
        */
        // Ler todas as linhas do arquivo
        List<String> lista = this.LerLinhasArquivo();

        // Transforma a ultima linha do arquivo em um array
        String[] arrayUltimaLinha= ConverteStringParaArray(lista.get(lista.size() - 1));

        // Retorna o id do array convertido para int
        return Integer.parseInt(arrayUltimaLinha[0]);
    }

    public String ObterLinhaPorId(int id){
        /*
        * Retornar o valor de uma linha do arquivo csv com base no id.
        * Se o id existe retorna uma string (a linha em si), caso não seja encontrado retorna null
        * */
        //Tdas as linhas do arquivo
        List<String> lista = this.LerLinhasArquivo();

        for(int i = 1; i < lista.size(); i++){
            // Converte uma linha em um array e atribui a uma variável
            String[] arrayLinha = ConverteStringParaArray(lista.get(i));

            // Verifica se o id da linha é igual ao id informado
            int idLinha = Integer.parseInt(arrayLinha[0]);

            if(idLinha == id){
                idLinha = 0;
                return lista.get(i);
            }

        }
        return null;
    }

    public int ObterNumeroLinhaPorId(int id){
         /*
            Retorna o número da linha que tem o id informado no arquivo
            Parâmetro:
                id - string numérico
        */

        List<String> linhasLista = LerLinhasArquivo();

        String linhaDesejada = ObterLinhaPorId(id);

        for(int i = 1; i < linhasLista.size(); i++)
        {
            if(linhasLista.get(i).equals(linhaDesejada))
            {
                return i + 1;

            }
        }

        return -1;
    }

    public int ObterIdPassagemPeloId(int id){
        String[] linhaArray = ConverteStringParaArray(ObterLinhaPorId(id));

        return Integer.parseInt(linhaArray[3]);
    }

    // Conversões
    public String[] ConverteStringParaArray(String str) {
        /*
            Converte uma string em um array.
            usada para transformar uma linha do arquivo em um array.
            Parâmetro:
                str - string com o ";" para ser separado no array
        */
        String[] array = str.split(",");
        return array;
    }

    public String ConverteArrayParaString(String[] array){

        return String.join(",", array);
    }
}
