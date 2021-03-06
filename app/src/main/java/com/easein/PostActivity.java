package com.easein;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;

/**
 * Activity which displays a login screen to the user, offering registration as well.
 */
public class PostActivity extends Activity {
  // UI references.
  private EditText postEditText;
  private TextView characterCountTextView;
  private Button postButton;
  private Button cancelarButton;

  private int maxCharacterCount = Application.getConfigHelper().getPostMaxCharacterCount();
  private ParseGeoPoint geoPoint;
  private ListView listaEspacios;
  private int posEspacioSeleccionado;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_post);

    Intent intent = getIntent();
    Location location = intent.getParcelableExtra(Application.INTENT_EXTRA_LOCATION);
    geoPoint = new ParseGeoPoint(location.getLatitude(), location.getLongitude());

    postEditText = (EditText) findViewById(R.id.post_edittext);
    postEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
      }

      @Override
      public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
      }

      @Override
      public void afterTextChanged(Editable s) {
        updatePostButtonState();
        updateCharacterCountTextViewText();
      }
    });

    characterCountTextView = (TextView) findViewById(R.id.character_count_textview);

    cancelarButton = (Button) findViewById(R.id.btn_cancelar);
    postButton = (Button) findViewById(R.id.post_button);
    postButton.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        post();
      }
    });

    cancelarButton.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        btnCancelar();
      }
    });

    updatePostButtonState();
    updateCharacterCountTextViewText();

    listaEspacios = (ListView) findViewById(R.id.listView);

    ArrayList<EspacioAccesible> items = obtenerItems();

    EspacioAccesibleAdapter adapter = new EspacioAccesibleAdapter(this, items);

    listaEspacios.setAdapter(adapter);

    listaEspacios.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        posEspacioSeleccionado = position;
        postEditText.setText("");
        postEditText.setText(nombreEspacio(posEspacioSeleccionado));
        post();
      }
    });
  }

  private String nombreEspacio(int posicion) {
    String nombre = "";
    ArrayList<EspacioAccesible> array = obtenerItems();

    for (int i = 0; i < array.size(); i++) {
      if (posicion == 0)
        nombre = "Ascensor";
      else if (posicion == 1)
        nombre = "Baño";
      else if (posicion == 2)
        nombre = "Parqueadero";
      else
        nombre = "Rampa";
    }

    return nombre;
  }

  private ArrayList<EspacioAccesible> obtenerItems() {
    ArrayList<EspacioAccesible> items = new ArrayList<EspacioAccesible>();

    items.add(new EspacioAccesible(1, getResources().getString(R.string.espacio_acensor),
            getResources().getString(R.string.espacio_acensor_desc), "mipmap/tagmapa_ascensor"));
    items.add(new EspacioAccesible(2, getResources().getString(R.string.espacio_banio),
            getResources().getString(R.string.espacio_banio_desc), "mipmap/tagmapa_banos"));
    items.add(new EspacioAccesible(3, getResources().getString(R.string.espacio_parqueadero),
            getResources().getString(R.string.espacio_parqueadero_desc), "mipmap/tagmapa_parqueadero"));
    items.add(new EspacioAccesible(4, getResources().getString(R.string.espacio_rampa),
            getResources().getString(R.string.espacio_rampa_desc), "mipmap/tagmapa_rampa"));

    return items;
  }

  private void btnCancelar() {
    finish();
  }

  private void post () {
    String text = postEditText.getText().toString().trim();

    // Set up a progress dialog
    final ProgressDialog dialog = new ProgressDialog(PostActivity.this);
    dialog.setMessage(getString(R.string.progress_post));
    dialog.show();

    // Create a post.
    Post post = new Post();

    // Set the location to the current user's location
    post.setLocation(geoPoint);
    post.setText(text);
    post.setTipoEspacio(text);
    post.setUser(ParseUser.getCurrentUser());
    ParseACL acl = new ParseACL();

    // Give public read access
    acl.setPublicReadAccess(true);
    post.setACL(acl);

    // Save the post
    post.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        dialog.dismiss();
        finish();
      }
    });
  }

  private String getPostEditTextText () {
    return postEditText.getText().toString().trim();
  }

  private void updatePostButtonState () {
    int length = getPostEditTextText().length();
    boolean enabled = length > 0 && length < maxCharacterCount;
    postButton.setEnabled(enabled);
  }

  private void updateCharacterCountTextViewText () {
    String characterCountString = String.format("%d/%d", postEditText.length(), maxCharacterCount);
    characterCountTextView.setText(characterCountString);
  }

  //TODO
}
