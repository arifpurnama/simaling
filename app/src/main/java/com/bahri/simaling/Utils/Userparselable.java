package com.bahri.simaling.Utils;

import android.os.Parcel;
import android.os.Parcelable;

public class Userparselable implements Parcelable{
    private int id;
    private String nik;
    private String pass;
    private String nama;
    private String image;
    private String notlp;

    public String getNotlp() {
        return notlp;
    }

    public void setNotlp(String notlp) {
        this.notlp = notlp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Userparselable(){

    }

    public Userparselable(int id, String nik, String pass, String nama, String image, String notlp) {
        this.id = id;
        this.nik = nik;
        this.pass = pass;
        this.nama = nama;
        this.image = image;
        this.notlp = notlp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nik);
        dest.writeString(this.pass);
        dest.writeString(this.nama);
        dest.writeString(this.image);
        dest.writeString(this.notlp);
    }

    protected Userparselable(Parcel in) {
        this.id = in.readInt();
        this.nik = in.readString();
        this.pass = in.readString();
        this.nama = in.readString();
        this.image = in.readString();
        this.notlp = in.readString();
    }

    public static final Creator<Userparselable> CREATOR = new Creator<Userparselable>() {
        @Override
        public Userparselable createFromParcel(Parcel source) {
            return new Userparselable(source);
        }

        @Override
        public Userparselable[] newArray(int size) {
            return new Userparselable[size];
        }
    };
}
