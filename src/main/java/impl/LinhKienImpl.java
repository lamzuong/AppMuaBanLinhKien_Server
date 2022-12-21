package impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.LinhKienDao;
import entity.LinhKien;
import util.HibernateUtil;

public class LinhKienImpl extends UnicastRemoteObject implements LinhKienDao {
	private EntityManager em;

	public LinhKienImpl() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@Override
	public boolean themLinhKien(LinhKien lk) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(lk);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean xoaLinhKien(String id) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			LinhKien lk = em.find(LinhKien.class, id);
			lk.setTrangThai(true);
			em.merge(lk);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean capnhatLinhKien(LinhKien lk) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(lk);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<LinhKien> getTatCaLinhKien() throws RemoteException {
		List<LinhKien> list = new ArrayList<LinhKien>();
		EntityTransaction tr = em.getTransaction();
		String sql = "select * from LinhKien";
		try {
			tr.begin();
			list = em.createNativeQuery(sql, LinhKien.class).getResultList();
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public List<LinhKien> getLinhKienTonTai() throws RemoteException {
		List<LinhKien> list = new ArrayList<LinhKien>();
		EntityTransaction tr = em.getTransaction();
		String sql = "select * from LinhKien where trangThai = 0";
		try {
			tr.begin();
			list = em.createNativeQuery(sql, LinhKien.class).getResultList();
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
}
