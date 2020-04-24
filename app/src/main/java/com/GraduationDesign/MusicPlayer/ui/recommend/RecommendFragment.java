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
import com.GraduationDesign.MusicPlayer.RxBus;
import com.GraduationDesign.MusicPlayer.data.model.Song;
import com.GraduationDesign.MusicPlayer.event.PlaySongEvent;
import com.GraduationDesign.MusicPlayer.ui.base.BaseFragment;
import com.GraduationDesign.MusicPlayer.ui.recommend.Adapter.CategoryAdapter;
import com.GraduationDesign.MusicPlayer.ui.recommend.Adapter.CategoryDetail;
import com.GraduationDesign.MusicPlayer.ui.local.ImageBannerHolder;
import com.GraduationDesign.MusicPlayer.utils.SecretUtil;
import com.GraduationDesign.MusicPlayer.utils.TimeHelper;
import com.GraduationDesign.MusicPlayer.utils.WyRecommendUtil;
import com.to.aboomy.banner.Banner;
import com.to.aboomy.banner.IndicatorView;
import com.to.aboomy.banner.ScaleInTransformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecommendFragment extends BaseFragment {
    Banner banner;
    List<String> list = new ArrayList<>();
    String APIKEY ;
    RecyclerView musicLists;
    CategoryAdapter categoryAdapter;
    List<CategoryDetail> categoryDetails = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_added_folders, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ButterKnife.bind(this, view);
        list.add("https://www.baiqing.work/love/img/1.jpg");
        list.add("https://www.baiqing.work/love/img/2.jpg");
        list.add("https://www.baiqing.work/love/img/3.jpg");
        banner = view.findViewById(R.id.banner);
        musicLists = view.findViewById(R.id.rv_press_category_novel_list);
        musicLists.setLayoutManager(new LinearLayoutManager(getActivity()));
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

        initData();




        LinearLayout linearLayout = view.findViewById(R.id.test_internet_music);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song song = new Song();
                song.setPath("https://www.baiqing.work/medium/偏偏.mp3");
                song.setSize(0);
                song.setDuration(200000);
                song.setTitle("偏偏");
                RxBus.getInstance().post(new PlaySongEvent(song));
            }
        });
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
                        Intent intent = new Intent(getActivity(),RecommendListsActicity.class);
                        intent.putExtra("ListId",ListId);
                        intent.putExtra("Key",APIKEY);
                        intent.putExtra("ListName",name);
                        startActivity(intent);
                    }
                });
        musicLists.setAdapter(categoryAdapter);
    }
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
    public  void getAPIKey(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String result = response.body().string();
                    APIKEY = result;
                }
            }
        });
    }

}
