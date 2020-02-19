package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.ketentuan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;
import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class KetentuanAdapter extends ExpandableRecyclerViewAdapter<KetentuanAdapter.KetentuanViewHolder,KetentuanAdapter.KetentuanContentViewHolder> {

    public KetentuanAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public KetentuanViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ketentuan_header, parent, false);
        return new KetentuanViewHolder(view);
    }

    @Override
    public KetentuanContentViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_ketentuan_content, parent, false);
        return new KetentuanContentViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(KetentuanContentViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final KetentuanContent artist = ((Ketentuan) group).getItems().get(childIndex);
        holder.setArtistName(artist.getName());
    }

    @Override
    public void onBindGroupViewHolder(KetentuanViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGenreTitle(group);
    }


    public class KetentuanViewHolder extends GroupViewHolder {

        private TextView genreTitle;
        private ImageView arrow;

        public KetentuanViewHolder(View itemView) {
            super(itemView);
            genreTitle = itemView.findViewById(R.id.list_item_genre_name);
            arrow = itemView.findViewById(R.id.list_item_arrow);
        }

        public void setGenreTitle(ExpandableGroup group) {
            genreTitle.setText(group.getTitle());
        }

        @Override
        public void expand() {
            RotateAnimation rotate =
                    new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            arrow.setAnimation(rotate);
        }

        @Override
        public void collapse() {
            RotateAnimation rotate =
                    new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            arrow.setAnimation(rotate);
        }

    }

    public class KetentuanContentViewHolder extends ChildViewHolder {

        private TextView artistName;

        public KetentuanContentViewHolder(View itemView) {
            super(itemView);
            artistName = itemView.findViewById(R.id.list_item_artist_name);
        }

        public void setArtistName(String name) {
            artistName.setText(name);
        }
    }
}
