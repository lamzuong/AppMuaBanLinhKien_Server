package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class TaiKhoan implements Serializable {
	/**
	 * 
	 */
	@Id
	private String maNV;
	
	@Column(nullable = false, unique = true)
	private String tenTK;
	
	@Column(nullable = false)
	private String matKhau;

	@OneToOne
	@MapsId
	@JoinColumn(name = "maNV")
	private NhanVien nhanVien;

	public String getTenTK() {
		return tenTK;
	}

	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public TaiKhoan(String tenTK, String matKhau, NhanVien nhanVien) {
		super();
		this.tenTK = tenTK;
		this.matKhau = matKhau;
		this.nhanVien = nhanVien;
	}

	public TaiKhoan() {
		super();
	}

	@Override
	public String toString() {
		return "TaiKhoan [tenTK=" + tenTK + ", matKhau=" + matKhau + ", nhanVien=" + nhanVien + "]";
	}

}
