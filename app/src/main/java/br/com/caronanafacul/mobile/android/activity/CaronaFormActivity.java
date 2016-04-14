package br.com.caronanafacul.mobile.android.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.caronanafacul.mobile.android.R;
import br.com.caronanafacul.mobile.android.repository.CaronaRepositoryRest;
import br.com.caronanafacul.mobile.android.model.Carona;
import br.com.caronanafacul.mobile.android.model.Usuario;

public class CaronaFormActivity extends AppCompatActivity {

    private class CaronasTask extends AsyncTask<Carona, Integer, Void> {

        private CaronasTask() {
        }

        @Override
        protected Void doInBackground(Carona... params) {
            CaronaRepositoryRest placeRepository = new CaronaRepositoryRest();
            placeRepository.put(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //TODO colocar aqui uma mensagem para sucesso ou erro
            //TODO voltar para a página inicial
        }
    }

    private Toolbar toolbar;
    private EditText etPontoPartida;
    private EditText etHorario;
    private EditText etTolerancia;
    private EditText etVagas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carona_form);

        initComponents();
        setEventsAndConfigurations();
    }

    private void initComponents() {
        etPontoPartida = (EditText) findViewById(R.id.etPontoPartida);
        etHorario = (EditText) findViewById(R.id.etHorario);
        etTolerancia = (EditText) findViewById(R.id.etTolerancia);
        etVagas = (EditText) findViewById(R.id.etVagas);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setEventsAndConfigurations(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_carona_form_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_criar_carona, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_done){
            Usuario usuario = (Usuario) getIntent().getSerializableExtra(getResources().getString(R.string.extra_key_usuario));

            String pontoPartida = etPontoPartida.getText().toString();
            String horario = etHorario.getText().toString();
            String tolerancia = etTolerancia.getText().toString();
            String vagas = etVagas.getText().toString();

            //TODO arrumar esse horário
            Log.e("Carona PUT", String.valueOf(horario.split(":")));

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            //TODO o horário está chumbado pra teste
            Calendar agora = Calendar.getInstance();
            agora.set(Calendar.HOUR, 10);
            agora.set(Calendar.MINUTE, 10);

            Date horarioCarona = agora.getTime();

            agora.add(Calendar.MINUTE, Integer.parseInt(tolerancia));

            Carona carona = new Carona(pontoPartida, Integer.parseInt(vagas), horarioCarona, agora.getTime(), usuario);

            Log.e("Carona PUT",carona.toString());

            new CaronasTask().execute(carona);
        }
        return super.onOptionsItemSelected(item);
    }
}