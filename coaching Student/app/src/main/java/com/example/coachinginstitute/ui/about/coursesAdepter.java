package com.example.coachinginstitute.ui.about;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.coachinginstitute.R;

import java.util.List;

public class coursesAdepter extends PagerAdapter {
        private Context context;
        private List<courseModal> list;

        public coursesAdepter(Context context, List<courseModal> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.courses_item_layout, container, false);

            ImageView memberIcon;
            TextView memberTitle, memberDesc;

            memberIcon = view.findViewById(R.id.memberIcon);
            memberTitle = view.findViewById(R.id.memberTitle);
            memberDesc = view.findViewById(R.id.memberDescription);

            memberIcon.setImageResource(list.get(position).getImg());

            memberTitle.setText(list.get(position).getTitle());

            memberDesc.setText(list.get(position).getDescription());

            container.addView(view, 0);

            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
}
