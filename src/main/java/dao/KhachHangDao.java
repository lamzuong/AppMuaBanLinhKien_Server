package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.KhachHang;

public interface KhachHangDao extends Remote {
	public boolean capnhatKhachHang(KhachHang kh) throws RemoteException;
	public List<KhachHang> getTatCaKhachHang() throws RemoteException;
	public List<KhachHang> getKhachHangSinhNhatHomNay() throws RemoteException;
}
