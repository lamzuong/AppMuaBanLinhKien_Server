package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class LinhKien implements Serializable {
	@Id
	private String maLK;
	
	@Column(columnDefinition = "nvarchar(255)",nullable = false)
	private String tenLK;
	
	@Column(columnDefinition = "money", nullable = false)
	private double giaBan;
	
	@Column(columnDefinition = "money", nullable = false)
	private double giaNhap;
	
	@Column(nullable = false)
	private int soLuong;
	
	@Column(columnDefinition = "date", nullable = false)
	private Date ngayNhapKho;
	
	@Column(columnDefinition = "nvarchar(255)",nullable = false)
	private String baoHanh;
	
	@Column(columnDefinition = "nvarchar(255)",nullable = false) 
	private String nhaSanXuat;
	
	@Column(nullable = false) 
	private boolean trangThai;
	
	@OneToMany(mappedBy = "linhKien")
	private List<ChiTietHoaDon> dsChiTietHoaDon;
	
	public LinhKien() {
	}

	public LinhKien(String maLK, String tenLK, double giaBan, double giaNhap, int soLuong, Date ngayNhapKho,
			String baoHanh, String nhaSanXuat, boolean trangThai) {
		super();
		this.maLK = maLK;
		this.tenLK = tenLK;
		this.giaBan = giaBan;
		this.giaNhap = giaNhap;
		this.soLuong = soLuong;
		this.ngayNhapKho = ngayNhapKho;
		this.baoHanh = baoHanh;
		this.nhaSanXuat = nhaSanXuat;
		this.trangThai = trangThai;
	}

	public LinhKien(String maLK) {
		super();
		this.maLK = maLK;
	}

	public String getMaLK() {
		return maLK;
	}
	public void setMaLK(String maLK) {
		this.maLK = maLK;
	}
	public String getTenLK() {
		return tenLK;
	}
	public void setTenLK(String tenLK) {
		this.tenLK = tenLK;
	}
	public double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	public double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public Date getNgayNhapKho() {
		return ngayNhapKho;
	}
	public void setNgayNhapKho(Date ngayNhapKho) {
		this.ngayNhapKho = ngayNhapKho;
	}
	public String getBaoHanh() {
		return baoHanh;
	}
	public void setBaoHanh(String baoHanh) {
		this.baoHanh = baoHanh;
	}
	public String getNhaSanXuat() {
		return nhaSanXuat;
	}
	public void setNhaSanXuat(String nhaSanXuat) {
		this.nhaSanXuat = nhaSanXuat;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public List<ChiTietHoaDon> getDsChiTietHoaDon() {
		return dsChiTietHoaDon;
	}
	public void setDsChiTietHoaDon(List<ChiTietHoaDon> dsChiTietHoaDon) {
		this.dsChiTietHoaDon = dsChiTietHoaDon;
	}

	@Override
	public String toString() {
		return "LinhKien [maLK=" + maLK + ", tenLK=" + tenLK + ", giaBan=" + giaBan + ", giaNhap=" + giaNhap
				+ ", soLuong=" + soLuong + ", ngayNhapKho=" + ngayNhapKho + ", baoHanh=" + baoHanh + ", nhaSanXuat="
				+ nhaSanXuat + "]";
	}
}
