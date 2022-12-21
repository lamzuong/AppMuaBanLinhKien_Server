package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;

public interface HoaDonDao extends Remote {
	public List<LinhKien> getAllLinhKienTonTai() throws RemoteException;
	public String getMaHoaDonCuoi() throws RemoteException;
	public String getMaKhachHangCuoi() throws RemoteException;
	public String getMaKhachHangTheoSDT(String soDT) throws RemoteException;
	public boolean updateSoLuongTheoMaKhiXoa(int soLuongMoi,String maLK) throws RemoteException;
	public boolean updateSoLuongTheoMaKhiBan(int soLuong, String maLK) throws RemoteException;
	public String getTenNhanVienTheoTenTK(String tenTK) throws RemoteException;
	public String getMaNhanVienTheoTenTK(String tenTK) throws RemoteException;
	public boolean addHoaDon(HoaDon hd) throws RemoteException;
	public boolean addKhachHang(KhachHang kh) throws RemoteException;
	public boolean updateHoaDon(HoaDon hd) throws RemoteException;
	public boolean removeChiTietHoaDon(ChiTietHoaDon cthd) throws RemoteException;
	public boolean addChiTietHoaDon(ChiTietHoaDon cthd) throws RemoteException;
	public boolean updateChiTietHoaDon(ChiTietHoaDon cthd) throws RemoteException;
	public int getSoCTHDTheoMaHD(String maHD)throws RemoteException;
	public List<ChiTietHoaDon> getCTHDTheoMaHD(String maHD) throws RemoteException;
	public boolean removeHoaDon(String maHD) throws RemoteException;
	public List<HoaDon> getAllHoaDon() throws RemoteException;
}
