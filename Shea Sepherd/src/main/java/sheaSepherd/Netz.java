package sheaSepherd;

import java.util.Locale;

import jakarta.persistence.*;

@Entity
public class Netz{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int nr = 1;
    private double longitude;
    private double latitude;
    private int größe;
    private Status status;
    
    
    public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getGröße() {
		return größe;
	}

	public void setGröße(int größe) {
		this.größe = größe;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}
	
	public String getStandort() {
		return String.format(Locale.US, "%.6f, %.6f", latitude, longitude); //für 6 Nachkommastellen, für . statt ,
	}
	
	
    public Netz() {
    	
    }
    
    public Netz(double latitude, double longitude, int größe) {
    	this.latitude = latitude;
    	this.longitude = longitude;
    	this.größe = größe;
    }
    
    public Netz(double latitude, double longitude, int größe, Status status, int nr) {
    	this.latitude = latitude;
    	this.longitude = longitude;
    	this.größe = größe;
    	this.status = status;
    	this.nr = nr;
    }

}