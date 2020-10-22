package com.dev.water_query.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.dev.water_query.R;
import com.dev.water_query.adapter.GridViewAdapter;
import com.dev.water_query.adapter.ImagePagerAdapter;
import com.dev.water_query.adapter.ViewPagerAdapter;
import com.dev.water_query.entity.GridViewEntity;
import com.dev.water_query.entity.NoticeEntity;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private View mRootView;

    //上方的服务项
    private GridViewAdapter mAdapterGridViewService;
    private GridView mGridViewService;
    private ArrayList<GridViewEntity> mListGridViewService;

    //居中的轮播图
    private ImagePagerAdapter mAdapterImgPager;
    private ArrayList<Integer> mListImgId;
    private ViewPager mVpImgContent;

    //最下方的通知栏
    private TextView mBtnNotice1, mBtnNotice2, mBtnNotice3;
    private ViewPagerAdapter mAdapterNoticePager;
    private ViewPager mVpNoticeContent;
    private ArrayList<Fragment> mListNoticeFragments;
    private ArrayList<NoticeEntity> mListNoticeEntities1;
    private ArrayList<NoticeEntity> mListNoticeEntities2;
    private ArrayList<NoticeEntity> mListNoticeEntities3;

    public static Fragment newInstance() {
        return new HomeFragment();
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.home_fragment, container, false);

        //初始化
        initData();
        initView(inflater, container);
        setListener();

        return mRootView;
    }

    public void initView(LayoutInflater inflater, ViewGroup container) {

        //初始化轮播图
        mVpImgContent = mRootView.findViewById(R.id.vp_home_img_content);
        mAdapterImgPager = new ImagePagerAdapter(getContext(), mListImgId);
        mVpImgContent.setAdapter(mAdapterImgPager);


        //初始化服务列表View
        mGridViewService = mRootView.findViewById(R.id.gridview_home_service_list);
        mAdapterGridViewService = new GridViewAdapter<GridViewEntity>(mListGridViewService, R.layout.gridview_icon) {
            @Override
            public void bindView(ViewHolder holder, GridViewEntity obj) {
                holder.setImageResource(R.id.img_icon, obj.getiId());
                holder.setText(R.id.txt_icon, obj.getiName());
            }
        };
        mGridViewService.setAdapter(mAdapterGridViewService);

        //初始化通知栏
        mBtnNotice1 = mRootView.findViewById(R.id.btn_home_notice_1);
        mBtnNotice2 = mRootView.findViewById(R.id.btn_home_notice_2);
        mBtnNotice3 = mRootView.findViewById(R.id.btn_home_notice_3);
        mVpNoticeContent = mRootView.findViewById(R.id.vp_home_notice_content);

        mAdapterNoticePager = new ViewPagerAdapter(mListNoticeFragments, getActivity().getSupportFragmentManager());
        mVpNoticeContent.setAdapter(mAdapterNoticePager);
    }

    public void initData() {
        //轮播图
        mListImgId = new ArrayList<Integer>();
        mListImgId.add(R.drawable.test1);
        mListImgId.add(R.drawable.test2);
        mListImgId.add(R.drawable.test3);

        //服务列表
        mListGridViewService = new ArrayList<GridViewEntity>();
        mListGridViewService.add(new GridViewEntity(R.drawable.ic_baseline_chat_24, "水费查询"));
        mListGridViewService.add(new GridViewEntity(R.drawable.ic_baseline_apps_24, "我要交费"));
        mListGridViewService.add(new GridViewEntity(R.drawable.ic_baseline_chat_24, "用水价格"));
        mListGridViewService.add(new GridViewEntity(R.drawable.ic_baseline_chat_24, "电子发票"));
        mListGridViewService.add(new GridViewEntity(R.drawable.ic_baseline_chat_24, "停水降压通知"));
        mListGridViewService.add(new GridViewEntity(R.drawable.ic_baseline_chat_24, "水质水压公告"));
        mListGridViewService.add(new GridViewEntity(R.drawable.ic_baseline_chat_24, "营业网点"));
        mListGridViewService.add(new GridViewEntity(R.drawable.ic_baseline_chat_24, "直饮水服务"));

        //通知栏
        mListNoticeEntities1 = new ArrayList<NoticeEntity>();
        mListNoticeEntities1.add(new NoticeEntity("江水降压通知1", "10-02", "notice1"));
        mListNoticeEntities1.add(new NoticeEntity("江水降压通知2", "10-11", "notice2"));
        mListNoticeEntities1.add(new NoticeEntity("江水降压通知3", "10-23", "notice3a"));
        mListNoticeEntities1.add(new NoticeEntity("江水降压通知11", "12-23", "notice3nb"));

        mListNoticeEntities2 = new ArrayList<NoticeEntity>();
        mListNoticeEntities2.add(new NoticeEntity("水质水压通知1", "08-02", "notice1 - 2"));
        mListNoticeEntities2.add(new NoticeEntity("水质水压通知2", "11-01", "notice2 - 2"));
        mListNoticeEntities2.add(new NoticeEntity("水质水压通知3", "12-13", "notice3 - 2"));
        mListNoticeEntities1.add(new NoticeEntity("江水降压通知4", "1-3", "notice3a"));
        mListNoticeEntities1.add(new NoticeEntity("江水降压通知5", "1-2", "notice3b"));

        mListNoticeEntities3 = new ArrayList<NoticeEntity>();
        mListNoticeEntities3.add(new NoticeEntity("招采信息通知1", "01-12", "notice1 - 3"));
        mListNoticeEntities3.add(new NoticeEntity("招采信息通知2", "11-31", "notice2 - 3"));
        mListNoticeEntities3.add(new NoticeEntity("招采信息通知3", "12-23", "notice3 - 3"));

        mListNoticeFragments = new ArrayList<Fragment>();
        mListNoticeFragments.add(new NoticeFragment(mListNoticeEntities1, getActivity()));
        mListNoticeFragments.add(new NoticeFragment(mListNoticeEntities2, getActivity()));
        mListNoticeFragments.add(new NoticeFragment(mListNoticeEntities3, getActivity()));
    }

    private void setListener() {
        mBtnNotice1.setOnClickListener(this);
        mBtnNotice2.setOnClickListener(this);
        mBtnNotice3.setOnClickListener(this);

        mVpNoticeContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                View id;
                if (i == 0) {
                    id = mBtnNotice1;
                } else if (i == 1) {
                    id = mBtnNotice2;
                } else {
                    id = mBtnNotice3;
                }
                onClick(id);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home_notice_1:
                mBtnNotice1.setTextColor(0xFFFFFFFF);
                mBtnNotice1.setBackgroundColor(0xFF00BCD4);

                mBtnNotice2.setBackgroundColor(0xFFFFFFFF);
                mBtnNotice2.setTextColor(0xFF000000);
                mBtnNotice3.setBackgroundColor(0xFFFFFFFF);
                mBtnNotice3.setTextColor(0xFF000000);

                //跳转到对应的fragment
                mVpNoticeContent.setCurrentItem(0);
                break;
            case R.id.btn_home_notice_2:
                mBtnNotice2.setTextColor(0xFFFFFFFF);
                mBtnNotice2.setBackgroundColor(0xFF00BCD4);

                mBtnNotice1.setBackgroundColor(0xFFFFFFFF);
                mBtnNotice1.setTextColor(0xFF000000);
                mBtnNotice3.setBackgroundColor(0xFFFFFFFF);
                mBtnNotice3.setTextColor(0xFF000000);

                //跳转到对应的fragment
                mVpNoticeContent.setCurrentItem(1);
                break;
            case R.id.btn_home_notice_3:
                mBtnNotice3.setTextColor(0xFFFFFFFF);
                mBtnNotice3.setBackgroundColor(0xFF00BCD4);

                mBtnNotice1.setBackgroundColor(0xFFFFFFFF);
                mBtnNotice1.setTextColor(0xFF000000);
                mBtnNotice2.setBackgroundColor(0xFFFFFFFF);
                mBtnNotice2.setTextColor(0xFF000000);

                //跳转到对应的fragment
                mVpNoticeContent.setCurrentItem(2);
                break;
        }
    }
}