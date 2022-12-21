package impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.HoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;
import util.HibernateUtil;

public class HoaDonImpl extends UnicastRemoteObject implements HoaDonDao {

	private EntityManager em;

	public HoaDonImpl() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@Override
	public List<LinhKien> getAllLinhKienTonTai() throws RemoteException {
		List<LinhKien> lisLK = new ArrayList<LinhKien>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			lisLK = em.createNativeQuery("select * from LinhKien where trangThai = 0", LinhKien.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return lisLK;
	}

	@Override
	public String getMaHoaDonCuoi() throws RemoteException {
		String maHDCuoi = "";
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			int so = (int) em.createNativeQuery("select count(*) from HoaDon").getSingleResult();
			if (so == 0)
				maHDCuoi = "";
			else {
				HoaDon hd = (HoaDon) em.createNativeQuery("select top 1 * from HoaDon order by maHD desc", HoaDon.class)
						.getSingleResult();
				maHDCuoi = hd.getMaHD().trim();
			}
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return maHDCuoi;
	}

	@Override
	public boolean updateSoLuongTheoMaKhiXoa(int soLuongMoi, String maLK) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			LinhKien lk = em.find(LinhKien.class, maLK);
			lk.setSoLuong(lk.getSoLuong() + soLuongMoi);
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
	public String getTenNhanVienTheoTenTK(String tenTK) throws RemoteException {
		String tenNV = "";
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql = "select nv.* from NhanVien nv join TaiKhoan tk on nv.maNV = tk.maNV where tenTK= '" + tenTK
					+ "'";
			NhanVien nv = (NhanVien) em.createNativeQuery(sql, NhanVien.class).getSingleResult();
			tenNV = nv.getTenNV();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return tenNV;
	}

	@Override
	public boolean addHoaDon(HoaDon hd) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(hd);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean updateHoaDon(HoaDon hd) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			hd.setNhanVien(em.find(NhanVien.class, hd.getNhanVien().getMaNV()));
			em.merge(hd);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean addChiTietHoaDon(ChiTietHoaDon cthd) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			cthd.setHoaDon(em.find(HoaDon.class, cthd.getHoaDon().getMaHD().trim()));
			cthd.setLinhKien(em.find(LinhKien.class, cthd.getLinhKien().getMaLK().trim()));
			em.persist(cthd);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean updateChiTietHoaDon(ChiTietHoaDon cthd) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			cthd.setHoaDon(em.find(HoaDon.class, cthd.getHoaDon().getMaHD().trim()));
			cthd.setLinhKien(em.find(LinhKien.class, cthd.getLinhKien().getMaLK().trim()));
			em.merge(cthd);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public int getSoCTHDTheoMaHD(String maHD) throws RemoteException {
		int soCTHD = 0;
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();

			soCTHD = em.createNativeQuery("select COUNT(*) from ChiTietHoaDon where maHD=" + maHD + " group by maHD")
					.getFirstResult();

			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return soCTHD;
	}

	@Override
	public boolean removeChiTietHoaDon(ChiTietHoaDon cthd) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			cthd.setHoaDon(em.find(HoaDon.class, cthd.getHoaDon().getMaHD().trim()));
			cthd.setLinhKien(em.find(LinhKien.class, cthd.getLinhKien().getMaLK().trim()));
			em.remove(em.merge(cthd));
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean removeHoaDon(String maHD) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			int so = (int) em.createNativeQuery("select count(*) from HoaDon where ngayLap is null").getSingleResult();
			if (so != 0) {
				List<HoaDon> listHD = em.createNativeQuery("select * from HoaDon where ngayLap is null", HoaDon.class)
						.getResultList();
				for (HoaDon hoaDon : listHD) {
					int soHDcuaKH = (int) em.createNativeQuery("select count(*) from HoaDon where maKH='"+hoaDon.getKhachHang().getMaKH()+"'").getSingleResult();
					
					em.remove(em.find(HoaDon.class, hoaDon.getMaHD()));
					if(soHDcuaKH == 1) {
						em.remove(em.find(KhachHang.class, hoaDon.getKhachHang().getMaKH()));
					}
				}
			}
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean updateSoLuongTheoMaKhiBan(int soLuong, String maLK) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			LinhKien lk = em.find(LinhKien.class, maLK);
			lk.setSoLuong(lk.getSoLuong() - soLuong);
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
	public List<ChiTietHoaDon> getCTHDTheoMaHD(String maHD) throws RemoteException {
		List<ChiTietHoaDon> listCTHD = new ArrayList<ChiTietHoaDon>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			listCTHD = em
					.createNativeQuery("select * from ChiTietHoaDon where maHD= '" + maHD + "'", ChiTietHoaDon.class)
					.getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return listCTHD;
	}

	@Override
	public boolean addKhachHang(KhachHang kh) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(kh);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public String getMaKhachHangCuoi() throws RemoteException {
		String maKHCuoi = "";
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			int so = (int) em.createNativeQuery("select count(*) from KhachHang").getSingleResult();
			if (so == 0)
				maKHCuoi = "";
			else {
				KhachHang kh = (KhachHang) em
						.createNativeQuery("select top 1 * from KhachHang order by maKH desc", KhachHang.class)
						.getSingleResult();
				maKHCuoi = kh.getMaKH().trim();
			}
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return maKHCuoi;
	}

	@Override
	public String getMaKhachHangTheoSDT(String soDT) throws RemoteException {
		String maKH = "";
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			KhachHang kh = (KhachHang) em
					.createNativeQuery("select* from KhachHang where soDT ='" + soDT + "'", KhachHang.class)
					.getSingleResult();
			maKH = kh.getMaKH();
			tr.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return maKH;
	}

	@Override
	public String getMaNhanVienTheoTenTK(String tenTK) throws RemoteException {
		String maNV = "";
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql = "select maNV from TaiKhoan where tenTK= '" + tenTK + "'";
			NhanVien nv = (NhanVien) em.createNativeQuery(sql, NhanVien.class).getSingleResult();
			maNV = nv.getMaNV();

			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return maNV;
	}

	@Override
	public List<HoaDon> getAllHoaDon() throws RemoteException {
		List<HoaDon>  listHD = null;
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			listHD=em.createNativeQuery("select * from HoaDon",HoaDon.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return listHD;
	}

}
