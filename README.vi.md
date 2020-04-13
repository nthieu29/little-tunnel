# little-tunnel
Giải pháp đơn giản và hiệu quả giúp bạn truy cập những website bị chặn bởi nhà mạng (ISP).
## Nguyên lý hoạt động
Little Tunnel hoạt động như một proxy trên máy tính của bạn, nó xử lý dữ liệu từ trình duyệt của bạn gửi đi, làm cho nhà mạng không thể được gói tin của bạn, từ đó không thể chặn bạn, giúp bạn có thể truy cập được website mà bạn muốn.
## Tại sao nên sử dụng Little Tunnel
- **Nhanh hơnr**: Little Tunnel sử dụng [LittleProxy](https://github.com/adamfisk/LittleProxy) - đây là một HTTP proxy hiệu năng cao, Little Tunnel chỉ xử lý những gói tin đến website mà bạn cấu hình, các gói tin còn lại sẽ lưu thông bình thường để tối ưu hiệu năng. Các gói tin sẽ đi trực tiếp từ máy tính của bạn đến website của bạn, không thông qua bất kỳ máy chủ VPN/Proxy thứ ba nào khác, từ đó đảm bảo tốc độ truy cập nhanh cho bạn.
- **Ổn định, an toàn và miễn phí**: Các dịch vụ VPN/Proxy thường không ổn định và chậm. Nếu bạn muốn dùng ổn định và nhanh, bạn sẽ phải trả phí cho dịch vụ. Little Tunnel luôn miễn phí và nguồn mở. Little Tunnel chạy trên máy tính của chính bạn, nên đảm bảo tính ổn định và nhanh.
- **Dễ dàng sử dụng**: Little Tunnel được tạo ra với tiêu chí hiệu quả nhưng vẫn phải thật dễ sử dụng. Tài liệu hướng dẫn đi kèm đầy đủ rõ ràng.

![Alt text](images/LittleTunnel.png?raw=true "Little Tunnel")

## Cài đặt và cấu hình
1. Cài JAVA: Bạn có thể tải tại [đây](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html). (Little Tunnel được viết bằng JAVA, nên để chạy được, bạn cần cài JAVA, các máy tính thông thường đều được cài sẵn JAVA rồi)
2. Bạn sẽ cần một trình duyệt có tính năng Proxy nội bộ (nghĩa là cấu hình trong trình duyệt sẽ không ảnh hưởng đến toàn hệ thống của bạn, những phần mềm hay trình duyệt khác vẫn dùng bình thường). Chúng tôi khuyến khích sử dụng Firefox (vì Firefox có tính năng Proxy nội bộ), bạn có thể tải tại [đây](https://www.mozilla.org/en-US/firefox/new/).
3. Bạn tải [Little Tunnel](https://github.com/ngotrunghieu29/little-tunnel/releases/download/v1.0/little-tunnel-1.0.jar) và nhấp đúp chuột để chạy nó.
4. Chọn tab **Sites Config**. Nhập địa chỉ website bị chặn mà bạn muốn truy cập, bỏ đi những phần như http, https hay www. Ví dụ bạn có thể nhập như sau: abc.com, example.com.

![Alt text](images/addSite.png?raw=true "Add Site")

5. Chọn tab **Server tab**. Click **Start Server**.
6. Cấu hình Firefox dùng Little Tunnel:
- Click menu button (hình 3 dấu gạch ngang) và chọn **Preferences**.
- Tại mục **General**, kéo xuống tới phần **Network Settings**.
- Click **Settings…**. Hộp thoại Connection Settings hiển thị.
- Chọn **Manual proxy configuration** và cấu hình như hình:

![Alt text](images/firefoxProxy.png?raw=true "Configure Proxy in Firefox")

## Mẹo vặt
1. **Luôn dùng HTTPS bất kỳ khi nào có thể** bởi vì HTTPS làm cho nhà mạng khó chặn được bạn hơn. Chỉ cần nhập _https://_ vào thanh địa chỉ rồi gõ địa chỉ website mà bạn muốn truy cập.
2. Little Tunnel sẽ tự động chuyển địa chỉ website mà bạn nhập thành chữ thường và loại bỏ các website bị trùng tên nhau để tối ưu hiệu năng xử lý.
3. Trong cùng thư mục mà Little Tunnel đang chạy, sẽ có một file cấu hình tên là _sites.txt_. Little Tunnel đọc dữ liệu danh sách các website từ file này, nên bạn có thể chỉnh sửa trực tiếp file này luôn cũng được. Khởi động lại Little Tunnel nếu bạn sửa trực tiếp file này nhé.
## Vẫn không truy cập được website mà bạn muốn?
1. Thử thêm _https://_ vào phần đầu của thanh địa chỉ trong trình duyệt để ép trình duyệt truy cập website của bạn bằng HTTPS xem.
2. Có thể nhà cung cấp mạng chặn bằng địa chỉ IP hoặc bằng các kỹ thuật khác mà hiện tại Little Tunnel chưa qua mặt được.
