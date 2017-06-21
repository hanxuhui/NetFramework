package com.hanxuhui.netframework.protocol.filedownload;

/**
 * Created by hanxuhui on 2016/7/14.
 */
public class FileEvent {

    //当前读取字节长度
    private long bytesRead;
    //总字节长度
    private long contentLength;
    //是否读取完成
    private boolean done;

    public FileEvent(long bytesRead, long contentLength, boolean done) {
        this.bytesRead = bytesRead;
        this.contentLength = contentLength;
        this.done = done;
    }

    public long getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "ProgressModel{" +
                "bytesRead=" + bytesRead +
                ", contentLength=" + contentLength +
                ", done=" + done +
                '}';
    }

}
