package model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "criptomonedas")
public class Criptomoneda implements Serializable, Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = false)
    String id;

    @ColumnInfo(name = "numeroMoneda")
    long numeroMoneda;

    @ColumnInfo(name = "nombre")
    String nombre;

    @ColumnInfo(name = "abreviatura")
    String abreviatura;

    @ColumnInfo(name = "precioActual")
    double precioActual;

    @ColumnInfo(name = "cambio24h")
    double cambio24h;

    @ColumnInfo(name = "cambio7d")
    double cambio7d;

    @ColumnInfo(name = "cambio1y")
    double cambio1y;

    @ColumnInfo(name = "volumen")
    long volumen;

    @ColumnInfo(name = "capital")
    long capital;

    @ColumnInfo(name = "imagen_small")
    String imagen_small;

    @ColumnInfo(name = "imagen_large")
    String imagen_large;

    @ColumnInfo(name = "precio_introducido")
    double precio_introducido;

    public Criptomoneda(String id, long numeroMoneda, String nombre, String abreviatura, double precioActual, double cambio24h, double cambio7d, double cambio1y, long volumen, long capital, String imagen_small, String imagen_large, double precio_introducido) {
        this.id = id;
        this.numeroMoneda=numeroMoneda;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.precioActual = precioActual;
        this.cambio24h = cambio24h;
        this.cambio7d = cambio7d;
        this.cambio1y = cambio1y;
        this.volumen = volumen;
        this.capital = capital;
        this.imagen_small = imagen_small;
        this.imagen_large = imagen_large;
        this.precio_introducido=precio_introducido;
    }

    protected Criptomoneda(Parcel in) {
        id = in.readString();
        numeroMoneda = in.readLong();
        nombre = in.readString();
        abreviatura = in.readString();
        precioActual = in.readDouble();
        cambio24h = in.readDouble();
        cambio7d = in.readDouble();
        cambio1y = in.readDouble();
        volumen = in.readLong();
        capital = in.readLong();
        imagen_small = in.readString();
        imagen_large = in.readString();
        precio_introducido = in.readDouble();
    }

    public static final Creator<Criptomoneda> CREATOR = new Creator<Criptomoneda>() {
        @Override
        public Criptomoneda createFromParcel(Parcel in) {
            return new Criptomoneda(in);
        }

        @Override
        public Criptomoneda[] newArray(int size) {
            return new Criptomoneda[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }

    public double getCambio1ano() {
        return cambio1y;
    }

    public void setCambio1ano(double cambio1y) {
        this.cambio1y = cambio1y;
    }

    public double getCambio24h() {
        return cambio24h;
    }

    public void setCambio24h(double cambio24h) {
        this.cambio24h = cambio24h;
    }

    public double getCambio7d() {
        return cambio7d;
    }

    public void setCambio7d(double cambio7d) {
        this.cambio7d = cambio7d;
    }

    public long getVolumen() {
        return volumen;
    }

    public void setVolumen(long volumen) {
        this.volumen = volumen;
    }

    public long getCapital() {
        return capital;
    }

    public void setCapital(long capital) {
        this.capital = capital;
    }

    public String getImagenSmall() {
        return imagen_small;
    }

    public void setImagenSmall(String imagen) {
        this.imagen_small = imagen;
    }

    public String getImagen_large() {
        return imagen_large;
    }

    public void setImagen_large(String imagen_large) {
        this.imagen_large = imagen_large;
    }

    public long getNumeroMoneda() {
        return numeroMoneda;
    }

    public void setNumeroMoneda(long numeroMoneda) {
        this.numeroMoneda = numeroMoneda;
    }

    public double getPrecio_introducido() {
        return precio_introducido;
    }

    public void setPrecio_introducido(double precio_introducido) {
        this.precio_introducido = precio_introducido;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeLong(numeroMoneda);
        parcel.writeString(nombre);
        parcel.writeString(abreviatura);
        parcel.writeDouble(precioActual);
        parcel.writeDouble(cambio24h);
        parcel.writeDouble(cambio7d);
        parcel.writeDouble(cambio1y);
        parcel.writeLong(volumen);
        parcel.writeLong(capital);
        parcel.writeString(imagen_small);
        parcel.writeString(imagen_large);
        parcel.writeDouble(precio_introducido);
    }
}
