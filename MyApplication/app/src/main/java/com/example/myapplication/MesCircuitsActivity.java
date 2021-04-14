package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MesCircuitsActivity extends AppCompatActivity {

    Cursor cur;
    SQLiteDatabase db;
    LinearLayout layNaviguer, layRecherche;
    EditText _txtVilleDepart, _txtVilleArivee,_txtPrix,_txtSelonPrix,_txtDuree,_txtRechercheCircuit,_txtRechercheCircuitSelonPrix;
    ImageButton _btnRecherche,_btnRechercheSelonPrix;
    Button _btnPrevious,_btnNext;
    Button _btnAdd,_btnUpdate,_btnDelete;
    Button _btnCancel,_btnSave;
    int op = 0;
    String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_circuits);

        layNaviguer = (LinearLayout) findViewById(R.id.layNaviguer);
        layRecherche= (LinearLayout) findViewById(R.id.layRecherche);

        _txtRechercheCircuit = (EditText) findViewById(R.id._txtRechercheCircuit);
      //  _txtRechercheCircuitSelonPrix = (EditText) findViewById(R.id._txtRechercheCircuitSelonPrix);
        _txtVilleDepart = (EditText) findViewById(R.id.txtVilleDepart);
        _txtVilleArivee = (EditText) findViewById(R.id.txtVilleArivee);
        _txtPrix = (EditText) findViewById(R.id.txtPrix);
        _txtSelonPrix = (EditText) findViewById(R.id.txtSelonPrix);
        _txtDuree = (EditText) findViewById(R.id.txtDuree);

        _btnAdd = (Button) findViewById(R.id.btnAdd);
        _btnUpdate = (Button) findViewById(R.id.btnUpdate);
        _btnDelete = (Button) findViewById(R.id.btnDelete);

        _btnPrevious = (Button) findViewById(R.id.btnPrevious);
        _btnNext = (Button) findViewById(R.id.btnNext);

        _btnCancel = (Button) findViewById(R.id.btnCancel);
        _btnSave = (Button) findViewById(R.id.btnSave);

        _btnRecherche = (ImageButton) findViewById(R.id.btnRecherche);
        _btnRechercheSelonPrix = (ImageButton) findViewById(R.id._btnRechercheSelonPrix);

        // ouverture d'une connexion vers la base de données
        db = openOrCreateDatabase("Circuits",MODE_PRIVATE,null);
        // Création de la table comptes
        db.execSQL("CREATE TABLE IF NOT EXISTS BDCIRCUITS (id integer primary key autoincrement, VilleDepart VARCHAR, VilleArivee VARCHAR, Prix REAL, Duree INTEGER);");

        layNaviguer.setVisibility(View.INVISIBLE);
        _btnSave.setVisibility(View.INVISIBLE);
        _btnCancel.setVisibility(View.INVISIBLE);

        _btnRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cur = db.rawQuery("select * from bdcircuits where VilleDepart like ?", new String[]{"%" + _txtRechercheCircuit.getText().toString() + "%"});
                try {
                    cur.moveToFirst();
                    _txtVilleDepart.setText(cur.getString(1));
                    _txtVilleArivee.setText(cur.getString(2));
                    _txtPrix.setText(cur.getString(3));
                    _txtSelonPrix.setText(cur.getString(3));
                    _txtDuree.setText(cur.getString(4));
                    if (cur.getCount() == 1){
                        layNaviguer.setVisibility(View.INVISIBLE);
                    } else {
                        layNaviguer.setVisibility(View.VISIBLE);
                        _btnPrevious.setEnabled(false);
                        _btnNext.setEnabled(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"aucun résultat.",Toast.LENGTH_SHORT).show();
                    _txtVilleDepart.setText("");
                    _txtVilleArivee.setText("");
                    _txtPrix.setText("");
                    _txtSelonPrix.setText("");
                    _txtDuree.setText("");
                    layNaviguer.setVisibility(View.INVISIBLE);
                }
            }
        });

        _btnRechercheSelonPrix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cur = db.rawQuery("select * from bdcircuits where Prix between 0 and 200", new String[]{"%" + _txtRechercheCircuitSelonPrix.getText().toString() + "%"});
                cur = db.rawQuery("select * from bdcircuits where Prix between 201 and 400", new String[]{"%" + _txtRechercheCircuitSelonPrix.getText().toString() + "%"});
                cur = db.rawQuery("select * from bdcircuits where Prix between 401 and 600", new String[]{"%" + _txtRechercheCircuitSelonPrix.getText().toString() + "%"});
                cur = db.rawQuery("select * from bdcircuits where Prix between 601 and 800", new String[]{"%" + _txtRechercheCircuitSelonPrix.getText().toString() + "%"});
                cur = db.rawQuery("select * from bdcircuits where Prix between 801 and 1000", new String[]{"%" + _txtRechercheCircuitSelonPrix.getText().toString() + "%"});
                try {
                    cur.moveToFirst();
                    _txtVilleDepart.setText(cur.getString(1));
                    _txtVilleArivee.setText(cur.getString(2));
                    _txtPrix.setText(cur.getString(3));
                    _txtSelonPrix.setText(cur.getString(3));
                    _txtDuree.setText(cur.getString(4));
                    if (cur.getCount() == 1){
                        layNaviguer.setVisibility(View.INVISIBLE);
                    } else {
                        layNaviguer.setVisibility(View.VISIBLE);
                        _btnPrevious.setEnabled(false);
                        _btnNext.setEnabled(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"aucun résultat.",Toast.LENGTH_SHORT).show();
                    _txtVilleDepart.setText("");
                    _txtVilleArivee.setText("");
                    _txtPrix.setText("");
                    _txtSelonPrix.setText("");
                    _txtDuree.setText("");
                    layNaviguer.setVisibility(View.INVISIBLE);
                }
            }
        });


        _btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cur.moveToNext();
                    _txtVilleDepart.setText(cur.getString(1));
                    _txtVilleArivee.setText(cur.getString(2));
                    _txtPrix.setText(cur.getString(3));
                    _txtSelonPrix.setText(cur.getString(3));
                    _txtDuree.setText(cur.getString(4));
                    _btnPrevious.setEnabled(true);
                    if (cur.isLast()){
                        _btnNext.setEnabled(false);
                    }


                } catch (Exception e) {
                    e.printStackTrace();


                }
            }
        });

        _btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cur.moveToPrevious();
                    _txtVilleDepart.setText(cur.getString(1));
                    _txtVilleArivee.setText(cur.getString(2));
                    _txtPrix.setText(cur.getString(3));
                    _txtSelonPrix.setText(cur.getString(3));
                    _txtDuree.setText(cur.getString(4));
                    _btnNext.setEnabled(true);
                    if (cur.isFirst()){
                        _btnPrevious.setEnabled(false);
                    }


                } catch (Exception e) {
                    e.printStackTrace();


                }
            }
        });

        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 1;
                _txtVilleDepart.setText("");
                _txtVilleArivee.setText("");
                _txtPrix.setText("");
                _txtSelonPrix.setText("");
                _txtDuree.setText("");
                _btnSave.setVisibility(View.VISIBLE);
                _btnCancel.setVisibility(View.VISIBLE);
                _btnUpdate.setVisibility(View.INVISIBLE);
                _btnDelete.setVisibility(View.INVISIBLE);
                _btnAdd.setEnabled(false);
                layNaviguer.setVisibility(View.INVISIBLE);
                layRecherche.setVisibility(View.INVISIBLE);
            }
        });

        _btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // tester si les champs ne sont pas vides
                try {
                    x = cur.getString(0);
                    op = 2;

                    _btnSave.setVisibility(View.VISIBLE);
                    _btnCancel.setVisibility(View.VISIBLE);

                    _btnDelete.setVisibility(View.INVISIBLE);
                    _btnUpdate.setEnabled(false);
                    _btnAdd.setVisibility(View.INVISIBLE);

                    layNaviguer.setVisibility(View.INVISIBLE);
                    layRecherche.setVisibility(View.INVISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Sélectionnez un compte puis appyuer sur le bouton de modification",Toast.LENGTH_SHORT).show();
                }

            }
        });

        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (op == 1){
                    // insertion
                    db.execSQL("insert into bdcircuits (VilleDepart,VilleArivee,Prix,Duree) values (?,?,?,?);", new String[] {_txtVilleDepart.getText().toString(), _txtVilleArivee.getText().toString(),_txtPrix.getText().toString(),_txtSelonPrix.getText().toString(),_txtDuree.getText().toString()});
                } else if (op == 2) {
                    // Mise à jour
                    db.execSQL("update bdcircuits set VilleDepart=?, VilleArivee=?, Prix=?, Duree=? where id=?;", new String[] {_txtVilleDepart.getText().toString(), _txtVilleArivee.getText().toString(),_txtPrix.getText().toString(),_txtSelonPrix.getText().toString(),_txtDuree.getText().toString(),x});
                }

                _btnSave.setVisibility(View.INVISIBLE);
                _btnCancel.setVisibility(View.INVISIBLE);
                _btnUpdate.setVisibility(View.VISIBLE);
                _btnDelete.setVisibility(View.VISIBLE);

                _btnAdd.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(true);
                _btnUpdate.setEnabled(true);
                _btnRecherche.performClick();
                _btnRechercheSelonPrix.performClick();
                layRecherche.setVisibility(View.VISIBLE);
            }
        });
        _btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 0;

                _btnSave.setVisibility(View.INVISIBLE);
                _btnCancel.setVisibility(View.INVISIBLE);
                _btnUpdate.setVisibility(View.VISIBLE);
                _btnDelete.setVisibility(View.VISIBLE);

                _btnAdd.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(true);
                _btnUpdate.setEnabled(true);

                layRecherche.setVisibility(View.VISIBLE);
            }
        });


        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    x=  cur.getString(0);
                    AlertDialog dial = MesOptions();
                    dial.show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Sélectionner un circuit puis appyuer sur le bouton de suppresssion",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });



    }

    private AlertDialog MesOptions(){
        AlertDialog MiDia = new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Est ce que vous voulez supprimer ce circuit?")
                .setIcon(R.drawable.validate)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.execSQL("delete from bdcircuits where id=?;",new String[] {cur.getString(0)});
                        _txtVilleDepart.setText("");
                        _txtVilleArivee.setText("");
                        _txtPrix.setText("");
                        _txtSelonPrix.setText("");
                        _txtDuree.setText("");
                        layNaviguer.setVisibility(View.INVISIBLE);
                        cur.close();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        return MiDia;
    }
}