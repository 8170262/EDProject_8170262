import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.toIntExact;

import Mapa.Mapa;
import Mapa.Espaco;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

public class FileManager{

    public Mapa readFile(String path) {
        JSONParser parser = new JSONParser();
        Mapa mapaNull = null;
        try {
            int pontos;
            String nome;
            JSONObject file = (JSONObject) parser.parse(new FileReader(path));
            //JSONObject file = (JSONObject) teste.get("mapa");
            Mapa mapa;

            nome = (String) file.get("nome");
            pontos = toIntExact((Long) (file.get("pontos")));

            JSONArray espacos = (JSONArray) (file.get("mapa"));
            Espaco[] espacoArray = (new Espaco[espacos.size()]);

            int contaEspacos = 0, fantasma;
            String aposento;
            String[] ligacoesArray;
            for (Object a : espacos) {
                if (a instanceof JSONObject) {
                    aposento = (String) ((JSONObject) a).get("aposento");
                    fantasma = toIntExact((Long) ((JSONObject) a).get("fantasma"));

                    JSONArray ligacoes = (JSONArray) ((JSONObject) a).get("ligacoes");
                    ligacoesArray = new String[ligacoes.size()];

                    for (int contaLigacoes=0;contaLigacoes<ligacoes.size();contaLigacoes++) {
                        ligacoesArray[contaLigacoes]=ligacoes.get(contaLigacoes).toString();
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
