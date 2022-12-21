package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class KhachHang implements Serializable {
	@Id
	private String maKH;
	
	@Column(columnDefinition = "nvarchar(255)",nullable = false)
	private String tenKH;
	
	@Column(nullable = false)
	private boolean gioiTinh;
	
	@Column(columnDefinition = "date", nullable = false)
	private Date ngaySinh;
	
	@Column(nullable = false, unique = true)
	private String soDT;
	
	@OneToMany(mappedBy = "khachHang")
	private List<HoaDon> hoaDon;
	
	public KhachHang() {
	}

	public KhachHang(String maKH, String tenKH, boolean gioiTinh, Date ngaySinh, String soDT) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.soDT = soDT;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public List<HoaDon> getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(List<HoaDon> hoaDon) {
		this.hoaDon = hoaDon;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh
				+ ", soDT=" + soDT + "]";
	}
	
	
}
