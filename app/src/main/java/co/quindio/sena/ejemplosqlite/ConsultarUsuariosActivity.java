package co.quindio.sena.ejemplosqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import co.quindio.sena.ejemplosqlite.utilidades.Utilidades;

public class ConsultarUsuariosActivity extends AppCompatActivity {

    EditText campoId,campoNombre,campoTelefono;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuarios);

        conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        campoId= (EditText) findViewById(R.id.documentoId);
        campoNombre= (EditText) findViewById(R.id.campoNombreConsulta);
        campoTelefono= (EditText) findViewById(R.id.campoTelefonoConsulta);

    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnConsultar:
                    consultar();

                break;
            case R.id.btnActualizar:consultarConSql();
                break;
            case R.id.btnEliminar:
                break;
        }

    }

    private void consultar() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoId.getText().toString()};
        String[] campos={Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};

        try {
            //indicamos la tabla, los campos que esperamos,el campo de la condicion, el parametro a consultar, groupBy, Having, OrderBy
            Cursor cursor=db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);

            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoTelefono.setText(cursor.getString(1));
            cursor.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no Existe!!! ",Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }

    }

    private void consultarConSql() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoId.getText().toString()};

        try {
            //SELECT nombre, telefono FROM usuario WHERE codigo= ?
            Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+
                            " FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.CAMPO_ID+"=? ",parametros);

            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoTelefono.setText(cursor.getString(1));
        }catch (Exception e){
        Toast.makeText(getApplicationContext(),"El documento no Existe!!! ",Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }

    }

    private void limpiarCampos() {
        campoNombre.setText("");
        campoTelefono.setText("");
    }

}
