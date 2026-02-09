package android.avanzado.actividades10;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VersionAdapter extends RecyclerView.Adapter<VersionAdapter.VersionViewHolder> {
    private List<VersionItem> lista;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public VersionAdapter(List<VersionItem> lista, OnItemClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VersionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_version, parent, false);
        return new VersionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionViewHolder holder, int position) {
        VersionItem item = lista.get(position);
        holder.tvNombre.setText(item.nombre);
        holder.tvAnio.setText(item.anio);
        holder.itemView.setBackgroundColor(selectedPosition == position ? Color.parseColor("#90CAF9") : Color.parseColor("#E3EAFD"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prev = selectedPosition;
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(prev);
                notifyItemChanged(selectedPosition);
                if (listener != null) listener.onItemClick(selectedPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void clearSelection() {
        int prev = selectedPosition;
        selectedPosition = RecyclerView.NO_POSITION;
        notifyItemChanged(prev);
    }

    static class VersionViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvAnio;
        VersionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvAnio = itemView.findViewById(R.id.tvAnio);
        }
    }
}
