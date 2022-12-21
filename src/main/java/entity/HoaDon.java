package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class HoaDon implements Serializable {
	@Id
	private String maHD;

	@ManyToOne
	@JoinColumn(name = "maNV")
	private NhanVien nhanVien;

	@ManyToOne
	@JoinColumn(name = "maKH")
	private KhachHang khachHang;

	@Column(columnDefinition = "datetime")
	private Date ngayLap;

	@Column(columnDefinition = "money")
	private double thue;

	@Column(columnDefinition = "money")
	private double tongTien;

	@OneToMany(mappedBy = "hoaDon")
	private List<ChiTietHoaDon> dsChiTietHoaDon;
	
	public HoaDon() {
	}

	public HoaDon(String maHD, NhanVien nhanVien, KhachHang khachHang, Date ngayLap, double thue, double tongTien) {
		super();
		this.maHD = maHD;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayLap = ngayLap;
		this.thue = thue;
		this.tongTien = tongTien;
	}

	public HoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public double getThue() {
		return thue;
	}

	public void setThue(double thue) {
		this.thue = thue;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public List<ChiTietHoaDon> getDsChiTietHoaDon() {
		return dsChiTietHoaDon;
	}

	public void setDsChiTietHoaDon(List<ChiTietHoaDon> dsChiTietHoaDon) {
		this.dsChiTietHoaDon = dsChiTietHoaDon;
	}

	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang + ", ngayLap=" + ngayLap
				+ ", thue=" + thue + ", tongTien=" + tongTien + ", dsChiTietHoaDon=" + dsChiTietHoaDon + "]";
	}

	
	
	

}
