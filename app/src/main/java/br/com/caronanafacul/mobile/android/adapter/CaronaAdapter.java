package br.com.caronanafacul.mobile.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.caronanafacul.mobile.android.R;
import br.com.caronanafacul.mobile.android.model.Carona;

/**
 * Created by bruno on 13/04/16.
 */
public class CaronaAdapter extends BaseAdapter {

    private List<Carona> data;
    private Context context;

    public CaronaAdapter(List<Carona> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.carona_item, null);
        }

        Carona carona = data.get(position);

        if(carona != null){
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            //TODO fazer cache ViewHolder
            TextView tvPartida = (TextView) convertView.findViewById(R.id.ltPartida);
            TextView tvHorario = (TextView) convertView.findViewById(R.id.ltHorario);

            tvPartida.setText(String.valueOf(carona.getPontoPartida()));
            tvHorario.setText(sd.format(carona.getHorario()));
        }
        return convertView;
    }
}
