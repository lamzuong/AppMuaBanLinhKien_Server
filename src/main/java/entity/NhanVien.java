package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class NhanVien implements Serializable {
	/**
	 * 
	 */
	@Id
	private String maNV;
	
	@Column(columnDefinition = "nvarchar(255)", nullable = false)
	private String tenNV;
	
	@Column(columnDefinition = "date", nullable = false)
	private Date ngaySinh;
	
	@Column(nullable = false)
	private String soDT;
	
	@Column(nullable = false)
	private String cmnd;
	
	@Column(nullable = false)
	private boolean gioiTinh;
	
	@Column(columnDefinition = "money", nullable = false)
	private double luong;
	
	@Column(nullable = false)
	private boolean trangThai;
	
	@OneToMany(mappedBy = "nhanVien")
	private List<HoaDon> hoaDon;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private TaiKhoan taiKhoan;
	
	public NhanVien() {
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public NhanVien(String maNV, String tenNV, Date ngaySinh, String soDT, String cmnd, boolean gioiTinh, double luong,
			boolean trangThai) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.soDT = soDT;
		this.cmnd = cmnd;
		this.gioiTinh = gioiTinh;
		this.luong = luong;
		this.trangThai = trangThai;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
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

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public List<HoaDon> getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(List<HoaDon> hoaDon) {
		this.hoaDon = hoaDon;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", ngaySinh=" + ngaySinh + ", soDT=" + soDT + ", cmnd="
				+ cmnd + ", gioiTinh=" + gioiTinh + ", luong=" + luong + "]";
	}
}
