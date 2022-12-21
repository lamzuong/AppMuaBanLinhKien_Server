package app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.LinhKienDao;
import dao.NhanVienDao;
import dao.TaiKhoanDao;
import dao.ThongKeDoanhThuDao;
import impl.HoaDonImpl;
import impl.KhachHangImpl;
import impl.LinhKienImpl;
import impl.NhanVienImpl;
import impl.TaiKhoanImpl;
import impl.ThongKeDoanhThuImpl;

public class ServerApp {
	public static void main(String[] args) {
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		String IP = "rmi://192.168.1.114:9999/";
		try {
			LocateRegistry.createRegistry(9999);

			HoaDonDao hoaDonDao = new HoaDonImpl();
			ThongKeDoanhThuDao thongKeDoanhThuDao = new ThongKeDoanhThuImpl();
			NhanVienDao nhanVienDao = new NhanVienImpl();
			KhachHangDao khachHangDao = new KhachHangImpl();
			LinhKienDao linhKienDao = new LinhKienImpl();
			TaiKhoanDao taiKhoanDao = new TaiKhoanImpl();

			Naming.bind(IP+"hoaDonDao", hoaDonDao);
			Naming.bind(IP+"nhanVienDao", nhanVienDao);
			Naming.bind(IP+"linhKienDao", linhKienDao);
			Naming.bind(IP+"taiKhoanDao", taiKhoanDao);
			Naming.bind(IP+"khachHangDao", khachHangDao);
			Naming.bind(IP+"thongKeDoanhThuDao", thongKeDoanhThuDao);

			System.out.println("Server bound in RMIRegistry");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
