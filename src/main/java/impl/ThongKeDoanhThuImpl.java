package impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.ThongKeDoanhThuDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.LinhKien;
import util.HibernateUtil;

public class ThongKeDoanhThuImpl extends UnicastRemoteObject implements ThongKeDoanhThuDao {

	private EntityManager em;

	public ThongKeDoanhThuImpl() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@Override
	public List<HoaDon> getAllHoaDon() throws RemoteException {
		List<HoaDon> listHD = new ArrayList<HoaDon>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			listHD = em.createNativeQuery("select * from HoaDon where ngayLap is not null", HoaDon.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return listHD;
	}

	@Override
	public double getThanhTienTheoMaHoaDon(String maHD) throws RemoteException {
		double thanhTien = 0;
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			BigDecimal thanhTienBig = (BigDecimal) em
					.createNativeQuery(
							"select sum(thanhTien) from ChiTietHoaDon where maHD='" + maHD + "' group by maHD ")
					.getSingleResult();
			thanhTien = thanhTienBig.doubleValue();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return thanhTien;
	}

	@Override
	public HoaDon getHoaDonTheoMaHoaDon(String maHD) throws RemoteException {
		HoaDon hd = null;
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			hd = (HoaDon) em.createNativeQuery("select * from HoaDon where maHD= '" + maHD + "'", HoaDon.class)
					.getSingleResult();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return hd;
	}

	@Override
	public List<HoaDon> getHoaDonHomNay() throws RemoteException {
		List<HoaDon> listHD = new ArrayList<HoaDon>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			listHD = em.createNativeQuery("select * from HoaDon where CONVERT(date,ngayLap) = CONVERT(date,GETDATE()) and ngayLap is not null ",
					HoaDon.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return listHD;
	}

	@Override
	public List<HoaDon> getHoaDon1Tuan() throws RemoteException {
		List<HoaDon> listHD = new ArrayList<HoaDon>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			listHD = em.createNativeQuery(
					"select * from HoaDon where DATEDIFF(day,CONVERT(date,ngayLap) , CONVERT(date,GETDATE())) <= 7 and ngayLap is not null ",
					HoaDon.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return listHD;
	}

	@Override
	public List<HoaDon> getHoaDon1Thang() throws RemoteException {
		List<HoaDon> listHD = new ArrayList<HoaDon>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			listHD = em.createNativeQuery(
					"select * from HoaDon where DATEDIFF(day,CONVERT(date,ngayLap) , CONVERT(date,GETDATE())) <= 30 and ngayLap is not null",
					HoaDon.class).getResultList();
			tr.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return listHD;
	}

	@Override
	public List<HoaDon> getHoaDonTrongKhoangThoiGian(Date tuNgay, Date denNgay) throws RemoteException {
		List<HoaDon> listHD = new ArrayList<HoaDon>();

		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			listHD = em.createNativeQuery("select * from HoaDon where ngayLap is not null and CONVERT(Date,ngayLap) >= '" + tuNgay
					+ "' and CONVERT(Date,ngayLap)<= '" + denNgay + "'", HoaDon.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return listHD;
	}

	@Override
	public List<LinhKien> getLinhKienTrongKhoangThoiGian(Date tuNgay, Date denNgay) throws RemoteException {
		List<LinhKien> listLK = new ArrayList<LinhKien>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			List<?> listTemp=null;
			if(tuNgay !=null & denNgay!=null) {
				listTemp = em.createNativeQuery("select lk.maLK,tenLK,giaBan,soLuongBanRa=sum(cthd.soLuong) \r\n"
					+ "from HoaDon hd join ChiTietHoaDon cthd on hd.maHD =cthd.maHD join\r\n"
					+ "LinhKien lk on cthd.maLK = lk.maLK\r\n"
					+ "where ngayLap is not null and CONVERT(Date,ngayLap) >='" + df.format(tuNgay)
					+ "' and CONVERT(Date,ngayLap)<= '" + df.format(denNgay) + "'\r\n" + "group by lk.maLK,lk.tenLK,lk.giaBan")
					.getResultList();
			}
			else if(tuNgay ==null & denNgay!=null) {
				listTemp = em.createNativeQuery("select lk.maLK,tenLK,giaBan,soLuongBanRa=sum(cthd.soLuong) \r\n"
						+ "from HoaDon hd join ChiTietHoaDon cthd on hd.maHD =cthd.maHD join\r\n"
						+ "LinhKien lk on cthd.maLK = lk.maLK\r\n"
						+ "where ngayLap is not null and CONVERT(Date,ngayLap)<= '" + df.format(denNgay) + "'\r\n" + "group by lk.maLK,lk.tenLK,lk.giaBan")
						.getResultList();
			}
			else if(tuNgay !=null & denNgay==null) {
				listTemp = em.createNativeQuery("select lk.maLK,tenLK,giaBan,soLuongBanRa=sum(cthd.soLuong) \r\n"
						+ "from HoaDon hd join ChiTietHoaDon cthd on hd.maHD =cthd.maHD join\r\n"
						+ "LinhKien lk on cthd.maLK = lk.maLK\r\n"
						+ "where ngayLap is not null and  CONVERT(Date,ngayLap)>= '" + df.format(tuNgay) + "'\r\n" + "group by lk.maLK,lk.tenLK,lk.giaBan")
						.getResultList();
			}
			else if(tuNgay ==null & denNgay==null) {
				listTemp = em.createNativeQuery("select lk.maLK,tenLK,giaBan,soLuongBanRa=sum(cthd.soLuong) \r\n"
						+ "from HoaDon hd join ChiTietHoaDon cthd on hd.maHD =cthd.maHD join\r\n"
						+ "LinhKien lk on cthd.maLK = lk.maLK "
						+ "where ngayLap is not null\r\n"
						+ "group by lk.maLK,lk.tenLK,lk.giaBan")
						.getResultList();
			}

			for (Object object : listTemp) {
				Object[] o = (Object[]) object;
				LinhKien lk = new LinhKien();
				lk.setMaLK((String) o[0]);
				lk.setTenLK((String) o[1]);
				lk.setGiaBan(((BigDecimal) o[2]).doubleValue());
				lk.setSoLuong((int) o[3]);
				
				listLK.add(lk);

			}
			tr.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return listLK;
	}


}
