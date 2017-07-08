package co.quindio.sena.ejemplosqlite.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.quindio.sena.ejemplosqlite.R;
import co.quindio.sena.ejemplosqlite.entidades.Usuario;

/**
 * Created by CHENAO on 8/07/2017.
 */

public class ListaPersonasAdapter extends RecyclerView.Adapter<ListaPersonasAdapter.PersonasViewHolder> {

    ArrayList<Usuario> listaUsuario;

    public ListaPersonasAdapter(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_personas,null,false);
        return new PersonasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        holder.documento.setText(listaUsuario.get(position).getId().toString());
        holder.nombre.setText(listaUsuario.get(position).getNombre());
        holder.telefono.setText(listaUsuario.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class PersonasViewHolder extends RecyclerView.ViewHolder {

        TextView documento,nombre,telefono;

        public PersonasViewHolder(View itemView) {
            super(itemView);
            documento = (TextView) itemView.findViewById(R.id.textDocumento);
            nombre = (TextView) itemView.findViewById(R.id.textNombre);
            telefono = (TextView) itemView.findViewById(R.id.textTelefono);
        }
    }
}
