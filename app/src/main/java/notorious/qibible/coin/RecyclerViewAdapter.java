package notorious.qibible.coin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import notorious.qibible.R;
import notorious.qibible.activiteListCoin.CultureGeneralActivity;
import notorious.qibible.activiteListCoin.GeneseActivity;
import notorious.qibible.activiteListCoin.JeanActivity;
import notorious.qibible.activiteListCoin.MarcActivity;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<LevelCoin> mData ;


    public RecyclerViewAdapter(Context mContext, List<LevelCoin> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_coin,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitle());
        holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position){
                    case 0:
                        Intent genese = new Intent(v.getContext(), GeneseActivity.class);
                        genese.putExtra("Title",mData.get(position).getTitle());
                        genese.putExtra("Description",mData.get(position).getDescription());
                        genese.putExtra("Thumbnail",mData.get(position).getThumbnail());
                        v.getContext().startActivity(genese);
                        break;
                    case 2:Intent marc = new Intent(v.getContext(), MarcActivity.class);
                        marc.putExtra("Title",mData.get(position).getTitle());
                        marc.putExtra("Description",mData.get(position).getDescription());
                        marc.putExtra("Thumbnail",mData.get(position).getThumbnail());
                        v.getContext().startActivity(marc);
                    case 3:
                        Intent cg = new Intent(v.getContext(), CultureGeneralActivity.class);
                        cg.putExtra("Title",mData.get(position).getTitle());
                        cg.putExtra("Description",mData.get(position).getDescription());
                        cg.putExtra("Thumbnail",mData.get(position).getThumbnail());
                        v.getContext().startActivity(cg);
                        break;
                    case 4:
                        Intent jean = new Intent(v.getContext(), JeanActivity.class);
                        jean.putExtra("Title",mData.get(position).getTitle());
                        jean.putExtra("Description",mData.get(position).getDescription());
                        jean.putExtra("Thumbnail",mData.get(position).getThumbnail());
                        v.getContext().startActivity(jean);
                        break;
//                    case 5:
//                        Intent matthieu = new Intent(v.getContext(), Activity.class);
//                        matthieu.putExtra("Title",mData.get(position).getTitle());
//                        matthieu.putExtra("Description",mData.get(position).getDescription());
//                        matthieu.putExtra("Thumbnail",mData.get(position).getThumbnail());
//                        v.getContext().startActivity(matthieu);
//                        break;
                }

//                Intent intent = new Intent(mContext, BookActivity.class);
//
//                // passing data to the book activity
//                intent.putExtra("Title",mData.get(position).getTitle());
//                intent.putExtra("Description",mData.get(position).getDescription());
//                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
//                // start the activity
//                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }


}
