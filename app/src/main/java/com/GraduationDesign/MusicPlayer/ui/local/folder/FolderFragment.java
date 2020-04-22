package com.GraduationDesign.MusicPlayer.ui.local.folder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.to.aboomy.banner.Banner;
import com.to.aboomy.banner.IndicatorView;
import com.to.aboomy.banner.ScaleInTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.RxBus;
import com.GraduationDesign.MusicPlayer.data.model.Folder;
import com.GraduationDesign.MusicPlayer.data.model.PlayList;
import com.GraduationDesign.MusicPlayer.data.source.AppRepository;
import com.GraduationDesign.MusicPlayer.event.AddFolderEvent;
import com.GraduationDesign.MusicPlayer.event.PlayListCreatedEvent;
import com.GraduationDesign.MusicPlayer.ui.base.BaseFragment;
import com.GraduationDesign.MusicPlayer.ui.base.adapter.OnItemClickListener;
import com.GraduationDesign.MusicPlayer.ui.common.DefaultDividerDecoration;
import com.GraduationDesign.MusicPlayer.ui.details.PlayListDetailsActivity;
import com.GraduationDesign.MusicPlayer.ui.local.Adapter.CategoryAdapter;
import com.GraduationDesign.MusicPlayer.ui.local.Adapter.CategoryDetail;
import com.GraduationDesign.MusicPlayer.ui.local.ImageBannerHolder;
import com.GraduationDesign.MusicPlayer.ui.local.filesystem.FileSystemActivity;
import com.GraduationDesign.MusicPlayer.ui.playlist.AddToPlayListDialogFragment;
import com.GraduationDesign.MusicPlayer.ui.playlist.EditPlayListDialogFragment;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 9/3/16
 * Time: 7:29 PM
 * Desc: FolderFragment
 */
public class FolderFragment extends BaseFragment {



}
