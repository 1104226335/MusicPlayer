package com.GraduationDesign.MusicPlayer.ui.recommend;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.ui.base.BaseFragment;
import com.GraduationDesign.MusicPlayer.ui.recommend.Adapter.CategoryAdapter;
import com.GraduationDesign.MusicPlayer.ui.recommend.Adapter.CategoryDetail;
import com.GraduationDesign.MusicPlayer.ui.local.ImageBannerHolder;
import com.GraduationDesign.MusicPlayer.ui.search.SearchActivity;
import com.GraduationDesign.MusicPlayer.utils.SecretUtil;
import com.GraduationDesign.MusicPlayer.utils.TimeHelper;
import com.GraduationDesign.MusicPlayer.utils.WyRecommendUtil;
import com.to.aboomy.banner.Banner;
import com.to.aboomy.banner.IndicatorView;
import com.to.aboomy.banner.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RecommendFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_press_category_novel_list)
    RecyclerView musicLists;
    @BindView(R.id.test_internet_music)
    LinearLayout linearLayout;
    Unbinder butterKnife;
    List<String> list = new ArrayList<>();
    String APIKEY ;

    CategoryAdapter categoryAdapter;

    List<CategoryDetail> categoryDetails = new ArrayList<>();
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    initAdapter();
                    break;
            }
            return false;
        }
    });
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_added_folders, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        butterKnife = ButterKnife.bind(this, view);
        list.add("https://www.baiqing.work/love/img/1.jpg");
        list.add("https://www.baiqing.work/love/img/2.jpg");
        list.add("https://www.baiqing.work/love/img/3.jpg");

        musicLists.setLayoutManager(new LinearLayoutManager(getActivity()));
        setBanner();

        initData();

    }
    @OnClick(R.id.test_internet_music)
    public void onSearchOnclick(){
        Intent intent = new Intent(getActivity(),SearchActivity.class);
        startActivity(intent);
    }

    public void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                categoryDetails = WyRecommendUtil.getRecommendFromWy();
                if (!categoryDetails.isEmpty())handler.sendMessage(handler.obtainMessage(1));
            }
        }).start();
        try{
            APIKEY = SecretUtil.md5(SecretUtil.md5("523077333")+
                    SecretUtil.sha1(TimeHelper.getStringDateForKey("yyyyMMddHH")));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void initAdapter(){
        categoryAdapter = new CategoryAdapter(getActivity(), categoryDetails,
                new MusicListListener() {
                    @Override
                    public void OnClickMore() {

                    }

                    @Override
                    public void OnClickItem(String ListId,String name) {
                        Intent intent = new Intent(getActivity(),RecommendListsActivity.class);
                        intent.putExtra("ListId",ListId);
                        intent.putExtra("Key",APIKEY);
                        intent.putExtra("ListName",name);
                        startActivity(intent);
                    }
                });
        musicLists.setAdapter(categoryAdapter);
    }
    public void setBanner(){
        //使用内置Indicator
        IndicatorView qyIndicator = new IndicatorView(getActivity())
                .setIndicatorColor(Color.DKGRAY)
                .setIndicatorSelectorColor(Color.WHITE);
        banner.setIndicator(qyIndicator)
                .setHolderCreator(new ImageBannerHolder())
                .setPages(list);
        //设置左右页面露出来的宽度及item与item之间的宽度
        banner.setPageMargin(20, 20)
                //内置ScaleInTransformer，设置切换缩放动画
                .setPageTransformer(true, new ScaleInTransformer());
        banner.startTurning();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        butterKnife.unbind();
    }
}
