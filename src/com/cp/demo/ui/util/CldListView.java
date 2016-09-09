package com.cp.demo.ui.util;

import android.content.Context;
import android.widget.ListView;

public class CldListView extends ListView {
	// 继承关系ListView->AbsListView->AdapterView->ViewGroup

	public CldListView(Context context) {
		super(context);
	}

	// RecycleBin机制是ListView可以呈现成千上万条数据而不会OOM的一个重要原因
}
