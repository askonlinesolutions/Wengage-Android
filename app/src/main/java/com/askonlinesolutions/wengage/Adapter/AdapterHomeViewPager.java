package com.askonlinesolutions.wengage.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.R;

import java.util.ArrayList;

/**
 * Created by Muhib.
 * Contact Number : +91 9796173066
 */
public class AdapterHomeViewPager extends PagerAdapter{

    LayoutInflater mLayoutInflater;
    Context context;
    ArrayList<String> cat_names = new ArrayList<>();
    ArrayList<Integer> cat_images = new ArrayList<>();
    ArrayList<String> view_pager_page_item = new ArrayList<>();
    FragmentManager manager;
//    int page_count, item_count;
    private Interface_Home click;

    public AdapterHomeViewPager(Context context, FragmentManager manager,
                                ArrayList<Integer> cat_images, ArrayList<String> cat_names,
                                ArrayList<String> view_pager_page_item, Interface_Home click) {
        this.context = context;
        mLayoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cat_images = cat_images;
        this.cat_names = cat_names;
        this.manager = manager;
        this.view_pager_page_item = view_pager_page_item;
        this.click = click;
    /*    item_count = cat_names.size()%8 ;
        if(item_count == 0){
            page_count = cat_names.size()/8;
        } else{
            page_count = cat_names.size()/8 + 1;
        }
    */}

    @Override
    public int getCount() {
        return view_pager_page_item.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_category_1, container, false);

//        Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();

        Log.d("ViewPagerItemCount", view_pager_page_item.get(position) + "\n");
        TextView tv1 = (TextView) itemView.findViewById(R.id.text_1);
        TextView tv2 = (TextView) itemView.findViewById(R.id.text_2);
        TextView tv3 = (TextView) itemView.findViewById(R.id.text_3);
        TextView tv4 = (TextView) itemView.findViewById(R.id.text_4);
        TextView tv5 = (TextView) itemView.findViewById(R.id.text_5);
        TextView tv6 = (TextView) itemView.findViewById(R.id.text_6);
        TextView tv7 = (TextView) itemView.findViewById(R.id.text_7);
        TextView tv8 = (TextView) itemView.findViewById(R.id.text_8);

        ImageView iv1 = (ImageView) itemView.findViewById(R.id.image_1);
        ImageView iv2 = (ImageView) itemView.findViewById(R.id.image_2);
        ImageView iv3 = (ImageView) itemView.findViewById(R.id.image_3);
        ImageView iv4 = (ImageView) itemView.findViewById(R.id.image_4);
        ImageView iv5 = (ImageView) itemView.findViewById(R.id.image_5);
        ImageView iv6 = (ImageView) itemView.findViewById(R.id.image_6);
        ImageView iv7 = (ImageView) itemView.findViewById(R.id.image_7);
        ImageView iv8 = (ImageView) itemView.findViewById(R.id.image_8);

        LinearLayout l1 = (LinearLayout) itemView.findViewById(R.id.layout_1);
        LinearLayout l2 = (LinearLayout) itemView.findViewById(R.id.layout_2);
        LinearLayout l3 = (LinearLayout) itemView.findViewById(R.id.layout_3);
        LinearLayout l4 = (LinearLayout) itemView.findViewById(R.id.layout_4);
        LinearLayout l5 = (LinearLayout) itemView.findViewById(R.id.layout_5);
        LinearLayout l6 = (LinearLayout) itemView.findViewById(R.id.layout_6);
        LinearLayout l7 = (LinearLayout) itemView.findViewById(R.id.layout_7);
        LinearLayout l8 = (LinearLayout) itemView.findViewById(R.id.layout_8);

        for(int i=0; i<view_pager_page_item.size(); i++){
            tv1.setText(cat_names.get(8*position + 0));
            tv2.setText(cat_names.get(8*position + 1));
            tv3.setText(cat_names.get(8*position + 2));
            tv4.setText(cat_names.get(8*position + 3));
            tv5.setText(cat_names.get(8*position + 4));
            tv6.setText(cat_names.get(8*position + 5));
            tv7.setText(cat_names.get(8*position + 6));
            tv8.setText(cat_names.get(8*position + 7));

            iv1.setImageResource(cat_images.get(8*position + 0));
            iv2.setImageResource(cat_images.get(8*position + 1));
            iv3.setImageResource(cat_images.get(8*position + 2));
            iv4.setImageResource(cat_images.get(8*position + 3));
            iv5.setImageResource(cat_images.get(8*position + 4));
            iv6.setImageResource(cat_images.get(8*position + 5));
            iv7.setImageResource(cat_images.get(8*position + 6));
            iv8.setImageResource(cat_images.get(8*position + 7));
        }
//        final VenueSubCatFragment fragment = new VenueSubCatFragment();

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!cat_names.get(position*8 + 0).equals(""))
                    click.click_interface_home(position, cat_names.get(position*8 + 0));
                /*Bundle bundle = new Bundle();
                bundle.putString("category", "The City");
                  fragment.setArguments(bundle);
                new BaseClass(context).callFragment(fragment, fragment.getClass().getName(), manager);*/
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cat_names.get(position*8 + 1).equals(""))
                    click.click_interface_home(position, cat_names.get(position*8 + 1));
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cat_names.get(position*8 + 2).equals(""))
                    click.click_interface_home(position, cat_names.get(position*8 + 2));
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cat_names.get(position*8 + 3).equals(""))
                    click.click_interface_home(position, cat_names.get(position*8 + 3));
            }
        });
        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cat_names.get(position*8 + 4).equals(""))
                    click.click_interface_home(position, cat_names.get(position*8 + 4));
            }
        });
        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cat_names.get(position*8 + 5).equals(""))
                    click.click_interface_home(position, cat_names.get(position*8 + 5));
            }
        });
        l7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cat_names.get(position*8 + 6).equals(""))
                    click.click_interface_home(position, cat_names.get(position*8 + 6));
            }
        });
        l8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cat_names.get(position*8 + 7).equals(""))
                    click.click_interface_home(position, cat_names.get(position*8 + 7));
            }
        });

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public interface Interface_Home{
        public void click_interface_home(int position, String name);
    }
}
