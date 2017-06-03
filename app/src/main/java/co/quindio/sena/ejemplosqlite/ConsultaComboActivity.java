package co.quindio.sena.ejemplosqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.quindio.sena.ejemplosqlite.entidades.Usuario;
import co.quindio.sena.ejemplosqlite.utilidades.Utilidades;

public class ConsultaComboActivity extends AppCompatActivity {

    Spinner comboPersonas;
    TextView txtNombre,txtDocumento,txtTelefono;
    ArrayList<String> listaPersonas;

    ArrayList<Usuario> personasList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_combo);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        comboPersonas= (Spinner) findViewById(R.id.comboPersonas);

        txtDocumento= (TextView) findViewById(R.id.txtDocumento);
        txtNombre= (TextView) findViewById(R.id.txtNombre);
        txtTelefono= (TextView) findViewById(R.id.txtTelefono);

        consultarListaPersona();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);

        comboPersonas.setAdapter(adaptador);


        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(adapterView.getContext(),"Selecciona: "+adapterView.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
               // Toast.makeText(adapterView.getContext(),"pos: "+position,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void consultarListaPersona() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Usuario persona=null;
        personasList=new ArrayList<Usuario>();

        try {
            //select nombre,telefono from usuario where codigo=?
            Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_USUARIO,null);

            while (cursor.moveToNext()){
                persona=new Usuario();
                persona.setId(cursor.getInt(0));
                persona.setNombre(cursor.getString(1));
                persona.setTelefono(cursor.getString(2));

                Log.i("Doc ",persona.getId().toString() );
                Log.i("Nombre ",persona.getNombre() );
                Log.i("Tel ",persona.getTelefono() );

                personasList.add(persona);
            }

            obtenerListaPersonas();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();

        }

    }

    private void obtenerListaPersonas() {
        listaPersonas=new ArrayList<String>();
        listaPersonas.add("Seleccione");

        for (int i=0; i<personasList.size();i++){
            listaPersonas.add(personasList.get(i).getId()+" - "+personasList.get(i).getNombre());
        }
    }
}
