package com.fixtime.fixtimejavafx.persistence;

import com.fixtime.fixtimejavafx.model.Cliente;

import java.io.*;
import java.util.ArrayList;

public class ClienteDAO{
    private static final String FILE_NAME = System.getProperty("user.dir") + "/data/clientes.dat";

    public static void salvar(ArrayList<Cliente> clientes) {
        File pasta = new File(FILE_NAME).getParentFile(); //declaração de uma variável (pasta do tipo File)
        if (!pasta.exists()) pasta.mkdirs(); // verifica caso a pasta data não exista

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        // FileOutputStream: Abre o arquivo no disco para escrita de bytes
        // ObjectOutputStream: Converte objetos Java em bytes (serializa) e os envia para o FileOutputStream.

        oos.writeObject(clientes); // transfomra o obejto (como o ArrayList) em uma sequência de bytes.
        oos.close(); // fecha o fluxo de saída

    }

    public static ArrayList<Cliente> carregar() throws IOException, ClassNotFoundException {
        // lê os bytes do arquivo e então transforma em objetos Cliente
        File file = new File(FILE_NAME); // cria um objeto file
        if (!file.exists()) return new ArrayList<>(); //verifica se o arquivo de dados existe

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME)); //transforma os bytes de volta em objetos (ObjectInputStream)
        ArrayList<Cliente> lista = (ArrayList<Cliente>) ois.readObject(); // pega oque foi enviado com writeObject e remonta em Objeto
        ois.close();
        return lista;
    }



}