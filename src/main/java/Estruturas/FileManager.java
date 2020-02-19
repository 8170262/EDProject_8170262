package Estruturas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Math.toIntExact;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

public class FileManager{

    public Mapa readFile(String path) {
        JSONParser parser = new JSONParser();
        Mapa mapaNull=null;
        try {
            Double pontos;
            String nome;
            JSONObject file = (JSONObject) parser.parse(new FileReader(path));
            Mapa mapa;

            nome = (String) file.get("nome");
            pontos = ((Long) file.get("pontos")).doubleValue();

            JSONArray espacos = (JSONArray) (file.get("mapa"));
            Espaco[] espacoArray = (new Espaco[espacos.size()]);

            int contaEspacos = 0, fantasma;
            String aposento;
            ArrayUnorderedList<String> ligacoesArray;
            for (Object a : espacos) {
                if (a instanceof JSONObject) {
                    aposento = (String) ((JSONObject) a).get("aposento");
                    fantasma = toIntExact((Long) ((JSONObject) a).get("fantasma"));

                    JSONArray ligacoes = (JSONArray) ((JSONObject) a).get("ligacoes");
                    ligacoesArray=new ArrayUnorderedList<>(ligacoes.size());

                    for (int contaLigacoes=0;contaLigacoes<ligacoes.size();contaLigacoes++) {
                        ligacoesArray.addToRear(ligacoes.get(contaLigacoes).toString());
                    }

                    espacoArray[contaEspacos] = new Espaco(aposento, fantasma, ligacoesArray);
                    contaEspacos++;
                }
            }
            mapa=new Mapa(nome, pontos, espacoArray);
            return mapa;

        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFound");
        } catch (IOException | ParseException e) {
            System.out.println("Exception");
        }
        return mapaNull;
    }
}
