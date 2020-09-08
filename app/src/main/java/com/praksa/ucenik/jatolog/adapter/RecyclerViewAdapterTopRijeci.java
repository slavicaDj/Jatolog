package com.praksa.ucenik.jatolog.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.praksa.ucenik.jatolog.R;
import com.praksa.ucenik.jatolog.db.IjekavicaPrimjerDBHelper;
import com.praksa.ucenik.jatolog.model.Ijekavica;
import com.praksa.ucenik.jatolog.model.IjekavicaPrimjer;
import com.praksa.ucenik.jatolog.util.Util;

import java.util.ArrayList;

import static com.praksa.ucenik.jatolog.util.Util.getCurrentLocale;

/**
 * Created by ucenik on 4/11/2018.
 */

public class RecyclerViewAdapterTopRijeci extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Ijekavica> list=new ArrayList<>();
    private int mExpandedPosition=-1;
    private ArrayList<Integer> positionSet=new ArrayList<>();

    public RecyclerViewAdapterTopRijeci(Context context, ArrayList<Ijekavica> list) {
        this.context = context;
        this.list = list;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txtIjekavica;
        private TextView txtEkavica;
        private TextView txtOpis;
        private TextView txtPrimjer;
        private TextView txtIzvor;
        private LinearLayout colapsableInfoNaj;
        private LinearLayout linearNajkoristenije;

        public MyViewHolder(final View itemView) {
            super(itemView);
            txtEkavica= itemView.findViewById(R.id.txtEkavicaNaj);
            txtIjekavica =  itemView.findViewById(R.id.txtIjekavicaNaj);
            txtOpis = itemView.findViewById(R.id.txtOpisNaj);
            txtPrimjer = itemView.findViewById(R.id.txtPrimjerNaj);
            txtIzvor = itemView.findViewById(R.id.txtIzvorNaj);
            colapsableInfoNaj=itemView.findViewById(R.id.colapsableInfoNaj);
            linearNajkoristenije=itemView.findViewById(R.id.linearNajkoristenije);
            colapsableInfoNaj.setVisibility(View.GONE);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_item_most_usable,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            IjekavicaPrimjerDBHelper ijekavicaPrimjerDBHelper=new IjekavicaPrimjerDBHelper(context);
            ArrayList<IjekavicaPrimjer> ijekavicaPrimjeri= new ArrayList<>();
            ijekavicaPrimjeri = ijekavicaPrimjerDBHelper.selectPrimjeriByIjekavica(list.get(position));

            final MyViewHolder myViewHolder = (MyViewHolder) holder;

            if(positionSet.contains(myViewHolder.getAdapterPosition())){
                myViewHolder.colapsableInfoNaj.setVisibility(View.VISIBLE);
                myViewHolder.txtIjekavica.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_expand_less_black_24px,0);

            }else {
                myViewHolder.colapsableInfoNaj.setVisibility(View.GONE);
                myViewHolder.txtIjekavica.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_expand_more_black_24px,0);

            }

          myViewHolder.linearNajkoristenije.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int position = myViewHolder.getAdapterPosition();
                   if(positionSet.contains(position)){
                       positionSet.remove((Integer)position);
                   }else {
                       positionSet.add(position);
                   }
                   notifyDataSetChanged();

               }
           });


            String primjeri = "";
            for(IjekavicaPrimjer ijp :ijekavicaPrimjeri){
                primjeri += ijp.getSadrzaj() +" \n";
            }
            String izvor = list.get(position).getIzvorTekst();
            izvor = (izvor == null)?"":izvor.trim();

            if (getCurrentLocale(context.getApplicationContext()).getLanguage().equals("en")) {
                myViewHolder.txtIjekavica.setText(list.get(position).getIjekavicaLatinica());
                myViewHolder.txtEkavica.setText(list.get(position).getEkavica().getRijecLatinica());
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
                myViewHolder.txtEkavica.setText(list.get(position).getEkavica().getRijecCirilica());
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


        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
