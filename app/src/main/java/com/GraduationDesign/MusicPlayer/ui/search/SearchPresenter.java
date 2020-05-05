package com.GraduationDesign.MusicPlayer.ui.search;


import com.GraduationDesign.MusicPlayer.RxBus;
import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.BodyBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.WySearchResult;
import com.GraduationDesign.MusicPlayer.data.model.PlayList;
import com.GraduationDesign.MusicPlayer.data.model.Song;
import com.GraduationDesign.MusicPlayer.data.source.AppRepository;
import com.GraduationDesign.MusicPlayer.event.PlayListUpdatedEvent;
import com.GraduationDesign.MusicPlayer.utils.SecretUtil;
import com.GraduationDesign.MusicPlayer.utils.TimeHelper;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SearchPresenter implements SearchContract.Presenter {

    private String APIKey ;
    private CompositeSubscription mSubscriptions;
    private SearchContract.View mView;
    public SearchPresenter(SearchContract.View view) {
        this.mView = view;
        mSubscriptions = new CompositeSubscription();
        view.setPresenter(this);
    }


    @Override
    public void stop() {
        mView = null;
        mSubscriptions.clear();
    }

    @Override
    public void start() {
        try{
            APIKey = SecretUtil.md5(SecretUtil.md5("523077333")+
                    SecretUtil.sha1(TimeHelper.getStringDateForKey("yyyyMMddHH")));
        }catch (Exception e){
            e.printStackTrace();
        }
//        search("河图");
    }
    @Override
    public void search(String key) {
        CommonApi.getWySearch(key, APIKey, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                WySearchResult wySearchResult = (WySearchResult)o;
                mView.setAdapter(wySearchResult.getBody());
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public void addSongToPlayList(Song song, PlayList playList) {
        if (playList.isFavorite()) {
            song.setFavorite(true);
        }
        playList.addSong(song, 0);
        Subscription insertSubscription = AppRepository.getInstance().insert(song)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Song>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Song song) {

                    }
                });
        mSubscriptions.add(insertSubscription);
        Subscription subscription = AppRepository.getInstance().update(playList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PlayList>() {
                    @Override
                    public void onStart() {
                        mView.showLoading();
                    }

                    @Override
                    public void onCompleted(){
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideLoading();
                        mView.handleError(e);
                    }

                    @Override
                    public void onNext(PlayList playList) {
                        RxBus.getInstance().post(new PlayListUpdatedEvent(playList));
                    }
                });
        mSubscriptions.add(subscription);
    }
}
