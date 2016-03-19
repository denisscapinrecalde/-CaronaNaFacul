package caronanafacul.com.br.caronanafacul;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private static final String PACKAGE_NAME = "caronanafacul.com.br.caronanafacul";

    private CallbackManager callbackManager;
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        initComponents();
        setEventsAndConfigurations();
        logPackageHashKey(PACKAGE_NAME);
    }

    private void initComponents() {
        loginButton = (LoginButton) findViewById(R.id.loginButton);
        callbackManager = CallbackManager.Factory.create();
    }

    private void setEventsAndConfigurations(){
        loginButton.setReadPermissions("public_profile", "email", "user_friends");
        loginButton.registerCallback(callbackManager, facebookLoginCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Método de callback de login do facebook.
     * Em caso de sucesso, cacelamento ou erro os respectivos códigos serão executados
     */
    private FacebookCallback<LoginResult> facebookLoginCallback = new FacebookCallback<LoginResult>() {

        /**
         * Método que será executado em caso de sucesso ao fazer login com o facebook
         * @param loginResult
         */
        @Override
        public void onSuccess(LoginResult loginResult) {
            GraphRequest request = GraphRequest.newMeRequest( loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {

                            Log.e("response: ", response + "");
                            try {
//                                user = new User();
//                                user.facebookID = object.getString("id").toString();
//                                user.email = object.getString("email").toString();
//                                user.name = object.getString("name").toString();
//                                user.gender = object.getString("gender").toString();
//                                PrefUtils.setCurrentUser(user,LoginActivity.this);

                                Toast.makeText(MainActivity.this, "welcome " + object.getString("email").toString(), Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                //TODO tratar isso direito
                                e.printStackTrace();
                            }
                        }
                    });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }

        /**
         * Método que será executado em caso de cancelamento ao fazer login com o facebook
         */
        @Override
        public void onCancel() {

        }

        /**
         * Método que será executado em caso de falha ao fazer login com o facebook
         * @param e
         */
        @Override
        public void onError(FacebookException e) {

        }
    };

    /**
     * Metódo responsável por logar o hash key da aplicação, essa hash será utilizada para fazer login no facebook(não é necessário logar essa informação toda hora)
     *
     * @param packageName nome do pacote principal da classe, pode ser obtido no AndroidManifest
     */
    private void logPackageHashKey(String packageName) {
        try {

            PackageInfo info = getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e) {
            //TODO tratar isso direito

        } catch (NoSuchAlgorithmException e) {
            //TODO tratar isso direito
        }
    }
}