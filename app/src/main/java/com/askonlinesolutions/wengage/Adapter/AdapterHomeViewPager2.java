package com.askonlinesolutions.wengage.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askonlinesolutions.wengage.Fragment.Main.venue.RestaurantList;
import com.askonlinesolutions.wengage.R;

import java.util.ArrayList;

/**
 * Created by Muhib.
 * Contact Number : +91 9796173066
 */
public class AdapterHomeViewPager2 extends PagerAdapter{


    LayoutInflater mLayoutInflater;
    Context context;
    ArrayList<String> cat_names = new ArrayList<>();
    ArrayList<Integer> cat_images = new ArrayList<>();
    FragmentManager manager;
    private String cat_name, selected_pref;
    ArrayList<String> view_pager_page_item = new ArrayList<>();
    private Interface_Home_2 click;


    public AdapterHomeViewPager2(Context context, FragmentManager manager, ArrayList<Integer> cat_images, ArrayList<String> cat_names,
                                 String cat_name, String selected_pref, ArrayList<String> view_pager_page_item,
                                 Interface_Home_2 click) {

        this.context = context;
        mLayoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cat_images = cat_images;
        this.cat_names = cat_names;
        this.manager = manager;
        this.cat_name = cat_name;
        this.view_pager_page_item = view_pager_page_item;
        this.click = click;
        this.selected_pref = selected_pref;
    }

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
        View itemView = mLayoutInflater.inflate(R.layout.item_category_2, container, false);

        TextView tv1 = (TextView) itemView.findViewById(R.id.text_9);
        TextView tv2 = (TextView) itemView.findViewById(R.id.text_10);
        TextView tv3 = (TextView) itemView.findViewById(R.id.text_11);
        TextView tv4 = (TextView) itemView.findViewById(R.id.text_12);

        ImageView iv1 = (ImageView) itemView.findViewById(R.id.image_9);
        ImageView iv2 = (ImageView) itemView.findViewById(R.id.image_10);
        ImageView iv3 = (ImageView) itemView.findViewById(R.id.image_11);
        ImageView iv4 = (ImageView) itemView.findViewById(R.id.image_12);

        LinearLayout l1 = (LinearLayout) itemView.findViewById(R.id.layout_9);
        LinearLayout l2 = (LinearLayout) itemView.findViewById(R.id.layout_10);
        LinearLayout l3 = (LinearLayout) itemView.findViewById(R.id.layout_11);
        LinearLayout l4 = (LinearLayout) itemView.findViewById(R.id.layout_12);

        for(int i=0; i<view_pager_page_item.size(); i++){
            tv1.setText(cat_names.get(4*position + 0));
            tv2.setText(cat_names.get(4*position + 1));
            tv3.setText(cat_names.get(4*position + 2));
            tv4.setText(cat_names.get(4*position + 3));

            iv1.setImageResource(cat_images.get(4*position + 0));
            iv2.setImageResource(cat_images.get(4*position + 1));
            iv3.setImageResource(cat_images.get(4*position + 2));
            iv4.setImageResource(cat_images.get(4*position + 3));
        }
        final RestaurantList fragment = new RestaurantList();

/*        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category", cat_name);
                bundle.putString("selected_pref", selected_pref);
                fragment.setArguments(bundle);
                new BaseClass(context).callFragment(fragment, fragment.getClass().getName(), manager);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category", cat_name);
                bundle.putString("selected_pref", selected_pref);
                fragment.setArguments(bundle);
                new BaseClass(context).callFragment(fragment, fragment.getClass().getName(), manager);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category", cat_name);
                bundle.putString("selected_pref", selected_pref);
                fragment.setArguments(bundle);
                new BaseClass(context).callFragment(fragment, fragment.getClass().getName(), manager);
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category", cat_name);
                bundle.putString("selected_pref", selected_pref);
                fragment.setArguments(bundle);
                new BaseClass(context).callFragment(fragment, fragment.getClass().getName(), manager);
            }
        });*/
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cat_names.get(position*4 + 0).equals(""))
                    click.click_interface_home_2(position, cat_names.get(position*4 + 0));
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cat_names.get(position*4 + 1).equals(""))
                    click.click_interface_home_2(position, cat_names.get(position*4 + 1));
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cat_names.get(position*4 + 2).equals(""))
                    click.click_interface_home_2(position, cat_names.get(position*4 + 2));
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cat_names.get(position*4 + 3).equals(""))
                    click.click_interface_home_2(position, cat_names.get(position*4 + 3));
            }
        });


        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
    public interface Interface_Home_2{
        public void click_interface_home_2(int position, String name);
    }
}
