package com.abc.designsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzm.designsample.R;

import java.util.ArrayList;


public class CardViewAdapter extends
		RecyclerView.Adapter<CardViewAdapter.ViewHolder> {

	private ArrayList<String> datalist;
	private Context context;

	public CardViewAdapter(ArrayList<String> datalist, Context context) {
		super();
		this.datalist = datalist;
		this.context = context;

	}


	public static class ViewHolder extends RecyclerView.ViewHolder {

		private TextView mTextView;

		public ViewHolder(View v) {
			super(v);

			mTextView = (TextView) v.findViewById(R.id.textView_card);
//			mImageView = (ImageView) v.findViewById(R.id.imageView1);

		}

	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return datalist == null ? 0 : datalist.size();
	}


	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

		View view = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.item_cardview, viewGroup, false);

		ViewHolder viewHolder=new ViewHolder(view);

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int i) {

		viewHolder.mTextView.setText(datalist.get(i));

	}

}
