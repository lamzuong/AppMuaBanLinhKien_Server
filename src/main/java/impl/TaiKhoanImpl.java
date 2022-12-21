package impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.TaiKhoanDao;
import entity.TaiKhoan;
import util.HibernateUtil;

public class TaiKhoanImpl extends UnicastRemoteObject implements TaiKhoanDao {
	private EntityManager em;

	public TaiKhoanImpl() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@Override
	public List<TaiKhoan> getTatCaTaiKhoan() throws RemoteException {
		List<TaiKhoan> list = new ArrayList<TaiKhoan>();
		EntityTransaction tr = em.getTransaction();
		String sql = "select * from TaiKhoan";
		try {
			tr.begin();
			list = em.createNativeQuery(sql, TaiKhoan.class).getResultList();
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public TaiKhoan getTaiKhoanTheoMaNV(String id) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		String sql = "select * from TaiKhoan where maNV = '"+ id +"'";
		try {
			tr.begin();
			TaiKhoan tk = (TaiKhoan) em.createNativeQuery(sql, TaiKhoan.class).getSingleResult();
			tr.commit();
			return tk;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public boolean capnhatTaiKhoan(TaiKhoan tk) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(tk);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public TaiKhoan getTaiKhoanTheoTenTK(String tenTK) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		String sql = "select * from TaiKhoan where tenTK = '"+ tenTK +"'";
		try {
			tr.begin();
			TaiKhoan tk = (TaiKhoan) em.createNativeQuery(sql, TaiKhoan.class).getSingleResult();
			tr.commit();
			return tk;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

}
