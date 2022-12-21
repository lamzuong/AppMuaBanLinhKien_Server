package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@IdClass(ChiTietHoaDonPK.class)
public class ChiTietHoaDon implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name = "maHD")
	private HoaDon hoaDon;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "maLK")
	private LinhKien linhKien;
	
	@Column(nullable = false)
	private int soLuong;
	
	@Column(columnDefinition = "money", nullable = false)
	private double giaTien;
	
	@Column(columnDefinition = "money", nullable = false)
	private double thanhTien;
	
	public ChiTietHoaDon() {
	}

	public ChiTietHoaDon(HoaDon hoaDon, LinhKien linhKien, int soLuong, double giaTien) {
		super();
		this.hoaDon = hoaDon;
		this.linhKien = linhKien;
		this.soLuong = soLuong;
		this.giaTien = giaTien;
		this.thanhTien = soLuong * giaTien;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public LinhKien getLinhKien() {
		return linhKien;
	}

	public void setLinhKien(LinhKien linhKien) {
		this.linhKien = linhKien;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", linhKien=" + linhKien + ", soLuong=" + soLuong + ", thanhTien="
				+ thanhTien + "]";
	}
	
}
