package br.com.caronanafacul.mobile.android.activity;

/**
 * Created by Rodrigo on 30/03/2016.
 */
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import br.com.caronanafacul.mobile.android.adapter.CaronaAdapter;
import br.com.caronanafacul.mobile.android.R;
import br.com.caronanafacul.mobile.android.repository.CaronaRepositoryRest;
import br.com.caronanafacul.mobile.android.repository.UsuarioRepositoryRest;
import br.com.caronanafacul.mobile.android.model.Carona;
import br.com.caronanafacul.mobile.android.model.Usuario;


public class HomeActivity extends AppCompatActivity {

    private class GetCaronasTask extends AsyncTask<Usuario, Integer, List<Carona>> {

        private GetCaronasTask() {
        }

        @Override
        protected List<Carona> doInBackground(Usuario... params) {
            CaronaRepositoryRest placeRepository = new CaronaRepositoryRest();
            return placeRepository.getCaronasByUserId(params[0].getId());
            //TODO fazer tratamento para falta de internet
        }

        @Override
        protected void onPostExecute(List<Carona> caronas) {
            lvHomeContent.setAdapter(new CaronaAdapter(caronas, HomeActivity.this));
        }
    }

    private class GetUsuarioTask extends AsyncTask<String, Integer, Usuario> {

        private GetUsuarioTask() {
        }

        @Override
        protected Usuario doInBackground(String... strings) {
            Usuario usuario = new UsuarioRepositoryRest().getUserByEmail(strings[0]);
                getIntent().putExtra(getResources().getString(R.string.extra_key_usuario), usuario);
                Log.e("Usuario", usuario.toString());
            return usuario;
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            getIntent().putExtra(getResources().getString(R.string.extra_key_usuario), usuario);
            loadCaronas(usuario);
        }
    }

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private ListView lvHomeContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        initComponents();
        setEventsAndConfigurations();
    }

    private void initComponents() {
        lvHomeContent = (ListView) findViewById(R.id.home_content);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
    }

    private void setEventsAndConfigurations(){
        toolbar.setTitle(R.string.title_home_activity);
        setSupportActionBar(toolbar);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle = setupDrawerToggle();
        setupDrawerContent(nvDrawer);

        //TODO pegar esse email da activity main(facebook)
        loadUsuario("usuario2@test");
    }

    public void loadCaronas(Usuario usuario){
        new GetCaronasTask().execute(usuario);
    }

    public void loadUsuario(String email){
        new GetUsuarioTask().execute(email);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        Fragment fragment = null;

        Class fragmentClass ;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                //fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_second_fragment:
                //fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                //fragmentClass = ThirdFragment.class;
                break;
            default:
                //fragmentClass = FirstFragment.class;
        }

        try {
            fragment = null;//(Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        // Highlight the selected item has been done by NavigationView
        // menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String extraKeyUsuario = getResources().getString(R.string.extra_key_usuario);

        if(item.getItemId() == R.id.action_new){
            Intent intent = new Intent(HomeActivity.this, CaronaFormActivity.class);
            Log.e("Colocando objeto usuar",getIntent().getSerializableExtra(extraKeyUsuario).toString());
            intent.putExtra(extraKeyUsuario, getIntent().getSerializableExtra(extraKeyUsuario));
            startActivity(intent);
        }

        // The action bar home/up action should open or close the drawer.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_caronas, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
