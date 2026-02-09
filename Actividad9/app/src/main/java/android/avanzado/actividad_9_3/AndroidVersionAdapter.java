package android.avanzado.actividad_9_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AndroidVersionAdapter extends RecyclerView.Adapter<AndroidVersionAdapter.VersionViewHolder> {
    private List<AndroidVersion> versionList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(AndroidVersion version);
    }

    public AndroidVersionAdapter(List<AndroidVersion> versionList, OnItemClickListener listener) {
        this.versionList = versionList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VersionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_android_version, parent, false);
        return new VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionViewHolder holder, int position) {
        AndroidVersion version = versionList.get(position);
        holder.bind(version, listener);
    }

    @Override
    public int getItemCount() {
        return versionList.size();
    }

    static class VersionViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewVersion;
        TextView textViewVersionName;

        VersionViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewVersion = itemView.findViewById(R.id.imageViewVersion);
            textViewVersionName = itemView.findViewById(R.id.textViewVersionName);
        }

        void bind(final AndroidVersion version, final OnItemClickListener listener) {
            imageViewVersion.setImageResource(version.getImageResId());
            textViewVersionName.setText(version.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(version);
                }
            });
        }
    }
}
