package com.praksa.ucenik.jatolog.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.praksa.ucenik.jatolog.R;
import com.praksa.ucenik.jatolog.db.IjekavicaPrimjerDBHelper;
import com.praksa.ucenik.jatolog.model.Ekavica;
import com.praksa.ucenik.jatolog.model.Ijekavica;
import com.praksa.ucenik.jatolog.model.IjekavicaPrimjer;
import com.praksa.ucenik.jatolog.model.PrijavaGreske;
import com.praksa.ucenik.jatolog.thread.PrijavaGreskeThread;
import com.praksa.ucenik.jatolog.util.Util;

import java.util.ArrayList;

import static com.praksa.ucenik.jatolog.util.Util.getCurrentLocale;

/**
 * Created by Ucenik on 04.04.2018..
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Ijekavica> list = new ArrayList<>();

    public RecyclerViewAdapter(Context context, ArrayList<Ijekavica> list) {
        this.context = context;
        this.list = list;
    }

    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txtIjekavica;
        private TextView txtOpis;
        private TextView txtPrimjer;
        private TextView txtIzvor;
        private LinearLayout colapsableInfo;
        private ImageView btnPrijaviGresku;
        private Ekavica ekavica;
        private int ijekavicaId;

        public MyViewHolder(View itemView) {

            super(itemView);
            txtIjekavica=itemView.findViewById(R.id.txtIjekavica);
            txtOpis=itemView.findViewById(R.id.txtOpis);
            txtPrimjer=itemView.findViewById(R.id.txtPrimjer);
            txtIzvor=itemView.findViewById(R.id.txtIzvor);
            colapsableInfo=itemView.findViewById(R.id.colapsableInfo);
            btnPrijaviGresku = itemView.findViewById(R.id.btnPrijaviGresku);
            colapsableInfo.setVisibility(View.GONE);

            txtIjekavica.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if (colapsableInfo.isShown()){
                        colapsableInfo.setVisibility(View.GONE);
                        txtIjekavica.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_expand_more_black_24px,0);
                    }else{
                        colapsableInfo.setVisibility(View.VISIBLE);
                        txtIjekavica.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_expand_less_black_24px,0);
                    }
                }

            });

            btnPrijaviGresku.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) {

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
                        alertDialog.setView(dialogView);

                        alertDialog.setTitle(R.string.prijava_greske);
                        alertDialog.setMessage("");

                        TextView txtIjekavicaDialog = (TextView) dialogView.findViewById(R.id.txtIjekavica);
                        txtIjekavicaDialog.setText(txtIjekavica.getText());

                        TextView txtEkavicaDialog = (TextView) dialogView.findViewById(R.id.txtEkavica);
                        if (Util.getCurrentLocale(context).getLanguage().equals("sr")) {
                            txtEkavicaDialog.setText(ekavica.getRijecCirilica());
                        }
                        else {
                            txtEkavicaDialog.setText(ekavica.getRijecLatinica());
                        }

                        alertDialog.setPositiveButton(R.string.prijavi, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                EditText editTextNapomena = view.findViewById(R.id.editTextNapomena);
                                EditText editTextEmail = view.findViewById(R.id.editTextEmail);

                                PrijavaGreske prijavaGreske = new PrijavaGreske();
                                prijavaGreske.setEkavicaId(ekavica.getEkavicaId());
                                prijavaGreske.setIjekavicaId(ijekavicaId);
                                prijavaGreske.setNapomena(editTextNapomena.getText().toString());
                                prijavaGreske.setEmail(editTextEmail.getText().toString());

                                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) {
                                    PrijavaGreskeThread prijavaGreskeThread = new PrijavaGreskeThread(context, prijavaGreske);
                                    prijavaGreskeThread.start();
                                    try {
                                        prijavaGreskeThread.join();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                else {
                                    Toast.makeText(view.getContext(), view.getContext().getString(R.string.nemaInternetKonekcije), Toast.LENGTH_SHORT).show();
                                }
                                dialogInterface.dismiss();
                            }
                        });

                        alertDialog.setNegativeButton(R.string.odustani, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        alertDialog.show();
                    }
                    else {
                        Toast.makeText(view.getContext(), R.string.nemaInternetKonekcije, Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){

            MyViewHolder myViewHolder= (MyViewHolder)holder;

            IjekavicaPrimjerDBHelper ijekavicaPrimjerDBHelper = new IjekavicaPrimjerDBHelper(context);
            ArrayList<IjekavicaPrimjer> ijekavicaPrimjeri = new ArrayList<>();
            String primjeri = "";
            for(IjekavicaPrimjer ijp :ijekavicaPrimjeri){
                primjeri += ijp.getSadrzaj() +" \n";
            }
            String izvor = list.get(position).getIzvorTekst();
            izvor = (izvor == null)?"":izvor.trim();


            ijekavicaPrimjeri = ijekavicaPrimjerDBHelper.selectPrimjeriByIjekavica(list.get(position));

            if (getCurrentLocale(context.getApplicationContext()).getLanguage().equals("en")) {
                myViewHolder.txtIjekavica.setText(list.get(position).getIjekavicaLatinica());
                String opis = list.get(position).getOpisLatinica();
                opis = (opis == null)?"":opis.trim();
                if ("".equals(opis)) {
                    myViewHolder.txtOpis.setText(R.string.nema_opisa);
                    myViewHolder.txtOpis.setTypeface(null, Typeface.ITALIC);
                }
                else {
                    myViewHolder.txtOpis.setText(opis);
                }
                if(!("".equals(primjeri))) {
                    myViewHolder.txtPrimjer.setText(primjeri);
                }
                else {
                    myViewHolder.txtPrimjer.setText(R.string.nema_primjera);
                    myViewHolder.txtPrimjer.setTypeface(null, Typeface.ITALIC);
                }
                if ("".equals(izvor)) {
                    myViewHolder.txtIzvor.setText(R.string.nema_izvora);
                    myViewHolder.txtIzvor.setTypeface(null, Typeface.ITALIC);
                }
                else {
                    myViewHolder.txtIzvor.setText(list.get(position).getIzvorTekst());
                }
            }
            else{
                myViewHolder.txtIjekavica.setText(list.get(position).getIjekavicaCirilica());
                String opis = list.get(position).getOpisCirilica();
                opis = (opis == null)?"":opis.trim();
                if ("".equals(opis)) {
                    myViewHolder.txtOpis.setText(R.string.nema_opisa);
                    myViewHolder.txtOpis.setTypeface(null, Typeface.ITALIC);
                }
                else {
                    myViewHolder.txtOpis.setText(opis);
                }
                if(!("".equals(primjeri))) {
                    myViewHolder.txtPrimjer.setText(Util.prevediNaCirilicu(primjeri));
                }
                else {
                    myViewHolder.txtPrimjer.setText(R.string.nema_primjera);
                    myViewHolder.txtPrimjer.setTypeface(null, Typeface.ITALIC);
                }
                if ("".equals(izvor)) {
                    myViewHolder.txtIzvor.setText(R.string.nema_izvora);
                    myViewHolder.txtIzvor.setTypeface(null, Typeface.ITALIC);
                }
                else {
                    myViewHolder.txtIzvor.setText(Util.prevediNaCirilicu(izvor));
                }
            }
            myViewHolder.ijekavicaId = list.get(position).getIjekavicaId();
            myViewHolder.ekavica = list.get(position).getEkavica();

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}