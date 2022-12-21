package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.TaiKhoan;

public interface TaiKhoanDao extends Remote {
	public List<TaiKhoan> getTatCaTaiKhoan() throws RemoteException;
	public TaiKhoan getTaiKhoanTheoMaNV(String id) throws RemoteException;
	public TaiKhoan getTaiKhoanTheoTenTK(String tenTK) throws RemoteException;
	public boolean capnhatTaiKhoan(TaiKhoan tk) throws RemoteException;
}
