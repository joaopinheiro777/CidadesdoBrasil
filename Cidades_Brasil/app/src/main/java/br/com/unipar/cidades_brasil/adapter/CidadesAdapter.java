package br.com.unipar.cidades_brasil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import br.com.unipar.cidades_brasil.R;
import br.com.unipar.cidades_brasil.entities.Cidades;

public class CidadesAdapter {

    private List<Cidades> cidades;
    private WeakReference<Context> weakReference;
    private LayoutInflater inflater;

    public CidadesAdapter(List<Cidades> cidades, WeakReference<Context> weakReference) {
        this.cidades = cidades;
        this.weakReference = weakReference;

        this.inflater = LayoutInflater.from(weakReference.get());
    }

    @Override
    public int getCount() {
        return cidades.size();
    }

    @Override
    public Object getItem(int i) {
        return cidades.get(i);
    }

    @Override
    public long getItemId(int i) {
        return cidades.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        Cidades cidades = (Cidades) getItem(i);

        if (view == null) {
            view = inflater.inflate(R.layout.item, null);

            viewHolder = new ViewHolder();

            viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);
            viewHolder.tvId = (TextView) view.findViewById(R.id.tvId);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.tvTitle);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Glide.with(weakReference.get())
                .load(cidades.getMunicipiosUrlUrl())
                .centerCrop()
                .into(viewHolder.imageView);

        viewHolder.tvId.setText(String.valueOf(cidades.getId()));
        viewHolder.tvTitle.setText(cidades.getNome());

        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView tvId;
        TextView tvTitle;
    }
}
