package br.ufc.dspm.urgent.unidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UnidadeSaudeDAO extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UnidadesSaude.db";
    public static final int DATABASE_VERSION = 4;

    public UnidadeSaudeDAO(Context context) {
        this(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public UnidadeSaudeDAO(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder sql = new StringBuilder();

        sql.append("create table unidades_saude (");
        sql.append("id integer primary key autoincrement,");
        sql.append("latitude double,");
        sql.append("longitude double,");
        sql.append("nome text,");
        sql.append("endereco text,");
        sql.append("telefone text,");
        sql.append("tipo text)");

        db.execSQL(sql.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists unidades_saude");
        onCreate(db);

    }

    public void adicionarUnidadeSaude(UnidadeSaude unidadeSaude) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("latitude", unidadeSaude.getLatitude());
        contentValues.put("longitude", unidadeSaude.getLongitude());
        contentValues.put("nome", unidadeSaude.getNome());
        contentValues.put("endereco", unidadeSaude.getEndereco());
        contentValues.put("telefone", unidadeSaude.getTelefone());
        contentValues.put("tipo", unidadeSaude.getTipo());

        db.insert("unidades_saude", null, contentValues);

    }

    public ArrayList<Hospital> listHospitais() {

        ArrayList<Hospital> hospitaisArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("select * from unidades_saude where tipo = 'hospital'",
                null);

        if (result != null && result.getCount() > 0) {

            result.moveToFirst();

            while (result.isAfterLast() == false) {

                Hospital hospital = new Hospital(result.getDouble(1), result.getDouble(2));
                hospital.setNome(result.getString(3));
                hospital.setEndereco(result.getString(4));
                hospital.setTelefone(result.getString(5));
                hospitaisArrayList.add(hospital);

                result.moveToNext();

            }

        }

        return hospitaisArrayList;

    }

    public ArrayList<PostoDeSaude> listPostosDeSaude() {

        ArrayList<PostoDeSaude> postoDeSaudeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("select * from unidades_saude where tipo = 'posto de saude'",
                null);

        if (result != null && result.getCount() > 0) {

            result.moveToFirst();

            while (result.isAfterLast() == false) {

                PostoDeSaude posto = new PostoDeSaude(result.getDouble(1), result.getDouble(2));
                posto.setNome(result.getString(3));
                posto.setEndereco(result.getString(4));
                posto.setTelefone(result.getString(5));
                postoDeSaudeList.add(posto);

                result.moveToNext();

            }

        }

        return postoDeSaudeList;

    }

    public List<UPA> listUpas() {

        List<UPA> upas = new LinkedList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("select * from unidades_saude where tipo = 'upa'",
                null);

        if (result != null && result.getCount() > 0) {

            result.moveToFirst();

            while (result.isAfterLast() == false) {

                UPA upa = new UPA(result.getDouble(1), result.getDouble(2));
                upas.add(upa);

                result.moveToNext();

            }

        }

        return upas;

    }

}