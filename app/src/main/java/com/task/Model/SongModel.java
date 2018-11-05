package com.task.Model;

/**
 * Created by vishal on 11/2/2018.
 */

public class SongModel {

    private String wrapperType;
    private String kind;
    private String artistId;
    private String collectionId;
    private String trackId;
    private String artistName;

    private String artistViewUrl;

    SongModel(String wrapperType,String kind,String artistId,String collectionId,String trackId,String artistName,String artistViewUrl){
        this.wrapperType =wrapperType;
        this.kind =kind;
        this.artistId =artistId;
        this.collectionId =collectionId;
        this.trackId =trackId;
        this.artistName =artistName;
        this.artistViewUrl =artistViewUrl;
    }

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }



}
