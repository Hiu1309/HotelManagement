# Hệ thống quản lý khách sạn

Đồ án OOP viết bằng Java, chạy trên giao diện dòng lệnh (console). Chương trình mô
phỏng các nghiệp vụ cơ bản của một khách sạn: quản lý phòng, khách hàng, nhân viên,
tài khoản, đặt phòng, lập hóa đơn, thanh toán và tính doanh thu.

## 1. Cách chạy chương trình

### Yêu cầu

- JDK 8 trở lên.
- Chạy lệnh tại thư mục gốc `HotelManagement`.
- Không đổi tên hoặc di chuyển thư mục `data`, vì chương trình dùng đường dẫn
  tương đối `data\`.

### Biên dịch và chạy trên Windows

```powershell
mkdir out
javac -encoding UTF-8 -d out src\app\*.java src\interfaces\*.java src\model\*.java
java -cp out app.Main
```

Nếu chạy bằng IDE, chọn `src\app\Main.java` làm lớp khởi động và đặt working
directory là thư mục gốc của dự án để chương trình tìm được `data\`.

## 2. Kiến trúc thư mục

```text
HotelManagement/
├── src/
│   ├── app/
│   │   ├── Main.java       # Điều phối chương trình và các menu nghiệp vụ
│   │   └── Utils.java      # Nhập dữ liệu, định dạng tiền, kiểm tra số
│   ├── interfaces/         # Các interface dùng chung
│   └── model/              # Lớp nghiệp vụ, lớp thực thể và lớp danh sách
├── data/                   # Dữ liệu dạng text, phân tách bằng ký tự |
└── README.md
```

`Main` giữ các danh sách dữ liệu và điều hướng menu. Các lớp `DanhSach...` chịu
trách nhiệm thêm, xóa, tìm, sửa, hiển thị và đọc/ghi dữ liệu. Các lớp trong
`model` mô tả đối tượng nghiệp vụ, còn `interfaces` mô tả các hành vi dùng chung.

## 3. Luồng hoạt động chính

1. `Main.main()` tạo một đối tượng `Main` rồi gọi `chay()`.
2. `chay()` đọc dữ liệu từ bảy tệp trong thư mục `data`.
3. Nếu danh sách phòng, nhân viên hoặc tài khoản đang rỗng, chương trình tạo dữ
   liệu mẫu để có thể đăng nhập và dùng thử ngay.
4. Người dùng chọn:
   - **Đăng nhập** để vào menu theo vai trò.
   - **Đăng ký tài khoản khách** để tạo `KhachHang` và `TaiKhoan` mới.
   - **Thoát** để ghi toàn bộ danh sách xuống các tệp text.
5. Sau khi đăng nhập, `TaiKhoan.vaiTro` quyết định menu được hiển thị:

| Vai trò | Chức năng chính |
|---|---|
| `KHACH` | Xem phòng trống, đặt phòng, xem hóa đơn và thông tin cá nhân |
| `LETAN` | Đặt phòng giúp khách, xem khách, cập nhật trạng thái đặt phòng |
| `KETOAN` | Lập hóa đơn, thanh toán, xem hóa đơn và doanh thu |
| `BUONGPHONG` | Xem phòng, cập nhật trạng thái phòng |
| `QUANLY` | Quản lý phòng, nhân viên, khách hàng, tài khoản, hóa đơn và lương |

### Quy trình đặt phòng và thanh toán

- Khách hoặc lễ tân chọn một phòng có trạng thái `Trong`.
- Chương trình kiểm tra mã khách, định dạng ngày `yyyyMMdd`, số đêm dương và
  phòng có bị trùng lịch hay không.
- Khi đặt thành công, tạo `DatPhong` và chuyển trạng thái phòng thành `Da dat`.
- Kế toán lập `HoaDon`. Tiền phòng được tính bởi `Phong.tinhGia(soDem)` của
  đúng loại phòng; điểm tích lũy của khách được dùng để tính giảm giá.
- Khi thanh toán, `HoaDon.thanhToan()` đổi trạng thái thành `Da thanh toan`.
  Doanh thu chỉ cộng các hóa đơn đã thanh toán.

## 4. Các lớp và quan hệ chính

### Lớp người và nhân viên

- `Nguoi` là lớp trừu tượng chứa thông tin chung: mã, họ tên, năm sinh, giới
  tính, số điện thoại và địa chỉ.
- `KhachHang` kế thừa `Nguoi`, bổ sung điểm tích lũy và loại khách.
- `NhanVien` kế thừa `Nguoi`, là lớp trừu tượng có `tinhLuong()`.
- `LeTan`, `KeToan`, `BuongPhong`, `QuanLy` kế thừa `NhanVien` và tính lương
  theo công thức riêng.

### Lớp phòng

- `Phong` là lớp trừu tượng có thông tin chung và hàm trừu tượng
  `tinhGia(int soDem)`.
- `PhongDon`, `PhongDoi`, `PhongVIP` kế thừa `Phong`.
- Mỗi loại phòng có cách tính tiền khác nhau: phụ thu phòng đơn, phụ phí theo
  số giường phòng đôi và phí dịch vụ cùng hệ số 1.2 cho phòng VIP.

### Dịch vụ, đặt phòng và hóa đơn

- `DichVu` là lớp cơ sở; `DichVuAn`, `DichVuGiatLa`, `DichVuSpa` mở rộng
  thuộc tính đặc thù của từng dịch vụ.
- `DatPhong` lưu khách, phòng, ngày nhận, ngày trả, số đêm và trạng thái.
- `HoaDon` lưu tiền phòng, tiền dịch vụ, giảm giá và trạng thái thanh toán.

### Các lớp danh sách

`DanhSachPhong`, `DanhSachNhanVien`, `DanhSachDatPhong`,
`DanhSachKhachHang`, `DanhSachTaiKhoan`, `DanhSachHoaDon` và
`DanhSachDichVu` đóng vai trò quản lý tập hợp đối tượng. Các lớp này kiểm tra
trùng mã/tên đăng nhập và cung cấp các thao tác CRUD cơ bản.

## 5. Đối chiếu với thang điểm

| Tiêu chí | Mức đáp ứng | Minh chứng trong mã nguồn |
|---|---:|---|
| Có ít nhất 15 class | Đạt | Có 27 lớp: `Main`, `Utils`, các lớp thực thể, lớp phòng, nhân viên, dịch vụ và các lớp danh sách |
| Các lớp có constructor | Đạt | Các lớp đều có constructor rỗng; lớp nghiệp vụ còn có constructor đầy đủ tham số, ví dụ `PhongDon`, `KhachHang`, `HoaDon` |
| Kế thừa hợp lý | Đạt | `KhachHang -> Nguoi`, `NhanVien -> Nguoi`, bốn loại nhân viên -> `NhanVien`, ba loại phòng -> `Phong`, ba loại dịch vụ -> `DichVu` |
| Đa hình | Đạt | Danh sách lưu kiểu cha như `Phong[]`, `NhanVien[]`, `ArrayList<DichVu>`; lời gọi `phong.tinhGia()` và `nhanVien.tinhLuong()` chạy theo lớp con thực tế |
| Lớp mảng các đối tượng | Đạt | `DanhSachPhong`, `DanhSachNhanVien`, `DanhSachDatPhong` dùng mảng đối tượng, tự tăng kích thước bằng `System.arraycopy` |
| Đọc/ghi file text | Đạt | Các lớp danh sách cài `IFileIO`, dùng `BufferedReader`, `FileReader`, `FileWriter`, `PrintWriter` để đọc/ghi bảy tệp `.txt` |
| Thuộc tính và phương thức `static` | Đạt | `Main` có các hằng đường dẫn `static final`; `Utils` có các hàm static; `KhachHang.demSoKhach`, `Phong.tongSoPhong`, `TaiKhoan.taiKhoanHienTai` là static |
| Lớp và hàm trừu tượng | Đạt | `Nguoi`, `NhanVien`, `Phong` là abstract; các hàm `hienThiThongTin`, `getVaiTro`, `tinhLuong`, `tinhGia` được khai báo abstract và cài đặt ở lớp con |
| Interface | Đạt | `IFileIO`, `IThanhToan`, `IXacThuc`; các lớp danh sách, `HoaDon` và `TaiKhoan` lần lượt implements các interface này |
| Sáng tạo | Đạt | Phân quyền theo năm vai trò, kiểm tra trùng lịch đặt phòng, tính giá khác nhau theo loại phòng, tích lũy/giảm giá, mã hóa mật khẩu đơn giản khi lưu và báo cáo doanh thu |

## 6. Giải thích các điểm OOP có thể được hỏi

### Đóng gói

Thuộc tính của các lớp đều để `private` và truy cập thông qua getter/setter.
Ví dụ, trạng thái phòng chỉ được thay đổi qua `getTrangThai()` và
`setTrangThai()`, giúp kiểm soát cách các đối tượng được sử dụng.

### Kế thừa và trừu tượng

Thông tin chung được đưa lên lớp cha để tránh lặp mã. `Nguoi` và `Phong` chỉ
mô tả phần chung, còn lớp con bắt buộc cung cấp hành vi riêng thông qua các hàm
abstract. Vì vậy không thể tạo trực tiếp một `Nguoi`, `NhanVien` hoặc `Phong`
chung chung không có cách hiển thị/tính giá cụ thể.

### Đa hình

Ví dụ trong `lapHoaDon()`:

```java
Phong phong = dsPhong.tim(datPhong.getMaPhong());
double tienPhong = phong.tinhGia(datPhong.getSoDem());
```

Biến có kiểu `Phong`, nhưng nếu đối tượng thật là `PhongDon`, `PhongDoi` hoặc
`PhongVIP` thì Java tự gọi phiên bản `tinhGia()` tương ứng. Tương tự,
`hienThiLuong()` duyệt `NhanVien` nhưng gọi công thức lương của từng lớp con.

### Interface

- `IFileIO` thống nhất hợp đồng `docFile`/`ghiFile` cho mọi lớp danh sách.
- `IThanhToan` quy định hóa đơn phải có `tinhTien` và `thanhToan`.
- `IXacThuc` quy định tài khoản phải cung cấp hành vi đăng nhập.

## 7. Dữ liệu và định dạng file

Các tệp dùng dấu `|` làm ký tự phân cách, mỗi dòng là một đối tượng. Một số
tiền tố loại được dùng để khôi phục đúng lớp con khi đọc:

- `phong.txt`: `DON`, `DOI`, `VIP`
- `nhanvien.txt`: `LETAN`, `KETOAN`, `BUONGPHONG`, `QUANLY`
- `dichvu.txt`: `AN`, `GIATLA`, `SPA`

Các tệp `khachhang.txt`, `taikhoan.txt`, `datphong.txt` và `hoadon.txt` lưu
thông tin tương ứng của từng đối tượng. Mật khẩu được biến đổi đơn giản bằng
cách dịch mã ký tự trước khi ghi file; đây là minh họa cho đồ án OOP, không phải
cơ chế bảo mật dùng trong hệ thống thực tế.

## 8. Tài khoản mẫu

Khi dữ liệu tài khoản trống, chương trình tạo các tài khoản sau:

| Tên đăng nhập | Mật khẩu nhập vào | Vai trò |
|---|---|---|
| `admin` | `admin123` | `QUANLY` |
| `letan` | `123456` | `LETAN` |
| `ketoan` | `123456` | `KETOAN` |

Tài khoản khách được tạo bằng chức năng **Đăng ký tài khoản khách**.

## 9. Gợi ý trình bày khi bảo vệ

1. Chỉ ra ba cây kế thừa: `Nguoi`, `NhanVien` và `Phong`.
2. Mở `DanhSachPhong` để giải thích mảng đối tượng và việc tăng kích thước mảng.
3. Mở `HoaDon` để trình bày interface `IThanhToan`.
4. Mở `Main.lapHoaDon()` để minh họa đa hình khi gọi `phong.tinhGia()`.
5. Mở một phương thức `docFile`/`ghiFile` để giải thích dữ liệu text được
   chuyển từ dòng ký tự thành đối tượng và ngược lại.
6. Có thể dùng tài khoản mẫu để trình diễn lần lượt đặt phòng, lập hóa đơn và
   thanh toán.
