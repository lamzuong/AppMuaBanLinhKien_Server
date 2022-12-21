package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import entity.HoaDon;
import entity.LinhKien;

public interface ThongKeDoanhThuDao extends Remote {
	public List<HoaDon> getAllHoaDon() throws RemoteException;
	public HoaDon getHoaDonTheoMaHoaDon(String maHD) throws RemoteException;
	public double getThanhTienTheoMaHoaDon(String maHD) throws RemoteException; 
	public List<HoaDon> getHoaDonHomNay() throws RemoteException;
	public List<HoaDon> getHoaDon1Tuan() throws RemoteException;
	public List<HoaDon> getHoaDon1Thang() throws RemoteException;
	public List<HoaDon> getHoaDonTrongKhoangThoiGian(Date tuNgay, Date denNgay) throws RemoteException;
	public List<LinhKien> getLinhKienTrongKhoangThoiGian(Date tuNgay,Date denNgay) throws RemoteException;
	
}
