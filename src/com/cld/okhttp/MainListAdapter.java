package com.cld.okhttp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class MainListAdapter extends BaseAdapter {

	private Context mContext;

	private List<String> mListItem;

	public MainListAdapter(Context mContext, List<String> mItem) {
		this.mContext = mContext;
		mListItem = mItem;
	}

	@Override
	public int getCount() {
		if (mListItem == null) {
			return 0;
		}
		return mListItem.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View mView = null;
		ViewHolder mViewH = null;
		if (convertView == null) {
			mView = LayoutInflater.from(mContext).inflate(
					R.layout.main_item_layout, null);
			mViewH = new ViewHolder(mView);
			mView.setTag(mViewH);
		} else {
			mView = convertView;
			mViewH = (ViewHolder) mView.getTag();
		}
		mViewH.mItemView
				.setText((position + 1) + "." + mListItem.get(position));
		return mView;
	}

	class ViewHolder {
		TextView mItemView;

		public ViewHolder(View view) {
			mItemView = (TextView) view.findViewById(R.id.item_content);
		}
	}

}
