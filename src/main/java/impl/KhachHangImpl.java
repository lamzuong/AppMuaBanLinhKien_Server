package impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.KhachHangDao;
import entity.KhachHang;
import util.HibernateUtil;

public class KhachHangImpl extends UnicastRemoteObject implements KhachHangDao {
	private EntityManager em;

	public KhachHangImpl() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@Override
	public boolean capnhatKhachHang(KhachHang kh) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(kh);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<KhachHang> getTatCaKhachHang() throws RemoteException {
		List<KhachHang> list = new ArrayList<KhachHang>();
		EntityTransaction tr = em.getTransaction();
		String sql = "select * from KhachHang";
		try {
			tr.begin();
			list = em.createNativeQuery(sql, KhachHang.class).getResultList();
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public List<KhachHang> getKhachHangSinhNhatHomNay() throws RemoteException {
		List<KhachHang> listKhachHang = null;
		String sql = "select * from KhachHang where month(ngaySinh) = month(getdate())\r\n" 
		+ "and day(ngaySinh) = day(getdate()) and year(ngaySinh) <= year(getdate())";
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			listKhachHang = em.createNativeQuery(sql, KhachHang.class).getResultList();
			tr.commit();
			return listKhachHang;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
}
