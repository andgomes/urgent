package br.ufc.dspm.urgent.unidades;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.ufc.dspm.urgent.R;

/**
 * Created by Gustavo on 29/06/2016.
 */
public class Util {

    public static ArrayList<PostoDeSaude> getEnderecoPostosList(Context context) {

        ArrayList<PostoDeSaude> enderecoList = new ArrayList<>();

        InputStream is = context.getResources().openRawResource(R.raw.record);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {

            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;

            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }

            is.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = writer.toString();

        try {

            JSONArray array = new JSONArray(jsonString);

            for (int i=0; i<array.length(); i++) {

                PostoDeSaude posto = new PostoDeSaude(0.0, 0.0);
                JSONObject obj = array.getJSONObject(i);

                if (obj.has("endereco")) {

                    obj.getString("endereco");
                    posto.setEndereco(obj.getString("endereco"));

                }

                if (obj.has("nome"))
                    posto.setNome(obj.getString("nome"));

                if(obj.has("telefone"))
                    posto.setTelefone(obj.getString("telefone"));

                enderecoList.add(posto);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return enderecoList;

    }

    public static ArrayList<PostoDeSaude> setCoordinatesByAddress(ArrayList<PostoDeSaude> list,
                                                                  Context context) {

        ArrayList<PostoDeSaude> newList = new ArrayList<>();

        Geocoder geocoder = new Geocoder(context);
        List<Address> enderecos;

        try {

            for (int i=0; i<list.size(); i++) {

                enderecos = geocoder.getFromLocationName(list.get(i).getEndereco(), 1);

                if (enderecos.size() > 0) {

                    PostoDeSaude posto = new PostoDeSaude(enderecos.get(0).getLatitude(),
                            enderecos.get(0).getLongitude());
                    posto.setEndereco(list.get(i).getEndereco());
                    posto.setNome(list.get(i).getNome());
                    posto.setTelefone(list.get(i).getTelefone());
                    newList.add(posto);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return newList;

    }

    public static ArrayList<String> getUpasAdress() {

        ArrayList<String> list = new ArrayList<>();

        //upas da prefeitura
        list.add("Avenida Presidente Castelo Branco s/n - Cristo Redentor");
        list.add("Avenida G, 9 – Vila Velha");
        list.add("Rua Betel s/n - Serrinha");
        list.add("Rua Sargento João Pinheiro com a Rua João Gentil – Bom Jardim");
        list.add("Avenida Castelo de Castro s/n – Jangurussu");

        //upas do governo
        list.add("Avenida Costa e Silva, s/n, fortaleza");
        list.add("Rua Júlio Silva, 440, fortaleza");
        list.add("Rua Cardeal Arcoverde, s/n, fortaleza");
        list.add("Rua Miguel Gurgel, s/n, fortaleza");
        list.add("Rua 15, fortaleza");

        return list;

    }

    public static ArrayList<UPA> getUpasList(Context context) {

        ArrayList<UPA> newList = new ArrayList<>();
        ArrayList<String> list = getUpasAdress();

        Geocoder geocoder = new Geocoder(context);
        List<Address> enderecos;

        try {

            for(int i=0; i<list.size(); i++) {

                enderecos = geocoder.getFromLocationName(list.get(i), 1);

                if (enderecos.size() > 0) {

                    UPA upa = new UPA(enderecos.get(0).getLatitude(),
                            enderecos.get(0).getLongitude());
                    newList.add(upa);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return newList;

    }

    public static ArrayList<String> getHospitaisAdress() {

        ArrayList<String> list = new ArrayList<>();

        //gonzaguinhas
        list.add("Av. Dom Aloísio Lorscheider, 1130 - Barra do Ceará");
        list.add("Av. D, 440 - José Walter");
        list.add("Av. Washington Soares, 7700 - Messejana");
        //frotinhas
        list.add("Rua Cândido Maia, 294 - Antônio Bezerra");
        list.add("Av. General Osório de Paiva, 1127 - Parangaba");
        list.add("Av. Presidente Costa e Silva, 1578 - Messejana");
        //dra zilda arns neumann
        list.add("Av. Lineu Machado, 155 - Jóquei Clube");
        //ijf
        list.add("Rua Barão do Rio Branco, 1816 - Centro");
        //nossa senhora de conceição
        list.add("Rua 1018, 148 - Conjunto Ceará");
        //centro de atendimento a criança
        list.add("Rua Guilherme Perdigão, 299 - Parangaba");

        return list;

    }

    public static ArrayList<Hospital> getHospitalList(Context context) {

        ArrayList<Hospital> newList = new ArrayList<>();
        ArrayList<String> list = getHospitaisAdress();

        Geocoder geocoder = new Geocoder(context);
        List<Address> enderecos;

        try {

            for(int i=0; i<list.size(); i++) {

                enderecos = geocoder.getFromLocationName(list.get(i), 1);

                if (enderecos.size() > 0) {

                    Hospital hospital = new Hospital(enderecos.get(0).getLatitude(),
                            enderecos.get(0).getLongitude());
                    newList.add(hospital);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return newList;

    }

    public static ArrayList<PostoDeSaude> sortList(ArrayList<PostoDeSaude> lista) {

        ArrayList<PostoDeSaude> orderedUnidadeSaudes = lista;

        Collections.sort(orderedUnidadeSaudes, new Comparator<UnidadeSaude>() {
            @Override
            public int compare(UnidadeSaude ob1, UnidadeSaude ob2) {
                return ob1.getDistance() < ob2.getDistance() ? -1 : (ob1.getDistance() > ob2.getDistance() ? +1 :0);
            }
        });

        return orderedUnidadeSaudes;

    }

}
