package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhanVien;

public interface NhanVienDao extends Remote {
	public boolean themNhanVien(NhanVien nv) throws RemoteException;
	public boolean xoaNhanVien(String id) throws RemoteException;
	public boolean capnhatNhanVien(NhanVien nv) throws RemoteException;
	public List<NhanVien> getTatCaNhanVien() throws RemoteException;
	public NhanVien getNhanVienTheoMa(String id) throws RemoteException;
	public NhanVien getNhanVienTheoTenTK(String tenTK) throws RemoteException;
}
