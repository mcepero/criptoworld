package model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exchanges")
public class Exchange implements Serializable, Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = false)
    String id;

    @ColumnInfo(name = "nombre")
    String nombre;

    @ColumnInfo(name = "pais")
    String pais;

    @ColumnInfo(name = "anoFundacion")
    int anoFundacion;

    @ColumnInfo(name = "volumen")
    long volumen;

    @ColumnInfo(name = "url")
    String url;

    @ColumnInfo(name = "imagen")
    String imagen;

    public Exchange(@NonNull String id, String nombre, String pais, int anoFundacion, long volumen, String url, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.anoFundacion = anoFundacion;
        this.volumen = volumen;
        this.url = url;
        this.imagen = imagen;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getAnoFundacion() {
        return anoFundacion;
    }

    public void setAnoFundacion(int anoFundacion) {
        this.anoFundacion = anoFundacion;
    }

    public long getVolumen() {
        return volumen;
    }

    public void setVolumen(long volumen) {
        this.volumen = volumen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
