package impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.NhanVienDao;
import entity.NhanVien;
import entity.TaiKhoan;
import util.HibernateUtil;

public class NhanVienImpl extends UnicastRemoteObject implements NhanVienDao {
	private EntityManager em;

	public NhanVienImpl() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@Override
	public boolean themNhanVien(NhanVien nv) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(nv);
			TaiKhoan tk = nv.getTaiKhoan();
			em.persist(tk);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean xoaNhanVien(String id) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			NhanVien nv = em.find(NhanVien.class, id);
			nv.setTrangThai(true);
			TaiKhoan tk = nv.getTaiKhoan();
			em.merge(nv);
			em.remove(tk);
			nv.setTaiKhoan(null);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean capnhatNhanVien(NhanVien nv) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			nv.setTaiKhoan(em.find(NhanVien.class, nv.getMaNV()).getTaiKhoan());
			em.merge(nv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<NhanVien> getTatCaNhanVien() throws RemoteException {
		List<NhanVien> list = new ArrayList<NhanVien>();
		EntityTransaction tr = em.getTransaction();
		String sql = "select * from NhanVien";
		try {
			tr.begin();
			list = em.createNativeQuery(sql, NhanVien.class).getResultList();
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public NhanVien getNhanVienTheoMa(String id) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		String sql = "select * from NhanVien where maNV like '"+ id +"'";
		try {
			tr.begin();
			NhanVien nv = (NhanVien) em.createNativeQuery(sql, NhanVien.class).getSingleResult();
			tr.commit();
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public NhanVien getNhanVienTheoTenTK(String tenTK) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		String sql = "select nv.*\r\n" + 
				"from NhanVien nv join TaiKhoan tk on nv.maNV = tk.maNV\r\n" + 
				"where tk.tenTK = '"+ tenTK +"'";
		try {
			tr.begin();
			NhanVien nv = (NhanVien) em.createNativeQuery(sql, NhanVien.class).getSingleResult();
			tr.commit();
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

}
