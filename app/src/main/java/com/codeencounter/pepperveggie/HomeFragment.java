package com.codeencounter.pepperveggie;


import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeencounter.pepperveggie.Adapters.CategoryAdapater;
import com.codeencounter.pepperveggie.Adapters.CategoryModel;
import com.codeencounter.pepperveggie.Adapters.GridProductLayoutAdapter;
import com.codeencounter.pepperveggie.Adapters.HorizontalProductScrollAdapter;
import com.codeencounter.pepperveggie.Adapters.HorizontalProductScrollModel;
import com.codeencounter.pepperveggie.Adapters.SliderAdapter;
import com.codeencounter.pepperveggie.Adapters.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    private RecyclerView categoryRecyclerView;
    private CategoryAdapater categoryAdapater;


    ////////////////// Banner Slider Start
    private ViewPager bannerSliderViewPager;
    private  List<SliderModel> sliderModelList;
    private  int currentPage=2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    ////////////////// Banner Slider End


    ///////////////// Strip Ad Layout Start
    private ImageView stripAdImage;
    private ConstraintLayout stripAdContainer;
    ///////////////// Strip Ad Layout End



    ///////////////// Horizontal Product Layout Start
    private TextView horizontalLayoutTile;
    private Button horizontalViewAllBtn;
    private RecyclerView horizontalRecyclerView;
    ///////////////// Horizontal Product Layout End
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List <CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("link","Home"));
        categoryModelList.add(new CategoryModel("link","Electronics"));
        categoryModelList.add(new CategoryModel("link","Appliances"));
        categoryModelList.add(new CategoryModel("link","Furniture"));
        categoryModelList.add(new CategoryModel("link","Fashion"));
        categoryModelList.add(new CategoryModel("link","Toys"));
        categoryModelList.add(new CategoryModel("link","Sports"));
        categoryModelList.add(new CategoryModel("link","Wall Arts"));
        categoryModelList.add(new CategoryModel("link","Books"));
        categoryModelList.add(new CategoryModel("link","Shoes"));

        categoryAdapater = new CategoryAdapater(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapater);
        categoryAdapater.notifyDataSetChanged();





        ////////////// Banner Slider Start



        bannerSliderViewPager = view.findViewById(R.id.banner_slider_viewpager);

        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.mipmap.banner_7));
        sliderModelList.add(new SliderModel(R.mipmap.banner_8));

        sliderModelList.add(new SliderModel(R.mipmap.banner_1));
        sliderModelList.add(new SliderModel(R.mipmap.banner_2));
        sliderModelList.add(new SliderModel(R.mipmap.banner_3));
        sliderModelList.add(new SliderModel(R.mipmap.banner_4));
        sliderModelList.add(new SliderModel(R.mipmap.banner_5));
        sliderModelList.add(new SliderModel(R.mipmap.banner_6));
        sliderModelList.add(new SliderModel(R.mipmap.banner_7));
        sliderModelList.add(new SliderModel(R.mipmap.banner_8));

        sliderModelList.add(new SliderModel(R.mipmap.banner_1));
        sliderModelList.add(new SliderModel(R.mipmap.banner_2));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        bannerSliderViewPager.setCurrentItem(currentPage);
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                currentPage = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if(state == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }

            }
        };


        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);
        startbannerSlideShow();
        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                pageLooper();
                stopBannerSlideShow();
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    startbannerSlideShow();
                }
                return false;
            }
        });


        /////////////// Banner Slider End



        /////////////// Strip Ad Layout Start
        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);
        stripAdImage.setImageResource(R.mipmap.banner);
        ////////////// Strip Ad Layout End


        ///////////////// Horizontal Product Layout Start
        horizontalLayoutTile = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalViewAllBtn= view.findViewById(R.id.horizontal_scroll_view_all_button);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recycler_view);

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.mobile,"Samsung S9","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.mobile2,"Samsung S9","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.mobile,"Samsung S9","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.mobile2,"Samsung S9","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.mobile,"Samsung S9","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.mobile,"Samsung S9","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.mobile2,"Samsung S9","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.mobile,"Samsung S9","SD 625 Processor","Rs. 5999/-"));

        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);

        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();
        ///////////////// Horizontal Product Layout End




        ///////////////// Grid Product Layout Start

        TextView gridLayoutTitle = view.findViewById(R.id.grid_product_layout_title);
        Button gridLayoutViewAllBtn = view.findViewById(R.id.grid_product_layout_viewall_btn);
        GridView gridView = view.findViewById(R.id.grid_product_layout_gridview);

        gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));

        ///////////////// Grid Product Layout End


        return view;
    }

    ///////////////////// Banner Function Start

    private void pageLooper(){

        if(currentPage == sliderModelList.size()-2){
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }
        if(currentPage == 1){
            currentPage = sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }

    }




    private void startbannerSlideShow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if(currentPage >= sliderModelList.size()){
                    currentPage = 1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);
    }


    private void stopBannerSlideShow(){

        timer.cancel();

    }



    ///////////////////// Banner Function End





}
