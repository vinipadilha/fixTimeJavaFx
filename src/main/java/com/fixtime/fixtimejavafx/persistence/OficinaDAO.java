package com.fixtime.fixtimejavafx.persistence; // define o pacote para organizar as classes

import com.fixtime.fixtimejavafx.model.Oficina; // importa a classe Oficina da model
import java.io.*; //  possui classes para entrada e saída de dados, como leitura e escrita de arquivos.
import java.util.ArrayList;

public class OficinaDAO {
    private static final String FILE_NAME = System.getProperty("user.dir") + "/data/oficinas.dat"; // user.dir retorna o caminho da pasta onde o seu projeto está sendo executado no momento
    // com static não precisa criar um objeto OficinaDAO para acessar FILE_NAME
    // o final indica que o valor de FILE_NAME não pode ser alterado (constante)

    public static void salvar(ArrayList<Oficina> oficinas) throws IOException { // metodo recebe uma lista de Oficinas e salva no .dat
        // void não retorna nenhum valor
        // throws IOException -> pode lançar uma exceção de entrada/saída, ou seja quem chama o metodo DEVE tratar usando try-catch
        File pasta = new File(FILE_NAME).getParentFile(); // getParentFile() retorna um novo objeto File representando uma pasta antes (diretório pai)
        if (!pasta.exists()) pasta.mkdirs(); // caso não exista, cria a pasta data

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        // FILE_NAME -> constante com o caminho para o oficinas.dat
        // new FileOutputStream -> objeto sendo criado e usando a constante FILE_name como argumento
        //
        oos.writeObject(oficinas);
        oos.close(); // fecha o fluxo de saída
    }

    public static ArrayList<Oficina> carregar() throws IOException, ClassNotFoundException { //metodo le os arquivos e retorna uma lista de oficinas
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>(); // vai verificar se o arquivo existe

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME)); // abre o arquivo para leitura com
        ArrayList<Oficina> lista = (ArrayList<Oficina>) ois.readObject(); // le e converte para arrayList novamente
        ois.close(); // fecha o arquivo  e retorna a lista
        return lista;
    }
}