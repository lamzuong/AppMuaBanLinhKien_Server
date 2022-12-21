package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.LinhKien;

public interface LinhKienDao extends Remote {
	public boolean themLinhKien(LinhKien lk) throws RemoteException;
	public boolean xoaLinhKien(String id) throws RemoteException;
	public boolean capnhatLinhKien(LinhKien lk) throws RemoteException;
	public List<LinhKien> getTatCaLinhKien() throws RemoteException;
	public List<LinhKien> getLinhKienTonTai() throws RemoteException;
}
