package com.GraduationDesign.MusicPlayer.Web;

public interface ProgressRequestListener  {
    void onRequestProgress(long bytesWritten, long contentLength, boolean done);
}
